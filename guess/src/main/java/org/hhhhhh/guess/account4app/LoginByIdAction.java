package org.hhhhhh.guess.account4app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.JsonAction;
import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.error.ErrorResult;
import org.hhhhhh.guess.user.User;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;


/**
 * 用用户ID登陆
 */
public class LoginByIdAction extends JsonAction {

	private static final long serialVersionUID = -7216556879198306440L;

	private String userId;
	private String pwd;
	private String tokenKey;

	private HttpSession session;

	@Override
	protected Object exec() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		User user = Server.loadUserById(getUserId());

		if (user == null) {
			return new ErrorResult("user not exist");
		} else {
			String md5a = user.getPassword();
			if (!md5a.equals(getPwd())) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
			String id = getId();
			String generate = Util.Token.generate(tokenKey, id);
			Log.d("token", tokenKey, generate, id);
			return generate;
		}
	}

}
