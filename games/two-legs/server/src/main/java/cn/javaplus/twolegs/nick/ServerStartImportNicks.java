package cn.javaplus.twolegs.nick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import cn.javaplus.log.Log;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.NickDao;
import cn.javaplus.twolegs.mongo.MongoGen.NickDao.NickDtoCursor;
import cn.javaplus.twolegs.mongo.MongoGen.NickDto;
import cn.javaplus.twolegs.mongo.MongoGen.NickEnDao;
import cn.javaplus.twolegs.mongo.MongoGen.NickEnDao.NickEnDtoCursor;
import cn.javaplus.twolegs.mongo.MongoGen.NickEnDto;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util.Exception;

import com.google.common.io.Resources;

public class ServerStartImportNicks {

	public void importNicks() {
		if (!isImportCn()) {
			importCn();
		}
		if (!isImportEn()) {
			importEn();
		}

	}

	public static String decode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}

	private void importCn() {
		Properties p = new Properties();
		URL r = Resources.getResource("nicks.properties");
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = r.openStream();
			p.load(is);

			int id = 0;
			Set<Object> ks = p.keySet();
			NickDao dao = Daos.getNickDao();
			for (Object o : ks) {
				String key = o.toString();
				String value = p.getProperty(key);

				NickDto dto = new NickDto();
				dto.setId(id);
				dto.setNick(value);
				dao.save(dto);
				id++;
				Log.d("import cn nicks:" + value + " " + id);
			}

		} catch (IOException e) {
			throw Exception.toRuntimeException(e);
		} finally {
			Closer.close(is);
			Closer.close(br);
		}
	}

	@SuppressWarnings("unused")
	private boolean isImportCn() {
		NickDao dao = Daos.getNickDao();
		NickDtoCursor find = dao.find();
		for (NickDto dto : find) {
			return true;
		}
		return false;
	}

	private void importEn() {
		URL r = Resources.getResource("ennicks.txt");
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = r.openStream();
			br = new BufferedReader(new InputStreamReader(is, "utf8"));

			int id = 0;
			NickEnDao dao = Daos.getNickEnDao();
			while (true) {
				String ss;
				ss = br.readLine();

				if (ss == null) {
					break;
				}

				NickEnDto dto = new NickEnDto();
				dto.setId(id);
				dto.setNick(ss);
				dao.save(dto);
				id++;
				Log.d("import cn nicks:" + ss);

			}

		} catch (IOException e) {
			throw Exception.toRuntimeException(e);
		} finally {
			Closer.close(is);
			Closer.close(br);
		}
	}

	@SuppressWarnings("unused")
	private boolean isImportEn() {

		NickEnDao dao = Daos.getNickEnDao();
		NickEnDtoCursor find = dao.find();
		for (NickEnDto dto : find) {
			return true;
		}
		return false;
	}
}
