package com.cnbizmedia;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.KeyValueDto;
import com.cnbizmedia.gen.dto.MongoGen.Lists;
import com.cnbizmedia.gen.dto.MongoGen.MongoDbProperties;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDao;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDao.ProjectDtoCursor;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDto;
import com.cnbizmedia.gen.dto.MongoGen.ZoneDto;
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
		Server.getRechargeManager();
		Server.getConfig().init();//初始化服务器配置
		
		initBaseConfig();
	}

	private void initBaseConfig() {
		ProjectDao p = Daos.getProjectDao();
		ProjectDtoCursor find = p.find();
		if(find.getCount() == 0)
			initBase();
	}

	private void initBase() {
		ProjectDao p = Daos.getProjectDao();
		ProjectDto dto = p.createDTO();
		dto.setId("142562186547910000009");
		dto.setGmScript("");
		dto.setName("飞禽走兽");
		
		List<ZoneDto> zones = Lists.newArrayList();
		
		zones.add(createInitDto());
		
		dto.setZones(zones);
		p.save(dto);
	}

	private ZoneDto createInitDto() {
		ZoneDto dto = new ZoneDto();
		dto.setId("10002");
		dto.setName("内网测试");
		dto.setProperties(createProperties());
		return dto;
	}

	private List<KeyValueDto> createProperties() {
		List<KeyValueDto> ls = Lists.newArrayList();
		add(ls, "dbPort", "27017", false, "数据库端口");
		add(ls, "dbName", "fqzs", false, "数据库名");
		add(ls, "dbPath", "127.0.0.1", false, "数据库路径");
		add(ls, "tokenKey", "ACoiuqwe9712311d", true, "Token验证码");
		add(ls, "webContextRoot", "vgame-a", true, "游戏服务器路径");
		add(ls, "serverIdentity", "xxxxxxxxxxxxxxx", true, "游戏服务器身份标识");
		add(ls, "host", "192.168.140.172", true, "服务器IP");
		add(ls, "isDebug", "true", true, "是否是调试模式");
		add(ls, "webContextRoot", "vgame-a", true, "游戏服务器路径");
		add(ls, "isShowZfb", "false", true, "支付宝支付界面是否显示");
		add(ls, "logFilePath", "/root/vgame-a-logs", false, "服务器日志文件路径");
		return ls;
	}

	private void add(List<KeyValueDto> ls, String key, String value,
			boolean isClientVisible, String dsc) {
		KeyValueDto dto = new KeyValueDto();
		dto.setDsc(dsc);
		dto.setIsClientVisible(isClientVisible);
		dto.setKey(key);
		dto.setValue(value);
		ls.add(dto);
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