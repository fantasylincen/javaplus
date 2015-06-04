package cn.mxz.bossbattle.ui;

import java.util.ArrayList;
import java.util.List;

import mongo.gen.MongoGen.BossRankInfoHistoryDao;
import mongo.gen.MongoGen.BossRankInfoHistoryDao.BossRankInfoHistoryDtoCursor;
import mongo.gen.MongoGen.BossRankInfoHistoryDto;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.SimpleChallengerDto;
import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.IRankInfoWithKiller;
import cn.mxz.bossbattle.ISimpleChallenger;

import com.google.common.collect.Lists;

public class RankInfoWithKillerImpl implements IRankInfoWithKiller {

	// private ISimpleChallenger killer;
	// private List<ISimpleChallenger> list = Lists.newArrayList();
	// private final int bossHpMax;

	private BossRankInfoHistoryDto	dto;

	public RankInfoWithKillerImpl(List<Challenger> topTenList, Challenger killer, int bossHpMax) {
		dto = new BossRankInfoHistoryDto();
		int rank = 1;
		for (Challenger c : topTenList) {
			SimpleChallengerImpl sc = new SimpleChallengerImpl(c, rank++);
			dto.getList().add(sc.getDto());
			if( c == killer ){
				sc.setReputation();
			}
		}
		if (killer != null) {
			SimpleChallengerImpl sc = new SimpleChallengerImpl(killer, -1);
			
			dto.setKiller(sc.getDto());
			// this.killer = new SimpleChallengerImpl( );
		}
		else{
			SimpleChallengerDto s = new SimpleChallengerDto();
			s.setNick("");
			s.setRank(-1);
			s.setUserId("");
			s.setAllDamage(0);
			dto.setKiller(s);
		}
		// else{
		// this.killer = new SimpleChallengerImpl( killer, -1 );
		// }
		this.dto.setBossHpMax(bossHpMax);
		save();

	}
	

	/**
	 * 从数据库初始化
	 */
	public RankInfoWithKillerImpl() {
		BossRankInfoHistoryDao dao = Daos.getBossRankInfoHistoryDao();
		BossRankInfoHistoryDtoCursor find = dao.find();
		for (BossRankInfoHistoryDto dto : find) {
			this.dto = dto;
		}
		if (dto == null) {
			initEmpty();
		}

		// temKiller = new SimpleChallengerImpl( );
		// killer.
	}

	private void initEmpty() {

		BossRankInfoHistoryDao dao = Daos.getBossRankInfoHistoryDao();
		dto = dao.createDTO();
		SimpleChallengerDto killer = new SimpleChallengerDto();
		killer.setAllDamage(0);
		killer.setNick("");
		killer.setRank(-1);
		killer.setUserId("");
		killer.setReputation(0);
		dto.setKiller( killer );
		save();
	}

	@Override
	public List<ISimpleChallenger> getTopChallenger() {
		ArrayList<ISimpleChallenger> ls = Lists.newArrayList();

		for (SimpleChallengerDto d : this.dto.getList()) {
			ls.add(new SimpleChallengerImpl(d));
		}

		return ls;
	}

	@Override
	public ISimpleChallenger getKiller() {
		return new SimpleChallengerImpl(dto.getKiller());
	}

	@Override
	public int getBossHpMax() {
		return dto.getBossHpMax();
	}

	public void save() {
		BossRankInfoHistoryDao dao = Daos.getBossRankInfoHistoryDao();
		dao.clear();

		dao.save(dto);
	}

	public static void main(String[] args) {
		BossRankInfoHistoryDao dao = Daos.getBossRankInfoHistoryDao();
		BossRankInfoHistoryDto dto = dao.createDTO();
		dto.setBossHpMax(15);

		SimpleChallengerDto e = new SimpleChallengerDto();
		e.setNick("....");
		// ...

		dto.getList().add(e);

		SimpleChallengerDto e2 = new SimpleChallengerDto();
		// ....
		dto.setKiller(e2);

		dao.save(dto);

	}
}
