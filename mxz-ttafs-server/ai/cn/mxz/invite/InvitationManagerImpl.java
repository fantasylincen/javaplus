package cn.mxz.invite;

import java.util.List;

import cn.mxz.city.City;
import db.dao.impl.DaoFactory;
import db.dao.impl.InviteUsersDao;
import db.domain.InviteUsers;
import db.domain.InviteUsersImpl;

/**
 * 邀请管理器
 * @author 林岑
 *
 */
public class InvitationManagerImpl implements InvitationManager {

	InviteUsersDao DAO = DaoFactory.getInviteUsersDao();
	City	city;
	List<InviteUsers>	all;

	public InvitationManagerImpl(City city) {
		this.city = city;
		all = DAO.findByUname(city.getId());
	}

	@Override
	public void add(String id) {
		InviteUsers o = new InviteUsersImpl();
		o.setUname(city.getId());
		o.setFriend(id);
		DAO.add(o);
		all.add(o);
	}

	@Override
	public Gift getGift(int number) {
		return new GiftImpl(city, number, all);
	}

	@Override
	public int getCount() {
		return all.size();
	}


}
