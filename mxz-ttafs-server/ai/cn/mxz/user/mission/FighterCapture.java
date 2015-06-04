package cn.mxz.user.mission;

/**
 * 被俘获的战士
 * @author 林岑
 *
 */
public interface FighterCapture {

	/**
	 * 被俘获的战士ID
	 * @return
	 */
	int getId();

	/**
	 * 被俘获的战士位置
	 * @return
	 */
	
	int getPosition();

	/**
	 * 战士品阶
	 * @return
	 */
	
	int getStep();

}
