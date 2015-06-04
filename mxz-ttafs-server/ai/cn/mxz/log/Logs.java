package cn.mxz.log;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;
import cn.mxz.xianshi.RecruitResault;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.LogBossAwardDao;
import db.dao.impl.LogBuyDao;
import db.dao.impl.LogConsumeDao;
import db.dao.impl.LogFindFighterDao;
import db.dao.impl.LogLoginDao;
import db.domain.LogBossAward;
import db.domain.LogBossAwardImpl;
import db.domain.LogBuy;
import db.domain.LogBuyImpl;
import db.domain.LogConsume;
import db.domain.LogConsumeImpl;
import db.domain.LogFindFighter;
import db.domain.LogFindFighterImpl;
import db.domain.LogLogin;
import db.domain.LogLoginImpl;

public class Logs {

	public static class FindFighterLog {

		public void addLog(RecruitResault rr, String uname, String nick) {
			LogFindFighterDao DAO = DaoFactory.getLogFindFighterDao();
			LogFindFighter l = new LogFindFighterImpl();

			Hero f = rr.getFighter();
			int spiriteId = rr.getSpiriteId();


			if(f != null) {

				l.setFighterId(f.getTypeId());
				l.setType(1);
			} else {
				l.setFighterId(spiriteId);
				l.setType(2);
			}

			l.setLogId(DaoFactory.nextLogFindFighterLogId());
			l.setTime(Util.Time.getCurrentSec());
			l.setUname(uname);
			l.setNick(nick);

			try {
				DAO.add(l);
			} catch (Exception e) {
				System.err.println("FindFighterLog.addLog 日志添加失败, 无影响");
			}

		}

	}

	public static class BossLog {

		/**
		 * boss掉落日志
		 * @param s
		 * @param city
		 */
		public void addLog(List<Prize> s, City city) {
			LogBossAwardDao DAO = DaoFactory.getLogBossAwardDao();
			List<LogBossAward> ls = Lists.newArrayList();
			for (Prize prize : s) {
				LogBossAward l = new LogBossAwardImpl();
				l.setLogId(DaoFactory.nextLogBossAwardLogId());
				l.setCount(prize.getCount());
				l.setNick(city.getPlayer().getNick());
				l.setUname(city.getId());
				l.setPropId(prize.getId());
				ls.add(l);
			}

			DAO.addAll(ls);
		}

	}

	public static class ConsumeLog {

		public void addLog(int cashNow, int cashOld, int goldNow, int goldOld, int jinBeiKeNow, int jinBeiKeOld, String explain, String id, String nick ) {
			int cashCount = cashNow - cashOld;
			int goldCount = goldNow - goldOld;
			int jinBeiKeCount = jinBeiKeNow - jinBeiKeOld;

			if(cashCount != 0 || goldCount != 0) {

				LogConsumeDao DAO = DaoFactory.getLogConsumeDao();
				LogConsume l = new LogConsumeImpl();
				l.setCashCount(cashCount);
				l.setComment(explain);
				l.setGoldCount(goldCount);
				l.setJinBeiKeCount(jinBeiKeCount);
				l.setLogId(DaoFactory.nextLogConsumeLogId());
				l.setNick(nick);
				l.setTime(Util.Time.getCurrentSec());
				l.setUname(id);
				try {
					DAO.add(l);
				} catch (Exception e) {
					System.err.println("ConsumeLog.addLog 日志添加失败, 无影响");
				}
			}
		}

	}

	public static class BuyLog {

		public void addBuyLog(int toolId, int count, int price, int cashPrice, City city) {
			LogBuyDao DAO = DaoFactory.getLogBuyDao();
			LogBuy l = new LogBuyImpl();
			l.setComment("shopping");
			l.setCount(count);
			l.setLogId(DaoFactory.nextLogBuyLogId());
			l.setNick(city.getPlayer().getNick());
			l.setPropId(toolId);
			l.setTime(Util.Time.getCurrentSec());
			l.setUname(city.getId());
			try {
				DAO.add(l);
			} catch (Exception e) {
				System.err.println("BuyLog.addBuyLog 日志添加失败, 无影响");
			}
		}

	}

	public static class LoginLog {

		/**
		 * 登入日志
		 *
		 * @param u
		 */
		public void addLoginLog(City u) {
			addLoginLog(u, 1);
		}

		private void addLoginLog(City u, int type) {
			LogLoginDao DAO = DaoFactory.getLogLoginDao();
			LogLogin l = new LogLoginImpl();
			l.setLogId(DaoFactory.nextLogLoginLogId() + 10);
			l.setLoginTime(Util.Time.getCurrentSec());
			l.setNick(u.getPlayer().getNick());
			l.setType(type);
			l.setUname(u.getId());
			try {
				DAO.add(l);
			} catch (Exception e) {
				System.err.println("LoginLog.addLoginLog 日志添加失败, 无影响");
			}
		}

		/**
		 * 登出日志
		 *
		 * @param u
		 */
		public void addLoginOutLog(City u) {
			addLoginLog(u, 0);
		}
	}

	public static LoginLog		loginLog	= new LoginLog();
	public static BuyLog		buyLog		= new BuyLog();
	public static ConsumeLog	consume		= new ConsumeLog();
	public static BossLog	bossLog = new BossLog();
	public static FindFighterLog	findFighterLog = new FindFighterLog();

}
