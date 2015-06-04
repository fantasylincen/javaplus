package cn.mxz.listeners.heishi;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.init.EnterGameEvent;
import cn.mxz.util.counter.CounterKey;
import define.D;

public class OnEnterGameListener implements Listener<EnterGameEvent> {

	@Override
	public void onEvent(EnterGameEvent e) {
		if( D.LANGUAGE != 2 ){//仅限于台湾版本
			return;
		}
		
		ActivityTemplet temp = ActivityTempletConfig.get(ActivityIds.XianShiHeiShi_14);
		
		boolean in = Util.Time.isIn(temp.getTime());
		if( !in){//不在活动中
			return;
		}
		City user = e.getCity();
		
		if( !user.getUserCounter().isMark( CounterKey.HEI_SHI_MEI_RI_DENG_LU)){
			user.getPrizeCenter().addPrize(5, D.HEI_SHI_SHUI_JING_JIANG_LI_1, S.STR60291, S.STR60290);
			user.getUserCounter().mark( CounterKey.HEI_SHI_MEI_RI_DENG_LU);
			
		}
	}

}
