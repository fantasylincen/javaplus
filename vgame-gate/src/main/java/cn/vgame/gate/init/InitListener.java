package cn.vgame.gate.init;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.util.Util;
import cn.vgame.gate.gen.dto.MongoGen.Daos;
import cn.vgame.gate.gen.dto.MongoGen.MongoDbProperties;
import cn.vgame.gate.gen.dto.MongoGen.UserDao;
import cn.vgame.gate.gen.dto.MongoGen.UserDto;

import com.google.common.io.Resources;

public class InitListener implements ServletContextListener {

	private final class MongoDbPropertiesImpl implements MongoDbProperties {
		private String host;
		private String port;
		private String name;

		private MongoDbPropertiesImpl(Properties p) {
			host = get(p, "host");
			port = get(p, "port");
			name = get(p, "name");
		}

		private String get(Properties p, String k) {
			String property = p.getProperty(k);
			Util.Exception.checkNull(property, k, "为null");
			return property;
		}

		@Override
		public int getPort() {
			return new Integer(port);
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getHost() {
			return host;
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		Daos.setProperties(createMongoDbProperties());
		initUsers();
	}

	private void initUsers() {
		UserDao dao = Daos.getUserDao();
		Properties p = Util.Property.getProperties(Resources.getResource("users.properties"));
		Set<Object> keySet = p.keySet();
		for (Object key : keySet) {
			String userId = key.toString();
			String pwd = p.getProperty(userId);
			
			if(dao.get(userId) == null) {
				UserDto dto = dao.createDTO();
				dto.setId(userId);
				dto.setPassword(pwd);
				dao.save(dto);
			}
		}
	}

	private MongoDbProperties createMongoDbProperties() {
		URL resource = Resources.getResource("");
		String file = resource.getFile();
		java.io.File f = new java.io.File(file);
		String path;
		try {
			path = f.getCanonicalPath();
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL u = Resources.getResource("mongodb.properties");
		final Properties p = Util.Property.getProperties(u);
		return new MongoDbPropertiesImpl(p);
	}
}