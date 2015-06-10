package util.taskscheduling.task;

import java.util.TimerTask;

import recharge.LTVDataProvider;
import datalogging.DataLogDataProvider;

/**
 * 每日 日志 初始化
 * @author DXF
 */
public class EveryDayLogInitTask extends TimerTask {

	@Override
	public void run() {
		// 每日初始化ltv
		LTVDataProvider.getInstance().init();
		// 处理日志记录
		DataLogDataProvider.getInstance().handle();
	}

}
