package cn.mxz.formation;

import cn.mxz.Attribute;
import cn.mxz.user.team.god.Hero;

interface IFormationAddtion {
	
	/**
	 * 计算阵型的加成
	 * @param hero
	 * @return
	 */
	
	Attribute getAddition( Hero hero );

}
