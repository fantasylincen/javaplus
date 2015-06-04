package cn.mxz.loginreward;

public interface Result {

	/**
	 * 下一次可以领取多少元宝
	 * @return
	 */
	int getNextGold();

	/**
	 * 这一次领取到了多少元宝
	 * @return
	 */
	int getReceived();

}
