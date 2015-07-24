<%@page import="cn.javaplus.collections.map.Maps"%>
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
	<div>
		<%
			String roleId = request.getParameter("roleId");
			String s = request.getParameter("showBackButton");
			boolean showBackButton = s == null;
			
			String nick = Server.getRole(roleId).getNick();
			
		 %>
		<h2>[<%=nick %>] 的金豆记录</h2>
		<table class="bordered">
			<thead>
				<tr>
					<th>时间</th>
					<th>FROM&nbsp;&nbsp;&gt;&gt;&gt;&nbsp;&nbsp;TO</th>
					<th>金币增量</th>
					<th>说明</th>
				</tr>
			</thead>

			<%!void print(StringBuffer sb, CoinLogDto dto) {

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(dto.getTime());
		sb.append("</td>");
		
		sb.append("<td>");
		
		String from = dto.getFrom();
		if("send coin".equals(dto.getDsc())) {
			from = Server.getRole(from).getNick();
		}
		
		Map<String, String> map = Maps.newHashMap();
		map.put("bank", "<font color=\"#1A6BE6\">银行</font>");
		map.put("system", "<font color=\"#D52B4D\">系统</font>");
		map.put("market", "<font color=\"#3CC43C\">商店</font>");
		
		if(map.get(from) != null)
		 	from = map.get(from);
		
		sb.append(from + "&nbsp;&nbsp;&gt;&gt;&gt;&nbsp;&nbsp;" + Server.getRole(dto.getTo()).getNick());

		sb.append("</td>");

		sb.append("<td>");
		sb.append(dto.getCoin());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(getDsc(dto));
		sb.append("</td>");
		

		sb.append("</tr>");

	}%>


<%!

	public String getDsc(CoinLogDto dto) {
		String dsc = dto.getDsc();
		if("receive coin".equals(dto.getDsc()) ) {
			return "领取金币";
		}
		if("gm add".equals(dto.getDsc()) ) {
			return "GM平台扣金币";
		}
		if("gm reduce".equals(dto.getDsc()) ) {
			return "GM平台加金币";
		}
		if("send coin".equals(dto.getDsc()) ) {
			return "赠送金豆";
		}
		return dsc;
	}
 %>
			<%
				int cev = 14;
				StringBuffer ssb = new StringBuffer();

				CoinLogDao dao = Daos.getCoinLogDao();

				String pg = request.getParameter("page");
				
				String cv = request.getParameter("countEvery");

				CoinLogDtoCursor find = dao.findByFromToFuzzy("*" + roleId + "*");
				Log.d("roleId", "[" + roleId + "]");
				
				int p = pg == null ? 0 : new Integer(pg);
				int countEvery = cv == null ? cev : new Integer(cv);

				if (p < 1)
					p = 1;

				if (countEvery > 2000 || countEvery <= 0)
					throw new ErrorResult("countEvery must < 2000 and > 0 ").toException();

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
				if(skip > 0) {
					find.skip(skip);
				}
				find.limit(countEvery);

				for (CoinLogDto dto : find) {
					print(ssb, dto);
				}

				out.println(ssb.toString());
			%>
		</table>





			 		总记录:<%=count %>&nbsp;&nbsp;&nbsp;&nbsp;以上是所有结果
		<br>
<br><br>

		<form id="nextPage" action="coinLog.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>&roleId=<%=roleId%>"> <input
				type="hidden" name="page" value="<%=p + 1%>">
		</form>

		<form id="prePage" action="coinLog.jsp" method="post">
			<input type="hidden" name="countEvery" value="<%=cev%>&roleId=<%=roleId%>"> <input
				type="hidden" name="page" value="<%=p - 1%>">
		</form>
		<form name="jump" action="coinLog.jsp" method="post">
			<a href="coinLog.jsp?page=<%=1%>&countEvery=<%=cev%>&roleId=<%=roleId%>">首页</a>&nbsp;
			<a href="coinLog.jsp?page=<%=p - 1%>&countEvery=<%=cev%>&roleId=<%=roleId%>">上一页</a>
			<%=p + "/" + pageAll%>
			<a href="coinLog.jsp?page=<%=p + 1%>&countEvery=<%=cev%>&roleId=<%=roleId%>">下一页</a>&nbsp;

			<a href="coinLog.jsp?page=<%=pageAll%>&countEvery=<%=cev%>&roleId=<%=roleId%>">末页</a>
			&nbsp;&nbsp;<input type="hidden" name="countEvery" value="<%=cev%>&roleId=<%=roleId%>">
			<input type="text" name="page" value="<%=p%>"> <a
				href="javascript:jump.submit();">go</a>
		</form>
		<%
			if(showBackButton) {
		 %>
		 		<a href="setUser.jsp?roleId=<%=roleId%>"> 返回</a>
			<%
			}
		 %>
			 <br>
		
	</div>
</body>
</html>
