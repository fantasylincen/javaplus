package cn.vgame.a.robot;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.io.Resources;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

public class RobotManager {

	Map<String, Robot> robots = Maps.newConcurrentMap();
	private static List<String> nicks;

	// private Object mustToId;

	public RobotManager() {
		loadFromDb();
	}

	private void loadFromDb() {
		RoleDao dao = Daos.getRoleDao();
		RoleDtoCursor allRobots = dao.findByIsRobot(true);
		for (RoleDto dto : allRobots) {
			String id = dto.getId();
			robots.put(id, new Robot(id));

			Role role = Server.getRole(id);
			Log.d("load robot", role.getId(), role.getNick());
		}
	}

	/**
	 * 随机压注
	 */
	public void randomCommit() {
		for (Robot r : robots.values()) {
			r.randomCommit();
		}
	}

	public void add(Robot robot) {
		this.robots.put(robot.getId(), robot);
	}

	public void remove(String id) {
		robots.remove(id);
	}

	public Collection<Robot> getRobots() {
		Collection<Robot> values = robots.values();
		return values;
	}

	public int getRobotCount() {
		return robots.size();
	}

	// /**
	// * 彩金是否一定给这个机器人 id: 机器人的id
	// */
	// public boolean isCaiJinMustTo(String id) {
	// if (!hasMustTo())
	// return false;
	// return mustToId.equals(id);
	// }

	// /**
	// * 本轮是否有必得彩金的机器人
	// */
	// public boolean hasMustTo() {
	// return mustToId != null;
	// }

	// /**
	// * 清空本轮必得彩金的机器人
	// */
	// public void clear() {
	// mustToId = null;
	// for (Robot r : getRobots()) {
	// r.clear();
	// }
	// }

	// public void setCaiJinMustTo(String id2) {
	// this.mustToId = id2;
	// }

	/**
	 * 是否是机器人
	 */
	public boolean isRobot(String id) {
		return robots.containsKey(id);
	}

	public void clearAllSwitchs() {
		for (Robot r : robots.values()) {
			r.clearSwitchs();
		}
	}
	
	public static List<String> getNicks() {
		if(nicks == null) {
			URL url = Resources.getResource("nicks.txt");
			List<String> lines = Util.File.getLines(url);
			Set<String> sets = Sets.newHashSet(lines);
			sets.remove("");
			sets.remove(" ");
			nicks = Lists.newArrayList(sets);
			Util.Collection.upset(nicks);
		}
		return nicks;
	}

	public static List<String> createNicks() {

		List<String> ns = Lists.newArrayList(getNicks());
		
		Iterator<String> it = ns.iterator();

		while (it.hasNext()) {
			String n = (String) it.next();
			if (n.trim().isEmpty()) {
				it.remove();
			}
		}

		return Util.Collection.sub(ns, 20);
	}
}
