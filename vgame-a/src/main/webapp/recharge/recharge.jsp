<%@page import="cn.javaplus.excel.Row"%>
<%@page import="cn.vgame.share.Xml"%>
<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@page import="cn.javaplus.util.Util"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<style>
@charset "utf-8";

body,div {
	margin: 0;
	padding: 0;
	font-style: normal;
	font: 24px/48px "\5B8B\4F53", Arial, Helvetica, sans-serif
}

ol,ul,li {
	list-style: none
}

img {
	border: 0;
	vertical-align: middle
}

body {
	color: #76502F;
	background: #FFFFFF;
	text-align: center
}

a {
	color: #76502F;
	text-decoration: none
}

a:hover {
	color: #BA2636;
	text-decoration: underline
}

.box1,.box2,.box3,.box4 {
	width: 600px;
	height: 57px;
	margin: 0 auto;
	border: 1px solid #666
}

.box1 {
	background: url(recharge/jd.png)
}

.box2 {
	background: url(recharge/jd.png) no-repeat 10px center
}

.box3 {
	background: url(recharge/jd.png) repeat-x 0 30px
}

.box4 {
	background: url(recharge/jd.png) repeat-y center
}
</style>

</head>

<body bgcolor="#76502F">

	<img src="recharge/zfb.png">
	<%
		String roleId = (String) session.getAttribute("roleId");
		if(roleId==null && Server.getConfig().getBoolean("isDebug")) {
			roleId = "VC100031001427";
		}
		Role role = Server.getRole(roleId);
	%>
	您正在为
	<%=role.getNick()%>充值
	<br>


	<%
		for (Row r : Xml.getSheet("recharge-A").getAll()) {
			int id = r.getInt("id");
			double rmb = r.getDouble("rmb");
			boolean isDebug = Server.getConfig().getBoolean("isDebug");
			
	%>
			<form id="contacts-form" name=form<%=id %> action=pay/jishi/alipayapi.jsp
			method=post target="_blank">
			<div class="box2" id="body" style="clear:left">
				<input type="hidden" name="WIDout_trade_no" value="<%=Util.ID.createId()%>" /> 
				<input type="hidden" name="WIDsubject" value="game coin" /> 
				<input type="hidden" name="WIDbody" value="buy game coin" /> 
				<input type="hidden" name="WIDshow_url" value="recharge/rechargeSuccess.jsp" /> 
				<input type="hidden" name="WIDtotal_fee" value=<%=isDebug? "0.01" : (rmb + "") %> /> 
				<input id="id" name="id" type="hidden" value="<%=id %>" /> <%=r.get("dsc") %>
				<a href="javascript:form<%=id %>.submit();"></a>
					
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:form<%=id %>.submit();"><img src="recharge/buttonUp.png" style="width:60px;height:38px;"/></a>
			</div>
			
			
			
	</form>
	<%
		}
	%>


	







</body>
</html>
