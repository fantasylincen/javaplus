package cn.mxz.formation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import message.S;
import cn.mxz.base.exception.OperationFaildException;

/**
 * 主阵型和替补位的工具类，提供一些字符串到阵型的转换函数
 *
 * @author Administrator
 *
 */
class FormationUtil {
	static Map<Integer, Integer> buildMapFromStr(String str) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (str != null) {
			String s[] = str.split(",");
			for (int i = 0; i < s.length; i += 2) {
				int fid = Integer.parseInt(s[i]);
				int position = Integer.parseInt(s[i + 1]);
				map.put(fid, position);
			}
		}
		return map;

	}

	static String buildStrFromMap(Map<Integer, Integer> map ) {
		if( map.size() == 0 ){
			throw new OperationFaildException(S.S10164);
		}

		StringBuilder sb = new StringBuilder();
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			sb.append(entry.getKey()).append(",").append(entry.getValue())
					.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();

	}
}
