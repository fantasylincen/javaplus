<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="cn.javaplus.smonitor.downloader.Market"%>
<%@page import="cn.javaplus.smonitor.downloader.SMonitor"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="kener.linfeng@gmail.com">

</head>

<body>



				<%
					for (String id : SMonitor.getInstance().getStockIds()) {
				%>
				<br>
				
				
				

					<a href="selectStock?id=<%=id%>">
						<%=Market.getCode(id)%>
					</a> 
					
					  
					&nbsp;&nbsp;
					<%
						List<Integer> marks = SMonitor.getInstance().getMarks(id);
						
						int ii = 0;
						for(int m : marks) {
							ii++;
					 		
					 		String cl ;
					 		if(m == 0) {
					 			cl = "EEEEEE";
					 		} else if(ii == 1) {
					 			cl = "DF2056";
					 		} else if(ii == 2) {
					 			cl = "1AC1E6";
					 		} else if(ii == 3) {
					 			cl = "FF0000";
					 		} else if(ii == 4) {
					 			cl = "CC0066";
					 		} else if(ii == 5) {
					 			cl = "339933";
					 		} else if(ii == 6) {
					 			cl = "00FFFF";
					 		} else if(ii == 7) {
					 			cl = "6666CC";
					 		} else if(ii == 8) {
					 			cl = "CCFF33";
					 		} else if(ii == 9) {
					 			cl = "0033CC";
					 		} else if(ii == 10) {
					 			cl = "CC0033";
					 		} else if(ii == 11) {
					 			cl = "6600CC";
					 		} else if(ii == 12) {
					 			cl = "00CC66";
					 		} else {
					 			
					 			cl = "CC6600";
					 		}
					 %>
					 		
					 		<a href="mark?id=<%=id %>&mark=<%=ii %>"><font color="#<%=cl%>">█</font></a>
					 <%
					 	}
					  %>
					&nbsp;&nbsp;
				
				
				<br>
				<img src="http://image.sinajs.cn/newchart/min/n/<%=Market.getCode(id)%>.gif">
				<img src="http://image.sinajs.cn/newchart/daily/n/<%=Market.getCode(id)%>.gif">
				<br>

				<%
					}
				%>
</body>
</html>
