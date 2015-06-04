package cn.mxz.activity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.ActivityService;
import cn.mxz.protocols.user.activity.ActivityP.ActivityPro;

@Component("activityService")
@Scope("prototype")
public class ActivityServiceImpl extends AbstractService implements ActivityService {

	// 0:神马也没开启, 1:Boss活动, 2:爬塔活动
	@Override
	@Deprecated
	public int getActivityType() {

		// if(BossActivityImpl.getInstance().isStart()) {
		//
		// return 1;
		// }
		//
		// if(TowerActivityImpl.getInstance().isStart()) {
		//
		// return 2;
		// }

		return 0;
	}

	@Deprecated
	@Override
	public ActivityPro getData() {

		// return new ActivityBuilder().build(getCity());
		return null;
	}

	@Override
	public String getActivityStatus() {
		// TODO LC
		return "";
	}

}
