<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>VGame-Login</title>
</head>
	<body>
	
	<input type="text" value="1<%=request.getAttribute("username")%>">
		<s:property value="username"/>
		<div>${username}  <s:property value="username"/></div>
		<!-- http://localhost/test/test?username=111 -->
	</body>
</html>

