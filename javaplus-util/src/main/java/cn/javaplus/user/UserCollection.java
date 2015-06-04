package cn.javaplus.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.javaplus.commons.user.IUser;

/**
 * 用户容器
 * @author 	林岑
 * @time	2013-5-14
 */
public class UserCollection<T extends IUser> implements IUserCollection<T> {


	/**房间内所有用户*/
	private Map<String, T> users;

	@Override
	public void add(T user) {
		users.put(user.getId(), user);
	}

	/**
	 * 移除用户
	 */
	@Override
	public T remove(String userId) {
		return users.remove(userId);
	}


	public UserCollection() {
		users = new ConcurrentHashMap<String, T>();
	}

	@Override
	public Collection<T> getOnlineAll() {

		List<T> all = new ArrayList<T>();

		for (T u : getAll()) {

			if(u.getSocket() != null) {

				all.add(u);
			}
		}
		return all;
	}

	@Override
	public Collection<T> getAll() {
		return new ArrayList<T>(users.values());
	}

	@Override
	public void clear() {
		users.clear();
	}

	@Override
	public int size() {
		return users.size();
	}

	@Override
	public boolean isEmpty() {
		return users.isEmpty();
	}

	@Override
	public T get(String userId) {
		return users.get(userId);
	}

	@Override
	public boolean isExist(String userId) {
		return users.containsKey(userId);
	}

	@Override
	public void addAll(Collection<T> users) {
		for (T i : users) {
			this.users.put(i.getId(), i);
		}
	}

}
