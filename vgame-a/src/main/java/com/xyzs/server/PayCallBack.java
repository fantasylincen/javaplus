package com.xyzs.server;


import com.xyzs.signature.GenSafeSign;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 支付充值回调接口
 * 
 * @method post
 * @param  string    orderid  订单ID
 * @param  string    uid      用户ID
 * @param  string    serverid 分服ID，默认为0
 * @param  string    amount   充值金额，格式00.00(人民币)
 * @param  string    extra    透传参数
 * @param  string    ts       时间戳
 * @param  string    sign     App签名串
 * @param  string    sig      支付签名串
 * @return json      
 *         array(ret=>状态码,msg=>描述信息)
 *
 * @author baocl@kingnet.com
 * @copyright 2014 xyzs.com
 * @version 1.0 beta
 */

public class PayCallBack {
    
    private static String appKey = "";
    private static String payKey = "";
    
    public static void setAppKey(String appKey) {
        PayCallBack.appKey = appKey;
    }
    
    public static void setPayKey(String payKey) {
        PayCallBack.payKey = payKey;
    }
    
    /**
     * 支付回调方法 
     */
    public static String callBack(String orderid,String uid,String serverid,String amount,String extra,String ts,String sign,String sig) {
        if(orderid.isEmpty() || uid.isEmpty()) {
            return responseResult(1);
        }
        
        Map maps = new TreeMap();
        maps.put("uid",uid);
        maps.put("orderid",orderid);
        maps.put("serverid",serverid);
        maps.put("amount",amount);
        maps.put("extra",extra);
        maps.put("ts", ts);
        
        // 验证App签名串
        String genSafeSign = GenSafeSign.getGenSafeSign(maps, PayCallBack.appKey);
        if(!genSafeSign.equalsIgnoreCase(sign)) {
            return responseResult(6);
        }
        
        // 如果支付签名串存在就验证
        if(!sig.isEmpty()) {
            genSafeSign = GenSafeSign.getGenSafeSign(maps, PayCallBack.payKey); 
            if(!genSafeSign.equalsIgnoreCase(sig)) {
                return responseResult(6);
            }
        }
        
        
        //TODO 处理发货逻辑
        boolean callbackResult = true;
         
                
        if(callbackResult) {
            return responseResult(0);
        }
        
        return responseResult(8);
    }
    
    /**
     * 响应结果方法
     * @throws JSONException 
     */
    public static String responseResult(int code) {
    	String msg = "";
    	
        switch(code) {
            case 0: msg = "回调成功"; break;
            case 1: msg = "参数错误"; break;
            case 2: msg = "玩家不存在"; break;
            case 3: msg = "游戏服不存在"; break;
            case 4: msg = "订单已存在"; break;
            case 5: msg = "透传信息错误"; break;
            case 6: msg = "签名校验错误"; break;
            case 7: msg = "数据库错误"; break;
            case 8: 
            default:
            	code= 8;
            	msg = "其它错误"; 
            	break;
        }
        
        JSONObject json = new JSONObject();
        
        try {
			json.put("ret",code);
			json.put("msg",msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        return json.toString();
    } 
}
