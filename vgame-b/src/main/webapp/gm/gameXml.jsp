

<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="cn.javaplus.collections.list.Lists"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="cn.javaplus.util.Util"%>
<%@page import="java.util.Map.Entry"%>
<html>
<head>
<title>文件上传</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>

	<div class="container theme-showcase" role="main">


		<p>
			<span style="font-size:18px;"><strong>配置表</strong></span>
		</p>
		<p>
			<span style="font-size:18px;"><strong>
			<hr />
			<br />
		</strong></span>
		</p>
		<p>
			<span style="font-size:18px;"><strong>
		</strong></span>
		</p>
		

		<br> 
		<form action="gm/uploadGameXmlAction" enctype="multipart/form-data"
			method="post">
			上传  game.xml: <input type="file" name="image"><br><br> <input
				type="submit" value="上传" />

		</form>
		
	</div>
</body>
</html>