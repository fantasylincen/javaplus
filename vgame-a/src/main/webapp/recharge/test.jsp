
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <style type="text/css">
		input:focus{
		    outline:none;
		}
	body {
		-webkit-tap-highlight-color:rgba(0,0,0,0);
		-webkit-user-select:none;
		user-select:none;
	}
        .container {
            width:100%;
            text-align: center;
        }

	.phone-no {
		border-bottom: #cccccc 1px solid;
		border-top: #cccccc 1px solid;
		margin: 10px 0px;
		text-align: left;
		height: 45px;
		background-color:#ffffff;
	}
	.phone-no input{
		border:none;
		border-radius: 3px;
		width: 180px;
		height: 100%;
		padding-left: 10px;
		font-size:18px;
		font-family:"Microsoft YaHei";
		line-height:45px;
	}

	#cards {
		margin:10px;
		padding:0px;
		list-style-type:none;
	}
	.cards-li{
		float:left;
		border: #cccccc 1px solid;
		border-radius: 1px;
		width: 45%;
		margin:5px;
		font-family:"Microsoft YaHei";
		font-size:15px;
		background-color:#ffffff;
		color:#666666;
		line-height: 45px;
        height:45px;
	}
	.div-rotate {
		display: inline-block;
		-o-transform: rotate(320deg); 
		-webkit-transform: rotate(320deg); 
		-moz-transform: rotate(320deg);
		background-color: #00ff00;
	}
	.div-ru{
		background-color: #e94643;
		color:#ffffff;
		float: right;
		font-size: 12px;
		padding: 2px 5px;
		font-family:"Microsoft YaHei";
		line-height: 14px;
	}
	.select{
		background-position:right;
		background-position-y: bottom;
		border: #e94643 2px solid;
		margin: 4px;
	}
	#tip {
		text-align:left;
		margin: 10px 15px;
		font-size: 15px;
		font-family: SimHei;
	}
	#really {
		text-align:left;
		margin: 0px 15px;
		font-size: 15px;
		clear: both;
		padding-top: 10px;
		display: none;
		font-family: SimHei;
	}
	.font-acc{
		color:#e94643;
	}
	#history {
		text-align:left;
		margin: -10px 0px;
		font-size: 13px;
		position:fixed;
		height:60%;
		overflow:auto;
        width:100%;
	}
	.history-hide{
		display:none;
	}
	.warning {
		color:#d00000;
	}
	.alert-box{
		color: #fff;
		top:20%;
		left:10%;
		width:80%;
		height:60%;
	}
	.alert-box text-div{
		margin: 10px 0px;
	}
	.alert-box button{
		width: 100%;
		margin: 10px 0px;
		display: block;
	}
	.bank-list{
		margin: 0px;
		padding: 0px;
	}
	.bank-list li{
		margin-right: 10px;
		padding-left:10px;
		line-height:43px;
		overflow:hidden;
		font-size: 15px;
	}
	.head{
		line-height: 45px;
		border:0px;
		height:45px;
	   	vertical-align:middle;
	   	text-align:center;
		width: 100%;
		position: relative;
		background-color:#f2f2f2;
	}
	.head-content{
			background-size: 67px;
			background-color: #e94643;
			margin: 0px;
			color:#ffffff;
	}
	.btn-orange-disable{
		margin-top: 15px;
		border-radius:3px;
		height: 43px;
		line-height: 33px;
		font-size: 18px;
		border:0px;
	   	text-align: center;
		cursor: pointer;
		color: #ffcbca;
		width: 100%;
		background-color:#ff8d8b;
		box-sizing:border-box;
	}
	.btn-orange{
		margin-top: 15px;
		border-radius:3px;
		height: 43px;
		line-height: 33px;
		font-size: 18px;
		border:0px;
	   	text-align: center;
		cursor: pointer;
		color: #fff;
		width: 100%;
		background-color:#e94643;
		box-sizing:border-box;
	}
	.copy-right-sub {
		color: #b3b3b3;
	   	font-size: 12px;
	   	text-shadow: 0 1px 1px #fff;
		margin: 0 auto;
		text-align: center;
		padding-top:10px;
		width:100%;
		bottom: 0;
		top:auto;
	}
	.more-card{
		float: right;
		display: block;
		background: url("https://www.baifubao.com/static/mobile/mobile-pay/style/images/arrow_down.png") no-repeat 15px 18px;
		height: 100%;
		width: 43px;
		border-left: #cccccc 1px solid;
	}
	.more-card-click{
		background: url("https://www.baifubao.com/static/mobile/mobile-pay/style/images/arrow_up.png") no-repeat 15px 18px;
	}
	.attru {
		color:#666666;
	}
	#a_go_back{
		position: absolute;
		display: inline-block;
		height: 13px;
		width: 13px;
		top: 15px;
		left: 15px;
		border-top: solid 2px #fff;
		border-left: solid 2px #fff;
		-webkit-transform: rotate(-45deg);
		transform: rotate(-45deg);
		display: none;
	}
	::-webkit-input-placeholder { font-family: SimHei;font-size:15px;}
	input:-moz-placeholder {font-family: SimHei;}
    </style>
</head>
<body style="margin:0px;background-color:#f2f2f2">


<div class="head">
	<h1 class="head-content" style="font-size:20px; margin:0px">话费充值</h1>
	<a id="a_go_back" href="javascript:void(0);"></a>
</div>


<div class="container" id="containerID">
	<div id="advertiseDiv" style="margin-left:15px; text-align:left;margin-top:10px;font-family: SimHei;font-size:15px;text-shadow: 0 1px 1px #fff;">
    </div>
	<div class="phone-no">
		<a href="javascript:;" class="more-card"></a>
		<span>
			<input id="ph-no" autocomplete="off" type="tel" placeholder="请输入手机号" maxlength="13"/>
			<img src="https://www.baifubao.com/static/mobile/mobile-pay/style/images/delete_num.png"  width="23" height="23" style="display:none;vertical-align: middle;float:right;margin: 10px;" onclick="clearPrev(this)">
		</span>
	</div>
	<div id="history" class="history-hide">
	<ul id="his_phone" onclick="javascript:hideHistory()" class="bank-list clearfix" id="bank-ul" style="background-color: #ffffff;border-bottom: #cccccc 1px solid;border-left: #cccccc 1px solid;border-right: #cccccc 1px solid;">
	<li onclick="javascript:changePhone(15022005630)">15022005630</li>
	</ul>
	</div>

	<div id="tip"></div>
	<div>
		<ul id="cards">
			<li id="am-30" onclick="javascript:clickMe(this, 30)" class="cards-li">
				<span id="face_amount-30">30元</span>
				<span id="dis-30" class="div-ru" style="display:none">88折</span>	
			</li>
			<li id="am-50" onclick="javascript:clickMe(this, 50)" class="cards-li" style="float:right">
				<span id="face_amount-50">50元</span>
				<span id="dis-50" class="div-ru" style="display:none">88折</span>	
			</li>
			<li id="am-100" onclick="javascript:clickMe(this, 100)" class="cards-li" style="clear: left;">
				<span id="face_amount-100">100元</span>
				<span id="dis-100" class="div-ru" style="display:none">88折</span>	
			</li>
			<li id="am-300" onclick="javascript:clickMe(this, 300)" class="cards-li" style="float:right">
				<span id="face_amount-300">300元</span>
				<span id="dis-300" class="div-ru" style="display:none">88折</span>	
			</li>
		</ul>
	</div>
	<div id="really"><span>实付金额：</span><span class="font-acc">15</span>元</div>
	<div style="padding:0px 15px">
		<button class="btn-orange input-submit" id="go-sumbit" data-ac="btn-orange-hover" style="display:none" onclick="charge()">立即充值</button>
		<button class="btn-orange-disable" id="go-sumbit-disable" >立即充值</button>
	    <div id="TipTxt" class="copy-right-sub" style="text-align:left; margin-top:10px;">
		  <div>提示：</div>
		  <div style="margin-top:10px;">1.付费成功后会在3分钟左右充值成功，月底月初到账时间会略有延迟</div>
		  <div>2.如遇问题，请拨打客服电话：<span style="color: #136ec2;TEXT-DECORATION: none;"> 400-898-8855</span></div>
		</div>
	</div>
</div>

<form id="my_form" action="/_u/pdc/common_charge/wap/" method="post">
	<input type="hidden" name="phone_number" id="my_phone" value=""/>
    <input type="hidden" name="is_huodong_price" id="is_huodong" value=""/>
	<input type="hidden" name="device_token" value="BaiduWallet_H5_common_charge"/>
	<input type="hidden" name="channel_no" value="CHF0000000"/>
	<input type="hidden" name="price" id="my_price" value="3000"/>
    <input type="hidden" name="invite_code" id="ipt_invite" value=""/>
</form>

	<div class="copy-right-sub">
	<div id="myfooter">
            <div style="position:relative;font-family:SimHei;font-size:13px;">
            <img style="position:absolute;margin-left:-12px;top:1px;height:13px;"
             src="data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAAAeAAD/4QNxaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW4
9Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDI
vMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnh
tcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmU
uY29tL3hhcC8xLjAvIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InhtcC5kaWQ6Rjc3RjExNzQwNzIwNjgxMTgyMkFGNTUzREFENjUzQTgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6M0JDMzY0QkM2M0QzMTFFNEI
5QkE4MkQ5MUVDNjhDNjciIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6M0JDMzY0QkI2M0QzMTFFNEI5QkE4MkQ5MUVDNjhDNjciIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSI
+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjI4QTNFQTg0MzYyMDY4MTE4MjJBRUU1MUM2MDE0RDBGIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkY3N0YxMTc0MDcyMDY4MTE4MjJ
BRjU1M0RBRDY1M0E4Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+/+4ADkFkb2JlAGTAAAAAAf/bAIQAEAsLCwwLEAwMEBcPDQ8XGxQQEBQbHxcXFxc
XHx4XGhoaGhceHiMlJyUjHi8vMzMvL0BAQEBAQEBAQEBAQEBAQAERDw8RExEVEhIVFBEUERQaFBYWFBomGhocGhomMCMeHh4eIzArLicnJy4rNTUwMDU1QEA/QEBAQEBAQEBAQEBA/8AAEQgAFgASAwEiAAIRAQMRAf/EAGQ
AAQADAQAAAAAAAAAAAAAAAAACBAUGAQEAAAAAAAAAAAAAAAAAAAAAEAABAgMFBwUAAAAAAAAAAAACAAERAwQhMVESE0FhoYIzFAWBsWIjJBEBAAAAAAAAAAAAAAAAAAAAAP/aAAwDAQACEQMRAD8A6Gp1pdVo0k0ycr5bO8B
fBTalBzEa2pLUe4YuzehFYnjXLLUzRZiqMH32+6lMKbO8bMKrHKQvEHex78OCDRy73uhfxRYHc1WL9PLyYogtzA/WR+PmfbbqS4PCO212yqM8as8vfnpSY7GjbyR4oiC7+D49Hf0kREH/2Q=="\>
        重庆商界互娱网络技术有限公司&copy2015 19vc.com
        </div> 
        </div>
    </div>
	<script src="https://www.baifubao.com/static/mobile/mobile-pay/js/zepto.js" charset="utf-8"></script>
	<script src="https://www.baifubao.com/static/mobile/mobile-pay/js/dialog.js" charset="utf-8"></script>
	<script src="https://www.baifubao.com/content/mywallet/h5/advertise.js" charset="gb2312"></script>
	<script src="https://www.baifubao.com/content/mywallet/h5/huodong/qianbao_invite_code.min.js"></script>
	<script>
		if(document.URL.indexOf("advertise") >=0 )   $("#advertise").show();
		var hd_price = 0;
		var divCon = document.getElementById("containerID");
		divCon.style.minHeight=$(window).height() - 115;
		var his_width = $(window).width() - 30;
		var faces = {};
		var remap = {};
		var is_login = false;

		var defaultAm = 50;
		var am30 = $("#am-30"); 
		var am50 = $("#am-50"); 
		var am100 = $("#am-100"); 
		var am300 = $("#am-300");
		var dis30 = $("#dis-30"); 
		var dis50 = $("#dis-50"); 
		var dis100 = $("#dis-100"); 
		var dis300 = $("#dis-300");
		var face_amount30 = $("#face_amount-30");
		var face_amount50 = $("#face_amount-50");
		var face_amount100 = $("#face_amount-100");
		var face_amount300 = $("#face_amount-300");
		var btnSubmit = $("#go-sumbit");
		var btnSubmitDisable = $("#go-sumbit-disable");
		am50.addClass("select");	

	        function localStorageGet(key){
			try {
				return localStorage.getItem(key);
			} catch(e) {
				return null;
			}
			}

                function localStorageSet(key , value){
		    try {
				localStorage.setItem(key, value);
			} catch(e) {
				return -1;
			}
		}

        function deparam (querystring) {
                // remove any preceding url and split
                querystring = querystring.slice(querystring.indexOf('?')+1).split('&');
                var params = {};
                var pair;
                var d = function( str ) {
                try{
                    return window.decodeURIComponent(str);
                }catch(e){
                    return str
                }
                }
                // march and parse
                for (var i = querystring.length - 1; i >= 0; i--) {
                        pair = querystring[i].split('=');
                       params[d(pair[0])] = d(pair[1]);
                 }
                return params;
                }
		function allowSubmit() {
			btnSubmit.show();
			btnSubmitDisable.hide();
		}
		function clearAll(){
			am30.removeClass("select");	
			am50.removeClass("select");	
			am100.removeClass("select");	
			am300.removeClass("select");
			dis30.hide();	
			dis50.hide();	
			dis100.hide();	
			dis300.hide();
			face_amount30.css("margin-left", "0px");	
			face_amount50.css("margin-left", "0px");	
			face_amount100.css("margin-left", "0px");	
			face_amount300.css("margin-left", "0px");	
		}
		function disableSubmit() {
			btnSubmit.hide();
			btnSubmitDisable.show();
			$("#tip").html("");
			$("#really").hide();
			clearAll();
			faces = {};
			$("#am-" + defaultAm).addClass("select");
		}
		function changePhone(num) {
			myPhone.val(num);
			myPhone.keyup();	
		}
		$(".more-card").bind("click",function(){
			$(this).toggleClass("more-card-click");
			$("#history").toggleClass("history-hide");
			event.stopPropagation();
		});
		function hideHistory() {
			$(".more-card").toggleClass("more-card-click");
			$("#history").toggleClass("history-hide");
		}
		$("#containerID").bind("click",function(){
			if(!$("#history").hasClass("history-hide")) {
				hideHistory();
			}
		});
		$("#containerID").bind("touchmove",function(){
			if(!$("#history").hasClass("history-hide")) {
				hideHistory();
			}
		});
		$("#his_phone").bind("touchmove", function(event){
			event.stopPropagation();
		});
		function clickMe(me, am) {
			clearAll();
			$(me).addClass("select");
			if( remap[am] != null) {
				defaultAm = remap[am];
			} else {
				defaultAm = am;
			}
			$(".font-acc").html((faces[defaultAm]/100).toFixed(2));
			if(faces[defaultAm] != null) {
				$("#face_amount-"+ am).css("margin-left", "30px");	
				$("#dis-"+am).show();
			}
		}
		function clearPrev(ele) {
			$(ele).prev().val("");
			$(ele).hide();
			$(ele).prev().keyup();
			checkCanCharge();
		}
		function charge() {
			var phones = localStorageGet("contact");//部分设备localStorage无法访问处理办法

			if(phones == null) {
				phones = "";
			}else {
				phones += ",";
			}
			var cardNo = myPhone.val();
			if (phones.indexOf(cardNo) < 0) {
				phones += cardNo;
				localStorageSet("contact", phones);//部分设备localStorage无法访问处理办法
			}
			localStorageSet("price", defaultAm);//部分设备localStorage无法访问处理办法
			localStorageSet("page_url", window.location.href);//部分设备localStorage无法访问处理办法
			$("#my_phone").val(cardNo.replace(/\s/g,""));
			$("#is_huodong").val(hd_price);
			$("#my_price").val(defaultAm + "00");
            		var invite_code = window.getInviteCode();
            		if(invite_code != undefined && invite_code != "")
                		$("#ipt_invite").val(invite_code);
            		else
                		$("#ipt_invite").remove();
			$("#my_form")[0].submit();

		}
		var myPhone = $("#ph-no");
		function checkCanCharge(){
			var cardNo = myPhone.val().replace(/\s/g,"");
			if(cardNo.length == 11 && (/^1[0-9]{10}$/.test(cardNo))) {
				allowSubmit();
			}else{
				disableSubmit();
			}
		}
		myPhone.bind("blur",function(){
			setTimeout(function(){myPhone.next().hide();},200);
		}).bind("focus",function(){
			if(myPhone.val().length > 0) myPhone.next().show();
		});

		myPhone.bind("input", function(){
		    checkCanCharge();
			var mcardNo = myPhone.val();
			var tailNo = mcardNo.replace(/\s/g,"");
			if(mcardNo.length == 11 && tailNo.length == 11) {
				if(/^1[0-9]{10}$/.test(mcardNo)){
					$.get("http://baifubao.baidu.com/jump", {uri:"callback",cmd:"1067", callback:"phonePriceInfo_common", mobile_num:mcardNo, device_token:"BaiduWallet_H5_common_charge", channel_no:"CHF0000000"} ,function(response) {
						var res = $.parseJSON(response);
						if(res.result == 0) {
							if(!(res.data.price_info.length>0)) {
								disableSubmit();
								$("#tip").html("该地区话费充值正在维护中，请稍后再试").removeClass("attru").addClass("warning");
								return;
							}
							$("#tip").html(res.data.area_operator).removeClass("warning").addClass("attru");
							var dis_info = res.data.price_info;
							am30.hide();
							am50.hide();
							am100.hide();
							am300.hide();
							for(var i=0; i<dis_info.length;i++) {//加上动态价格标签变化
								var discount = "";
								discount += dis_info[i].sell_price / dis_info[i].face * 10;
								var face = "";
								face += dis_info[i].face/100;
								if(discount == "10") discount = discount.substring(0,2);
								else discount = discount.substring(0,3);
								discount += "折";
								switch(i){
								case 0:
                                                                $("#face_amount-30").html(face+"元");
								$("#dis-30").html(discount);
								remap[30] = face;
								am30.show();
								break;
								case 1:
                                                                $("#face_amount-50").html(face+"元");
								$("#dis-50").html(discount);
								remap[50] = face;
								am50.show();
								break;
								case 2:
                                                                $("#face_amount-100").html(face+"元");
								$("#dis-100").html(discount);
								remap[100] = face;
								am100.show();
								break;
								case 3:
                                				$("#face_amount-300").html(face+"元");
								$("#dis-300").html(discount);
								remap[300] = face;
								am300.show();
								break;
								}
								faces[face] = dis_info[i].sell_price;
							}
							$(".font-acc").html((faces[defaultAm]/100).toFixed(2));
							$("#really").show();
							$("#am-" + defaultAm).addClass("select");
							$("#face_amount-"+ defaultAm).css("margin-left", "30px");
							$("#dis-" + defaultAm).show();
							if(res.data.is_login == 1) is_login = true;
							hd_price = res.data.is_huodong_price;
							myPhone.next().hide();
						}else {
							$("#tip").html(res.result_info).removeClass("attru").addClass("warning");
						}
					});
				}
			}
		});

		myPhone.keyup(function(){
			checkCanCharge();
			var privNo = myPhone.val();
			if (privNo.length > 0) {
				myPhone.next().show();
			} else {
				myPhone.next().hide();
			}
			var e = e || window.event;
			var keyCode = e.keyCode || e.which || e.charCode;
			if(keyCode == 8) {
				if(privNo.length == 9) 	myPhone.val(privNo.substring(0,8));
				else if(privNo.length == 4) 	myPhone.val(privNo.substring(0,3));
				return;
			}
			var cardNo = myPhone.val().replace(/\s/g,"");
			if(cardNo.length == 11) {
				if(/^1[0-9]{10}$/.test(cardNo)){
					$.get("https://www.baifubao.com/callback", {cmd:"1067", callback:"phonePriceInfo_common", mobile_num:cardNo, device_token:"BaiduWallet_H5_common_charge", channel_no:"CHF0000000"} ,function(response) {
						var res = $.parseJSON(response);
						if(res.result == 0) {
							if(!(res.data.price_info.length>0)) {
								disableSubmit();
								$("#tip").html("该地区话费充值正在维护中，请稍后再试").removeClass("attru").addClass("warning");
								return;
							}
							$("#tip").html(res.data.area_operator).removeClass("warning").addClass("attru");
							var dis_info = res.data.price_info;
							am30.hide();
							am50.hide();
							am100.hide();
							am300.hide();
							for(var i=0; i<dis_info.length;i++) {//加上动态价格标签变化
								var discount = "";
								discount += dis_info[i].sell_price / dis_info[i].face * 10;
								var face = "";
								face += dis_info[i].face/100;
								if(discount == "10") discount = discount.substring(0,2);
								else discount = discount.substring(0,3);
								discount += "折";
								switch(i){
								case 0:
                                                                $("#face_amount-30").html(face+"元");
								$("#dis-30").html(discount);
								remap[30] = face;
								am30.show();
								break;
								case 1:
                                                                $("#face_amount-50").html(face+"元");
								$("#dis-50").html(discount);
								remap[50] = face;
								am50.show();
								break;
								case 2:
                                                                $("#face_amount-100").html(face+"元");
								$("#dis-100").html(discount);
								remap[100] = face;
								am100.show();
								break;
								case 3:
                                				$("#face_amount-300").html(face+"元");
								$("#dis-300").html(discount);
								remap[300] = face;
								am300.show();
								break;
								}
								faces[face] = dis_info[i].sell_price;
							}
							$(".font-acc").html((faces[defaultAm]/100).toFixed(2));
							$("#really").show();
							$("#am-" + defaultAm).addClass("select");
							$("#face_amount-"+ defaultAm).css("margin-left", "30px");
							$("#dis-" + defaultAm).show();
							if(res.data.is_login == 1) is_login = true;
							hd_price = res.data.is_huodong_price;
							myPhone.next().hide();
						}else {
							$("#tip").html(res.result_info).removeClass("attru").addClass("warning");
						}
					});
				} else {
					$("#tip").html("手机号码格式有误，请重新输入").removeClass("attru").addClass("warning");
				}
			}else if( cardNo.length && (cardNo.length == 3 || cardNo.length == 7)) {
				myPhone.val(privNo + " ");
			} else if(cardNo.length == 4 && privNo.length == 4) {
				myPhone.val(privNo.substring(0,3) + " " + privNo.substring(3,4));	
			} else if(cardNo.length == 8 && privNo.length == 9) {
				myPhone.val(privNo.substring(0,8) + " " + privNo.substring(8,9));	
			}
		});
	$("#go-sumbit").on("touchstart", function(){$("#go-sumbit").css("background-color","#c3302d");});
	$("#go-sumbit").on("touchend", function(){$("#go-sumbit").css("background-color","#e94643");});
        (function($){
            var phoneString = "";

            var myHistory = localStorageGet("contact");//部分设备localStorage无法访问处理办法
				
            var phones = myHistory == null ? [1111]:myHistory.split(",");
            var length = phones.length;

            for(var i=0; i<length; i++) {
                if (i > 0)   phoneString += "<div style=\"border-top:1px dashed #cccccc;height: 1px;margin-left:8px;margin-right:8px\"></div>";
                phoneString += "<li  onclick=\"javascript:changePhone('"+ phones[i] +"')\"  >" +  phones[i] + "</li>";
            }
			if(myHistory == null) {
				$(".more-card").hide();	
			}

			var TT = initDialogTT();
            var option = {
                body:"<div style=\"font-family: SimHei;background:#ffffff;color:#000000;border:#ccc solid 1px\"><p style=\"padding: 10px;color:#FFFFFF;background:#2C4063;margin: 0px;\" onclick=\"javascript:t.hide()\"><span>选择手机号</span><span style=\"float:right\"><img src=\"https://www.baifubao.com/static/mobile/mobile-pay/style/images/delete.png\" width=\"18\" height=\"18\"></span></p><ul onclick=\"javascript:t.hide()\" class=\"bank-list clearfix\" id=\"bank-ul\" style=\"overflow-y:auto; overflow-x:auto;background-color: #ffffff;\">" + phoneString +"</ul></div>"
            }
            window.t = new TT.UI.Alert(option);
			$("#his_phone").html(phoneString);
        })(Zepto);
	</script>
	<script>
	var _hmt = _hmt || [];
	(function() {
  		var hm = document.createElement("script");
  		hm.src = "//hm.baidu.com/hm.js?b565123dd8be7826fe024354fac5cb5b";
  		var s = document.getElementsByTagName("script")[0]; 
  		s.parentNode.insertBefore(hm, s);
	})();
	</script>
</body>
</html>

<!--27745516350475683850060917-->
<script> var _trace_page_logid = 2774551635; </script><!--27745516350863448586060917-->