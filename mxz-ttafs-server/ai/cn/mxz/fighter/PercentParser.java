package cn.mxz.fighter;

/**
 * 百分比转换器
 * @author 林岑
 *
 */

public class PercentParser {

	/**
	 * v * 100 后, 截取1位小数, 返回
	 * @param v
	 * @return
	 */
	public String parse(float v) {

		return String.format("%.1f", v);
	}
}
