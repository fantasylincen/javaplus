package cn.mxz.nvwa;

import cn.mxz.base.servertask.TaskSafetyLogToFile;
import cn.mxz.system.SystemCounter;
import cn.mxz.system.SystemCounterKey;

public class NvwaAddBuyTask extends TaskSafetyLogToFile {

	private AddBuy addBuy;

	public NvwaAddBuyTask(AddBuy addBuy) {
		this.addBuy = addBuy;
	}

	@Override
	public void runSafty() {
		if(NvwaRule.isStart()) {
			SystemCounter.getInstance().add(SystemCounterKey.NVWA_BOUGHT_COUNT, addBuy.getCount());
		}
	}

}
