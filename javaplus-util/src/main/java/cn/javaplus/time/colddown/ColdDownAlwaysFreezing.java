package cn.javaplus.time.colddown;

import cn.javaplus.time.colddown.compulsive.ColdDownCheckAfterAdd;

/**
 * 始终被冻结的CD
 * @author 	林岑
 * @time	2013-4-17
 */
public class ColdDownAlwaysFreezing extends ColdDownCheckAfterAdd {

	public ColdDownAlwaysFreezing() {
		super(Long.MAX_VALUE, 0, Integer.MAX_VALUE);
	}

	@Override
	protected void updateEndTime() {

	}

}
