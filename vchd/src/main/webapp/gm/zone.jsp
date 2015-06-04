<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
<meta content="MSHTML 6.00.2900.3492" name=GENERATOR>


<script language="javascript">
	var isPiss = 0;
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && e.keyCode == 116) {
			parent.frames['body'].location.reload();
			event.keyCode = 0;
			event.cancelBubble = true;
			event.returnValue = false;
			return false;
		}
	};
</script>

</head>



<frameset id="allFrame" frameSpacing=0 rows=80,* frameBorder=0>

	<frame name=top src="head.jsp" frameBorder=0 noResize scrolling=no
		name="topFrame">
	<frameset frameSpacing=0 frameBorder=0 cols=220,*>
		<frame id="menu" name=menu src="menu.jsp" frameBorder=0 noResize>
		<frame id="body" name=body src="zoneBody.jsp" frameBorder=0>
	</frameset>
	<noframes></noframes>
</frameset>
</html>
