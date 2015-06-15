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
			HttpServletRequest request, int count, String dsc) {

		if (ParameterUtil.getParameter(request, session, "count") == null) {
			session.setAttribute("count", "40");
		}

		String ct = ParameterUtil.getParameter(request, session, "count");

		int cnt = new Integer(ct);

		if (cnt == count) {
			return "<span style=\"font-weight:bold;\">" + dsc
					+ "</span>&nbsp;&nbsp;";
		} else {
			return "<a href=\"consoleLogs.jsp?count=" + count + "\">" + dsc
					+ "</a>&nbsp;&nbsp;";
		}

	}%>
	<div>

		<%
			String find = ParameterUtil.getParameter(request, session,
					"console-log-find");
			if (find == null) {
				find = "";
			}
		%>
		
		
		<%
			String fileName = ParameterUtil.getParameter(request, session,
					"console-log-file-name");
			if (fileName == null) {
				fileName = "";
			}
		%>

		<%=buildTimeScope(session, request, 40, "40条")%>
		<%=buildTimeScope(session, request, 100, "100条")%>
		<%=buildTimeScope(session, request, 200, "200条")%>
		<%=buildTimeScope(session, request, 500, "500条")%>
		<%=buildTimeScope(session, request, 1000, "1000条")%>
		<%=buildTimeScope(session, request, 2000, "2000条")%>
		<%=buildTimeScope(session, request, 5000, "5000条")%>

		<br> <br>
		
		<form name="jump" action="consoleLogs.jsp" method="post">
			<input type="text" name="console-log-find" id="console-log-find" value="<%=find%>" /> <a href="javascript:jump.submit();">查找</a>
		</form>

		<a href="consoleLogs.jsp">刷新</a> &nbsp;&nbsp;&nbsp; <a href="consoleLogList.jsp">返回</a><br>
		<br>

		<%
			String cnt = ParameterUtil.getParameter(request, session, "count");
			if (cnt != null) {
				session.setAttribute("count", cnt);
			}
		%>
	</div>



	<%
		List<ConsoleLog> logs = ConsoleLog.get(fileName, new Integer(cnt), find);

		if (logs.isEmpty()) {
			out.println("没有记录");
		} else {

			for (ConsoleLog log : logs) {
	%>
	<%=log.toString()%>
	<br>
	<%
		}
		}
	%>




	<br>
	<br>
</body>
</html>