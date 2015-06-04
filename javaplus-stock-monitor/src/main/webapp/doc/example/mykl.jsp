<%@page import="cn.javaplus.log.Log"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto"%>
<%@page import="cn.javaplus.monichaogu.ShiChang"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.GuPiaoDto"%>
<%@page import="cn.javaplus.monichaogu.User"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.UserDto"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.UserDao"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.Daos"%>
<%@page import="cn.javaplus.monichaogu.gen.dto.MongoGen.SelectDto"%>
<%@page import="cn.javaplus.monichaogu.StockView"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String code = request.getParameter("code");
	if (code != null)
		ShiChang.getUser().setLastSelectCode(code);

	code = ShiChang.getUser().getLastSelectCode();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="kener.linfeng@gmail.com">
<title>ECharts · Example</title>

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
</head>

<body>

	<div class="container-fluid">
		<div class="row-fluid example">
			<div id="graphic" class="col-md-8">
				<div id="main" class="main"></div>
				<div style="display: none;">
					<button type="button" class="btn btn-sm btn-success"
						onclick="refresh(true)">刷 新</button>
					<span class="text-primary">切换主题</span> <select id="theme-select"></select>

					<span id='wrong-message' style="color:red"></span>
				</div>


				<div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/back?day=500';">500 ←</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/back?day=100';">100 ←</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/back?day=20';">20 ←</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/back?day=1';">1 ←</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/next?day=1';">→ 1</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/next?day=20';">→ 20</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/next?day=100';">→ 100</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/next?day=500';">→ 500</button>
					<br> <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/buy?count=1500&code=<%=code%>';">+1500</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/buy?count=1000&code=<%=code%>';">+1000</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/buy?count=500&code=<%=code%>';">+500</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/buy?count=100&code=<%=code%>';">+100</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/sell?count=100&code=<%=code%>';">-100</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/sell?count=500&code=<%=code%>';">-500</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/sell?count=1000&code=<%=code%>';">-1000</button>
					<button type="button" class="btn btn-sm btn-success"
						onclick="window.location='/gp/sell?count=1500&code=<%=code%>';">-1500</button>
					<br> <br>

					<%
						User user = ShiChang.getUser();
					%>


					当前时间:<%=ShiChang.getCurrentDate()%> &nbsp;&nbsp;&nbsp; <%=ShiChang.getCurrentWeek()%>
					<br> 资产:<%=ShiChang.toPriceString(user.getZiChan())%>
					<br> 可用:<%=ShiChang.toPriceString(user.getRmb())%>
					<br> 持仓: <br>
					<%
						List<GuPiaoDto> gps = user.getGupiaos();
						for (GuPiaoDto dto : gps) {
					%>
					
					<%
							if(dto.getId().equals(code)) {
						%>
						
								<a href="mykl.jsp?code=<%=dto.getId()%>"><font color="#FF0000"><%=dto.getId()%></font></a> 
						<%
							} else {
						%>
								<a href="mykl.jsp?code=<%=dto.getId()%>"><%=dto.getId()%></a>
						
						<%
							}
						%>
					
					
					:&nbsp;&nbsp;&nbsp;
					<%=dto.getCount()%>
					&nbsp;&nbsp;&nbsp; RMB:<%=ShiChang.toPriceString(dto.getCount()
						* ShiChang.getPrice(dto))%> &nbsp;&nbsp; 
						<%=ShiChang.getUpPercentString(dto.getId()) %>&nbsp;&nbsp; 
						<%=ShiChang.toPriceString(ShiChang.getPrice(dto.getId()) )%><br>
					<%
						}
					%>
					<br>
					<br>








					自选:<br>

					<%
						for (SelectDto dto : user.getSelects()) {
					%>
						<%
							if(dto.getId().equals(code)) {
						%>
						
								<a href="mykl.jsp?code=<%=dto.getId()%>"><font color="#FF0000"><%=dto.getId()%></font></a>
						<%
							} else {
						%>
								<a href="mykl.jsp?code=<%=dto.getId()%>"><%=dto.getId()%></a> 
						
						<%
							}
						%>
						:&nbsp;&nbsp;&nbsp;
							<%=ShiChang.getUpPercentString(dto.getId()) %>&nbsp;&nbsp; 
							<%=ShiChang.toPriceString(ShiChang.getPrice(dto.getId()) )%><br>
					<%
						}
					%>
					

					<br> <br>
					<br>
					<form action="/gp/addZiXuan" id="addZiXuan">
						<textarea rows="10" id="addZiXuan" name="addZiXuan"></textarea>
						<button type="submit" class="btn btn-sm btn-success">+</button>
					</form>

					<br> <br>
					
					
					
					<a href="query.jsp">猜你喜欢</a>

				</div>
				<br>
			</div>
		</div>

	</div>



	<div style="display:none" class="well sidebar-nav">
		<div class="nav-header">
			<a href="#" onclick="autoResize()"
				class="glyphicon glyphicon-resize-full" id="icon-resize"></a>option
		</div>

		<textarea id="code" name="code">
		
		<%
					List<PriceDto> dataArrayList = new StockView().getDataArrayList(
							code, ShiChang.getCurrentDate());
				%>
		
option = {
    title : {
        text: '<%=code + "&nbsp;&nbsp;&nbsp;" + ShiChang.getUpPercentString(code) %>'
    },
    tooltip : {
        trigger: 'axis',
        formatter: function (params) {
            var res = params[0].seriesName + ' ' + params[0].name;
            res += '<br />  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
            res += '<br />  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
            return res;
        }
    },
    legend: {
        data:['<%=code %>']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataZoom : {show: true},
            dataView : {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    dataZoom : {
        show : true,
        realtime: true,
				
        start : <%=20%>,
        end : <%=100%>
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            axisTick: {onGap:false},
            splitLine: {show:false},
            data : <%=new StockView().getDates(code, ShiChang.getCurrentDate())%>
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            boundaryGap: [0.01, 0.01]
        }
    ],
    series : [
        {
            name:'上证指数',
            type:'k',
            data:<%=new StockView().getDataArray(dataArrayList)%>
        }
    ]
};

        </textarea>

	</div>



	<script src="../asset/js/jquery.min.js"></script>
	<script type="text/javascript" src="../asset/js/echartsHome.js"></script>
	<script src="../asset/js/bootstrap.min.js"></script>
	<script src="../asset/js/echartsExample.js"></script>
</body>
</html>

