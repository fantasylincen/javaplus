

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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../images/favicon.ico">

<title>Theme Template for Bootstrap</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<link href="../css/simpletable.css" rel="stylesheet">
<link href="../css/theme.css" rel="stylesheet">
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js/ie-emulation-modes-warning.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->



<meta name="description"
	content="The power of GitHub's social coding for your own workgroup. Pricing, tour and more.">
<meta http-equiv="Content-type:text/html;charset=utf-8">
<link href="/assets/application-55f07ba38e3d64171dacc542c1927633.css"
	media="all" rel="stylesheet" />
<link href="/assets/print-0a04386179bac34932c1e3701a290e49.css"
	media="print" rel="stylesheet" />

<script src="/assets/application-39c65e5269029f77399e35204fc1b25b.js"></script>

<meta content="authenticity_token" name="csrf-param" />
<meta
	content="wVyoRJESvkdzyLgzg7qWCJQWj4R8+LqBqqzfJfAH9MwohwZpl4Rko+EreFXQpEXEXQpRwGIM3s1qUmoxoXu7xA=="
	name="csrf-token" />
<script src="/assets/event_tracking-de44750e00c71a7199b1447084374783.js"></script>
</head>

<body>


	<h2>
		<%
			Zone zone = Server.getProjectManager().getZone(session);
			out.print(Server.getProjectManager().getProjectName(session)
					+ " - "
					+ zone.getDto().getId()
					+ " - "
					+ zone.getName());
		%>
	</h2>
	<form id="form2" action="saveSystemProperty" method="post">
		<table class="bordered">
			<thead>
				<tr>
					<th>键名</th>
					<th>值</th>
					<th>客户端是否可见</th>
					<th>说明</th>
					<th>操作</th>
				</tr>
			</thead>

			<%!public String getDsc(String key) {
		return key;
	}%>


			<%
				List<KeyValueDto> ks = zone.getProperties();
				for (KeyValueDto e : ks) {

					out.println("<tr>");
					out.println("<td><input name=\"key\" value=\""
							+ e.getKey()
							+ "\" style=\"border-left:0px;border-top:0px;border-right:0px;border-bottom:0px\"></td>");
					out.println("<td><input name=\"value\" value=\""
							+ e.getValue()
							+ "\" style=\"border-left:0px;border-top:0px;border-right:0px;border-bottom:0px\"></td>");
					out.println("<td><input name=\"isClientVisible\" value=\""
							+ e.getIsClientVisible()
							+ "\" style=\"border-left:0px;border-top:0px;border-right:0px;border-bottom:0px\"></td>");
					out.println("<td><input name=\"dsc\" value=\""
							+ e.getDsc()
							+ "\" style=\"border-left:0px;border-top:0px;border-right:0px;border-bottom:0px\"></td>");

					out.println("<td><input type=button value=\"删除\" onclick=\"if(confirm('确认删除吗')) location.href='deleteSystemProperty?key="
							+ e.getKey() + "';\"></td>");

					out.println("</tr>");
				}
			%>

			<tr>
				<td><input type="text" name="key" /></td>
				<td><input type="text" name="value" /></td>
				<td><input type="text" name="isClientVisible" /></td>
				<td><input type="text" name="dsc" /></td>
				<td></td>
			</tr>


		</table>


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
		<input type="submit" value="保存"
			onclick="document.getElementById('form2').submit();" /> <a
			href="<%=s%>">重新加载系统配置</a>
	</form>

	<br>
	<br>
	<br>

	<form id="setZoneName" action="setZoneName" method="post">
		修改分区名字:<input name="newName" type="text" value="<%=zone.getName()%>" />
		<input type="submit" value="修改" />
	</form>

	<br>
	<br>

	<%
		if (zone.getProperties().isEmpty()) {
	%>
	<form id="copyProperties" action="copyProperties" method="post">
		从其他区导入配置(分区ID):<input name="zoneId" type="text" /> <input
			type="submit" value="复制" />
	</form>
	<%
		}
	%>
</body>
</html>
