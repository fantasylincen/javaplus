package cn.javaplus.mxzrobot.actions;

import java.util.List;

import cn.mxz.Loader;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.prizecenter.PropIdCheck;

public class QueryId extends ActionBase {

	public String execute(Args args) {
		String name = args.getString("name").trim();
		
		Loader.loadAll();
		
		List<SkillTemplet> n = SkillTempletConfig.findByName(name);
		if(!n.isEmpty()) {
			return n.get(0).getName();
		}
		int id = PropIdCheck.getId(name);
		return id + "";
	}

}
