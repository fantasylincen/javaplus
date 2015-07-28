<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.Server"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDto"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao"%>
<%@page import="cn.vgame.a.result.ErrorResult"%>
<%@page import="cn.javaplus.log.Log"%>
<%@page import="com.hp.hpl.sparta.Document"%>
<%@page import="cn.vgame.share.GameException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java"
	import="cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.RoleDto"%>
<%@ page language="java" import="cn.javaplus.util.Util"%>

<html>
<head>
<%@include file="head.html"%>
</head>
<body>
<center>
<%
		int cev = 1000;
 %>

	<div data-demo-html="true" style="width: 95%; ">
		<table border="1">
			<thead>
				<tr>
					<th>时间</th>
					<th>FROM&nbsp;&nbsp;&gt;&gt;&gt;&nbsp;&nbsp;TO</th>
					<th>金币</th>
					<th>说明</th>
				</tr>
			</thead>
			<%!void print(StringBuffer sb, CoinLogDto dto) {

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(dto.getTime());
		sb.append("</td>");

		sb.append("<td>");

		Role from = Server.getRole(dto.getFrom());
		Role to = Server.getRole(dto.getTo());
		String fromNick;
		String toNick;

		if (from == null) {
			fromNick = dto.getFrom();
		} else {
			fromNick = from.getNick();
		}
		
		if (to == null) {
			toNick = dto.getTo();
		} else {
			toNick = to.getNick();
		}

		fromNick = "<a href=\"coinLog.jsp?page=200000000&countEvery=1000&roleId="
				+ dto.getFrom() + "\">" + fromNick + "</a>";
		toNick = "<a href=\"coinLog.jsp?page=200000000&countEvery=1000&roleId="
				+ dto.getTo() + "\">" + toNick + "</a>";

		sb.append(fromNick + "&nbsp;&nbsp;&gt;&gt;&gt;&nbsp;&nbsp;" + toNick);

		sb.append("</td>");

		sb.append("<td>");
		sb.append(dto.getCoin());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(getDsc(dto));
		sb.append("</td>");

		sb.append("</tr>");

	}%>


			<%!public String getDsc(CoinLogDto dto) {
		String dsc = dto.getDsc();
		if ("receive coin".equals(dto.getDsc())) {
			return "领取金币";
		}
		if ("gm add".equals(dto.getDsc())) {
			return "GM平台扣金币";
		}
		if ("gm reduce".equals(dto.getDsc())) {
			return "GM平台加金币";
		}
		if ("send coin".equals(dto.getDsc())) {
			return "赠送金豆";
		}
		return dsc;
	}%>
			<%
				StringBuffer ssb = new StringBuffer();

				CoinLogDao dao = Daos.getCoinLogDao();

				String pg = request.getParameter("page");

				String cv = request.getParameter("countEvery");

				CoinLogDtoCursor find = dao.findByDsc("send coin");

				int p = pg == null ? Integer.MAX_VALUE : new Integer(pg);
				int countEvery = cv == null ? cev : new Integer(cv);

				if (p < 1)
					p = 1;

				if (countEvery > 2000 || countEvery <= 0)
					throw new ErrorResult("countEvery must < 2000 and > 0 ")
							.toException();

				int pageAll;
				int count = find.getCount();
				if (count % countEvery == 0) {
					pageAll = count / countEvery;
				} else {
					pageAll = count / countEvery + 1;
				}

				if (p > pageAll)
					p = pageAll;

				int skip = (p - 1) * countEvery;
				if (skip > 0) {
					find.skip(skip);
				}
				find.limit(countEvery);

				for (CoinLogDto dto : find) {
					print(ssb, dto);
				}

				out.println(ssb.toString());
			%>
		</table>


		总记录:<%=count%>&nbsp;&nbsp;&nbsp;&nbsp;以上是所有结果 <br> <br>
		<br>

		<form id="nextPage" action="coinLogs.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>"> <input
				type="hidden" name="page" value="<%=p + 1%>">
		</form>

		<form id="prePage" action="coinLogs.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>"> <input
				type="hidden" name="page" value="<%=p - 1%>">
		</form>
		<form name="jump" action="coinLogs.jsp" method="post">
			<a href="coinLogs.jsp?page=<%=1%>&countEvery=<%=cev%>">首页</a>&nbsp; <a
				href="coinLogs.jsp?page=<%=p - 1%>&countEvery=<%=cev%>">上一页</a>
			<%=p + "/" + pageAll%>
			<a href="coinLogs.jsp?page=<%=p + 1%>&countEvery=<%=cev%>">下一页</a>&nbsp;

			<a href="coinLogs.jsp?page=<%=pageAll%>&countEvery=<%=cev%>">末页</a>
			&nbsp;&nbsp;<input type="hidden" name="countEvery" value="<%=cev%>">
			<input type="text" name="page" value="<%=p%>"> <a
				href="javascript:jump.submit();">go</a>
		</form>

		<a href="menu.jsp"> 返回</a> <br>

	</div>
	</center>
</body>
</html>
