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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>维C互动</title>

<%@include file="head.html"%>

</head>

<body>

	<%@include file="head.jsp"%>

	<div class="content">
		<div class="inside">
			<div class="wrapper">
				<%!User getUser(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			return null;
		User user = Server.loadUserById(userId);
		return user;
	}%>
				<%
					User user = getUser(session);
					if (user != null) {
				%>
				<section id="content"> <article>
				<p>
					<strong><span
						style="font-size:16px;font-family:'Microsoft YaHei';">支付宝
							充值</span> </strong>
				</p>
				<p>
					<br />
				</p>




				<form id="contacts-form" name=alipayment action=pay/jishi/alipayapi.jsp
					method=post target="_blank">
					<div id="body" style="clear:left">
						<input type="hidden" size="30" name="WIDout_trade_no"
							value="<%=Util.ID.createId()%>" /> <input type="hidden"
							size="30" name="WIDsubject" value="V币" /> <input type="hidden"
							size="30" name="WIDbody" value="购买V币" /> <input type="hidden"
							size="30" name="WIDshow_url" value="http://www.19vc.com" />


						<table style="width:90%;" border="0">
							<tbody>
								<tr>
									<td>我的V币:</td>
									<td><%=user != null ? user.getVb() : "请先登陆"%></td>
									<td></td>
								</tr>
								<tr>
									<td>充值V币数量:</td>
									<td><input id="vb" name="vb" type="number" value="" />
										<button class="new-btn-login" type="submit"
											style="text-align:center;">立即充值</button></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td><font color="0xFF000000">1V币 = 0.01人民币</font></td>
									<td></td>
								</tr>
							</tbody>
						</table>

					</div>



				</form>




				</article> <br> <br> <br> <br> <br> <br> <article>


										<p>
											<strong><span
												style="font-size:16px;font-family:'Microsoft YaHei';">充值到
													游戏内 
											</strong>
										</p>
										<p>
											<br />
										</p>

										<%
											List<ServerNode> servers = GameServersXml.getServers();
												for (ServerNode b : servers) {
										%>
										<form action="/recharge/czdyx" id="form-czdyx" method="post">
											<div class="field">
												<label><strong><span
														style="font-size:12px;"> 《<%=b.getGameName()%>》 <%=b.getString("name")%></span>
												</strong> &nbsp;&nbsp;&nbsp;&nbsp;(请输入 <%=b.getString("goldName")%>
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
										%> </article> <%
 	List<Order> orders = user.getOrders();
 		orders = Util.Collection.sub(orders, 20);
 		if (!orders.isEmpty()) {
 			Collections.sort(orders);
 %> <br> <br> <br> <br> <br> <br> <article>

																<p>
																	<strong><span
																		style="font-size:16px;font-family:'Microsoft YaHei';">最近
																			游戏内充值 
																	</strong>
																</p>
																<p>
																	<br />
																</p>

																<table border="0" width="90%" class="ke-zeroborder">
																	<tbody>

																		<tr>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 订单号 </span> </strong>
																			</td>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 提交时间 </span> </strong>
																			</td>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 服务器 </span> </strong>
																			</td>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 充值数量 </span> </strong>
																			</td>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 用户 </span> </strong>
																			</td>
																			<td style="text-align:center;"><strong><span
																					style="font-size:14px;"> 状态 </span> </strong>
																			</td>
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
																</article> <%
 	}
 %>
														
				</section>

				<%
					} else {
				%>

				<section id="content">


				<p style="text-align:center;">
					<span style="font-size:18px;font-family:'Microsoft YaHei';">请先</span><strong><span
						style="font-size:18px;font-family:'Microsoft YaHei';"><a
							href="login.jsp">登陆 </a> </span> </strong>
				</p>
				</section>

				<%
					}
				%>

			</div>
		</div>
	</div>
<%@ include file= "footer.jsp"%>
</body>

</html>
