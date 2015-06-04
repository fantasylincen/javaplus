package cn.mxz.czfk;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class FeedBackUIImpl implements FeedBackUI {

	private FeedBackManager fm;

	public FeedBackUIImpl(FeedBackManager fm) {
		this.fm = fm;
	}

	@Override
	public List<FeedBackItem> getItems() {
		ArrayList<FeedBackItem> ls = Lists.newArrayList();
		List<FeedBack> basks = fm.getBoxes();
		for (FeedBack feedBack : basks) {
			ls.add(new FeedBackItemImpl(feedBack));
		}
		return ls;
	}

	@Override
	public int getRechargeAll() {
		return fm.getRechargeAll();
	}

	@Override
	public int getRemainSec() {
		return fm.getRemainSec();
	}

}
