package cn.mxz.shenmo.battle;

import java.util.ArrayList;
import java.util.List;

import cn.mxz.battle.AbstractCamp;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.shenmo.ShenmoFighter;

/**
 * Boss的阵容, 这个阵容专门用于放置一个Boss
 *
 * @author 林岑
 *
 */
class ShenMoCamp extends AbstractCamp<ShenmoFighter> {

	private List<ShenmoFighter>	fighters;
	private ShenmoFighter boss;

	ShenMoCamp(ShenmoFighter boss) {

		this.boss = boss;
		fighters = new ArrayList<ShenmoFighter>();

		fighters.add(boss);
	}
	@Override
	public String getUserId() {
		return null;
	}

	@Override
	public List<ShenmoFighter> getFighters() {
		return fighters;
	}

	@Override
	public int getPosition(Fighter f) {
		return 4;
	}

	@Override
	public int getShenJia() {
		int r = 0;
		for (ShenmoFighter b : fighters) {
			r += b.getShenJia();
		}
		return r;
	}

	@Override
	public Fighter getMainFighter() {
		return boss;
	}

	@Override
	public Dogz getDogz() {
		return null;
	}

}
