package org.hhhhhh.prophet.account4app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.JsonAction;
import org.hhhhhh.prophet.account4web.UserCreator;
import org.hhhhhh.prophet.error.ErrorResult;
import org.hhhhhh.prophet.exception.RegistException;
import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.prophet.hibernate.dto.UserDto;

import cn.javaplus.util.Util;


public class RegistAction extends JsonAction {

	public static class RegistSuccess {
		private final String pwdMd5;
		private String pwd;

		public RegistSuccess(String userId, String pwd) {
			this.userId = userId;
			this.pwd = pwd;
			pwdMd5 = Util.Secure.md5(pwd);
		}

		public boolean getIsSuccess() {
			return true;
		}

		public String getUserId() {
			return userId;
		}

		public String getPwdMd5() {
			return pwdMd5;
		}
		
		public String getPwd() {
			return pwd;
		}

		private String userId;
	}

	private static final long serialVersionUID = -8965549726279594696L;
	private HttpSession session;

	String username;
	String password;
	
	private String userId;

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
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		String p = getPassword();
		if (p == null) {
			return new ErrorResult("please type password");
		} else if (p.length() < 6) {
			return new ErrorResult("password lenth < 6");
		} else if (getUsername() == null || getUsername().isEmpty()) {
			return new ErrorResult("please type username");
		} else if (!getUsername()
				.matches(
						"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
			return new ErrorResult("please type email address");
		} else if (isAreadyRegist()) {
			return new ErrorResult("user aready exist");
		}
		
		try {
			UserDto dto = new UserCreator().createNewUser(session, getUsername(), getPassword(), getUserId());
			return new RegistSuccess(dto.getId(), getPassword());
		} catch (RegistException e) {
			return new ErrorResult(e.getMessage());
		}
	}

	private boolean isAreadyRegist() {
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.find("email", getUsername());
		return c.hasNext();

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}