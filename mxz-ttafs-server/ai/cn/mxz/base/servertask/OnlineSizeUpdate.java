package cn.mxz.base.servertask;

import java.util.Date;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.util.debuger.SystemLog;
import db.GameDB;
import db.dao.impl.UmsOnlinenoDao;
import db.dao.impl.UmsOnlinenoDao1;
import db.domain.UmsOnlineno;
import db.domain.UmsOnlinenoImpl;

public class OnlineSizeUpdate extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		int size = WorldFactory.getWorld().getOnlineAll().size();
		Date now = new Date(System.currentTimeMillis());

		UmsOnlinenoDao DAO = new UmsOnlinenoDao1(GameDB.getInstance());
		UmsOnlineno ums = DAO.get(ServerConfig.getServerId());

		if(ums == null) {
			ums = new UmsOnlinenoImpl();
			ums.setServerId(ServerConfig.getServerId());
			ums.setOnlineNumber(size);
			ums.setUpdateTime(now);
		} else {
			ums.setUpdateTime(now);
			ums.setOnlineNumber(size);
		}
		DAO.save(ums);


		try {
			ThirdPartyPlatform c = ThirdPartyPlatformFactory.getThirdPartyPlatform();
			c.updateOnlineSize(size);
			SystemLog.debug("通知Erating当前在线人数:" + size);
		} catch (Exception e) {
			SystemLog.error("OnlineSizeUpdate.runSafty() 第三方平台服务器连接失败: "  + e.getMessage() , e  );
		}

		OnlineSizeManager.getInstance().adjust(size);
		
	}

}
