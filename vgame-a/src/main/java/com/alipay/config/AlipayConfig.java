package com.alipay.config;


public class AlipayConfig {
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088711833258389";
	
	// 收款支付宝账号
	public static String seller_email = "vchudong@gmail.com";

	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = partner;
	
	// 商户的私钥
	public static String private_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9X0FOOYZjp5tiE59K2RwV6GyO/nkFhxgNhza8Ram7qEDo9oL5gkN/pADO2B+AG45ml/tmmnyK+KZnsDb5sA/6odi/38xZo3WLdX/I++KhsFmq06Se6BpqD195Nx7FPN2ehijfdMbd6jdjJHwR+9lrsNYnC3iJge3ZqS5sIMzccwIDAQAB";
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
