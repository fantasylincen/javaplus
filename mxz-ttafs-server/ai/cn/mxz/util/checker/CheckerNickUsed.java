package cn.mxz.util.checker;

import java.util.Map;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;


/**
 * 昵称检查器， 用于检查该昵称是否可用
 * @param nick	昵称
 * @return
 */
public class CheckerNickUsed {

	public void check(String nick) {

		final World world = WorldFactory.getWorld();
		final Map<String, String> all = world.getNickManager().getNickAll();
		if(all.containsKey(nick)) {
			throw new OperationFaildException(S.S10002);
		}
	}

}
