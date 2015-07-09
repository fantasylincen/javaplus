package org.hhhhhh.prophet.account4web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;
import org.hhhhhh.prophet.config.GameProperties;
import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.SystemKeyValueDao;
import org.hhhhhh.prophet.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.prophet.user.User;

import cn.javaplus.mail.MailSenderInfo;
import cn.javaplus.mail.SimpleMailSender;
import cn.javaplus.util.Util;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport {

	private static final long serialVersionUID = -7706071822124421174L;
	private HttpSession session;

	private String email;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		int errorCode;

		User user = Server.loadUserByUsername(getEmail());

		if (user != null) {
			String code = generateResetEmailCode();
			sendEmail(code);
			errorCode = 0;
		} else {
			errorCode = 1;
		}

		session.setAttribute("reset_password_error_code", errorCode);

		if (errorCode != 0) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	private String generateResetEmailCode() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = new SystemKeyValueDto();

		String random = Util.Random.getRandomString(32)
				+ Util.Secure.md5(Util.ID.createId());
		random = random.toLowerCase();

		String key = "RESET_PASSWORD:" + random;
		String value = getEmail() + ":" + System.currentTimeMillis();

		dto.setKey(key);
		dto.setValue(value);

		dao.save(dto);
		return random;
	}

	private void sendEmail(String code) {
		String cc = GameProperties.getStringNoTrim("emailContent");
		String content = cc.replace("EMAIL_CODE", code);
		String title = GameProperties.getString("emailTitle").trim();
		String from = GameProperties.getString("email").trim();
		String host = GameProperties.getString("emailServerHost").trim();
		String port = GameProperties.getString("emailServerPort").trim();
		String pwd = GameProperties.getString("emailPassword").trim();

		String emailTo = getEmail();

		SimpleMailSender s = new SimpleMailSender();
		MailSenderInfo m = new MailSenderInfo();
		m.setValidate(true);
		m.setMailServerHost(host);
		m.setMailServerPort(port);
		m.setUserName(from);
		m.setPassword(pwd);
		m.setFromAddress(from);
		m.setToAddress(emailTo);
		m.setSubject(title);
		m.setContent(content);
		s.sendTextMailInThread(m);
	}

	public static void main(String[] args) {

		ResetPasswordAction a = new ResetPasswordAction();

		a.setEmail("574907580@qq.com");
		a.sendEmail("absdfawer");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
