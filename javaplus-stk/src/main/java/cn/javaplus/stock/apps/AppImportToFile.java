package cn.javaplus.stock.apps;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockPriceDao;
import cn.javaplus.stock.util.Market;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Maps;

public class AppImportToFile {

	private static final String A = "C:\\Users\\Administrator\\Desktop\\股票五分钟2008csv\\股票五分钟2008csv\\";
	private static Set<String> ids = Sets.newHashSet();

	public static void main(String[] args) {

		ArrayList<String> idss = Market.getAllStockIds();

		ids.addAll(idss);

		File file = new File(A);
		File[] all = file.listFiles();
		for (File f : all) {
			importF(f);
		}
	}

	private static void importF(File f) {
		List<String> lines = Util.File.getLines(f);

		String name = f.getName();
		name = name.substring(2, 8);

		if (!ids.contains(name))
			return;
		Log.d(name);


		Map<String, StringPrinter> files = group(name, lines);
		for (String fileName : files.keySet()) {
			StringPrinter content = files.get(fileName);
			Util.File.write(A + fileName, content);
		}
	}

	private static Map<String, StringPrinter> group(String code, List<String> lines) {
		HashMap<String, StringPrinter> map = Maps.newHashMap();

		for (String line : lines) {
			String[] s = line.split(",");
			String dt = s[0].replaceAll("/", "");
			String c = s[1];
			String price = s[2];
			
			String fileName = code + "_"  + dt;
			
			StringPrinter out = map.get(fileName);
			if(out == null)
			{
				out = new StringPrinter();
				map.put(fileName, out);
			}
			
//			out.println(x);
		}

		return map;
	}
}
