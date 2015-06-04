package cn.mxz.fighter;

import org.junit.Test;

import cn.mxz.testbase.TestBaseAccessed;

/**
 *
 * 战士协议测试
 * @author 林岑
 *
 */
public class FighterServiceTest extends TestBaseAccessed{


	@Test
	public void testGetFighter() {
//		((FighterService)factory.get("fighterService")).getFighter(30019);
	}


	@Test
	public void addGods() {

		for (int i = 0; i < 40; i++) {
//			400005
//			400006
//			400007
//			400008

			getCity("dw200").getTeam().createNewHero(400005);
		}
	}


}
