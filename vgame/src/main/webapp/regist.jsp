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
		<div class="container container-custom">
			<div class="row wrap-login">
				<div class="login-user col-xs-12 col-sm-6 col-md-5 col-lg-5">
					<div class="login-part">
						<h3>帐号注册</h3>
						<div class="user-info">
							<div class="user-pass">

								<form id="fm1" action="/account/regist" method="post">

									<input id="email" name="email" tabindex="1"
										placeholder="输入邮箱" class="user-name" type="text" value="" />

									<input id="password1" name="password1" tabindex="2"
										placeholder="输入密码" class="pass-word" type="password" value=""
										autocomplete="off" /> <input id="password2" name="password2"
										tabindex="2" placeholder="再次输入密码" class="pass-word"
										type="password" value="" autocomplete="off" />



									<%
										Object o = session.getAttribute("error_code");
										int e = 0;
										if (o == null) {
											e = 0;
										} else {
											e = (Integer) o;
										}
									%>

									<%
										if (e == 1) {
									%>
									<div class="error-mess">
										<span class="error-icon"></span><span id="error-message">两次密码不匹配</span>
									</div>
									<%
										} else if (e == 2) {
									%>
									<div class="error-mess">
										<span class="error-icon"></span><span id="error-message">请输入正确的邮箱</span>
									</div>
									<%
										} else if (e == 3) {
									%>
									<div class="error-mess">
										<span class="error-icon"></span><span id="error-message">密码太短</span>
									</div>
									<%
										} else if (e == 4) {
									%>
									<div class="error-mess">
										<span class="error-icon"></span><span id="error-message">密码不能为空</span>
									</div>
									<%
										} else if (e == 6) {
									%>
									<div class="error-mess">
										<span class="error-icon"></span><span id="error-message">已经存在该用户</span>
									</div>
									<%
										}
									%>

									<input class="logging" accesskey="l" value="     注册"
										tabindex="5" type="submit" width="200px" height="565px" />

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.html"%>
</body>
</html>

