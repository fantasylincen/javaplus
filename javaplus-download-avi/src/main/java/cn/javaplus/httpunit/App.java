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
public class App {
	private static final String URL = "http://btkitty.org/search/e7aj9fmu9QA/";
//	http://btkitty.org/search/e7aj9fmu9QA/  
//	http://btkitty.org/search/e7JzMwA/

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		for (int j = 1; j < 1000; j++) {
			getPage(j);
		}
	}

	private static void getPage(int i) throws IOException,
			MalformedURLException {
		WebClient wc = new WebClient();
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待

		String u = URL + i
				+ "/0.html";
		HtmlPage page = wc.getPage(u);
		Pattern p = Pattern.compile("http://btkitty.org/torrent/.+html");
		Matcher m = p.matcher(page.asXml());
		System.out.println("page=" + i);

		FileWriter out = new FileWriter("magnets.txt", true);
		out.write("\r");
		out.write("\r");
		out.write("  ------------------------  " + u + "  ------------------------\r");
		out.write("\r");
		out.write("\r");
		out.flush();
		out.close();
		
		wc.closeAllWindows();
		while(m.find()) {
			String toUrl = m.group();
			System.out.println(toUrl);
			saveMagnet(toUrl);
			
			
		}
	}

//			href='magnet:?xt=urn:btih:00b1d82fdd5462ace92a62b45887f6a273b236c0&amp;dn=%E5%B7%A8%E4%B9%B3+9.22'>
	private static void saveMagnet(String toUrl) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient wc = new WebClient();
		wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待

		HtmlPage page = wc.getPage(toUrl);
		Pattern p = Pattern.compile("<a href=.magnet.+>");
//		<a href="magnet:?
		String asXml = page.asXml();
		Matcher m = p.matcher(asXml);
		
		while(m.find()) {
			String r = m.group();
			r = r.replaceAll("<a href=.", "").replaceAll("\">", "");
			System.out.println(r);
			FileWriter out = new FileWriter("magnets.txt", true);
			out.write(r+"\r");
			out.flush();
			out.close();
		}
		wc.closeAllWindows();
	}
}
