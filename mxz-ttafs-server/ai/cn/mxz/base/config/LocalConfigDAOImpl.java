package cn.mxz.base.config;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;

import com.lemon.commons.database.ServerProperties;

public class LocalConfigDAOImpl implements ConfigDAO {

	@Override
	public ServerProperties get(int serverId) {
		
		IProperties e1 = Util.Property.getProperties(ServerConfig.CONFIG_SERVER_PROPERTIES);

		SuperProperties e = new SuperProperties(e1);
		
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
		return s;
	}

}
