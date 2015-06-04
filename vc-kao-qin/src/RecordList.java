import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.javaplus.time.Time;

import com.google.common.collect.Lists;

public class RecordList {

	private List<Bean> list = Lists.newArrayList();

	public void insert(Bean bean) {
		list.add(bean);
	}

	public List<Bean> getList() {
		return list;
	}

	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	public List<Result> getMeiDaKaResult() {
		ArrayList<Result> ls = Lists.newArrayList();
		List<Date> dates = getDates();// 获得这个月的每一天
		for (Date date : dates) {
			if (!hasZaoShangDaka(date)) {
				ls.add(new Result(true, sf.format(date)));
			}

			if (!hasWanShangDaka(date)) {
				ls.add(new Result(false, sf.format(date)));
			}
		}
		return ls;
	}

	SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");

	private List<Date> getDates() {

		Bean bean = list.get(0);
		Date date = bean.getDate();

		String mouth = sf2.format(date);
		String format = sf.format(date);

		try {
			date = sf2.parse(mouth);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		long time = date.getTime();

		ArrayList<Date> ls = Lists.newArrayList();

		for (int i = 0; i < 33; i++) {
			Date d = new Date(time);
			time += Time.MILES_ONE_DAY;

			if (PrintAll.exclude.contains(sf.format(d)))
				continue;

			if (isThisMouth(mouth, d)) {

				if (isShuangXiu(d)) {
					continue;
				}
				ls.add(d);
			}
		}

		return ls;
	}

	private boolean isShuangXiu(Date d) {
		Calendar s = Calendar.getInstance();
		s.setTime(d);
		int xx = s.get(Calendar.DAY_OF_WEEK);
		return xx == 1 || xx == 7;
	}

	private boolean isThisMouth(String mouth, Date d) {
		String ss = sf2.format(d);
		return mouth.equals(ss);
	}

	public static void main(String[] args) {
		RecordList ls = new RecordList();
		ls.list.add(new Bean("", "2000-12-31", "20:11"));
		List<Date> ds = ls.getDates();

		for (Date date : ds) {
			System.out.println(sf.format(date));
		}
	}

	private boolean hasWanShangDaka(Date date) {
		for (Bean b : list) {
			Date d1 = b.getDate();
			String f1 = sf.format(d1);
			String f2 = sf.format(date);
			if (f1.equals(f2)) {
				String time = b.getTime();
				if (startWith(time, "12", "13", "14", "15", "16", "17", "18",
						"19", "20", "21", "22", "23")) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasZaoShangDaka(Date date) {
		for (Bean b : list) {
			Date d1 = b.getDate();
			String f1 = sf.format(d1);
			String f2 = sf.format(date);
			if (f1.equals(f2)) {
				String time = b.getTime();
				if (startWith(time, "0", "10", "11")) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean startWith(String time, String... ss) {
		for (String string : ss) {
			if (time.startsWith(string))
				return true;
		}
		return false;
	}
}
