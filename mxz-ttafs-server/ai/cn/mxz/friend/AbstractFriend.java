package cn.mxz.friend;

import mongo.gen.MongoGen.FriendDto;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;

public class AbstractFriend implements Friend {

	protected FriendDto	friend;
	protected City		me;

	public AbstractFriend() {
		super();
	}

	@Override
	public String getFriendId() {

		if (me.getId().equals(friend.getUname())) {

			return friend.getFriendName();

		} else {

			return friend.getUname();
		}
	}

	@Override
	public City getFriendCity() {

		World w = WorldFactory.getWorld();

		return w.get(getFriendId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend == null) ? 0 : friend.hashCode());
		result = prime * result + ((me == null) ? 0 : me.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFriend other = (AbstractFriend) obj;
		if (friend == null) {
			if (other.friend != null)
				return false;
		} else if (!friend.equals(other.friend))
			return false;
		if (me == null) {
			if (other.me != null)
				return false;
		} else if (!me.equals(other.me))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getFriendId();
	}

	@Override
	public String getMyId() {
		return me.getId();
	}

}