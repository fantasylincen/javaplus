package cn.javaplus.monichaogu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.monichaogu.gen.dto.MongoGen;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoMap;
import cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDto;
import cn.javaplus.time.Time;

import com.alibaba.fastjson.JSONArray;

public class StockView {

	public String getDataArray(String id, String dateEnd) {
		List<PriceDto> ls = getDataArrayList(id, dateEnd);
		return getDataArray(ls);
	}
	

	public String getDataArray(List<PriceDto> ls) {
		JSONArray jr = new JSONArray();

		for (PriceDto d : ls) {
			jr.add(createPriceArray(d));
		}

		String jsonString = jr.toJSONString();
		return jsonString;
	}
	
	public List<PriceDto> getDataArrayList(String id, String dateEnd) {

		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get(id);

		if(dto == null)
			return Lists.newArrayList();
		
		List<PriceDto> ls = getPrices(dto,  dateEnd);

		return ls;
	}

	private Object createPriceArray(PriceDto d) {
		JSONArray jr = new JSONArray();

		jr.add(leave2(d.getOpen()));
		jr.add(leave2(d.getClose()));
		jr.add(leave2(d.getLow()));
		jr.add(leave2(d.getHigh()));

		return jr;
	}
	private Double leave2(double close) {
		return new Double(String.format("%.2f", close));
	}

	// data:[ // 开盘，收盘，最低，最高
	// [2320.26,2302.6,2287.3,2362.94],
	// [2300,2291.3,2288.26,2308.38],
	// [2295.35,2346.5,2295.35,2346.92],
	// [2347.22,2358.98,2337.35,2363.8],
	// [2360.75,2382.48,2347.89,2383.76],
	// [2383.43,2385.42,2371.23,2391.82]

	public String getDates(String id, String dateEnd) {

		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get(id);
		

		JSONArray jr = new JSONArray();

		if(dto == null) {
			return jr.toJSONString();
		}
		
		List<PriceDto> ls = getPrices(dto, dateEnd);

		for (PriceDto d : ls) {
			jr.add(d.getDate());
		}

		String jsonString = jr.toJSONString();
		return jsonString;
	}

	private List<PriceDto> getPrices(StockDto dto, String dateEnd) {
		MongoMap<PriceDto> prices = dto.getPricesFuQuan();

		Integer end = new Integer(dateEnd);
		Integer st = getStart(dateEnd);

		Collection<PriceDto> values = prices.values();
		List<PriceDto> ls = Lists.newArrayList(values);

		Comparator<? super PriceDto> c = new Comparator<PriceDto>() {

			@Override
			public int compare(PriceDto o1, PriceDto o2) {
				String date = o1.getDate();
				String date2 = o2.getDate();
				return new Integer(date) - new Integer(date2);
			}
		};
		Collections.sort(ls, c);

		ls = Lists.newArrayList(ls);

		Iterator<PriceDto> it = ls.iterator();

		while (it.hasNext()) {
			MongoGen.PriceDto d = (MongoGen.PriceDto) it.next();
			String date = d.getDate();
			Integer dd = new Integer(date);
			if (st > dd || dd > end) {
				it.remove();
			}
		}

		return ls;
	}

	private Integer getStart(String dateEnd) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			date = df.parse(dateEnd);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Date date2 = new Date(date.getTime() - Time.MILES_ONE_DAY * 365);
		String format = df.format(date2);
		return new Integer(format);
	}
}
