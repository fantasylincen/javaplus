package org.hhhhhh.guess.gmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		dto.setEndTime(parse(endTime));
		dto.setStartTime(parse(startTime));
		dto.setName(name.trim());
		dto.setDsc(dsc.trim());
		DbUtil.save(dto);

		return SUCCESS;
	}

	private String parse(String time) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
			SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = sf.parse(time);
			return sf2.format(parse);
		} catch (ParseException e) {
			return time;
		}
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
}