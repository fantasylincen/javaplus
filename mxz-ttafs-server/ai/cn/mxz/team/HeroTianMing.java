package cn.mxz.team;

import java.util.List;

import cn.mxz.Attribute;

public interface HeroTianMing {

	/**
	 * 获得被激活的天命ID列表
	 * @param f
	 * @return
	 */
	public List<Integer> getTianMingIds();

	/**
	 * 天命加成
	 * @return
	 */
	public Attribute getAddition();
}
