package org.hhhhhh.guess.gmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.RoundDto;

import com.opensymphony.xwork2.ActionSupport;

public class SetRoundAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6587363842547199208L;
	String name;
	String startTime;
	String endTime;
	private String dsc;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

	@Override
	public String execute() throws Exception {


		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		
		String roundId = (String) session.getAttribute("roundId");
		RoundDto dto = DbUtil.get(RoundDto.class, roundId);
		
		dto.setEndTime(endTime);
		dto.setStartTime(startTime);
		dto.setName(name.trim());
		dto.setDsc(dsc.trim());
		DbUtil.save(dto);

		return SUCCESS;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

}