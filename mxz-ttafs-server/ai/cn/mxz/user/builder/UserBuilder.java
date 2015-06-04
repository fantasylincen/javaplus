package cn.mxz.user.builder;

import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.base.servertask.PowerTask;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.dogz.Dogz;
import cn.mxz.loginreward.LoginRewardPlayer;
import cn.mxz.mission.IMissionManager;
import cn.mxz.mission.type.GuidePlayer;
import cn.mxz.onlinereward.OnlineRewardManager;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.PhysicalTask;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.util.FractionBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.vip.VipPlayer;

import com.linekong.platform.protocol.erating.server.RechargeLogic;

/**
 * 用户协议
 */
public class UserBuilder {

	public UserPro build(City city) {

		UserPro.Builder b = UserPro.newBuilder();

		Player p = city.getPlayer();

		b.setCash(p.get(PlayerProperty.CASH));

		int value = p.getGold();

		b.setGold(value);
		b.setGold2(p.get(PlayerProperty.NEW_GOLD));
//long time = System.currentTimeMillis();
		FractionBuilder fb = new FractionBuilder();

		int remainSec = PowerTask.getInstance().getRemainSec(p);

		b.setPowerRemainSec(remainSec);

		b.setPhysicalRemainSec(PhysicalTask.getInstance().getRemainSec(p));

		UserCounter his = city.getUserCounterHistory();
		b.setRechargeGold(his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT));

		b.setExp(fb.build(p.getExp()));
		b.setFighter(fb.build(p.getHeroInfo()));
		b.setPhysical(fb.build(p.getPhysical()));
		b.setPower(fb.build(p.getPower()));
		b.setQiCaiJingShi(city.getHeishiManager().getQsjs());

		Formation formation = city.getFormation();

		int shenJia = formation.getShenJia();

		b.setFightingCapacity(shenJia);

		IMissionManager ms = city.getMission();

		b.setMissionId(ms.getMaxMissionId());

		b.setCurrentMissionId(ms.getCurMissionId());

		b.setReputation(p.get(PlayerProperty.REPUTATION));

		LoginRewardPlayer player = city.getLoginRewardPlayer();

		b.setNeedShowLoginRewardPanel(player.isShowable());

		b.setShouHun(p.get(PlayerProperty.SHOU_HUN));

		b.setHasPrize(!city.getPrizeCenter().getData().isEmpty());
		
//		Debuger.debug("{enclosing_type}.build 耗时:"
//				+ (System.currentTimeMillis() - time));

		IMissionManager mission = city.getMission();

		int cId = mission.getMaxMissionId();

		MissionMapTemplet temp = MissionMapTempletConfig.get(cId);
		boolean new1;

		if(cId == 0) {
			new1 = true;
		} else if (temp == null) {
			new1 = false;
		} else {
			GuidePlayer g = new GuidePlayer(temp, city);
			new1 = g.isNew();
		}

		if (new1) {
			b.setPrompt(new PromptBuilder().buildEmpty());
		} else {
			b.setPrompt(new PromptBuilder().build(city));
		}

		b.setBase(new UserBaseBuilder().build(city.getPlayer(), shenJia));

		VipPlayer vp = city.getVipPlayer();
		b.setVipGrowthN(vp.getGrowth().getNumerator());
		b.setVipGrowthD(vp.getGrowth().getDenominator());

		Dogz fighting = city.getDogzManager().getFighting();

		if (fighting != null) {
			b.setFightingDogzId(fighting.getTypeId());
		} else {
			b.setFightingDogzId(-1);
		}

		boolean mark = city.getUserCounterHistory().isMark(
				CounterKey.HAS_RECEIVE_FIRST_RECHARGE_REWARD);
		b.setHasReceiveRechargeReward(mark);

		int ccc = city.getUserCounterHistory().get(
				CounterKey.MONTH_CARD_END_SECOND);
		ccc -= (System.currentTimeMillis() / 1000);
		if (ccc < 0) {
			ccc = 0;
		}

		b.setMonthCardEndSecond(ccc);
		b.setFirshtRechargeStr(new RechargeLogic(city).getFirshtRechargeStr());
		String thirdPartyId = city.getPlayer().getThirdPartyId();
		if (thirdPartyId == null) {
			thirdPartyId = city.getId();
		}
		b.setLineKongId(thirdPartyId);
		
		OnlineRewardManager manager = city.getOnlineRewardManager();
//		OnlineReward build = new OnlineRewardBuild().build(manager);
//		if(build != null) {
//			b.setOnlineReward(build);
//		}
		b.setIsShowOnlineRewardButton(!manager.hasReceiveAllReward());
		b.setHasOnlineReward(manager.hasRewardCanReceive());
		return b.build();
	}
}
