package cn.mxz.invite;

import java.util.List;

import cn.mxz.city.City;

public class InviteTransformImpl implements InviteTransform {

	private City user;

	@Override
	public InviteUI getUI() {
		return new InviteUIAdaptor(user.getInviteManager());
	}

	@Override
	public CodeUI getMyCode() {
		InviteManager manager = user.getInviteManager();
		return new CodeUIImpl(manager.getMyCode());
	}

	@Override
	public CommitResult commitCode(String code) {
		InviteManager manager = user.getInviteManager();
		return new CommitResultAdaptor(manager.commitCode(code));
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public InviteUI receive(int id) {

		InviteManager manager = user.getInviteManager();
		List<InviteReward> all = manager.getRewards();
		for (InviteReward i : all) {
			int id2 = i.getId();
			if (id == id2) {
				i.receive();
			}
		}
		return new InviteUIAdaptor(manager);
	}

}
