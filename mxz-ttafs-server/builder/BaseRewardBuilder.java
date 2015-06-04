import java.util.Iterator;
import java.util.List;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;
import cn.mxz.BaseRewardTemplet;
import cn.mxz.BaseRewardTempletConfig;
import cn.mxz.city.PlayerProperty;


public class BaseRewardBuilder {

	public static void build() {
		generateBaseRewardId();
		
	}

	private static void generateBaseRewardId() {

		Templet t = new Templet(get("res/build/BaseRewards.temp"));

		setFileds(t);
		setIdsInit(t);
		addSwitch(t);
		
		
		t.writeToFile("D:/workspace/MobileServer/ai/cn/mxz/city/BaseRewards.java");
	}

	private static void setIdsInit(Templet t) {
		List<BaseRewardTemplet> all = BaseRewardTempletConfig.getAll();
		StringPrinter out = new StringPrinter();
//		ID_FILEDS
//		REWARD_IDS
//		ADD_SWITCH
		Iterator<BaseRewardTemplet> it = all.iterator();
		while (it.hasNext()) {
			BaseRewardTemplet next = it.next();
			out.print(next.getId());
			if(it.hasNext()) {
				out.print(",");
			}
		}
		t.set("REWARD_IDS", out.toString());
	}
	
	private static void addSwitch(Templet t) {
		List<BaseRewardTemplet> all = BaseRewardTempletConfig.getAll();
		for (BaseRewardTemplet next : all) {
			t.append("ADD_SWITCH", buildCase(next));
		}

//case 11001:
//	user.getPlayer().add(PlayerProperty.ACTIVITY1, count);
//	break;
	}
	
	private static String buildCase(BaseRewardTemplet next) {
		StringPrinter out = new StringPrinter();
		//case 11001:
//		user.getPlayer().add(PlayerProperty.ACTIVITY1, count);
//		break;
		out.println("		case " + next.getId() + ":");
		out.println("			user.getPlayer().add(PlayerProperty." + getName(next) + ", count);");
		out.println("			return;");
		return out.toString();
	}

	private static void setFileds(Templet t) {
		List<BaseRewardTemplet> all = BaseRewardTempletConfig.getAll();
		StringPrinter out = new StringPrinter();
//		ID_FILEDS
//		REWARD_IDS
//		ADD_METHOD
		for (BaseRewardTemplet tt : all) {
			buildField(out, tt);
		}
		t.set("ID_FILEDS", out.toString());
	}

	private static void buildField(StringPrinter out, BaseRewardTemplet tt) {

		out.println("		/**");
		out.println("		 * " + tt.getName() + "  " + tt.getDescription());
		out.println("		 */");
		out.println("		public static final int " + getName(tt) +  " = " + tt.getId() + ";");
		
	}

	private static String getName(BaseRewardTemplet tt) {
		String name = Util.Chinese.getPinYinHump(tt.getName());
		return name + "_" + tt.getId();
	}

	private static String get(String string) {
		return Util.File.getContent(string);
	}

}
