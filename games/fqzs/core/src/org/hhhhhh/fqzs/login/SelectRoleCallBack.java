package org.hhhhhh.fqzs.login;

import org.hhhhhh.fqzs.result.RoleData;

public interface SelectRoleCallBack {

	void faild(String message);

	void onSuccess(RoleData role);

}
