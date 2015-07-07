package cn.vgame.a.system;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.turntable.Turntable;

public class OnlineCounter extends HttpServlet implements HttpSessionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 171682421883834821L;
	private static int onlineSize;

	public OnlineCounter() {
		Log.d("OnlineCounter initialized.");
	}

	public void sessionCreated(HttpSessionEvent se) {
		onlineSize++;
		Log.d("session created onlineSize:" + onlineSize);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		onlineSize--;
		Log.d("session destroied onlineSize:" + onlineSize);
		logoutUser(se);
	}

	private void logoutUser(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		String roleId = (String) session.getAttribute("roleId");		
		if(roleId != null) {
			Role role = Server.getRole(roleId);
			role.logout();
		}
	}

	public static int getOnlineSize() {
//		return onlineSize;
		return Turntable.getInstance().getRoleCount();
	}
}