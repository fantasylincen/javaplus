package cn.javaplus.smonitor.downloader;

import java.util.List;

public interface SaveListener {

	void onSave(List<StockRecord> records);

}
