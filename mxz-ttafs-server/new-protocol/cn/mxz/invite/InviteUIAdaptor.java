package cn.mxz.invite;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.log.Debuger;

import com.google.common.collect.Lists;

public class InviteUIAdaptor implements InviteUI {

	private InviteManager manager;

	public InviteUIAdaptor(InviteManager inviteManager) {
		this.manager = inviteManager;
	}

	@Override
	public int getNumber() {
		int number = manager.getNumber();
		Debuger.debug("邀请人数：" + number);
		return number;
	}

	@Override
	public String getMyCode() {
		return manager.getMyCode();
	}

	@Override
	public boolean getHasBeenInvited() {
		boolean hasBeanInvited = manager.hasBeanInvited();
		return hasBeanInvited;
	}

	@Override
	public List<InviteBoxUI> getBoxes() {
		List<InviteReward> rs = manager.getRewards();
		ArrayList<InviteBoxUI> ls = Lists.newArrayList();
		for (InviteReward b : rs) {
			ls.add(new InviteBoxUIAdaptor(b));
		}
		return ls;
	}

	@Override
	public boolean getHasReceive() {
		return manager.hasReceive();
	}

}
