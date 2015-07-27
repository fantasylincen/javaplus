package cn.javaplus.crazy.login;

import cn.javaplus.crazy.http.CallBack
import cn.javaplus.crazy.http.JsonResult

public class RegistCallBack implements CallBack {

	@Override
	public void completed(JsonResult result) {
		Log.d("regist sucess");
	}

	@Override
	public void onTimeOut() {
		Log.d("regist time out");
	}

	@Override
	public void failed(String error) {
		Log.d("regist error:" + error);
	}

	@Override
	public void onNetError(SocketException ex) {
		Log.d("net error");
	}

}
