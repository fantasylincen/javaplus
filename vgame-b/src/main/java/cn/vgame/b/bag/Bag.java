package cn.vgame.b.bag;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.Role;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.KeyValue;
import cn.vgame.share.Xml;

/**
 * 玩家背包
 */
public class Bag {

	private final Role role;

	public Bag(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void add(int type, int count) {
		KeyValue kv = role.getKeyValueForever();
		kv.add(key(type), count);
	}

	private String key(int type) {
		return "BAG:" + type;
	}

	public int getCount(int type) {
		KeyValue kv = role.getKeyValueForever();
		return kv.getInt(key(type));
	}

	public void remove(int type, int hanHuaNeed) {
		KeyValue kv = role.getKeyValueForever();
		int count = kv.getInt(key(type));
		if (count < hanHuaNeed)
			throw new ErrorResult(10023, getName(type)).toException();
		kv.set(key(type), count - hanHuaNeed);
	}

	private String getName(int type) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("goods");
		Row row = sheet.get(type);
		return row.get("name");
	}
}
