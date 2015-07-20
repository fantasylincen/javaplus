package org.hhhhhh.house.spider;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.house.config.GameProperties;
import org.hhhhhh.house.hibernate.dao.Daos;
import org.hhhhhh.house.hibernate.dao.HouseDao;
import org.hhhhhh.house.hibernate.dao.HouseDtoCursor;
import org.hhhhhh.house.hibernate.dto.HouseDto;

import cn.javaplus.log.Log;
import cn.javaplus.mail.MailSenderInfo;
import cn.javaplus.mail.SimpleMailSender;
import cn.javaplus.time.Time;

import com.google.common.collect.Lists;

public class Spider {

	public class SendEmailThread extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					sendEmail();
				} catch (Exception e) {
					e.printStackTrace();
				}
				cn.javaplus.util.Util.Thread.sleep(10000);
			}
		}

		private void sendEmail() {

			List<HouseDto> willNotify = getWillNotify();

			try {
				Log.d("send email...");
				if (willNotify.isEmpty()) {

					Log.d("update list is empty!");
					return;
				}

				String cc = GameProperties.getStringNoTrim("emailContent");
				String content = cc.replace("CONTENT", buildConent(willNotify));

				String title = GameProperties.getString("emailTitle").trim();
				String from = GameProperties.getString("email").trim();
				String host = GameProperties.getString("emailServerHost")
						.trim();
				String port = GameProperties.getString("emailServerPort")
						.trim();
				String pwd = GameProperties.getString("emailPassword").trim();
				String emailTo = GameProperties.getString("emailTo").trim();

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

				Log.d("send successful count:" + willNotify.size());

				marksend(willNotify);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private List<HouseDto> getWillNotify() {
			HouseDao dao = Daos.getHouseDao();
			HouseDtoCursor dto = dao.find("is_send_email", false);
			ArrayList<HouseDto> ls = Lists.newArrayList();
			for (HouseDto d : dto) {
				ls.add(d);
			}
			return ls;
		}

		private void marksend(List<HouseDto> willNotify) {
			for (HouseDto dto : willNotify) {
				dto.setIs_send_email(true);
				Daos.getHouseDao().save(dto);
			}
		}

	}

	public class SpiderThread extends Thread {

		List<FangYuanWangDownloader> downloaders = Lists.newArrayList();

		@Override
		public void run() {
			while (true) {
				try {
					downloadInfos();
				} catch (Exception e) {
					e.printStackTrace();
				}
				cn.javaplus.util.Util.Thread.sleep(5 * Time.MILES_ONE_MIN);
			}
		}

		private void downloadInfos() {

			for (FangYuanWangDownloader d : downloaders) {

				List<HouseDto> ls = d.download();
				for (HouseDto dto : ls) {

					try {
						Daos.getHouseDao().save(dto);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				Log.d("update ok");
			}

		}

		public void addDownloader(FangYuanWangDownloader d) {
			downloaders.add(d);
		}
	}

	private static Spider ins;

	private SpiderThread downloadThread;

	private SendEmailThread sendEmailThread;

	private Spider() {
	}

	private String buildConent(List<HouseDto> willNotify) {
		StringBuilder sb = new StringBuilder();
		for (HouseDto dto : willNotify) {
			String name = dto.getName();

			sb.append(dto.getHref() + "   ");
			sb.append(name + "   ");
			sb.append(dto.getHuxing() + "   ");
			sb.append("￥" + dto.getPrice() + "   ");

			String tel = dto.getTel();
			if (!tel.startsWith("http://")) {
				sb.append(dto.getOwner() + ":" + tel + "   ");
			}

			sb.append("\r\n");
		}
		return sb.toString();
	}

	public static Spider getInstance() {
		if (ins == null)
			ins = new Spider();
		return ins;
	}

	public void ensureStart() {
		if (downloadThread == null) {
			downloadThread = new SpiderThread();
			downloadThread.setPriority(Thread.MAX_PRIORITY);
			downloadThread.addDownloader(new DiYiShiJianDownloader());
			downloadThread.start();
		}
		if (sendEmailThread == null) {
			sendEmailThread = new SendEmailThread();
			sendEmailThread.start();
		}
	}

	public static void main(String[] args) {
		// HouseDtoCursor all = Daos.getHouseDao().find();
		// ArrayList<HouseDto> ls = Lists.newArrayList();
		// for (HouseDto dto : all) {
		// ls.add(dto);
		// }
		//
		// new Spider().sendEmail(ls);

		HouseDtoCursor dto = Daos.getHouseDao().find("is_send_email", false);
		for (HouseDto d : dto) {
			System.out.println(d.getName());
		}
	}
}
