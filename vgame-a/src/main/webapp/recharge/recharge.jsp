<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@page import="cn.javaplus.util.Util"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

  </head>
  
  <body bgcolor="#76502F">
  
  	<font size="36">
  
    	<%
    		String roleId = (String)session.getAttribute("roleId");
    		Role role = Server.getRole(roleId);
    	 %>
    	您正在为 <%=role.getNick() %>充值<br>
    </font>
    	<form id="contacts-form" name=alipayment action=pay/jishi/alipayapi.jsp
					method=post target="_blank">
			<div id="body" style="clear:left">
				<input type="hidden" size="30" name="WIDout_trade_no" value="<%=Util.ID.createId()%>" /> 
				<input type="hidden" size="30" name="WIDsubject" value="game coin" />
				<input type="hidden" size="30" name="WIDbody" value="buy game coin" />
				<input type="hidden" size="30" name="WIDshow_url" value="recharge/rechargeSuccess.jsp" />

				<input id="vb" name="vb" type="hidden" value="1" />
				
				<font size="36">
					<a href="javascript:alipayment.submit();">立即充值</a>
				</font>

			</div>
		</form>

  </body>
</html>
