package cn.mxz.util.room;

import java.util.Collection;
import java.util.Iterator;

import com.lemon.commons.user.IUser;

public interface Room<T extends IUser> {

	public abstract void add(T user);

	public abstract T remove(String userId);

	public abstract Collection<T> getAll();

	public abstract Collection<T> getOnlineAll();

	public abstract T get(String userId);

	/**
	 * 判断某个用户是否存在
	 * @param userId
	 * @return
	 */
	
	public abstract boolean isExist(String userId);

	public abstract Iterator<T> iterator();

}