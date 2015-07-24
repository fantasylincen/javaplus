<%@page import="cn.vgame.a.turntable.generator.ProfitCalc.Xs"%>
<%@page import="cn.vgame.a.turntable.generator.ProfitCalc"%>
<%@page import="cn.javaplus.collections.map.Maps"%>
<%@page import="cn.javaplus.collections.set.Sets"%>
<%@page import="cn.vgame.a.turntable.generator.SwitchWithOutRobot"%>
<%@page import="com.google.common.collect.Lists"%>
<%@page import="cn.javaplus.excel.Row"%>
<%@page import="cn.javaplus.excel.Sheet"%>
<%@page import="cn.vgame.share.Xml"%>
<%@page import="cn.vgame.a.account.Role"%>
<%@page import="cn.vgame.a.turntable.swt.SwitchAll"%>
<%@page import="cn.vgame.a.turntable.TurntableUtil"%>
<%@page import="cn.vgame.a.turntable.swt.ISwitchs"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDto"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao"%>
<%@page import="cn.vgame.a.Server"%>
<%@page import="cn.vgame.a.turntable.Turntable.Controller"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@ page language="java" import="cn.vgame.a.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java"
	import="cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor"%>
<%@ page language="java" import="cn.vgame.a.turntable.Turntable"%>
<%@ page language="java" import="cn.vgame.a.system.OnlineCounter"%>
<%@ page language="java" import="cn.javaplus.util.Util"%>


<html>
<head>
<%@include file="head.html"%>
<title>显示重复昵称</title>
</head>
<body>
	<%
		RoleDao dao = Daos.getRoleDao();

		RoleDtoCursor c = dao.find();
		Set<String> set = Sets.newHashSet();
		int a = 0;

		for (RoleDto dd : c) {
			if (dd.getId().startsWith("VC10006")) {
				set.add(dd.getNick());
			}
		}/*
		out.println(set.size() + set.toString());
		out.println("<br>");

		c = dao.find();
		for (RoleDto dd : c) {

			//out.println(dd.getNick() + "-----------------<br>");
			if (dd.getId().startsWith("VC10005")
					&& set.contains(dd.getNick())) {
				out.println(dd.getNick() + " &nbsp;" + a++ + dd.getId()
						+ " ; " + (dd.getCoin() + dd.getBankCoin()) + ";" + dd.getRechargeHistory());
				out.println("<br>");

				if (dd.getCoin() + dd.getBankCoin() == 0) {
					Daos.getRoleDao().delete(dd);
				}
			}
		}*/
	%>

</body>
</html>
