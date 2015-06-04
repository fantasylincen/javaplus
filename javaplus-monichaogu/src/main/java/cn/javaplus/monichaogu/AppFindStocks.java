package cn.javaplus.monichaogu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.common.io.Resources;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.javaplus.monichaogu.InitListener.MongoDbPropertiesImpl;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao.StockDtoCursor;
import cn.javaplus.util.Util;

public class AppFindStocks {
	public static void main(String[] args) {
		URL u = Resources.getResource("mongodb.properties");
		final Properties p = Util.Property.getProperties(u);
		MongoDbPropertiesImpl mongo = new MongoDbPropertiesImpl(p);
		
		Daos.setProperties(mongo);
		findStocks();
	}
	
	//从2006年05月 8日左右开始
	public static List<String> findStocks() {
		StockDao dao = Daos.getStockDao();
		StockDtoCursor all = dao.find();
		ArrayList<String> ls = Lists.newArrayList();
		for (StockDto dto : all) {
			if(matchs(dto)) {
				ls.add(dto.getId());
				System.out.println(dto.getId() );
			}
		}
		return ls;
	}

	private static boolean matchs(StockDto dto) {
		PriceDto d = ShiChang.getNowPriceDto(dto.getId());

		if(d == null)
			return false;
		
		double max = d.getMax60();
		
		double close = d.getClose();
		boolean b = close / max < 0.7;
		return b && -0.01 < d.getUp() ;
	}
}
