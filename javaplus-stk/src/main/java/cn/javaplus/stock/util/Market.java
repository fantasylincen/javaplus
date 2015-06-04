package cn.javaplus.stock.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.StockDto;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDao.StockIdDtoCursor;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDto;
import cn.javaplus.stock.stock.DayData;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.stock.stock.Stock2;
import cn.javaplus.stock.stock.StockReader;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class Market {

	public static final String WORKSPACE_DIR = "D:/stocks/";
	static SimpleDateFormat FORMAT = new SimpleDateFormat("HHmmss");

	public static boolean inJiaoYiTime() {

		if (isWeekday())
			return false;

		Date date = new Date(System.currentTimeMillis());
		String time = FORMAT.format(date);
		int now = new Integer(time);
		return (91450 < now && now < 113020) || (125950 < now && now < 150010);
	}

	private static boolean isWeekday() {
		Calendar ins = Calendar.getInstance();
		int day = ins.get(Calendar.DAY_OF_WEEK);
		return day == Calendar.SUNDAY || day == Calendar.SATURDAY;
	}

	public static int getSpace(DayData d, DayData yes) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String date = d.getDate();
		String d1 = yes.getDate();
		try {
			Date dateI = sf.parse(date);
			Date d1I = sf.parse(d1);
			long a = (dateI.getTime() - d1I.getTime()) / Time.MILES_ONE_DAY;
			return (int) a;
		} catch (ParseException e) {
			return -1;
		}
	}

	/**
	 * 保留2位小数 四舍五入
	 */
	public static String toPriceString(double close) {
		return String.format("%.2f", close);
	}

	/**
	 * ting pai chaoguo 30 ri, szxxxxxx shxxxxxx
	 * 
	 * @return
	 */
	public static List<String> getTingPai30() {
		StockReader r = new StockReader();
		final ArrayList<String> ls = Lists.newArrayList();
		r.foreachShenHu(new It() {

			@Override
			public void onRead(Stock1 read) {
				if (conform(read)) {
					String code = getCode(read.getId());
					ls.add(code);
					Log.d("ting pai code", code);
				}
			}
		});
		Log.d("ting pai 30 count", ls.size());
		return ls;
	}

	static boolean conform(Stock1 stock) {

		OneDayData last = stock.getLast();
		if (last == null)
			return false;

		long dateMillis = last.getDateMillis();

		return System.currentTimeMillis() - dateMillis > Time.MILES_ONE_DAY * 30;

	}

	public static String getCode(String code) {
		code = code.trim();
		code = code.toLowerCase();
		if (code.startsWith("s"))
			return code;
		if (code.startsWith("60")) {
			code = "sh" + code;
		} else {
			code = "sz" + code;
		}
		return code;
	}

	public static double getBuyPrice(DayData day) {
		double d = Math.rint(day.getClose() * 100) / 100;
		return d;
	}

	public static double getSellPrice(DayData sellDay) {
		double d = Math.rint(sellDay.getClose() * 100) / 100;
		return d;
	}

	/**
	 * 随机加载n个股票数据
	 * 
	 * @param n
	 * @return
	 */
	public static List<Stock2> loadTopStocks(int n) {
		List<String> stockIds = getTopId(n);
		return load(stockIds);
	}

	private static List<String> getTopId(int n) {
		ArrayList<String> ids = getAllStockIds();
		return Util.Collection.sub(ids, n);
	}

	private static List<Stock2> load(List<String> stockIds) {
		ArrayList<Stock2> ls = Lists.newArrayList();
		for (String id : stockIds) {
			StockDto dto = Daos.getStockDao().get(id);
			if (dto == null)
				continue;
			Stock2 s = new Stock2(dto);
			ls.add(s);
		}
		return ls;
	}

	/**
	 * 随机加载n个股票数据
	 * 
	 * @param n
	 * @return
	 */
	public static List<Stock2> loadRandomStocks(int n) {
		List<String> stockIds = getRandomStockIds(n);
		return load(stockIds);
	}

	private static List<String> getRandomStockIds(int stockCount) {
		ArrayList<String> ls = getAllStockIds();
		Util.Collection.upset(ls);
		return Util.Collection.sub(ls, stockCount);
	}

	public static ArrayList<String> getAllStockIds() {
		StockIdDao dao = Daos.getStockIdDao();
		StockIdDtoCursor all = dao.find();
		ArrayList<String> ls = Lists.newArrayList();
		for (StockIdDto dto : all) {
			String id = dto.getId();
			ls.add(id);
		}
		return ls;
	}

	/**
	 * 往后走一天
	 * 
	 * @param date
	 * @return
	 */
	public static int nextDate(int date) {
		long space = Time.MILES_ONE_DAY;
		return space(date, space);
	}

	/**
	 * 往后前一天
	 * 
	 * @param date
	 * @return
	 */
	public static int previousDate(int date) {
		long space = -Time.MILES_ONE_DAY;
		return space(date, space);
	}

	private static int space(int date, long space) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = sf.parse(date + "");
			long time = d.getTime();
			Date d2 = new Date(time + space);
			date = new Integer(sf.format(d2));
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 计算年化收益率
	 */
	public static double getProfitOneYear(double profitEveryDay) {
		int jiaoYiRiYear = 240;
		double profit = Math.pow(1 + profitEveryDay, jiaoYiRiYear) - 1;
		return profit;
	}

	/**
	 * 获得两天之间间隔几个交易日
	 * 
	 * @param sell
	 * @param buy
	 * @return
	 */
	public static int getSpaceDay(DayData sell, DayData buy) {

		int count = 0;
		while (true) {
			count++;
			if (sell == null)
				throw new NullPointerException();
			sell = sell.getYestoday();
			if (sell == buy)
				return count;
		}

	}

	public static Stock2 load(String id) {
		ArrayList<String> ls = Lists.newArrayList();
		ls.add(id);
		List<Stock2> load = load(ls);
		if (load.isEmpty())
			return null;
		return load.get(0);
	}

	static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 间隔时间, yyyyMMdd
	 */
	public static int getSpace(String from, String to) {
		try {
			Date t = sf.parse(to);
			Date f = sf.parse(from);
			return (int) ((t.getTime() - f.getTime()) / Time.MILES_ONE_DAY);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	static Set<String> selectStocks;

	public static Set<String> getSelectStocks() {
		if (selectStocks == null) {
			selectStocks = Sets.newHashSet();
			add("000681");
			add("000035");
			add("000598");
			add("000590");
			add("000519");
			add("000014");
			add("000801");
			add("000007");
			add("000809");
			add("000526");
			add("000428");
			add("000657");
			add("000625");
			add("000662");
			add("000795");
			add("000563");
			add("000594");
			add("000716");
			add("000032");
			add("000524");
			add("000503");
			add("000752");
			add("002273");
			add("000012");
			add("000010");
			add("000793");
			add("000663");
			add("000530");
			add("000540");
			add("000777");
			add("000151");
			add("000401");
			add("000797");
			add("000564");
			add("000630");
			add("000416");
			add("000525");
			add("000069");
			add("000626");
			add("000815");
			add("000569");
			add("000738");
			add("000150");
			add("000906");
			add("000652");
			add("000425");
			add("000078");
			add("000831");
			add("000883");
			add("000537");
			add("000758");
			add("600346");
			add("000005");
			add("000893");
			add("000819");
			add("600885");
			add("000056");
			add("000516");
			add("000751");
			add("000066");
			add("000627");
			add("000418");
			add("000782");
			add("000731");
			add("000756");
			add("600677");
			add("000576");
			add("600801");
			add("000520");
			add("000861");
			add("600145");
			add("000723");
			add("000518");
			add("000591");
			add("000779");
			add("000029");
			add("000669");
			add("000707");
			add("000727");
			add("000552");
			add("002222");
			add("000829");
			add("000798");
			add("000046");
			add("000796");
			add("000514");
			add("000011");
			add("000897");
			add("000818");
			add("000036");
			add("600979");
			add("000045");
			add("000502");
			add("000606");
			add("000043");
			add("000609");
			add("000026");
			add("000521");
			add("000404");
			add("000409");
			add("600202");
			add("601808");
			add("000862");
			add("000902");
			add("000400");
			add("000006");
			add("000050");
			add("000856");
			add("000820");
			add("000505");
			add("000018");
			add("000702");
			add("600241");
			add("000410");
			add("000868");
			add("000695");
			add("000698");
			add("000532");
			add("000790");
			add("000534");
			add("002139");
			add("000705");
			add("000565");
			add("000523");
			add("601958");
			add("600634");
			add("000628");
			add("000739");
			add("000070");
			add("000596");
			add("000852");
			add("000835");
			add("000691");
			add("000019");
			add("000632");
			add("000419");
			add("000789");
			add("000661");
			add("000546");
			add("000610");
			add("000880");
			add("000407");
			add("000061");
			add("000908");
			add("000002");
			add("000617");
			add("000100");
			add("000605");
			add("000811");
			add("000701");
			add("600864");
			add("000030");
			add("000008");
			add("000616");
			add("000613");
			add("000040");
			add("000572");
			add("000562");
			add("000430");
			add("000009");
			add("000673");
			add("000420");
			add("000059");
			add("000909");
			add("000068");
			add("600126");
			add("000022");
			add("600207");
			add("000690");
			add("000020");
			add("002225");
			add("000607");

		}
		return selectStocks;
	}

	private static void add(String string) {
		selectStocks.add(string.trim());
	}

	public static String toPriceString2(double ac) {
		if (ac < 100000) {
			return (int) ac + "";
		}
		double rint = Math.rint(ac / 10000);
		return Market.toPriceString(rint) + " W";
	}

}
