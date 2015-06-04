package util.taskscheduling.task;

import java.util.TimerTask;

import datalogging.DataLogDataProvider;

import notice.NoticeManager;

/**
 * 每10分钟刷新 公告
 * @author DXF
 */
public class EveryTMUpataNoticeTask extends TimerTask{

	@Override
	public void run() {
		NoticeManager.getInstance().update_timely();
		DataLogDataProvider.getInstance().run();
	}

}
