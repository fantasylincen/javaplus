package cn.mxz.task;

import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.city.City;
import cn.mxz.protocols.user.task.TaskP.TaskBoxPro;
import cn.mxz.qiyu.QiYuButton;
import cn.mxz.qiyu.QiYuButtons;

public class TaskBoxBuilder {

	public TaskBoxPro build(TaskBox bx) {
		TaskBoxPro.Builder b = TaskBoxPro.newBuilder();
		b.setCanReceive(bx.canReceive());
		b.setIsOpen(bx.isOpen());
		boolean activityOpen = isActivityOpen(bx);
		b.setIsActivityOpen(activityOpen);
		return b.build();
	}

	private boolean isActivityOpen(TaskBox bx) {
		City city = bx.getCity();
		int activityId = bx.getTemplet().getModuleId();
		QiYuButtons bs = city.getQiyuManager().getButtons();
		List<QiYuButton> buts = bs.getButtons();
		for (QiYuButton b : buts) {
			if (b.getId() == activityId) {
				boolean open = b.isOpen();
				ActivityTemplet temp = ActivityTempletConfig.get(activityId);
				if(temp == null) {
					return false;
				}
				return open && Util.Time.isIn(temp.getTime().trim());
			}
		}
		return false;
	}

}
