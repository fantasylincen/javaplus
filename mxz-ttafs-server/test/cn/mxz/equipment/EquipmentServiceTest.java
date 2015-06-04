package cn.mxz.equipment;

import org.junit.Test;

import cn.mxz.handler.EquipmentService;
import cn.mxz.testbase.TestBaseAccessed;

public class EquipmentServiceTest extends TestBaseAccessed{

	public EquipmentService getService(){
		
		return (EquipmentService) factory.get("equipmentService");
		
	}
	
	//夺宝日志
		@Test
		public void testSnatchLog(){

			getService().snatchLog(1);
		}
		
	//夺宝
		@Test
		public void testSnatch(){

			getService().snatch("lc1", 130001);
			}
}
