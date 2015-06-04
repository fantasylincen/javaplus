package cn.mxz.shangxiang;

import org.junit.Test;

import cn.mxz.handler.UserShangxiangService;
import cn.mxz.testbase.TestBaseAccessed;

public class UserShangxiangServiceTest extends TestBaseAccessed{
	
public UserShangxiangService getService(){
		
		return (UserShangxiangService) factory.get("userShangxiangServiceImpl");
		
	}
	
	//获得玩家当前上香状态
	@Test
	public void testgetSX(){
	
		getService().getSX();
	}
	
	//上香
	@Test
	public void testsendSX(){
	
		getService().sendSX();
	}
}
