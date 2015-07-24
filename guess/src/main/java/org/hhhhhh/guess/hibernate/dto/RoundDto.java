package org.hhhhhh.guess.hibernate.dto;


public class RoundDto  {
	String id;
	String name;
	String startTime;
	String endTime;
	private String dsc;
	/**
	 * 已经预测的人数
	 */
	private int yiYuCe;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public int getYiYuCe() {
		return yiYuCe;
	}
	public void setYiYuCe(int yiYuCe) {
		this.yiYuCe = yiYuCe;
	}
}
