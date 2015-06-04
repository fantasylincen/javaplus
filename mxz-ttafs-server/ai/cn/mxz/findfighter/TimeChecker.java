//package cn.mxz.findfighter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import message.S;
//import cn.javaplus.common.util.time.Time;
//import cn.mxz.GodTypeTemplet;
//import cn.mxz.base.exception.OperationFaildException;
//
//class TimeChecker {
//
//	public void checkTime(GodTypeTemplet temp) {
//
//		String timeOpen = temp.getTimeOpen();
//
//		String timeClose = temp.getTimeClose();
//
//		if(timeClose.trim().isEmpty() || timeOpen.trim().isEmpty()) {
//
//			return ;
//		}
//
//		boolean oneDayCheck = isOneDayCheck(temp);
//
//		if (oneDayCheck) { // 如果是每天都要检查
//
//			checkByDay(temp);
//
//		} else {
//
//			checkByTime(temp);
//		}
//	}
//
//	private void checkByTime(GodTypeTemplet temp) {
//
//		Date start = parse(temp.getTimeOpen());
//
//		Date end = parse(temp.getTimeClose());
//
//		Date now = new Date(System.currentTimeMillis());
//
//		check(now, start, end);
//	}
//
//	/**
//	 * 活动结束时间
//	 * @param temp
//	 * @return
//	 */
//	public Date getEndTime(GodTypeTemplet temp) {
//
//		boolean oneDayCheck = isOneDayCheck(temp);
//
//		if (oneDayCheck) { // 如果是每天都要检查
//
//			return parseOneDay(temp.getTimeClose());
//
//		} else {
//
//			return parse(temp.getTimeClose());
//		}
//	}
//
//	/**
//	 * 活动开始时间
//	 * @param temp
//	 * @return
//	 */
//	public Date getStartTime(GodTypeTemplet temp) {
//
//		boolean oneDayCheck = isOneDayCheck(temp);
//
//		if (oneDayCheck) { // 如果是每天都要检查
//
//			return parseOneDay(temp.getTimeOpen());
//
//		} else {
//
//			return parse(temp.getTimeOpen());
//		}
//	}
//
//	private void checkByDay(GodTypeTemplet temp) {
//
//		Date start = parseOneDay(temp.getTimeOpen());
//
//		Date end = parseOneDay(temp.getTimeClose());
//
//		Date now = new Date(System.currentTimeMillis() % Time.MILES_ONE_DAY);
//
//		start.setTime(start.getTime() % Time.MILES_ONE_DAY);
//
//		end.setTime(end.getTime() % Time.MILES_ONE_DAY);
//
//		check(now, start, end);
//	}
//
//	private void check(Date now, Date start, Date end) {
//
//		if (now.before(start) || now.after(end)) {
//
//			throw new OperationFaildException(S.S10114);
//		}
//	}
//
//
//	private Date parse(String timeOpen) {
//
//		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd|HH:mm");
//
//		try {
//
//			return s.parse(timeOpen);
//
//		} catch (ParseException e) {
//
//			throw new IllegalArgumentException("非法时间:" + timeOpen);
//		}
//	}
//
//	private Date parseOneDay(String timeOpen) {
//
//		SimpleDateFormat s = new SimpleDateFormat("HH:MM");
//
//		try {
//
//			return s.parse(timeOpen);
//
//		} catch (ParseException e) {
//
//			throw new IllegalArgumentException("非法时间:" + timeOpen);
//		}
//	}
//
//	private boolean isOneDayCheck(GodTypeTemplet temp) {
//
//		SimpleDateFormat s = new SimpleDateFormat("HH:mm");
//
//		try {
//			s.parse(temp.getTimeOpen());
//
//			s.parse(temp.getTimeClose());
//
//			return true;
//
//		} catch (ParseException e) {
//
//			return false;
//		}
//	}
//
//}
