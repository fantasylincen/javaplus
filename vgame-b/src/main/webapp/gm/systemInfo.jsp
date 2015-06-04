<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hp.hpl.sparta.Document"%>
<%@page import="cn.vgame.share.GameException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java"
	import="cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.RoleDto"%>
<%@ page language="java" import="cn.javaplus.util.Util"%>


<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java"
	import="cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor"%>
<%@ page language="java" import="cn.vgame.b.system.OnlineCounter"%>
<%@ page language="java" import="cn.javaplus.util.Util"%>


<html>
<head>
<%@include file="head.html"%>
</head>
<body>

	<a href="systemInfo.jsp">刷新</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="menu.jsp"> 返回</a>
	<%
		RoleDao dao = Daos.getRoleDao();
		int playerCount = dao.find().getCount();

	%>
	<form id="setSystemInfo" action="setSystemInfo" method="post">
		<h2>系统信息</h2>
		<table class="bordered">
			<thead>
				<tr>
					<th>系统属性</th>
					<th>值</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>在线人数(近10分钟登陆的用户)</td>
					<td><%=OnlineCounter.getOnlineSize()%></td>
				</tr>

				<tr>
					<td>总注册人数</td>
					<td><%=playerCount%></td>
				</tr>

				<%
					SimpleDateFormat FORMAT = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
				%>
				<tr>
					<td>服务器时间</td>
					<td><%=FORMAT.format(new Date(System.currentTimeMillis()))%></td>
				</tr>

			</tbody>
		</table>

		<br> <input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> <br>
	</form>
</body>
</html>
