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
</head>
<body>

	<a href="setKuCun.jsp">刷新</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="systemInfo.jsp"> 返回</a>
	<%
		Turntable t = Turntable.getInstance();
		Controller tc = t.getController();
	%>

	<form id="setKuCun" action="setKuCun" method="post">
		当前库存: <input type="text" name="kuCun" value="<%=tc.getKuCun()%>" />
		<br> <input type="submit" value="修改" />
	</form>
</body>
</html>
