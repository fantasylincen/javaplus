package com.cnbizmedia.recharge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cnbizmedia.Server;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;
import com.cnbizmedia.user.User;
import com.opensymphony.xwork2.ActionSupport;

public class RechargeAction extends ActionSupport {

	private static final long serialVersionUID = -7153959615616607087L;
	private HttpSession session;

	private int vb;

	public int getVb() {
		return vb;
	}

	public void setVb(int vb) {
		this.vb = vb;
	}

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		int vb = getVb();
		if (vb <= 0) {
			return ERROR;
		}
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return ERROR;
		}
		User user = Server.loadUserById(userId);
		if (user == null) {
			return ERROR;
		}
		UserDto dto = user.getDto();
		int result = dto.getVb() + vb;

		if (result < 0) {
			return ERROR;
		}
		dto.setVb(result);
		Daos.getUserDao().save(dto);

		return SUCCESS;
	}
}