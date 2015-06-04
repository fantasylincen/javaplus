package cn.javaplus.stock.stock;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import cn.javaplus.collections.set.Sets;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public class StockReader {

	static Collection<String> excluds = getExcluds();

	static String dir = getDir();

	static List<String> stockDirs = getStockDirs();

	private static String getDir() {
		URL resource = Resources.getResource("config.properties");
		Properties p = Util.Property.getProperties(resource);
		return p.getProperty("TONG_HUA_SHUN_PATH");
	}

	private static Collection<String> getExcluds() {
		Collection<String> excluds;
		URL url = Resources.getResource("excluds");
		excluds = Util.File.getLines(url);
		excluds = Sets.newHashSet(excluds);
		return excluds;
	}

	// private StockCache stocks = new StockCache();

	private boolean allIsZero(byte[] day) {
		for (byte b : day) {
			if (b != 0)
				return false;
		}
		return true;
	}

	private static List<String> getStockDirs() {
		String property = Util.Property.getProperties(

		Resources.getResource("config.properties")).getProperty("STOCK_DIRS");

		return Lists.newArrayList(property.split(","));
	}

	/**
	 * 读取所有 包括基金
	 */
	public List<Stock1> readAll() {

		List<String> ids = getIds();

		return getByIds(ids);

	}

	public void foreachAll(It it) {
		List<String> ids = getIds();
		foreach(it, ids);
	}

	public void foreach(It it, List<String> ids) {
		for (String id : ids) {
			Stock1 read = read(id);
			if (read != null)
				it.onRead(read);
		}
	}

	private List<Stock1> getByIds(List<String> ids) {
		ArrayList<Stock1> all = Lists.newArrayList();

		for (String id : ids) {

			// Log.d("loading... " + id);
			Stock1 read = read(id);

			if (read != null)
				all.add(read);
		}
		return all;
	}

	/**
	 * 只是深沪A股
	 * 
	 * @return
	 */
	public List<Stock1> readShenHuA() {
		List<String> ids = getIds();
		filterShenHuA(ids);
		return getByIds(ids);
	}

	public void foreachShenHu(It it) {
		List<String> ids = getIds();
		filterShenHuA(ids);
		foreach(it, ids);
	}

	private void filterShenHuA(List<String> ids) {
		Iterator<String> it = ids.iterator();

		Set<String> heads = Sets.newHashSet("60", "00", "30");

		while (it.hasNext()) {
			String id = (String) it.next();
			if (!startWith(id, heads)) {
				it.remove();
			}
		}
	}

	private boolean startWith(String id, Set<String> heads) {
		for (String h : heads) {
			if (id.startsWith(h))
				return true;
		}
		return false;
	}

	public List<String> getIds() {
		ArrayList<String> ls = Lists.newArrayList();
		for (String dir : stockDirs) {
			ls.addAll(getIds(dir));
		}
		return ls;
	}

	private Collection<? extends String> getIds(String dd) {

		ArrayList<String> ls = Lists.newArrayList();
		File d = new File(dir + dd);

		File[] files = d.listFiles();

		for (File file : files) {
			String id = file.getName().replace(".day", "");
			ls.add(id);
		}

		return ls;

	}

	private int readInt(DataInputStream in) {

		try {
			int aa = in.readUnsignedByte();
			int bb = in.readUnsignedByte();
			int cc = in.readUnsignedByte();
			int dd = in.readUnsignedByte();

			int date = (aa + 256 * (bb + 256 * (cc + 256 * dd))) & 0xFFFFFFFF;
			return date;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Stock1 read(String id) {

		Log.d(id);

		if (excluds.contains(id))
			return null;

		Stock1 s = new Stock1(id);

		File file = getExistFile(id);

		try {
			DataInputStream inputstream = new DataInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			long filesize = file.length();
			filesize = (filesize - 64) / 48;
			inputstream.skip(64 + 24 + 48 * 2);

			OneDayData priceYestoday = null;

			for (int i = 1; i < filesize; i++) {

				int len = 48 * 3 + 24;
				byte[] day = new byte[len];

				inputstream.read(day, 0, len);
				if (allIsZero(day)) {
					break;
				}

				ByteArrayInputStream bi = new ByteArrayInputStream(day);
				DataInputStream in = new DataInputStream(bi);

				double a = 1342177280;

				int date = readInt(in);
				double open = (readInt(in) + a) / 1000;
				double high = (readInt(in) + a) / 1000;
				double low = (readInt(in) + a) / 1000;
				double close = (readInt(in) + a) / 1000;
				readInt(in);// 均价 预留
				long volume = readLong(in);

				OneDayData price = new OneDayData();
				price.setId(id);
				price.setDate(date);
				price.setOpen(open);
				price.setHigh(high);
				price.setLow(low);
				price.setClose(close);
				price.setVolume(volume);
				price.setYestoday(priceYestoday);
				if (priceYestoday != null)
					priceYestoday.setTomorrow(price);

				priceYestoday = price;

				s.add(price);

			}

			s.fuQuan();
			s.calcZhiBiao();
			inputstream.close();

			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private long readLong(DataInputStream in) {

		try {
			long aa = in.readUnsignedByte();
			long bb = in.readUnsignedByte();
			long cc = in.readUnsignedByte();
			long dd = in.readUnsignedByte();
			long ee = in.readUnsignedByte();
			long ff = in.readUnsignedByte();
			long gg = in.readUnsignedByte();
			long hh = in.readUnsignedByte();

			// long date = (aa + 256 * (bb + 256 * (cc + 256 * dd))) &
			// 0xFFFFFFFF;
			long date = (aa + 256 * (bb + 256 * (cc + 256 * (dd + 256 * (ee + 256 * (ff + 256 * (gg + 256 * hh))))))) & 0xFFFFFFFFFFFFFFFFL;
			return date;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private File getExistFile(String id) {
		for (String dd : stockDirs) {
			File f = new File(dir + dd + id + ".day");
			if (f.exists())
				return f;
		}
		return null;
	}

	public List<String> getShenHuIds() {
		List<String> ids = getIds();
		filterShenHuA(ids);
		return ids;
	}
}
