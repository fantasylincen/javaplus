package cn.mxz.fengshentai;

public class PropItem {
	
	

	public PropItem() {
		
	}

	/**
	 * 冷却结束的时间，以秒为单位
	 */
	private int coldDown;
	
	/**
	 * 已经购买的次数
	 */
	private int buyCount;
	
	/**
	 * 对应的道具Id
	 */
	private int	propId;

	public PropItem(int typeId) {
		this.setPropId(typeId);
	}

	public int getColdDown() {
		return coldDown;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.aaa#getBuyCount()
	 */
	public int getBuyCount() {
		return buyCount;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.aaa#getPropId()
	 */
	public int getPropId() {
		return propId;
	}

	public void setColdDown(int coldDown) {
		this.coldDown = coldDown;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.fengshentai.aaa#getRemainSec()
	 */
	public int getRemainSec() {
		int current = (int) (System.currentTimeMillis() / 1000);
		return Math.max( coldDown - current, 0 );
	}

	/**
	 * @param propId 要设置的 propId
	 */
	public void setPropId(int propId) {
		this.propId = propId;
	}

	
	
}
