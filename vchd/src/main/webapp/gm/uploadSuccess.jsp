<%@page import="com.cnbizmedia.gen.dto.MongoGen.KeyValueDto"%>
<%@page import="com.cnbizmedia.Server"%>
<%@page import="com.cnbizmedia.gm.Zone"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	uri="/struts-tags" prefix="s"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'message.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<%!public String getValue(String key, Zone zone) {
		List<KeyValueDto> ks = zone.getProperties();
		for (KeyValueDto e : ks) {
			if (e.getKey().equals(key)) {
			
				return e.getValue();
			}
		}

		return null;
	}%>
	<%
		Zone zone = Server.getProjectManager().getZone(session);
		String s = "http://" + getValue("host", zone) + "/"
				+ getValue("webContextRoot", zone) + "/gm/reloadConfig";
	%>
	
<body onload="Load('<%=s%>')">
	上传成功!
	
	

	<p id="Showp"></p>

	<script language="javascript">
		var secs = 2; //倒计时的秒数 

		var URL;

		function Load(url) {

			URL = url;

			for ( var i = secs; i >= 0; i--)

			{

				window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);

			}

		}

		function doUpdate(num)

		{

			document.getElementById('Showp').innerHTML = '' + num
					+ '秒后自动加载';

			if (num == 0) {
				window.location = URL;
			}

		}
	</script>
</body>
</html>