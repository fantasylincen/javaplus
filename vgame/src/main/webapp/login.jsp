<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
						<h3>帐号登录</h3>
						<div class="user-info">
							<div class="user-pass">

								<form id="fm1" action="/account/login" method="post">

									<input id="username" name="username" tabindex="1"
										placeholder="输入用户名/邮箱" class="user-name" type="text" value="" />

									<input id="password" name="password" tabindex="2"
										placeholder="输入密码" class="pass-word" type="password" value=""
										autocomplete="off" />

									<div class="error-mess" style="display:none;">
										<span class="error-icon"></span><span id="error-message"></span>
									</div>


									<div class="row forget-password">
										<span
											class="col-xs-6 col-sm-6 col-md-6 col-lg-6 forget tracking-ad"
											data-mod="popu_26"> <a href="/account/fpassword.jsp"
											tabindex="4">忘记密码</a> </span>
									</div>
									<%
										Object o = session.getAttribute("login_error_code");
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
										<span class="error-icon"></span><span id="error-message">帐号或密码错误</span>
									</div>
									<%
										}
									%>

									<input class="logging" accesskey="l" value="    登 录"
										tabindex="5" type="submit" />

								</form>
							</div>
						</div>
						<div class="line"></div>
						<div class="third-part tracking-ad" data-mod="popu_27">
							<!-- <span>第三方帐号登录</span> <span id="qqLoginBtn"></span>
							<script type="text/javascript">
								QC.Login({
									btnId : "qqLoginBtn" //插入按钮的节点id
								});
							</script>
 -->
							<div class="register-now">
								<span>还没有VGame帐号？</span> <span class="register tracking-ad"
									data-mod="popu_28"> <a href="/regist.jsp">马上注册</a> </span>
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

