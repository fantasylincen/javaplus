package cn.mxz.base.config;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDao;
import mongo.gen.MongoGen.KeyValueDto;

public class KeyValueManagerImpl implements KeyValueManager {

	@Override
	public String get(KeyValueDefine key, Object... args) {

		KeyValueDto v = fromDB(key, args);

		return v.getV();
	}

	private KeyValueDto fromDB(KeyValueDefine key, Object... args) {

		KeyValueDao DAO = Daos.getKeyValueDao();

		String k = buildKey(key, args);

		KeyValueDto v = DAO.get(k);

		if(v == null) {

			v = new KeyValueDto();

			v.setK(k);

			v.setV(key.getInitValue().toString());

			DAO.save(v);
		}

		return v;
	}

	private String buildKey(KeyValueDefine key, Object... args) {

		String k = key + "";

		for (Object object : args) {

			k += object;
		}

		return k;
	}

	@Override
	public void put(KeyValueDefine key, Object o, Object... args) {

		KeyValueDto v = fromDB(key, args);

		KeyValueDao DAO = Daos.getKeyValueDao();

		if (v == null) {

			v = new KeyValueDto();

			v.setK(buildKey(key, args));

			v.setV(o.toString());
		}

		v.setV(o.toString());
		DAO.save(v);
	}

}
