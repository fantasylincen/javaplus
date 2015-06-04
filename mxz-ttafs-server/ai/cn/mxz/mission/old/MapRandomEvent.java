package cn.mxz.mission.old;

/**
 * 随机事件
 *
 * @author PC 2013.8.1
 */
public interface MapRandomEvent {

	/**
	 * @return 随机事件ID
	 */
	int getId();

	/**
	 * 响应随机事件
	 * @return
	 */
	String responseEvent();

	int getIndex();

	int getPath();

}
