<%@page import="cn.vgame.a.turntable.TurntableUtil"%>
<%@page import="cn.vgame.a.turntable.swt.ISwitchs"%>
<%@page import="cn.vgame.a.turntable.swt.SwitchAll"%>
<%@page import="cn.vgame.a.robot.Robot"%>
<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.robot.RobotManager"%>
<%@page import="cn.javaplus.collections.set.Sets"%>
<%@page import="com.hp.hpl.sparta.Document"%>
<%@page import="cn.vgame.share.GameException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.Daos"%>

<%@ page language="java" import="java.net.UnknownHostException"%>
<%@ page language="java" import="java.util.HashMap"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="java.util.Map"%>

<%@ page language="java" import="javax.servlet.http.HttpServletRequest"%>
<%@ page language="java" import="javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" import="javax.servlet.http.HttpSession"%>

<%@ page language="java" import="cn.javaplus.collections.map.Maps"%>
<%@ page language="java" import="cn.javaplus.excel.Row"%>
<%@ page language="java" import="cn.javaplus.excel.Sheet"%>
<%@ page language="java" import="cn.vgame.a.Server"%>
<%@ page language="java" import="cn.vgame.share.Xml"%>
<%@ page language="java"
	import="cn.vgame.a.gen.dto.MongoGen.MongoDbProperties"%>
<%@ page language="java" import="cn.vgame.a.turntable.Turntable"%>

<html>
<head>
<%@include file="head.html"%>
</head>
<body>

	<div style="width: 95%; ">

		<a href="turntableControl.jsp">刷新</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
			href="menu.jsp"> 返回</a>

		<h2>机器人信息</h2>
		<table border="2">
			<thead>
				<tr>
					<th>ID</th>
					<th>昵称</th>
					<th>金币</th>
					<th>今日盈利</th>
					<th>今日下注</th>
					<th>历史盈利</th>
					<th>历史下注</th>
					<th>本轮下注量</th>
					<th>是否下注金鲨</th>
				</tr>
			</thead>



			<tbody>

				<%
					RobotManager manager = Server.getRobotManager();
					StringBuilder sbb = new StringBuilder();
					for (Robot role : manager.getRobots()) {
						printRobot(role, sbb);
					}

					out.println(sbb);
				%>



				<%!private static void printRobot(Robot robot, StringBuilder sb) {

		RobotManager manager = Server.getRobotManager();
		sb.append("<tr>");

		sb.append("<td>");
		sb.append("<a href=\"setUser.jsp?roleId=" + robot.getId() + "\">"
				+ robot.getId() + "</a>");
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getNick());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCoin());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCoinInToday());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCoinOutToday());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCoinInHistory());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCoinOutHistory());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.getCommitAll());
		sb.append("</td>");

		sb.append("<td>");
		sb.append(robot.isCommitJinSha() ? "是" : "否");
		sb.append("</td>");

		sb.append("<td>");

	}%>

			</tbody>
		</table>

		<br> <br>
	</div>
</body>
</html>
