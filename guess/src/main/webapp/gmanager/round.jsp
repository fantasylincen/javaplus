<%@page import="org.hhhhhh.guess.util.ParameterUtil"%>
<%@page import="cn.javaplus.log.Log"%>
<%@page import="org.hhhhhh.guess.hibernate.dto.RoundDto"%>
<%@page import="org.hhhhhh.guess.hibernate.dao.RoundDao"%>
<%@page import="org.hhhhhh.guess.hibernate.dao.Daos"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	String roundId = ParameterUtil.getParameter(request, session, "roundId");
	Log.d("enter round.jsp", roundId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>详情</title>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="../css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../_assets/css/jqm-demos.css">
<script src="../js/jquery.js"></script>
<!-- <script src="../_assets/js/index.js"></script> -->
<script src="../js/jquery.mobile-1.4.5.min.js"></script>



</head>



<body>



	<div data-role="page" class="jqm-demos" data-quicklinks="true">
		<div data-role="header" class="jqm-header">
			<h2>
				<a title="Guess Home"><img height="800"
					src="../_assets/img/jquery-logo.png" alt="jQuery Mobile"> </a>
			</h2>
		</div>



		<div data-role="content" class="content">


			<ul data-role="listview" data-inset="true"
				class="ui-listview ui-listview-inset ui-corner-all ui-shadow">
				<li class="ui-li-has-thumb"><a href="questions.jsp"
					class="ui-btn ui-icon-carat-r">
						<h2>问题列表</h2>
						<p>打开查看本期所有问题</p> </a>
				</li>
				
				<li class="ui-li-has-thumb"><a href="setRound.jsp"
					class="ui-btn ui-icon-carat-r">
						<h2>编辑</h2>
						<p>编辑本期基本信息</p> </a>
				</li>

			</ul>
		</div>
	</div>



</body>
</html>

