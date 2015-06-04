package cn.javaplus.time.colddown;

import cn.javaplus.time.colddown.compulsive.ColdDownCheckAfterAdd;

/**
 * 始终不会被冻结的CD
 * @author 	林岑
 * @time	2013-4-17
 */
public class ColdDownUnFreezing extends ColdDownCheckAfterAdd {

	public ColdDownUnFreezing() {
		super(0, 1000, 0);
	}

	@Override
	protected void updateEndTime() {

	}

}
