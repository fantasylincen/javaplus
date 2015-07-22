<%@page import="cn.javaplus.log.Log"%>
<%@page import="cn.vgame.share.ParameterUtil"%>
<%@page import="cn.vgame.a.log.GmLogTranslate"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDto"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDao.GmLogDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.GmLogDao"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="head.html"%>
</head>
<body>
	<div data-demo-html="true" style="width: 95%; ">
		<%
			String pg = ParameterUtil.getParameter(request, session, "page");
			String countEve = ParameterUtil.getParameter(request, session,
					"countEvery");

			if (pg == null) {
				pg = "100000000";
			}

			if (countEve == null) {
				countEve = "22";
			}

			GmLogDao dao = Daos.getGmLogDao();

			GmLogDtoCursor c = dao.find();
			int cev = new Integer(countEve);
			int pageInt = new Integer(pg);
			c.page(pageInt, cev);

			Log.d("count", c.getCount(), countEve);

			int pageAll = c.getPageAll();

			if (pageInt > pageAll)
				pageInt = pageAll;
			if (pageInt < 1)
				pageInt = 1;
		%>


		<table border="1" data-role="table"  data-mode="columntoggle"
			class="ui-body-d ui-shadow table-stripe ">

			<thead>
				<tr>
					<th>时间</th>
					<th>操作者</th>
					<th>说明</th>
					<th>方法</th>
					<th>参数列表</th>
					<th>操作结果</th>
				</tr>
			</thead>

			<tbody>

				<%
					for (GmLogDto dto : c) {
				%>
				<tr>
					<td><%=dto.getDate()%></td>
					<td><%=dto.getUser()%></td>
					<td><%=GmLogTranslate.translate(dto)%></td>
					<td><%=dto.getClassName() + "." + dto.getMethodName()%></td>
					<td><%=dto.getArgs()%></td>
					<td><%="success".equals(dto.getResult()) ? "成功" : "失败"%></td>
				</tr>
				<%
					}
				%>

			</tbody>

		</table>





		<form id="nextPage" action="logs.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>"> <input
				type="hidden" name="page" value="<%=pageInt + 1%>">
		</form>

		<form id="prePage" action="logs.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>"> <input
				type="hidden" name="page" value="<%=pageInt - 1%>">
		</form>
		<form name="jump" action="logs.jsp" method="post">
			<a href="logs.jsp?page=<%=1%>&countEvery=<%=cev%>">首页</a>&nbsp; <a
				href="logs.jsp?page=<%=pageInt - 1%>&countEvery=<%=cev%>">上一页</a>
			<%=pageInt + "/" + pageAll%>
			<a href="logs.jsp?page=<%=pageInt + 1%>&countEvery=<%=cev%>">下一页</a>&nbsp;

			<a href="logs.jsp?page=<%=pageAll%>&countEvery=<%=cev%>">末页</a>
			&nbsp;&nbsp;<input type="hidden" name="countEvery" value="<%=cev%>">
			<input type="text" name="page" value="<%=pageInt%>"> <a
				href="javascript:jump.submit();">go</a> &nbsp;&nbsp;&nbsp;
		</form>





		<br> <br> <a href="menu.jsp">返回</a>
	</div>
</body>
</html>