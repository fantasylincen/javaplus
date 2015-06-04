<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cnbizmedia.*"%>
<%@ page import="com.cnbizmedia.account.*"%>
<%@ page import="com.cnbizmedia.user.*"%>

<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>VGame-User</title>

<%@include file="head.html"%>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">

</head>

<body>

	<%@include file="head.jsp"%>


	<div class="main-box">
		<div class="container">
			<div class="inside">
				<div class="wrapper">
					<!-- aside -->
					<aside>

						<h2>
							我的 <span>信息</span>
						</h2>

						<%
							String userId = (String) session.getAttribute("userId");
							User user = Server.loadUserById(userId);
						%>
						<ul class="contacts">
							<li><strong>帐号:</strong><%=user.getNick()%></li>
							<li><strong>V币:</strong><%=user.getVb()%>
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="recharge.jsp">充值</a></li>
						</ul>

					</aside>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.html"%>
</body>
</html>

