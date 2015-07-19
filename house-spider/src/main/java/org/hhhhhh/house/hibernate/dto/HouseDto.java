package org.hhhhhh.house.hibernate.dto;

import java.io.Serializable;
import java.sql.Date;

public class HouseDto  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7650716400879559994L;
	String id;
	String name;
	String href;
	String plate;
	String price;
	String tel;
	Date date;
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
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
