<%@page import="cn.javaplus.log.Log"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>    
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
<title>出错了</title>
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
				<a title="Guess Home"><img src="img/logo.png"
					alt="jQuery Mobile"> </a>
			</h2>
		</div>
		<h1><s:property value="exception.message"/></h1> 
	</div>




</body>
</html>

