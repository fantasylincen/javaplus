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

				<tr>
					<td>金币输出(今日/历史)</td>
					<td><%=t.getCoinOutToday() + "/" + t.getCoinOutHistory()%></td>
				</tr>

				<tr>
					<td>金币输入(今日/历史)</td>
					<td><%=t.getCoinInToday() + "/" + t.getCoinInHistory()%></td>
				</tr>

				<tr>
					<td>开奖次数(今日/历史)</td>
					<td><%=t.getGenerateTimesToday() + "/"
					+ t.getGenerateTimesHistory()%></td>
				</tr>
				<%
					SimpleDateFormat FORMAT = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
				%>
				<tr>
					<td>服务器时间</td>
					<td><%=FORMAT.format(new Date(System.currentTimeMillis()))%></td>
				</tr>
				<tr>
					<td>彩金</td>
					<td><%=Turntable.getInstance().getCaiJin()%></td>
				</tr>


				<tr>
					<td>系统库存</td>
					<td><%=tc.getKuCun()%> &nbsp;&nbsp;&nbsp;&nbsp;<a href="setKuCun.jsp">修改</a>
					</td>
				</tr>

				<tr>
					<td>当系统库存小于该值时, 触发强制收分程序<%=tc.getNormalShouFenSec() / 60%>分钟</td>
					<td><input name="maxKuCun" value="<%=tc.getMaxKuCun()%>">
					</td>
				</tr>

				<tr>
					<td>触发收分程序时, 强制干涉(强制收分)概率</td>
					<td><input name="chuFaTunFenGaiLv" value="<%=tc.getChuFaTunFenGaiLv()%>">
					</td>
				</tr>
				
				
				<tr>
					<td>触发收分程序时, 强制干涉(强制收分)时长(分钟)</td>
					<td><input name="chuFaTunFenShiChang" value="<%=tc.getChuFaTunFenShiChang()%>">
					</td>
				</tr>
				
				<tr>
					<td>回报率档位(吐分速率)</td>
					<td><input name="dangWei" type="range"
						min="<%=tc.getDangWeiMin()%>" max="<%=tc.getDangWeiMax()%>"
						step="1" value="<%=tc.getDangWei()%>"></td>
				</tr>
				
				<tr>
					<td>当系统库存正负性发生变化时, 是否将档位调到正常状态</td>
					<td>
						<%
							if (tc.isToNormal()) {
						%> <input name="toNormal" type="checkbox" checked="checked" /> <%
 	} else {
 %> <input name="toNormal" type="checkbox" /> <%
 	}
 %>
					</td>
				</tr>

				<tr>
					<td>当前配置表回报率(大于1:吐分, 小于1:吞分)</td>
					<td><%=tc.getHuiBaoLv()%>&nbsp;&nbsp;&nbsp;&nbsp;<%=tc.getHuiBaoLvDsc()%></td>
				</tr>

				<tr>
					<td>系统强制干涉类型</td>
					<td>
						<%
							if (tc.isQiangZhiTunFen()) {
						%> <input type="radio" value="1" name="qiangZhiType"
						checked="checked">强制吞分 <input type="radio" value="0"
						name="qiangZhiType">强制吐分 <%
 	} else {
 %> <input type="radio" value="1" name="qiangZhiType">强制吞分 <input
						type="radio" value="0" name="qiangZhiType" checked="checked">强制吐分
						<%
 	}
 %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 剩余干涉时间: <%=tc.getRemainGanSheHour()%>
						小时 <%=tc.getRemainGanSheMin()%> 分 <a href="setGanSheTime.jsp">修改</a>
					</td>
				</tr>

				<tr>
					<td>系统强制干涉概率</td>
					<td><input name="tunTuGaiLv"
						value="<%=tc.getQiangZhiTunTuGaiLv()%>">
					</td>
				</tr>

				<tr>
					<td>系统干涉状态</td>
					<td>
						<%
							if(tc.getQiangZhiTunTuGaiLv() <0.0001 || tc.getGetGanSheRemainSec() <= 0) {
							 
						 %>
						 	<font color="#FF2200">停止运行</font>
						 <%
							} else {
							 	String type = tc.isQiangZhiTunFen() ? "吞分" : "吐分";
						 %>
						 	<font color="#111111">正在<%=type %> </font>
						 <%
							}
							 
						 %>
					</td>
				</tr>

			</tbody>
		</table>

		<br> <input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp;
		<a href="menu.jsp"> 返回</a> <br>
	</form>
</body>
</html>
