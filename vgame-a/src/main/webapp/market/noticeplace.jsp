<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";




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
<style type="text/css">
body
{
font-family : 微软雅黑,宋体;
font-size : 1em;
color : #080808;
}
</style>

  <body  style=" width:600 height:800" >
  <center>
     <table>
      <tr>
    <td style="color:red" width="800" >
     
      <font size="8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公&nbsp;&nbsp;&nbsp;&nbsp;告</font> 
     </td>
     </tr>
        <tr>
    <td  width="800" >
     
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
         <tr>
    <td  width="800" >
     
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     <tr>
    <td  width="700" >
     
      <font size="5">一、飞禽走兽-水果机测试阶段结束，现将清除所有玩家的金豆和奖券，对于在测试期间充值的玩家，将在版本更新后给予双倍金豆补偿。</font> 
     </td>
     </tr>
        <tr>
    <td  width="700" >
      <font size="5">
                                                        二、飞禽走兽-正式开放“兑奖”功能。</font> 
     </td>
     </tr>
      <tr>
    <td  width="700" >
      <font size="5">
                                                        三、充值内购12元、25元、30元现加入首充翻倍奖励。</font> 
     </td>
     </tr>
          <tr>
    <td  width="700" >
      <font size="5">
                                                        四、飞禽”和“走兽”的押注金额提升至999999。</font> 
     </td>
     </tr>
     </table>
     </center>
  </body>

  


</html>
