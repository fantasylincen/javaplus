package cn.mxz.onlinereward;

import java.util.List;

public class OnlineRewardBoxItemImpl implements OnlineRewardBoxItem {

	private OnlineRewardBox box;
	private List<OnlineRewardBox> boxes;

	public OnlineRewardBoxItemImpl(OnlineRewardBox box, List<OnlineRewardBox> boxes) {
		this.box = box;
		this.boxes = boxes;
	}

	public boolean getCanReceive() {
		return box.getCanReceive();
	}

	public boolean getHasReceive() {
		return box.getHasReceive();
	}

	public int getRemainSec() {
		return box.getRemainSec();
	}

	public String getReward() {
		return box.getReward();
	}

	@Override
	public int getId() {
		return box.getId();
	}

	@Override
	public int compareTo(OnlineRewardBoxItem o) {
		return getId() - o.getId();
	}

	@Override
	public boolean isShowCd() {
		return getLowerestCdBox() == box;
	}

	private OnlineRewardBox getLowerestCdBox() {
		int min = Integer.MAX_VALUE;
		
		OnlineRewardBox bx = null;
		for (OnlineRewardBox b : boxes) {
			if(b.getHasReceive()) {
				continue;
			}
			if(b.getRemainSec() < min) {
				min = b.getRemainSec();
				bx = b;
			}
		}
		return bx;
	}
}
