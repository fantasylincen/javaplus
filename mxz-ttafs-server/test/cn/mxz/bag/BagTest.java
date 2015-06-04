package cn.mxz.bag;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import cn.javaplus.util.Util;
import cn.mxz.StuffTempletConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.handler.BagService;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.util.debuger.Debuger;
import define.D;

public class BagTest extends TestBaseAccessed {

	// 材料
	// 130001 -- 130007

	// 宝石
	// 120002 -- 120006
	// 120102 -- 120106 .....

	// 装备
	// 150001 -- 150162

	// 消耗品
	// 140001 -- 140004

	// 鱼
	// 160001 -- 160015

	private BagService getService() {
		return ((BagService) factory.get("bagService"));
	}

	private Bag<Grid> getBag() {

		final BagService b = getService();

		return b.getCity().getBag();
	}

	@Test
	public void testAddOneProp() {

		getBag().addProp(130003, 304);

//
//		City city = getCity("");
//
//		Hero god = city.getTeam().getGod(1);
//
//		city.getTeam().remove(new HeroImpl());
	}

	@Test
	public void testAddAnyProp() {

		for (int i = 0; i < 13; i++) {

			Debuger.debug("BagServiceTest.testAddAnyProp() 添加次数:" + i);

			getBag().addProp(130001, 1);
		}
	}


	/**
	 *
	 * 移除很多个道具
	 *
	 * 	1.如果足够n个, 全部移除完
	 *
	 * 	2.如果不够, 一个都不移除, 抛出数量不够异常
	 *
	 */
	@Test
	public void testRemoveSoMany() {

		getBag().remove(130001, 100);

	}


	@Test
	public void testAddRandomPropToRandomUser() {
		StuffTempletConfig.load();
		Collection<City> all = WorldFactory.getWorld().getAll();

//		int prop = getRandomProp();
		int prop = 136015;
		for (int i = 0; i < 10000; i++) {
			City user = Util.Random.getRandomOne(all);


			user.getPiecesBag().addProp(prop, 1);
			Debuger.debug("BagTest.testAddRandomPropToRandomUser()" + user + ", " + prop + ", " + i);
		}
	}

	private int getRandomProp() {
		List<Integer> keys = StuffTempletConfig.getKeys();
		return Util.Random.getRandomOne(keys);
	}

	/**
	 * 预期:
	 *
	 * 	1.如果增加1000个130001, (130001的叠加上限是20), 那么就得准备50个格子来装130001, 每个格子装20个
	 *
	 * 	2.需要满足:要么一次加完, 要么一个都不加
	 *
	 */
	@Test
	public void testAddTooMany() {

		// 采用的2.要么一次加完, 要么一个都不加
		getBag().addProp(130001, 10000);

		getBag().addProp(130001, 10000);
	}

	@Test
	public void testAddAndRemove() {

		getBag().addProp(130001, 860);

		getBag().remove(130001, 286);
	}

	@Test
	public void testUseProp() {

		getService().useProp(100);
	}

	@Test
	public void testAddAnyTimes() {

		for (int i = 0; i < 100; i++) {

			getBag().addProp(130001, 1);
		}
	}


	@Test
	public void testGetRemainTimes() {

		System.out.println(getService().getRemainUseTimes(D.ID_SHEN_XING_DAN));
	}
}
