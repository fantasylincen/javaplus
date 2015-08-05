package cn.javaplus.crazy.pocker;

public class JdzBean {

	private boolean isSayDz;
	private Place place;
	private boolean isJdzing;

	public JdzBean(boolean isSayDz, Place place, boolean isJdzing) {
		this.isSayDz = isSayDz;
		this.place = place;
		this.isJdzing = isJdzing;
	}

	public boolean isSayDz() {
		return isSayDz;
	}

	public Place getPlace() {
		return place;
	}

	public boolean isJdzing() {
		return isJdzing;
	}

}
