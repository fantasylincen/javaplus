package cn.javaplus.stock.apps;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.stock.quotes.StockRecord;
import cn.javaplus.util.Util;

public class StockRecords {

	private String code;
	Map<String, Buy1> buy1s = Maps.newTreeMap();

	public StockRecords(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void appendRecords(File file) {
		List<String> lines = Util.File.getLines(file);

		String date = getDate(file);

		Buy1 buy1 = new Buy1();

		buy1.setOpenBuy1(getOpenBuy1(lines));
		buy1.setCloseBuy1(getCloseBuy1(lines));

		buy1s.put(date, buy1);
	}

	public Map<String, Buy1> getBuy1s() {
		return buy1s;
	}

	private String getDate(File file) {
		return file.getName().replaceAll("\\.txt", "")
				.replaceAll("[a-z]{2}[0-9]{6}\\.", "");
	}

	private long getCloseBuy1(List<String> lines) {
		int a = 145400;
		return getBiggerThan(lines, a);
	}

	private long getBiggerThan(List<String> lines, int a) {
		for (String line : lines) {
			StockRecord r = new StockRecord(line);
			String c = r.getClock();
			c = c.replaceAll(":", "");
			int v = new Integer(c);
			if (a < v) {
				return new Long(r.getBuyCount1());
			}
		}
		return -1;
	}

	private long getOpenBuy1(List<String> lines) {
		int a = 94000;
		return getBiggerThan(lines, a);
	}

}