package cn.mxz.db;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.mxz.testbase.TestBaseAccessed;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.LogsDao;
import db.domain.Logs;
import db.domain.LogsImpl;

public class TestDB extends TestBaseAccessed {

	@Test
	public final void testA() {
		LogsDao DAO = DaoFactory.getLogsDao();

		List<Logs> ls = Lists.newArrayList();

		for (int i = 0; i < 10000; i++) {
			Logs e = new LogsImpl();
			e.setLogId(i);
			e.setUname("a");
			e.setLog("aaaaaaaaaaaaaaaaaaaaaaaaaa");
			e.setCreateTime(new Date());
			e.setType(1);
			ls.add(e);
		}

		Debuger.debug("TestDB.testA() 正在添加");

		DAO.clear();

		long ms = System.currentTimeMillis();
//		for (Logs logs : ls) {
//			DAO.add(logs);
//		}
		DAO.addAll(ls);
		long ms2 = System.currentTimeMillis();

		Debuger.debug("TestDB.testA() 添加完毕" + (ms2 - ms));
	}

	@Test
	public void test2() {
//		DatabaseTransaction trans = user.getDatabaseTransaction();
//		DAO<String, ActivityStatus> DAO = trans.getActivityStatusDAO();
//		trans.end();
	}
}
