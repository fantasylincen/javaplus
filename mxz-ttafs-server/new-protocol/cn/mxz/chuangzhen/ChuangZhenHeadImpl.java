package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.battle.Camp;
import cn.mxz.mission.old.demon.Demon;

public class ChuangZhenHeadImpl implements ChuangZhenHead{

	private int			x;
	private Camp<Demon>	camp;

	/**
	 * @param camp
	 * @param x		加成倍数
	 */
	public ChuangZhenHeadImpl(Camp<Demon> camp, int x) {
		this.camp = camp;
		this.x = x;
	}

	@Override
	public int getFighterId() {
		List<Demon> fighters = camp.getFighters();
		return fighters.get(0).getTypeId();
	}

	@Override
	public int getX() {
		return x;
	}

	public Camp<Demon> camp() {
		return camp;
	}
}
