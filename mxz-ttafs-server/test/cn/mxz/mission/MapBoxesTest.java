package cn.mxz.mission;

import org.junit.Test;

import cn.mxz.city.City;
import cn.mxz.mission.old.MapBox;
import cn.mxz.mission.old.MapBoxImpl;
import cn.mxz.testbase.TestBaseAccessed;

public class MapBoxesTest extends TestBaseAccessed {


	@Test
	public final void test() {
		City city = getCity("lc100_r0");
		final MapBox box = new MapBoxImpl(140025, city);

		box.open(city.getPlayer());
	}

}
