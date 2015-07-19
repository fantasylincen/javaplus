package org.hhhhhh.guess.hibernate.dto;

import org.junit.Test;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;

public class ActionTests {

	static String httpHead = "http://localhost/guess/";

	@Test
	public void testRegist() {
		String KEY = "JJYSB";
		String username = "574907580@qq.com";
		String password = "123456";
		long time = System.currentTimeMillis();
		String flag = Util.Secure.md5(username + password + time + KEY);
		
		String url = httpHead + "account4app/regist?username=USERNAME&password=PASSWORD&time=TIME&flag=FLAG";
		url = url.replace("USERNAME", username);
		url = url.replace("PASSWORD", password);
		url = url.replace("TIME", time + "");
		url = url.replace("FLAG", flag);
		
		String content = WebContentFethcer.get("utf8", url);
		Log.d(content);
	}
	

	@Test
	public void testLogin() {
		String username = "574907580@qq.com";
		String password = "123456";
		
		String url = httpHead + "account4app/login?username=USERNAME&password=PASSWORD";
		url = url.replace("USERNAME", username);
		url = url.replace("PASSWORD", password);
		
		String content = WebContentFethcer.get("utf8", url);
		Log.d(content);
	}
}
