package cn.mxz.coutinuous;

public enum ContinuousType {

	/**
	 * 登陆奖励
	 */
	LOGIN_REWARD,DAJI

	;

	public Integer toNumber() {
		return ordinal();
	}

}
