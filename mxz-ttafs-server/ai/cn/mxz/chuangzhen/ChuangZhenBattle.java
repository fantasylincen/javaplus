package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.CopterDifficultyTemplet;
import cn.mxz.FighterExpPrize;
import cn.mxz.battle.AbstractBattle;
import cn.mxz.enemy.EmptyExpPrize;
import cn.mxz.mission.type.Scope;
import cn.mxz.mission.type.ScopeImpl;
import cn.mxz.pvp.SuperCamp;

import com.google.common.collect.Lists;

import define.D;

public class ChuangZhenBattle extends AbstractBattle {

	private ChuangZhenPlayer player;
	/**
	 * 1:力战 2:奋战 3:血战
	 */
	private int type;

	/**
	 * @param player
	 * @param type
	 *            1:力战 2:奋战 3:血战
	 */
	protected ChuangZhenBattle(ChuangZhenPlayer player, int type) {
		super(new SuperCamp(player.camp(),
				new ChuangZhenAdditionAdaptor(player)),
				((ChuangZhenHeadsImpl) player.getHeads()).camp(type));
		this.player = player;
		this.type = type;
	}

	@Override
	protected FighterExpPrize getMapTemplet() {
		return new EmptyExpPrize();
	}

	/**
	 * 单倍星星奖励
	 * 
	 * @return
	 */
	public int getStarSingle() {
		return calcStar(getRound());
	}

	/**
	 * 星星倍数
	 * 
	 * @return
	 */
	public int getStarX() {
		CopterDifficultyTemplet templet = ChuangZhenUtil.getTemplet(type,
				player);
		return templet.getStar();
	}

	/**
	 * 最终获得的星星
	 * 
	 * @return
	 */
	public int getStarReceived() {
		int starX = getStarX();
		return starX * getStarSingle();
	}

	private int calcStar(int round) {

		String[] split = D.STAR_DEFINE.split(",");

		int index = findIndexByRound(split, round);

		if (index == -1) {

			return 0;
		}

		return getStar(split, index);
	}

	private int findIndexByRound(String[] split, int round) {
		List<Scope> s = parse(split);
		for (int i = 0; i < s.size(); i++) {
			Scope sc = s.get(i);
			if (sc.contains(round)) {
				return i;
			}
		}
		return -1;
	}

	private List<Scope> parse(String[] split) {
		List<Scope> ls = Lists.newArrayList();
		for (String string : split) {
			ls.add(parse(string));
		}
		return ls;
	}

	private int getStar(String[] split, int index) {
		String temp = split[index];
		String string = temp.split(":")[1];
		return new Integer(string);
	}

	private Scope parse(String string) {
		String string2 = string.split(":")[0];
		String[] split = string2.split("-");

		int min = new Integer(split[0]);
		int max = new Integer(split[1]);

		return new ScopeImpl(min, max);
	}

}
