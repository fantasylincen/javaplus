package cn.javaplus.mxzrobot.actions;

import java.util.List;

import cn.javaplus.string.StringPrinter;
import cn.mxz.ExclusiveTemplet;
import cn.mxz.ExclusiveTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;

public class QueryTianMing extends ActionBase {

	public String execute(Args args) {
		String fighter = args.getString("fighter").trim();
		int fighterId = -1;
		if(fighter.matches("[0-9]+")) {
			fighterId = new Integer(fighter);
		} else {
			FighterTempletConfig.load();
			List<FighterTemplet> tmp = FighterTempletConfig.findByName(fighter);
			List<FighterTemplet> all = FighterTempletConfig.getAll();
			
			if(tmp.isEmpty()) {
				throw new RuntimeException("没有找到战士:" + fighter);
			}
			FighterTemplet temp = tmp.get(0);
			fighterId = temp.getId();
		}
		
		List<ExclusiveTemplet> findByFighterId = ExclusiveTempletConfig.findByFighterId(fighterId);
		StringPrinter sp = new StringPrinter();
		sp.println("天命类型（1伙伴 2装备 3技能 4联携 5五行天命）");
		sp.println("");
		for (ExclusiveTemplet t : findByFighterId) {
			sp.println(t.getName() + "	id:" + t.getId() + "	描述:" + t.getDescription() + "	激活ids:"+ t.getExclusiveId() + "	type:" + t.getType());
		}
		return sp.toString();
	}

}
