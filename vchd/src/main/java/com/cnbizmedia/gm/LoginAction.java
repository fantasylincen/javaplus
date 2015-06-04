package com.cnbizmedia.gm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnbizmedia.config.GameProperties;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	public class GmUser {

		private final JSONObject obj;

		public GmUser(JSONObject obj) {
			this.obj = obj;
		}

		public String getPwdMD5() {
			String pwd = obj.getString("password");
			return Util.Secure.md5(pwd);
		}

		public String getId() {
			String string = obj.getString("username");
			return string;
		}

	}

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
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		int errorCode = 0;

		GmUser user = loadUser();
		
		if (user == null) {
			errorCode = 1;
		} else {
			String md5b = Util.Secure.md5(getPassword());
			String md5a = user.getPwdMD5();
			if (!md5a.equals(md5b)) {
				errorCode = 1;
			}
		}
		session.setAttribute("login_error_code", errorCode);

		if (errorCode != 0) {
			return ERROR;
		} else {
			session.setAttribute("gmUserId", user.getId());
			return SUCCESS;
		}
	}

	private GmUser loadUser() {
		String us = GameProperties.getString("users");
		JSONArray arr = JSON.parseArray(us);
		
		String un = getUsername();
		
		for (Object o : arr) {
			JSONObject obj = (JSONObject) o;
			String username = obj.getString("username");
			if(username.equals(un)) {
				return new GmUser(obj);
			}
		}
		return null;
	}
}
