package cn.mxz;

/**
 * 道具模板
 * @author 林岑
 */
public interface PropTemplet {

	/**
	 * 叠加上限
	 * @return
	 */
	int getAddUp();

	/**
	 * 道具名字
	 * @return
	 */
	String getName();

	/**
	 * 道具识别ID
	 * @return
	 */
	int getSpot();

	/**
	 * 类型ID
	 * @return
	 */
	int getId();

	/**
	 * 获取品质
	 * @return
	 */
	int getQuality();
}
