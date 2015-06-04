package cn.mxz.mission.old;

import cn.javaplus.math.Fraction;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.battle.BattleExpPrize;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.user.team.god.Hero;

public class FighterPrizeImpl implements BattleExpPrize {

	private int fighterId;

	private int expAdd;

	private int levelAdd;

	private int	expNeed;

	@Override
	public int getExp() {
		return expAdd;
	}

	public void award(City city) {
		award(city.getPlayer());
	}
	@Override
	public void award(Player player) {

		final World w = WorldFactory.getWorld();

		final City city = w.get(player.getId());

		final Hero god = city.getTeam().get(fighterId);

		final int levelOld = god.getLevel();

		god.addExp(expAdd);

		levelAdd = god.getLevel() - levelOld;

		Fraction exp = god.getExp();

		expNeed = exp.getDenominator() - exp.getNumerator();

//		Debuger.debug("FighterPrizeImpl.award()" + god + " 等级增加:" + levelAdd);
	}

	@Override
	public int getFighterId() {
		return fighterId;
	}

	public void setExp(int exp) {
		this.expAdd = exp;
	}

	public void setFighterId(int fighterId) {
		this.fighterId = fighterId;
	}

	@Override
	public int getLevelAdd() {
		return levelAdd;
	}

	@Override
	public int getId() {
		throw new RuntimeException("无ID");
	}

	@Override
	public int getCount() {
		return expAdd;
	}

	@Override
	public int getExpNeed() {
		return expNeed;
	}
	public int getExpAdd() {
		return expAdd;
	}


}