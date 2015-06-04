package cn.mxz.activity.boss;

import cn.mxz.protocols.user.boss.BossP.FriendPointPro.FriendItemPro;
import cn.mxz.user.builder.UserBaseBuilder;

class FriendItemBuilder {

	public FriendItemPro build(SharePoint s) {

		FriendItemPro.Builder b = FriendItemPro.newBuilder();

		b.setAll(s.getAll());

		b.setRank(s.getRank());

		b.setShareToMe(s.getShareToMe());

		b.setUser(new UserBaseBuilder().build(s.getCity().getPlayer()));

		return b.build();
	}

}
