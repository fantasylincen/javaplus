package cn.javaplus.crazy.login;

import java.net.SocketException;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.http.CallBack;
import cn.javaplus.crazy.log.Log;

public abstract class AbstractCallBack implements CallBack {

	@Override
	public void onTimeOut() {
		AppContext.getMessageBox().showMessage("time out");
		Log.e("time out");
	}

	@Override
	public void failed(String error) {
		AppContext.getMessageBox().showMessage("error:" + error);
		Log.e("error:" + error);
	}

	@Override
	public void onNetError(SocketException ex) {
		AppContext.getMessageBox().showMessage("net error");
		Log.e("net error");
	}

}