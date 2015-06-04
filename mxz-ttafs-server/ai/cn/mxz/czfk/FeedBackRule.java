package cn.mxz.czfk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.activity.ActivityIds;

public class FeedBackRule {

	public static String getEndTime() {
		ActivityTemplet temp = ActivityTempletConfig
				.get(ActivityIds.ChongZhiHuiKui_20);
		String time = temp.getTime();
		String[] split = time.split(" to ");
		return split[1];
	}

	public static Date getEndDate() {
		try {
			String endTime = getEndTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd|HH:mm");
			return sf.parse(endTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
