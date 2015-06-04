<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>VGame-Login</title>

<%@include file="head.html"%>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">

<script language="javascript">
	var secs = 3; //倒计时的秒数 
	var URL;
	function Load(url) {
		URL = url;
		for ( var i = secs; i >= 0; i--) {
			window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);
		}
	}
	function doUpdate(num) {
		document.getElementById('ShowDiv').innerHTML = '将在' + num + '秒后自动跳转到主页';
		if (num == 0) {
			window.location = URL;
		}
	}
</script>
</head>

<body onload="Load('index.jsp')">

	<%@include file="head.jsp"%>


	<div class="main">
		<div class="container container-custom">
			<div class="row wrap-login">

				<div id="ShowDiv"></div>
			</div>
		</div>
	</div>

</body>
</html>








