package cn.javaplus.monichaogu;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.monichaogu.gen.dto.MongoGen;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoMap;
import cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDto;

import com.alibaba.fastjson.JSONArray;

public class StockView {

	public String getDataArray(String id, String dateStart, String dateEnd) {
		JSONArray jr = new JSONArray();

		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get(id);

		List<PriceDto> ls = getPrices(dto, dateStart, dateEnd);

		for (PriceDto d : ls) {
			jr.add(createPriceArray(d));
		}

		return jr.toJSONString();
	}

	private Object createPriceArray(PriceDto d) {
		JSONArray jr = new JSONArray();

		jr.add(d.getOpen());
		jr.add(d.getClose());
		jr.add(d.getLow());
		jr.add(d.getHigh());

		return jr.toJSONString();
	}

	// data:[ // 开盘，收盘，最低，最高
	// [2320.26,2302.6,2287.3,2362.94],
	// [2300,2291.3,2288.26,2308.38],
	// [2295.35,2346.5,2295.35,2346.92],
	// [2347.22,2358.98,2337.35,2363.8],
	// [2360.75,2382.48,2347.89,2383.76],
	// [2383.43,2385.42,2371.23,2391.82]

	public String getDates(String id, String dateStart, String dateEnd) {

		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get(id);

		JSONArray jr = new JSONArray();

		List<PriceDto> ls = getPrices(dto, dateStart, dateEnd);

		for (PriceDto d : ls) {
			jr.add(d.getDate());
		}

		return jr.toJSONString();
	}

	private List<PriceDto> getPrices(StockDto dto, String dateStart,
			String dateEnd) {
		MongoMap<PriceDto> prices = dto.getPricesFuQuan();

		Integer st = new Integer(dateStart);
		Integer end = new Integer(dateEnd);

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
}
