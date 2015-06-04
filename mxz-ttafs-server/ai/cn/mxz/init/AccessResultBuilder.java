package cn.mxz.init;

import cn.mxz.protocols.user.UserP.AccessResultPro;

public class AccessResultBuilder {

	public AccessResultPro build(boolean isNewUser, int errorCode) {
		AccessResultPro.Builder bb = AccessResultPro.newBuilder();
		bb.setErrorCode(errorCode);
		bb.setIsNewUser(isNewUser);
		return bb.build();
	}

}
