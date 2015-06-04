package cn.javaplus.stock.stock;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.map.Maps;
import cn.javaplus.stock.gen.dto.MongoGen.ChuQuanDateDto;
import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.DayDto;
import cn.javaplus.stock.gen.dto.MongoGen.Lists;
import cn.javaplus.stock.gen.dto.MongoGen.MongoMap;
import cn.javaplus.stock.gen.dto.MongoGen.StockDto;
import cn.javaplus.stock.gen.dto.MongoGen.StockNameDto;

public class Stock2 {

	private StockDto dto;
	private List<DayData> dayDatas;
	private Map<String, DayData> dayDatasMap;
	private String name;

	public Stock2(StockDto dto) {
		this.dto = dto;
		String id = dto.getId();
		StockNameDto d = Daos.getStockNameDao().get(id);
		if (d == null)
			name = "undefine";
		else
			name = d.getName();
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return dto.getId();
	}

	public List<ChuQuanDateDto> getChuQuanDates() {
		return dto.getChuQuanDates();
	}

	public List<DayData> getDayDatas() {
		if (dayDatas == null) {
			initDayDatas();
		}
		return dayDatas;
	}

	private void initDayDatas() {
		putToList();
		sort();
		setTomorrowAndYestoday();
	}

	private void setTomorrowAndYestoday() {
		for (int i = 0; i < dayDatas.size(); i++) {
			DayData data = dayDatas.get(i);
			data.setYestoday(get(i - 1));
			data.setTomorrow(get(i + 1));
		}
	}

	private DayData get(int index) {
		if (index < 0)
			return null;
		if (index >= dayDatas.size())
			return null;
		return dayDatas.get(index);
	}

	private void putToList() {
		dayDatas = Lists.newArrayList();
		MongoMap<DayDto> days = dto.getDays();
		Collection<DayDto> all = days.values();
		for (DayDto dto : all) {
			dayDatas.add(new DayData(dto));
		}
	}

	private void sort() {
		Collections.sort(dayDatas);
	}

	public DayData getByDate(String date) {
		if (dayDatas == null)
			initMap();
		return dayDatasMap.get(date);
	}

	private void initMap() {
		dayDatasMap = Maps.newHashMap();
		for (DayData d : getDayDatas()) {
			dayDatasMap.put(d.getDate(), d);
		}
	}

}
