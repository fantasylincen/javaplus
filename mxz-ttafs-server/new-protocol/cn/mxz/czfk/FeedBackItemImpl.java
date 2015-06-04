package cn.mxz.czfk;

public class FeedBackItemImpl implements FeedBackItem {

	private FeedBack feedBack;

	public FeedBackItemImpl(FeedBack feedBack) {
		this.feedBack = feedBack;
	}

	public int getId() {
		return feedBack.getId();
	}

	public int getBagId() {
		return feedBack.getBagId();
	}

	public String getName() {
		return feedBack.getName();
	}

	public int getRechargeNeed() {
		return feedBack.getRechargeNeed();
	}

	public boolean getCanReceive() {
		return feedBack.getCanReceive();
	}

	public boolean getHasReceive() {
		return feedBack.getHasReceive();
	}

}
