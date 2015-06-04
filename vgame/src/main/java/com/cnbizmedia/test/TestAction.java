package com.cnbizmedia.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1083572882373448794L;
	private String username;

	public String getUsername() {
		username = "cccd";
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
