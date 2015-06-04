package cn.javaplus.stock.apps;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.util.Market;

import com.google.common.collect.Lists;

import cn.javaplus.stock.util.Market;

public class AppPrintBuy1 {

	private static Set<String> codes = Sets.newHashSet();

	public static void main(String[] args) {

		add("	000020	");
		add("	000033	");
		add("	000413	");
		add("	000509	");
		add("	000533	");
		add("	000557	");
		add("	000591	");
		add("	000593	");
		add("	000605	");
		add("	000611	");
		add("	000636	");
		add("	000662	");
		add("	000670	");
		add("	000678	");
		add("	000828	");
		add("	000860	");
		add("	000889	");
		add("	000920	");
		add("	000976	");
		add("	002006	");
		add("	002034	");
		add("	002070	");
		add("	002071	");
		add("	002072	");
		add("	002152	");
		add("	002157	");
		add("	002174	");
		add("	002207	");
		add("	002227	");
		add("	002270	");
		add("	002308	");
		add("	002317	");
		add("	002323	");
		add("	002354	");
		add("	002411	");
		add("	002491	");
		add("	002579	");
		add("	002652	");
		add("	002660	");
		add("	002721	");
		add("	300018	");
		add("	300025	");
		add("	300056	");
		add("	300063	");
		add("	300116	");
		add("	300144	");
		add("	300169	");
		add("	300195	");
		add("	300208	");
		add("	300222	");
		add("	300243	");
		add("	300247	");
		add("	300252	");
		add("	300296	");
		add("	300363	");
		add("	300372	");
		add("	300379	");
		add("	600103	");
		add("	600105	");
		add("	600129	");
		add("	600207	");
		add("	600229	");
		add("	600231	");
		add("	600238	");
		add("	600258	");
		add("	600421	");
		add("	600423	");
		add("	600458	");
		add("	600510	");
		add("	600580	");
		add("	600619	");
		add("	600654	");
		add("	600689	");
		add("	600705	");
		add("	600741	");
		add("	600754	");
		add("	600767	");
		add("	601299	");
		add("	603003	");
		add("	603008	");

		List<StockRecords> ss = getStockRecords();
		for (StockRecords s : ss) {
			if (codes.contains(s.getCode()))
				print(s);
		}
	}

	private static void add(String string) {
		codes.add(string.trim());
	}

	private static void print(StockRecords s) {
		Log.d(s.getCode());
		Map<String, Buy1> buys = s.getBuy1s();
		for (String g : buys.keySet()) {
			Buy1 b = buys.get(g);
			Log.d(g, getW(b.getOpenBuy1()), getW(b.getCloseBuy1()));
		}
		Log.d("----------------------");
	}

	private static Object getW(long openBuy1) {
		return (openBuy1 / 10000 + 0d) / 100 + "W";
	}

	private static List<StockRecords> getStockRecords() {
		Map<String, StockRecords> ss = getStockRecordsMap();
		return Lists.newArrayList(ss.values());
	}

	private static Map<String, StockRecords> getStockRecordsMap() {
		Map<String, StockRecords> map = Maps.newHashMap();

		File f = new File(Market.WORKSPACE_DIR);
		File[] fs = f.listFiles();
		for (File file : fs) {
			if (matches(file))
				addToMap(file, map);
		}
		return map;
	}

	private static boolean matches(File file) {
		String name = file.getName();
		boolean matches = name.matches("[a-z]{2}[0-9]{6}.+txt");
		return matches;
	}

	private static void addToMap(File file, Map<String, StockRecords> map) {
		String code = getCode(file);
		StockRecords sr = map.get(code);
		if (sr == null) {
			sr = new StockRecords(code);
			map.put(code, sr);
		}
		sr.appendRecords(file);
	}

	private static String getCode(File file) {
		return file.getName().substring(2, 2 + 6);
	}

}
