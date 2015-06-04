package cn.mxz.friend;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserFriendRequestDao;
import mongo.gen.MongoGen.UserFriendRequestDto;
import cn.mxz.base.exception.MessageOnlyException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

import com.google.common.collect.Lists;

import define.D;

/**
 * 玩家好友管理器
 * 
 * @author 林岑
 * 
 */
class FriendManagerImpl implements FriendManager {

	private City city;

	private List<FriendManagerListener> listeners = Lists.newArrayList();

	public FriendManagerImpl(City city) {
		this.city = city;
	}

	@Override
	public List<Friend> getAll() {
		return FriendCenter.getInstance().getFriends(city.getId());
	}

	@Override
	public boolean isFriend(String userId) {
		return FriendCenter.getInstance().isFriend(city.getId(), userId);
	}

	@Override
	public Friend getFriend(String friendId) {
		return FriendCenter.getInstance().getFriend(city.getId(), friendId);
	}

	@Override
	public int getRequestCount() {

		return getRequests().size();
	}

	@Override
	public void remove(String friendId) {

		FriendCenter.getInstance().remove(city.getId(), friendId);
	}

	@Override
	public void cancel(String friendId) {

		FriendCenter.getInstance().removeRequest(city.getId(), friendId);
	}

	@Override
	public void accept(String userId) {

		city.getChecker().checkPlayerExist(userId);

		
		if(isFriendMax(userId)) {
			FriendCenter.getInstance().removeRequest(userId, city.getId());
			throw new OperationFaildException(S.S10066);
		}
		
		if(isFriendMax(city.getId())) {
			FriendCenter.getInstance().removeRequest(userId, city.getId());
			throw new OperationFaildException(S.S10320);
		}


		checkAreadyFriend(userId);

		checkMySelf(userId);


		add(userId);

		FriendCenter.getInstance().removeRequest(userId, city.getId());

		onAccept(userId);
		

		removeEnemy(city.getId(), userId);
		removeEnemy(userId, city.getId());
	}

	private void removeEnemy(String id1, String id2) {
		City cc = CityFactory.getCity(id1);
		cc.getEnemyManager().remove(id2);		
	}

	private boolean isFriendMax(String id) {

		City city = CityFactory.getCity(id);
		FriendManager fm = city.getFriendManager();
		List<Friend> all = fm.getAll();
		int size = all.size();
		return size >= D.FRIEND_COUNT_MAX;
	}

	@Override
	public void addListener(FriendManagerListener l) {

		listeners.add(l);
	}

	@Override
	public void addRequest(String userId) {
		if(isFriendMax(city.getId())) {
			throw new OperationFaildException(S.S10324);
		}
		checkAreadyFriend(userId);
		city.getChecker().checkPlayerExist(userId);
		if (isSendRecive(userId)) {
			throw new MessageOnlyException(S.S10115);
		}
		checkMySelf(userId);
		checkAreadyRequest(userId);
		FriendCenter.getInstance().sendRequest(city.getId(), userId);
		onRequest(userId);
		removeEnemy(city.getId(), userId);
	}

	@Override
	public void refuse(String userId) {
		FriendCenter.getInstance().removeRequest(userId, city.getId());
	}

	@Override
	public void checkFriend(String userId) {
		if (!isFriend(userId)) {
			throw new OperationFaildException(S.S10018);
		}
	}

	@Override
	public void refuseAll() {
		FriendCenter.getInstance().removeAllRequest(city.getId());
	}

	@Override
	public void add(String friendId) {
		FriendCenter.getInstance().addFriend(city.getId(), friendId);
	}

	@Override
	public List<Request> getRequests() {
		return FriendCenter.getInstance().getRequests(city.getId());
	}

	private void onAccept(String userId) {

		for (FriendManagerListener f : listeners) {

			f.onAccept(new AcceptEvent(userId));
		}
	}

	/**
	 * 判断是不是好友
	 * 
	 * @param userId
	 * @return
	 */
	private void checkAreadyFriend(String userId) {

		if (isFriend(userId)) {

			final City city = WorldFactory.getWorld().get(userId);

			throw new MessageOnlyException(S.S10079, city.getPlayer().getNick());
		}
	}

	/**
	 * true 表示已经申请好友、等待回复getUserFriendRequestDAO
	 * 
	 * @param userId
	 * @return
	 */
	private void checkAreadyRequest(String userId) {

		UserFriendRequestDao DAO = getFriendRequestDataDAO();

		UserFriendRequestDto f = DAO.get(city.getPlayer().getId(), userId);

		if (f != null) {

			final City city = WorldFactory.getWorld().get(userId);

			throw new MessageOnlyException(S.S10078, city.getPlayer().getNick());
		}
	}

	private void checkMySelf(String userId) {

		final String myUserId = city.getPlayer().getId();
		if (myUserId.equals(userId)) {
			throw new OperationFaildException(S.S10234);
		}
	}

	// 是否发送过好友请求
	private boolean isSendRecive(String userId) {

		UserFriendRequestDao DAO = getFriendRequestDataDAO();

		UserFriendRequestDto f = DAO.get(userId, city.getPlayer().getId());

		if (f == null) {

			f = DAO.get(userId, city.getPlayer().getId());
		}

		return f != null;
	}

	private void onRequest(String userId) {

		for (FriendManagerListener f : listeners) {

			f.onRequest(new RequestEvent(userId));
		}
	}

	private UserFriendRequestDao getFriendRequestDataDAO() {

		return Daos.getUserFriendRequestDao();
	}
}
