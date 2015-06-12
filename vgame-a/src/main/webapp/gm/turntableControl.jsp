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

	<a href="turntableControl.jsp">刷新</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> 
	<%
 		Map<Integer, Integer> weightsAdd = getWeightsAdd(request);
 			if (weightsAdd != null)
 		Turntable.getInstance().updateWeightsAdd(weightsAdd);
 	%>

	<%!private static Map<Integer, Integer> getWeightsAdd(
			HttpServletRequest request) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		HashMap<Integer, Integer> map = Maps.newHashMap();
		for (Row row : all) {
			String id = row.get("id");
			String w = request.getParameter("weightAdd:" + id);
			if (w == null)
				return null;
			map.put(new Integer(id), new Integer(w));
		}
		return map;
	}%>


	<%!private static void print(Row row, StringBuilder sb) {
		Turntable t = Turntable.getInstance();
		String type = row.get("type");
		String dsc = row.get("dsc");
		int x = t.getX(type);

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(type);
		sb.append("</td>");

		sb.append("<td>");
		sb.append(dsc);
		sb.append("</td>");

		sb.append("<td>");
		sb.append(x);
		sb.append("</td>");

		sb.append("<td>");
		sb.append(t.getCountThisTime(type));
		sb.append("</td>");

		sb.append("<td>");
		sb.append(t.getCountHistory(type));
		sb.append("</td>");

		sb.append("<td>");

		if ("AD".contains(type)) {
			sb.append("");
		} else {

			int id = findId(type);

			if (t.isMustGenerate(id)) {
				sb.append("<a href=\"mustGenerate?id=" + id + "\">取消</a>");
			} else {
				sb.append("<a href=\"mustGenerate?id=" + id + "\">本轮必出</a>");
			}
		}

		sb.append("</td>");

		sb.append("</tr>");
	}%>

	<%!public static int findId(String type) {
		Xml a = Server.getXml();

		Sheet sheet = a.get("weights");
		Row r = sheet.findFirst("type", type);
		return r.getInt("id");
	}%>






	<h2>下注信息</h2>
	<table border="2">
		<thead>
			<tr>
				<th>类型ID</th>
				<th>类型名</th>
				<th>倍率</th>
				<th>本轮下注人数</th>
				<th>历史下注人数</th>
				<th>高级功能</th>
			</tr>
		</thead>



		<tbody>

			<%
				Xml xml = Server.getXml();
					Sheet sheet = xml.get("x");
					List<Row> all = sheet.getAll();
					StringBuilder sb = new StringBuilder();
					for (Row row : all) {
						print(row, sb);
					}

					out.println(sb);
			%>

		</tbody>
	</table>


	<br>
	<br>

	<h2>机器人信息</h2>
	<table border="2">
		<thead>
			<tr>
				<th>ID</th>
				<th>昵称</th>
				<th>金币</th>
				<th>今日金币输入</th>
				<th>今日金币输出</th>
				<th>历史金币输入</th>
				<th>历史金币输出</th>
				<th>本轮下注量</th>
				<th>是否下注金鲨</th>
				<th>高级功能</th>
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
				+ robot.getId() + "</a>" );
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

	/* 	if (robot.isCommitJinSha()) {
			if (manager.isCaiJinMustTo(robot.getId())) {
				sb.append("<a href=\"caiJinMustTo\">取消</a>");
			} else {
				sb.append("<a href=\"caiJinMustTo?id=" + robot.getId()
						+ "\">本轮必出金鲨, 并把彩金给他</a>");
			}
		} else {
			sb.append("-");
		} */
		sb.append("-");
		
		sb.append("</td>");

		sb.append("</tr>");
	}%>

		</tbody>
	</table>

	<br>
	<br>

	<form id="updateWeightAdd" action="turntableControl.jsp" method="post">

		<h2>轮盘信息</h2>
		<!-- 	<table class="bordered"> -->
		<table border="2">
			<thead>
				<tr>
					<th>ID</th>
					<th>类型名</th>
					<th>预设权重</th>
					<th>权重增量</th>
					<th>实际权重</th>
				</tr>
			</thead>
			<tbody>

				<%
					sheet = xml.get("weights");
					all = sheet.getAll();
					sb = new StringBuilder();
					for (Row row : all) {
						print2(row, sb);
					}
					out.println(sb);
				%>
				<%!private static void print2(Row row, StringBuilder sb) {
		int id = row.getInt("id");
		String dsc = row.get("dsc");
		
		int weight = Turntable.getInstance().getWeight(row);

		Turntable t = Turntable.getInstance();
		int weightAdd = t.getWeightAdd(id);

		sb.append("<tr>");
		sb.append("<td>");
		sb.append(id);
		sb.append("</td>");

		sb.append("<td>");
		sb.append(dsc);
		sb.append("</td>");

		sb.append("<td>");
		sb.append(weight);
		sb.append("</td>");

		sb.append("<td>");
		sb.append("<input name=\"weightAdd:" + id + "\" value=\"" + weightAdd
		//		+ "\" style=\"border-left:0px;border-top:0px;border-right:0px;border-bottom:0px\">");
				+ "\"\">");
		sb.append("</td>");

		sb.append("<td>");
		sb.append(weightAdd + weight);
		sb.append("</td>");

		sb.append("</tr>");
	}%>

			</tbody>
		</table>
		<br> <a href="javascript:updateWeightAdd.submit();"> 修改 </a> &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> <br>
		
	</form>


</body>
</html>
