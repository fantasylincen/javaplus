package cn.mxz.mission.old.listeners;

import cn.mxz.mission.old.BeforeEnterEvent;
import cn.mxz.mission.old.GiveUpEvent;
import cn.mxz.mission.old.NewPlayerEndEvent;
import cn.mxz.mission.old.events.EndEvent;
import cn.mxz.mission.old.events.EnterEvent;

/**
 * 副本监听器
 * @author 林岑
 *
 */
public interface MissionListener {

	/**
	 * 玩家进入副本时调用
	 * @param enterEvent
	 */
	
	void onEnter(EnterEvent e);

	/**
	 * 通关时调用
	 * @param e
	 */
	
	void onEnd(EndEvent e);

	/**
	 * 新手引导结束时调用
	 * @param newPlayerEndEvent
	 */
	
	void onNewPlayerEnd(NewPlayerEndEvent newPlayerEndEvent);

	/**
	 * 放弃地图时
	 
	 * @param giveUpEvent
	 */
	void onGiveUp(GiveUpEvent giveUpEvent);

	/**
	 * 进入地图之前调用
	 
	 * @param beforeEnterEvent
	 */
	void onBeforeEnter(BeforeEnterEvent beforeEnterEvent);

}
