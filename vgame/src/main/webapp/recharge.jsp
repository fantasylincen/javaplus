<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cn.javaplus.util.Util"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cnbizmedia.*"%>
<%@ page import="com.cnbizmedia.account.*"%>
<%@ page import="com.cnbizmedia.gen.dto.MongoGen"%>
<%@ page import="com.cnbizmedia.user.*"%>
<%@ page import="com.cnbizmedia.config.*"%>
<%@ page import="com.cnbizmedia.recharge.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>VGame</title>
<%@include file="head.html"%>
</head>

<body>

	<%@include file="head_and_section.jsp"%>

	<div class="main-box">
		<div class="container">
			<div class="inside">
				<div class="wrapper">
				<%!
					User getUser(HttpSession session) {
						String userId = (String) session.getAttribute("userId");
						if(userId == null)
						    return null;
						User user = Server.loadUserById(userId);
						return  user;
					}
				 %>
					<%
						User user = getUser(session);
						if (user != null) {
					%>
					<section id="content">

						<article>
							<h2>
								支付宝 <span>充值</span>
							</h2>
							<form id="contacts-form" action="/recharge/recharge"
								method="post">



								<fieldset>


									<table style="width:100%;" class="ke-zeroborder">
										<tbody>
											<tr>
												<td>我的V币:</td>
												<td><%=user != null ? user.getVb() : "请先登陆"%></td>
												<td></td>
											</tr>
											<tr>
												<td>充值V币数量:</td>
												<td><input id="vb" name="vb" type="number" value="" />
													<button
														onclick="document.getElementById('contacts-form').submit()">OK</button>
												</td>
												<td></td>
											</tr>
											<tr>
												<td></td>
												<td><font color="0xFF000000">1V币 = 0.01人民币</font>
												</td>
												<td></td>
											</tr>
										</tbody>
									</table>
									<br />

								</fieldset>

							</form>
						</article>

						<br> <br> <br> <br> <br> <br>
						<article>

							<h2>
								充值到 <span>游戏内</span>
							</h2>

							<%
								List<ServerNode> servers = GameServersXml.getServers();
									for (ServerNode b : servers) {
							%>
							<form action="/recharge/czdyx" id="form-czdyx" method="post">
								<div class="field">
									<label><strong><span style="font-size:12px;">
												《<%=b.getGameName()%>》 <%=b.getString("name")%></span> </strong>
										&nbsp;&nbsp;&nbsp;&nbsp;(请输入 <%=b.getString("goldName")%>
										数量&nbsp; <font color="0xFF000000"><%=b.getString("dsc")%></font>):</label>
									<br> 帐号 <input id="uid" name="uid" type="text"
										value="为自己充值可以不填"
										onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
										onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
										style="color:#999999" />&nbsp;&nbsp;&nbsp;&nbsp;数量&nbsp;<input
										id="count" name="count" type="number" value=""
										checked="checked" />
									<button
										onclick="document.getElementById('form-czdyx').submit()">OK</button>
									<br> <br>
								</div>
								<input id="id" name="id" type="hidden"
									value="<%=b.getString("id")%>" />

							</form>
							<%
								}
							%>
						</article>

						<%
							List<Order> orders = user.getOrders();
								orders = Util.Collection.sub(orders, 20);
								if (!orders.isEmpty()) {
									Collections.sort(orders);
						%>

						<br> <br> <br> <br> <br> <br>
						<article>

							<h2>
								最近 <span>游戏内充值</span>
							</h2>


							<table style="width:120%;" class="ke-zeroborder">
								<tbody>

									<tr>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 订单号 </span> </strong></td>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 提交时间 </span> </strong></td>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 服务器 </span> </strong></td>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 充值数量 </span> </strong></td>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 用户 </span> </strong></td>
										<td style="text-align:center;"><strong><span
												style="font-size:14px;"> 状态 </span> </strong></td>
									</tr>
									<tr>
										<%
											SimpleDateFormat sf = new SimpleDateFormat(
															"yyyy-MM-dd HH:mm:ss");
													for (Order o : orders) {
														String id = o.getServerId();
														ServerNode b = GameServersXml.getServer(id);

														String status;
														if (o.getIsError()) {
															status = "<font color=\"0xFF000000\">处理异常</font>";
															String r = o.getReason();
															if (r != null && !r.isEmpty()) {
																status += "(" + r + ")";
															}
														} else if (o.isOk()) {
															status = "<font color=\"0x3CAA6200\">成功到账</font>";
														} else {
															status = "<font color=\"0xEE961100\">正在处理</font>";
														}
														String time = sf.format(new Date(o.getTime()));
										%>

										<td style="text-align:center;"><%=o.getId()%></td>
										<td style="text-align:center;"><%=time%></td>
										<td style="text-align:center;"><%=b.getGameName() + " - " + b.getString("name")%></td>
										<td style="text-align:center;"><%=o.getCount()%></td>
										<td style="text-align:center;"><%=o.getUserId()%></td>
										<td style="text-align:center;"><%=status%></td>
									</tr>
									<%
										}
									%>

								</tbody>
							</table>
						</article>

						<%
							}
						%>
					</section>

					<%
						} else {
					%>

					<section id="content">

						<article>
							<h2>请先<a href="login.jsp">登陆 </a></h2>
						</article>
					</section>

					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>

</body>
<%@include file="footer.html"%>
</body>


</html>
