<%@page import="cn.javaplus.util.Util"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.javaplus.tb.*"%>
<%@ page import="cn.javaplus.tb.gen.dto.MongoGen.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta name="google-site-verification"
	content="sDpJcmu5_LiEBandw_hmCImhDllcN5s5jC9e86Ehi2U" />
<meta name="baidu-site-verification" content="LHinlQqloR" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.6.3.min.js"></script>
<script type="text/javascript" src="/view/js/main.js"></script>
<link rel="stylesheet" href="http://pachong.org/view/style/css.css" />
<script type="text/javascript">
	var cock = 3449 + 2092;
	var dog = 462 + 8332 ^ cock;
	var calf = 2773 + 5238 ^ dog;
	var rat = 7298 + 6935 ^ calf;
	var ant = 1642 + 3543 ^ rat;
</script>
</head>
<body>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".menu>li").bind('mouseover', function() {
				$(this).find(".sec").show();
			})
			$(".menu>li").bind('mouseleave', function() {
				$(this).find(".sec").hide();
			})
		});
	</script>
	<div class="mainWap">
		<h1 class="title">商品列表</h1>
		<form id="contacts-form" action="backstage/add" method="post">
			SourceId <input type="text" name="sourceId" /> Id <input type="text"
				name="id" /> <input type="submit" value="Add" />
		</form>
		<table class="tb">

			<thead>
				<tr>
					<td class="tcenter">源</td>
					<td class="tcenter">我的</td>
					<td class="tcenter">原价</td>
					<td class="tcenter">店铺价</td>
					<td class="tcenter">源库存</td>
					<td class="tcenter">剩余库存</td>
					<td class="tcenter">更新时间</td>
				</tr>
			</thead>
			<tbody>
				<%
					SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm:ss");
					for (RecordDto record : Server.getRecordManager().getRecords()) {

						RecordAdaptor r = new RecordAdaptor(record);
						String time = sf.format(new Date(record.getLastUpdateTime()));
				%>

				<tr>

					<td><a href="<%=r.getSourceUrl()%>"><%=record.getSourceId()%></a></td>
					<td><a href="<%=r.getUrl()%>"><%=record.getMineId()%></a></td>
					<td><%=r.getSourcePrice()%></td>
					<td><%=r.getPrice()%></td>
					<td><%=r.getSourceCount()%></td>
					<td><%=r.getCount()%></td>
					<%
						if(System.currentTimeMillis() - record.getLastUpdateTime() > Util.Time.MILES_ONE_MIN * 20) {
					%>
							<td><font color="FF000000"><%=time%></font></td>
					<%
						} else {
					%>
							<td><%=time%></td>
					<%
						}
					%>
				</tr>

				<%
					}
				%>
			</tbody>


		</table>
</body>
</html>