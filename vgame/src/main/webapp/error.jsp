<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<title>VGame-Login</title>
<%@include file="head.html"%>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">

</head>

<body>

	<%@include file="head.jsp"%>

	<div class="main">


				<%
					String e = request.getParameter("error");
					String type = request.getParameter("type");
				%>
				<strong><span style="font-size:20px;"> Error: <%=e%>
				</span> </strong>
				<br><br><br> <strong><span style="font-size:20px;">Type:
						<%=type%> </span> </strong>
	</div>
	<%@include file="footer.html"%>
</body>
</html>

