package cn.mxz.mission.type;

import java.util.List;

import cn.mxz.battle.BattleExpPrize;
import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.mission.old.PropPrize;

import com.google.common.collect.Lists;

/**
 * 扫荡奖励接收器
 * 
 * @author 林岑
 * 
 */
public class MoppingupPrizeReiceiver implements MissionPrizeReceiver {

	private List<PropPrize> prizes = Lists.newArrayList();
	private int mapId;
	private List<BattleExpPrize> fighterPrizes = Lists.newArrayList();
	private boolean isMain;
	private boolean isBoss;
	private City user;

	public MoppingupPrizeReiceiver(City user, int mapId, boolean isBoss,
			boolean isMain) {
		this.user = user;
		this.mapId = mapId;
		this.isBoss = isBoss;
		this.isMain = isMain;
	}

	@Override
	public boolean isMain() {
		return isMain;
	}

	@Override
	public boolean isBoss() {
		return isBoss;
	}

	@Override
	public List<PropPrize> getPropPrize() {
		return prizes;
	}

	@Override
	public int getMapId() {
		return mapId;
	}

	@Override
	public List<BattleExpPrize> getFighterPrize() {
		return fighterPrizes;
	}

	@Override
	public PlayerCamp getUnderPlayerCamp() {
		return user.getFormation().getSelected();
	}

}
