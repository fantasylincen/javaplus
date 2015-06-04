package cn.mxz.czfk2;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class FeedBack2UIImpl implements FeedBack2UI {

	private FeedBackManager2 manager;

	public FeedBack2UIImpl(FeedBackManager2 manager) {
		this.manager = manager;
	}

	@Override
	public List<FeedBack2Item> getItems() {
		ArrayList<FeedBack2Item> ls = Lists.newArrayList();
		List<FeedBack2> basks = manager.getBoxes();
		for (FeedBack2 feedBack : basks) {
			ls.add(new FeedBack2ItemImpl(feedBack));
		}
		return ls;
	}

	@Override
	public int getRechargeAll() {
		return manager.getRechargeAll();
	}

	@Override
	public int getRemainSec() {
		return manager.getRemainSec();
	}

}
