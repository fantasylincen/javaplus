package cn.vgame.a.robot;

import java.util.Collection;
import java.util.Map;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

public class RobotManager {

	Map<String, Robot> robots = Maps.newConcurrentMap();
	private Object mustToId;

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
		return robots.values();
	}

	/**
	 * 彩金是否一定给这个机器人 id: 机器人的id
	 */
	public boolean isCaiJinMustTo(String id) {
		if (!hasMustTo())
			return false;
		return mustToId.equals(id);
	}

	/**
	 * 本轮是否有必得彩金的机器人
	 */
	public boolean hasMustTo() {
		return mustToId != null;
	}

	/**
	 * 清空本轮必得彩金的机器人
	 */
	public void clear() {
		mustToId = null;
		for (Robot r : getRobots()) {
			r.clear();
		}
	}

	public void setCaiJinMustTo(String id2) {
		this.mustToId = id2;
	}

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
}
