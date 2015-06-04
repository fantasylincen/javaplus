package cn.mxz.base.config;

import com.lemon.commons.database.ServerProperties;

public interface ConfigDAO {

	public abstract ServerProperties get(int serverId);

}