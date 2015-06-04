package cn.mxz.define;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.io.LinesReader;
import cn.javaplus.util.Util;

import com.google.common.collect.Maps;
import com.google.common.io.Resources;

public class NewFighterSql {
	private static Map<String, Integer> map;

	/**
	 * 根据角色ID 获取等级
	 * 
	 * @param roleId
	 * @return
	 */
	public static Integer getLevel(String roleId) {
		ensureInit();
		roleId = roleId.trim();
		Integer l = map.get(roleId);
		return l;
	}

	private static void ensureInit() {
		if (map == null) {
			map = Maps.newHashMap();

			URL resource = Resources.getResource("new_fighter.sql");
			LinesReader linesReader = null;
			try {
				linesReader = new LinesReader(resource.openStream());
			} catch (IOException e) {
				throw Util.Exception.toRuntimeException(e);
			}
			for (String s : linesReader) {
				if (s.startsWith("INSERT INTO `new_fighter` VALUES ('")) {

					Pattern pattern = Pattern.compile("\'.+\'");
					Matcher m = pattern.matcher(s);
					m.find();
					String group = m.group();
					String[] ss = group.split(",");

					String typeId = ss[1].replaceAll("'", "").trim();

					if(typeId.startsWith("3")) { //主角
						String roleId = ss[0].replaceAll("'", "").trim();
						int level = new Integer(ss[2].replaceAll("'", "")
								.trim());
						map.put(roleId, level);
					}
				}
			}
		}
	}
}
