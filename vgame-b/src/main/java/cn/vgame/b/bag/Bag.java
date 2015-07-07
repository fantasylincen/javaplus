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

	public void add(int id, int count) {
		KeyValue kv = role.getKeyValueForever();
		kv.add(key(id), count);
	}

	private String key(int type) {
		return "BAG:" + type;
	}

	public int getCount(int id) {
		KeyValue kv = role.getKeyValueForever();
		return kv.getInt(key(id));
	}

	public void remove(int id, int hanHuaNeed) {
		KeyValue kv = role.getKeyValueForever();
		int count = kv.getInt(key(id));
		if (count < hanHuaNeed)
			throw new ErrorResult(10023, getName(id)).toException();
		kv.set(key(id), count - hanHuaNeed);
	}

	private String getName(int type) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("goods");
		Row row = sheet.get(type);
		return row.get("name");
	}
}
