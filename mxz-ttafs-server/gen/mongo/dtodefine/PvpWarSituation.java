package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;
@Dao
interface PvpWarSituation {

	@Key
	int getSituationId();

	String getChallengerId();

	String getDefenderId();

	int getCreateTime();

	byte[] getData();

	boolean getIsWin();

	boolean isSaw();


}