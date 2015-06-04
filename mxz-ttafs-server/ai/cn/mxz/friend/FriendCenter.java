package cn.mxz.friend;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FriendDao;
import mongo.gen.MongoGen.FriendDao.FriendDtoCursor;
import mongo.gen.MongoGen.FriendDto;
import mongo.gen.MongoGen.UserFriendRequestDao;
import mongo.gen.MongoGen.UserFriendRequestDao.UserFriendRequestDtoCursor;
import mongo.gen.MongoGen.UserFriendRequestDto;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FriendCenter {

	private static final String				SP			= ":";

	private static FriendCenter				instance;

	private Map<String, FriendDto>			allFriends	= Maps.newHashMap();
	private Map<String, UserFriendRequestDto>	allRequest	= Maps.newHashMap();

	private FriendCenter() {
		loadFriends();
		loadRequest();
	}

	private void loadRequest() {

		UserFriendRequestDao DAO = Daos.getUserFriendRequestDao();

		UserFriendRequestDtoCursor all = DAO.find();
		for (UserFriendRequestDto u : all) {
			String key = getKey(u);
			allRequest.put(key, u);
		}
	}

	private void loadFriends() {
		FriendDao DAO = Daos.getFriendDao();
		FriendDtoCursor all = DAO.find();
		for (FriendDto a : all) {
			String key = getKey(a);
			allFriends.put(key, a);
		}
	}

	private String getKey(FriendDto a) {
		return getKey(a.getFriendName(), a.getUname());
	}

	private String getKey(String uid1, String uid2) {
		List<String> ls = Lists.newArrayList();
		ls.add(uid1);
		ls.add(uid2);
		Collections.sort(ls);
		return Util.Collection.linkWith(SP, ls);
	}

	public static final FriendCenter getInstance() {
		if (instance == null) {
			instance = new FriendCenter();
		}
		return instance;
	}

	public List<Friend> getFriends(String id) {
		List<Friend> all = Lists.newArrayList();
		for (FriendDto fb : this.allFriends.values()) {
			if (fb.getFriendName().equals(id)) {
				all.add(buildFriend(id, fb));
			} else if (fb.getUname().equals(id)) {
				all.add(buildFriend(id, fb));
			}
		}
		return all;
	}

	private Friend buildFriend(String id, FriendDto fb) {
		City city = CityFactory.getCity(id);
		return new FriendImpl(city, fb);
	}

	public boolean isFriend(String userId1, String userId2) {
		String key = getKey(userId1, userId2);
		return allFriends.containsKey(key);
	}

	public Friend getFriend(String userId, String friendId) {
		String key = getKey(userId, friendId);
		FriendDto fb = allFriends.get(key);
		if (fb == null) {
			return null;
		}
		return new FriendImpl(CityFactory.getCity(userId), fb);
	}

	public void remove(String id, String friendId) {
		String key = getKey(id, friendId);
		FriendDto remove = allFriends.remove(key);
		if (remove != null) {
			FriendDao DAO = Daos.getFriendDao();
			DAO.delete(id, friendId);
			DAO.delete(friendId, id);
		}
	}

	public void removeRequest(String id, String friendId) {
		String key = getKey(id, friendId);
		UserFriendRequestDto remove = allRequest.remove(key);
		if (remove != null) {
			UserFriendRequestDao DAO = Daos.getUserFriendRequestDao();
			DAO.delete(id, friendId);
			DAO.delete(friendId, id);
		}
	}

	public void sendRequest(String id, String userId) {
		UserFriendRequestDao DAO = Daos.getUserFriendRequestDao();

		final UserFriendRequestDto ur = DAO.createDTO();
		ur.setApplicant(id);
		ur.setReceiver(userId);
		ur.setRequestTime(System.currentTimeMillis());
		DAO.save(ur);

		String key = getKey(ur);
		allRequest.put(key, ur);
	}

	private String getKey(UserFriendRequestDto ur) {
		return getKey(ur.getApplicant(), ur.getReceiver());
	}
	public void removeAllRequest(String id) {
		Collection<UserFriendRequestDto> values = allRequest.values();
		Iterator<UserFriendRequestDto> it = values.iterator();
		while (it.hasNext()) {
			UserFriendRequestDto ur = it.next();
			String r = ur.getReceiver();
			String a = ur.getApplicant();
			if (r.equals(id)) {
				it.remove();
				UserFriendRequestDao DAO = Daos.getUserFriendRequestDao();
				DAO.delete(r, a);
				DAO.delete(a, r);
			}
		}
	}

	public void addFriend(String id, String friendId) {
		FriendDao DAO = Daos.getFriendDao();
		FriendDto fb = new FriendDto();
		fb.setUname(id);
		fb.setFriendName(friendId);
		DAO.save(fb);

		String key = getKey(fb);
		allFriends.put(key, fb);
	}

	public List<Request> getRequests(String id) {
		List<Request> ls = Lists.newArrayList();
		Collection<UserFriendRequestDto> values = allRequest.values();
		Iterator<UserFriendRequestDto> it = values.iterator();
		while (it.hasNext()) {
			UserFriendRequestDto ur = it.next();
			String r = ur.getReceiver();
			if (r.equals(id)) {
				ls.add(new RequestImpl(ur));
			}
		}
		return ls;
	}

}
