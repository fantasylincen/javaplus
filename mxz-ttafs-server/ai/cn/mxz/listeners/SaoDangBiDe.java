package cn.mxz.listeners;

import java.util.List;

import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.UserPrizeSender;
import cn.mxz.events.Listener;
import cn.mxz.events.MoppingUpEvent;
import cn.mxz.mission.IMissionManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import define.D;

/**
 * 新手扫荡一次 小怪 2 , 3 关卡 获得某个奖励
 * 
 * @author 林岑
 * 
 */
//新手扫荡一次必得
public class SaoDangBiDe implements Listener<MoppingUpEvent> {

	
	@Override
	public void onEvent(MoppingUpEvent e) {

		int count = e.getCount();
		
		if(count != 1) {
			return;
		}
		
		City user = e.getUser();
		int missionId = e.getMissionId();

		MissionMapTemplet temp = MissionMapTempletConfig.get(missionId);
		if (!isNew(temp, user)) {
			return;
		}
		

		StringResolver r = Util.Str.resolve(D.SAO_DANG_BI_DE);
		List<StringResolver> ls = r.split("#");
		for (StringResolver s : ls) {
			List<StringResolver> ss = s.split(":");
			int mId = ss.get(0).getInt();
			if (missionId == mId) {
				UserPrizeSender sender = user.getPrizeSender1();
				String award = ss.get(1).getString();
				List<Prize> ls1 = sender.buildPrizes(award);
				e.getPrize().addAll(ls1);
			}
		}

	}

	private boolean isNew(MissionMapTemplet temp, City user) {
		IMissionManager ms = user.getMission();
		boolean b = temp.getIsNew() == 1;

		int maxMissionId = ms.getMaxMissionId();
		int maxNewPlayerMissionId = getMaxNewPlayerMissionId();

		return b && maxMissionId <= maxNewPlayerMissionId;
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
