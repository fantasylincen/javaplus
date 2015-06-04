package cn.mxz.regist;

import java.sql.Date;
import java.util.Calendar;

class RegisterManagerImpl implements RegisterManager {

	private String	value;

	RegisterManagerImpl(String value) {
		this.value = value;
	}

	@Override
	public int getRewardId(int day) {
		
		String[] tv = value.split(":");
		
		String[] split = tv[1].split(",");
		
		String s = split[day - 1];
		
		return new Integer(s);
	}

	public boolean isTimeOut() {
		

		String[] tv = value.split(":");
		
		long time = new Long(tv[0]);
		
		Calendar c = Calendar.getInstance();
		
		//当前月份
		int mNow = c.get(Calendar.MONTH);
		
		c.setTime(new Date(time));

		//创建时月份
		int mCreate = c.get(Calendar.MONTH);
		
		return mNow != mCreate;
	}

}
