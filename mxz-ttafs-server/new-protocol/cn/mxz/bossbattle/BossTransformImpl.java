package cn.mxz.bossbattle;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.bossbattle.ui.BossUI1Impl;
import cn.mxz.bossbattle.ui.BossUI2Impl;
import cn.mxz.bossbattle.ui.RankInfoWithMyselfImpl;
import cn.mxz.bossbattle.ui.SubBossUI2Impl;
import cn.mxz.city.City;

public class BossTransformImpl implements IBossTransform {

	City				user;
	BossBattleActivity	activiy	= BossBattleActivity.INSTANCE;
	//BattleInfo			info	= activiy.getBattleInfo();

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public IBossUI1 getBossUI1() {

//		if(activiy.getBattleInfo() == null) {
//			return new EmptyBossUI();
//		}

		IBossUI1 data = new BossUI1Impl(activiy.getBattleInfo(), user);
		return data;
	}

	@Override
	public IBossUI2 getBossUI2() {
		if( BossBattleActivity.INSTANCE.isRunning() ){
			return new BossUI2Impl(activiy.getBattleInfo(), user);
		}
		return null;
	}

	@Override
	public ISubBossUI2 inspire(Boolean isMax) {

		Challenger me = activiy.getBattleInfo().getChallenger(user);
		if (me == null) {
			throw new OperationFaildException(S.S10278);
		}
		me.getAddtion().inspire(isMax);
		return new SubBossUI2Impl(activiy.getBattleInfo(), user);
	}

	@Override
	public void joinActivity() {
		activiy.getBattleInfo().joinActivity(user);
	}

	@Override
	public IRankInfoWithMyself getRankList() {
		Challenger me = activiy.getBattleInfo().getChallenger(user);
		if (me == null) {
			throw new OperationFaildException(S.S10250);
		}
		return new RankInfoWithMyselfImpl(activiy.getBattleInfo().getChallengerManager().getTopTen(), me);
	}

	@Override
	public IAwardInfo getAward() {

		return activiy.showAward(user);
	}


	@Override
	public ISubBossUI2 rebirth() {

		Challenger me = activiy.getBattleInfo().getChallenger(user);
		if( me == null ){
			throw new OperationFaildException(S.S10278);
		}
		me.rebirth();
		return new SubBossUI2Impl(activiy.getBattleInfo(), user);
	}

	@Override
	public void forceJoinActivity() {
		activiy.getBattleInfo().forceJoinActivity(user);
		
	}


}
