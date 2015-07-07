package com.xyzs.demo;

import com.xyzs.server.PayCallBack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baocl
 */
public class Demo {
    
    // appkey 和 paykey 请改成dev后台
    private static String appKey = "b6da5900312eaaae2b3f78b9073106f0";
    private static String payKey = "tdTmL9KWaNBwsW40FH7FVQKEYkx9UYfk";
    
    public static void main(String args[]) {
        // 以下参数请从http接口中接受
        String orderid = "100001_10001_1407811507_9347";
        String uid     = "10001";
        String amount  = "1.00";
        String serverid = "0";
        String extra    = "201408036987";
        String ts       = "1407811565";
        String sign     = "6631e0616ac73df7ea610bb4f9e03e0d";
        String sig      = "2957d2b348dec094c5d7f9fb84821d1c";
        
        PayCallBack.setAppKey(appKey);
        PayCallBack.setPayKey(payKey);
        
        // 发货处理，请在callBack方法中处理发货逻辑
        String callBack = PayCallBack.callBack(orderid, uid, serverid, amount, extra, ts, sign, sig);
        
        
        System.out.println(callBack);
    }
}
