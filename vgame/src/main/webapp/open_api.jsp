<%@page import="cn.javaplus.util.Util"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cnbizmedia.*"%>
<%@ page import="com.cnbizmedia.account.*"%>
<%@ page import="com.cnbizmedia.gen.dto.MongoGen"%>
<%@ page import="com.cnbizmedia.user.*"%>
<%@ page import="com.cnbizmedia.config.*"%>
<%@ page import="com.cnbizmedia.recharge.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>VGame</title>
<%@include file="head.html"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<script type="text/javascript"
	src="http://file.u77.com/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/common.js?v=20150128"></script>
<link type="text/css" rel="stylesheet"
	href="/static/css/common.css?v=20150128">

</head>

<body>

	<%@include file="head.jsp"%>

	<div class="main-box">
		<div class="container">
			<div class="inside">
				<div class="wrapper">
					<p>
	<span><span style="line-height:normal;"><strong>1.平台注册接口</strong></span></span>
</p>
<p>
	<span><span style="line-height:normal;"><strong>2.平台登陆接口</strong></span></span>
</p>
<p>
	<span><span style="line-height:normal;"><strong>3.请您提供游戏充值接口</strong></span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp; &nbsp; &nbsp;a.详细参数约定</span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp; &nbsp; &nbsp;b.Key约定</span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp; &nbsp; &nbsp;c.返回格式:&nbsp;</span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{ "code":"success", "desc":""}</span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:medium;line-height:normal;">{ "code":"error", "desc</span><span style="font-size:medium;line-height:normal;">":"reason"} &nbsp; // 如果充值失败, 这个reason 将显示到玩家充值记录的 状态列里面</span><br />
</span></span>
</p>
<p>
	<span><span style="line-height:normal;">&nbsp; &nbsp; &nbsp;d.注意 平台可能重复提交订单给游戏服务器, 请游戏服务器通过订单ID自行判定订单是否重复, 如果重复请游戏服务器将其忽略, 同时返回成功&nbsp;<span style="font-size:medium;line-height:normal;">&nbsp;&nbsp;</span><span style="font-size:medium;line-height:normal;">{ "code":"success", "desc</span><span style="font-size:medium;line-height:normal;">":"xxxxxxxxxxxxxx"}</span></span></span>
</p>
				</div>
			</div>
		</div>
	</div>

</body>
<%@include file="footer.html"%>
</body>


</html>
