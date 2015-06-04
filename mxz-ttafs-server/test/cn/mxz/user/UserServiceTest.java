package cn.mxz.user;

import org.junit.Before;
import org.junit.Test;

import cn.mxz.handler.UserService;
import cn.mxz.testbase.TestBaseAccessed;


public class UserServiceTest extends TestBaseAccessed{
	
	private UserService service;
	
	@Before
	public void initUserService() {
		service = (UserService)factory.get("userService");
	}
	
	@Test
	public void testGetData() {
		service.getData();
	}
	
	@Test
	public void testGetFightingCapcity() {
		final int c = service.getFightingCapacity();
		System.out.println("战斗力:" + c);
	}
}
