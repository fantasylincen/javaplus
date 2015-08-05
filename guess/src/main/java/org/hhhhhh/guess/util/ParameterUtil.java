package org.hhhhhh.guess.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class ParameterUtil {

	/**
	 * 默认从request中取参数, 如果request中没有, 从session中取, 如果request中有, 同步更新到session中
	 * 
	 * @param request
	 * @param session
	 * @param parameterName
	 * @return
	 */
	public static String getParameter(HttpServletRequest request,
			HttpSession session, String parameterName) {
		String p = request.getParameter(parameterName);
		if (p != null) {
			session.setAttribute(parameterName, p);
			return p;
		}
		String attribute = (String) session.getAttribute(parameterName);
		return attribute;
	}

	public static String getParameter(String parameterName) {

		HttpServletResponse response;
		HttpServletRequest request;
		HttpSession session;
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		return getParameter(request, session, parameterName);
	}

}
