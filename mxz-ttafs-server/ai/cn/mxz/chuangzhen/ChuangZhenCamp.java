package cn.mxz.chuangzhen;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.util.Util;
import cn.mxz.CopterDifficultyTemplet;
import cn.mxz.CopterMonsterTemplet;
import cn.mxz.CopterMonsterTempletConfig;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.mission.old.demon.AbstractDemonGroup;
import cn.mxz.mission.old.demon.Demon;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ChuangZhenCamp extends AbstractDemonGroup {

	public ChuangZhenCamp(ChuangZhenPlayer player, CopterDifficultyTemplet temp) {
		super(buildDemonList(temp, player));
	}

	private static List<Demon> buildDemonList(CopterDifficultyTemplet temp, ChuangZhenPlayer player) {

		List<Demon> ls = Lists.newArrayList();

		String p = temp.getDifficultyDeploy();
		String[] split = p.split("\\|");

		for (String s : split) {
			if (!s.isEmpty()) {
				List<Integer> is = Util.Collection.getIntegers(s);
				int q = is.get(0);
				int count = is.get(1);

				for (int i = 0; i < count; i++) {
					ls.add(buildDemon(ls, q, player, temp));
				}
			}
		}
		return ls;
	}

	private static Demon buildDemon(List<Demon> ls, int q, ChuangZhenPlayer player, CopterDifficultyTemplet temp) {
		List<CopterMonsterTemplet> all = CopterMonsterTempletConfig.findByStep(q);
		Iterator<CopterMonsterTemplet> it = all.iterator();
		while (it.hasNext()) {

			CopterMonsterTemplet c = it.next();
			if (c.getArise() == 0) {
				it.remove();
			}
		}

		Set<Integer> s = Sets.newHashSet();
		for (Demon t : ls) {
			s.add(t.getTypeId());
		}
//		getCurrentFloor  1		当前在第几层
//		getCurrentTimes  12		当前挑战了多少轮
//		getNextAddition  0		距下次发加成奖还剩多少层
//		getNextReward  5		距下次结算奖励还剩多少层
//		getRemainFloor  5		再过好多关结算奖励
//		getRemainStar  91		剩余星星数量
//		getStar  0				当前得星数量
//		getStarMaxHistory  45	历史最高得星
		int j = 0;
		while (j++ < 1000) {
			CopterMonsterTemplet t = Util.Collection.getRandomOne(all);
			if (!s.contains(t.getId())) {
				return new ChuangZhenDemon(t, player, temp.getFactor());
			}
		}
		throw new RuntimeException("无法找到!");

	}

	@Override
	public int getShenJia() {
		int a = 0;
		for (Demon d : demons.values()) {
			a += d.getShenJia();
		}
		return a;
	}

	@Override
	public int getPosition(Fighter f) {
		Set<Entry<Integer, Demon>> s = demons.entrySet();

		for (Entry<Integer, Demon> entry : s) {
			Demon value = entry.getValue();
			if (value.equals(f)) {
				return entry.getKey();
			}
		}

		throw new IllegalArgumentException("不在阵形中!" + f);
	}

	@Override
	public Dogz getDogz() {
		return null;
	}
}
