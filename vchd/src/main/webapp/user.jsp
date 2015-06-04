<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>密码找回</title>

<%@include file="head.html"%>

</head>

<body>

	<%@include file="head.jsp"%>

	<div class="content">
		<div class="container">
			<div class="inside">
				<div class="wrapper">
					<!-- aside -->
					<aside>

														<p>
	<strong><span style="font-size:16px;font-family:'Microsoft YaHei';">我的信息</strong>
</p>
<p>
	<br />
</p>
<br>
						<%
							String userId = (String) session.getAttribute("userId");
							User user = Server.loadUserById(userId);
						%>
							<li><strong>帐号:</strong><%=user.getNick()%></li>
							<li><strong>V币:</strong><%=user.getVb()%>
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="recharge.jsp">充值</a></li>

					</aside>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

