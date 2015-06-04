package cn.mxz.activity.boss;

import java.util.List;

import cn.mxz.protocols.user.boss.BossP.FriendPointPro;

/**
 * 
 * 好友分享积分
 * 
 * @author 林岑
 * 
 */

public class FriendPointBuilder {

	public FriendPointPro build(FriendSharedPoints fp) {

		FriendPointPro.Builder b = FriendPointPro.newBuilder();

		List<SharePoint> fs = fp.getAll();

		b.setAll(fp.getAllPoint());

		for (SharePoint s : fs) {

			b.addFriends(new FriendItemBuilder().build(s));
		}

		return b.build();
	}

}
