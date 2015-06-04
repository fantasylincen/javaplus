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

public class AppGroupFiles {

	private static final String A = "G:\\分时明细\\";

	public static void main(String[] args) {

		int a = 0;
		File file = new File(A);
		File[] all = file.listFiles();
		for (File f : all) {
			moveFileF(f);
			Log.d(f.getName(), a++);
		}
	}

	private static void moveFileF(File f) {

		String name = f.getName();

		if (name.endsWith(".xls")) {
			String d = f.getParent() + "/" + name.substring(0, 6);
			File dir = new File(d);
			if(!dir.exists()) {
				dir.mkdir();
				Log.d("mkdir" , dir);
			}
			String pathname = d + "/" + f.getName();
			File dest = new File(pathname);
			f.renameTo(dest);
			Log.d(dest);
			
		}
	}

	private static Map<String, StringPrinter> group(String code,
			List<String> lines) {
		HashMap<String, StringPrinter> map = Maps.newHashMap();

		for (String line : lines) {
			String[] s = line.split(",");
			String dt = s[0].replaceAll("/", "");
			String c = s[1];
			String price = s[2];

			String fileName = code + "_" + dt;

			StringPrinter out = map.get(fileName);
			if (out == null) {
				out = new StringPrinter();
				map.put(fileName, out);
			}

			// out.println(x);
		}

		return map;
	}
}
