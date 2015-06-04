
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


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../images/favicon.ico">

<title>游戏管理平台</title>

<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<link href="../css/simpletable.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js/ie-emulation-modes-warning.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp" target="body">游戏管理平台</a>
			</div>
		</div>
	</div>

	<div class="container theme-showcase" role="main">
		<form id="form2" action="createZone" method="post">
			<table class="bordered">
				<thead>
					<tr>
						<th>分区名</th>
						<th>分区ID</th>
						<th>操作</th>
					</tr>
				</thead>

				<%!private static List<ZoneDto> getZones(ProjectDto dto) {
		if (dto == null) {
			return Lists.newArrayList();
		}
		List<ZoneDto> zones = dto.getZones();
		return Util.Collection.nullToEmpty(zones);
	}%>
				<%
					ProjectDao dao = Daos.getProjectDao();
					String projectId = (String)session.getAttribute("projectId");
					ProjectDto pd = dao.get(projectId);
					List<ZoneDto> zones = getZones(pd);

					for (ZoneDto dto : zones) {
						String name = dto.getName();
						String id = dto.getId();

						out.println("<tr>");

						out.println("<td>");
						out.print("<a href=\"selectZone?id=" + dto.getId() + "\" >"
								+ name + "</a>");
						out.println("</td>");

						out.println("<td>");
						out.println(dto.getId());
						out.println("</td>");

						out.println("<td>");
						out.println("<input type=button value=\"删除\" onclick=\"if(confirm('确认删除吗')) location.href='deleteZone?id="
								+ dto.getId() + "';\">");

						out.println("</td>");

						out.println("</tr>");
					}
				%>

				<tr>
					<td><input type="text" name="zoneName" />
					</td>


					<td><input type="text" name="zoneId" /></td>
					<td><input type="submit" value="创建分区"
						onclick="document.getElementById('form2').submit();" />
					</td>
				</tr>



			</table>

		</form>
	</div>



</body>
</html>
