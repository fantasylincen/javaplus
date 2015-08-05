<%@page import="cn.vgame.a.message.Message"%>
<%@page import="cn.vgame.a.message.MessageManager"%>
<%@page import="cn.vgame.a.turntable.History"%>
<%@page import="cn.vgame.a.turntable.GetHistoryResult"%>
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
<meta http-equiv="refresh" content="5">
<%@include file="head.html"%>

<style type="text/css">
.dark-matter {
margin-left: auto;
margin-right: auto;
max-width: 500px;
background: #555;
padding: 20px 30px 20px 30px;
font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
color: #D3D3D3;
text-shadow: 1px 1px 1px #444;
border: none;
border-radius: 5px;
-webkit-border-radius: 5px;
-moz-border-radius: 5px;
}
.dark-matter h1 {
padding: 0px 0px 10px 40px;
display: block;
border-bottom: 1px solid #444;
margin: -10px -30px 30px -30px;
}
.dark-matter h1>span {
display: block;
font-size: 11px;
}
.dark-matter label {
display: block;
margin: 0px 0px 5px;
}
.dark-matter label>span {
float: left;
width: 20%;
text-align: right;
padding-right: 10px;
margin-top: 10px;
font-weight: bold;
}
.dark-matter input[type="text"], .dark-matter input[type="email"], .dark-matter textarea, .dark-matter select {
border: none;
color: #525252;
height: 25px;
line-height:15px;
margin-bottom: 16px;
margin-right: 6px;
margin-top: 2px;
outline: 0 none;
padding: 5px 0px 5px 5px;
width: 70%;
border-radius: 2px;
-webkit-border-radius: 2px;
-moz-border-radius: 2px;
-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
background: #DFDFDF;
}
.dark-matter select {
background: #DFDFDF url('down-arrow.png') no-repeat right;
background: #DFDFDF url('down-arrow.png') no-repeat right;
appearance:none;
-webkit-appearance:none;
-moz-appearance: none;
text-indent: 0.01px;
text-overflow: '';
width: 70%;
height: 35px;
color: #525252;
line-height: 25px;
}
.dark-matter textarea{
height:100px;
padding: 5px 0px 0px 5px;
width: 70%;
}
.dark-matter .button {
background: #FFCC02;
border: none;
padding: 10px 25px 10px 25px;
color: #585858;
border-radius: 4px;
-moz-border-radius: 4px;
-webkit-border-radius: 4px;
text-shadow: 1px 1px 1px #FFE477;
font-weight: bold;
box-shadow: 1px 1px 1px #3D3D3D;
-webkit-box-shadow:1px 1px 1px #3D3D3D;
-moz-box-shadow:1px 1px 1px #3D3D3D;
}
.dark-matter .button:hover {
color: #333;
background-color: #EBEBEB;
}
</style>

</head>
<body>
	<center>
		
		
	
		<%
		
			String gmUserId = (String)request.getAttribute("gmUserId");
			String gmUserPwd = (String)request.getAttribute("gmUserPwd");
			
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
		<div data-demo-html="true" style="width: 95%; ">
			<form id="setSystemInfo" action="setSystemInfo" method="post">

				<table border="1" data-role="table" id="table-custom-2"
					data-mode="columntoggle"
					class="ui-body-d ui-shadow table-stripe ui-responsive ui-table ui-table-columntoggle">
					<!-- <thead>
				<tr>
					<th>系统属性</th>
					<th>值</th>
				</tr>
			</thead> -->
					<tbody>
					<tr>
							<td>内存</td>
							<%
								long free = Runtime.getRuntime().freeMemory();
								long total = Runtime.getRuntime().totalMemory();
								long max = Runtime.getRuntime().maxMemory();
								double p = ((free + 0.0) / total * 100) ;
								
							 %>
							<td><%=free/1024/1024%>M /<%=total/1024/1024%>M  [<%=String.format("%.2f", p)%>%] max:<%=max/1024/1024 %>M</td>
						</tr>					

			<%-- 			<tr>
							<td>开奖次数(今日/历史)</td>
							<td><%=t.getGenerateTimesToday() + "/"
					+ t.getGenerateTimesHistory()%></td>
						</tr> --%>
						
						<tr>
							<td>今日新增/历史创建</td>
							<%
								long sendToday = Server.getKeyValueDaily().getLong("CREATE_USER_SEND_COIN");
								long sendHistory = Server.getKeyValueForever().getLong("CREATE_USER_SEND_COIN");
							 %>
							<td><%=Server.getKeyValueDaily().getLong("CREATE_ROLE_COUNT")%>/<%=Server.getKeyValueForever().getLong("CREATE_ROLE_COUNT")%> 送豆[<%=buildRmb(sendToday)%>/<%=buildRmb(sendHistory)%>]</td>
						</tr>
						<tr>
							<td>今日登陆/历史登陆</td>
							<td><%=Server.getKeyValueDaily().getLong("ENTER_GAME_TIMES")%>/<%=Server.getKeyValueForever().getLong("ENTER_GAME_TIMES")%></td>
						</tr>
						<tr>
							<td>今日充值/历史充值</td>
							<%
								long td = Server.getKeyValueDaily().getLong("SYSTEM_RECHARGE");
								long hd = Server.getKeyValueForever().getLong("SYSTEM_RECHARGE");
							 %>
							<td><%=td + "(≈¥" + td/ 1200 + ")"%>/<%=hd + "(≈¥" + hd/ 1200 + ")"%></td>
						</tr>
						<tr>
							<td>今日交易量/历史交易量</td>
							<td><%=buildRmb(Server.getKeyValueDaily().getLong("TRADE_VOL"))%>/<%=buildRmb(Server.getKeyValueForever().getLong("TRADE_VOL"))%></td>
						</tr>

						<tr>
							<td>场外金币+银行</td>
							<td><font color="#FF0000"><%=all%> + <%=allBank%> 
									= <%=(allBank + all) / 10000%>W &nbsp;:&nbsp;&nbsp; <%=buildRmb(allBank + all) %></font></td>
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
							<td><%=buildRmb(tc.getKuCun())%>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=-1000000">-100W</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=-100000">-10W</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=-10000">-1W</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=10000">+1W</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=100000">+10W</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="addKuCun?add=1000000">+100W</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td>系统盈利衰减率</td>
							<td><input name="kuCunShuaiJian"
								value="<%=tc.getKuCunShuaiJian()%>">
							</td>
						</tr>
						<tr>
							<td>系统盈利衰减值</td>
							<td><input name="kuCunShuaiJianZhi"
								value="<%=tc.getKuCunShuaiJianZhi()%>">
							</td>
						</tr>
						<tr>
							<td>今日投放量/历史投放量</td>
							<%
								long ssHistory = Server.getKeyValueForever().getLong(
										"KU_CUN_TOU_FANG_LIANG");
								long ssToday = Server.getKeyValueDaily().getLong(
										"KU_CUN_TOU_FANG_LIANG");
							%>
							<td><%=buildRmb(ssToday)%>&nbsp;/&nbsp;<%=buildRmb(ssHistory)%></td>
						</tr>


						<tr>
							<td>今日衰减量/历史衰减量/最后一轮衰减量</td>
							<%
								long sHistory = Server.getKeyValueForever().getLong(
										"KU_CUN_SHUAI_JIAN_LIANG");
								long sToday = Server.getKeyValueDaily().getLong(
										"KU_CUN_SHUAI_JIAN_LIANG");
								long sLast = Server.getKeyValueForever().getLong(
										"LAST_KU_CUN_SHUAI_JIAN_LIANG");
							%>
							<td><%=buildRmb(sToday)%>&nbsp;/&nbsp;<%=buildRmb(sHistory)%>&nbsp;/&nbsp;<%=buildRmb(sLast)%></td>
						</tr>


					</tbody>
				</table>

				<br> <input style="display: none;" type="submit" value="保存" />
			</form>
		<%
			GetHistoryResult r = t.getHistory();
			List<History> hs = r.getHistory();
			hs = Lists.newArrayList(hs);
			Collections.reverse(hs);
			StringBuilder sb = new StringBuilder();
			
			for(History h : hs) {
				Sheet sheet = Server.getXml().get("weights");
				Row row = sheet.get(h.getId());
				String type = row.get("type");
				String ch = TurntableUtil.toChinese(type);
				sb.append(ch + "&nbsp");
			}
		 %>
			<%=Turntable.getInstance().getCd() / 1000%>秒  &nbsp;&nbsp;   <%=sb.toString() %>

			<table border="1" data-role="table" data-mode="columntoggle"
				class="ui-body-d ui-shadow table-stripe ">
				<thead>
					<tr>
						<th>序号</th>
						<th>玩家昵称</th>
						<th>平台</th>
						<th>金豆</th>
						<th>银行</th>
						<th>飞禽</th>
						<th>银鲨</th>
						<th>金鲨</th>
						<th>走兽</th>
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
						printBeiLv(sbba);
						printProfit(sbba);
						printBiChu(sbba);
						int i = 1;
						for (String roleId : d) {
							if (Server.getRobotManager().isRobot(roleId)) {
								continue;
							}
							ISwitchs s = ss.get(roleId);
							print(i++, roleId, s, sbba);
						}

						out.println(sbba);
					%>

					<%!private static void printBiChu(StringBuilder sb) {

		List<String> types = TurntableUtil.getAllTypes();
		sb.append("<tr>");
		sb.append("<td><font color=\"#FF6600\">高级功能</font></td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");
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
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");

		Turntable t = Turntable.getInstance();

		List<Xs> xs = ProfitCalc.getXss(t.getSwitchs(), t.getResultGenerator()
				.getRandomXNumber());

		for (String type : types) {

			sb.append("<td>");
			Long a = getProfit(type, xs);
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


					<%!private static void printBeiLv(StringBuilder sb) {

		List<String> types = TurntableUtil.getAllTypes();
		sb.append("<tr>");
		sb.append("<td><font color=\"#0066FF\">当前倍率</font></td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");
		sb.append("<td>-</td>");

		Turntable t = Turntable.getInstance();

		for (String type : types) {

			sb.append("<td>");
			sb.append("<font color=\"#000000\"> x" + t.getX(type) + "</font>");

			sb.append("</td>");

		}
		sb.append("</tr>");

	}%>


					<%!public static long getProfit(String type, List<Xs> xs) {
		for (Xs x : xs) {
			String t = x.getType();
			if (type.equals(t)) {
				return x.getProfit();
			}
		}
		return 0;
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
					<%!private static void print(int i, String id, ISwitchs s, StringBuilder sb) {
		Role role = Server.getRole(id);

		sb.append("<tr>");

		sb.append("<td>");
		sb.append(i);
		sb.append("</td>");
		sb.append("<td>");

		sb.append("<a href=\"coinLog.jsp?showBackButton=false&roleId="
				+ role.getId()
				+ "&page=200000000&countEvery=14\">"
				+ role.getNick()
				+ (role.getRechargeHistory() > 0 ? "<font color='#FF0000'>(充"
						+ role.getRechargeHistory() + ")</font>" : "") + "</a>");
		sb.append("</td>");

		sb.append("<td>");

		String p = role.getKeyValueForever().getString("PLANTFORM");
		if (p != null) {
			sb.append(p);
		} else {
			sb.append("-");
		}

		sb.append("</td>");

		sb.append("<td>");
		long coin = role.getCoin();
		long bankCoin = role.getBankCoin();

		int rmbC = (int) (coin / 2800);
		int rmbB = (int) (bankCoin / 2800);

		if (coin >= 200000) {
			sb.append("<font color = \"#FF0000\">" + buildRmb(coin) + "</font>");
		} else {
			sb.append(buildRmb(coin));
		}

		sb.append("</td>");
		sb.append("<td>");

		if (bankCoin >= 200000) {
			sb.append("<font color = \"#FF0000\">" + buildRmb(bankCoin) + "</font>");
		} else {
			sb.append(buildRmb(bankCoin));
		}

		sb.append("</td>");

		List<String> types = TurntableUtil.getAllTypes();
		for (String type : types) {
			sb.append("<td>");
			sb.append(TurntableUtil.getByType(s, type));
			sb.append("</td>");
		}

		sb.append("</tr>");
	}%>

					<%!public static String buildRmb(long coin) {
		int rmb = (int) (coin / 2800);
		return coin + "&nbsp;&nbsp;(¥" + rmb + ")";
	}%>

				</tbody>
			</table>

<br>
<br>

<table border="1" data-role="table" id="table-custom-2"
					data-mode="columntoggle"
					class="ui-body-d ui-shadow table-stripe ui-responsive ui-table ui-table-columntoggle">
					<tbody>
					
						<%
			MessageManager mm = Server.getMessageManager();
			
			List<Message> ms = mm.getMessages().getMessages();
			ms = Lists.newArrayList(ms);
			Collections.reverse(ms);
			for(Message m: ms) {
			
				out.println("<tr>");
				out.println("<td>");
				out.println(m.getDate());
				out.println("</td>");
				out.println("<td>");
				out.println("<font color=#007700>" + m.getNick() + "</font>");
				out.println("</td>");
				out.println("<td> ");
				out.println(m.getMessage());
				out.println("</td>");
				out.println("</tr>");
			}
		 %>

</tbody>
</table>



		</div>
	</center>
	<br><br>
</body>
</html>
