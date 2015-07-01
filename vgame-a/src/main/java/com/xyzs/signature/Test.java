/*    */ package com.xyzs.signature;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ public class Test
/*    */ {
/*    */   private static final String AppKey = "b6da5900312eaaae2b3f78b9073106f0";
/*    */   private static final String PayKey = "tdTmL9KWaNBwsW40FH7FVQKEYkx9UYfk";
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 21 */     Map maps = new TreeMap();
/*    */ 
/* 23 */     maps.put("uid", "10001");
/* 24 */     maps.put("orderid", "100001_10001_1407811507_9347");
/* 25 */     maps.put("serverid", "0");
/* 26 */     maps.put("amount", "1.00");
/* 27 */     maps.put("extra", "201408036987");
/* 28 */     maps.put("ts", "1407811565");
/*    */ 
/* 31 */     maps.put("sig", "2957d2b348dec094c5d7f9fb84821d1c");
/* 32 */     maps.put("sign", "6631e0616ac73df7ea610bb4f9e03e0d");
/*    */ 
/* 34 */     String genSafeSign = GenSafeSign.getGenSafeSign(maps, "b6da5900312eaaae2b3f78b9073106f0");
/* 35 */     String genSafeSig = GenSafeSign.getGenSafeSign(maps, "tdTmL9KWaNBwsW40FH7FVQKEYkx9UYfk");
/*    */ 
/* 37 */     System.out.println("sign:" + genSafeSign);
/* 38 */     System.out.println("sig:" + genSafeSig);
/*    */   }
/*    */ }

/* Location:           E:\360data\重要数据\桌面\fqzs\部署脚本\scripts\jars\signature.jar
 * Qualified Name:     com.xyzs.signature.Test
 * JD-Core Version:    0.6.2
 */