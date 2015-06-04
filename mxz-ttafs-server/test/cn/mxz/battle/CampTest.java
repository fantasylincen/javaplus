package cn.mxz.battle;

import org.junit.Test;

import cn.mxz.city.City;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.handler.UserService;
import cn.mxz.testbase.TestBaseAccessed;

public class CampTest extends TestBaseAccessed {


	@Test
	public final void testGetFightingCapacity() {

		UserService u = (UserService) factory.get("userService");


		City city = getCity(userId);

		PlayerCamp selected = city.getFormation().getSelected();

		for (int i = 0; i < 1000000; i++) {
			long t1 = System.currentTimeMillis();
//			UserPro data = u.getData();

			selected.getShenJia();
			System.out.println("耗时:" + (System.currentTimeMillis() - t1));

//			System.out.println(i + ":"  + cap);
		}

	}

}
