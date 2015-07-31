package cn.vgame.a.log;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.gen.dto.MongoGen.GmLogDto;

public class Translate {

	private static Map<String, String> tans;
	private static long lastUpdateTime;

	public static String translate(String log) {
		for (Entry<String, String> e : getTans().entrySet()) {
			String k = e.getKey();
			String v = e.getValue();

			log = log.replaceAll(k, v);
		}
		return log;
	}

	public static String translate(GmLogDto dto) {

		String className = dto.getClassName();
		String methodName = dto.getMethodName();

		return translate(className + "." + methodName);
	}
	
	public static Map<String, String> getTans() {

		if (timeOut()) {
			tans = null;
		}

		if (tans == null) {
			initTans();
			lastUpdateTime = System.currentTimeMillis();
			Log.d("reload translate config");
		}

		return tans;
	}

	private static void initTans() {
		tans = Maps.newHashMap();
		Sheet sheet = Server.getXml().get("translate");
		List<Row> all = sheet.getAll();
		for (Row r : all) {
			String key = r.get("key");
			String v = r.get("value");
			tans.put(key, v);
		}
	}

	private static boolean timeOut() {
		return System.currentTimeMillis() - lastUpdateTime > 10000;//10秒缓存
	}

	public static void setTans(Map<String, String> tans) {
		Translate.tans = tans;
	}

}
