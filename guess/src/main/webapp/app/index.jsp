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
<title>预测事件</title>
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
<body>

	<div data-role="page" class="jqm-demos" data-quicklinks="true">
		<div data-role="header" class="jqm-header" >
			<img src="img/question-0001.png" style="max-width:100%;">
		</div>
		<div data-role="content" class="content">
		<form id="chooseForm" name="chooseForm">
	
			    <fieldset data-role="controlgroup" data-mini="true">
			        <legend>你们猜,2015年足球南美洲杯冠军是哪个国家?</legend>
			        <input type="radio" name="radio-choice-v-6" id="radio-choice-v-6a" value="on" checked="checked">
			        <label for="radio-choice-v-6a">阿根廷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10002466人选择</label>
			        <input type="radio" name="radio-choice-v-6" id="radio-choice-v-6b" value="off">
			        <label for="radio-choice-v-6b">巴西&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;908154人选择</label>
			        <input type="radio" name="radio-choice-v-6" id="radio-choice-v-6c" value="other">
			        <label for="radio-choice-v-6c">中国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0人选择</label>
			    </fieldset>
			
				<a href="#" class="ui-btn" onclick="document.getElementById('chooseForm').submit()">下一题</a>
		</form>
			
		</div>
<a href="http://www.baidu.com">
		<div data-role="footer" data-position="fixed">
			<img style="max-width:100%;" src="img/ad-0001.png"/>
		</div>
</a>
	</div>

</body>
</html>

