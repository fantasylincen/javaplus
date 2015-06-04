package cn.javaplus.stock.quotes;

import java.util.List;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.page.Page;
import cn.javaplus.stock.util.Market;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

public class CodesUpdate implements Runnable {

	public class MyPage {

		private Page<String> page;
		private int pageAll;

		public MyPage(Set<String> otherCodes) {

			page = new Page<>(Lists.newArrayList(otherCodes), 200);
			pageAll = page.getPageAll();
		}

		public int getPageAll() {
			return pageAll;
		}

		public List<String> getPage(int p) {
			return page.getPage(p);
		}

	}

	private Set<String> allCodes = Sets.newConcurrentHashSet();

	public Set<String> getAllCodes() {
		return allCodes;
	}

	private void addCodesInFile() {
		List<String> lines = Util.File.getLines("codes");
		for (String code : lines) {
			code = code.toLowerCase();
			code = code.trim();
			if (!code.isEmpty()) {
				allCodes.add(code);
			}
		}
	}

	@Override
	public void run() {
		addCodesInFile();
		while (true) {
			if (Market.inJiaoYiTime()) {
				try {
					tryToUpdateAllCodes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Util.Thread.sleep(5 * Time.MILES_ONE_MIN);
		}
	}

	private void saveCodesToFile() {
		StringPrinter content = new StringPrinter();

		for (String string : allCodes) {
			content.println(string);
		}

		Util.File.write("codes", content.toString());
	}

	private void tryToUpdateAllCodes() {

		Set<String> otherCodes = getOtherCodes();
		MyPage pg = new MyPage(otherCodes);

		for (int page = 1; page <= pg.getPageAll(); page++) {
			List<String> codes = pg.getPage(page);
			Log.d("request codes who has a lot of buy1 start");
			update(codes);
			saveCodesToFile();
			Log.d("request codes who has a lot of buy1 end");
			Util.Thread.sleep(5000);
		}
	}

	private Set<String> getOtherCodes() {
		List<String> lines = Util.File.getLines("codesAll");

		Set<String> set = Sets.newHashSet();
		for (String string : lines) {
			String code = Market.getCode(string);
			if (!code.isEmpty()) {
				set.add(code);
			}
		}
		set.removeAll(allCodes);
		return set;
	}

	private void update(List<String> codes) {

		List<StockRecord> rs = new StockRequester().request(codes);
		for (StockRecord s : rs) {
			if (new Integer(s.getBuyCount1()) > 60000000) {
				allCodes.add(s.getCode());
				Log.d("found who has a lot of buy1", s.getCode());
			}
		}
	}
}
