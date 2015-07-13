package org.hhhhhh.guess.account4app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.JsonAction;
import org.hhhhhh.guess.account4app.RegistAction.RegistSuccess;
import org.hhhhhh.guess.hibernate.dto.UserDto;

import cn.javaplus.util.Util;


public class OneKeyRegistAction extends JsonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -192810160703493446L;

	private HttpSession session;

	public Object exec() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		int pwds = Util.Random.get(100000, 999999);
		String pwd = pwds + "";
		UserDto dto = new UserCreator().createNewUser(session, "", pwd);
		return new RegistSuccess(dto);
	}


}