package cn.mxz.events;

import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.city.City;

/**
 * 战斗胜利事件
 * @author 林岑
 *
 */
public class FightingWinEvent {

	private int	round;
	private City	city;
	private BattleCamp	upper;
	private Battle	battle;

	public FightingWinEvent(Battle battle, int round, City city, BattleCamp upper) {
		this.battle = battle;
		this.round = round;
		this.city = city;
		this.upper = upper;
	}

	/**
	 * 战斗回合数
	 * @return
	 */
	public int getRound() {
		return round;
	}

	public City getCity() {
		return city;
	}
	public BattleCamp getUpper() {
		return upper;
	}
	public Battle getBattle() {
		return battle;
	}
}
