package cn.mxz.onlinereward;

import java.util.Collections;
import java.util.List;

import cn.mxz.OnlineTemplet;
import cn.mxz.OnlineTempletConfig;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class OnlineRewardManager {

	private City city;
	private List<OnlineRewardBox> boxes;

	public OnlineRewardManager(City city) {
		this.city = city;
		initBoxes();
	}

	private void initBoxes() {
		boxes = Lists.newArrayList();
		List<Integer> keys = Lists.newArrayList(OnlineTempletConfig.getKeys());
		Collections.reverse(keys);
		for (Integer id : keys) {
			OnlineTemplet temp = OnlineTempletConfig.get(id);
			boxes.add(new OnlineRewardBox(temp, city));
		}
	}

	public OnlineRewardBox getBoxCanReceive() {
		List<OnlineRewardBox> all = getBoxes();
		for (OnlineRewardBox b : all) {
			if (b.getCanReceive()) {
				return b;
			}
		}
		return null;
	}

	public void receiveById(int id) {
		OnlineRewardBox box = getBox(id);
		box.receive();
	}

	public OnlineRewardBox getBox(int id) {
		List<OnlineRewardBox> all = getBoxes();
		for (OnlineRewardBox box : all) {
			if (box.getId() == id) {
				return box;
			}
		}
		return null;
	}

	public List<OnlineRewardBox> getBoxes() {
		return boxes;
	}

	public boolean hasReceiveAllReward() {
		for (OnlineRewardBox b : boxes) {
			if(!b.getHasReceive()) {
				return false;
			}
		}
		return true;
	}

	public boolean hasRewardCanReceive() {
		for (OnlineRewardBox b : boxes) {
			if(b.getCanReceive()) {
				return true;
			}
		}
		return false;
	}

}
