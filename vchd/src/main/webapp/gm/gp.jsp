<!DOCTYPE html>
<%@page import="cn.javaplus.web.WebContentFethcer"%>
<%@page import="cn.javaplus.util.Util"%>
<%@page import="com.google.common.collect.Lists"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>

<body>

	<%
	
		List<String> gps = getGps();
		String url = "http://hq.sinajs.cn/list=" + Util.Collection.linkWith(",", gps) ;
		String content = WebContentFethcer.get("gb2312", url);
		
		response.setCharacterEncoding("utf8");
		
		out.println("<table border='1'>");
				out.println("<thead>");
				out.println("	<tr>");
				out.println("		<th> name</th>");
				out.println("		<th> code</th>");
				out.println("		<th> up</th>");
				out.println("		<th> buy1</th>");
				out.println("		<th> bidding1</th>");
				out.println("		<th> buy1 * 1.1</th>");
				out.println("	<tr>");
				out.println("</thead>");
				out.println("<tbody>");
				
				String [] lines = content.split(";");
				System.out.println("xxxx xxxx:" + lines.length);
				for(String line: lines) {
					line = line.trim();
					if(line.isEmpty()) {
						continue;
					}
					
					
					String code = line.split("=")[0].replace("var hq_str_", "");
					String ee = line.split("=")[1].replace("\"", "");
					
					if(ee.isEmpty()) {
						continue;
					}
					
					String [] e = ee.split(",");
					
					float buy1 = new Float(e[11]);
					float yestoday = new Float(e[2]);
					float bidding1 = new Float(e[6]);
					float up = (buy1 - yestoday) / yestoday * 100;
					
					if(Math.abs(buy1 - 0) < 0.001 && Math.abs(bidding1 - 0) < 0.001 ) {
						continue;
					}
					
					out.println("<tr>");

					out.println("<td>" + e[0] + "</td>");
					out.println("<td>" + code + "</td>");
					
					if(up > 9.97) {
					
					out.println("<td> <font color=\"#CE0000\">" + String.format("%.2f",up) + "% </font> </td>");
					} else {
						out.println("<td> <font color=\"#D0D0D0\">" + String.format("%.2f",up) + "% </font> </td>");
					}
					
					
					out.println("<td>" + buy1 + "</td>");
					out.println("<td>" + bidding1 + "</td>");
					out.println("<td>" + String.format("%.2f", (buy1 * 1.1)) + "</td>");
		
					out.println("</tr>");
					
					
					
					
					
				}
				
				
				
				out.println("</tbody>");
				
		
	%>
</body>
</html>



<%!public void append(List<String> ls, String s) {
		ls.add(s.trim().toLowerCase());
	}%>



<%!public List<String> getGps() {
		List<String> ls = Lists.newArrayList();

		append(ls, "	SZ002180	");
		append(ls, "	SZ000969	");
		append(ls, "	SZ002231	");
		append(ls, "	SH600827	");
		append(ls, "	SH600637	");
		append(ls, "	SZ002476	");
		append(ls, "	SH600155	");
		append(ls, "	SZ002047	");
		append(ls, "	SH601588	");
		append(ls, "	SZ002350	");
		append(ls, "	SH600083	");
		append(ls, "	SZ000605	");
		append(ls, "	SZ000415	");
		append(ls, "	SZ002569	");
		append(ls, "	SZ002160	");
		append(ls, "	SZ300036	");
		append(ls, "	SZ000990	");
		append(ls, "	SH600649	");
		append(ls, "	SZ002010	");
		append(ls, "	SZ300366	");
		append(ls, "	SZ002512	");
		append(ls, "	SZ002421	");
		append(ls, "	SZ000719	");
		append(ls, "	SZ300186	");
		append(ls, "	SH600747	");
		append(ls, "	SH600719	");
		append(ls, "	SZ000593	");
		append(ls, "	SH600558	");
		append(ls, "	SZ002249	");
		append(ls, "	SZ300073	");
		append(ls, "	SH600136	");
		append(ls, "	SZ300396	");
		append(ls, "	SH600614	");
		append(ls, "	SZ002675	");
		append(ls, "	SZ300059	");
		append(ls, "	SH600086	");
		append(ls, "	SZ002611	");
		append(ls, "	SH600832	");
		append(ls, "	SZ300118	");
		append(ls, "	SZ300379	");
		append(ls, "	SZ300367	");
		append(ls, "	SZ002672	");
		append(ls, "	SZ300183	");
		append(ls, "	SZ002011	");
		append(ls, "	SH600516	");
		append(ls, "	SZ002196	");
		append(ls, "	SZ000636	");
		append(ls, "	SH600498	");
		append(ls, "	SH600483	");
		append(ls, "	SH600196	");
		append(ls, "	SZ002357	");
		append(ls, "	SZ300034	");
		append(ls, "	SZ300098	");
		append(ls, "	SH600701	");
		append(ls, "	SH600597	");
		append(ls, "	SH600894	");
		append(ls, "	SZ000978	");
		append(ls, "	SH600962	");
		append(ls, "	SZ000616	");
		append(ls, "	SZ000503	");
		append(ls, "	SZ300065	");
		append(ls, "	SZ002255	");
		append(ls, "	SZ300201	");
		append(ls, "	SH600221	");
		append(ls, "	SZ002596	");
		append(ls, "	SH600238	");
		append(ls, "	SZ002529	");
		append(ls, "	SZ002498	");
		append(ls, "	SZ002036	");
		append(ls, "	SH600677	");
		append(ls, "	SH600271	");
		append(ls, "	SZ002290	");
		append(ls, "	SZ000633	");
		append(ls, "	SZ300141	");
		append(ls, "	SH600179	");
		append(ls, "	SZ002104	");
		append(ls, "	SZ000687	");
		append(ls, "	SZ300081	");
		append(ls, "	SZ002211	");
		append(ls, "	SH600461	");
		append(ls, "	SZ000883	");
		append(ls, "	SZ300278	");
		append(ls, "	SH600475	");
		append(ls, "	SZ002645	");
		append(ls, "	SH600503	");
		append(ls, "	SZ000607	");
		append(ls, "	SZ300110	");
		append(ls, "	SH600062	");
		append(ls, "	SZ300095	");
		append(ls, "	SZ002630	");
		append(ls, "	SZ300025	");
		append(ls, "	SZ300271	");
		append(ls, "	SH600741	");
		append(ls, "	SH600191	");
		append(ls, "	SZ000995	");
		append(ls, "	SZ002680	");
		append(ls, "	SH600172	");
		append(ls, "	SZ300368	");
		append(ls, "	SZ000692	");
		append(ls, "	SZ300022	");
		append(ls, "	SZ300213	");
		append(ls, "	SZ300116	");
		append(ls, "	SZ002316	");
		append(ls, "	SZ002176	");
		append(ls, "	SZ000820	");
		append(ls, "	SZ300233	");
		append(ls, "	SZ300349	");
		append(ls, "	SZ002624	");
		append(ls, "	SZ002464	");
		append(ls, "	SZ000510	");
		append(ls, "	SZ002722	");
		append(ls, "	SH600722	");
		append(ls, "	SH600679	");
		append(ls, "	SZ002548	");
		append(ls, "	SZ000587	");
		append(ls, "	SZ002721	");
		append(ls, "	SH600232	");
		append(ls, "	SZ300220	");
		append(ls, "	SZ300316	");
		append(ls, "	SZ002171	");
		append(ls, "	SZ002349	");
		append(ls, "	SZ000989	");
		append(ls, "	SZ002411	");
		append(ls, "	SZ300040	");
		append(ls, "	SZ002037	");
		append(ls, "	SZ002279	");
		append(ls, "	SZ002656	");
		append(ls, "	SZ000939	");
		append(ls, "	SZ002119	");
		append(ls, "	SZ300222	");
		append(ls, "	SZ300192	");
		append(ls, "	SH600806	");
		append(ls, "	SZ000971	");
		append(ls, "	SZ002513	");
		append(ls, "	SZ002612	");
		append(ls, "	SH600756	");
		append(ls, "	SZ002319	");
		append(ls, "	SZ002131	");
		append(ls, "	SZ300269	");
		append(ls, "	SZ002280	");
		append(ls, "	SH600285	");
		append(ls, "	SZ002717	");
		append(ls, "	SZ002442	");
		append(ls, "	SH603003	");
		append(ls, "	SZ000912	");
		append(ls, "	SZ002617	");
		append(ls, "	SH600209	");
		append(ls, "	SZ002114	");
		append(ls, "	SZ000502	");
		append(ls, "	SZ002660	");
		append(ls, "	SZ000889	");
		append(ls, "	SZ300038	");
		append(ls, "	SZ000968	");
		append(ls, "	SZ300242	");
		append(ls, "	SZ000012	");
		append(ls, "	SZ002553	");
		append(ls, "	SH600250	");
		append(ls, "	SZ000611	");
		append(ls, "	SZ300053	");
		append(ls, "	SZ002711	");
		append(ls, "	SH600090	");
		append(ls, "	SZ002225	");
		append(ls, "	SZ002301	");
		append(ls, "	SZ002359	");
		append(ls, "	SZ002173	");
		append(ls, "	SH600076	");
		append(ls, "	SZ002520	");
		append(ls, "	SZ000626	");
		append(ls, "	SZ300126	");
		append(ls, "	SZ300243	");
		append(ls, "	SZ300241	");
		append(ls, "	SZ300339	");
		append(ls, "	SZ300298	");
		append(ls, "	SZ002159	");
		append(ls, "	SH600293	");
		append(ls, "	SZ000863	");
		append(ls, "	SZ002417	");
		append(ls, "	SZ000826	");
		append(ls, "	SZ002358	");
		append(ls, "	SH600870	");
		append(ls, "	SZ002598	");
		append(ls, "	SZ300008	");
		append(ls, "	SZ002565	");
		append(ls, "	SH600748	");
		append(ls, "	SZ000038	");
		append(ls, "	SZ000010	");
		append(ls, "	SZ000034	");
		append(ls, "	SZ000062	");
		append(ls, "	SZ002361	");
		append(ls, "	SZ000008	");
		append(ls, "	SZ000410	");
		append(ls, "	SH600226	");
		append(ls, "	SZ002473	");
		append(ls, "	SZ002446	");
		append(ls, "	SZ300090	");
		append(ls, "	SZ002691	");
		append(ls, "	SZ002137	");
		append(ls, "	SZ002602	");
		append(ls, "	SZ000005	");
		append(ls, "	SZ002558	");
		append(ls, "	SH600258	");
		append(ls, "	SZ002555	");
		append(ls, "	SZ000860	");
		append(ls, "	SZ300322	");
		append(ls, "	SZ002712	");
		append(ls, "	SZ000518	");
		append(ls, "	SZ002728	");
		append(ls, "	SH600129	");
		append(ls, "	SH600667	");
		append(ls, "	SZ002517	");
		append(ls, "	SZ002213	");
		append(ls, "	SZ000070	");
		append(ls, "	SZ300001	");
		append(ls, "	SZ300362	");
		append(ls, "	SZ002509	");
		append(ls, "	SZ300390	");
		append(ls, "	SZ300063	");
		append(ls, "	SZ002491	");
		append(ls, "	SZ000766	");
		append(ls, "	SH600438	");
		append(ls, "	SZ002049	");
		append(ls, "	SZ000591	");
		append(ls, "	SZ000630	");
		append(ls, "	SZ002261	");
		append(ls, "	SH600575	");
		append(ls, "	SH600576	");
		append(ls, "	SH600681	");
		append(ls, "	SH603010	");
		append(ls, "	SH600246	");
		append(ls, "	SZ000564	");
		append(ls, "	SZ000721	");
		append(ls, "	SZ002125	");
		append(ls, "	SZ002089	");
		append(ls, "	SZ002264	");
		append(ls, "	SZ002188	");
		append(ls, "	SZ002376	");
		append(ls, "	SH600777	");
		append(ls, "	SZ300130	");
		append(ls, "	SZ002298	");
		append(ls, "	SZ002390	");
		append(ls, "	SH600866	");
		append(ls, "	SH600599	");
		append(ls, "	SZ002607	");
		append(ls, "	SZ000671	");
		append(ls, "	SZ300274	");
		append(ls, "	SZ300030	");
		append(ls, "	SZ000796	");
		append(ls, "	SZ002458	");
		append(ls, "	SZ000526	");
		append(ls, "	SH600858	");
		append(ls, "	SZ000411	");
		append(ls, "	SZ000663	");
		append(ls, "	SH600157	");
		append(ls, "	SZ002174	");
		append(ls, "	SZ002277	");
		append(ls, "	SZ000584	");
		append(ls, "	SZ300174	");
		append(ls, "	SZ002161	");
		append(ls, "	SH600725	");
		append(ls, "	SZ002637	");
		append(ls, "	SZ000625	");
		append(ls, "	SZ300348	");
		append(ls, "	SH600797	");
		append(ls, "	SZ300101	");
		append(ls, "	SZ000676	");
		append(ls, "	SZ002169	");
		append(ls, "	SZ000887	");
		append(ls, "	SZ002057	");
		append(ls, "	SH601299	");
		append(ls, "	SH601888	");
		append(ls, "	SH601766	");
		append(ls, "	SZ000996	");
		append(ls, "	SZ300177	");
		append(ls, "	SZ002364	");
		append(ls, "	SZ300308	");
		append(ls, "	SH600053	");
		append(ls, "	SZ002021	");
		append(ls, "	SZ300078	");
		append(ls, "	SZ000506	");
		append(ls, "	SZ002659	");
		append(ls, "	SH600522	");
		append(ls, "	SH601608	");
		append(ls, "	SZ000715	");
		append(ls, "	SH600745	");
		append(ls, "	SZ000982	");
		append(ls, "	SH600759	");
		append(ls, "	SZ300232	");
		append(ls, "	SZ000938	");
		append(ls, "	SZ300181	");

		return ls;
	}%>
