package org.hhhhhh.guess.gmanager;

import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.RoundDto;

import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class CreateRoundAction extends ActionSupport {

	private static final long serialVersionUID = 2384545888471766345L;
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

	@Override
	public String execute() throws Exception {

		RoundDto dto = new RoundDto();
		dto.setId(Util.ID.createId());
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