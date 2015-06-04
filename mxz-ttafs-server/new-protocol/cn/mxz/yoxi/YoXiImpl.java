package cn.mxz.yoxi;

import cn.mxz.base.config.ServerConfig;

import com.lemon.commons.database.ServerProperties;

public class YoXiImpl implements YoXi {

	private String yoXi;

	public YoXiImpl(String yoXi) {
		ServerProperties c = ServerConfig.getConfig();
		this.yoXi = c.getId() + ":" + c.getName() + ":" + yoXi;
	}

	@Override
	public String getYoXi() {
		return yoXi;
	}

}
