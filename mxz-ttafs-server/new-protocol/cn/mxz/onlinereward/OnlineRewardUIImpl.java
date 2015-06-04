package cn.mxz.onlinereward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class OnlineRewardUIImpl implements OnlineRewardUI {

	private OnlineRewardManager manager;

	public OnlineRewardUIImpl(OnlineRewardManager manager) {
		this.manager = manager;
	}

	@Override
	public List<OnlineRewardBoxItem> getBoxes() {
		ArrayList<OnlineRewardBoxItem> ls = Lists.newArrayList();
		List<OnlineRewardBox> boxes = manager.getBoxes();
		for (OnlineRewardBox box : boxes) {
			ls.add(new OnlineRewardBoxItemImpl(box, boxes));
		}
		sort(ls);
		return ls;
	}

	private void sort(ArrayList<OnlineRewardBoxItem> ls) {
		Collections.sort(ls);
	}

}
