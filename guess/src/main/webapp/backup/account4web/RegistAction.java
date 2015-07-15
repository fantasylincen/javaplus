package org.hhhhhh.guess.account4web;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDao;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDtoCursor;

import com.opensymphony.xwork2.ActionSupport;

public class RegistAction extends ActionSupport {

	private static final long serialVersionUID = -8965549726279594696L;
	String username;
	String password1;
	String password2;
	private HttpSession session;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		int errorCode = 0;

		String p2 = getPassword2();
		String p1 = getPassword1();
		int p1l = p1.length();
		int p2l = p2.length();
		if (p1 == null || p2 == null) {
			errorCode = 4;
		} else if (p1l < 6 || p2l < 6) {
			errorCode = 3;
		} else if (p1l != p2l || !p2.equals(p1)) {// 两次密码不匹配
			errorCode = 1;
		} else if (getUsername() == null || getUsername().isEmpty()) {
			errorCode = 2;
		} else if (!getUsername()
				.matches(
						"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
			errorCode = 2;
		} else if (isAreadyRegist()) {
			errorCode = 6;
		}

		session.setAttribute("error_code", errorCode);

		if (errorCode != 0) {
			return ERROR;
		} else {
			new UserCreator().createNewUser(session, getUsername(), getPassword1());
			return SUCCESS;
		}
	}

	private boolean isAreadyRegist() {
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.find("email", getUsername());
		return c.hasNext();

	}


	private static final int INIT_VALUE = 100000000;
	private static AtomicInteger userId = new AtomicInteger(INIT_VALUE);

}