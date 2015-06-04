package cn.mxz.mission.star;

import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util.Collection;

public class MissionStarBuilder {

	public String build(List<MissionStar> all) {
		return Collection.linkWith(",", all, new Fetcher<MissionStar, String>() {

			@Override
			public String get(MissionStar s) {
				return build(s);
			}
		});
	}

	private String build(MissionStar s) {
		int count = s.getCount();
		int max = s.getMax();

		if (count > max) {
			count = max;
		}

		String t = s.getMissionId() + ":" + count + "/" + max;
//		Debuger.debug("MissionStarBuilder.build()" + t);
		return t;
	}

}
