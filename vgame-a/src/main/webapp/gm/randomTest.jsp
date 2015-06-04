<%@page import="cn.vgame.share.Xml"%>
<%@page import="cn.vgame.a.turntable.TurntableUtil"%>
<%@page import="cn.javaplus.excel.Row"%>
<%@page import="cn.javaplus.excel.Sheet"%>
<%@page import="cn.vgame.a.turntable.Result"%>
<%@page import="cn.vgame.a.turntable.PlayResult"%>
<%@page import="cn.javaplus.collections.counter.Counter"%>
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

<%@ page language="java" import="cn.vgame.a.Server"%>
<%@ page language="java" import="cn.vgame.a.account.Role"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@ page language="java"
	import="cn.vgame.a.gen.dto.MongoGen.MongoDbProperties"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.RoleDto"%>
<%@ page language="java" import="cn.javaplus.log.*"%>






<%@ page language="java" import="cn.javaplus.log.Log"%>
<%@ page language="java" import="cn.javaplus.util.*"%>
<%@ page language="java" import="java.util.List"%>
<%@ page language="java" import="cn.vgame.a.gm.test.TestRole"%>
<%@ page language="java" import="cn.vgame.a.gm.test.SwitchTest"%>
<%@ page language="java" import="cn.vgame.a.turntable.Turntable"%>
<%@ page language="java" import="cn.javaplus.collections.list.Lists"%>


<html>
<head>
<title>随机测试</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>

	<p>
		<span style="font-size:18px;"><strong>随机测试</strong> </span>
	</p>
	<p>
		<span style="font-size:18px;"><strong>
				<hr /> <br /> </strong> </span>
	</p>
	<p>
		<span style="font-size:18px;"><strong> </strong> </span>
	</p>



	<%
		int coinStart = 100000000;
		int times = 10000;
		int countMin = 1;
		int countMax = 6;
		int valueMin = 100;
		int valueMax = 10000;
		TestRole role = new TestRole();
		role.setCoin(coinStart);

		long coin = role.getCoin();
		session.setAttribute("role", role);
		int allUse = 0;

		Counter<String> counts = new Counter<String>();

		for (int i = 0; i < times; i++) {
			SwitchTest s = new SwitchTest();
			randomCount(s, countMin, countMax, valueMin, valueMax);
			Turntable t = Turntable.getInstance();
			PlayResult rst = t.playOnceWithOutTime(role, s);

			List<PlayResult.Result> rss = rst.getResults();

			for (PlayResult.Result row : rss) {
				counts.add(row.getType());
			}

			allUse += TurntableUtil.getCountAll(s);
		}

		long coinEnd = role.getCoin();
	%>


	测试次数&nbsp;&nbsp;&nbsp;<%=times%>
	<br>
	<br> 初始金币&nbsp;&nbsp;&nbsp;<%=coinStart%>
	<br>
	<br> 最终金币&nbsp;&nbsp;&nbsp;<%=coinEnd%>
	<br>
	<br> 总计压注&nbsp;&nbsp;&nbsp;<%=allUse%>
	<br>
	<br> 每次随机压注项数&nbsp;&nbsp;&nbsp;<%=countMin + "-" + countMax%>
	<br>
	<br> 每项随机压注分数&nbsp;&nbsp;&nbsp;<%=valueMin + "-" + valueMax%>
	<br>

	<%
		for (String k : counts.keySet()) {
			int count = counts.get(k);
			Sheet sheet = Xml.getSheet("x");
			Row r = sheet.get(k);
			String dsc = r.get("dsc");
	%>
	<br>
	<%=dsc%>&nbsp;&nbsp;&nbsp;<%=count%>次 
	<br>

	<%
		}
	%>

	<br>
	<br>
	<br>
	<a href="randomTest.jsp">重新测试</a>



	<%!public void randomCount(SwitchTest action, int countMin, int countMax,
			int valueMin, int valueMax) {
		int count = Util.Random.get(countMin, countMax); // 随机压 1 - 6种
		List<Integer> indexs = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
				10, 11);
		Util.Collection.upset(indexs);
		List<Integer> selected = Util.Collection.sub(indexs, count);

		for (int i = 0; i < selected.size(); i++) {
			int s = selected.get(i);
			selectByRandomValue(s, action, valueMin, valueMax);
		}

	}%>

	<%!void selectByRandomValue(int s, SwitchTest action, int valueMin,
			int valueMax) {
		int count = Util.Random.get(valueMin, valueMax); // 随机压分: 10分 - 1000分

		if (s == 0) {
			action.setA(count);
		}
		if (s == 1) {
			action.setB(count);
		}
		if (s == 2) {
			action.setC(count);
		}
		if (s == 3) {
			action.setD(count);
		}
		if (s == 4) {
			action.setE(count);
		}
		if (s == 5) {
			action.setF(count);
		}
		if (s == 6) {
			action.setG(count);
		}
		if (s == 7) {
			action.setH(count);
		}
		if (s == 8) {
			action.setI(count);
		}
		if (s == 9) {
			action.setJ(count);
		}
		if (s == 10) {
			action.setK(count);
		}
		if (s == 11) {
			action.setL(count);
		}
	}%>
</body>
</html>
