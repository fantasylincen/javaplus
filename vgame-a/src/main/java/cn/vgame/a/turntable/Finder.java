package cn.vgame.a.turntable;

import cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor;

public interface Finder {

	CoinLogDtoCursor find(String id);

}
