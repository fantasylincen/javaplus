package cn.mxz.friend;


import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

/**
 * 好友管理器工厂
 *
 * @author 林岑
 *
 */
public class FriendManagerFactory {

	private static final class FriendManagerListenerImpl implements FriendManagerListener {

		private final City c;

		String nick;

		private FriendManagerListenerImpl(City c) {
			this.c = c;
			nick = c.getPlayer().getNick();
		}

		@Override
		public void onAccept(AcceptEvent e) {

			City c2 = WorldFactory.getWorld().get(e.getUserId());

			MessageFactory.getFriend().onFriendMessage(c2.getSocket(),
					c.getId(), nick);

			MessageFactory.getFriend().onToBeFriend(c2.getSocket(), c.getId(),
					nick);

			MessageFactory.getUser().onUpdateUserList(c2.getSocket(), new UserBuilder().build(c2));


			addCharm(c2);

		}

		/**
		 * 增加魅力值
		 * @param c2
		 */
		private void addCharm(City c2) {

			final int add = D.ADD_FRIEND_CHARM;

			add(c, add);

			add(c2, add);
		}

		private void add(City city, int add) {

			UserCounter u = city.getUserCounterHistory();

			int his = u.get(CounterKey.FRIEND_ACCEPT_CHARM_ADD);

			if(his < 1000) {

				u.add(CounterKey.FRIEND_ACCEPT_CHARM_ADD, add);

				city.getPlayer().add(PlayerProperty.CHARM, add);
			}
		}

		@Override
		public void onRequest(RequestEvent e) {

			City c2 = WorldFactory.getWorld().get(e.getUserId());

			MessageFactory.getFriend().onFriendMessage(c2.getSocket(),
					c.getId(), nick);

			MessageFactory.getFriend().onGetRequest(c2.getSocket(), c.getId(),
					nick);

			MessageFactory.getUser().onUpdateUserList(c2.getSocket(), new UserBuilder().build(c2));
		}
	}

	/**
	 * 普通好友管理器
	 *
	 * @param c
	 * @return
	 */
	public static FriendManager createFriendManager(final City c) {

		final FriendManagerImpl fm = new FriendManagerImpl(c);

		fm.addListener(new FriendManagerListenerImpl(c));

		return fm;
	}
//
//	/**
//	 * 活动好友管理器
//	 *
//	 * @param c
//	 * @return
//	 */
//	public static ActivityFriendManager createActivityFriendManager(City c) {
//
//		final ActivityFriendMangerImpl fm = new ActivityFriendMangerImpl();
//
//		fm.setCity(c);
//
//		return fm;
//	}

}
