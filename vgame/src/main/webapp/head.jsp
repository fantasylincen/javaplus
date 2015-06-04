<%@page import="com.cnbizmedia.user.User"%>
<%@page import="com.cnbizmedia.Server"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<div class="pagetop_notice txt_gray" align="right">


	<div align="right">
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

		<a href="/account/getUser"><%=nick%></a> <a href="/account/logout">退出</a>
		<%
			}
		%>

	</div>




</div>




<header>
	<div class="container">
		<h1>
			<a>VGame</a>
		</h1>
		<nav>

			<ul>
				<%!public List<com.cnbizmedia.HeadButtonBean> buildBeans(String uri) {
		List<com.cnbizmedia.HeadButtonBean> ls = new ArrayList<com.cnbizmedia.HeadButtonBean>();
		add("index.jsp", "&#x4E3B;&#x9875;", uri, ls);//主页按钮
		add("recharge.jsp", "&#x5145;&#x503C;", uri, ls);//充值按钮
		add("other_game.jsp", "&#x7CBE;&#x54C1;&#x6E38;&#x620F;", uri, ls);//充值按钮
		add("open_api.jsp", "&#x5F00;&#x653E;&#x5E73;&#x53F0;", uri, ls);//充值按钮
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
									+ "\" class=\"current\">" + b.getName()
									+ "</a></li>");
						} else {
							out.println("<li><a href=\"" + b.getPath() + "\">"
									+ b.getName() + "</a></li>");
						}
					}
				%>
			</ul>
		</nav>
	</div>
</header>
