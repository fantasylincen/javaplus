package cn.vgame.gate.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;
import cn.vgame.gate.Server;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -7216556879198306440L;

	String username;
	String password;

	private HttpSession session;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		int errorCode = 0;

		User user = Server.loadUserById(getUsername());
		
		if (user == null) {
			errorCode = 1;
		} else {
			String p1 = getPassword();
			String p2 = user.getPassword();
			if (!p2.equals(p1)) {
				errorCode = 1;
			}
		}
		session.setAttribute("login_error_code", errorCode);

		if (errorCode != 0) {
			return ERROR;
		} else {
			session.setAttribute("userId", user.getId());
			return SUCCESS;
		}
	}
}
