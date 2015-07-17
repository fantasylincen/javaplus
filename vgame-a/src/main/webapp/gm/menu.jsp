<%@page import="cn.vgame.share.ParameterUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>

	<%
		ParameterUtil.getParameter(request, session, "gmUserId");
	 %>

	<p>
		<span style="font-size:18px;"><strong>系统管理</strong>
		</span>
	</p>
	<p>
		<span style="font-size:18px;"><strong>
				<hr /> <br /> </strong>
		</span>
	</p>
	<p>
		<span style="font-size:18px;"><strong> </strong>
		</span>
	</p>

	<a href="queryUsers.jsp">用户管理</a>
	<br>

	<br>
	<a href="systemInfo.jsp">系统信息</a>
	<br>
	<br>
	<a href="turntableControl.jsp">系统控制</a>
	<br>
	<br>
	<a href="logs.jsp?page=100000000">操作日志</a>
	<br>
	<br>
	<a href="rechargeLogs.jsp?page=100000000">充值记录</a>
	<br>
	<br>
	<a href="coinLogs.jsp?page=100000000">转账记录</a>
	<br>
	<br>
	<a href="consoleLogList.jsp">系统日志</a>
	<br>
	<!-- <br>
	<a href="../notice/notice.jsp">公告页面</a>
	<br> -->
	<br>
	<a href="../yinshang/yinshang.jsp">银商管理</a>
	<br>
	<br>
</body>
</html>