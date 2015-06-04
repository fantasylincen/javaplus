<%@page import="com.alibaba.fastjson.JSONObject"%><%@page import="com.cnbizmedia.gm.Zone"%><%@page import="com.cnbizmedia.Server"%><%@page import="cn.javaplus.collections.list.Lists"%><%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@page import="java.util.List"%><%@page import="com.cnbizmedia.gen.dto.MongoGen"%><%@page import="java.util.List"%><%@page import="cn.javaplus.util.Util"%><%@page import="com.cnbizmedia.gen.dto.MongoGen.Daos"%><%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDao"%><%@page import="com.cnbizmedia.gen.dto.MongoGen.ProjectDto"%><%@page import="com.cnbizmedia.gen.dto.MongoGen.ZoneDto"%><%@page import="com.cnbizmedia.gen.dto.MongoGen.KeyValueDto"%><%@page import="java.util.Map.Entry"%><html><head><meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta name="viewport" content="width=device-width, initial-scale=1"><meta name="description" content=""><meta name="author" content=""><link rel="icon" href="../images/favicon.ico"><title>Theme Template for Bootstrap</title><!-- Bootstrap core CSS --><link href="../css/bootstrap.min.css" rel="stylesheet"><!-- Bootstrap theme --><link href="../css/bootstrap-theme.min.css" rel="stylesheet"><link href="../css/simpletable.css" rel="stylesheet"><!-- Custom styles for this template --><link href="../css/theme.css" rel="stylesheet"><!-- Just for debugging purposes. Don't actually copy these 2 lines! --><!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]--><script src="../js/ie-emulation-modes-warning.js"></script><!-- IE10 viewport hack for Surface/desktop Windows 8 bug --><script src="../js/ie10-viewport-bug-workaround.js"></script><!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries --><!--[if lt IE 9]>      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>    <![endif]--><meta name="description"	content="The power of GitHub's social coding for your own workgroup. Pricing, tour and more."><meta http-equiv="Content-type:text/html;charset=utf-8"><link href="/assets/application-55f07ba38e3d64171dacc542c1927633.css"	media="all" rel="stylesheet" /><link href="/assets/print-0a04386179bac34932c1e3701a290e49.css"	media="print" rel="stylesheet" /><script src="/assets/application-39c65e5269029f77399e35204fc1b25b.js"></script><meta content="authenticity_token" name="csrf-param" /><meta	content="wVyoRJESvkdzyLgzg7qWCJQWj4R8+LqBqqzfJfAH9MwohwZpl4Rko+EreFXQpEXEXQpRwGIM3s1qUmoxoXu7xA=="	name="csrf-token" /><script src="/assets/event_tracking-de44750e00c71a7199b1447084374783.js"></script></head><body>	<div class="container theme-showcase" role="main"><h2><br>/account
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" A.正常情况:
  	{
  		String id :  客户端传过来的userId,
  		String sessionId,
  	}
  
  B.错误:
   标准错误: 10003 token error
  
  
  说明: 标准错误, 客户端需要对所有包  统一处理
     {
     	String error 错误文字,
     	int code 这个错误对应到配置表 messages 里面的错误号,
       String args [    比如 消息: 10008    message too long len must < %s0     那么 args =[ 10] 时 , 该消息表示  message too long len must < 10
       	String arg1...
       	String arg2...
       	String arg3...
       ],
     }" href=" http://19vc.com/vgame-a/account/enterServer?token=XXXXXX&userId=XXXXXX">
玩家进入游戏服务器
</a></td>
<td>
enterServer
</td>
<td>
token:验证串<br>
userId:玩家ID<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { role:{ id 角色ID, ownerId 所属帐号, nick 昵称, coin 金币数量, cd 开奖CD时间(秒) } }
  
  B.错误: 标准错误: 10004 nick contains sencitive word 10005 you not enter server
  
  
  说明: 标准错误, 客户端需要对所有包 统一处理 { String error 错误文字, int code 这个错误对应到配置表 messages
  里面的错误号, String args [ 比如 消息: 10008 message too long len must < %s0 那么 args =[
  10] 时 , 该消息表示 message too long len must < 10 String arg1... String arg2...
  String arg3... ], }" href=" http://19vc.com/vgame-a/account/createRole?nick=XXXXXX">
创建角色
</a></td>
<td>
createRole
</td>
<td>
nick:昵称<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  	{
  		roles:[
  				角色ID1,角色ID2,角色ID3,角色ID4,角色ID5
  		]
  	}
  
  B.错误:
   标准错误: 
   		10005 you not enter server
  
  
  说明: 标准错误, 客户端需要对所有包  统一处理
     {
     	String error 错误文字,
     	int code 这个错误对应到配置表 messages 里面的错误号,
       String args [    比如 消息: 10008    message too long len must < %s0     那么 args =[ 10] 时 , 该消息表示  message too long len must < 10
       	String arg1...
       	String arg2...
       	String arg3...
       ],
     }" href=" http://19vc.com/vgame-a/account/getRoleList">
获取角色列表
</a></td>
<td>
getRoleList
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  	{
  		role:{
  				id 角色ID,
  				ownerId 所属帐号,
  				nick 昵称,
  				coin 金币数量,
  				cd 开奖CD时间(秒)
  		}
  	}
  
  B.错误:
   标准错误: 10002 10006
  
  
  说明: 标准错误, 客户端需要对所有包  统一处理
     {
     	String error 错误文字,
     	int code 这个错误对应到配置表 messages 里面的错误号,
       String args [    比如 消息: 10008    message too long len must < %s0     那么 args =[ 10] 时 , 该消息表示  message too long len must < 10
       	String arg1...
       	String arg2...
       	String arg3...
       ],
     }" href=" http://19vc.com/vgame-a/account/selectRole?roleId=XXXXXX">
选定某个角色加入游戏
</a></td>
<td>
selectRole
</td>
<td>
roleId:角色ID<br>
</td>
</tr>
</table>
<h2><br>/recharge
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" A.正常情况: { }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/recharge/recharge4YiJie?ssid=XXXXXX&app=XXXXXX&cbi=XXXXXX&ct=XXXXXX&fee=XXXXXX&pt=XXXXXX&sdk=XXXXXX&sign=XXXXXX&st=XXXXXX&tcd=XXXXXX&uid=XXXXXX&ver=XXXXXX">
充值
</a></td>
<td>
recharge4YiJie
</td>
<td>
ssid:未知<br>
app:未知<br>
cbi:未知<br>
ct:未知<br>
fee:未知<br>
pt:未知<br>
sdk:未知<br>
sign:未知<br>
st:未知<br>
tcd:未知<br>
uid:未知<br>
ver:未知<br>
</td>
</tr>
</table>
<h2><br>/turntable
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" A.正常情况: { a, b, c, d, e, f, g, h, i, j, k, l, }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/commitMySwitchs?a=XXXXXX&b=XXXXXX&c=XXXXXX&d=XXXXXX&e=XXXXXX&f=XXXXXX&g=XXXXXX&h=XXXXXX&i=XXXXXX&j=XXXXXX&k=XXXXXX&l=XXXXXX">
提交我的压注数据
</a></td>
<td>
commitMySwitchs
</td>
<td>
a:A 2 飞禽<br>
b:B 24 银鲨鱼<br>
c:C 48 金鲨鱼<br>
d:D 2 走兽<br>
e:E 6 燕子<br>
f:F 8 鸽子<br>
g:G 8 孔雀<br>
h:H 12 老鹰<br>
i:I 12 狮子<br>
j:J 8 熊猫<br>
k:K 8 猴子<br>
l:L 6 兔子<br>
</td>
</tr>
<tr>
<td><a  title=" 返回: 同 GetAllSwitchs" href=" http://19vc.com/vgame-a/turntable/synchronizeData?a=XXXXXX&b=XXXXXX&c=XXXXXX&d=XXXXXX&e=XXXXXX&f=XXXXXX&g=XXXXXX&h=XXXXXX&i=XXXXXX&j=XXXXXX&k=XXXXXX&l=XXXXXX">
同步数据
</a></td>
<td>
synchronizeData
</td>
<td>
a:A 2 飞禽<br>
b:B 24 银鲨鱼<br>
c:C 48 金鲨鱼<br>
d:D 2 走兽<br>
e:E 6 燕子<br>
f:F 8 鸽子<br>
g:G 8 孔雀<br>
h:H 12 老鹰<br>
i:I 12 狮子<br>
j:J 8 熊猫<br>
k:K 8 猴子<br>
l:L 6 兔子<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  	{
  		cd 开奖CD,
  		id 回合id,
  		long 彩金数量caiJin,
  		zhuangCoin:庄家金豆,
  		zhuangNick:庄家昵称,
  		switchs: { 压注情况
  			a,
  			b,
  			c,
  			d,
  			e,
  			f,
  			g,
  			h,
  			i,
  			j,
  			k,
  			l
  		}
  		
  		xs: { 所有开关的倍率
  			a,
  			b,
  			c,
  			d,
  			e,
  			f,
  			g,
  			h,
  			i,
  			j,
  			k,
  			l
  		}
  	}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/getAllSwitchs">
获取桌面数据
</a></td>
<td>
getAllSwitchs
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  	{
  
  		coin: 玩家金币(结算后的金币)
  		results:[
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			}
  		]
  
  
  	}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/playonce?a=XXXXXX&b=XXXXXX&c=XXXXXX&d=XXXXXX&e=XXXXXX&f=XXXXXX&g=XXXXXX&h=XXXXXX&i=XXXXXX&j=XXXXXX&k=XXXXXX&l=XXXXXX">
玩一次转盘
</a></td>
<td>
playonce
</td>
<td>
a:A 2 飞禽<br>
b:B 24 银鲨鱼<br>
c:C 48 金鲨鱼<br>
d:D 2 走兽<br>
e:E 6 燕子<br>
f:F 8 鸽子<br>
g:G 8 孔雀<br>
h:H 12 老鹰<br>
i:I 12 狮子<br>
j:J 8 熊猫<br>
k:K 8 猴子<br>
l:L 6 兔子<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  	{
  		coin: 玩家金币(结算后的金币),
  
  		caiJin: 得了多少彩金,
  
  		results:[
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			},
  			{
  				type 对应到 weights表里面的type
  				id 对应到 weights表里面的id
  			}
  		]
  	}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/getPlayResult?a=XXXXXX&b=XXXXXX&c=XXXXXX&d=XXXXXX&e=XXXXXX&f=XXXXXX&g=XXXXXX&h=XXXXXX&i=XXXXXX&j=XXXXXX&k=XXXXXX&l=XXXXXX">
获取开奖结果
</a></td>
<td>
getPlayResult
</td>
<td>
a:A 2 飞禽<br>
b:B 24 银鲨鱼<br>
c:C 48 金鲨鱼<br>
d:D 2 走兽<br>
e:E 6 燕子<br>
f:F 8 鸽子<br>
g:G 8 孔雀<br>
h:H 12 老鹰<br>
i:I 12 狮子<br>
j:J 8 熊猫<br>
k:K 8 猴子<br>
l:L 6 兔子<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  				id 角色ID,
  				ownerId 所属帐号,
  				nick 昵称,
  				coin 金币数量,
  				cd 开奖CD时间(秒)
  
  				coinStatus : {
  					boolean canReceive 是否可以领取
  					int cd 剩余时间秒
  					long coin 可领金币数量
  				}
  		}
  
  B.错误:
   标准错误  10007" href=" http://19vc.com/vgame-a/turntable/getUserData">
获取玩家数据
</a></td>
<td>
getUserData
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  				cd 开奖CD时间(秒)
  		}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/getCd">
获取开奖CD (毫秒)
</a></td>
<td>
getCd
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  			history: [
  				
  				{id=1},
  				{id=2},
  				{id=3},
  				{id=4},
  				{id=5},
  				.
  				.
  				.
  				.
  				{id=20}
  			]
  		}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/getHistory">
获取开奖历史记录
</a></td>
<td>
getHistory
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  			coin: 1, // 身上的金豆
           bankCoin:2// 银行中的金豆
  		}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/toBank?coin=XXXXXX">
存钱
</a></td>
<td>
toBank
</td>
<td>
coin:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  			coin: 1, // 身上的金豆
           bankCoin:2// 银行中的金豆
  		}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/fromBank?coin=XXXXXX&password=XXXXXX">
取钱
</a></td>
<td>
fromBank
</td>
<td>
coin:未知<br>
password:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  		{
  			coin: 1, // 身上的金豆
           bankCoin:2// 银行中的金豆
  		}
  
  B.错误:
   标准错误" href=" http://19vc.com/vgame-a/turntable/sendCoin?coin=XXXXXX&roleId=XXXXXX&password=XXXXXX">
赠送金豆
</a></td>
<td>
sendCoin
</td>
<td>
coin:未知<br>
roleId:未知<br>
password:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { roleId: r13125465, nick: 刘 , }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/getRole?nickOrId=XXXXXX">
获取角色基本信息
</a></td>
<td>
getRole
</td>
<td>
nickOrId:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/setBankPassword?oldPassword=XXXXXX&newPassword=XXXXXX">
在知道密码 的情况下 修改密码
</a></td>
<td>
setBankPassword
</td>
<td>
oldPassword:未知<br>
newPassword:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/unlockBankPassword">
强制解锁, 需要N小时等待时间
</a></td>
<td>
unlockBankPassword
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { bankPasswordCd:1, //解锁cd bankPasswordStatus:0, 密码状态 }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/cancelUnlockBankPassword">
取消强制解锁
</a></td>
<td>
cancelUnlockBankPassword
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { "cd":3600 //秒 }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/getBankPasswordUI">
获取解锁界面数据
</a></td>
<td>
getBankPasswordUI
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { 
 	zhuangCoin 当前庄家金币
 	zhuangNick 当前庄家昵称
  		roles: [
  			{roleId :100122231, nick:xxxx, coin:10000000},
  			{roleId :100122232, nick:xxxx, coin:10000001},
  			{roleId :100122233, nick:xxxx, coin:10000002},
  			{roleId :100122234, nick:xxxx, coin:10000003}
  		]
  
  }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/getZhuangList">
获得上庄排队列表
</a></td>
<td>
getZhuangList
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: 返回结果同 getShangZhuangList
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/shangZhuang">
上庄
</a></td>
<td>
shangZhuang
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { 返回值同 GetZhuangListAction }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/xiaZhuang">
下庄
</a></td>
<td>
xiaZhuang
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { type:购买物品类型, id:购买id  客户端发好多就原样返回, count:数量}
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/buy?id=XXXXXX">
买东西
</a></td>
<td>
buy
</td>
<td>
id:未知<br>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: 
  
  {
  		historys: [
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量}
 		]
   }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/getCaiJinHistory">
获得彩金历史记录
</a></td>
<td>
getCaiJinHistory
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况:
  
  获得随机推荐20个玩家 传入参数 count 可选项, 如果不发,默认20个 返回 { count:数量, roles: [ { roleId:角色id,
  nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
  nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
  nick昵称}, ] }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/recommendRoles">
获得彩金历史记录
</a></td>
<td>
recommendRoles
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" A.正常情况: { zhuang:{//庄家 nick:昵称 roleId:角色id coin: 获得金豆 }
  
  me: { //本家 nick:昵称 roleId:角色id coin: 获得金豆 }
  
  rankingList: [ { nick:昵称, roleId:角色id, coin: 获得金豆}, { nick:昵称, roleId:角色id,
  coin: 获得金豆}, { nick:昵称, roleId:角色id, coin: 获得金豆}, { nick:昵称, roleId:角色id,
  coin: 获得金豆}, { nick:昵称, roleId:角色id, coin: 获得金豆}, ... ] }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/turntable/getRankingList">
获取本轮排行榜列表
</a></td>
<td>
getRankingList
</td>
<td>
</td>
</tr>
</table>
<h2><br>/message
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" A.正常情况: { boolean suceess = true }
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/message/sendMessage?message=XXXXXX">
发送喊话消息 , 消耗 game.xml - const.HAN_HUA_LA_BA 个喇叭
</a></td>
<td>
sendMessage
</td>
<td>
message:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 正常情况:
  {
  		messages:[
  			{id:10001, message:fuckyou, time:1301111009331, date:2013-11-23 14:12:10},
  			{id:10002, message:fuckyou2, time:1301111009331, date:2013-11-23 14:12:10},
  			{id:10003, message:fuckyou3, time:1301111009331, date:2013-11-23 14:12:10},
  			{id:10004, message:fuckyou4, time:1301111009331, date:2013-11-23 14:12:10},
  			{id:10005, message:fuckyou5, time:1301111009331, date:2013-11-23 14:12:10},
  			{id:10006, message:fuckyou6, time:1301111009331, date:2013-11-23 14:12:10}
   	]	
  }
  
  错误:
     标准错误" href=" http://19vc.com/vgame-a/message/getMessages">
拉取喊话列表
</a></td>
<td>
getMessages
</td>
<td>
</td>
</tr>
</table>
<h2><br>/receivecoin
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" A.正常情况: 跟 "获取玩家数据" 返回值一样
  
  B.错误: 标准错误" href=" http://19vc.com/vgame-a/receivecoin/receivecoin">
领取金币
</a></td>
<td>
receivecoin
</td>
<td>
</td>
</tr>
</table>
<h2><br>/gm
</h2><table class="bordered">	<thead>		<tr>			<th>说明</th>			<th>名字</th>			<th>参数列表</th>		</tr>	</thead><tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/reloadConfig">
重新加载系统配置(刘雨诚请无视)
</a></td>
<td>
reloadConfig
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/execScript">
重新加载系统配置(刘雨诚请无视)
</a></td>
<td>
execScript
</td>
<td>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/mustGenerate?id=XXXXXX">
本轮必出(刘雨诚请无视)
</a></td>
<td>
mustGenerate
</td>
<td>
id:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/caiJinMustTo?id=XXXXXX">
彩金给某个机器人(刘雨诚请无视)
</a></td>
<td>
caiJinMustTo
</td>
<td>
id:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/setUser?newNick=XXXXXX&isJinYan=XXXXXX&isFengHao=XXXXXX&roleId=XXXXXX&reason=XXXXXX&addLaBa=XXXXXX&addCoin=XXXXXX&reduceLaBa=XXXXXX&reduceCoin=XXXXXX">
重新加载系统配置(刘雨诚请无视)
</a></td>
<td>
setUser
</td>
<td>
newNick:未知<br>
isJinYan:未知<br>
isFengHao:未知<br>
roleId:未知<br>
reason:未知<br>
addLaBa:未知<br>
addCoin:未知<br>
reduceLaBa:未知<br>
reduceCoin:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/setSystemInfo?maxKuCun=XXXXXX&dangWei=XXXXXX&tunTuGaiLv=XXXXXX&toNormal=XXXXXX&qiangZhiType=XXXXXX&chuFaTunFenShiChang=XXXXXX&chuFaTunFenGaiLv=XXXXXX">
修改系统配置(刘雨诚请无视)
</a></td>
<td>
setSystemInfo
</td>
<td>
maxKuCun:未知<br>
dangWei:未知<br>
tunTuGaiLv:未知<br>
toNormal:未知<br>
qiangZhiType:未知<br>
chuFaTunFenShiChang:未知<br>
chuFaTunFenGaiLv:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/setGanSheTime?hour=XXXXXX&min=XXXXXX">
修改系统干涉时长(刘雨诚请无视)
</a></td>
<td>
setGanSheTime
</td>
<td>
hour:未知<br>
min:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/setKuCun?kuCun=XXXXXX">
修改库存(刘雨诚请无视)
</a></td>
<td>
setKuCun
</td>
<td>
kuCun:未知<br>
</td>
</tr>
<tr>
<td><a  title=" 无" href=" http://19vc.com/vgame-a/gm/setRobot?roleId=XXXXXX">
重新加载系统配置(刘雨诚请无视)
</a></td>
<td>
setRobot
</td>
<td>
roleId:未知<br>
</td>
</tr>
</table>
	</div></body></html>