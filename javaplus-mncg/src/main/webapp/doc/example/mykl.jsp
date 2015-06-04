<%@page import="cn.javaplus.monichaogu.StockView"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
				<div>
					<button type="button" class="btn btn-sm btn-success"
						onclick="refresh(true)">刷 新</button>
					<span class="text-primary">切换主题</span> <select id="theme-select"></select>

					<span id='wrong-message' style="color:red"></span> <br> <br>
					<br>
				</div>
			</div>
		</div>

	</div>



	<div style="display:none" class="well sidebar-nav">
		<div class="nav-header">
			<a href="#" onclick="autoResize()"
				class="glyphicon glyphicon-resize-full" id="icon-resize"></a>option
		</div>

		<textarea id="code" name="code">
option = {
    title : {
        text: '2013年上半年上证指数'
    },
    tooltip : {
        trigger: 'axis',
        formatter: function (params) {
            var res = params[0].seriesName + ' ' + params[0].name;
            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
            return res;
        }
    },
    legend: {
        data:['上证指数']
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
        start : 50,
        end : 100
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            axisTick: {onGap:false},
            splitLine: {show:false},
            data : <%=new StockView().getDates("002240", "20130101", "20140101") %>
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
            data:<%=new StockView().getDataArray("002240", "20130101", "20140101") %>
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

