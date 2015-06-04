package cn.mxz.base.exception;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import cn.javaplus.mail.MailSenderInfo;
import cn.javaplus.mail.MyStringWriter;
import cn.javaplus.mail.SimpleMailSender;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.util.debuger.Debuger;

public class ExceptionMailSenderImpl implements ExceptionMailSender {

	private Properties						property;

	private MailThread						thread;

	private static ExceptionMailSenderImpl	instance;

	private ExceptionMailSenderImpl() {
		property = new Properties();

		try {

			property.load(new FileReader(new File("res/exception_mail.properties")));

		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	public static final ExceptionMailSenderImpl getInstance() {
		if (instance == null) {
			instance = new ExceptionMailSenderImpl();
		}
		return instance;
	}

	private Queue<ExceptionMail>	exceptions	= new LinkedList<ExceptionMail>();

	@Override
	public void send(Throwable e) {

		sendMail(e, property.get("default") + "");

		for (Object key : property.keySet()) {

			Object mailPath = property.get(key);

			if (exceptionStackTraceContains(e, key)) {

				sendMail(e, mailPath + "");
			}
		}
	}

	/**
	 * @param e
	 * @param mailPath
	 *            邮件路径
	 */
	private void sendMail(Throwable e, String mailPath) {

		exceptions.add(new ExceptionMail(e, mailPath));

		if (thread == null) {

			thread = new MailThread();

			thread.start();
		}
	}

	private boolean exceptionStackTraceContains(Throwable e, Object key) {

		StackTraceElement[] stackTrace = e.getStackTrace();

		for (StackTraceElement es : stackTrace) {

			if ((es + "").contains(key + "")) {

				return true;
			}
		}

		return false;
	}

	/**
	 * 邮件发送线程
	 *
	 * @author 林岑
	 *
	 */
	private class MailThread extends Thread {

		public MailThread() {

			setDaemon(true);
		}

		@Override
		public void run() {

			while (!exceptions.isEmpty()) {

				ExceptionMail poll = exceptions.poll();

				send(poll);

				cn.javaplus.util.Util.Thread.sleep(2000);
			}

			thread = null;
		}

		/**
		 * 发送该邮件
		 *
		 * @param poll
		 */
		private void send(ExceptionMail poll) {

			// 这个类主要是设置邮件
			MailSenderInfo mailInfo = new MailSenderInfo();

			mailInfo.setMailServerHost("smtp.163.com");

			mailInfo.setMailServerPort("25");

			mailInfo.setValidate(true);

			mailInfo.setUserName("15111981112@163.com");

			// 您的邮箱密码
			mailInfo.setPassword("258819045");

			mailInfo.setFromAddress("15111981112@163.com");

			mailInfo.setToAddress(poll.getMailPath());

			mailInfo.setSubject(ServerConfig.getServerId() + "区报错了:" + poll.getException().getMessage());

			StringBuilder sb = new StringBuilder();

			Writer w = new MyStringWriter(sb);

			PrintWriter s = new PrintWriter(w);

			poll.getException().printStackTrace(s);

			String textContent = sb + "";

			mailInfo.setContent(textContent);

			Debuger.debug("ExceptionMailSenderImpl.MailThread.send() 发送异常" + poll.getException().getMessage() + "	 至邮件:" + poll.getMailPath());

			SimpleMailSender ss = new SimpleMailSender();

			ss.sendTextMail(mailInfo);
		}
	}
}
