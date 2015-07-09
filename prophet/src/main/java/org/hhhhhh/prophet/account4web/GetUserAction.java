package org.hhhhhh.prophet.account4web;

import com.opensymphony.xwork2.ActionSupport;

public class GetUserAction extends ActionSupport {

	private static final long serialVersionUID = 5148850925791334210L;

	String mes;

	public String getMes() {
		return mes;
	}

	private TestBean value;

	public TestBean getValue() {
		return value;
	}

	public void setValue(TestBean value) {
		this.value = value;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String execute() {
		value = new TestBean();
		value.setName("cdfsdfasdf");
//		HttpServletRequest request = ServletActionContext.getRequest();

		return SUCCESS;
	}

}