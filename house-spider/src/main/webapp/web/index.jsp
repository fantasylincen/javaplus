<%@page import="cn.javaplus.collections.list.Lists"%>
<%@page import="org.hhhhhh.house.hibernate.dao.HouseDtoCursor"%>
<%@page import="org.hhhhhh.house.hibernate.dao.HouseDao"%>
<%@page import="org.hhhhhh.house.hibernate.dao.Daos"%>
<%@page import="org.hhhhhh.house.hibernate.dto.HouseDto"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>房源信息</title>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="../css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../_assets/css/jqm-demos.css">
<script src="../js/jquery.js"></script>
<script src="../_assets/js/index.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>





</head>
<body>
	<div data-role="page" class="jqm-demos" data-quicklinks="true">


		<div class="jqm-block-content">
			<h3>房源信息</h3>
			<font size="1"> <%
 	StringBuffer ssb = new StringBuffer();

 	HouseDtoCursor find = Daos.getHouseDao().findSortByLimit(
 			"commit_date", 100);

 	List<HouseDto> ls = Lists.newArrayList();
 	for (HouseDto dto : find) {
 		print(ssb, dto);
 	}

 	out.println(ssb.toString());
 %> </font>

			<%!void print(StringBuffer sb, HouseDto dto) {

		String name = dto.getName();

		String color;
		
		color = "#29CBFC";
		sb.append("<p><font color=\"" + color + "\">");
		sb.append(dto.getCommit_date().substring(5, 16));
		sb.append("</font>&nbsp;&nbsp;");

		color = "#DD74EC";
		sb.append("<font color=\"" + color + "\">");
		sb.append("<a href=\"" + dto.getHref() + "\">");
		sb.append(name);
		sb.append("</a></font>&nbsp;&nbsp;");

		color = "#29CBFC";
		sb.append("<font color=\"" + color + "\">");
		sb.append("&nbsp;&nbsp;" + dto.getOwner());
		sb.append("</font>&nbsp;&nbsp;");

		color = "#AB3B48";
		sb.append("<font color=\"" + color + "\">");
		String tel = dto.getTel();
		if (tel.startsWith("http://")) {
			//sb.append("<img width=\"70\" src=\"" + tel + "\">");
		} else {
			sb.append(tel);
		}
		sb.append("</font>&nbsp;&nbsp;");

		color = "#59CA1C";
		sb.append("<font color=\"" + color + "\">");
		sb.append("￥" + dto.getPrice());
		sb.append("</font>&nbsp;&nbsp;");

		color = "#29CBFC";
		sb.append("<font color=\"" + color + "\">");
		sb.append("&nbsp;&nbsp;" + dto.getHuxing());
		sb.append("</font>&nbsp;&nbsp;");
		sb.append("</p>");
		sb.append("<br>");
	}%>

		</div>

	</div>
</body>
</html>

