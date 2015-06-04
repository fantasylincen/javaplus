package cn.mxz.mission;

import cn.mxz.mission.templet.MissionNodeTemplet;
import cn.mxz.mission.type.IEvent;

/**
 * 包括IEvent以及MissionNodeTemplet两部分的一个完整节点
 * @author Administrator
 *
 */
public class MissionNode {
	
	private IEvent							event;
	private final MissionNodeTemplet		templet;
	/**
	 * 本关卡是否已经闯过
	 * @return
	 */
	boolean isDone;
	
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public MissionNode(MissionNodeTemplet templet, IEvent e, boolean isDone) {
		super();
		this.templet = templet;
		this.event = e;
		this.isDone = isDone;
	}
	public IEvent getEvent() {
		return event;
	}
	public MissionNodeTemplet getTemplet() {
		return templet;
	}
	
	@Override
	public String toString() {
		return "" + event.getType() + ":" + event.getMissionArg() + "," + templet.getId(); 
	}

	
}