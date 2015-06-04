package cn.mxz.mission;

import java.util.List;

import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.mission.old.MapBox;
import cn.mxz.mission.old.MapBoxImpl;

import com.google.common.collect.Lists;

public class MapBoxes {

	private List<Integer>	all;
	private City	user;

	public MapBoxes(List<Integer> all, City user) {
		this.all = all;
		this.user = user;
	}

	public List<Prize> open() {
		List<Prize> ls = Lists.newArrayList();
		for (Integer id : all) {
			ls.addAll(open(id));
		}
		return ls;
	}

	private List<Prize> open(Integer id) {
		final MapBox box = new MapBoxImpl(id, user);
		final ConsumableTemplet c = ConsumableTempletConfig.get(box.getId());
		box.open(user.getPlayer());
		List<Prize> all = box.getAll();
		return all;
	}

}
