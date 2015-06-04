package cn.javaplus.stock.quotes;

import java.util.List;

public interface SaveListener {

	void onSave(List<StockRecord> records);

}
