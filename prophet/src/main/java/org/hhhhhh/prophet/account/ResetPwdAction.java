package org.hhhhhh.prophet.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.SystemKeyValueDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.SystemKeyValueDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.UserDto;
import org.hhhhhh.prophet.user.User;

import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPwdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666447929660774525L;
	String password1;
	String password2;
	private String code;
	private HttpSession session;

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
		}

		session.setAttribute("error_code", errorCode);

		if (errorCode != 0) {
			
			return ERROR;
			
		} else {

			SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
			SystemKeyValueDto dto = dao.get("RESET_PASSWORD:" + getCode());
			String value = dto.getValue();
			String[] aa = value.split(":");
			String email = aa[0];
			long time = new Long(aa[1]);
			
			if(isTimeUp(time)) {
				return "timeUp";
			}
			
			User user = Server.loadUserByEmail(email);
			UserDto dd = user.getDto();
			dd.setPwdMD5(Util.Secure.md5(p1));
			
			Daos.getUserDao().save(dd);
			
			return SUCCESS;
		}
	}


	private boolean isTimeUp(long time) {
		return System.currentTimeMillis() - time > Time.MILES_ONE_DAY;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}