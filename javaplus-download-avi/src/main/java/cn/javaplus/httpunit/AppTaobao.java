package cn.javaplus.httpunit;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Hello world!
 *
 */
public class AppTaobao {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient wc = new WebClient();
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待

		HtmlPage page = wc.getPage("http://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-10181608163.2.ZJWwef&id=43907261731");
		System.out.println("page=" + page.asXml());

		
		wc.closeAllWindows();
	}

}