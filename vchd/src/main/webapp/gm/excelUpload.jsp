

<%@page import="com.cnbizmedia.gm.gamexml.ClientXml"%>
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


		<p>
			<span style="font-size:18px;"><strong>服务器配置表</strong></span>
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
		
		
		<%
			String pId = (String) session.getAttribute("projectId");
			String zId = (String) session.getAttribute("zoneId");
			ActionContext ac = ActionContext.getContext();
			ServletContext sc = (ServletContext) ac
					.get(ServletActionContext.SERVLET_CONTEXT);

			Zone zone = Server.getProjectManager().getZone(session);
			GameXml xml = zone.getGameXml();
			String p = "gm/fileDownload?projectId=" + pId + "&zoneId=" + zId;

			if (xml.exist()) {

				out.println("文件版本:&nbsp;&nbsp;" + xml.getVersion());
				out.println("<br>");
				out.println("<br>");
				out.println("文件指纹:&nbsp;&nbsp;" + xml.getMd5());
				out.println("<br>");
				out.println("<br>");
				out.println("上传时间:&nbsp;&nbsp;" + xml.getUploadTime());
				out.println("<br>");
				out.println("<br>");
				out.println("<a href=\"../" + p + "\">点击下载 </a>");
				out.println("<br>");
			}
		%>

		<br> 
		<form action="uploadAction" enctype="multipart/form-data"
			method="post">
			上传 game.xml: <input type="file" name="image"><br><br> <input
				type="submit" value="上传" />



			<%!public String getValue(String key, Zone zone) {
		List<KeyValueDto> ks = zone.getProperties();
		for (KeyValueDto e : ks) {
			if (e.getKey().equals(key)) {
			
				return e.getValue();
			}
		}

		return null;
	}%>
			<%
				String s = "http://" + getValue("host", zone) + "/"
						+ getValue("webContextRoot", zone) + "/gm/reloadConfig";
			%>
			<br><br>
			<a href="<%=s%>">重新加载系统配置</a>

		</form>
		
		
		
		
		
		
		
		
		<br>
		<br>
		<br>
		<br>
		
		
		<p>
			<span style="font-size:18px;"><strong>客户端配置表</strong></span>
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<%
			ClientXml clientXml = zone.getClientXml();
			
			String path = "gm/fileDownloadClientXml?projectId=" + pId + "&zoneId=" + zId;

			if (clientXml.exist()) {

				out.println("文件版本:&nbsp;&nbsp;" + clientXml.getVersion());
				out.println("<br>");
				out.println("<br>");
				out.println("文件指纹:&nbsp;&nbsp;" + clientXml.getMd5());
				out.println("<br>");
				out.println("<br>");
				out.println("上传时间:&nbsp;&nbsp;" + clientXml.getUploadTime());
				out.println("<br>");
				out.println("<br>");
				out.println("<a href=\"../" + path + "\">点击下载 </a>");
				out.println("<br>");
			}
		%>

		<br> 
		<form action="uploadClientXmlAction" enctype="multipart/form-data"
			method="post">
			选择客户端zip包: <input type="file" name="image"><br><br> <input
				type="submit" value="上传" />

		</form>
	</div>
</body>
</html>