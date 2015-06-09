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
	public static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALl07+aVNocjunFTxGzGTkVYDSePWnEBQTi5rDI+UH5fwurSB5adBqa22U9te0JREriGxNLO92YWxbM5LDDhjsf6P13ykkJJEpEMt1bECNx/e+EIIU1skREsvSzx3uHY3doDsI6f+kJGH0dAaFqk88mYdV+fbv9+KVQCh+hcqaAlAgMBAAECgYArEoqpPhyE9HpX2cG0FbaWCmY7uuKhfk4ck5r3rFtbjnghu5gBlgS2cZ81AFNxSo0TiFFsOJ9i+YT/JBcsXyaezAZ0utEg2ebEEoERtQPf3ffNBwUybfWi9s/G5Q2gCqXMWT7U9aKeOV/s9TrVSHpbdEAZwtmBfqgFyhnOxA4S3QJBAOh2QIue76ZLRc4HLJWEWueBrv5V/CD67PIKfD+EoL3FojFBU73l2LPmKEbwHs1tDFCPRZQ8kD0gn2gYnqCbzmMCQQDMPD5/QZHbg9V5gFhdamfDNRhgLBGmaELMZ3sukWbcD2ewEgzO8mx/bxZPZplVL6cE7v+sLsdzD7pv9QAR+/nXAkEAtbi7fGoirbzqNMUm9TsiVRfr1KcjEiUWVaAcwQrNHY0B3b9T1392iCvEELBlY/F7Tv6xRdCScTavZpDYcHVcQQJAKm9mCOjl1nd49ordUWOXda7lhq4BaNMG9hOWO6HIfnbQ83t8n88tDflV6F+IAnQpPtQt2WfK1zRwZ2y/e/0f9wJAE6o9FjeUCoYk+px1YgPFvNlpAIu3+ED9TxSdf0aEzObbqvQ+RlNdKdFGPhRvl+rbIQinAPeKJFMNHjO6/PxT/Q==";
	
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
