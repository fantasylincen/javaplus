<%@page import="cn.vgame.a.gen.dto.MongoGen.YinShangDto"%>
<%@page
	import="cn.vgame.a.gen.dto.MongoGen.YinShangDao.YinShangDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.YinShangDao"%>
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

	<div data-demo-html="true" style="width: 95%; ">
		<%
			
		%>
		<table border="1">
			<thead>
				<tr>
					<th>银商ID</th>
					<th>昵称</th>
					<th>金豆</th>
					<th>银行</th>
					<th>密码</th>
				</tr>
			</thead>

			<%!void print(StringBuffer sb, YinShangDto dto) {

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(dto.getId());
		sb.append("</td>");

		sb.append("<td>");

		Role role = Server.getRole(dto.getRoleId());

		sb.append("<a href=\"coinLog.jsp?roleId=" + role.getId()
				+ "&page=200000000&countEvery=14\">" + role.getNick() + "</a>");

		sb.append("</td>");

		sb.append("<td>");

		sb.append(role.getCoin());

		sb.append("</td>");

		sb.append("<td>");

		sb.append(role.getBankCoin());

		sb.append("</td>");

		sb.append("<td>");
		sb.append(dto.getPassword());
		sb.append("</td>");

		sb.append("</tr>");

	}%>


			<%
				StringBuffer ssb = new StringBuffer();

				YinShangDao dao = Daos.getYinShangDao();

				YinShangDtoCursor find = dao.find();

				for (YinShangDto dto : find) {
					print(ssb, dto);
				}

				out.println(ssb.toString());
			%>

		</table>
		<br> <br> <br>
		<form method="post" id="form2" name="form2" action="addYinShang">
			<label>银商ID</label> <input type="text" name="id"> <label>银商角色ID</label>
			<input type="text" name="roleId"> <label>银商密码</label> <input
				type="text" name="password"> <input type="submit"
				value="添加银商">
		</form>
		<form method="post" id="form3" name="form3" action="addYinShangCoin">
			<label>银商ID</label> <input type="text" name="id"> <label>金豆数量</label>
			<input type="text" name="coin"> <input type="submit"
				value="增加银商金豆">
		</form>


	</div>
</body>
</html>
