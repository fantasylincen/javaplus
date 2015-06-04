package cn.mxz.bossbattle.ui;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.bossbattle.BattleInfo;
import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.ISubBossUI2;
import cn.mxz.city.City;

public class SubBossUI2Impl implements ISubBossUI2 {

	//private BattleInfo 					info;
	private City						user;

	private final Challenger			challenger;

	public SubBossUI2Impl(BattleInfo info, City user) {
		//this.info = BossBattleActivity.INSTANCE.getBattleInfo();
		this.user = user;

		challenger = info.getChallenger( user );
		if( challenger == null ){
			throw new SureIllegalOperationException( user.getId() + "玩家尚未加入到游戏" );
		}
	}

	@Override
	public int getInspireGold() {
		return challenger.getAddtion().calcInspireGold();
	}

	@Override
	public int getMaxInspireGold() {
		return challenger.getAddtion().calcMaxInspireGold();
	}


	@Override
	public int getUseGold() {
		return challenger.getAddtion().getAllUseGold();
	}

	@Override
	public float getAddtionPercent() {
		return challenger.getAddtion().getAddtionPercent();
	}

	@Override
	public boolean isRibirthInTurn() {
		return challenger.getAddtion().isRebirthInTurn();	}

	@Override
	public int getPeiyangdanFromGold() {
		return challenger.getAddtion().getPeiyangdanFromGold();
	}

	@Override
	public int getBattleCodeDown() {
		return challenger.getFightRemainSec();
	}

	@Override
	public int getRibirthGold() {
		return challenger.getAddtion().calcRebirthGold();
	}

	@Override
	public int getGold() {
		return user.getPlayer().getGold();
	}

	@Override
	public int getCurUseInspireGold() {
		return challenger.getAddtion().getCurUseInspireGold();
	}

}
