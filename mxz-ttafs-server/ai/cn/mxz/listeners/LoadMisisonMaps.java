package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;
import cn.mxz.mission.templet.MissionPathCfg;

//服务器启动时, 加载地图json
public class LoadMisisonMaps implements Listener<ServerStartEvent> {

	@Override
	public void onEvent(ServerStartEvent e) {
		MissionPathCfg.init();
	}

}
