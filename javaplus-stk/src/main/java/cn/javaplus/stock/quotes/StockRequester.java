package cn.javaplus.stock.quotes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.apps.AppMoniBuy1.MoniStockRequester;
import cn.javaplus.stock.util.Market;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class StockRequester {

	public List<StockRecord> request(Collection<String> allCodes) {
		try {
			if(allCodes.isEmpty())
				return Lists.newArrayList();
			String url = buildUrl(allCodes);
			long t = System.currentTimeMillis();
			String content = get("gb2312", url);
			Log.d("request time use mills", System.currentTimeMillis() - t);
			return parseContent(content);
		} catch (Exception e) {
			e.printStackTrace();
			return Lists.newArrayList();
		}
	}


	private String buildUrl(Collection<String> allCodes) {
		// http://hq.sinajs.cn/list=sh600000,sh600004
		Set<String> set = Sets.newHashSet();
		for (String id : allCodes) {
			set.add(Market.getCode(id));
		}
		allCodes = set;
		 
		String codes = Util.Collection.linkWith(",", allCodes);
		return "http://hq.sinajs.cn/list=" + codes;
	}

	private List<StockRecord> parseContent(String content) {

		String[] lines = content.split(";");
		ArrayList<StockRecord> ls = Lists.newArrayList();

		for (String line : lines) {

			line = line.trim();
			if (line.isEmpty())
				continue;

			String ee = line.split("=")[1].replace("\"", "");

			if (ee.isEmpty()) {
				continue;
			}

			String code = line.split("=")[0].replace("var hq_str_", "");

			StockRecord r = new StockRecord(code, ee);

			ls.add(r);
		}

		return ls;

	}

	static String get(String encoding, String url) {

		Log.d(url);

		URL getUrl;
		HttpURLConnection connection = null;
		StringPrinter out = new StringPrinter();
		try {
			getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setConnectTimeout(18000);
			connection.connect();
			InputStream is = connection.getInputStream();
			InputStreamReader in = new InputStreamReader(is, encoding);
			BufferedReader reader = new BufferedReader(in);
			String line;
			while (true) {
				line = reader.readLine();
				if (line == null)
					break;
				out.println(line);
			}
			reader.close();
		} catch (Exception e) {
			Log.e("Error", url);
			throw new RuntimeException(e);
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return out.toString();
	}

}
