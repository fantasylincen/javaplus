package cn.mxz.regist;

import java.util.Calendar;

import cn.javaplus.time.Time;
import cn.mxz.protocols.user.RegistP.CalendarPro;
import cn.mxz.protocols.user.RegistP.RegistRecordPro;
import cn.mxz.util.debuger.Debuger;

class RegistCalendarBuilder {

	public CalendarPro build(Register r) {

		CalendarPro.Builder b = CalendarPro.newBuilder();

		Calendar c = Calendar.getInstance();

		int day = c.get(Calendar.DAY_OF_MONTH);

		long firstDay = c.getTimeInMillis() - Time.MILES_ONE_DAY * day;

		b.setDay(day);


		b.setMonth(c.get(Calendar.MONTH) + 1);

		c.setTimeInMillis(firstDay);

//		b.setStart(buildStart(c));
		b.setStart(0);

		for (RegistRecord rc : r.getRecords()) {

			Debuger.debug("RegistCalendarBuilder.build()"  + rc.getDayOfMonth() + ", " + rc.getRewardId());

			b.addRecords(build(rc));
		}

		return b.build();
	}

//	/**
//	 * 日一二三四五六  0 1 2 3 4 5 6
//	 * @param c
//	 * @return
//	 */
//	private int buildStart(Calendar c) {
//
//		int i = c.get(Calendar.DAY_OF_WEEK);
//
//		Debuger.debug("RegistCalendarBuilder.buildStart() 开始天数" + i);
//
//		if(i == 7) {
//
//			return 0;
//		}
//
//		return i;
//	}

	private RegistRecordPro build(RegistRecord rc) {

		RegistRecordPro.Builder b = RegistRecordPro.newBuilder();

		b.setType(buildType(rc));// (0:空白   1:已签到   2:未签到)

		b.setRewardId(rc.getRewardId());

		return b.build();
	}

	/**
	 * (0:空白   1:已签到   2:未签到)
	 * @param rc
	 * @return
	 */
	private int buildType(RegistRecord rc) {

		if(!rc.isPast()) {	//是否还没有成为过去

			return 0;

		} else if(rc.hasReceived()) {

			return 1;

		} else {

			return 2;
		}
	}

}
