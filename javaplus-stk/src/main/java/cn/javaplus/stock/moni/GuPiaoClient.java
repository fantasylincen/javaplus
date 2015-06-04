package cn.javaplus.stock.moni;

import java.util.List;

public interface GuPiaoClient {
	List<IMyStock> getStocks();
	void buy(String id, String price, int count);
	void sell(String id, String price, int count);
	double getRmb();
	void refresh();
}
