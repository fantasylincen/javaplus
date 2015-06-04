package cn.mxz.bossbattle.ui;

import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.bossbattle.BattleInfo;
import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.IBossUI1;
import cn.mxz.bossbattle.IRankInfoWithKiller;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;


public class BossUI1Impl implements IBossUI1 {

	private static final int	SHENFU_ID = 130043;
	private BattleInfo 			info;
	private City				user;



	public BossUI1Impl(BattleInfo info, City user) {
		this.user = user;
		this.info = BossBattleActivity.INSTANCE.getBattleInfo();
	}

	@Override
	public boolean isMorning() {
		return info.getActivityType() == 1;
	}

	@Override
	public String getBossName() {
		if( info.getBoss() == null ){
			return "";
		}
		return info.getBoss().getName();
	}

	@Override
	public int getBossId() {
		if( info.getBoss() == null ){
			return -1;
		}
		return info.getBoss().getTypeId();
	}

	@Override
	public int getStartRemainSec() {
//		System.out.println( "xxxxxxxxxx" + BossBattleActivity.INSTANCE.getStartRemainSec() );
		return BossBattleActivity.INSTANCE.getStartRemainSec();
	}

	@Override
	public int getEndRemainSec() {
//		System.out.println( "yyyyyyyyyyyyy" + BossBattleActivity.INSTANCE.getEndRemainSec() );
		return BossBattleActivity.INSTANCE.getEndRemainSec();
	}

	@Override
	public int getBossHp() {
		if( info.getBoss() == null ){
			return 0;
		}
		return info.getBoss().getHpMax();
	}

	@Override
	public int getCurrentBossHp() {
		if( info.getBoss() == null ){
			return 0;
		}
		return info.getBoss().getHpNow();
	}

	@Override
	public int getChallengerCount() {
		return info.getChallengerManager().getAllChallengers().size();
	}

	@Override
	public IRankInfoWithKiller getHistory() {
		return BossBattleActivity.INSTANCE.getHistory();
	}

	@Override
	public boolean isNewChallenger() {
		Challenger findByUser = info.getChallengerManager().findByUser(user);
		System.err.println( findByUser == null );
		return findByUser == null;
	}

	@Override
	public int getShenfu() {
//		City city = CityFactory.getCity("lc1");
		Bag<Grid> bag = user.getBag();
		return bag.getCount(SHENFU_ID);
//		return 1;
	}

	@Override
	public int getReputation() {
		return user.getPlayer().get( PlayerProperty.REPUTATION );
	}

}
