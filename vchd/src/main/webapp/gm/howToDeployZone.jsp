
<%@page import="cn.javaplus.collections.list.Lists"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen"%>
<%@page import="java.util.List"%>
<%@page import="cn.javaplus.util.Util"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.Daos"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDao"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDto"%>
<%@page import="com.cnbizmedia.gen.dto.MongoGen.ZoneDto"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../images/favicon.ico">

<title>如何部署新区</title>

<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<link href="../css/simpletable.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../js/ie-emulation-modes-warning.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    
    
    
<style>
@font-face {
	font-family: "Times New Roman";
}

@font-face {
	font-family: "宋体";
}

@font-face {
	font-family: "Wingdings";
}

@font-face {
	font-family: "Arial";
}

@font-face {
	font-family: "黑体";
}

@
list l0:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 39.0000pt;
	mso-level-number-position: left;
	margin-left: 39.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l1:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 81.0000pt;
	mso-level-number-position: left;
	margin-left: 81.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l2:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 18.0000pt;
	mso-level-number-position: left;
	margin-left: 18.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l3:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 18.0000pt;
	mso-level-number-position: left;
	margin-left: 18.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l4:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 60.0000pt;
	mso-level-number-position: left;
	margin-left: 60.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l5:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 60.0000pt;
	mso-level-number-position: left;
	margin-left: 60.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l6:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 39.0000pt;
	mso-level-number-position: left;
	margin-left: 39.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l7:level1 {
	mso-level-number-format: bullet;
	mso-level-suffix: tab;
	mso-level-text: \F06C;
	mso-level-tab-stop: 102.0000pt;
	mso-level-number-position: left;
	margin-left: 102.0000pt;
	text-indent: -18.0000pt;
	font-family: Wingdings;
}

@
list l8:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 81.0000pt;
	mso-level-number-position: left;
	margin-left: 81.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

@
list l9:level1 {
	mso-level-number-format: decimal;
	mso-level-suffix: tab;
	mso-level-text: "%1.";
	mso-level-tab-stop: 102.0000pt;
	mso-level-number-position: left;
	margin-left: 102.0000pt;
	text-indent: -18.0000pt;
	font-family: 'Times New Roman';
}

p.MsoNormal {
	mso-style-name: 正文;
	mso-style-parent: "";
	margin: 0pt;
	margin-bottom: .0001pt;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 10.5000pt;
	mso-font-kerning: 1.0000pt;
}

h1 {
	mso-style-name: "标题 1";
	mso-style-next: 正文;
	margin-top: 17.0000pt;
	margin-bottom: 16.5000pt;
	mso-para-margin-top: 0.0000gd;
	mso-para-margin-bottom: 0.0000gd;
	page-break-after: avoid;
	mso-pagination: lines-together;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-outline-level: 1;
	line-height: 240%;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-weight: bold;
	font-size: 22.0000pt;
	mso-font-kerning: 22.0000pt;
}

h2 {
	mso-style-name: "标题 2";
	mso-style-noshow: yes;
	mso-style-next: 正文;
	margin-top: 13.0000pt;
	margin-bottom: 13.0000pt;
	mso-para-margin-top: 0.0000gd;
	mso-para-margin-bottom: 0.0000gd;
	page-break-after: avoid;
	mso-pagination: lines-together;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-outline-level: 2;
	line-height: 172%;
	font-family: Arial;
	mso-fareast-font-family: 黑体;
	font-weight: bold;
	font-size: 16.0000pt;
	mso-font-kerning: 1.0000pt;
}

h3 {
	mso-style-name: "标题 3";
	mso-style-noshow: yes;
	mso-style-next: 正文;
	margin-top: 13.0000pt;
	margin-bottom: 13.0000pt;
	mso-para-margin-top: 0.0000gd;
	mso-para-margin-bottom: 0.0000gd;
	page-break-after: avoid;
	mso-pagination: lines-together;
	text-align: justify;
	text-justify: inter-ideograph;
	mso-outline-level: 3;
	line-height: 172%;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-weight: bold;
	font-size: 16.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.10 {
	font-family: 'Times New Roman';
}

p.MsoHeader {
	mso-style-name: 页眉;
	margin: 0pt;
	margin-bottom: .0001pt;
	border-top: none;;
	mso-border-top-alt: none;;
	border-right: none;;
	mso-border-right-alt: none;;
	border-bottom: none;;
	mso-border-bottom-alt: none;;
	border-left: none;;
	mso-border-left-alt: none;;
	padding: 1pt 4pt 1pt 4pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: justify;
	text-justify: inter-ideograph;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

p.MsoFooter {
	mso-style-name: 页脚;
	margin: 0pt;
	margin-bottom: .0001pt;
	layout-grid-mode: char;
	mso-pagination: none;
	text-align: left;
	font-family: 'Times New Roman';
	mso-fareast-font-family: 宋体;
	font-size: 9.0000pt;
	mso-font-kerning: 1.0000pt;
}

span.msoIns {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: underline;
	text-underline: single;
	color: blue;
}

span.msoDel {
	mso-style-type: export-only;
	mso-style-name: "";
	text-decoration: line-through;
	color: red;
}

table.MsoNormalTable {
	mso-style-name: 普通表格;
	mso-style-parent: "";
	mso-style-noshow: yes;
	mso-tstyle-rowband-size: 0;
	mso-tstyle-colband-size: 0;
	mso-padding-alt: 0.0000pt 5.4000pt 0.0000pt 5.4000pt;
	mso-para-margin: 0pt;
	mso-para-margin-bottom: .0001pt;
	mso-pagination: widow-orphan;
	font-family: 'Times New Roman';
	font-size: 10.0000pt;
	mso-ansi-language: #0400;
	mso-fareast-language: #0400;
	mso-bidi-language: #0400;
}

@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
}

@page Section0 {
	margin-top: 72.0000pt;
	margin-bottom: 72.0000pt;
	margin-left: 90.0000pt;
	margin-right: 90.0000pt;
	size: 595.3000pt 841.9000pt;
	layout-grid: 15.6000pt;
}

div.Section0 {
	page: Section0;
}
</style>
    
    
    
    
    
    
</head>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp" target="body">游戏管理平台</a>
			</div>
		</div>
	</div>

	<div class="container theme-showcase" role="main">
	
	
	
	
	
	
	<!--StartFragment-->
	<div class="Section0" style="layout-grid:15.6000pt; ">
		<h3 style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">现以部署&nbsp;名为</span><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">&#8221;</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">联运<font
				face="Times New Roman">4</font>
			</span><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">&#8221;</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; ">,&nbsp;<font
				face="宋体">分区</font><font face="Times New Roman">ID</font><font
				face="宋体">为&nbsp;</font><font face="Times New Roman">10007&nbsp;</font><font
				face="宋体">的分区为例</font>
			</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-weight:bold; font-size:16.0000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</h3>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">创建分区</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="236"
				src="images/howToDeployZone39.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">初始化分区配置</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="214"
				src="images/howToDeployZone50.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">修改分区配置</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="532" height="680"
				src="images/howToDeployZone60.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">进入游戏服务器</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="434" height="221"
				src="images/howToDeployZone72.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">进入服务器目录</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">,&nbsp;<font
				face="宋体">并创建新的游戏服务器实例</font>
			</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="38"
				src="images/howToDeployZone97.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">修改游戏服务器端口配置</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="523" height="165"
				src="images/howToDeployZone113.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">部署游戏服务器</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="35"
				src="images/howToDeployZone125.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">同步游戏服务器标识符到管理平台</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="110"
				src="images/howToDeployZone145.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">同步游戏服务器标识符到管理平台</span><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="567"
				src="images/howToDeployZone165.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">重启游戏服务器</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="554" height="18"
				src="images/howToDeployZone176.png"><span
				style="mso-spacerun:'yes'; font-family:'Times New Roman'; mso-fareast-font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; ">部署成功</span><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p></o:p>
			</span>
		</p>
		<p class=MsoNormal style="text-align:center; ">
			<img width="553" height="421"
				src="images/howToDeployZone185.png"><span
				style="mso-spacerun:'yes'; font-family:宋体; font-size:10.5000pt; mso-font-kerning:1.0000pt; "><o:p>&nbsp;</o:p>
			</span>
		</p>
	</div>
	<!--EndFragment-->
	
	
	
	
	
	
	</div>








</body>
</html>

