package org.hhhhhh.fqzs.login;

import java.util.List;

public interface RoleListCallBack {

	void onGetRoleList(List<RoleItem> roles);

	void faild(String message);


}
