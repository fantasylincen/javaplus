package org.hhhhhh.guess.account4web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.action.JsonAction;
import org.hhhhhh.guess.error.ErrorResult;
import org.hhhhhh.guess.user.User;

public class LoginAction extends JsonAction {

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
	protected Object exec() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		User user = Server.getUser(getUsername());

		if (user == null) {
			return new ErrorResult("user not exist");
		} else {
			String pwd = user.getPassword();
			if (!pwd.equals(getPassword())) {
				return new ErrorResult("password error");
			}
		}

		session.setAttribute("username", user.getUsername());
		
		return new LoginSuccessResult(user);
	}

	class LoginSuccessResult {


		private final User user;

		public LoginSuccessResult(User user) {
			this.user = user;
		}

		public String getUsername() {
			return user.getUsername();
		}

		public String getNick() {
			return user.getNick();
		}

		public int getJiFen() {
			return user.getJiFen();
		}

		public String getId() {
			return user.getUsername();
		}
		
		


	}

}
