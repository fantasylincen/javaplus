package cn.mxz.newpvp;

import cn.javaplus.time.colddown.ColdDown;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.protocols.pvp.PvpP.DanRewardPro;
import cn.mxz.protocols.pvp.PvpP.PvpScenePro;
import cn.mxz.util.FractionBuilder;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.debuger.Debuger;

class PvpSceneBuilder {

	public PvpScenePro build(PvpPlayer player) {

		PvpScenePro.Builder b = PvpScenePro.newBuilder();
		int w = player.getCurrentWinStreak();
		int wm = player.getMaxWinStreak();
//		Debuger.debug("PvpSceneBuilder.build() 当前连胜次数:" + w + "/" + wm);
		b.setCurrentWinStreak(w);
		b.setDanId(player.getDan());
		b.setFightingCapacity(player.getShenJia());
		b.setLoseTimes(player.getLoseTimes());
		b.setMaxWinStreak(wm);
		b.setPower(player.getPower());
		b.setRank(player.getRankInAll());
		b.setWinPercent(player.getWinPercent());
		b.setWinTimes(player.getWinTimes());
		b.setPractice(new FractionBuilder().build(player.getPractice()));
		b.setRemainTimes(player.getRemainTimes());
		
		City city = player.getCity();
		ColdDown cd = city.getCDManager().get(CDKey.PVP_CHALLENGE);
		
		int remainingSec = cd.getRemainingSec();
		Debuger.debug("PvpSceneBuilder.build()" + remainingSec);
		b.setChallengeCd(remainingSec);
//		City city = player.getCity();

		b.setStatus(/* city.isTester() ? "wl" : */player.getStatus());
		b.setBeKowtowTimes(player.getBeKowtowTimes());
		b.setBestRank(player.getBestRank());

		b.setHasReceived(player.canNotReceiveRankReward());
		b.setRankRewardRemainSec(player.getRankRewardRemainSec());
		b.setRankOnEightClock(player.getRankInEightClock());
		
		boolean value = /*city.isTester() ? true : */player.isInUpMatch();
		b.setIsUp(value);
		b.setKowtowTimes(player.getKowtowTimes());
		b.setRemainUserFuWenTimes(player.getRemainUseFuWenTimes());
		b.setUserLevel(city.getLevel());

		PvpManager m = city.getNewPvpManager();
		DanReward reward = m.getDanReward();
		DanRewardPro rPro = new DanRewardBuilder().build(reward);

		b.setReward(rPro);

		Debuger.debug("PvpSceneBuilder.build() " + player.getStatus() + "  "
				+ rPro.getRewards() + "      " + rPro.getCanReceive() + ","
				+ player.canNotReceiveRankReward());

		b.setIsFirstUp(player.isFirstUp());

		b.setRongYu(city.getPlayer().get(PlayerProperty.RONG_YU));
		
		return b.build();
	}

	/**
	 * 这个方法是专门用在玩家点击随机的3个人物的时候
	 * 
	 * @param u
	 * @return
	 */
	public PvpScenePro build(PvpFightUser u) {

		// 胜点
		// 胜率
		// 胜利次数
		// 失败次数
		// 段位ID

		PvpScenePro.Builder b = PvpScenePro.newBuilder();
		b.setDanId(u.getDanId());
		int t = u.getLoseTimes() + u.getWinTimes();
		if (t == 0) {
			b.setWinPercent(0);
		} else {
			b.setWinPercent((u.getWinTimes() + 0f) / t);
		}
		b.setLoseTimes(u.getLoseTimes());
		b.setWinTimes(u.getWinTimes());

		b.setPractice(new FractionBuilder().build(u.getPractice()));

		b.setCurrentWinStreak(1);
		b.setFightingCapacity(u.getCamp().getShenJia());

		b.setMaxWinStreak(1);
		b.setPower(1);
		b.setRank(1);

		b.setRemainTimes(1);
		b.setStatus("");
		b.setBeKowtowTimes(1);
		b.setBestRank(1);
		b.setHasReceived(true);
		b.setRankRewardRemainSec(0);
		b.setRankOnEightClock(-1);
		b.setIsUp(true);
		b.setKowtowTimes(1);
		b.setRemainUserFuWenTimes(1);
		b.setChallengeCd(0);
		b.setUserLevel(u.getPlayer().getLevel());

		DanRewardPro.Builder d = DanRewardPro.newBuilder();
		d.setCanReceive(false);
		d.setRewards("");
		d.setDanId(-1);
		b.setReward(d.build());
		b.setIsFirstUp(false);
		b.setRongYu(0);
		return b.build();
	}
}
