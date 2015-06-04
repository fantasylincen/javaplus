package com.linekong.platform.protocol.erating.server;

import java.util.Calendar;

public class MonthRechargeKeyBuilder {

	public String getCurrentMonthKey() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		return year + "-" + month;
	}
	
	public int getCurrentMonthKeyNum(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		return Integer.parseInt( year+""+month);
	}

	public static void main(String[] args) {
		System.out.println( new MonthRechargeKeyBuilder().getCurrentMonthKeyNum());
	}
}
