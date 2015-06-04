package cn.mxz.mission.type;

import java.util.List;

import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.Util;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.city.City;
import cn.mxz.mission.IMissionManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class GuidePlayer {

	private City	user;
	private MissionMapTemplet	temp;

	public GuidePlayer(MissionMapTemplet temp, City user) {
		this.temp = temp;
		this.user = user;
	}

	public boolean isNew() {
		IMissionManager ms = user.getMission();
		boolean b = temp.getIsNew() == 1;

		int maxMissionId = ms.getMaxMissionId();
		int maxNewPlayerMissionId = getMaxNewPlayerMissionId();

		return b && maxMissionId < maxNewPlayerMissionId;
	}

	/**
	 * 最大新手地图id
	 * @return
	 */
	private int getMaxNewPlayerMissionId() {
		List<MissionMapTemplet> all = Lists.newArrayList();
		all.addAll(MissionMapTempletConfig.getAll());
		List<MissionMapTemplet> a = filterNew(all);
		int[] ids = getAllIds(a);
		return Util.Array.getMax(ids);
	}

	private int[] getAllIds(List<MissionMapTemplet> a) {
		return Util.Collection.getArrayByOneFields(new IntegerFetcher<MissionMapTemplet>() {

			@Override
			public Integer get(MissionMapTemplet t) {
				return t.getId();
			}
		}, a);
	}

	private List<MissionMapTemplet> filterNew(List<MissionMapTemplet> all) {
		List<MissionMapTemplet> a = Lists.newArrayList(Collections2.filter(all, new Predicate<MissionMapTemplet>() {

			@Override
			public boolean apply(MissionMapTemplet input) {
				return input.getIsNew() == 1;
			}
		}));
		return a;
	}

}
