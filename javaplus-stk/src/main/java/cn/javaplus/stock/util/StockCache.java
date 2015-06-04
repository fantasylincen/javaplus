package cn.javaplus.stock.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.javaplus.stock.stock.Stock1;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

public class StockCache {

	public Stock1 get(String id) {
		File f = new File(Market.WORKSPACE_DIR + "cache." + id);
		if (!f.exists()) {
			return null;
		}

		byte[] data = Util.File.getBytes(f);
		ByteArrayInputStream in = null;
		ObjectInputStream o = null;
		try {
			in = new ByteArrayInputStream(data);
			o = new ObjectInputStream(in);
			Object obj = o.readObject();
			return (Stock1) obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(in);
			Closer.close(o);
		}
	}

	public void put(String id, Stock1 s) {
		File f = new File(Market.WORKSPACE_DIR + "cache." + id);

		FileOutputStream out = null;
		ObjectOutputStream o = null;
		try {
			out = new FileOutputStream(f);
			o = new ObjectOutputStream(out);
			o.writeObject(s);
			o.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(out);
			Closer.close(o);
		}

	}

}
