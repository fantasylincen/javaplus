package cn.mxz.mission.old;

import java.util.List;

public interface Location {

	
	int getPath();

	
	int getIndex();

	/**
	 * 判断该位置是否在ls中
	 *
	 * @param ls
	 *            位置列表
	 *
	 * @return
	 */
	
	boolean isIn(List<? extends LocationAble> ls);

}