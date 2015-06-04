package cn.mxz.util.room;

import java.util.Collection;
import java.util.Iterator;

import cn.javaplus.user.IUserCollection;
import cn.javaplus.user.UserCollection;

import com.google.common.collect.Lists;
import com.lemon.commons.user.IUser;

/**
 * 房间
 * @author 	林岑
 * @time	2013-3-24
 */
public abstract class AbstractRoom<T extends IUser> implements Iterable<T>, Room<T>{

	private IUserCollection<T> users;

	public AbstractRoom() {
		users = new UserCollection<T>();
	}

	@Override
	public void add(T user) {
		users.add(user);
	}

	@Override
	public T remove(String userId) {
		final T remove = users.remove(userId);
		return remove;
	}

	@Override
	public Collection<T> getAll() {
		return users.getAll();
	}

	@Override
	public Collection<T> getOnlineAll() {
		return users.getOnlineAll();
	}

	@Override
	public T get(String userId) {
		return users.get(userId);
	}

	@Override
	public boolean isExist(String userId) {
		return users.isExist(userId);
	}

	@Override
	public Iterator<T> iterator() {
		return Lists.newArrayList(getAll()).iterator();
	}

}
