package cn.mxz.log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.javaplus.page.Page;
import cn.javaplus.serchengine.SearchEngine;
import cn.javaplus.serchengine.SearchEngineImpl;
import cn.mxz.activity.friend.RandomUserGenerator;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.friend.Friend;
import cn.mxz.friend.FriendManager;
import cn.mxz.friend.Request;
import cn.mxz.protocols.user.friend.ApplyP.ApplyPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendSendAndReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendsAllListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendsAllPro;
import cn.mxz.protocols.user.friend.FriendAppayMessageListP.ApplyListPro;
import cn.mxz.protocols.user.friend.QueryFriendAppayP.QueryListPro;
import cn.mxz.user.Player;
import cn.mxz.user.builder.UserBaseBuilder;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class FriendApplyListBuilder implements IFriendApplyListBuilder {

	private City				city;
	private FriendManager		friendManager;
	private RandomUserGenerator	randomer;

	public FriendApplyListBuilder(City city , FriendManager friendManager) {
		this.city = city;
		this.friendManager = friendManager;
	}

	private FriendsAllPro buildFriendPro(String nick, int level, int type, boolean ispreson, String userId, int lot, boolean isPractice, int lastLogin, int vipLevel) {

		FriendsAllPro.Builder b = FriendsAllPro.newBuilder();

		b.setNick(nick);

		b.setLevel(level);

		b.setType(type);

		b.setIsMan(ispreson);

		b.setUserId(userId);

		b.setLot(lot);

		b.setIsPractice(ispreson);

		// 最后登陆时间
		b.setLastLogin(lastLogin);

		b.setBase(new UserBaseBuilder().build(getWorld().get(userId).getPlayer()));

		b.setVipLevel(vipLevel);
		
		b.setHasNewMessage(city.getUserChater().hasNewMessage(userId));

		return b.build();
	}

	private World getWorld() {

		return WorldFactory.getWorld();
	}

	private QueryListPro randomList() {
		Collection<City> rc = randomer.recommendFriends();
		return build(1, rc);
	}

	private QueryListPro getQueryMessageByNick(String nick, int pageNumber) {

		Collection<City> citys;
		if (nick.isEmpty()) {
			citys = randomer.recommendFriends();
		} else {
			citys = randomer.getCitys();
		}

		QueryListPro searchIn = searchIn(nick, pageNumber, citys);
		return searchIn;
	}

	private QueryListPro searchIn(String nick, int pageNumber, Collection<City> c) {

		c.remove(city);

		SearchEngine<City> se = new SearchEngineImpl<City>();

		ArrayList<City> a = Lists.newArrayList(c);

		Set<City> search = se.search(a, nick);

		return build(pageNumber, search);
	}

	private Player getPlayer() {
		return city.getPlayer();
	}

	private QueryListPro build(int pageNumber, Collection<City> search) {

		List<City> ls = Lists.newArrayList(search);

		ls = cn.javaplus.util.Util.Collection.sub(ls, 200); // 最多取前200条记录

		filterAreadyFriendAll(ls);

		Page<City> page = new Page<City>(ls, 10);

		List<City> page2 = page.getPage(pageNumber);

		int pageAll = page.getPageAll();

		return build2(page2, pageAll, pageNumber);
	}

	private void filterAreadyFriendAll(Collection<City> search) {
		Set<String> allFriendId = getAllFriendId();
		Iterator<City> it = search.iterator();
		while (it.hasNext()) {
			City city = (City) it.next();
			if (city.equals(this.city) || allFriendId.contains(city.getId())) {
				it.remove();
			}
		}
	}

	private Set<String> getAllFriendId() {

		List<Friend> all = friendManager.getAll();

		HashSet<String> set = Sets.newHashSet();

		for (Friend s : all) {

			set.add(s.getFriendId());
		}

		return set;
	}

	private QueryListPro build2(Collection<City> result, int pageAll, int pageNumber) {

		QueryListPro.Builder b = QueryListPro.newBuilder();

		for (City player : result) {

			b.addQueryMessage(new PlaysQueryBuilder().build(player));
		}

		b.setPageNow(pageNumber);

		b.setPageAll(pageAll);

		return b.build();
	}


	private List<ApplyPro> buildSends(ApplyListPro.Builder b, int page) {

		List<Request> sends = getAllSends();

		Page<?> p = subPage(sends, page);

		b.setPageNow(page);

		b.setPageAll(p.getPageAll());

		return build(sends);
	}

	private List<ApplyPro> build(List<Request> sends) {

		List<ApplyPro> all = new ArrayList<ApplyPro>();

		for (Request u : sends) {

			all.add(build(u));
		}

		return all;
	}

	private ApplyPro build(Request u) {

		ApplyPro.Builder b = ApplyPro.newBuilder();

		City city = getWorld().get(getOppositeId(getPlayer(), u));

		Team team2 = city.getTeam();

		Player player = city.getPlayer();

		Hero h = team2.getPlayer();

		String nick = player.getNick();

		int typeId = h.getTypeId();

		boolean man = player.isMan();

		int level = player.getLevel();

		String userId = player.getId();

		b.setType(typeId);

		b.setIsMan(man);

		b.setLevel(level);

		b.setNick(nick);

		b.setLastLogin(city.getPlayer().getLastLoginSec());

		b.setIsSend(isSend(u));

		b.setUserId(userId);

		return b.build();
	}

	private String getOppositeId(Player player, Request u) {

		if (u.getApplicant().equals(player.getId())) {

			return u.getReceiver();

		} else {

			return u.getApplicant();
		}
	}

	private Page<?> subPage(List<Request> sends, int page) {

		Page<Request> p = new Page<Request>(sends, 10);

		List<Request> ps = p.getPage(page);

		sends.clear();

		sends.addAll(ps);

		return p;
	}

	private boolean isSend(Request u) {

		String id = getCity().getPlayer().getId();

		return u.getReceiver().equals(id);
	}

	private City getCity() {
		return city;
	}

	private List<Request> getAllSends() {

		List<Request> all = Lists.newArrayList(friendManager.getRequests());
		Predicate<Request> predicate = new Predicate<Request>() {

			@Override
			public boolean apply(Request input) {
				return input.getReceiver().equals(getPlayer().getId());
			}
		};

		return Lists.newArrayList(Collections2.filter(all, predicate));
	}

	private List<ApplyPro> buildRecives(FriendReceiveListPro.Builder b, int page) {

		List<Request> sends = getAllRecives();

		Page<?> p = subPage(sends, page);

		b.setPageNow(page);

		b.setPageAll(p.getPageAll());

		return build(sends);
	}

	private List<ApplyPro> buildAllApply(FriendSendAndReceiveListPro.Builder b, int page) {

		List<Request> all = new ArrayList<Request>();

		all.addAll(getAllSends());

		all.addAll(getAllRecives());

		b.setPageNow(page);

		Page<?> p = subPage(all, page);

		b.setPageAll(p.getPageAll());

		return build(all);
	}

	private List<Request> getAllRecives() {
		List<Request> all = Lists.newArrayList(friendManager.getRequests());
		Predicate<Request> predicate = new Predicate<Request>() {

			@Override
			public boolean apply(Request input) {
				return input.getApplicant().equals(getPlayer().getId());
			}
		};

		return Lists.newArrayList(Collections2.filter(all, predicate));
	}

	@Override
	public FriendsAllListPro buildFriendAllList(int page) {

		FriendsAllListPro.Builder b = FriendsAllListPro.newBuilder();

		List<Friend> all = Lists.newArrayList(friendManager.getAll());

		// 玩家对应现在好友
		List<String> onf = new ArrayList<String>();

		for (Friend fb : all) {

			boolean isOnline = getWorld().isOnline(fb.getFriendId());

			if (isOnline) {

				onf.add(fb.getFriendId());
			}
		}

		int pageAll = 1;

//		if (all.size() >= 10 * (page - 1)) {

			final Page<Friend> datas = new Page<Friend>(all, 10000);

			pageAll = datas.getPageAll();

			final List<Friend> page2 = datas.getPage(page);

			int level = 0;

			int type = 0;

			boolean ispreson = true;

			String userId;

			int lot = 0;

//			Debuger.debug("AbstractFriendService.getFriendsAll() 我的好友:");

			for (Friend fBag : page2) {

//				Debuger.debug(">>" + fBag.getFriendId());

				String name = null;
				if (getPlayer().getId().equals(fBag.getMyId())) {
					name = fBag.getFriendId();
				}
				if (getPlayer().getId().equals(fBag.getFriendId())) {
					name = fBag.getMyId();
				}
				City city = getWorld().get(name);

				Team team2 = city.getTeam();

				Player player = city.getPlayer();

				Hero h = team2.getPlayer();

				ispreson = player.isMan();

				type = h.getTypeId();

				String nick = player.getNick();

				level = player.getLevel();

				userId = player.getId();

				boolean isPractice = false;

				int vipLevel = city.getVipCardPlayer().getLevel();
				b.addFriends(buildFriendPro(nick, level, type, ispreson, userId, lot, isPractice, city.getPlayer().getLastLoginSec(), vipLevel));
			}

			b.setFriendOnline(onf.size());

			b.setPageNow(page);

			b.setPageAll(pageAll);

			b.setFriendCount(all.size());

//		} else {
//
//			b.setPageNow(page);
//
//			b.setPageAll(pageAll);
//
//			b.setFriendOnline(onf.size());
//
//			b.setFriendCount(all.size());
//
//		}

		return b.build();
	}

	@Override
	public QueryListPro buildQueryMessage(String userMes, int page) {

		final String nick = userMes.trim();

		if (nick.isEmpty()) {

			return randomList();
		} else {

			return getQueryMessageByNick(nick, page);
		}
	}

	@Override
	public FriendSendAndReceiveListPro buildApplyRefuseMessage(int page) {

		FriendSendAndReceiveListPro.Builder b = FriendSendAndReceiveListPro.newBuilder();

		b.addAllFriends(buildAllApply(b, page));

		return b.build();
	}

	@Override
	public FriendReceiveListPro buildReceiveMessage(int page) {

		FriendReceiveListPro.Builder b = FriendReceiveListPro.newBuilder();

		b.addAllFriends(buildRecives(b, page));

		return b.build();
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see cn.mxz.log.IFriendApplyListBuilder#buildApplyMessage(int)
	 */
	@Override
	public ApplyListPro buildApplyMessage(int page) {

		ApplyListPro.Builder b = ApplyListPro.newBuilder();

		b.addAllApplyMessage(buildSends(b, page));

		return b.build();
	}

	public void setRandomUserGenerator(RandomUserGenerator randomer) {
		this.randomer = randomer;
	}
}
