package cn.mxz.listeners.mission;

import cn.mxz.base.prize.EquipmentStuffDropChecker;
import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;

//服务器启动时
public class InitMissionEquipmentStuffCheckerListener implements Listener<ServerStartEvent> {


	@Override
	public void onEvent(ServerStartEvent e) {
		EquipmentStuffDropChecker.init();
	}

}
