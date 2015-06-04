package cn.javaplus.user;

import java.util.Collection;

import cn.javaplus.commons.user.IUser;

/**
 * 玩家容器
 * @author 	林岑
 * @time	2013-5-14
 */
public interface IUserCollection<T extends IUser> {
	
	/**
	 * 获得所有用户
	 */
	Collection<T> getAll();

	/**
	 * 所有在线用户
	 */
	Collection<T> getOnlineAll();

	/**
	 * 清除所有用户
	 */
	void clear();

	/**
	 * 是否没有用户
	 */
	boolean isEmpty();

	/**
	 * 用户数量
	 */
	int size();

	/**
	 * 获得某个用户
	 */
	T get(String userId);
	
	/**
	 * 移除某个用户
	 */
	T remove(String userId);
	
	/**
	 * 添加一个用户到容器中
	 */
	void add(T user);
	
	/**
	 * 某个用户是否存在
	 * @param userId
	 * @return
	 */
	boolean isExist(String userId);
	
	void addAll(Collection<T> users);
}
