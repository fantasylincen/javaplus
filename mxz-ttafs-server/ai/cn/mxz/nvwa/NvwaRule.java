package cn.mxz.nvwa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;
import cn.mxz.RolesTemplet;
import cn.mxz.RolesTempletConfig;
import cn.mxz.activity.ActivityIds;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

public class NvwaRule {

	private static List<SplitLine> lines;
	private static List<AddBuy> addBuys;
	private static Integer price;
	private static Integer boxId;



	public static int getRemainSec() {

		Date end = getEndDate();

		long tend = end.getTime();
		long curr = System.currentTimeMillis();

		long ms = tend - curr;
		int remain = (int) (ms / 1000);

		if (remain < 0) {
			remain = 0;
		}
		return remain;

	}

	public static String getEndTime() {
		ActivityTemplet temp = ActivityTempletConfig
				.get(ActivityIds.ShangGuHunXia_19);
		String time = temp.getTime();
		String[] split = time.split(" to ");
		return split[1];
	}
	public static Date getEndDate() {
		try {
			String endTime = NvwaRule.getEndTime();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd|HH:mm");
			Date end = sf.parse(endTime);
			return end;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<SplitLine> getLines() {
		if (lines == null) {
			initLins();
		}
		return lines;
	}

	private static void initLins() {
		String role = getRoleText();
		JSONObject obj = JSON.parseObject(role);
		JSONArray zheKous = obj.getJSONArray("zheKous");
		lines = Lists.newArrayList();
		for (Object o : zheKous) {
			JSONObject jo = (JSONObject) o;
			Integer count = jo.getInteger("count");
			Integer zheKou = jo.getInteger("zheKou");
			lines.add(new SplitLine(count, zheKou));
		}
	}

	private static String getRoleText() {
		RolesTemplet temp = RolesTempletConfig.get("NV_WA_ROLE");
		String role = temp.getRole();
		return role.replaceAll("\\[newline\\]", "\r");
	}

	public static List<AddBuy> getAddBuys() {
		initAddBuys();
		return addBuys;
	}

	private static void initAddBuys() {
		if (addBuys != null) {
			return;
		}
		String role = getRoleText();
		JSONObject obj = JSON.parseObject(role);
		JSONArray zheKous = obj.getJSONArray("addBuys");
		addBuys = Lists.newArrayList();
		for (Object o : zheKous) {
			JSONObject jo = (JSONObject) o;
			String time = jo.getString("time");
			Integer addCount = jo.getInteger("addCount");
			addBuys.add(new AddBuy(time, addCount));
		}
	}

	public static boolean isStart() {
		int id = ActivityIds.ShangGuHunXia_19;
		ActivityTemplet temp = ActivityTempletConfig.get(id);
		String time = temp.getTime();
		return Util.Time.isIn(time);
	}

	public static int getBasePrice() {
		if (price == null) {
			String role = getRoleText();
			JSONObject obj = JSON.parseObject(role);
			price = obj.getInteger("basePrice");
		}
		return price;
	}

	public static int getBoxId() {
		if (boxId == null) {
			String role = getRoleText();
			JSONObject obj = JSON.parseObject(role);
			boxId = obj.getInteger("boxId");
		}
		return boxId;
	}
}
