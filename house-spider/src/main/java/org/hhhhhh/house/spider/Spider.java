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

			List<HouseDto> willNotify = Lists.newArrayList();
			for (FangYuanWangDownloader d : downloaders) {

				List<HouseDto> ls = d.download();
				for (HouseDto dto : ls) {

					if (isNew(dto)) {
						willNotify.add(dto);
					}
					Daos.getHouseDao().save(dto);

				}
				Log.d("更新成功");
			}

			sendEmail(willNotify);
		}


		

		private boolean isNew(HouseDto dto) {
			String id = dto.getHref();
			HouseDao dao = Daos.getHouseDao();
			return dao.get(id) == null;
		}

		public void addDownloader(FangYuanWangDownloader d) {
			downloaders.add(d);
		}
	}

	private static Spider ins;

	private SpiderThread thread;

	private Spider() {
	}

	private void sendEmail(List<HouseDto> willNotify) {
		try {
			if(willNotify.isEmpty())
				return;
			
			String cc = GameProperties.getStringNoTrim("emailContent");
			String content = cc.replace("CONTENT", buildConent(willNotify));
			
			String title = GameProperties.getString("emailTitle").trim();
			String from = GameProperties.getString("email").trim();
			String host = GameProperties.getString("emailServerHost").trim();
			String port = GameProperties.getString("emailServerPort").trim();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if (thread == null) {
			thread = new SpiderThread();
			thread.setPriority(Thread.MAX_PRIORITY);
			thread.addDownloader(new DiYiShiJianDownloader());
			thread.start();
		}
	}
	
	public static void main(String[] args) {
//		HouseDtoCursor all = Daos.getHouseDao().find();
//		ArrayList<HouseDto> ls = Lists.newArrayList();
//		for (HouseDto dto : all) {
//			ls.add(dto);
//		}
//		
//		new Spider().sendEmail(ls);
		
		HouseDtoCursor dto = Daos.getHouseDao().findSortByLimit("update_date", 10);
		for (HouseDto d : dto) {
			System.out.println(d.getName());
		}
	}
}
