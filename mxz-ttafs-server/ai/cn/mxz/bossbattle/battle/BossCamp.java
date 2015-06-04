package cn.mxz.bossbattle.battle;

import java.util.List;

import cn.mxz.battle.Camp;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

/**
 * Boss阵容
 * @author 林岑
 *
 */
public class BossCamp implements Camp<BossFighter> {

	private BossFighter	boss;

	public BossCamp(BossFighter boss) {
		this.boss = boss;
	}

	@Override
	public int getShenJia() {
		return 0;
	}

	@Override
	public List<BossFighter> getFighters() {
		return Lists.newArrayList(boss);
	}

	@Override
	public int getPosition(Fighter f) {
		return 4;
	}

	@Override
	public Fighter get(int position) {
		if(position == 4 && !boss.isDeath()) {
			return boss;
		}
		return null;
	}

	@Override
	public Fighter getMainFighter() {
		return boss;
	}

	@Override
	public Dogz getDogz() {
		return null;
	}

	@Override
	public String getUserId() {
		return null;
	}

}
