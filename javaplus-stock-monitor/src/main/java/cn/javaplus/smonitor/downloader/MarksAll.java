package cn.javaplus.smonitor.downloader;

import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.javaplus.log.Log;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class MarksAll {

	private Map<String, Marks> marks = Maps.newConcurrentMap();

	public Marks get(String id) {
		id = Market.getCode(id);
		Marks m = marks.get(id);
		if (m == null) {
			m = new Marks(id + ":" + "0,0,0,0,0,0,0,0,0,0,0,0,0,0");
			put(m);
			Log.d("init");
		}
		return m;
	}

	public void put(Marks marks) {
		String id = marks.getId();
		id = Market.getCode(id);
		this.marks.put(id, marks);
	}

	public List<Marks> getAll() {
		return Lists.newArrayList(marks.values());
	}

	public void save() {

		List<Marks> ms = getAll();
		StringPrinter out = new StringPrinter();
		for (Marks marks : ms) {
			List<Integer> list = marks.toList();
			String id = marks.getId();
			id = Market.getCode(id);
			out.println(id + ":" + Util.Collection.linkWith(",", list));
		}
		Util.File.write(SMonitor.MARK_FILE_PATH_2, out);

	}

}
