package cn.javaplus.monichaogu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.GuPiaoDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.KeyValueDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.KeyValueDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoMap;
import cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao.StockDtoCursor;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.UserDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.UserDto;
import cn.javaplus.time.Time;

public class ShiChang {

	public static double getPrice(GuPiaoDto dto) {
		String id = dto.getId();

		return getPrice(id);
	}

	public static String getCurrentDate() {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto value = dao.get("CURRENT_DATE");
		if (value == null) {
			value = new KeyValueDto();
			value.setKey("CURRENT_DATE");
			value.setValue("19900101");
			dao.save(value);
		}
		return value.getValue();
	}

	public static String getCurrentWeek() {
		String date = getCurrentDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		Date dd;
		try {
			dd = sf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Calendar ins = Calendar.getInstance();
		ins.setTime(dd);

		int aa = ins.get(Calendar.DAY_OF_WEEK);
		if (aa == 1)
			return "日";
		if (aa == 2)
			return "一";
		if (aa == 3)
			return "二";
		if (aa == 4)
			return "三";
		if (aa == 5)
			return "四";
		if (aa == 6)
			return "五";
		return "六";
	}

	public static String getUpPercentString(String code) {
		PriceDto nowPriceDto = ShiChang.getNowPriceDto(code);
		if (nowPriceDto == null)
			return "xxxxxx";
		double price = nowPriceDto.getUp() * 100;
		return ShiChang.toPriceString(price) + "%";
	}

	public static List<String> findStocks() {
		StockDao dao = Daos.getStockDao();
		StockDtoCursor all = dao.find();
		ArrayList<String> ls = Lists.newArrayList();
		for (StockDto dto : all) {
			if (matchs(dto)) {
				ls.add(dto.getId());
			}
		}
		return ls;
	}

	private static boolean matchs(StockDto dto) {
		PriceDto d = getNowPriceDto(dto.getId());

		if (d == null)
			return false;

		double max = d.getMax60();

		double close = d.getClose();
		return close / max < 0.8;
	}

	private static void sort(List<PriceDto> all) {
		Comparator<? super PriceDto> c = new Comparator<PriceDto>() {

			@Override
			public int compare(PriceDto o1, PriceDto o2) {
				String d1 = o1.getDate();
				String d2 = o2.getDate();

				return new Integer(d1) - new Integer(d2);
			}
		};
		Collections.sort(all, c);
	}

	public static void back(int day) {
		KeyValueDao dao = Daos.getKeyValueDao();

		String date = getCurrentDate();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		try {
			Date dd = sf.parse(date);
			long time = dd.getTime();
			time -= Time.MILES_ONE_DAY * day;

			Date dt = new Date(time);

			KeyValueDto value = new KeyValueDto();
			value.setKey("CURRENT_DATE");
			value.setValue(sf.format(dt));
			dao.save(value);

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toPriceString(double price) {
		return String.format("%.2f", price);
	}

	public static void next(int day) {
		back(-day);
	}

	public static User getUser() {
		UserDao dao = Daos.getUserDao();
		UserDto udto = dao.get("user");
		if (udto == null) {
			udto = new UserDto();
			udto.setId("user");
			udto.setRmb(10000);
			dao.save(udto);
		}

		return new User(udto);
	}

	public static double getPrice(String code) {

		PriceDto nowPriceDto = getNowPriceDto(code);
		if (nowPriceDto == null)
			return 0;
		return nowPriceDto.getClose();
	}

	public static PriceDto getNowPriceDto(String code) {

		String currentDate = getCurrentDate();
		StockDao dao = Daos.getStockDao();
		StockDto dd = dao.get(code);

		if (dd == null)
			return null;

		MongoMap<PriceDto> gps = dd.getPricesFuQuan();
		List<PriceDto> all = Lists.newArrayList(gps.values());

		sort(all);

		int cur = new Integer(currentDate);
		for (int i = 0; i < all.size() - 1; i++) {
			PriceDto before = all.get(i);
			PriceDto after = all.get(i + 1);

			if (new Integer(before.getDate()) <= cur
					&& cur < new Integer(after.getDate())) {
				return before;
			}
		}

		return null;
	}
}
