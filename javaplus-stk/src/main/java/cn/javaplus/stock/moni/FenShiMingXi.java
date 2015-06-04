package cn.javaplus.stock.moni;

import java.io.File;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

/**
 * 分时明细
 */
public class FenShiMingXi implements PriceGetter {

	public static final String DIR = "G:/分时明细/";

	static int zeroCount;
	static int allCount;
	
	@Override
	public String getPrice(String code, String date, String clock) {
		if (!exist(code, date, DIR)) {
			new FenShiDownloader().download(code, date, DIR);
		}
		allCount++;
		Log.d("counts" + zeroCount, allCount);
		List<String> lines = Util.File.getLines(getPath(code, date, DIR));
		for (String line : lines) {
			String[] s = line.split("\t");
			String time = s[0];
			if (time.startsWith(clock)) {
				return s[1];
			}
		}zeroCount++;
		return "0.00";
	}

	private boolean exist(String code, String date, String dir) {
		return new File(getPath(code, date, dir)).exists();
	}

	private String getPath(String code, String date, String dir) {
		return dir + code + "_" + date + ".xls";
	}

}
