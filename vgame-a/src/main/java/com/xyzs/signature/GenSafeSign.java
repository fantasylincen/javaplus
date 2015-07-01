/*    */ package com.xyzs.signature;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.util.Util;
/*    */ 
/*    */ public class GenSafeSign
/*    */ {
/* 10 */   private static String keys = "";
/*    */ 
/*    */   public static String getGenSafeSign(Map maps, String keys) {
/* 13 */     if (maps.isEmpty()) {
/* 14 */       return "";
/*    */     }
/*    */ 
/* 17 */     if (keys.isEmpty()) {
/* 18 */       keys = keys;
/*    */     }
/*    */ 
/* 21 */     maps.keySet();
/*    */ 
/* 23 */     String params = "";
/*    */ 
/* 25 */     Iterator it = maps.entrySet().iterator();
/* 26 */     while (it.hasNext()) {
/* 27 */       Map.Entry pairs = (Map.Entry)it.next();
/* 28 */       if ((pairs.getKey().equals("sign")) || (pairs.getKey().equals("sig"))) {
/* 29 */         it.remove();
/*    */       }
/*    */       else
/*    */       {
/* 33 */         if (params != "") {
/* 34 */           params = params + "&";
/*    */         }
/*    */ 
/* 37 */         params = params + pairs.getKey() + "=" + pairs.getValue();
/*    */       }
/*    */     }
/* 40 */     String sign = Util.Secure.md5(keys + params);
/*    */ 
/* 42 */     return sign;
/*    */   }
/*    */ }

/* Location:           E:\360data\重要数据\桌面\fqzs\部署脚本\scripts\jars\signature.jar
 * Qualified Name:     com.xyzs.signature.GenSafeSign
 * JD-Core Version:    0.6.2
 */