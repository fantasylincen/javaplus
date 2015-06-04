package cn.javaplus.sendads;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuth extends Authenticator {
	String user, password;

	// 设置账号信息
	void setAccount(String user, String password) {
		this.user = user;
		this.password = password;
	}

	// 取得PsswordAuthentication对象
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
}