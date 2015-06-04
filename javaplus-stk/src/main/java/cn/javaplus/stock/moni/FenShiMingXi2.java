package cn.javaplus.stock.moni;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import cn.javaplus.log.Log;
import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;

public class FenShiMingXi2 implements PriceGetter {

	private static final String A = "C:\\Users\\Administrator\\Desktop\\股票五分钟2008csv\\股票五分钟2008csv\\";
	static int zeroCount;
	static int allCount;

	public static void main(String[] args) {
//		Set<String> ids = Market.getSelectStocks();
//		for (String id : ids) {
//			if (!contains(id)) {
//				Log.d(id);
//			}
//		}
		Log.d(contains("000627"));
	}

	private static boolean contains(String id) {
		String code2 = Market.getCode(id).toUpperCase();
		String filePath = A + code2 + ".csv";
		return new File(filePath).exists();
	}

	@Override
	public String getPrice(String code, String date, String clock) {
		allCount++;
		String code2 = Market.getCode(code).toUpperCase();
		List<String> lines = Util.File.getLines(A + code2 + ".csv");
		Collections.reverse(lines);
		for (String line : lines) {
			String[] s = line.split(",");
			String dt = s[0].replaceAll("/", "");
			String c = s[1];
			String price = s[2];

			int clockX = toInt(clock);
			int cX = toInt(c);
			
			if (dt.equals(date) && clockX >= cX) {
				return price;
			}
		}
		Log.d(code,date, clock, zeroCount, allCount);
		zeroCount++;
		return "0.00";//000669, 20080130, 11:16, 8356, 20896
	}

	private Integer toInt(String clock) {
		return new Integer(clock.replace(":", ""));
	}

}
