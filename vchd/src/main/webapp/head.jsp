<%@page import="com.cnbizmedia.user.User"%>
<%@page import="com.cnbizmedia.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<div id="top">
 <div class="logo"><a href="index.jsp"><img src="./images/logo.png" alt="VC互动" border="0" /></a></div>
 <ul class="top-nav">
    <li><a href="index.jsp">首页</a></li>
    <li>|</li>
    
	<li><a href="contact.jsp">联系我们</a></li>
    <li>|</li>
 	<li>
		<%
			if (session.getAttribute("userId") == null) {
		%>
		<font color="#FFFFFF">请</font><a href="login.jsp">登陆 </a>
		<%
			} else {
		%>
		<%
			String userId = (String) session.getAttribute("userId");
			User user = Server.loadUserById(userId);
			String nick = user.getNick();
		%>

		<a href="/account/getUser"><%=nick.trim() %>&nbsp;</a> <a href="/account/logout">退出</a>
		<%
			}
		%>

	</li>
  	
 </ul> 
</div>
<!-- nav -->
<div id="nav">
 <div id="menu">
  <ul >

		<%!public List<com.cnbizmedia.HeadButtonBean> buildBeans(String uri) {
		List<com.cnbizmedia.HeadButtonBean> ls = new ArrayList<com.cnbizmedia.HeadButtonBean>();
		add("index.jsp", "&#x5173;&#x4E8E;&#x6211;&#x4EEC;", uri, ls);
		add("news.jsp", "&#x516C;&#x53F8;&#x52A8;&#x6001;", uri, ls);
		add("product.jsp", "&#x4EA7;&#x54C1;&#x4ECB;&#x7ECD;", uri, ls);
		add("recruitment.jsp", "&#x62DB;&#x8058;&#x82F1;&#x624D;", uri, ls);
		add("cooperation.jsp", "&#x5546;&#x52A1;&#x5408;&#x4F5C;", uri, ls);
		add("contact.jsp", "&#x8054;&#x7CFB;&#x6211;&#x4EEC;", uri, ls);
		add("recharge.jsp", "&#x5145;&#x503C;&#x5E73;&#x53F0;", uri, ls);
		
		
		return ls;
	}

	public void add(String a, String b, String uri,
			List<com.cnbizmedia.HeadButtonBean> ls) {
		com.cnbizmedia.HeadButtonBean bean = new com.cnbizmedia.HeadButtonBean();
		bean.setPath(a);
		bean.setName(b);
		bean.setCurrent(uri.equals("/" + a));
		ls.add(bean);
	}%>

				<%
					String uri = request.getRequestURI();
					List<com.cnbizmedia.HeadButtonBean> beans = buildBeans(uri);

					for (com.cnbizmedia.HeadButtonBean b : beans) {
						if (b.isCurrent()) {
							out.println("<li><a href=\"" + b.getPath()
									+ "\" class=\"active\">" + b.getName()
									+ "</a></li>");
						} else {
							out.println("<li><a href=\"" + b.getPath() + "\">"
									+ b.getName() + "</a></li>");
						}
					}
				%>
  </ul>
 </div>
</div>

