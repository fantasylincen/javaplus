package cn.mxz.bossbattle.ui;

import java.util.List;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.bossbattle.BattleInfo;
import cn.mxz.bossbattle.BossBattleActivity;
import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.IBossUI2;
import cn.mxz.bossbattle.IDamageMessage;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class BossUI2Impl implements IBossUI2 {

	private BattleInfo			info;
	// private City user;

	private final Challenger	challenger;

	public BossUI2Impl(BattleInfo info, City user) {
		this.info = BossBattleActivity.INSTANCE.getBattleInfo();
		// this.user = user;

		challenger = info.getChallenger(user);
		if (challenger == null) {
			throw new OperationFaildException(S.S10209, user.getId());
		}
	}

	@Override
	public List<IDamageMessage> getDamages() {
		List<? extends IDamageMessage> message = info.getMessage(challenger.getLastReceivTimeStamp());
		//System.err.println("其他人的伤害情况为" + message);
		return Lists.newArrayList(message);
		// 测试完毕记得去掉注释

		// List<IDamageMessage> list = Lists.newArrayList();
		// IDamageMessage m = new DamageMessageImpl( "abc", 300 );
		// for( int i = 0; i < 20; i++ ){
		// m = new DamageMessageImpl( "def"+i, new Random().nextInt(10000) );
		// list.add( m );
		// }
		// return list;

	}

	@Override
	public int getFightRemainSec() {
		return challenger.getFightRemainSec();
	}

	@Override
	public int getRemainSec() {
		Debuger.debug( challenger.getUser().getPlayer().getNick() + " 剩余时间" + BossBattleActivity.INSTANCE.getEndRemainSec() );
		return BossBattleActivity.INSTANCE.getEndRemainSec();

	}

	@Override
	public int getChallengerCount() {
		return info.getChallengerManager().getAllChallengers().size();
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
	public int getRibirthGold() {
		return challenger.getAddtion().calcRebirthGold();
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
	public int getBossHpCurrent() {
		Debuger.debug( challenger.getUser().getPlayer().getNick() + " boss当前血量" + info.getBoss().getHpNow() );
		return info.getBoss().getHpNow();
	}

	@Override
	public int getChallengCount() {
		return challenger.getChallengeCount();
	}

	// @Override
	// public int getPeiyangdanFromGold() {
	// return challenger.getAddtion().getPeiyangdanFromGold();
	// }

	// @Override
	// public int getPeiyangdanFromChallengeCount() {
	// return challenger.getPeiyangdanFromChallengeCount( true );
	// }

	@Override
	public boolean isRibirthInTurn() {
		return challenger.getAddtion().isRebirthInTurn();
	}

	@Override
	public int getCurUseInspireGold() {
		return challenger.getAddtion().getCurUseInspireGold();
	}

	@Override
	public int getGold() {
		return challenger.getUser().getPlayer().getGold();
	}

}
