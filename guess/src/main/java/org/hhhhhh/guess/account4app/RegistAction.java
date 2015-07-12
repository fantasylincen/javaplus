package org.hhhhhh.guess.account4app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.JsonAction;
import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.account4web.UserCreator;
import org.hhhhhh.guess.error.ErrorResult;
import org.hhhhhh.guess.exception.RegistException;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDao;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.guess.hibernate.dto.UserDto;

import cn.javaplus.util.Util;

public class RegistAction extends JsonAction {

	public static class RegistSuccess {
		private final String password;

		public RegistSuccess(String userId, String password) {
			this.userId = userId;
			this.password = password;
		}

		public boolean getIsSuccess() {
			return true;
		}

		public String getUserId() {
			return userId;
		}

		public String getPassword() {
			return password;
		}

		private String userId;
	}

	private static final long serialVersionUID = -8965549726279594696L;
	private HttpSession session;

	String username;
	String password;
	private String flag;
	private long time;

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

	public Object exec() {

		checkFlag();

		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		String pwd = getPassword();
		String p = pwd;
		String un = getUsername();
		if (p == null) {
			return new ErrorResult("please type password");
		} else if (p.length() < 6) {
			return new ErrorResult("password lenth < 6");
		} else if (un == null || un.isEmpty()) {
			return new ErrorResult("please type username");
		} else {
			String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
			if (!un.matches(regex)) {
				return new ErrorResult("please type email address");
			} else if (isAreadyRegist()) {
				return new ErrorResult("user aready exist");
			}
		}

		try {
			UserCreator c = new UserCreator();
			UserDto dto = c.createNewUser(session, un, pwd);
			return new RegistSuccess(dto.getId(), pwd);
		} catch (RegistException e) {
			return new ErrorResult(e.getMessage());
		}
	}

	private void checkFlag() {
		String md5 = Util.Secure.md5(username + password + getTime()
				+ Server.KEY);
		if (!md5.equals(flag))
			throw new RuntimeException("sign error");
	}

	private boolean isAreadyRegist() {
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.find("username", getUsername());
		return c.hasNext();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}