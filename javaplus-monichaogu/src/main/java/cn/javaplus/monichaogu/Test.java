package cn.javaplus.monichaogu;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.google.common.io.Resources;

import cn.javaplus.monichaogu.InitListener.MongoDbPropertiesImpl;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoMap;
import cn.javaplus.monichaogu.gen.dto.MongoGen.PriceDto;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDao;
import cn.javaplus.monichaogu.gen.dto.MongoGen.StockDto;
import cn.javaplus.util.Util;

public class Test {
	public static void main(String[] args) {
		URL u = Resources.getResource("mongodb.properties");
		final Properties p = Util.Property.getProperties(u);
		MongoDbPropertiesImpl mongo = new MongoDbPropertiesImpl(p);
		
		Daos.setProperties(mongo);
		
		
		StockDao dao = Daos.getStockDao();
		StockDto stock = dao.get("000569");
		MongoMap<PriceDto> fq = stock.getPricesFuQuan();
		Set<String> keySet = fq.keySet();
		List<String> ls = cn.javaplus.collections.list.Lists.newArrayList(keySet);
		Collections.sort(ls);
		for (String string : ls) {
			System.out.println(string);
		}
	}
}
