package cn.mxz.formation;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

public class SelectedCampTest extends TestBaseAccessed{

	@Test
	public final void testGetFightersFront() {

		City city = getCity("dw117");

		Camp<Hero> selected = city.getFormation().getSelected();

		List<Hero> front = selected.getFighters();

		for (Hero hero : front) {

			Debuger.debug("SelectedCampTest.testGetFightersFront()" + hero);
		}
	}

	@Test
	public final void testGetFightersBack() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPosition() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

}
