package org.hhhhhh.guess.account4web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.account4app.UserCreator;
import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;

import cn.javaplus.log.Log;

import com.opensymphony.xwork2.ActionSupport;

public class RegistAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922340422655972833L;

	private String username;
	private String password;

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
	public String execute() {

		Log.d("regist action");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String pwd = getPassword();
		String un = getUsername();
		String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		if (un == null || !un.matches(regex)) {
			throw new GuessException("please type email address");
		} else if (isAreadyRegist()) {
			throw new GuessException("user aready exist");
		}

		UserCreator c = new UserCreator();
		c.createNewUser(session, un, pwd);
		Log.d("regist successful");
		return SUCCESS;
	}

	private boolean isAreadyRegist() {
		UserDao dao = Daos.getUserDao();
		List<UserDto> c = dao.find("username", getUsername());
		return !c.isEmpty();
	}
}