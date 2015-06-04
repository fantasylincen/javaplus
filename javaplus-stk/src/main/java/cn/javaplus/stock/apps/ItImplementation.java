package cn.javaplus.stock.apps;

import java.util.List;

import cn.javaplus.stock.gen.dto.MongoGen.Daos;
import cn.javaplus.stock.gen.dto.MongoGen.DayDto;
import cn.javaplus.stock.gen.dto.MongoGen.MongoMap;
import cn.javaplus.stock.gen.dto.MongoGen.MongoMapImpl;
import cn.javaplus.stock.gen.dto.MongoGen.StockDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockDto;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDao;
import cn.javaplus.stock.gen.dto.MongoGen.StockIdDto;
import cn.javaplus.stock.stock.It;
import cn.javaplus.stock.stock.OneDayData;
import cn.javaplus.stock.stock.Stock1;

public class ItImplementation implements It {

	@Override
	public void onRead(Stock1 read) {
		StockDao dao = Daos.getStockDao();
		StockDto dto = dao.get(read.getId());
		if(dto == null)
		  dto = dao.createDTO();
		setDays(dto, read);
		dto.setId(read.getId());
		dao.save(dto);

		saveStockId(dto.getId());
	}

	private void setDays(StockDto dto, Stock1 read) {
		MongoMap<DayDto> dtos = dto.getDays();
		if(dtos == null) {
			dtos = new MongoMapImpl<DayDto>();
		}
		

		List<OneDayData> dayDatas = read.getDayDatas();
		for (OneDayData o : dayDatas) {
			dtos.put(o.getDate() + "", build(o));
		}
		
		dto.setDays(dtos);		
	}

	private void saveStockId(String id) {
		StockIdDao dao = Daos.getStockIdDao();
		StockIdDto dto = dao.createDTO();
		dto.setId(id);
		dao.save(dto);
	}


	private DayDto build(OneDayData o) {
		DayDto dto = new DayDto();
		dto.setId(o.getId());
		dto.setAvg10(o.getAvg(10));
		dto.setAvg100(o.getAvg(100));
		dto.setAvg120(o.getAvg(20));
		dto.setAvg15(o.getAvg(15));
		dto.setAvg20(o.getAvg(20));
		dto.setAvg30(o.getAvg(30));
		dto.setAvg45(o.getAvg(45));
		dto.setAvg5(o.getAvg(5));
		dto.setAvg60(o.getAvg(60));
		dto.setClose(o.getClose());
		dto.setDate(o.getDate() + "");
		dto.setDea(o.getDea());
		dto.setDiff(o.getDiff());
		dto.setEmaShort(o.getEma(Stock1.MACD_SHORT));
		dto.setEmaLong(o.getEma(Stock1.MACD_LONG));
		dto.setHigh(o.getHigh());
		dto.setLow(o.getLow());
		dto.setMacd(o.getMacd());


		dto.setMax2(o.getMax(2));
		dto.setMax3(o.getMax(3));
		dto.setMax4(o.getMax(4));
		dto.setMax5(o.getMax(5));
		dto.setMax6(o.getMax(6));
		dto.setMax10(o.getMax(10));
		dto.setMax15(o.getMax(15));
		dto.setMax20(o.getMax(20));
		
		dto.setMax30(o.getMax(30));
		dto.setMax60(o.getMax(60));
		dto.setMax90(o.getMax(90));
		dto.setOpen(o.getOpen());
		dto.setUp(o.getUp());
		dto.setPercentOfFuQuan(o.getPercentOfFuQuan());
		dto.setVolume(o.getVolume());
		dto.setOpenCloseZhenFu(o.getOpenCloseZhenFu());
		dto.setHighLowZhenFu(o.getHighLowZhenFu());
		return dto;
	}
}