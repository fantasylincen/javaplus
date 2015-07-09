package org.hhhhhh.prophet.hibernate.dto;

public class UserDto {
	String id;
	String email;
	String pwd;
	String nick;
	int jiFen;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getJiFen() {
		return jiFen;
	}
	public void setJiFen(int jiFen) {
		this.jiFen = jiFen;
	}
}
