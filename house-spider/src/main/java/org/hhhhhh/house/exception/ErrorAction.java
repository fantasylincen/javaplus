package org.hhhhhh.house.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.log.Log;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ErrorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2024840756436138450L;

	@Override
	public String execute() {
		Log.d("error action");
		return SUCCESS;
	}
}
