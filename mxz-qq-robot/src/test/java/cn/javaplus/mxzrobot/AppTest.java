package cn.javaplus.mxzrobot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scala.collection.mutable.StringBuilder;
import cn.javaplus.file.IProperties;
import cn.javaplus.mxzrobot.actions.Action;
import cn.javaplus.mxzrobot.events.Ask;
import cn.javaplus.mxzrobot.listeners.AskFinder;
import cn.javaplus.util.Util;
import cn.mxz.util.debuger.Debuger;

/**
 * Hello world!
 */
public class AppTest {

//	private final static String EMAIL_REGEX = "[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})"; /* 正則表達式 */
//	private final static String CHUNK_TYPE = "email"; /* 未知何用, constructor需要 */
//	private final static double CHUNK_SCORE = 0.0; /* 未知何用, constructor需要 */
//
//	public AppTest() {
//		super(EMAIL_REGEX, CHUNK_TYPE, CHUNK_SCORE); /* 就是上面3個 */
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] a) throws Exception {
//		String aaa = "My email is gorebill@163.com and my friend's is superkillball@gmail.com."; /* 這個String就系要分析噶String */
//		Chunker chunker = new AppTest();
//		Chunking chunking = chunker.chunk(aaa);
//		System.out.println("input=" + aaa);
//		System.out.println("chunking=" + chunking);
//		Set<Chunk> chunkSet = chunking.chunkSet();
//		Iterator<Chunk> it = chunkSet.iterator();
//
//		while (it.hasNext()) {
//			Chunk chunk = it.next();
//			int start = chunk.start();
//			int end = chunk.end();
//			String text = aaa.substring(start, end);
//			System.out.println("     chunk=" + chunk + "  text=" + text);
//		}
//	}
//				input=My email is gorebill@163.com and my friend's is superkillball@gmail.com.
//				chunking=My email is gorebill@163.com and my friend's is superkillball@gmail.com. : [12-28:email@0.0, 48-71:email@0.0]
//			    chunk=12-28:email@0.0  text=gorebill@163.com
//			    chunk=48-71:email@0.0  text=superkillball@gmail.com


	public static void main(String[] args) throws ParseException, IOException {
//		// String c = "给localhost:9999增加1111元宝5qu";
//		String c = "lc私服给lc1增加10元宝";
//		Ask a = new AskFinder().find(c);
//		if (a != null) {
//			Action ac = a.getAction();
//			String result = ac.execute(a.getArgs());
//			System.out.println(result);
//		} else {
//			Debuger.debug("enclosing_type.enclosing_method() not found");
//		}
//		//
//		// ServerMessageSender s = new ServerMessageSender();
//		// String send = s.send("pvp_robot_sid_559002i_1", "localhost", 31510,
//		// " System.out.println(\"yoXi\");  ");
//		// System.out.println(send);
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = date.parse("2014-10-14");
//		System.out.println(d.getTime());
		replace();
	}
	//
	//
	//
	// //
	 // public static void main(String[] args) {
	 // MessageExtractor e = new MessageExtractor();
	 // e.defineFormat("你是?");
	 // Map<String, String> result = e.getResult("你是?");
	 // System.out.println(result);

	private static void replace() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		String path = "C:/Users/Administrator/Desktop/ax.txt";
		String content = Util.File.getContent(path);
		InputStreamReader ir = new InputStreamReader(new FileInputStream("D:/workspace/MobileServer/res/build/translate.properties"), "utf8");
		Properties p = new Properties();
		p.load(ir);
		Set<Object> keySet = p.keySet();
		for (Object k : keySet) {
			String key = k + "";
			String value = p.get(key) + "";
			content = content.replaceAll(key, value);
			
//			1413216001
		}

		String[] split = content.split("\r");
		
		Pattern c = Pattern.compile("14[0-9]{8}");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < split.length; i++) {
			Matcher m = c.matcher(split[i]);
			boolean find = m.find();
			if(!find) {
				continue;
			}
			String g = m.group();
			String format = sf.format(new Date(new Long(g) * 1000));
			split[i] = m.replaceFirst(format);
			System.out.println(i++);
		}
		
		content = link(split);
		
		Util.File.write(path, content);
	}
	private static String link(String[] split) {
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			sb.append(s);
			sb.append("\r");
		}
		return sb.toString();
	}

	// // }
}
