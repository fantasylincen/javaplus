package com.cnbizmedia.account4games;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;

import com.cnbizmedia.JsonAction;
import com.cnbizmedia.account.UserCreator;
import com.cnbizmedia.account4games.RegistAction.RegistSuccess;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;

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
		return new RegistSuccess(dto.getId(), pwd);
	}


}