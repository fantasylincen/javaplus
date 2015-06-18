import game.award.AwardInfo;
import game.award.AwardType;

import java.util.Map;

import manager.DWType;
import user.UserInfo;
import util.GMUtil;

import com.google.common.collect.Maps;

import config.equipment.EquipmentTempletCfg;
import config.fighter.HeroTempletCfg;

public class SendPrize {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String sendPrize(String user, String name, String count) {
		UserInfo u = GMUtil.getUser(user);
		int c = GMUtil.getCount(count);
		sendPrize(u, name, c);
		return "赠送物品成功!<br>玩家:" + user + "<br>名字:" + name + "<br>数量:" + count;
	}

	public void sendPrize(UserInfo u, String name, int count) {
		int id = 0;
		AwardType type = null;
		if (name.equals("金币")) {
			type = AwardType.CASH;
		} else if (name.equals("水晶")) {
			type = AwardType.GOLD;
		} else if (name.equals("奖杯")) {
			type = AwardType.TROPHY;
		} else if (name.equals("体力")) {
			type = AwardType.STRENGTH;
		} else {

			if (isInteger(name)) {
				id = Integer.parseInt(name);
			} else {
				id = getByName(name);
			}

			type = String.valueOf(id).length() == 4 ? AwardType.PROP : AwardType.HERO;
		}
		AwardInfo award = new AwardInfo(type, id, count);
		if (u.changeAward(award, "GM操作", DWType.MISCELLANEOUS) == -1)
			throwException(name);
	}

	private void throwException(String name) {
		throw new RuntimeException("赠送失败 , 未找到[" + name + "]物品!");
	}

	private int getByName(String name) {

		int id = HeroTempletCfg.getByName(name);
		if (id != 0)
			return id;

		id = EquipmentTempletCfg.getByName(name, "绿");
		if (id != 0)
			return id;

		Map<String, Integer> a = Maps.newHashMap();
		a.put("泰坦", 10000);
		a.put("猪妹", 10001);
		a.put("狗熊", 10002);
		a.put("曙光女神", 10003);
		a.put("树人", 10004);
		a.put("牛头人", 10005);
		a.put("石像鬼", 10006);
		a.put("沐沐", 10007);
		a.put("龙龟", 10008);
		a.put("皇子", 10009);
		a.put("德玛", 10010);
		a.put("炼金", 10011);
		a.put("剑魔", 10012);
		a.put("绿巨人", 10013);
		a.put("蔚", 10014);
		a.put("狮子", 10015);
		a.put("人头狗", 10016);
		a.put("人马", 10017);
		a.put("龙女", 10018);
		a.put("锐文", 10019);
		a.put("蝎子", 10020);
		a.put("盗墓贼", 10021);
		a.put("鳄鱼", 10022);
		a.put("巨魔", 10023);
		a.put("螃蟹", 10024);
		a.put("石头人", 10025);
		a.put("机器人", 10026);
		a.put("金属狂人", 10027);
		a.put("四象行者", 10028);
		a.put("沙漠死神", 10029);
		a.put("小波比", 10030);
		a.put("大酒桶", 10031);
		a.put("吸血鬼", 10032);
		a.put("雪人", 10033);
		a.put("狂战士", 10034);
		a.put("一灯大师", 10035);
		a.put("狼人", 10036);
		a.put("大天使", 10037);
		a.put("亡灵", 10038);
		a.put("信爷", 10039);
		a.put("盲僧", 10040);
		a.put("化学狂人", 10041);
		a.put("大虫子", 10042);
		a.put("斯巴达", 10043);
		a.put("战争女神", 10044);
		a.put("投弹手", 10045);
		a.put("影子刺客", 10046);
		a.put("飞天螳螂", 10047);
		a.put("泰隆", 10048);
		a.put("梦魔", 10049);
		a.put("妖姬", 10050);
		a.put("刀妹", 10051);
		a.put("卡特", 10052);
		a.put("阿卡丽", 10053);
		a.put("寡妇", 10054);
		a.put("恶魔小丑", 10055);
		a.put("探险家", 10056);
		a.put("蛮王", 10057);
		a.put("豹女", 10058);
		a.put("剑姬", 10059);
		a.put("剑圣", 10060);
		a.put("大嘴怪", 10061);
		a.put("飞斧手", 10062);
		a.put("提百万", 10063);
		a.put("阿狸", 10064);
		a.put("小鱼人", 10065);
		a.put("皎月女神", 10066);
		a.put("天启者", 10067);
		a.put("美人鱼", 10068);
		a.put("典狱长", 10069);
		a.put("卡牌大师", 10070);
		a.put("淘气精灵", 10071);
		a.put("冰雪女巫", 10072);
		a.put("蜘蛛女皇", 10073);
		a.put("暗黑元首", 10074);
		a.put("婕拉", 10075);
		a.put("炸弹人", 10076);
		a.put("机械武者", 10077);
		a.put("上古巫灵", 10078);
		a.put("发条魔灵", 10079);
		a.put("复仇焰魂", 10080);
		a.put("机械公敌", 10081);
		a.put("魔蛇女王", 10082);
		a.put("狂暴之心", 10083);
		a.put("稻草人", 10084);
		a.put("虚空先知", 10085);
		a.put("光辉女郎", 10086);
		a.put("大发明家", 10087);
		a.put("冰晶凤凰", 10088);
		a.put("时光守护者", 10089);
		a.put("坠天使", 10090);
		a.put("流浪法师", 10091);
		a.put("虚空行者", 10092);
		a.put("邪恶小法", 10093);
		a.put("死歌", 10094);
		a.put("火女", 10095);
		a.put("大统领", 10096);
		a.put("暗夜猎手", 10097);
		a.put("寒冰射手", 10098);
		a.put("德玛之翼", 10099);
		a.put("惩戒之手", 10100);
		a.put("瘟疫之源", 10101);
		a.put("高富帅", 10102);
		a.put("白富美", 10103);
		a.put("麦林炮手", 10104);
		a.put("赏金猎人", 10105);
		a.put("海洋之灾", 10106);
		a.put("法外狂徒", 10107);
		a.put("风暴之怒", 10108);
		a.put("星之子", 10109);
		a.put("琴瑟仙女", 10110);
		a.put("宝石骑士", 10111);
		a.put("暮光之眼", 10112);
		a.put("齐天大圣", 10113);
		a.put("卡特", 10114);
		a.put("圣诞老人", 10115);
		a.put("近战小兵", 30001);
		a.put("远程小兵", 30011);
		a.put("攻城小兵", 30021);
		a.put("超攻炮兵", 30024);
		a.put("超级兵", 30034);
		a.put("远古镜像", 30047);
		a.put("疾风狼", 30050);
		a.put("远古幽魂", 30062);
		a.put("蓝丶八八", 30067);
		a.put("红丶八八", 30073);
		a.put("小龙(经验)", 30081);
		a.put("小龙(金币)", 30082);
		a.put("近战小兵", 30089);
		a.put("远程小兵", 30099);
		a.put("攻城小兵", 30109);
		a.put("超攻炮兵", 30112);
		a.put("超级兵", 30122);
		a.put("精英小龙(经验)", 30129);
		a.put("精英小龙(金币)", 30130);
		a.put("大雷鸟", 30140);
		a.put("蜂鸟", 30141);
		a.put("花妖", 30142);
		a.put("树精", 30143);
		a.put("灵异果", 30144);
		a.put("巨型蜈蚣", 30145);
		a.put("迅捷蜈蚣", 30146);
		a.put("迅猛兽", 30147);
		a.put("远古凶兽", 30148);
		a.put("死灵法师", 30149);
		a.put("远古幽灵", 30150);
		a.put("神圣武师", 30151);
		a.put("铁甲守卫", 30152);
		a.put("铁甲雄鹰", 30153);
		a.put("影子侍卫", 30154);
		a.put("死亡之翼", 30155);
		a.put("钢铁兽人", 30156);
		a.put("角斗士", 30157);
		a.put("巨型仙人掌", 30158);
		a.put("剧毒仙人掌", 30159);
		a.put("兽人牧师", 30160);
		a.put("兽人法师", 30161);
		a.put("铁甲翼龙", 30162);
		a.put("部族猎人", 30163);
		a.put("虚空猎手", 30164);
		a.put("长矛侍卫", 30165);
		a.put("大法师", 30166);
		a.put("大祭司", 30167);
		a.put("蟹爪仙人掌", 30168);
		a.put("阿塔玛之戟", 1001);
		a.put("血腥九头蛇", 1101);
		a.put("无尽之刃", 1201);
		a.put("时光权杖", 1301);
		a.put("轻语", 1401);
		a.put("安娜飓风", 1501);
		a.put("天使之杖", 1601);
		a.put("冰霜之锤", 1701);
		a.put("死亡之帽", 2901);
		a.put("杜蕾矢", 3901);
		a.put("春哥甲", 4901);
		a.put("蓝盾", 5901);
		a.put("闪避靴", 6901);

		Integer obj = a.get(name);
		if (obj == null) {
			throwException(name);
		}
		return obj;
	}

	public boolean isInteger(String str) {
		return str.matches("[0-9]\\d*");
	}
}