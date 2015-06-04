package cn.javaplus.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

import cn.javaplus.util.Closer;
import cn.javaplus.util.Util.Property;

public class KeyValueDBImpl implements KeyValueDB {

	private IProperties	properties;

	private String	dbFilePath;

	/**
	 * @param dbFilePath	数据文件路径, 该文件是一个Properties文件
	 */
	public KeyValueDBImpl(String dbFilePath) {

		this.dbFilePath = dbFilePath;

		File file = new File(dbFilePath);

		if(!file.exists()) {

			try {

				file.createNewFile();

			} catch (IOException e) {

				throw new RuntimeException(e);
			}
		}

		properties = Property.getProperties(dbFilePath);
	}

	@Override
	public String get(Object key) {

		String property = properties.getProperty(key + "");

		if(property == null ) {

			return null;
		}

		return property + "";
	}

	@Override
	public void add(Object key, String value) {

		properties.put(key, value);

		commit();

	}

	private void commit() {

		PrintStream os = null;

		try {

			os = new PrintStream(new File(dbFilePath));

			properties.list(os);

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(os);
		}
	}

	@Override
	public void delete(Object key) {

		properties.remove(key);

		commit();
	}

	@Override
	public void update(Object key, String value) {

		properties.put(key, value);

		commit();
	}

	@Override
	public Set<Object> keySet() {

		return properties.keySet();
	}

}
