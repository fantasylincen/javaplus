package cn.mxz.fengshentai;

public interface IPropTransform {
	public abstract int getBuyCount();

	public abstract int getPropId();

	/**
	 * 返回冷却到期时间，如果过了返回0
	 * @param endSec
	 * @return
	 */
	public abstract int getRemainSec();
	
	/**
	 * 已经拥有的数量
	 * @return
	 */
	public int getCount();
}
