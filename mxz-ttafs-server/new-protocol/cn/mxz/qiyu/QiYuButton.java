package cn.mxz.qiyu;

public interface QiYuButton {




	int getId();

	boolean getHasTips();

	int getFighterId();

	/**
	 * 云游先人ID
	 * @return
	 */
	int getYunYouId();

	/**
	 * 是否开启
	 * @return
	 */
	boolean isOpen();
}
