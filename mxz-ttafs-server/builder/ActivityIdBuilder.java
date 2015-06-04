import java.util.List;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.mxz.ActivityTemplet;
import cn.mxz.ActivityTempletConfig;


public class ActivityIdBuilder {

	public static void build() {
		generateBaseRewardId();
		
	}

	private static void generateBaseRewardId() {

		Templet t = new Templet(get("res/build/ActivityIds.temp"));

		setFileds(t);
		
		t.writeToFile("D:/workspace/MobileServer/ai/cn/mxz/activity/ActivityIds.java");
	}


	private static void setFileds(Templet t) {
		List<ActivityTemplet> all = ActivityTempletConfig.getAll();
		StringPrinter out = new StringPrinter();
//		ID_FILEDS
//		REWARD_IDS
//		ADD_METHOD
		for (ActivityTemplet tt : all) {
			buildField(out, tt);
		}
		t.set("ID_FILEDS", out.toString());
	}

	private static void buildField(StringPrinter out, ActivityTemplet tt) {

		out.println("		/**");
		out.println("		 * " + tt.getName() );
		out.println("		 */");
		out.println("		public static final int " + getName(tt) +  " = " + tt.getId() + ";");
		
	}

	private static String getName(ActivityTemplet tt) {
		String name2 = tt.getName();

		name2 = name2.replaceAll("成長計畫", "成长计划");
		name2 = name2.replaceAll("限时商店", "限时黑市");
		name2 = name2.replaceAll("儲值回饋", "充值回馈");
		name2 = name2.replaceAll("每日簽到", "签到有礼");
		name2 = name2.replaceAll("好友邀請", "社群邀请");
		name2 = name2.replaceAll("每日首儲", "每日首充");
		name2 = name2.replaceAll("首儲有禮", "首充有礼");
		
		
		
		
		String name = Util.Chinese.getPinYinHump(name2);
		name = name.replaceAll("u:", "v");

		
		return name + "_" + tt.getId();
	}

	private static String get(String string) {
		return Util.File.getContent(string);
	}

}
