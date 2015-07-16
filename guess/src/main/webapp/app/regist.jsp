<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>注册</title>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="../css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../_assets/css/jqm-demos.css">
<script src="../js/jquery.js"></script>
<script src="../_assets/js/index.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>

</head>
<script type="text/javascript">
	function check() {
		var p1 = document.getElementById("password").value;
		var p2 = document.getElementById("password2").value;
		var eq = p1 == p2;
		if (!eq) {
			alert("两次输入的密码不一样,请重新输入");
			return false;
		}
		if (p1.length < 6) {
			alert("密码太短");
			return false;
		}
		return true;
	}
</script>

<style type="text/css">
p {
	font-size: 1.5em;
	font-weight: bold;
}

header div {
	font-size: 1.5em;
}

#regist {
	width: 150px;
	height: 50px;
	margin: 5px;
}
</style>

<body>

	<div data-role="page" class="jqm-demos" data-quicklinks="true">
		<div data-role="header" class="jqm-header">
			<h2>
				<a title="Guess Home"><img height="800" src="img/logo.png"
					alt="jQuery Mobile"> </a>
			</h2>
		</div>
		<div data-role="content" class="content">
			<form method="post" action="regist" onsubmit="return check()">
				<label for="username">请输入用户名</label> <input type="text"
					name="username" id="username" /> <label for="password">请输入密码</label>
				<input type="password" name="password" id="password" /> <label
					for="password">再次输入密码</label> <input id="password2"
					name="password2" type="password" class="txt2" />
				<!-- <label
						for="nick">昵 称</label> <input type="text" name="nick" id="nick" /> -->

				<button class="ui-btn" type="submit">立即注册</button>
			</form>
		</div>
		<div data-role="footer" data-position="fixed" data-id="footernav">
			<div data-role="navbar">
				<ul>
					<li><a href="regist.jsp"
						class="ui-btn-active ui-state-persist">注册</a></li>
					<li><a href="login.jsp">登录</a></li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>

