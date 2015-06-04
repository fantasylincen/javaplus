package cn.mxz.shop;

public class Price {

	int goldNew;

	int cashNew;

	private int goldOld;

	private int cashOld;

	private boolean isJinBeiKe;

	public int getGoldNew() {
		return goldNew;
	}

	public int getCashNew() {
		return cashNew;
	}

	public Price(boolean isJinBeiKe, int goldNew, int cashNew, int goldOld,
			int cashOld) {
		this.isJinBeiKe = isJinBeiKe;
		this.goldNew = goldNew;
		this.cashNew = cashNew;
		this.goldOld = goldOld;
		this.cashOld = cashOld;
	}

	public boolean isJinBeiKe() {
		return isJinBeiKe;
	}

	public void setJinBeiKe(boolean isJinBeiKe) {
		this.isJinBeiKe = isJinBeiKe;
	}

	public int getCashOld() {
		return cashOld;
	}

	public int getGoldOld() {
		return goldOld;
	}
}
