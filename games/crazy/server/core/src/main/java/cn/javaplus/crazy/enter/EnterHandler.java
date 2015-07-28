package cn.javaplus.crazy.enter;

import cn.javaplus.comunication.annotations.Communication;

@Communication
interface EnterHandler {

	EnterResultP enter(String uname, String token, String roleData);

	void pk();
}
