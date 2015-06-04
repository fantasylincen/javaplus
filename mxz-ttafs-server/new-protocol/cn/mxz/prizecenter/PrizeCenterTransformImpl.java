package cn.mxz.prizecenter;

import java.util.List;

import message.S;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.events.prizecenter.BeforeOpenPrizeCenterEvent;
import cn.mxz.mission.old.PrizeImpl;

import com.google.common.collect.Lists;

public class PrizeCenterTransformImpl implements IPrizeCenterTransform {

	City				user;

	@Override
	public void setUser(City user) {
		this.user = user;
	}


	@Override
	public IPrizeCenterUI getData() {
		Events.getInstance().dispatch(new BeforeOpenPrizeCenterEvent(user));
		return new PrizeCenterUIAdaptor(user.getPrizeCenter());
	}

//	@Override
//	public void getPrize(int id) {
//		user.getPrizeCenter().getPrize( id );
//
//	}

	@Override
	public void getPrizeFromLinekong(String prizeStr,int activityId) {
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		return;
		
		List<Prize> list = Lists.newArrayList();
		String[] tempArr = prizeStr.split(",");
		for( int i = 0; i < tempArr.length; ){
			int id = Integer.parseInt( tempArr[i++] );
			int count = Integer.parseInt( tempArr[i++] );
			Prize prize = new PrizeImpl( id, count);
			list.add( prize );
		}
		user.getPrizeCenter().getPrize(list, activityId );
		user.getMessageSender().send(S.S10147 );
	}


	@Override
	public void getPrize(int activityId) {
		user.getPrizeCenter().getPrize(activityId);
		user.getMessageSender().send(S.S10147 );
		
	}

}
