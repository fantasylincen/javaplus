<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String roleId = request.getParameter("roleId");

Role role = Server.getRole(roleId);
long jiangQuan = role.getJiangQuan();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>兑换手机</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <table>
     <tr>
     <td>
     <img src="images/iphone.jpg"  onclick="show1();">
     </td>
     </tr>
     <tr>
     <td>
     <img src="images/iphone.jpg"  onclick="show2();">
     </td>
     </tr>
     </table>
  </body>
  
  <script lanuage="javascript">
  function show1()
 	{
 	var jiangQuan=document.getElementById("<%=jiangQuan%>").value ;
 	if(jiangQuan>=1000){
 		alert("兑换成功！请与客服QQ：123456789联系领取奖品！谢谢！");
 		}else{
 		alert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖卷！");
 		}
 	}
 	
  function show2()
 	{
 		alert("兑换成功！请与客服QQ：123456789联系领取奖品！谢谢！");
 	}
  </script>


</html>
