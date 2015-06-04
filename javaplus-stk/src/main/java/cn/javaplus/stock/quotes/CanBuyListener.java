package cn.javaplus.stock.quotes;

import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;

/**
 * jian ting keyi mairu de gupiao
 * @author Administrator
 *
 */
public class CanBuyListener implements SaveListener {

	@Override
	public void onSave(List<StockRecord> records) {
		List<Object> ls = Lists.newArrayList();
		
		for (StockRecord r : records) {
			if(new Integer(r.getBuyCount1()) > 60000000) {
				ls.add(r.getCode());
			}
		}
		Log.d(ls);
	}

}
