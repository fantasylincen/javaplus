<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>欢迎访问我的主页</h1>
  </div>

  <div data-role="content">
    <p>点击链接来查看弹窗效果。</p>
    <a href="#pagetwo" data-transition="pop">转到页面二</a>
  </div>

  <div data-role="footer">
    <h1>页脚文本</h1>
  </div>
</div> 

<div data-role="page" id="pagetwo">
  <div data-role="header">
    <h1>欢迎访问我的主页</h1>
  </div>	

  <div data-role="content">
    <p>点击链接返回页面一。<b>注释：</b>：fade 是默认效果。</p>
    <a href="#pageone">返回页面一</a>
  </div>

  <div data-role="footer">
    <h1>页脚文本</h1>
  </div>
</div> 

</body>
</html>
