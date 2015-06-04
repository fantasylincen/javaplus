package cn.mxz.base.config;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.lemon.commons.database.ServerProperties;

public class ConfigDAOImpl2 implements ConfigDAO {

	@Override
	public ServerProperties get(int serverId) {

		List<ServerProperties> all = getAll();
		for (ServerProperties s : all) {
			if (s.getId() == serverId) {
				return s;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ServerProperties> getAll() {

		List<ServerProperties> ls = Lists.newArrayList();

		try {

			IProperties p = Util.Property
					.getProperties("config/config.properties");
			String u = p.getProperty("serverListUrl");

			URL url = new URL(u);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(url);

			Element employees = document.getRootElement().element("serverlist");
			for (Iterator<Element> i = employees.elementIterator(); i.hasNext();) {
				Element ee = i.next();

				MyElement e = new MyElement(ee);
				ServerProperties s = new ServerProperties();

				s.setId(e.getInt("id"));

				s.setLogDataBasePassword(e.getString("logDataBasePassword"));
				s.setPort(e.getInt("port"));
				s.setDataBaseUname(e.getString("dataBaseUname"));
				s.setEnterAble(e.getBoolean("enterAble"));
				s.setTest(e.getBoolean("test"));
				s.setLogDataBaseUname(e.getString("logDataBaseUname"));
				s.setDataBasePassword(e.getString("dataBasePassword"));
				s.setGameManagerPort(e.getInt("gameManagerPort"));
				s.setUpdateSite(e.getString("updateSite"));
				s.setIp(e.getString("ip"));
				s.setDataBasePath(e.getString("dataBasePath"));
				s.setShowAble(e.getBoolean("showAble"));
				s.setLogDataBasePath(e.getString("logDataBasePath"));
				s.setName(e.getString("name"));
				s.setPlayerCountMax(e.getInt("playerCountMax"));
				s.setRechargePort(e.getInt("rechargePort"));
				s.setEratingPath(e.getString("eratingPath"));
				s.setMongoDbPath(e.getString("mongoDbPath"));

				ls.add(s);
			}

			return ls;
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

}
