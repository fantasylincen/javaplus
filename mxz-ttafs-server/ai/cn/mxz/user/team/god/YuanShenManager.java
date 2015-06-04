package cn.mxz.user.team.god;

import java.util.List;

import cn.mxz.battle.YuanShen;

/**
 * 战士元神管理器
 * @author 林岑
 *
 */
public interface YuanShenManager {

	/**
	 * 战士所有的元神
	 * @return
	 */
	List<YuanShen> getYuanShens();

	/**
	 * 清除所有元神
	 */
	void clear();

	/**
	 * 增加一堆元神到某个战士身上
	 * @param yuanShens
	 */
	void add(List<YuanShen> yuanShens);

	void set(List<YuanShen> yuanShens);
}
