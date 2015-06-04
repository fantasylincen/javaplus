package cn.javaplus.chatrobot.extractor;

import java.util.Map;

public class ExtractorTest {
	public static void main(String[] args) {
		// * 输入1: student1,student2
		// * 输入2: 今天 student1 把 student2 打了
		// * 输入3: 今天小明把小张打了
		// * 返回: <student1:小明><student2:小张>
		// *

		MessageExtractor message = new MessageExtractor();
		// message.defineArg("student1");
		// message.defineArg("student2");
		// message.defineFormat("今天student1把student2打了 ");
		// message.input("今天小明把小张打了");
//
//		message.defineArg("serverId");
//		message.defineArg("name1");
//		message.defineArg("name2");
//		message.defineFormat("name1name2帮我重启一下服务器serverId懂了");
//
//		Map<String, String> r = message.getResult("周杰伦阿杜帮我重启一下服务器serverIdljkljgi67igb';hlh懂了");
		

		message.defineArg("a1");
		message.defineArg("a2");
		message.defineArg("a3");
		message.defineArg("a4");
		message.defineArg("a5");
		message.defineFormat("a1,a2帮我a3重启一下服务器a4懂了a5");

		Map<String, String> r = message.getResult("周杰伦,阿杜帮我xxx重启一下服务器serverIdljkljgi67igb';hlh懂了yyy");
		System.out.println("ExtractorTest.main()" + r);

	}
}
