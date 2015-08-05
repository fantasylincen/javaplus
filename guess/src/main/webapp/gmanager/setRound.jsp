<%@page import="org.hhhhhh.guess.util.ParameterUtil"%>
<%@page import="cn.javaplus.log.Log"%>
<%@page import="org.hhhhhh.guess.hibernate.dao.DbUtil"%>
<%@page import="org.hhhhhh.guess.hibernate.dto.RoundDto"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Log.d("enter setRound.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>编辑</title>
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
				<a title="Guess Home"><img height="800" src="../_assets/img/jquery-logo.png"
					alt="jQuery Mobile"> </a>
			</h2>
		</div>
		<div data-role="content" class="content">
<%
		String roundId = ParameterUtil.getParameter("roundId");
		Log.d("roundId", roundId);
		RoundDto dto = DbUtil.get(RoundDto.class, roundId);
		String name = dto.getName();
		String startTime = dto.getStartTime();
		String endTime = dto.getEndTime();
		String dsc = dto.getDsc();
 %>

			<form method="post" action="setRound">
				<label for="name">期刊名字</label> 
				<input id="name" name="name" type="text" class="txt1" value="<%=name%>" />
				 
				<label for="dsc">说明</label> 
				<input id="dsc" name="dsc" type="text" class="txt1" value="<%=dsc%>" /> 
				<label for="startTime">开始时间</label> 
				<input id="startTime" name="startTime" type="date" class="txt2" value="<%=startTime%>" /> 
				<label for="endTime">结束时间</label> 
				<input id="endTime" name="endTime" type="date" class="txt2" value="<%=endTime%>" /> 
				<button class="ui-btn" type="submit">修改</button>
			</form>

		</div>
	</div>





</body>
</html>

