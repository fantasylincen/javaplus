package cn.javaplus.sendads;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHtmlMail {

	String SMTPHost = "";
	String user = "";
	String password = "";
	String from = "";
	String to = "";
	String subject = "";
	String content = "";

	public SendHtmlMail() {

	}

	public String getSMTPHost() {
		return SMTPHost;
	}

	public void setSMTPHost(String host) {
		SMTPHost = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean send() {
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTPHost);
		props.put("mail.smtp.auth", "true");
		try {
			SmtpAuth auth = new SmtpAuth();
			auth.setAccount(user, password);
			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			message.setSentDate(new Date());
			message.setHeader("X-Priority", "1");
			message.saveChanges();
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(SMTPHost, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}