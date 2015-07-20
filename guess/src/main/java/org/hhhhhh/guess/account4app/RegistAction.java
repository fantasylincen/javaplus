package org.hhhhhh.guess.account4app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.EncodingUtil;
import org.hhhhhh.guess.action.JsonAction;
import org.hhhhhh.guess.error.ErrorResult;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;

import cn.javaplus.log.Log;

public class RegistAction extends JsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3922340422655972833L;

	private String username;
	private String password;
	private String nick;
	
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


	private boolean isAreadyRegist() {
		UserDao dao = Daos.getUserDao();
		List<UserDto> c = dao.find("username", getUsername());
		return !c.isEmpty();
	}

	@Override
	protected Object exec() {

		Log.d("regist action");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		String pwd = getPassword();
		String un = getUsername();
		String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		if (un == null || !un.matches(regex)) {
			return new ErrorResult("please type email address");
		} else if (isAreadyRegist()) {
			return new ErrorResult("user aready exist");
		}

		UserCreator c = new UserCreator();
		UserDto dto = c.createNewUser(session, un, pwd);
		dto.setNick(nick);
		Log.d("regist successful");
		return new RegistSuccessfulResult(dto);
	}

	public String getNick() {
		
		return nick;
	}

	public void setNick(String nick) {
		nick = EncodingUtil.iso2Utf8(nick);
		this.nick = nick;
	}
}