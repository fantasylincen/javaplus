package cn.javaplus.smonitor.downloader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Time;

import com.alibaba.fastjson.JSONArray;

public class SMonitor {

	public static final String ZI_XUAN_FILE_PATH = "G:/stocks/configs/smonitor-selected";
	public static final String MARK_FILE_PATH = "G:/stocks/configs/smonitor-marks";
	public static final String MARK_FILE_PATH_2 = "G:/stocks/configs/smonitor-marks-2";
	public static final String DATAS_PATH = "G:/stocks/day-datas/";
	public static SMonitor instance;
	public String id;
	private int day;
	private int min;
	private MarksAll marksAll;

	public static final SMonitor getInstance() {
		if (instance == null)
			instance = new SMonitor();
		return instance;
	}

	public int getDay() {
		if (day == 0)
			day = 3;
		return day;
	}

	public int getMin() {
		if (min == 0)
			min = 3;
		return min;
	}

	public List<Integer> getMarks(String id) {
		MarksAll all = getMarksAll();
		Marks marks = all.get(id);
		return marks.toList();
	}

	public List<String> getCurrentBuy1Status(String id) {
		return Lists.newArrayList("FF0000", "FE9900", "FD0000", "FC0000",
				"FB0000", "FA0000", "F90000");
	}

	public Collection<String> getStockIds() {
		ensureExists(ZI_XUAN_FILE_PATH);
		return Util.File.getLines(ZI_XUAN_FILE_PATH);
	}

	public boolean isMark(String id) {
		id = id.trim();
		Set<String> ids = getMarkIds();
		return ids.contains(id);
	}

	private Set<String> getMarkIds() {
		ensureExists(MARK_FILE_PATH);
		List<String> lines = Util.File.getLines(MARK_FILE_PATH);
		Set<String> ss = Sets.newHashSet(lines);

		ss.remove("");
		return ss;
	}

	private void ensureExists(String path) {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void appendZiXuan(ArrayList<String> ls) {
		Collection<String> ids = getStockIds();
		Set<String> set = Sets.newHashSet(ids);
		set.addAll(ls);
		save(set);
	}

	public void selectStock(String id) {
		this.id = id;
	}

	public String getSelectId() {
		if (id == null) {
			Collection<String> ids = getStockIds();
			for (String id : ids) {
				this.id = id;
				return id;
			}
		}
		return id;
	}

	public void mark(String id) {
		Set<String> ids = getMarkIds();
		ids.add(id);
		saveMarks(ids);
	}

	public void deleteMark(String id) {
		Set<String> ids = getMarkIds();
		ids.remove(id);
		saveMarks(ids);
	}

	private void saveMarks(Set<String> ids) {
		StringPrinter out = new StringPrinter();
		for (String id : ids) {
			out.println(id);
		}
		Util.File.write(MARK_FILE_PATH, out);
	}

	public void deleteStock(String id) {
		Collection<String> stockIds = getStockIds();
		stockIds.remove(id);
		save(stockIds);
	}

	private void save(Collection<String> ids) {
		StringPrinter out = new StringPrinter();
		for (String id : ids) {
			out.println(id);
		}
		Util.File.write(ZI_XUAN_FILE_PATH, out.toString());
	}

	/**
	 * 当前所有数据
	 */
	public String getSelectDatas() {
		List<StockRecord> lastDatas = getLastDatas();
		JSONArray arr = new JSONArray();
		for (StockRecord d : lastDatas) {
			// Log.d(d.getDate() + ":" + d.getClock());
			arr.add(d.getBuyCount1());
		}
		return arr.toJSONString();
	}

	public String getSelectVolumes() {
		List<StockRecord> lastDatas = getLastDatas();
		JSONArray arr = new JSONArray();
		for (StockRecord d : lastDatas) {
			// Log.d(d.getDate() + ":" + d.getClock());
			arr.add(d.getChengJiaoLiang());
		}
		return arr.toJSONString();
	}

	private List<StockRecord> getLastDatas() {

		List<File> fs = getLastDayFiles();
		ArrayList<StockRecord> ls = Lists.newArrayList();
		for (File file : fs) {
			ls.addAll(buildDayDatas(file));
		}
		step5Min(ls);
		return ls;
	}

	private void step5Min(ArrayList<StockRecord> ls) {
		Iterator<StockRecord> it = ls.iterator();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		Date lastDate = null;
		while (it.hasNext()) {
			StockRecord s = (StockRecord) it.next();
			String date = s.getDate() + "_" + s.getClock();
			try {
				Date c = sf.parse(date);

				if (lastDate == null) {
					lastDate = c;
				}

				long time = c.getTime();
				long time2 = lastDate.getTime();
				long reduce = time - time2;

				if (reduce < Time.MILES_ONE_MIN * getMin()) {
					it.remove();
				} else {
					lastDate = c;
				}

			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	private Collection<StockRecord> buildDayDatas(File file) {
		ArrayList<StockRecord> ls = Lists.newArrayList();
		List<String> lines = Util.File.getLines(file);
		for (String data : lines) {
			StockRecord dt = new StockRecord(data);
			ls.add(dt);
		}
		return ls;
	}

	private List<File> getLastDayFiles() {
		File f = new File(DATAS_PATH);
		File[] fs = f.listFiles();
		List<File> ls = Lists.newArrayList();
		for (File file : fs) {
			String name = file.getName();
			String code = name.substring(2, 2 + 6);
			if (code.equals(id))
				ls.add(file);
		}

		Collections.reverse(ls);

		int day2 = getDay();

		ls = Util.Collection.sub(ls, day2);
		Collections.reverse(ls);
		return ls;
	}

	/**
	 * 横坐标说明
	 */
	public String getXAxis() {
		List<StockRecord> lastDatas = getLastDatas();
		JSONArray arr = new JSONArray();
		for (StockRecord d : lastDatas) {
			String date = d.getDate();
			String substring;
			try {
				substring = date.substring(5, 10);
			} catch (Exception e) {
				e.printStackTrace();
				substring = "xx-xx";
			}
			arr.add(substring + "_" + d.getClock());
		}
		return arr.toJSONString();
	}

	public void mark(String id, int mark) {

		id = id.toLowerCase();
		id = id.replaceAll("[a-z]", "");
		MarksAll all = getMarksAll();
		Marks marks = all.get(id);
		marks.mark(mark);
	}

	public MarksAll getMarksAll() {
		if (marksAll == null)
			initMarksAll();
		return marksAll;
	}

	private void initMarksAll() {

		ensureExists(MARK_FILE_PATH_2);
		List<String> lines = Util.File.getLines(MARK_FILE_PATH_2);
		marksAll = new MarksAll();
		for (String line : lines) {
			if (!line.trim().isEmpty()) {
				marksAll.put(new Marks(line));
			}
		}
	}

	public void selectDay(int day) {
		this.day = day;
	}

	public void selectMin(int min) {
		this.min = min;
	}

}
