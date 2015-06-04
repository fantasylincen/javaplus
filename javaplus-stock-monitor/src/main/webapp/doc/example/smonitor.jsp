<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="cn.javaplus.smonitor.downloader.Market"%>
<%@page import="cn.javaplus.smonitor.downloader.SMonitor"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="kener.linfeng@gmail.com">
<title>SMonitor</title>

<link rel="shortcut icon" href="../asset/ico/favicon.png">

<link href="../asset/css/font-awesome.min.css" rel="stylesheet">
<link href="../asset/css/bootstrap.css" rel="stylesheet">
<link href="../asset/css/carousel.css" rel="stylesheet">
<link href="../asset/css/echartsHome.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="./www/js/echarts.js"></script>
<script src="../asset/js/codemirror.js"></script>
<script src="../asset/js/javascript.js"></script>

<link href="../asset/css/codemirror.css" rel="stylesheet">
<link href="../asset/css/monokai.css" rel="stylesheet">
<style>
span {
	display: inline-block;
	width: 80px;
}
</style>
</head>

<body>


	<div class="container-fluid">
		<div class="row-fluid example">
			<div id="sidebar-code" class="col-md-4">
				<div class="well sidebar-nav">

					<span></span><span></span>新复破常重跌高破买买买不<br>
					<span></span><span></span>　　　态点破价板入入入等<br> 
					<span></span><span></span>　　　化关一股即两三标上<br> 
					<span></span><span></span>股牌板　注字　买手手记涨<br>

					<%
						String a = request.getParameter("isAdmin");

						if (a != null) {
							session.setAttribute("isAdmin", true);
						}

						boolean isAdmin = session.getAttribute("isAdmin") != null;

						String sId = SMonitor.getInstance().getSelectId();
						int i = 0;
						for (String id : SMonitor.getInstance().getStockIds()) {

							String idText;
							if (id.equals(sId)) {
								idText = "<font color=#810000>" + id + "</font>";
							} else {
								idText = id;
							}
							i++;

							boolean isNewLine = i % 4 == 0;
							boolean isMark = SMonitor.getInstance().isMark(id);
							boolean isCurrent = id.equals(sId);
							String color = isMark ? "FF0000" : "0000FF";
							if (isCurrent) {
								color = "EE55DD";
							}
					%>




					<a href="selectStock?id=<%=id%>"> <font color="#<%=color%>">
							<%=idText%> </font> </a> &nbsp;&nbsp; <span><%=Market.getName(id)%></span>

					<%-- &nbsp;&nbsp;
					
					<%
						List<String> colors = SMonitor.getInstance().getCurrentBuy1Status(id);
						for(String c : colors) {
						
					 %>
					 
					 		<font color="#<%="000000"%>">█</font>
					 <%
					 	}
					  %> --%>


					&nbsp;&nbsp;

					<%
						List<Integer> marks = SMonitor.getInstance().getMarks(id);

							int ii = 0;
							for (int m : marks) {
								ii++;

								String cl;
								if (m == 0) {
									cl = "EEEEEE";
								} else if (ii == 1) {
									cl = "DF2056";
								} else if (ii == 2) {
									cl = "1AC1E6";
								} else if (ii == 3) {
									cl = "FF0000";
								} else if (ii == 4) {
									cl = "CC0066";
								} else if (ii == 5) {
									cl = "339933";
								} else if (ii == 6) {
									cl = "00FFFF";
								} else if (ii == 7) {
									cl = "6666CC";
								} else if (ii == 8) {
									cl = "CCFF33";
								} else if (ii == 9) {
									cl = "0033CC";
								} else if (ii == 10) {
									cl = "CC0033";
								} else if (ii == 11) {
									cl = "6600CC";
								} else if (ii == 12) {
									cl = "00CC66";
								} else {

									cl = "CC6600";
								}
					%>


					<%
						if (isAdmin) {
					%>
					<a href="mark?id=<%=id%>&mark=<%=ii%>"><font color="#<%=cl%>">█</font>
					</a>
					<%
						} else {
					%>
					<font color="#<%=cl%>">█</font>

					<%
						}
					%>
					<%
						}
					%>
					&nbsp;&nbsp;

					<%
						if (isAdmin) {
					%>
					<a href="deleteZiXuan?id=<%=id%>">删</a> &nbsp;&nbsp; <%--  <a
						href="test?id=<%=id%>">测试</a>  --%>
					<%
						}
					%>
					&nbsp;&nbsp; <br>
					<%
						}
					%>

					<form action="addZiXuan">

						<textarea cols="33" name="addZiXuan"></textarea>
						<input type="submit" value="Add" />
					</form>

					<div style="display:none;">
						<textarea id="code" name="code">
option = {
    title : {
        text: '<%=SMonitor.getInstance().getSelectId()%>'
    },
    legend: {
        data:['Volume of Buy1']
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
           	data :  <%=SMonitor.getInstance().getXAxis()%>
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'Volume of Buy1',
            type:'line',
           	data : <%=SMonitor.getInstance().getSelectDatas()%>
        }
    ]
};
                    </textarea>
					</div>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div id="graphic" class="col-md-8">
				<div id="main" class="main"></div>
				<div style="display: none">
					<button type="button" class="btn btn-sm btn-success"
						onclick="refresh(true)">刷 新</button>
					<span class="text-primary">切换主题</span> <select id="theme-select"></select>

					<span id='wrong-message' style="color:red"></span>

				</div>
			</div>


<%!

	public String getSelectDayText(int a) {
		SMonitor ins = SMonitor.getInstance();
		int d = ins.getDay();
		if(d != a) {
			return "<a href=\"selectDay?day=" + a + "\">" + a + "日</a>";
		}
		return a + "日";
	}

	public String getSelectMinText(int a) {
		SMonitor ins = SMonitor.getInstance();
		int d = ins.getMin();
		if(d != a) {
			return "<a href=\"selectMin?min=" + a + "\">" + a + "分</a>";
		}
		return a + "分";
	
		
	}
 %>
			<div>
				<%=getSelectDayText(1) %>&nbsp;
				<%=getSelectDayText(3) %>&nbsp;
				<%=getSelectDayText(5) %>&nbsp;
				<%=getSelectDayText(10) %>&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%=getSelectMinText(1) %>&nbsp;
				<%=getSelectMinText(3) %>&nbsp;
				<%=getSelectMinText(5) %>&nbsp;
				<%=getSelectMinText(10) %>&nbsp;
			</div>
			<br>
			<div>
				<%
					String id = SMonitor.getInstance().getSelectId();
					if (id != null) {
						id = Market.getCode(id);
					}
				%>
				<img src="http://image.sinajs.cn/newchart/daily/n/<%=id%>.gif">
				<img src="http://image.sinajs.cn/newchart/min/n/<%=id%>.gif">
				<br> <a href="all.jsp">显示全部</a>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	<br>
	<br>

	<script src="../asset/js/jquery.min.js"></script>
	<script type="text/javascript" src="../asset/js/echartsHome.js"></script>
	<script src="../asset/js/bootstrap.min.js"></script>
	<script src="../asset/js/echartsExample.js"></script>
</body>
</html>
