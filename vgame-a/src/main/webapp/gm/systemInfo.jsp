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
</head>
<body>

	<a href="systemInfo.jsp">刷新</a> &nbsp;&nbsp;&nbsp;&nbsp;
	<a href="menu.jsp"> 返回</a>
	<%
		RoleDao dao = Daos.getRoleDao();
		int playerCount = dao.find().getCount();

		Turntable t = Turntable.getInstance();
		Controller tc = t.getController();
		
	
		RoleDtoCursor c = dao.find();
		long all = 0;
		long allBank = 0;
		long allJiangQuan = 0;
		long allRecharge = 0;
		for(RoleDto d : c) {
			if(!d.getId().startsWith("r")) { // 非机器人
				all += d.getCoin() ;
				allBank += d.getBankCoin();
				allJiangQuan += d.getJiangQuan();
				allRecharge += d.getRechargeHistory();
			}
		}
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
				<%
					SimpleDateFormat FORMAT = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
				%>
				<tr>
					<td>服务器时间</td>
					<td><%=FORMAT.format(new Date(System.currentTimeMillis()))%></td>
				</tr>
				<tr>
					<td>在线人数/注册人数</td>
					<td><%=OnlineCounter.getOnlineSize()%>/<%=playerCount%></td>
				</tr>

				<tr>
					<td>开奖次数(今日/历史)</td>
					<td><%=t.getGenerateTimesToday() + "/"
					+ t.getGenerateTimesHistory()%></td>
				</tr>
				
				<tr>
					<td>场外携带金币</td>
					<td><%=all%></td>
				</tr>
				
				<tr>
					<td>银行存量</td>
					<td><%=allBank%></td>
				</tr>
				<tr>
					<td>累计充值</td>
					<td><%=allRecharge%>&nbsp;金豆</td>
				</tr>
				<tr>
					<td>奖券</td>
					<td><%=allJiangQuan%></td>
				</tr>

				<tr>
					<td>彩金</td>
					<td><%=Turntable.getInstance().getCaiJin()%></td>
				</tr>

				<tr>
					<td>库存</td>
					<td><%=tc.getKuCun()%>&nbsp;&nbsp;&nbsp;&nbsp;<a href="setKuCun.jsp">修改</a>
					</td>
				</tr>
				<tr>
					<td>库存每轮衰减比例(大于0才衰减)</td>
					<td><input name="kuCunShuaiJian" value="<%=tc.getKuCunShuaiJian()%>">
					</td>
				</tr>
				<tr>
					<td>库存每轮衰减值(大于0才衰减)</td>
					<td><input name="kuCunShuaiJianZhi" value="<%=tc.getKuCunShuaiJianZhi()%>">
					</td>
				</tr>
				<tr>
					<td>今日库存投放量/历史库存投放量</td>
					<%
						long ssHistory = Server.getKeyValueForever().getLong("KU_CUN_TOU_FANG_LIANG");
						long ssToday = Server.getKeyValueDaily().getLong("KU_CUN_TOU_FANG_LIANG");
						
					 %>
					<td><%=ssToday %>/<%=ssHistory %>
					</td>
				</tr>
				
				
				<tr>
					<td>今日衰减量/历史衰减量</td>
					<%
						long sHistory = Server.getKeyValueForever().getLong("KU_CUN_SHUAI_JIAN_LIANG");
						long sToday = Server.getKeyValueDaily().getLong("KU_CUN_SHUAI_JIAN_LIANG");
					 %>
					<td><%=sToday %>/<%=sHistory %>
					</td>
				</tr>
				

			</tbody>
		</table>

		<br> <input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> <br>
	</form>
</body>
</html>
