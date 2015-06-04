package cn.mxz.battle;

import java.util.List;

import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.user.team.god.ShenJiaAble;

/**
 * 阵容
 * @author 林岑
 *
 *	Iterabtor:遍历所有的战士(包括已经死掉的)
 *
 */
public interface Camp<T extends Fighter> extends ShenJiaAble /*extends Iterable<T>*/ {

	/**
	 * 所有上阵战士
	 */
	List<T> getFighters();

	/**
	 * 获得战士在阵容中的位置
	 * @param f
	 * @return
	 */
	int getPosition(Fighter f);

	/**
	 * 获得指定位置上的战士
	 * @param position
	 * @return
	 */
	Fighter get(int position);

	/**
	 * 主战士
	 * @return
	 */
	Fighter getMainFighter();

	/**
	 * 阵容上的神兽
	 * @return
	 */
	Dogz getDogz();

	String getUserId();
}
