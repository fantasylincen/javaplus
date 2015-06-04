package cn.mxz.invite;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.FriendsFeedbackTemplet;
import cn.mxz.FriendsFeedbackTempletConfig;
import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.InviteService;
import cn.mxz.protocols.user.InviteP.InvitesPro;
@Component("inviteService")
@Scope("prototype")
public class InviteServiceImpl extends AbstractService implements InviteService {

	@Override
	public InvitesPro getData() {
		InvitesPro.Builder b = InvitesPro.newBuilder();
		List<FriendsFeedbackTemplet> all = FriendsFeedbackTempletConfig.getAll();
		InvitationManager m = getCity().getInvitationManager();
		b.setCount(m.getCount());
		for (FriendsFeedbackTemplet t : all) {
			Gift g = m.getGift(t.getNumber());
			b.addGifts(new InviteBuilder().build(g));
		}
		return b.build();
	}

	@Override
	public void receive(int count) {
		InvitationManager m = getCity().getInvitationManager();
		Gift g = m.getGift(count);
		g.receive();
	}

}
