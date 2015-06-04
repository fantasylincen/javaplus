<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<style>
<!--
.error-mess .error-icon {
	display: inline-block;
	background: url("images/login-logic-icons.png") no-repeat 0 0;
	width: 16px;
	height: 16px;
	vertical-align: middle;
	margin: -4px 5px 0 5px;
}
-->
</style>
<link rel="icon" href="images/favicon.ico">


<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<%
		String code = (String)request.getParameter("code");
		if(code != null) {
			session.setAttribute("code", code);
		}
	 %>

	<form class="form-signin" action="/account/resetPwd" method="post">
		<input type="hidden" name="code" value="<%=session.getAttribute("code") %>"
			class="form-control" placeholder="username" required autofocus>

		<br> 新密码<input type="password" name="password1"
			class="form-control" placeholder="Password" required> <br>
		再次输入<input type="password" name="password2" class="form-control"
			placeholder="Password" required>

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
			}
		%>

		<button class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
		<br>

	</form>

</body>
</html>

