package cn.mxz.listeners;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.Listener;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.invite.InvitationManager;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDataDao;
import db.domain.UserData;

/**
 * 邀请有礼
 *
 * @author 林岑
 *
 */
//邀请有礼
public class InvitationGift implements Listener<UserCreateEvent> {

	@Override
	public void onEvent(UserCreateEvent event) {

		String code = event.getInvitationCode();

		if (code.isEmpty()) {
			return;
		}

		City friend = getCityByCode(code);

		if (friend == null) {
			return;
		}

		City me = event.getCity();

		toBeFriend(me, friend);

		InvitationManager manager = friend.getInvitationManager();

		manager.add(me.getId());
	}

	/**
	 * 根据激活码获得指定用户
	 *
	 * @param code
	 * @return
	 */
	private City getCityByCode(String code) {
		UserDataDao dao = DaoFactory.getUserDataDao();
		List<UserData> find = dao.findByInvitationCode(code);
		if (find.isEmpty()) {
			return null;
		}
		UserData first = Util.Collection.getFirst(find);
		return CityFactory.getCity(first.getUname());
	}

	private void toBeFriend(City me, City friend) {
		me.getFriendManager().add(friend.getId());
	}
}
