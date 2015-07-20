package org.hhhhhh.guess;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

	public static String iso2Utf8(String s) {
		try {
			s=new String(s.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return s;
	}

}
