package cn.mxz.xianshi;

import java.util.Iterator;
import java.util.List;

import cn.mxz.FighterTemplet;
import cn.mxz.IFighterTemplet;

/**
 * 过滤出所有非主角玩家
 * @author 林岑
 *
 */
public class PlayerFilter {

	/**
	 * 过滤出所有非主角玩家
	 * @param q
	 */
	public void filter(List<FighterTemplet> q) {
		Iterator<FighterTemplet> it = q.iterator();
		while (it.hasNext()) {
			IFighterTemplet f = it.next();
			int c = f.getCategory();
			if(c == 3) {
				it.remove();
			}
		}
	}

}
