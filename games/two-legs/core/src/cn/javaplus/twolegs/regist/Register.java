package cn.javaplus.twolegs.regist;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.log.Log;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.game.OneKeyRegistCallBack;
import cn.javaplus.twolegs.game.TwoLegsRequestAdaptor;

public class Register {
	public void regist(CallBackJsonAdaptor back) {
		Log.d("registing...");
		TwoLegsHttpClient c = App.getHttp();
		c.excute(new TwoLegsRequestAdaptor() {

			@Override
			public String getDomain() {
				return "oneKeyRegist";
			}

		}, new OneKeyRegistCallBack(back));
	}

	public boolean hasRegist() {
		IPreferences p = App.getPreferences();
		String roleId = p.getString("roleId");
		return roleId != null && !roleId.trim().isEmpty();
	}
}
