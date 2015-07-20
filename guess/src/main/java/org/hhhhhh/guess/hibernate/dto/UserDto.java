package org.hhhhhh.guess.hibernate.dto;

import java.io.Serializable;

public class UserDto  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7650716400879559994L;
	String username;
	String password;
	String nick;
	int jiFen;
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
