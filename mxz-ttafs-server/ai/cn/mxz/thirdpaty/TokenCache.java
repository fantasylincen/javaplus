package cn.mxz.thirdpaty;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.MxzTokenDto;
import cn.javaplus.time.Time;

public class TokenCache {

	private MxzTokenDto dto;

	public TokenCache(MxzTokenDto dto) {
		this.dto = dto;
	}

	public boolean isTimeUp() {
		long time = dto.getGenerateTime();
		return System.currentTimeMillis() - time > Time.MILES_ONE_DAY;
	}

	public void delete() {
		Daos.getMxzTokenDao().delete(dto);
	}

	public String getUserId() {
		return dto.getUserId();
	}

}
