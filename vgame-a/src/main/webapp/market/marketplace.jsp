<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String roleId = request.getParameter("roleId");

if(Server.getConfig().getBoolean("isDebug") && roleId == null) {
	roleId = "r100031000092";
}
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
  <center>
  <body>
  <input id="jiangQuan" value="<%=jiangQuan%>" style="display:none;">
     <table>	
     <tr>
     <td>
     <img src="images/iphone.jpg"  onclick="show1();">
     <h2>  &nbsp;&nbsp;&nbsp;&nbsp;iPhone 6</h2>
     </td>
     <td>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </td>
      <td>
      <img src="images/iphone.jpg"  onclick="show2();">
      <h2>iPhone 6plus</h2>
     </td>
     </tr>
     <tr>
     <td>
     <img src="images/huafei.png"  onclick="show2();">
     <h5>50话费(电信、联通、移动)</h5>
     </td>
     </tr>
     </table>
  </body>
  </center>
  <script lanuage="javascript">
  function show1()
 	{
    
 	if(document.getElementById("jiangQuan").value>1000){
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
