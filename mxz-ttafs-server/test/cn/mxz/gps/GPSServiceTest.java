package cn.mxz.gps;

import org.junit.Test;

import cn.mxz.handler.GPSService;
import cn.mxz.testbase.TestBaseAccessed;

public class GPSServiceTest extends TestBaseAccessed{
	
	public GPSService getService(){
		
		return (GPSService) factory.get("gPSServiceImpl");
		
	}


	//发送自己的坐标给服务器
	@Test
	public void testFriendListPro(){

		getService().sendLocation(1, 1);
	}
	
	//自己的好友
		@Test
		public void testgetLookAround(){

			getService().getLookAround(1, 3);
		}
}
