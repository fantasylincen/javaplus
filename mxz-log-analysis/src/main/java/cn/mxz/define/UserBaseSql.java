package cn.mxz.define;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.io.LinesReader;
import cn.javaplus.util.Util;

import com.google.common.collect.Maps;

public class UserBaseSql {

	private static Map<String, String> map;

	/**
	 * 根据角色ID 获取昵称
	 * 
	 * @param roleId
	 * @return
	 */
	public static String getNick(String roleId) {
		ensureInit();
		return map.get(roleId);
	}

	private static void ensureInit() {
		if (map == null) {
			map = Maps.newHashMap();
			LinesReader linesReader = Util.Str
					.getLinesIterator("user_base.sql");
			for (String s : linesReader) {
				if (s.startsWith("INSERT INTO `user_base` VALUES ('")) {

					Pattern pattern = Pattern.compile("\'.+\'");
					Matcher m = pattern.matcher(s);
					m.find();
					String group = m.group();
					String[] ss = group.split(",");
					String r = ss[0].replaceAll("'", "");
					String nick = ss[1].replaceAll("'", "");

					map.put(r, nick);
				}
			}
		}
	}

}
