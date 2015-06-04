package cn.vgame.a.gm;

import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 修改系统干涉时长(刘雨诚请无视) ----------------- 无
 */
public class SetGanSheTimeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1338629638850090516L;

	int hour;
	int min;
	
	
	public int getHour() {
		return hour;
	}


	public void setHour(int hour) {
		this.hour = hour;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	@Override
	public String execute() throws Exception {
		Controller c = Turntable.getInstance().getController();
		c.setGanSheTime(getHour(), getMin());
		return SUCCESS;
	}
}
