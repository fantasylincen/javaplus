package cn.mxz.activity.boss;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mxz.base.config.Cfg;

import com.lemon.commons.database.DBProperties;
import com.lemon.commons.database.DataBasePool;

import db.dao.impl.DaoFactory;
import db.dao.impl.UserDataDao;
import db.domain.UserData;

public class BossActivityTest/* extends TestBaseAccessed */{

	//	public BossService getService() {
	//
	//		return ((BossService) factory.get("bossService"));
	//	}
	//
	//	@Test
	//	public void testEnter() {
	//		getService().enter(1);
	//
	//	}
	@Test
	public void addboss() {

		DBProperties p = new DBProperties();

		p.setDataCon("jdbc:mysql://192.168.1.2:3306/mobile10005");
		p.setDrives(Cfg.DBDriver);
		p.setPassword("mxz2013");
		p.setUser("root");

		DataBasePool fecher = new DataBasePool(p);


		UserDataDao DAO = DaoFactory.getUserDataDao();

		for (int i = 0; i < 22; i++) {

			List<UserData> list = new ArrayList<UserData>();

			for (int aa = 0; aa < 22222; aa++) {

				UserData d = DAO.createDTO();

				d.setUname("c" + i + "," + aa);

				d.setNick("cn" + i + "," + aa);

				list.add(d);

				System.out.println("add "  + i + "," + aa);
			}

			DAO.addAll(list);
		}
	}

}
