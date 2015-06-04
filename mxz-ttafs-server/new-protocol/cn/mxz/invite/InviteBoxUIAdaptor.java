package cn.mxz.invite;

public class InviteBoxUIAdaptor implements InviteBoxUI {

	private InviteReward box;

	public InviteBoxUIAdaptor(InviteReward box) {
		this.box = box;
	}

	@Override
	public int getId() {
		return box.getId();
	}

	@Override
	public String getContent() {
		return box.getContent();
	}

	@Override
	public boolean getCanReceive() {
		return box.canReceive();
	}

	@Override
	public boolean getHasReceive() {
		return box.hasReceive();
	}

	@Override
	public String getDescription() {
		return box.getDescription();
	}

}
