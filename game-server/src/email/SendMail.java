package email;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	/**
	 * 发送简单邮件
	 * @param str_from ：发件人地址
	 * @param str_to : 收件人地址
	 * @param str_title ：邮件标题
	 * @param str_content ：邮件正文
	 */
	public static void send( String str_from, String str_to, String str_title, String str_content ) {

		// str_content="<a href='www.163.com'>html元素</a>"; //for testing send
		// html mail!

		try {
			// 建立邮件会话
			Properties props = new Properties(); // 用来在一个文件中存储键-值对的，其中键和值是用等号分隔的，
			// 存储发送邮件服务器的信息
			props.put("mail.smtp.host", Constant.mailServer);
			// 同时通过验证
			props.put("mail.smtp.auth", "true");
			// 根据属性新建一个邮件会话
			Session s = Session.getInstance(props);
			s.setDebug(true); // 有他会打印一些调试信息。

			// 由邮件会话新建一个消息对象
			MimeMessage message = new MimeMessage(s);

			// 设置邮件
			InternetAddress from = new InternetAddress(str_from); // pukeyouxintest2@163.com
			message.setFrom(from); // 设置发件人的地址
			//
			// //设置收件人,并设置其接收类型为TO
			InternetAddress to = new InternetAddress(str_to); // pukeyouxintest3@163.com
			message.setRecipient(Message.RecipientType.TO, to);

			// 设置标题
			message.setSubject(str_title); // java学习

			// 设置信件内容
			// message.setText(str_content); //发送文本邮件 //你好吗？
			message.setContent(str_content, "text/html;charset=utf8"); // 发送HTML邮件
																		// //<b>你好</b><br><p>大家好</p>
			// 设置发信时间
			message.setSentDate(new Date());

			// 存储邮件信息
			message.saveChanges();

			// 发送邮件
			Transport transport = s.getTransport("smtp");
			// 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
			transport.connect( Constant.mailServer, Constant.mailCount, Constant.mailPassword );
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String content_form 	= "<p>Dear MiniLegend player,<br>Here is your game password {0} , see you in the game :)</p><br><font color='#CD0000'>MiniLegend dev team.</font>";
	public static String title 			= "MiniLegend password retrieve";
	
	
	public static void main(String[] args) {
		
		String content = MessageFormat.format( content_form, "'asdsadsa'" );
		
		// 测试用的，你吧你想写的内容写上去就行   80558895
		send( Constant.mailAddress, "80558895@qq.com", title, content );

	}
}