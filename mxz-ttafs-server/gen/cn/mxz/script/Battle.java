package cn.mxz.script;import static define.D.STAR_DEFINE;import java.util.List;import cn.javaplus.math.Scope;import cn.mxz.battle.BattleCamp;import cn.mxz.fighter.Fighter;import com.google.common.collect.Lists;/** 战斗 battle */public class Battle {	/** 战斗星星计算 */
	
	
	private int getStarByHp(int hpStart, BattleCamp camp) {
	
		int now = getHpAll(camp);
	
		float percent = (now + 0f) / hpStart;
	
		if(percent >= 0.9) {
			return 3;
		}
	
		if(percent >= 0.6) {
			return 2;
		}
	
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////以下策划不管
	private int getStarNormal(int round) {
		String[] split = STAR_DEFINE.split(",");
	
		int index = findIndexByRound(split, round);
	
		if (index == -1) {
	
			return 0;
		}
	
		return getStar(split, index);
	}
	
	
	/** 战斗星星计算
	 * @param battleCamp
	 * @param hpStart */
	
	public int calcStar(int round, int hpStart, BattleCamp camp) {
		// 1 - 3 3
		// 4 - 6 2
		// 7 - 10 1
	
		// "1-3:3,4-6:2,7-10:1"
	
	//		星星评价：1-3回合3星；4-6回合2星；7-10回合1星
	//		剩余血量90%以上3星；60%-89%2星，60%以下1星；两种评价取最大值。
	
		int starNormal = getStarNormal(round);
		int getStarByHp = getStarByHp(hpStart, camp);
		return Math.max(getStarByHp, starNormal); 
	}
	
	
	private int findIndexByRound(String[] split, int round) {
		List<Scope> s = parse(split);
		for (int i = 0; i < s.size(); i++) {
			Scope sc = s.get(i);
			if (sc.getMin() <= round && sc.getMax() >= round) {
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
	
	private Scope parse(String string) {
		String string2 = string.split(":")[0];
		String[] split = string2.split("-");
	
		int min = new Integer(split[0]);
		int max = new Integer(split[1]);
	
		return new Scope(min, max);
	}
	
	private int getStar(String[] split, int index) {
		String temp = split[index];
		String string = temp.split(":")[1];
		return new Integer(string);
	}
	
	
	private int getHpAll(BattleCamp camp) {
		int count = 0;
		for (Fighter f : camp.getFighters()) {
			int h = f.getHpNow();
			count += h;
		}
		return count;
	}
}