package cn.mxz.mission;

import java.util.List;

/**
 * 副本章节完成度
 * @author 林岑
 *
 */
public interface MissionCompletenessUI {

	/**
	 * 完成度分子
	 * @return
	 */
	int getN();

	/**
	 * 完成度分母
	 * @return
	 */
	int getD();

	/**
	 * 章节宝箱
	 * @return
	 */
	List<BoxUI> getBoxes();
}
