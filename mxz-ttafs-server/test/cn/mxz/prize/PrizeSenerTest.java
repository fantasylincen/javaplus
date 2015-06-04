/**
 *
 */
package cn.mxz.prize;

import org.junit.Test;

import cn.mxz.base.prize.PrizeSender;
import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.user.Player;
import cn.mxz.util.Factory;

/**
 *
 * @author 林岑
 *
 */
public class PrizeSenerTest extends TestBaseAccessed {

	@Test
	public final void test() {

		final PrizeSender p = (PrizeSender) Factory.get("prizeSener");

		Player player = null;

		p.send(player, "");
	}

}
