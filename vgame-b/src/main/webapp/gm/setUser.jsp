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

<%@ page language="java" import="cn.vgame.b.Server"%>
<%@ page language="java" import="cn.vgame.b.account.Role"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.Daos"%>
<%@ page language="java"
	import="cn.vgame.b.gen.dto.MongoGen.MongoDbProperties"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.RoleDao"%>
<%@ page language="java" import="cn.vgame.b.gen.dto.MongoGen.RoleDto"%>
<%@ page language="java" import="cn.javaplus.log.*"%>


<html>
<head>
<title>修改玩家数据</title>

<%@include file="head.html"%>

</head>
<body bgcolor="rgb(110,110,110)">

	<script>
function juge(form){
	if (form.reason.value == null || form.reason.value == ""){ 
   	alert("请输入修改原因!");
    	form.reason.focus();
    	return false;
   }
}

</script>

	<%
		String roleId = (String) request.getParameter("roleId");
		if (roleId != null) {
			session.setAttribute("roleId", roleId);
		}
		roleId = (String) session.getAttribute("roleId");
		Role role = Server.getRole(roleId);
	%>

	<form id="submitUser" action="setUser" method="post"
		onsubmit="return juge(this)">

		<h2>基本信息</h2>
		<table>
			<!-- 	<thead>
				<tr>
					<th>属性</th>
					<th>值</th>
				</tr>
			</thead> -->
			<tbody>
				<tr>
					<td>玩家ID</td>
					<td><%=roleId%><input type="hidden" name="roleId"
						value="<%=roleId%>" /></td>
				</tr>

				<tr>
					<td>所属帐号</td>
					<td><%=role.getOwnerId()%></td>
				</tr>
				<tr>
					<td>注册时间</td>
					<td><%=Util.Time.format(role.getCreateTime())%></td>
				</tr>
				<tr>
					<td>最后登陆时间</td>
					<%
						long t = System.currentTimeMillis() - role.getLastLoginTime();
						long day = t / Util.Time.MILES_ONE_DAY;
						long hour = t % Util.Time.MILES_ONE_DAY / Util.Time.MILES_ONE_HOUR;
						long min = t % Util.Time.MILES_ONE_HOUR / Util.Time.MILES_ONE_MIN;
					%>
					<td><%=Util.Time.format(role.getLastLoginTime())%>
						&nbsp;&nbsp;<%=day%>天 <%=hour%>小时<%=min%>分前</td>
				</tr>

				<tr>
					<td>昵称</td>
					<td><input name="newNick" value="<%=role.getNick()%>" />
					</td>
				</tr>
				<tr>
					<td>携带金币</td>
					<td><%=role.getCoin()%></td>
				</tr>
				<tr>
					<td>赠送金币</td>
					<td><input name="addCoin" value="0" /></td>
				</tr>
				<tr>
					<td>扣除金币</td>
					<td><input name="reduceCoin" value="0" /></td>
				</tr>
				<tr>
					<td>喇叭数量</td>

					<td><%=role.getLaBa()%></td>
				</tr>
				<tr>
					<td>赠送喇叭</td>
					<td><input name="addLaBa" value="0" /></td>
				</tr>
				<tr>
					<td>扣除喇叭</td>
					<td><input name="reduceLaBa" value="0" /></td>
				</tr>

				<tr>
					<td>封号</td>

					<td>
						<%
							if (role.hasFengHao()) {
						%> <input name="isFengHao" type="checkbox" checked="checked" /> <%
 	} else {
 %> <input name="isFengHao" type="checkbox" /> <%
 	}
 %>
					</td>
				</tr>
				<tr>
					<td>禁言</td>

					<td>
						<%
							if (role.hasJinYan()) {
						%> <input name="isJinYan" type="checkbox" checked="checked" /> <%
 	} else {
 %> <input name="isJinYan" type="checkbox" /> <%
 	}
 %>
					</td>



				</tr>

				<tr>

					<td>修改原因</td>

					<td><input id="reason" name="reason" type="text" /></td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="保存" /> &nbsp;&nbsp;&nbsp;&nbsp; <a
			href="queryUsers.jsp"> 返回</a> <br>



	</form>
</body>

</html>
