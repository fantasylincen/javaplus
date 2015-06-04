package cn.vgame.share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		return (String) session.getAttribute(parameterName);
	}
}
