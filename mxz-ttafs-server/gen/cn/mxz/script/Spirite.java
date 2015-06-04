package cn.mxz.script;import cn.mxz.FighterTemplet;import cn.mxz.FighterTempletConfig;import cn.mxz.fighter.HeroProperty;import cn.mxz.user.team.god.Hero;/** 魂魄 spirite */public class Spirite {	/** 升星所需魂魄数量 */
	/**
	 * 升星或者招募所需魂魄数量 计算公式
	 * @param typeId	神将ID
	 * @param hero	是否是招募
	 * @return
	 */
	public int getCountMax(int typeId, Hero hero) {
		int times; // 第n次升星
	
		FighterTemplet temp = FighterTempletConfig.get(typeId);
	
		if (hero == null) {	//招募
			times = 0;
			return (int) temp.getSoul();	//招募所需数量
			
		} else {			//升星
			times = hero.get(HeroProperty.QUALITY) + 1;
			return (int) (temp.getSoul() + times * 1.1 + 1);
		}
	}
}