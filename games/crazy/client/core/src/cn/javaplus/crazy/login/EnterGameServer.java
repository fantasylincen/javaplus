package cn.javaplus.crazy.login;

import cn.javaplus.crazy.App.AppContext;
import cn.javaplus.crazy.Protocols;
import cn.javaplus.crazy.Protocols.EnterHandler;
import cn.javaplus.crazy.Protocols.EnterHandlerEnterEvent;
import cn.javaplus.crazy.Protocols.EnterHandlerListeners.AbstractEnterCallBack;
import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.events.Listener;
import cn.javaplus.crazy.log.Log;

public class EnterGameServer implements Listener<LoginSuccessEvent> {

	@Override
	public void onEvent(LoginSuccessEvent e) {
		if (e.isNewUser()) {
			createRole(e);
		} else {
			enterGameServer(e, e.getRoleData());
		}
	}

	private void createRole(LoginSuccessEvent e) {
		e.getStage().getMessageBox().showMessage("please wait...");
		String uname = e.getUname();
		String nick = e.getUname();
		String token = e.getToken();
		CreateRoleRequest r = new CreateRoleRequest(uname, token, nick, 1);
		Log.d("create role");
		AppContext.getHttpClient().excute(r, new CreateRoleCallBack(this, e));
	}

	private class AccessCallBack extends AbstractEnterCallBack {

		@Override
		public void completed(EnterHandlerEnterEvent e) {
			Log.d("enter to game server");
			Events.dispatch(new EnterGameEvent(e));
		}

		@Override
		public void faild(String error) {
			AppContext.getMessageBox().showMessage("request error:" + error);
		}

		@Override
		public void timeOut() {
			AppContext.getMessageBox().showMessage("request timeout");
		}
	}

	void enterGameServer(LoginSuccessEvent event, String roleData) {
		AppContext.ensureInitGameClient();

		Protocols getClient = AppContext.getClient();
		EnterHandler e = getClient.getEnterHandler();
		String token = event.getToken();
		String uname = event.getUname();
		Log.d ("got token enter game server:" + token);
		e.enter(uname, token, roleData, new AccessCallBack());
	}
}
