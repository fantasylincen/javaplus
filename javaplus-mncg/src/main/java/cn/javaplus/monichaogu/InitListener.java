package cn.javaplus.monichaogu;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.log.Log;
import cn.javaplus.monichaogu.gen.dto.MongoGen.Daos;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoDbProperties;
import cn.javaplus.util.Util;

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
		Log.d("contextDestroyed");
		System.exit(0);
	}

	public void contextInitialized(ServletContextEvent sce) {
		Log.d("contextInitialized");
		Daos.setProperties(createMongoDbProperties());
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