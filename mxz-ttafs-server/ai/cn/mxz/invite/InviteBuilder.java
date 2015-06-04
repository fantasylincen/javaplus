package cn.mxz.invite;

import cn.mxz.protocols.user.InviteP.InvitePro;

public class InviteBuilder {

	public InvitePro build(Gift g) {
		InvitePro.Builder b = InvitePro.newBuilder();
		b.setCount(g.getCount());
		b.setHasReceived(g.hasReceive());
		return b.build();
	}

}
