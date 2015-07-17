<%@page import="cn.vgame.a.turntable.Turntable"%>
<%@page import="cn.javaplus.collections.list.Lists"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDto"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.Daos"%>
<%@page import="cn.vgame.a.gen.dto.MongoGen.CoinLogDao"%>
<%@page import="cn.vgame.a.message.Message"%>
<%@page import="cn.vgame.a.message.Messages"%>
<%@page import="cn.vgame.a.message.MessageManager"%>
<%@page import="cn.vgame.a.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>飞禽走兽</title>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="../css/themes/default/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="../_assets/css/jqm-demos.css">
<script src="../js/jquery.js"></script>
<script src="../_assets/js/index.js"></script>
<script src="../js/jquery.mobile-1.4.5.min.js"></script>




<script type="text/javascript">
	function check() {
		var p1 = document.getElementById("message").value;
		if (p1.len == 0) {
			alert("消息内容不能为空");
			return false;
		}
		return true;
	}
</script>



</head>
<body>


	<div data-role="page" class="jqm-demos" data-quicklinks="true">

		<div data-role="header" class="jqm-header">
			<h2>
				<a title="Guess Home"><img height="800" src="img/logo.png"
					alt="jQuery Mobile"> </a>
			</h2>
		</div>
		<div class="jqm-block-content">


			<h3>聊天记录</h3>
			<font size="1"> <%
 	MessageManager m = Server.getMessageManager();
 	Messages ms = m.getMessages();
 	List<Message> mss = ms.getMessages();
 	for (Message message : mss) {
 %>
				<p>
					<%="<font color=\"#884452\">"
						+ message.getDate().replaceAll("201[0-9]\\-", "")
						+ "&nbsp;&nbsp;" + message.getNick()
						+ "</font>:&nbsp;&nbsp;&nbsp;&nbsp;"
						+ message.getMessage()%>

				</p> <%
 	}
 %> </font>
			<p>
			<form id="sendMessage" name="sendMessage" action="sendMessage">
				<input id="message" name="message" type="text" maxlength="50"
					class="txt1"></input>
				<button class="ui-btn" type="submit">发送</button>


			</form>
			</p>

		</div>
		<div class="jqm-block-content">
			<h3>信息</h3>

			<p>
				我的帐号:<%=(String) session.getAttribute("id")%>
			</p>
			<p>
				我的昵称:<%=Server.getRole((String) session.getAttribute("roleId")).getNick()%>
			</p>
			<p>
				我的金豆:<%=Server.getRole((String) session.getAttribute("roleId"))
					.getCoin()%>
			</p>
			<p>
				在线人数:<%=Turntable.getInstance().getRoleCount()%>
			</p>

			<p>
			<form id="sendCoin" name="sendCoin" action="sendCoin">
				<input id="nick" name="nick" class="txt1" type="text"
					value="请输入用户昵称"
					onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
					onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
					style="color:#999999"> <input id="coin" name="coin"
					class="txt1" type="text" value="请输入转账金豆"
					onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
					onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
					style="color:#999999">
				<button class="ui-btn" type="submit">转账</button>
			</form>
			</p>
		</div>

		<div class="jqm-block-content">
			<h3>历史记录</h3>
			<font size="1">
				<%
					String roleId = (String) session.getAttribute("roleId");
					StringBuffer ssb = new StringBuffer();
	
					List<CoinLogDto> last = getLast(roleId, 100);
					
					for (CoinLogDto dto : last) {
						print(ssb, dto, roleId);
					}
	
					out.println(ssb.toString());
				%>
			</font>
			<%!
				public List<CoinLogDto> getLast(String roleId, int count) {
				
					CoinLogDao dao = Daos.getCoinLogDao();
				
					CoinLogDtoCursor find = dao.findByFromToFuzzy("*" + roleId + "*");

					int skip = find.getCount() - count;
					
					if(skip <0) {
						skip = 0;
					}
					find.skip(skip);
					find.limit(count);
					List<CoinLogDto> ls = Lists.newArrayList();
					for (CoinLogDto dto : find) {
						ls.add(dto);
					}
					
					Collections.reverse(ls);
					return ls;
					
					
				}
			 %>

			<%!void print(StringBuffer sb, CoinLogDto dto, String meId) {

		String from = dto.getFrom();
		if (!"send coin".equals(dto.getDsc())) {
			return;
		}
		
		
		String color = "#884452";
		
		if(!meId.equals(dto.getFrom())) {
			color = "#4D61B3";
		}
		
		sb.append("<p><font color=\"" + color + "\">" );
		sb.append(dto.getTime().replaceAll("201[0-9]\\-", ""));
		sb.append("&nbsp;");
		sb.append(Server.getRole(dto.getFrom()).getNick() + "&nbsp;&gt;&gt;&nbsp;" + Server.getRole(dto.getTo()).getNick());
		sb.append("</font>&nbsp;&nbsp;");
		sb.append(dto.getCoin());
		sb.append("金豆");
		sb.append("</p>");
	}%>

		</div>

	</div>






</body>
</html>

