<%@page import="cn.vgame.a.log.ConsoleLog"%>
<%@page import="cn.javaplus.log.Log"%>
<%@page import="cn.vgame.share.ParameterUtil"%>
<%@page import="cn.vgame.a.log.GmLogTranslate"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDto"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDao.GmLogDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDao"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>

	<%!public String buildTimeScope(HttpSession session,
			HttpServletRequest request, int min, String dsc) {

		if (ParameterUtil.getParameter(request, session,
				"console-log-select-min") == null) {
			session.setAttribute("console-log-select-min", "10");
		}

		String mm = ParameterUtil.getParameter(request, session,
				"console-log-select-min");

		int minn = new Integer(mm);

		if (minn == min) {
			return "<span style=\"font-weight:bold;\">" + dsc + "</span>&nbsp;&nbsp;";
		} else {
			return "<a href=\"consoleLogs.jsp?console-log-select-min=" + min + "\">" + dsc
					+ "</a>&nbsp;&nbsp;";
		}

	}%>
	<div>
	
		<%
			String min = ParameterUtil.getParameter(request, session, "console-log-select-min");
			if(min != null) {
				session.setAttribute("console-log-select-min", min);
			}
		 %>
	
		<%=buildTimeScope(session, request, 10, "10分钟")%>
		<%=buildTimeScope(session, request, 30, "30分钟")%>
		<%=buildTimeScope(session, request, 60, "1小时")%>
		<%=buildTimeScope(session, request, 120, "2小时")%>
		<%=buildTimeScope(session, request, 300, "5小时")%>
		<%=buildTimeScope(session, request, 600, "10小时")%>
		<%=buildTimeScope(session, request, 1440, "1天")%>
		<br>
		<br>
	</div>

	<table border="1">

		<tbody>

			<%
				List<ConsoleLog> logs = ConsoleLog.get(new Integer(min));
				for (ConsoleLog log : logs) {
			%>
					<tr>
						<td><%=log.toString()%></td>
					</tr>
			<%
				}
			%>

		</tbody>

	</table>


	<br>
	<br>
	<a href="menu.jsp">返回</a>
</body>
</html>