package cn.javaplus.security;

import java.security.MessageDigest;

public class MD5 {

	public static String getMD5(String text){

		StringBuffer md5=new StringBuffer();

		try{

			MessageDigest md=MessageDigest.getInstance("MD5");

			md.update(text.getBytes());

			byte[] digest=md.digest();

			for(int i=0;i<digest.length;i++){

				md5.append(Character.forDigit((digest[i]&0xF0)>>4,16));

				md5.append(Character.forDigit((digest[i]&0xF),16));
			}

		}catch(Exception e){

			e.printStackTrace();
		}

		return md5.toString();
	}
}
