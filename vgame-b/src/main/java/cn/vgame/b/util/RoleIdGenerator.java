package cn.vgame.b.util;

import cn.javaplus.util.Util;
import cn.vgame.b.Server;
import cn.vgame.share.KeyValue;

public class RoleIdGenerator {

	/**
	 * 创建角色ID
	 * @return
	 */
	public static String createRoleId() {
		final String zoneId = Server.getZoneId();
		return "VC" + zoneId + "" + createId();
	}

	private static long createId() {
		long id = getId();
		id += Util.Random.get(8, 30);
		save(id);
		return id;
	}

	private static void save(long id) {
		KeyValue kv = Server.getKeyValueForever();
		kv.set("USER_ID", id);
	}

	private static long getId() {
		KeyValue kv = Server.getKeyValueForever();
		long id = kv.getLong("USER_ID");
		if (id == 0) {
			id = 1000000;
			save(id);
		}
		return id;
	}
}
