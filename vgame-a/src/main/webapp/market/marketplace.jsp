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
String nick = role.getNick();//昵称
long coin = role.getCoin();//金豆


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
  
  <style>
.divAlert{
	width:300px;
	border: 2px solid #37B6D1;
	font-weight: bold;
	font-size: 12px;
	left: 364px;
	top: 109px;
}
.divAlert_Title{ line-height:25px; padding:0px 5px;	background-color: #37B6D1;}
.divAlert_Content{background-color: #fff; line-height:80px; text-align:center; }
.divAlert_Btn{ text-align:center; padding:0px 0px 20px;background-color: #fff;  }
.btnSure{ border:1px solid #CCC; background-color:#CCC; width:50px; height:25px;}

</style>

     <script src="spring/springweb.js" type="text/javascript" ></script>
        <script type="text/javascript" >                                       
            setBasePath("spring");                        
        </script>
  <body>
  <table>
 
     <tr>
     <td>
     <h2 style="color: red">尊敬的<%=nick%>:欢迎你来到兑换区，当前你的奖券：<%=jiangQuan%> <img src="images/jiangquan.png" width="25" height="25"></h2>
     </td>
     </tr>
  </table>
  </body>

  <body  >
  <input id="jiangQuan" value="<%=jiangQuan%>" style="display:none;">
     <table>
     <tr >
     <td style="position: relative ;left:60px;" >
     <img src="images/10.png"  onclick="show1();" width="250" height="250">
     <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;88888/1000奖券</h3>
     </td>
     <td>	
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     <td style="position: relative ;left:80px;" >
      <img src="images/iphone.jpg"  onclick="show2();" width="250" height="250">
     <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iPhone 6/100000奖券</h3>
     </td>
     <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
      <td style="position: relative ;left:100px;" >
      <img src="images/iphone.jpg"  onclick="show3();" width="250" height="250">
      <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iPhone 6plus/120000奖券</h4>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     <tr>
    <td style="position: relative ;left:60px;" >
     <img src="images/huafei.png"  onclick="show4();" width="250" height="250">
     <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;50话费(电信、联通、移动)/500奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     <td style="position: relative ;left:80px;" >
     <img src="images/iphone.jpg"  onclick="show5();" width="250" height="250">
     <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iPhone 6/100000奖券</h3>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
    <td style="position: relative ;left:100px;" >
     <img src="images/huafei.png"  onclick="show6();" width="250" height="250">
     <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;100话费(电信、联通、移动)/1000奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     <tr>
    <td style="position: relative ;left:60px;" >
     <img src="images/E.png"  onclick="show7();" width="250" height="250">
     <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;京东E卡/1000奖券</h4>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
      <td style="position: relative ;left:80px;" >
     <img src="images/iphone.jpg"  onclick="show8();" width="250" height="250">
     <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;iPhone 6/100000奖券</h3>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
    <td style="position: relative ;left:100px;" >
     <img src="images/iphone.jpg" 	" onclick="show9();" width="250" height="250">
     <h5> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机器人笔记本/1000000奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     </table>
  </body>

  <script type="text/javascript">
  function show1()
 	{
 	if(document.getElementById("jiangQuan").value>=100000){
 		 richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		
 		  richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		
 		}
 	}
 	
  function show2()
 	{
 	 	if(document.getElementById("jiangQuan").value>=100000){
 		 richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		 richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	
 	  function show3()
 	{
 	 	if(document.getElementById("jiangQuan").value>=120000){
            richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示"); 		
            }else{
            richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	  function show4()
 	{
 	 	if(document.getElementById("jiangQuan").value>=500){
 	    richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		  richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	  function show5()
 	{
 	 	if(document.getElementById("jiangQuan").value>=100000){
 		richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 	      richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	  function show6()
 	{
 	 	if(document.getElementById("jiangQuan").value>=1000){
 			richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		 richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	  function show7()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=1000){
 			richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		 richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	
 	 	  function show8()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=100000){
 			richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		 richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
 	
 	 	  function show9()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=1000000){
 			richAlert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！", "提示");
 		}else{
 		 richAlert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！", "提示");
 		}
 	}
  </script>


</html>
