package cn.mxz.battle.skill.attacktype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mxz.BattleSelectorTemplet;
import cn.mxz.BattleSelectorTempletConfig;
import cn.mxz.battle.BattleCamp;
import cn.mxz.battle.buffer.Buffer;
import cn.mxz.battle.skill.FighterBeAttack;
import cn.mxz.battle.skill.attacktype.base.FighterBeAttackImpl;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeSetable;
import cn.mxz.fighter.Fighter;

import com.google.common.collect.Lists;

/**
 * 攻击类型
 *
 * @author 林岑
 *
 */
public class AttackTypeUtil {

	public static final void add(List<Fighter> ls, BattleCamp ans, int... position) {
		for (int p : position) {
			Fighter fighter = ans.get(p);
			if(fighter != null && !fighter.isDeath()) {
				ls.add(fighter);
			}
		}
	}
	private static void sort(final BattleCamp ans, final List<? extends Fighter> lives, final int position) {

		Collections.sort(lives, new Comparator<Fighter>() {

			@Override
			public int compare(final Fighter o1, final Fighter o2) {

				int p1 = ans.getPosition(o1);

				int p2 = ans.getPosition(o2);

				final BattleSelectorTemplet temp = BattleSelectorTempletConfig.get(position);

				if (temp == null) {

					throw new NullPointerException("position = " + position);
				}

				final String[] split = temp.getDst().split(",");

				List<Integer> ls = parse(split);

				return ls.indexOf(p1) - ls.indexOf(p2);
			}

			private List<Integer> parse(String[] split) {

				List<Integer> ls = new ArrayList<Integer>();

				for (String s : split) {

					ls.add(new Integer(s.trim()));
				}

				return ls;
			}
		});
	}

	/**
	 *
	 * 普通攻击应该被攻击到的那个目标战士
	 *
	 * @param ans
	 *            受众方列表
	 * @param position
	 *            攻击方所占阵营位置
	 * @return
	 */
	public static Fighter getTarget(BattleCamp ans, int position) {

		List<? extends Fighter> lives = ans.getLives();

//		Debuger.debug("AttackTypeUtil.getTarget() 活着的人1:" + lives);

		sort(ans, lives, position);

//		Debuger.debug("AttackTypeUtil.getTarget() 活着的人2:" + lives);
		Fighter fighter = lives.get(0);

		return fighter;
	}

	/**
	 * 构造一个衰减比例为decay的目标战士, 如果target == null return null
	 *
	 * @param target
	 *            目标战士
	 *
	 * @param decay
	 *            衰减比例
	 *
	 * @param isFriend
	 *            是否攻击队友
	 * @return
	 */
	public static FighterBeAttack build(Fighter target, float decay) {

		if (target == null) {

			return null;
		}

		FighterBeAttack fa = new FighterBeAttackImpl();

		fa.setDecay(decay);

		fa.setTarget(target);

		AttributeSetable copy = AttributeCalculator.copy(target.getAttribute());

		copy.setHp(target.getHpNow());

		fa.setAttributeOld(copy);

		List<Buffer> buffers = target.getBufferManager().getBuffers();

		for (Buffer buffer : buffers) {

			fa.addBufferId(buffer.getId());
		}

		return fa;
	}

	/**
	 *
	 * 把fb里面的null全过滤掉, 返回一个列表
	 *
	 * @param fb
	 * @return
	 */
	public static List<FighterBeAttack> buildList(FighterBeAttack... fb) {

		List<FighterBeAttack> ls = new ArrayList<FighterBeAttack>();

		for (FighterBeAttack b : fb) {

			if (b != null) {

				ls.add(b);
			}
		}

		return ls;
	}

	/**
	 * 他后面的那个人
	 * @param ans
	 * @param target
	 * @return
	 */
	public static Fighter getBehind(BattleCamp ans, Fighter target) {
		int position = ans.getPosition(target);
		if(position == 1) {
			Fighter four = ans.get(4);
			if(four != null) {
				return four;
			}

			return ans.get(7);
		}

//		  1       1
//		3 4 5   5 4 3
//		  7       7

		if(position == 4) {
			return ans.get(7);
		}

		return null;
	}

	public static List<FighterBeAttack> buildList(List<Fighter> fs) {
		List<FighterBeAttack> ls = Lists.newArrayList();
		for (Fighter fighter : fs) {
			ls.add(build(fighter, 1));
		}
		return ls;
	}

	/**
	 * 获得指定位置的所有战士
	 * @param ans
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	public static List<Fighter> getFighters(BattleCamp ans, int i, int j, int k) {
		List<Fighter> ls = Lists.newArrayList();
		add2(ls, ans, i);
		add2(ls, ans, j);
		add2(ls, ans, k);
		return ls;
	}

	private static void add2(List<Fighter> ls, BattleCamp ans, int k) {
		Fighter fighter = ans.get(k);
		if(fighter != null) {
			ls.add(fighter);
		}
	}
}
