package cn.javaplus.crazy.http;

import java.net.SocketException;

public interface CallBack {

	void completed(JsonResult result);

	void onTimeOut();

	void failed(String error);

	void onNetError(SocketException ex);

}
