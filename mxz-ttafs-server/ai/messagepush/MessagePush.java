package messagepush;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

public enum MessagePush {

	INSTANCE;

	private final static String[]	MESSAGE	= new String[] { "晨昏三叩首，早晚一炷香（20体力）", "晨昏三叩首，早晚一炷香（20体力）", "九尾妖狐妲己即将出现！！各路仙友蓄势待发，就等尊下你了！", "传说中的大天尊已做好准备，接受天下仙友挑战！奖励翻倍，可不能错过哦！", };

	public void run() {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

		int[] delay = calcFirstDelaySecond();
		for (int i : delay) {
			System.out.println( i );
		}
		//service.scheduleAtFixedRate(myRunable, 0, 1, TimeUnit.SECONDS);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("-----------333333333333333333333333333333333");
				System.out.println( MESSAGE[0]);

			}
		},delay[0], 24 * 3600, TimeUnit.SECONDS);

		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println( MESSAGE[1]);

			}
		}, delay[1], 24 * 3600, TimeUnit.SECONDS);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println( MESSAGE[2]);

			}
		}, delay[2], 24 * 3600, TimeUnit.SECONDS);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println( MESSAGE[3]);

			}
		}, delay[3], 24 * 3600, TimeUnit.SECONDS);

	}

	/**
	 * 计算4个定时任务的第一次延时 上香 12:01 晨昏三叩首，早晚一炷香（20体力）。ret[0] 18:01
	 * 晨昏三叩首，早晚一炷香（20体力）。ret[1]
	 * 
	 * BOSS战 12:56 九尾妖狐妲己即将出现！！各路仙友蓄势待发，就等尊下你了！ret[2] 20:56
	 * 传说中的大天尊已做好准备，接受天下仙友挑战！奖励翻倍，可不能错过哦！ret[3]
	 * 
	 * @return
	 */
	int[] calcFirstDelaySecond() {
		int[] ret = new int[4];
		ret[0] = calcFirstDelaySecond("12:01");
		ret[1] = calcFirstDelaySecond("18:01");
		ret[2] = calcFirstDelaySecond("12:56");
		ret[3] = calcFirstDelaySecond("20:56");
		return ret;
	}

	int calcFirstDelaySecond(String beginStr) {
		String[] time = beginStr.split(":");
		int hour = Integer.parseInt(time[0]);
		int minute = Integer.parseInt(time[1]);
		DateTime current = new DateTime();
		int year = current.get(DateTimeFieldType.year());
		int month = current.get(DateTimeFieldType.monthOfYear());
		int day = current.get(DateTimeFieldType.dayOfMonth());
		DateTime begin = new DateTime(year, month, day, hour, minute, 0);

		int ret = 0;
		ret = (int) ((begin.getMillis() - current.getMillis()) / 1000);
		if (ret > 0) {
			return ret;
		}
		return ret + 24 * 3600;

	}

	public static void main(String[] args) {
		MessagePush.INSTANCE.run();
	}

}
