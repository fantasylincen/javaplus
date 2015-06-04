package com.cnbizmedia;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public abstract class JsonAction extends ActionSupport {

	private static final long serialVersionUID = -5854659914804921582L;

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

	@Override
	public final String execute() throws Exception {
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		session = request.getSession();
		
		Log.d("vchd SessionID:" + session.getId());

		HttpServletResponse response = ServletActionContext.getResponse();

		PrintWriter out = response.getWriter();
		Object r;
		try {
			r = exec();
			out.println(toJSONString(r));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.close(out);
		}

		return SUCCESS;
	}

	private String toJSONString(Object r) {
		if (r == null) {
			return "{}";
		}
		return JSON.toJSONString(r);
	}

	protected abstract Object exec();

}
