package cn.mxz.shop;

import org.junit.Before;
import org.junit.Test;

import cn.mxz.handler.ShopService;
import cn.mxz.testbase.TestBaseAccessed;

public class ShopServiceTest extends TestBaseAccessed{

	private ShopService shopService;

	@Before
	public void initBefore() {
		shopService = (ShopService)factory.get("shopService");
	}
	
	@Test
	public void testGetData() {
		shopService.getData();
	}
	
	@Test
	public void testbuyTool() {
		shopService.buyTool(130002, 10);
	}


}
