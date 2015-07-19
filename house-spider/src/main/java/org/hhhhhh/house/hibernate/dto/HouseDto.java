package org.hhhhhh.house.hibernate.dto;

import java.io.Serializable;

public class HouseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8756575037194057720L;

	public HouseDto() {
		zoneName = "";
		name = "";
		floor = "";
		plate = "";
		price = "";
		tel = "";
		huxing = "";
		owner = "";
		commit_date = "";
		update_date = "";
		dsc = "";
		href = "";
	}

	private String href;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHuxing() {
		return huxing;
	}

	public void setHuxing(String huxing) {
		this.huxing = huxing;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCommit_date() {
		return commit_date;
	}

	public void setCommit_date(String commit_date) {
		this.commit_date = commit_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getDsc() {
		return dsc;
	}

	public void setDsc(String dsc) {
		this.dsc = dsc;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	private String zoneName;
	private String name;
	private String floor;
	private String plate;
	private String price;
	private String tel;
	private String huxing;
	private String owner;
	private String commit_date;
	private String update_date;
	private String dsc;
}
