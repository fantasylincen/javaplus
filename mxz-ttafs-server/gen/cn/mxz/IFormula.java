package cn.mxz;

public interface IFormula {

	/**
	 * 所属大类
	 */
	public abstract String getType();

	/**
	 * 方法名字
	 */
	public abstract String getMethodName();

	/**
	 * 参数列表
	 */
	public abstract String getArgs();

	/**
	 * 计算公式
	 */
	public abstract String getFormula();

	/**
	 * 说明
	 */
	public abstract String getComment();

	/**
	 * 返回值类型
	 * @return
	 */
	public abstract String getReturnType();

}