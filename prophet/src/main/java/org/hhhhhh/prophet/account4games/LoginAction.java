package org.hhhhhh.prophet.account4games;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.JsonAction;
import org.hhhhhh.prophet.Server;
import org.hhhhhh.prophet.error.ErrorResult;
import org.hhhhhh.prophet.user.User;

import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends JsonAction {

	private static final long serialVersionUID = -7216556879198306440L;

	String username;
	String password;
	private String tokenKey;

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
	protected Object exec() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		User user = Server.loadUserByEmail(getUsername());

		if (user == null) {
			return new ErrorResult("user not exist");
		} else {
			String md5b = Util.Secure.md5(getPassword());
			String md5a = user.getPwdMD5();
			if (!md5a.equals(md5b)) {
				return new ErrorResult("password error");
			}
		}

		session.setAttribute("userId", user.getId());
		
		return new LoginSuccessResult(user.getId());
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	class LoginSuccessResult {

		private final String id;

		public LoginSuccessResult(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public String getToken() {
			String tokenKey = getTokenKey();
			return Util.Token.generate(tokenKey, getId());
		}
	}

}
