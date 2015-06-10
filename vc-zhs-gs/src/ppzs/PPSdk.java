//package ppzs;
//
//import game.events.Event;
//import game.events.all.recharge.RechargeEvent_pp;
//import game.log.Logs;
//
//import java.io.BufferedReader;  
//import java.io.IOException;  
//import java.io.InputStream;  
//import java.io.InputStreamReader;
//import java.security.InvalidKeyException;  
//import java.security.KeyFactory;  
//import java.security.KeyPair;  
//import java.security.KeyPairGenerator;  
//import java.security.NoSuchAlgorithmException;  
//import java.security.SecureRandom;  
//import java.security.interfaces.RSAPrivateKey;  
//import java.security.interfaces.RSAPublicKey;  
//import java.security.spec.InvalidKeySpecException;  
//import java.security.spec.X509EncodedKeySpec;  
//
//import javax.crypto.BadPaddingException;  
//import javax.crypto.Cipher;  
//import javax.crypto.IllegalBlockSizeException;  
//import javax.crypto.NoSuchPaddingException;  
//
//import com.alibaba.fastjson.JSONObject;
//
//import config.recharge.RechargeTemplet;
//import config.recharge.RechargeTempletCfg;
//import recharge.RechargeDataProvider;
//import recharge.RechargeManager;
//import sun.misc.BASE64Decoder;
//import user.UserInfo;
//import user.UserManager;
//
//@SuppressWarnings("restriction")
//public class PPSdk {
//	
//	//默认公钥(openssl)
//    public static final String DEFAULT_PUBLIC_KEY=   
//    		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyMXeYgDt8RYDKMKJUaMq" + "\r\n" +  
//    		"wU+W9ENgr4lEc5hrviM3UsVhndZFHD2D/EKVkcSh6ylbgQ0J3MJYjmX5NBhelKfk" + "\r\n" +  
//    		"7jbhIvto+VYdQ8NiqAcm22LdVSUNfmWRQAlXh/Vf9VH1GkFDD7D1IEsW1lrcTZLw" + "\r\n" +  
//    		"WoVBu6PFqKpCHfbwfl938Si7OGw53mDI8VMTnHjedgEcvv/TOq8JlTVj8CmvboGY" + "\r\n" +  
//    		"Tg06jIzbddaOJ7YWIiFeFuu34RFoFyRmH+qCgbVaAWk9UkBSm1pLEy8iHAIchM6w" + "\r\n" +  
//    		"8jb/ulIqOnpCjI6GePfIatSWnDAUcfoFyLSP1qunoismVOyWafmzfni2hzzU9Px7" + "\r\n" +  
//    		"OwIDAQAB" + "\r\n";
//    
//    
//
//	private static final String appid = "5399";
//  
//    
//    /** 
//     * 私钥 
//     */  
//    private RSAPrivateKey privateKey;  
//  
//    /** 
//     * 公钥 
//     */  
//    private RSAPublicKey publicKey;  
//      
//    /** 
//     * 字节数据转字符串专用集合 
//     */  
//    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
//    
//    /** 
//     * 获取私钥 
//     * @return 当前的私钥对象 
//     */  
//    public RSAPrivateKey getPrivateKey() {  
//        return privateKey;  
//    }  
//    
//    /** 
//     * 获取公钥 
//     * @return 当前的公钥对象 
//     */  
//    public RSAPublicKey getPublicKey() {  
//        return publicKey;  
//    }  
//  
//    /** 
//     * 随机生成密钥对 
//     */  
//    public void genKeyPair(){  
//        KeyPairGenerator keyPairGen= null;  
//        try {  
//            keyPairGen= KeyPairGenerator.getInstance("RSA");  
//        } catch (NoSuchAlgorithmException e) {  
//            e.printStackTrace();  
//        }  
//        keyPairGen.initialize(1024, new SecureRandom());  
//        KeyPair keyPair= keyPairGen.generateKeyPair();  
//        this.privateKey= (RSAPrivateKey) keyPair.getPrivate();  
//        this.publicKey= (RSAPublicKey) keyPair.getPublic();  
//    }  
//  
//    /** 
//     * 从文件中输入流中加载公钥 
//     * @param in 公钥输入流 
//     * @throws Exception 加载公钥时产生的异常 
//     */  
//    public void loadPublicKey(InputStream in) throws Exception{
//        try {  
//            BufferedReader br= new BufferedReader(new InputStreamReader(in));  
//            String readLine= null;  
//            StringBuilder sb= new StringBuilder();  
//            while((readLine= br.readLine())!=null){  
//                if(readLine.charAt(0)=='-'){  
//                    continue;  
//                }else{  
//                    sb.append(readLine);  
//                    sb.append('\r');  
//                }  
//            }  
//            loadPublicKey(sb.toString());  
//        } catch (IOException e) {  
//            throw new Exception("公钥数据流读取错误");  
//        } catch (NullPointerException e) {  
//            throw new Exception("公钥输入流为空");  
//        }  
//    }  
//  
//  
//    /** 
//     * 从字符串中加载公钥 
//     * @param publicKeyStr 公钥数据字符串 
//     * @throws Exception 加载公钥时产生的异常 
//     */  
//    public void loadPublicKey(String publicKeyStr) throws Exception{  
//    	//System.out.println("publicKeyStr:"+ publicKeyStr);
//        try {  
//            BASE64Decoder base64Decoder= new BASE64Decoder();
//            byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);  
//            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
//            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
//            this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);  
//        } catch (NoSuchAlgorithmException e) {  
//            throw new Exception("无此算法");  
//        } catch (InvalidKeySpecException e) {  
//            throw new Exception("公钥非法");  
//        } catch (IOException e) {  
//            throw new Exception("公钥数据内容读取错误");  
//        } catch (NullPointerException e) {  
//            throw new Exception("公钥数据为空");  
//        }  
//    }  
//  
//    /** 
//     * 公钥加密过程 
//     * @param publicKey 公钥 
//     * @param plainTextData 明文数据 
//     * @return 
//     * @throws Exception 加密过程中的异常信息 
//     */  
//    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{  
//        if(publicKey== null){  
//            throw new Exception("加密公钥为空, 请设置");  
//        }  
//        Cipher cipher= null;  
//        try {
//        	//使用默认RSA
//            cipher= Cipher.getInstance("RSA");
//            //cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());    
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
//            byte[] output= cipher.doFinal(plainTextData);  
//            return output;  
//        } catch (NoSuchAlgorithmException e) {  
//            throw new Exception("无此加密算法");  
//        } catch (NoSuchPaddingException e) {  
//            e.printStackTrace();  
//            return null;  
//        }catch (InvalidKeyException e) {  
//            throw new Exception("加密公钥非法,请检查");  
//        } catch (IllegalBlockSizeException e) {  
//            throw new Exception("明文长度非法");  
//        } catch (BadPaddingException e) {  
//            throw new Exception("明文数据已损坏");  
//        }  
//    }  
//
//    /** 
//     * 公钥解密过程 
//     * @param publicKey 公钥 
//     * @param cipherData 密文数据 
//     * @return 明文 
//     * @throws Exception 解密过程中的异常信息 
//     */  
//    public byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception{  
//        if (publicKey== null){  
//            throw new Exception("解密公钥为空, 请设置");  
//        }  
//        Cipher cipher= null;  
//        try {  
//        	//使用默认RSA
//            cipher= Cipher.getInstance("RSA");
//            //cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
//            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
//            byte[] output= cipher.doFinal(cipherData);  
//            return output;  
//        } catch (NoSuchAlgorithmException e) {  
//            throw new Exception("无此解密算法");  
//        } catch (NoSuchPaddingException e) {  
//            e.printStackTrace();  
//            return null;  
//        }catch (InvalidKeyException e) {  
//            throw new Exception("解密公钥非法,请检查");  
//        } catch (IllegalBlockSizeException e) {  
//            throw new Exception("密文长度非法");  
//        } catch (BadPaddingException e) {  
//            throw new Exception("密文数据已损坏");  
//        }         
//    }  
//  
//      
//    /** 
//     * 字节数据转十六进制字符串 
//     * @param data 输入数据 
//     * @return 十六进制内容 
//     */  
//    public static String byteArrayToString(byte[] data){  
//        StringBuilder stringBuilder= new StringBuilder();  
//        for (int i=0; i<data.length; i++){  
//            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移   
//            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);  
//            //取出字节的低四位 作为索引得到相应的十六进制标识符   
//            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
//            if (i<data.length-1){  
//                stringBuilder.append(' ');  
//            }  
//        }  
//        return stringBuilder.toString();  
//    }  
//  
//  
//    public static void main(String[] args){  
//    	PPSdk rsaEncrypt= new PPSdk();  
//        //rsaEncrypt.genKeyPair();   
//  
//        //加载公钥   
//        try {  
//            rsaEncrypt.loadPublicKey(PPSdk.DEFAULT_PUBLIC_KEY);  
//            System.out.println("加载公钥成功");  
//        } catch (Exception e) {  
//            System.err.println(e.getMessage());  
//            System.err.println("加载公钥失败");  
//        }  
//        
//        //文档测试数据 
//        String testDataStr = "XB3CRx/WYJlmz6dkwuT3XIzCF6Oj/YdghViv6HfPzY7zlMG6M9ZgxHZGK5x2Yz/ICDeQ5NtBwsC12Lfz75mViLfU7agKZE0yxTgiuTwxmemSAEbTAXGT3FkRwCwyzYpJaCilZ/EQXugyyLSVx+DRb4Yf9HMa8pSW+ryu5lIwGKuMdbMAHGYOIAXtgrDw0bMmlO2556kuM8zAWaAO/F9CjhaxuY8daDHx+1ZjAsYHzKnRYNIXu0SzeZEX65pTLgX5nBOHDO2KutqJHO+EBWqlrseshO/KePnl8fncDzxdJecuYWPJd0Tei60IEGrwOnxY4TtXvmRDBxPHrGgZLEV1sw==";    
//  
//        try {
////            BASE64Encoder base64Encoder = new BASE64Encoder();
//            BASE64Decoder base64Decoder = new BASE64Decoder();
//            
//            byte[] dcDataStr = base64Decoder.decodeBuffer( testDataStr );
//            byte[] plainData = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), dcDataStr );  
//            System.out.println("文档测试数据明文长度:" + plainData.length);  
//            System.out.println(PPSdk.byteArrayToString(plainData));
//            System.out.println(new String(plainData));
//            JSONObject json	= JSONObject.parseObject( new String(plainData) );
//            System.out.println( json.get( "order_id" ) );
//            System.out.println( json.get( "account" ) );
//            System.out.println( json.get( "amount" ) );
//            System.out.println( json.get( "billno" ) );
//            System.out.println( json.get( "status" ) );
//            System.out.println( json.toJSONString() );
//        } catch (Exception e) {  
//            System.err.println(e.getMessage());  
//        }  
//    }
//
//    /**
//     * 开始验证 并充值
//     * @param content
//     * @param order_id
//     * @param billno
//     * @param account
//     * @param amount
//     * @param status
//     * @param app_id
//     * @param uuid
//     * @param roleid
//     * @param zone
//     * @param sign
//     * @return
//     * @throws Exception 
//     */
//	public static boolean payResultNotify(String content, String order_id, String billno, String account,
//			String amount, String status, String app_id, String uuid, String roleid, String zone, String sign) throws Exception {
//		
//		PPSdk rsaEncrypt	= new PPSdk();
//		
////		System.out.println( content );
////		System.out.println( sign );
//		//加载公钥   
//        rsaEncrypt.loadPublicKey( PPSdk.DEFAULT_PUBLIC_KEY );  
//        BASE64Decoder base 	= new BASE64Decoder();
//        byte[] dcDataStr 	= base.decodeBuffer( sign );
//        byte[] plainData 	= rsaEncrypt.decrypt( rsaEncrypt.getPublicKey(), dcDataStr ); 
//        JSONObject json		= JSONObject.parseObject( new String(plainData) );
//        
////        System.out.println( json.toJSONString() );
//        
//		RechargeTemplet r	= RechargeTempletCfg.get( Short.parseShort( billno.split("-")[0] ) );
//		UserInfo user 		= UserManager.getInstance().getByName( Integer.parseInt( roleid ) );
//		short code 			= 1;
//		
//		do{
//			if( user == null ) { Logs.error( "充值错误  user为空  roleid=" + roleid ); break; }
//			
//			if( !app_id.equals( appid ) ) { Logs.error( user, "请求充值错误  无效app_id=" + app_id ); break; }
//			
////			if ( !"1".equals(status) ) { Logs.error( user, "请求充值错误  无效status=" + status ); break; }
//			
//			// 检查秘银是否正确
//			if( 	!json.get( "billno" ).equals( billno ) || 
//					!json.get( "amount" ).equals( amount ) || 
////					!json.get( "status" ).equals( status ) ||
//					!json.get( "order_id" ).equals( order_id ) ||
//					!json.get( "account" ).equals( account )
//			){
//				Logs.error( user, "请求充值错误  sign错误json=" + json.toJSONString() + ", content=" + content ); 
//				break;
//			}
//			
//			// 然后验证数据库 是否有这个交易ID了
//			if( RechargeDataProvider.getInstance().verify( user, order_id ) ){  Logs.error( user, "请求充值错误  数据库已经有这个交易ID=" + order_id ); break; }
//			
//			// 先从配置表验证 这个商品ID 是否正确
//			if( r == null ) { Logs.error( user, "请求充值错误  没有ID=" + billno ); break; };
//			
//			// 如果是充值月卡  那就看是否还可以充值
////			if( r.isMonthCard() ) if( !user.isCanRecharge( r.getId()%100 ) ){ Logs.error( user, "请求充值错误  月卡天数已经上限 不能再冲" ); break ; }
//			
//			// 如果是限购 不能再买  这里如果加了 限制会导致前端购买了 扣了钱 但是不给水晶
//			//if( r.isRestriction() ) if( !user.chekCanBuyRestriction( r.getId() ) ) { CLog.error( user, "请求充值错误  已经购买过限购 不在再买" ); break ; }
//			
////			float money 	= Float.parseFloat( orderMoney );
////			int quantity	= Integer.parseInt( goodsCount );
//			// 开始保存数据库
//			if( !RechargeDataProvider.getInstance().create( user, order_id, String.valueOf( r.getId() ), r.getMoney(), 1, content ) ) break;
//			
//			// 开始发放水晶
//			RechargeManager.getInstance().newHandler( user, order_id, 1, r, "buy" );
//			
//			UserManager.getInstance().putUpdate( user );
//			
//			code = 0;
//		} while ( false );
//		
//		if( user != null ) {
//			RechargeEvent_pp rec_pp = (RechargeEvent_pp) Event.RECHARGE_EVENT_PP.getEventInstance();
//			rec_pp.run( user , code, billno );
//		}
//		
//		return true;
//	}
//	
//	
//	
//}
