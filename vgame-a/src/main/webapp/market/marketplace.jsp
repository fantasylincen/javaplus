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

<script language="javascript" type="text/javascript">

        //自定义弹出框        
	function msgbox(title,content,func,cancel,focus,icon){
		/*		
		参数列表说明:
		title :弹出对话框的标题,标题内容最好在25个字符内,否则会导致显示图片的异常															
		text  :弹出对话框的内容,可以使用HTML代码,例如<font color='red'>删除么?</font>,如果直接带入函数,注意转义
		func  :弹出对话框点击确认后执行的函数,需要写全函数的引用,例如add(),如果直接带入函数,注意转义。
		cancel:弹出对话框是否显示取消按钮,为空的话不显示,为1时显示
		focus :弹出对话框焦点的位置,0焦点在确认按钮上,1在取消按钮上,为空时默认在确认按钮上
		icon  :弹出对话框的图标
		Author:Jedliu
		Blog  :Jedliu.cublog.cn 
		【网页转载请保留版权信息,实际使用时可以除去该信息】
		*/	
		icon="msgbox_"+icon+".png";
		create_mask();
		var temp="<div style=\"width:300px;border: 2px solid #37B6D1;background-color: #fff; font-weight: bold;font-size: 12px;\" >"
				+"<div style=\"line-height:25px; padding:0px 5px;	background-color: #37B6D1;\">"+title+"</div>"
				+"<table  cellspacing=\"0\" border=\"0\"><tr><td style=\" padding:0px 0px 0px 20px; \"></td>"
				+"<td ><div style=\"background-color: #fff; font-weight: bold;font-size: 12px;padding:20px 0px ; text-align:left;\">"+content
				+"</div></td></tr></table>"
				+"<div style=\"text-align:center; padding:0px 0px 20px;background-color: #fff;\"><input type='button'  style=\"border:1px solid #CCC; background-color:#CCC; width:50px; height:25px;\" value='确定'id=\"msgconfirmb\"   onclick=\"remove();"+func+";\">";
		if(null!=cancel){temp+="&nbsp;&nbsp;&nbsp;<input type='button' style=\"border:1px solid #CCC; background-color:#CCC; width:50px; height:25px;\" value='取消'  id=\"msgcancelb\"   onClick='remove()'>";}
		temp+="</div></div>";
		create_msgbox(400,200,temp);
		if(focus==0||focus=="0"||null==focus){document.getElementById("msgconfirmb").focus();}
		else if(focus==1||focus=="1"){document.getElementById("msgcancelb").focus();}			
	}
	function get_width(){
		return (document.body.clientWidth+document.body.scrollLeft);
	}
	function get_height(){
		return (document.body.clientHeight+document.body.scrollTop);
	}
	function get_left(w){
		var bw=document.body.clientWidth;
		var bh=document.body.clientHeight;
		w=parseFloat(w);
		return (bw/2-w/2+document.body.scrollLeft);
	}
	function get_top(h){
		var bw=document.body.clientWidth;
		var bh=document.body.clientHeight;
		h=parseFloat(h);
		return (bh/2-h/2+document.body.scrollTop);
	}
	function create_mask(){//创建遮罩层的函数
		var mask=document.createElement("div");
		mask.id="mask";
		mask.style.position="absolute";
		mask.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=4,opacity=25)";//IE的不透明设置
		mask.style.opacity=0.4;//Mozilla的不透明设置
		mask.style.background="black";
		mask.style.top="0px";
		mask.style.left="0px";
		mask.style.width=get_width();
		mask.style.height=get_height();
		mask.style.zIndex=1000;
		document.body.appendChild(mask);
	}
	function create_msgbox(w,h,t){//创建弹出对话框的函数
		var box=document.createElement("div")	;
		box.id="msgbox";
		box.style.position="absolute";
		box.style.width=w;
		box.style.height=h;
		box.style.overflow="visible";
		box.innerHTML=t;
		box.style.zIndex=1001;
		document.body.appendChild(box);
		re_pos();
	}
	function re_mask(){
		/*
		更改遮罩层的大小,确保在滚动以及窗口大小改变时还可以覆盖所有的内容
		*/
		var mask=document.getElementById("mask")	;
		if(null==mask)return;
		mask.style.width=get_width()+"px";
		mask.style.height=get_height()+"px";
	}
	function re_pos(){
		/*
		更改弹出对话框层的位置,确保在滚动以及窗口大小改变时一直保持在网页的最中间
		*/
		var box=document.getElementById("msgbox");
		if(null!=box){
			var w=box.style.width;
			var h=box.style.height;
			box.style.left=get_left(w)+"px";
			box.style.top=get_top(h)+"px";
		}
	}
	function remove(){
		/*
		清除遮罩层以及弹出的对话框
		*/
		var mask=document.getElementById("mask");
		var msgbox=document.getElementById("msgbox");
		if(null==mask&&null==msgbox)return;
		document.body.removeChild(mask);
		document.body.removeChild(msgbox);
	}
	
	function re_show(){
		/*
		重新显示遮罩层以及弹出窗口元素
		*/
		re_pos();
		re_mask();	
	}
	function load_func(){
		/*
		加载函数,覆盖window的onresize和onscroll函数
		*/
		window.onresize=re_show;
		window.onscroll=re_show;	
	}
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
     <td style="position: relative ;left:80px;" >
     <img src="images/iphone.jpg"  onclick="show1();">
     <h3>iPhone 6/100000奖券</h3>
     </td>
     <td>	
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     <td style="position: relative ;left:180px;" >
      <img src="images/iphone.jpg"  onclick="show2();">
     <h3>iPhone 6/100000奖券</h3>
     </td>
     <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
      <td style="position: relative ;left:280px;" >
      <img src="images/iphone.jpg"  onclick="show3();">
      <h4>iPhone 6plus/120000奖券</h4>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     <tr>
    <td style="position: relative ;left:80px;" >
     <img src="images/huafei.png"  onclick="show4();">
     <h5>50话费(电信、联通、移动)/500奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     <td style="position: relative ;left:180px;" >
     <img src="images/iphone.jpg"  onclick="show5();">
     <h3>iPhone 6/100000奖券</h3>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
    <td style="position: relative ;left:280px;" >
     <img src="images/huafei.png"  onclick="show6();">
     <h5>100话费(电信、联通、移动)/1000奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     <tr>
    <td style="position: relative ;left:80px;" >
     <img src="images/E.png"  onclick="show7();">
     <h4>&nbsp;&nbsp;京东E卡/1000奖券</h4>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
      <td style="position: relative ;left:180px;" >
     <img src="images/iphone.jpg"  onclick="show8();">
     <h3>iPhone 6/100000奖券</h3>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
    <td style="position: relative ;left:280px;" >
     <img src="images/iphone.jpg" 	" onclick="show9();">
     <h5> &nbsp;&nbsp;机器人笔记本/1000000奖券</h5>
     </td>
      <td>
     &nbsp;&nbsp;&nbsp;&nbsp;
     </td>
     </tr>
     </table>
  </body>

  <script lanuage="javascript">
  function show1()
 	{
      
 	if(document.getElementById("jiangQuan").value>=100000){
 		//alert("恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！");
 		msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		//alert("对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！");
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	
  function show2()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=100000){
 		msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	
 	  function show3()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=120000){
 			msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	  function show4()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=500){
 		msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	  function show5()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=100000){
 		msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 	    msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	  function show6()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=1000){
 			msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	  function show7()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=1000){
 			msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	
 	 	  function show8()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=100000){
 			msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
 	
 	 	  function show9()
 	{
 		
 	 	if(document.getElementById("jiangQuan").value>=1000000){
 			msgbox('提示','恭喜！您的奖券数量已足够兑换该奖品！请联络客服QQ3107252937登记您的收货地址信息与联络方式，我们将尽快为您寄发礼品！','',null,0,'Warning')
 		}else{
 		 msgbox('提示','对不起，您的兑换劵不足，不能兑换此奖品，请先集齐兑奖券！','',null,0,'Warning')
 		}
 	}
  </script>


</html>
