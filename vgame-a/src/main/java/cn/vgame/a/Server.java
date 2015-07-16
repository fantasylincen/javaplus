package cn.vgame.a;

import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import cn.javaplus.cmd.Cmd;
import cn.javaplus.log.Log;
import cn.javaplus.util.Resources;
import cn.javaplus.util.Util;
import cn.vgame.a.account.Role;
import cn.vgame.a.config.ServerConfig;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.message.MessageManager;
import cn.vgame.a.recharge.ZfbRechargeManager;
import cn.vgame.a.robot.RobotManager;
import cn.vgame.a.system.Const;
import cn.vgame.a.util.SystemKeyValueDaoForeverAdaptor;
import cn.vgame.a.zhuang.ZhuangManager;
import cn.vgame.share.CacheManager;
import cn.vgame.share.ISystemKeyValueDao;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;
import cn.vgame.share.SystemKeyValueDaily;
import cn.vgame.share.SystemKeyValueForever;
import cn.vgame.share.Xml;

public class Server {

	private static ServerConfig config;
	private static Const cst;
	private static MessageManager msgm;
	private static RobotManager robotManager;
	private static ZhuangManager zhuangManager;

	private static ZfbRechargeManager zfbRechargeManager;

	public static ZfbRechargeManager getZfbRechargeManager() {
		if (zfbRechargeManager == null) {
			zfbRechargeManager = new ZfbRechargeManager();
		}
		return zfbRechargeManager;
	}

	private static final String KEY = "ROLES";

	public static Role getRole(HttpSession session) {
		String id = (String) session.getAttribute("roleId");
		return getRole(id);
	}

	public static Role getRole(String id) {

		if (id == null)
			return null;
		String k = key(id);
		Role role = (Role) CacheManager.get(k);

		if (role == null) {
			role = load(id);
			if (role != null)
				put(role);
		}
		return role;
	}

	private static Role load(String id) {
		RoleDao dao = Daos.getRoleDao();
		RoleDto dto = dao.get(id);
		if(dto == null)
			return null;
		return new Role(dto);
	}

	public static void put(Role role) {
		String id = role.getId();
		CacheManager.put(key(id), role);
	}

	private static String key(String id) {
		return KEY + ":" + id;
	}

	public static ServerConfig getConfig() {
		if (config == null)
			config = new ServerConfig();
		return config;
	}

	public static Const getConst() {
		if (cst == null)
			cst = new Const();
		return cst;
	}

	public static MessageManager getMessageManager() {
		if (msgm == null)
			msgm = new MessageManager();
		return msgm;
	}

	public static KeyValueSaveOnly getKeyValueSaveOnly() {
		return new KeyValueSaveOnlyImplementation();
	}

	public static KeyValue getKeyValueForever() {
		ISystemKeyValueDao dao = new SystemKeyValueDaoForeverAdaptor();
		return new SystemKeyValueForever(dao);
	}

	public static KeyValue getKeyValueDaily() {
		ISystemKeyValueDao dao = new SystemKeyValueDaoForeverAdaptor();
		return new SystemKeyValueDaily(dao);
	}

	private final static class KeyValueSaveOnlyImplementation implements
			KeyValueSaveOnly {
		@Override
		public void set(Object key, Object value) {
			getKeyValueDaily().set(key, value);
			getKeyValueForever().set(key, value);
		}

		@Override
		public void add(Object key, long add) {
			getKeyValueDaily().add(key, add);
			getKeyValueForever().add(key, add);
		}
	}

	/**
	 * 服务器分区标识符
	 * 
	 * @return
	 */
	public static String getIdentity() {
		String aa = getMAC() + ":" + getPath();
		String md5 = Util.Secure.md5(aa);
		Log.d(isLinux(), "my id", aa, md5);
		return md5;
	}

	public static void main(String[] args) {
		// Log.d(isLinux());
		System.out.println(getIdentity());
	}

	private static String getMAC() {
		String p = "[0-9a-zA-Z]{2}[:\\-][0-9a-zA-Z]{2}[:\\-][0-9a-zA-Z]{2}[:\\-][0-9a-zA-Z]{2}[:\\-][0-9a-zA-Z]{2}[:\\-][0-9a-zA-Z]{2}";
		if (isLinux()) {
			return cmd("cat /sys/class/net/eth0/address", p);
		} else {
			return cmd("cmd /c ipconfig /all", p);
		}
	}

	private static String getPath() {
		URL url = Resources.getResource("struts.xml");
		return url.getPath();
	}

	private static String cmd(String cmd, String patten) {
		List<String> result = Cmd.exec(cmd);
		for (String ss : result) {
			Pattern p = Pattern.compile(patten);
			Matcher m = p.matcher(ss);
			boolean find = m.find();
			if (find) {
				String group = m.group();
				return group;
			}
		}
		return null;
	}

	private static boolean isLinux() {
		try {
			cmd("ls", ".*");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static RobotManager getRobotManager() {
		if (robotManager == null)
			robotManager = new RobotManager();
		return robotManager;
	}

	public static ZhuangManager getZhuangManager() {
		if (zhuangManager == null)
			zhuangManager = new ZhuangManager();
		return zhuangManager;
	}

	public static Xml getXml() {
		return Xml.getXml();
	}

	public static String getZoneId() {
		return getConfig().getString("zoneId");
	}
}
