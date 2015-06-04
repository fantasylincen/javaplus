package mongo.dtodefine;

import java.util.List;


import cn.javaplus.db.mongo.Dao;

@Dao
interface BossRankInfoHistory {

	SimpleChallenger getKiller();

	List<SimpleChallenger> getList();

	int getBossHpMax();

}
