package cn.mxz.util.db;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDao;
import mongo.gen.MongoGen.KeyValueDto;
import cn.javaplus.db.KeyValueCollection;

class MongoKeyValueCollection implements KeyValueCollection<Object, String> {

	@Override
	public String get(Object k) {
		KeyValueDao DAO = Daos.getKeyValueDao();
		KeyValueDto v = DAO.get(k.toString());
		if(v == null) {
			return null;
		}
		return v.getV();
	}

	@Override
	public void put(Object k, String v) {
		KeyValueDao DAO = Daos.getKeyValueDao();
		KeyValueDto value = DAO.get(k.toString());
		if(value == null) {
			value = new KeyValueDto();
			value.setK(k.toString());
		}
		value.setV(v);
		DAO.save(value);
	}

}
