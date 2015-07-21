<%@page import="cn.javaplus.collections.map.Maps"%>
<%@page import="cn.javaplus.collections.set.Sets"%>
<%@page import="cn.vgame.a.turntable.generator.SwitchWithOutRobot"%>
<%@page import="com.google.common.collect.Lists"%>
<%@page import="cn.vgame.a.turntable.generator.PZResultGenerator.Xs"%>
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
<meta http-equiv="refresh" content="5">
<%@include file="head.html"%>


</head>
<body>

	<%=Turntable.getInstance().getCd() / 1000%>秒

	<table border="2">
		<thead>
			<tr>
				<th>玩家昵称</th>
				<th>金豆</th>
				<th>银行</th>
				<th>走兽</th>
				<th>银鲨</th>
				<th>金鲨</th>
				<th>飞禽</th>
				<th>燕子</th>
				<th>鸽子</th>
				<th>孔雀</th>
				<th>老鹰</th>
				<th>狮子</th>
				<th>熊猫</th>
				<th>猴子</th>
				<th>兔子</th>
			</tr>
		</thead>


		<tbody>

			<%
				StringBuilder sbba = new StringBuilder();
				SwitchAll ss = Turntable.getInstance().getSwitchs();
				Set<String> d = ss.getAll();
				for (String roleId : d) {
					if (Server.getRobotManager().isRobot(roleId)) {
						continue;
					}
					ISwitchs s = ss.get(roleId);
					print(roleId, s, sbba);
				}

				printBiChu(sbba);
				printProfit(sbba);

				out.println(sbba);
			%>

			<%!private static void printBiChu(StringBuilder sb) {

		List<String> types = TurntableUtil.getAllTypes();
		sb.append("<tr>");
		sb.append("<td><font color=\"#FF6600\">高级功能</font></td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");

		for (String type : types) {

			sb.append("<td>");
			if ("AD".contains(type)) {
				sb.append("-");
			} else {

				int id = findId(type);

				Turntable t = Turntable.getInstance();
				if (t.isMustGenerate(id)) {
					sb.append("<a href=\"mustGenerate?id=" + id + "\">取消</a>");
				} else {
					sb.append("<a href=\"mustGenerate?id=" + id + "\">必出</a>");
				}

			}
			sb.append("</td>");

		}
		sb.append("</tr>");

	}%>

			<%!private static void printProfit(StringBuilder sb) {

		List<String> types = TurntableUtil.getAllTypes();
		sb.append("<tr>");
		sb.append("<td><font color=\"#0066FF\">系统盈利</font></td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");
		Map<String, Long> map = getXss(Turntable.getInstance().getSwitchs());
		for (String type : types) {

			sb.append("<td>");
			Long a = map.get(type);
			if (a != null) {
				if (a < 0)
					sb.append("<font color=\"#007700\">" + a + "</font>");
				else if (a == 0)
					sb.append(a);
				else
					sb.append("<font color=\"#990000\">+" + a + "</font>");
			} else
				sb.append("-");

			sb.append("</td>");

		}
		sb.append("</tr>");

	}%>

			<%!public static int findId(String type) {
		Xml a = Server.getXml();

		Sheet sheet = a.get("weights");
		Row r = sheet.findFirst("type", type);
		return r.getInt("id");
	}%>

			<%!public static Map<String, Long> getXss(SwitchAll switchs) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		List<Row> all = sheet.getAll();

		Map<String, Long> map = Maps.newHashMap();

		SwitchWithOutRobot sr = new SwitchWithOutRobot(switchs);

		long roleCoinAll = TurntableUtil.getCountAllWithOutAAndD(sr);

		for (Row r : all) {
			String type = r.get("type");
			int x = Turntable.getInstance().getX(type);

			if (Sets.newHashSet("A", "D").contains(type)) {
				continue;
			}

			long coin = switchs.getByTypeWithOutRobot(type);
			long profit = roleCoinAll - x * coin;
			map.put(type, profit);
		}

		return map;
	}%>
			<%!private static void print(String id, ISwitchs s, StringBuilder sb) {
		Role role = Server.getRole(id);

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(role.getNick());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(role.getCoin());
		sb.append("</td>");
		sb.append("<td>");
		sb.append(role.getBankCoin());
		sb.append("</td>");

		List<String> types = TurntableUtil.getAllTypes();
		for (String type : types) {
			sb.append("<td>");
			sb.append(TurntableUtil.getByType(s, type));
			sb.append("</td>");
		}

		sb.append("</tr>");
	}%>

		</tbody>
	</table>

	<%
		RoleDao dao = Daos.getRoleDao();
		int playerCount = dao.find().getCount();

		Turntable t = Turntable.getInstance();
		Controller tc = t.getController();

		RoleDtoCursor c = dao.find();
		long all = 0;
		long allBank = 0;
		long allJiangQuan = 0;
		long allTrade = 0;
		for (RoleDto dd : c) {
			if (!dd.getId().startsWith("r")) { // 非机器人
				all += dd.getCoin();
				allBank += dd.getBankCoin();
				allJiangQuan += dd.getJiangQuan();
			}
		}
	%>
	<form id="setSystemInfo" action="setSystemInfo" method="post">
		<table class="bordered">
			<thead>
				<tr>
					<th>系统属性</th>
					<th>值</th>
				</tr>
			</thead>
			<tbody>
				`
				<%
				SimpleDateFormat FORMAT = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
			%>
				<tr>
					<td>服务器时间</td>
					<td><%=FORMAT.format(new Date(System.currentTimeMillis()))%></td>
				</tr>
				<tr>
					<td>下注人数/注册人数</td>
					<td><%=OnlineCounter.getOnlineSize()%>/<%=playerCount%></td>
				</tr>

				<tr>
					<td>开奖次数(今日/历史)</td>
					<td><%=t.getGenerateTimesToday() + "/"
					+ t.getGenerateTimesHistory()%></td>
				</tr>

				<tr>
					<td>今日新增用户/历史创建用户</td>
					<td><%=Server.getKeyValueDaily().getLong("CREATE_ROLE_COUNT")%>/<%=Server.getKeyValueForever().getLong("CREATE_ROLE_COUNT")%></td>
				</tr>
				<tr>
					<td>今日登陆/历史登陆</td>
					<td><%=Server.getKeyValueDaily().getLong("ENTER_GAME_TIMES")%>/<%=Server.getKeyValueForever().getLong("ENTER_GAME_TIMES")%></td>
				</tr>
				<tr>
					<td>今日充值/历史充值</td>
					<td><%=Server.getKeyValueDaily().getLong("SYSTEM_RECHARGE")%>/<%=Server.getKeyValueForever().getLong("SYSTEM_RECHARGE")%></td>
				</tr>
				<tr>
					<td>今日交易量/历史交易量</td>
					<td><%=Server.getKeyValueDaily().getLong("TRADE_VOL")%>/<%=Server.getKeyValueForever().getLong("TRADE_VOL")%></td>
				</tr>

				<tr>
					<td>场外金币+银行</td>
					<td><font color="#FF0000"><%=all%> + <%=allBank%> = <%=allBank + all%>
							= <%=(allBank + all) / 10000%>W</font>
					</td>
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
					<td><%=tc.getKuCun()%>&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="setKuCun.jsp">修改</a></td>
				</tr>
				<tr>
					<td>库存每轮衰减比例(大于0才衰减)</td>
					<td><input name="kuCunShuaiJian"
						value="<%=tc.getKuCunShuaiJian()%>"></td>
				</tr>
				<tr>
					<td>库存每轮衰减值(大于0才衰减)</td>
					<td><input name="kuCunShuaiJianZhi"
						value="<%=tc.getKuCunShuaiJianZhi()%>"></td>
				</tr>
				<tr>
					<td>今日库存投放量/历史库存投放量</td>
					<%
						long ssHistory = Server.getKeyValueForever().getLong(
								"KU_CUN_TOU_FANG_LIANG");
						long ssToday = Server.getKeyValueDaily().getLong(
								"KU_CUN_TOU_FANG_LIANG");
					%>
					<td><%=ssToday%>/<%=ssHistory%></td>
				</tr>


				<tr>
					<td>今日衰减量/历史衰减量</td>
					<%
						long sHistory = Server.getKeyValueForever().getLong(
								"KU_CUN_SHUAI_JIAN_LIANG");
						long sToday = Server.getKeyValueDaily().getLong(
								"KU_CUN_SHUAI_JIAN_LIANG");
					%>
					<td><%=sToday%>/<%=sHistory%></td>
				</tr>


			</tbody>
		</table>

		<br> <input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> &nbsp;&nbsp;&nbsp;&nbsp;<a
			href="systemInfo.jsp">刷新</a> &nbsp;&nbsp;&nbsp;&nbsp;<a
			href="../yinshang/yinshang.jsp">银商管理</a> <br>
	</form>
</body>
</html>
