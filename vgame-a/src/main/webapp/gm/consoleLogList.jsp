<%@page import="java.io.File"%>
<%@page import="cn.vgame.a.log.LogFile"%>
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

	<div>

		<a href="menu.jsp">返回</a><br>
		<br>
	</div>



	<%
		List<File> logs = ConsoleLog.getFiles();

		if (logs.isEmpty()) {
			out.println("没有日志记录");
		} else {

			for (File log : logs) {
	%>
	<a href="consoleLogs.jsp?console-log-file-name=<%=log.getName()%>"><%=log.getName()%></a>
	<br>
	<%
		}
		}
	%>




	<br>
	<br>
</body>
</html>