package cn.mxz.base.world;

import java.util.Collection;
import java.util.Map;

import cn.javaplus.collections.keyvalue.KeyValue;
import cn.javaplus.collections.keyvalue.KeyValueCollection;
import cn.javaplus.collections.keyvalue.KeyValueCollectionImpl;
import cn.javaplus.time.Time;
import cn.javaplus.user.IUserCollection;
import cn.mxz.base.server.Server;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.NotInitOverException;
import cn.mxz.events.Events;
import cn.mxz.events.SocketBindEvent;
import cn.mxz.events.SocketOnDestroyEvent;
import cn.mxz.init.SocketManager;
import cn.mxz.system.GameSystem;
import cn.mxz.system.SystemCounter;
import cn.mxz.user.init.ReadyUser;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;

import com.google.common.collect.Maps;
import com.lemon.commons.socket.ISocket;

/**
 * 
 * 世界
 * 
 * @author 林岑
 * @since 2013年5月28日 11:38:13
 * 
 */
public class GameWorld implements World {

	private IUserCollection<City> users;

	private SocketManagerImpl initer;

	private Map<String, City> nearests;

	private boolean isLock;

	public GameWorld() {

		initer = new SocketManagerImpl();

		load();
	}

	private void load() {

		SystemLog.debug("GameWorld.load() 加载所有用户...");
		users = CityFactory.createCitys();
		SystemLog.debug("GameWorld.load() 加载完毕! 人数:" + users.size());

		SystemLog.debug("GameWorld.load() 加载所有最近登陆的用户...");
		nearests = getAllNearest();
		SystemLog.debug("GameWorld.load() 加载完毕! 人数:" + nearests.size());
	}

	private Map<String, City> getAllNearest() {

		final Map<String, City> map = Maps.newConcurrentMap();

		for (City c : users.getAll()) {

			if (isNearest(c)) {

				map.put(c.getId(), c);
			}
		}

		return map;
	}

	private boolean isNearest(City c) {

		long lastLoginTime = c.getLastLoginMillis();
		lastLoginTime *= 1000;

		return System.currentTimeMillis() - lastLoginTime < 30 * Time.MILES_ONE_DAY;
	}

	@Override
	public Collection<City> getAll() {
		return users.getAll();
	}

	@Override
	public Collection<City> getOnlineAll() {
		return users.getOnlineAll();
	}

	@Override
	public void clear() {
		users.clear();
	}

	@Override
	public boolean isEmpty() {
		return users.isEmpty();
	}

	@Override
	public int size() {
		return users.size();
	}

	@Override
	public City get(String userId) {
		return users.get(userId);
	}

	@Override
	public City remove(String userId) {

		nearests.remove(userId);

		return users.remove(userId);
	}

	@Override
	public void add(City city) {
		users.add(city);
		nearests.put(city.getId(), city);
	}

	@Override
	public boolean isExist(String userId) {
		return users.isExist(userId);
	}

	@Override
	public void addAll(Collection<City> users) {
		users.addAll(users);
	}

	@Override
	public SocketManager getSocketManager() {
		return initer;
	}

	@Override
	public boolean isOnline(String uId) {

		final City city = get(uId);

		return city.getSocket() != null;
	}

	@Override
	public Map<String, City> getNearests() {
		return nearests;
	}

	private class SocketManagerImpl implements SocketManager {

		/**
		 * 用户和对应的套接字键值对
		 * 
		 * 一旦用户初始化成功后, 就会在该容器内生成用户和套接字的组合 一旦套接字断开, 就会移除这个组合
		 */
		private KeyValueCollection<City, ISocket> socket_user;

		/**
		 * 所有待创建的用户信息
		 */
		private Map<ISocket, ReadyUser> readys;

		SocketManagerImpl() {

			socket_user = new KeyValueCollectionImpl<City, ISocket>();

			readys = Maps.newHashMap();
		}

		@Override
		public void bind(ISocket s, City u) {

			socket_user.put(u, s);

			Events.getInstance().dispatch(new SocketBindEvent(u, s));
		}

		@Override
		public void unbind(ISocket s) {
			socket_user.remove(s);
		}

		@Override
		public void removeReadyUser(ISocket s) {
			readys.remove(s);
		}

		@Override
		public City getUser(ISocket s) {
			return socket_user.get(s);
		}

		@Override
		public ISocket getSocket(City u) {
			return socket_user.get(u);
		}

		@Override
		public void ready(ISocket s, ReadyUser u) {
			readys.put(s, u);
		}

		@Override
		public ReadyUser getReady(ISocket s) {
			return readys.get(s);
		}

		@Override
		public void onDestroy(ISocket socket) {

			// ConnectionManager.begin();

			final City city = socket_user.remove(socket);

			readys.remove(socket);

			Events.getInstance().dispatch(
					new SocketOnDestroyEvent(socket, city));

			// ConnectionManager.end();

			Debuger.debug("Logout", city);
		}
	}

	@Override
	public void reload() {

		if (isLock) {
			throw new NotInitOverException();
		}

		isLock = true;

		load();

		KeyValueCollection<City, ISocket> temp = new KeyValueCollectionImpl<City, ISocket>();

		for (KeyValue<City, ISocket> kv : initer.socket_user.entrySet()) {

			City c = get(kv.getKey().getId());

			temp.put(c, kv.getValue());
		}

		initer.socket_user = temp;

		isLock = false;
	}

	@Override
	public boolean isLock() {
		Server server = GameSystem.getInstance().getServer();
		if (server == null) {
			return true;
		}
		boolean running = server.isRunning();
		if (!running) {
			return true;
		}
		return isLock;
	}

	@Override
	public NickManager getNickManager() {
		return new NickManager();
	}

	@Override
	public SystemCounter getCounter() {
		return SystemCounter.getInstance();
	}

	@Override
	public Server getServer() {
		return GameSystem.getInstance().getServer();
	}
}
