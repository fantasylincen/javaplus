package cn.mxz.equipment;

import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.SnatchTemplet;
import cn.mxz.SnatchTempletConfig;
import cn.mxz.bag.BagAuto;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.pvp.SuperCamp;
import cn.mxz.user.builder.PlayerBase;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class UnRobotUser implements FightingUser {

	private City city;

	public UnRobotUser(City city) {
		this.city = city;
	}

	@Override
	public String getId() {
		return city.getId();
	}

	@Override
	public PlayerCamp getCamp() {
		return new SuperCamp(city.getFormation().getSelected(), 1);
	}

	@Override
	public boolean isRobot() {
		return false;
	}

	@Override
	public int getDanId() {
		PvpManager pm = city.getNewPvpManager();
		PvpPlayer p = pm.getPlayer();
		return p.getDan();
	}

	@Override
	public PlayerBase getPlayer() {
		return city.getPlayer();
	}

	@Override
	public BagAuto getBagAuto() {
		return city.getBagAuto();
	}

	@Override
	public float getProbability(City city, int stuffTempletId) {

		int times = getSnatchTimes(city, stuffTempletId);

		PropTemplet t = PropTempletFactory.get(stuffTempletId);
		int q = t.getQuality();
		SnatchTemplet temp = SnatchTempletConfig.get(q + ",2");
		float probability = temp.getFinder();

		AuthorityTemplet tt = AuthorityTempletConfig.get(city.getLevel());

		probability *= tt.getSnatchPar();

		if (mustDrop(temp, times)) {
			probability = 1;
		}

		if (probability > 1) {
			probability = 1;
		}
		if (probability < 0) {
			probability = 0;
		}

		return probability;
	}

	private int getSnatchTimes(City city, int stuffTempletId) {
		UserCounter c = city.getUserCounterHistory();
		int times = c
				.get(CounterKey.SNATCH_TIMES_FOR_MUST_DROP, stuffTempletId);
		return times;
	}

	/**
	 * 必定掉落 ?
	 * 
	 * @param temp
	 * @param times
	 * @return
	 */
	private boolean mustDrop(SnatchTemplet temp, int times) {
		int plunder = temp.getPlunder();
		return (times + 1) % plunder == 0;
	}

}
