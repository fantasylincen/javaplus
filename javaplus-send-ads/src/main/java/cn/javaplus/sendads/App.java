package cn.javaplus.sendads;

import java.io.File;
import java.net.URL;
import java.util.List;

import cn.javaplus.util.Util;

import com.google.common.io.Resources;

/**
 * Hello world!
 * 
 */
public class App {
	static int count = getSendCount();
	static int index = 0;

	public static void main(String[] args) {
		new Thread() {
			public void run() {

				List<String> emailList = getEmailList();
				for (String email : emailList) {
					send(email);
					Util.Thread.sleep(20000);
				}
			};
		}.start();
	}

	private static List<String> getEmailList() {
		List<String> sends = Util.File.getLines(Resources
				.getResource("emails.txt"));
		return sends;
	}

	private static void send(String email) {
		SendHtmlMail s = new SendHtmlMail();
		String c = Util.File.getContent(Resources.getResource("module.html"));
		c = c.replaceAll("EMAIL_ID", count + "");
		c = c.replaceAll("RANDOM_STR", Util.Random.getRandomString(300));

		String user = getRandomUser();

		s.setContent(c);
		s.setFrom(user);
		s.setPassword("258819045");
		s.setSMTPHost("smtp.163.com");
		s.setSubject(getRandomTitle());
		s.setTo(email + "@qq.com");
		s.setUser(user);
		s.send();

		System.out.println("user:" + user);
		saveSendCount(++count);
		saveLastEmail(email);
	}

	private static String getRandomTitle() {
		List<String> titles = Util.File.getLines(Resources
				.getResource("titles.txt"));
		return Util.Random.getRandomOne(titles);
	}

	private static void saveLastEmail(String email) {
		Util.File.write(new File("src/main/resources/last-email.txt"), email);
	}

	private static void saveSendCount(int i) {
		File file = new File("src/main/resources/send-count.txt");
		Util.File.write(file, i + "");
	}

	private static int getSendCount() {
		URL resource = Resources.getResource("send-count.txt");
		String content = Util.File.getContent(resource);
		String c = content.trim();
		return new Integer(c);
	}

	private static String getRandomUser() {
		List<String> emails = Util.File.getLines(Resources
				.getResource("myemails.txt"));
		index++;
		if (index > emails.size() - 1)
			index = 0;
		return emails.get(index);
	}
}
