package cn.mxz.thirdpaty;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.MxzTokenDao;
import mongo.gen.MongoGen.MxzTokenDao.MxzTokenDtoCursor;
import mongo.gen.MongoGen.MxzTokenDto;
import cn.mxz.util.debuger.Debuger;

public class TokenCacheManager {

	public static TokenCache getCache(String uname, String token) {
		MxzTokenDao dao = Daos.getMxzTokenDao();
		MxzTokenDto dto = dao.get(uname, token);
		if (dto == null) {
			return null;
		}
		return new TokenCache(dto);
	}

	public static void putTokenToCache(String uname, String token, String userId) {
		MxzTokenDao dao = Daos.getMxzTokenDao();
		MxzTokenDto dto = dao.createDTO();
		dto.setGenerateTime(System.currentTimeMillis());
		dto.setLineKongUname(uname);
		dto.setToken(token);
		dto.setUserId(userId);

		dao.save(dto);
	}

	public static void clear() {
		try {
			MxzTokenDao dao = Daos.getMxzTokenDao();
			MxzTokenDtoCursor f = dao.find();

			while (f.hasNext()) {
				MxzTokenDto dto = f.next();
				TokenCache t = new TokenCache(dto);
				if (t.isTimeUp()) {
					t.delete();
				}
			}
		} catch (Exception e) {
			Debuger.error("", e);
		}
	}

}
