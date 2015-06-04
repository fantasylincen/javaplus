package cn.mxz.friend;
import mongo.gen.MongoGen.FriendDto;
import cn.mxz.city.City;

class FriendImpl extends AbstractFriend implements Friend {


	FriendImpl(City me, FriendDto friend) {

		this.me = me;

		this.friend = friend;
	}
}
