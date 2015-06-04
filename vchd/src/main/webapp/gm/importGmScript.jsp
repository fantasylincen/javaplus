

<%@page import="com.cnbizmedia.gm.gamexml.GameXml"%>
<%@page import="com.cnbizmedia.config.GameProperties"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.cnbizmedia.gm.Zone"%>
<%@page import="com.cnbizmedia.Server"%>
<%@page import="cn.javaplus.collections.list.Lists"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen"%>
<%@page import="java.util.List"%>
<%@page import="cn.javaplus.util.Util"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.Daos"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDao"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDto"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ZoneDto"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.KeyValueDto"%>
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

		<br>
		<form action="importGmScript" enctype="multipart/form-data"
			method="post">
			请上传 .xml 脚本: <br><input type="file" name="image">
			<input type="submit" value="导入" />

		</form>
	</div>

<br><br>
<br><br>
或者
<br><br>
<br><br>
	<div class="container theme-showcase" role="main">

		<br>
		<form action="importGmScriptText" method="post">
			从文本导入:<br>
			<textarea name="text" rows="20" cols="50">
								</textarea>
			<input type="submit" value="导入" />

		</form>
	</div>
</body>
</html>