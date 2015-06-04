<!DOCTYPE html>
<%@page import="cn.javaplus.log.Log"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf8"%>
<%@page import="com.cnbizmedia.gm.project.GmScriptManager"%>
<%@page import="com.cnbizmedia.gm.project.GmScriptItem"%>
<%@page import="com.cnbizmedia.gm.project.GmScriptGroup"%>
<%@page import="com.cnbizmedia.gm.project.Project"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen"%>
<%@page import="java.util.List"%>
<%@page import="com.cnbizmedia.gm.Zone"%>
<%@page import="com.cnbizmedia.Server"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<style>
.topnav {
	width: 213px;
	padding: 40px 28px 25px 0;
	font-family: "CenturyGothicRegular", "Century Gothic", Arial, Helvetica,
		sans-serif;
}

ul.topnav {
	padding: 0;
	margin: 0;
	font-size: 1em;
	line-height: 0.5em;
	list-style: none;
}

ul.topnav li {
	
}

ul.topnav li a {
	line-height: 10px;
	font-size: 11px;
	padding: 10px 5px;
	color: #000;
	display: block;
	text-decoration: none;
	font-weight: bolder;
}

ul.topnav li a:hover {
	background-color: #675C7C;
	color: white;
}

ul.topnav ul {
	margin: 0;
	padding: 0;
	display: none;
}

ul.topnav ul li {
	margin: 0;
	padding: 0;
	clear: both;
}

ul.topnav ul li a {
	padding-left: 20px;
	font-size: 10px;
	font-weight: normal;
	outline: 0;
}

ul.topnav ul li a:hover {
	background-color: #D3C99C;
	color: #675C7C;
}

ul.topnav ul ul li a {
	color: silver;
	padding-left: 40px;
}

ul.topnav ul ul li a:hover {
	background-color: #D3CEB8;
	color: #675C7C;
}

ul.topnav span {
	float: right;
}
</style>
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="../js/scriptbreaker-multiple-accordion-1.js"></script>
<script language="JavaScript">
	$(document).ready(function() {
		$(".topnav").accordion({
			accordion : false,
			speed : 500,
			closedSign : '',
			openedSign : ''
		});
	});
</script>

<ul class="topnav">
	<li><a href="zoneBody.jsp" target="body">系统配置</a> <!-- <ul>
			<li><a href="#">Cookies</a></li>
			<li><a href="#">Navigations</a>
				<ul>
					<li><a href="#">CSS</a></li>
					<li><a href="#">JavaScript</a></li>
					<li><a href="#">JQuery</a></li>
				</ul>
			</li>
			<li><a href="#">Tabs</a></li>
		</ul> --></li>
	<li><a href="excelUpload.jsp" target="body">数值表</a></li>

	<%
		Zone zone = Server.getProjectManager().getZone(session);
		String url = zone.getServerUrl();
		String gmUserId = (String) session.getAttribute("gmUserId");
		
		out.println("<li><a href=\"" + url + "/gm/menu.jsp?gmUserId=" +gmUserId + "\" target=\"body\">系统管理</a></li>");
	%>
	
	<!-- <li><a href="importGmScript.jsp" target="body">导入GM功能</a></li> -->
	<li><a href="protocolDoc.jsp" target="body">通信协议</a></li>


</ul>
</body>
</html>