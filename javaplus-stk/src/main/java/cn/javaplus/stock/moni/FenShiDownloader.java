package cn.javaplus.stock.moni;

import java.io.File;

import cn.javaplus.stock.util.Market;
import cn.javaplus.util.Util;
import cn.javaplus.web.WebContentFethcer;

public class FenShiDownloader {

	/**
	 * @param id
	 * @param date  yyyyMMdd
	 * @param dir
	 */
	public void download(String id, String date, String dir) {

		String name = id + "_" + date + ".xls";
		String path = dir + id + "/" + name;

		if (exist(path)) {
			return;
		}

		String code = Market.getCode(id);
		String head = "http://market.finance.sina.com.cn/downxls.php?date=";
		String str = WebContentFethcer.get("gb2312", head + date
				+ "&symbol=" + code);
		Util.File.write(path, str);
	}

	private boolean exist(String path) {
		return new File(path).exists();
	}
	
}
