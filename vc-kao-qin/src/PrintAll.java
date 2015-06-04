import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class PrintAll {

	public static Set<String> exclude = Sets.newHashSet();

//	xxx.txt Œƒº˛∏Ò Ω
//	–Ï‘®	2015-02-02	08:56
//	–Ï‘®	2015-02-02	17:33
//	–Ï‘®	2015-02-03	08:51
//	–Ï‘®	2015-02-03	17:31
//	–Ï‘®	2015-02-04	08:53
//	–Ï‘®	2015-02-05	08:52
//	–Ï‘®	2015-02-05	17:31
//	–Ï‘®	2015-02-06	08:57
//	–Ï‘®	2015-02-06	17:36
//	–Ï‘®	2015-02-09	08:41
//	–Ï‘®	2015-02-10	17:30
//	–Ï‘®	2015-02-11	08:57
//	–Ï‘®	2015-02-11	17:31
//	–Ï‘®	2015-02-12	08:52
//	–Ï‘®	2015-02-12	18:13
//	–Ï‘®	2015-02-13	08:56
	public static void main(String[] args) throws FileNotFoundException {

		System.setOut(new PrintStream(new File("result.txt")));
		exclude.add("2015-02-16");
		exclude.add("2015-02-17");
		exclude.add("2015-02-18");
		exclude.add("2015-02-19");
		exclude.add("2015-02-20");
		exclude.add("2015-02-21");
		exclude.add("2015-02-22");
		exclude.add("2015-02-23");
		exclude.add("2015-02-24");

		List<String> line = Util.File.getLines("E:/360data/÷ÿ“™ ˝æ›/◊¿√Ê/xxx.txt");

		List<Bean> beans = getBeans(line);

		Map<String, RecordList> ls = createMap(beans);
		for (Entry<String, RecordList> e : ls.entrySet()) {
			String key = e.getKey();
			RecordList v = e.getValue();
			printResult(key, v);
		}
	}

	private static Map<String, RecordList> createMap(List<Bean> beans) {
		HashMap<String, RecordList> map = Maps.newHashMap();

		for (Bean bean : beans) {
			String name = bean.getName();

			RecordList rl = map.get(name);
			if (rl == null) {
				rl = new RecordList();
				map.put(name, rl);
			}

			rl.insert(bean);
		}
		return map;
	}

	private static void printResult(String key, RecordList v) {
		List<Result> ls = v.getMeiDaKaResult();
		for (Result result : ls) {
			System.out.println(key + " " + result.getDate() + " "
					+ (result.isZaoShangMeiDaKa() ? "‘Á…œ√ª¥Úø®" : "ÕÌ…œ√ª¥Úø®"));
		}
	}

	private static List<Bean> getBeans(List<String> line) {
		ArrayList<Bean> ls = Lists.newArrayList();
		for (String ss : line) {
			ss = ss.trim();
			if (ss.isEmpty()) {
				continue;
			}
			String[] s = ss.split("\t");
			String name = s[0];
			String date = s[1];
			String time = s[2];
			ls.add(new Bean(name, date, time));

		}
		return ls;
	}
}
