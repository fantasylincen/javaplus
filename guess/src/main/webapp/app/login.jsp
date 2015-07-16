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
<title>猜猜猜</title>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="../css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../_assets/css/jqm-demos.css">
<script src="../js/jquery.js"></script>
<script src="../_assets/js/index.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						if ($.cookie("rmbUser") == "true") {
							$("#ck_rmbUser").prop("checked", true);
							$("#username").val($.cookie("username"));
							$("#password").remove();
							$("#pass")
									.append(
											"<input id='password' type='password' class='txt2'/>");
							$("#password").val($.cookie("password"));
						}
						$("#loginButton").click(function() {
							if (check()) {
								login();
							}
						});
					});

	//记住用户名密码 
	function save() {
		if ($("#ck_rmbUser").prop("checked")) {
			var username = $("#username").val();
			var password = $("#password").val();
			$.cookie("rmbUser", "true", {
				expires : 7
			}); //存储一个带7天期限的cookie 
			$.cookie("username", username, {
				expires : 7
			});
			$.cookie("password", password, {
				expires : 7
			});
		} else {
			$.cookie("rmbUser", "false", {
				expire : -1
			});
			$.cookie("username", "", {
				expires : -1
			});
			$.cookie("password", "", {
				expires : -1
			});
		}
	};

	function check() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "" || username == "请输入用户名") {
			$("#tip").text("请输入用户名!");
			$("#username").focus();
			return false;
		}
		if (password == "" || password == "请输入密码") {
			$("#tip").text("请输入密码!");
			$("#password").focus();
			return false;
		}
		$("#tip").text("");
		return true;
	}

	function login() {
		$.ajax({
			type : "POST",
			url : "login!loginValidate.action",
			data : {
				userName : $("#username").val(),
				password : $("#password").val()
			},
			dataType : "json",
			beforeSend : function() {
				showOverlay();
			},
			success : function(data) {
				if (data.success) {
					addCookie("userName", $("#username").val(), 0);
					save();
					location.href = "/index.jsp";
				} else {
					$("#overlay").hide();
					$("#tip").text("用户名或密码错误，请重新登录！");
					return false;
				}

			}
		});
	}
</script>
</head>
<body>


	<div data-role="page" class="jqm-demos" data-quicklinks="true">

		<div data-role="header" class="jqm-header">
			<h2>
				<a title="Guess Home"><img src="../_assets/img/jquery-logo.png"
					alt="jQuery Mobile"> </a>
			</h2>
		</div>

		<div data-role="content" class="content">



			<form method="post" action="login">
				<label for="username">用户名</label> <input id="username"
					name="username" type="text" class="txt1"
					onclick="if(this.value=='input username'){this.value=''; }"
					onfocus="if(this.value=='input username'){this.value=''; }" /> <label
					for="password">密码</label> <input id="password" name="password"
					type="password" class="txt2"
					onclick="if(this.value=='input password'){this.value='';this.type='password';}"
					onfocus="if(this.value=='input password'){this.value='';this.type='password';}" />
				<button class="ui-btn" type="submit">登录</button>
			</form>

		</div>

	</div>
	<!-- /page -->

</body>
</html>

