package cn.javaplus.twolegs.game;

import java.net.SocketException;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.log.Log;

import cn.javaplus.twolegs.App;

public class OneKeyRegistCallBack extends CallBackJsonAdaptor {

	private CallBackJsonAdaptor back;

	public OneKeyRegistCallBack(CallBackJsonAdaptor back) {
		this.back = back;
	}

	@Override
	public void completed(JsonResult result) {

		Log.e("oneKeyRegist completed" );
		IPreferences p = App.getPreferences();

		String roleId = result.getString("roleId");
		String password = result.getString("password");
		String nick = result.getString("nick");

		p.put("roleId", roleId);
		p.put("password", password);
		p.put("nick", nick);

		Log.d("regist success:", roleId, password, nick);
		
		if (back != null)
			back.completed(result);
	}

	public void onTimeOut() {
		Log.e("oneKeyRegist onTimeOut" );
		if (back != null)
			back.onTimeOut();
	}

	public void failed(String error) {
		Log.e("oneKeyRegist failed " + error);
		if (back != null)
			back.failed(error);
	}

	public void onNetError(SocketException ex) {
		Log.e("oneKeyRegist onNetError " + ex.getMessage());
		if (back != null)
			back.onNetError(ex);
	}

}
