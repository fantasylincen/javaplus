package cn.mxz.listeners;

import java.util.Collection;
import java.util.List;

import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.DogzTemplet;
import cn.mxz.DogzTempletConfig;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.FormationTempletConfig;
import cn.mxz.PropTemplet;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.bag.Bag;
import cn.mxz.bag.BagAuto;
import cn.mxz.bag.Grid;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.dogz.Dogz;
import cn.mxz.dogz.DogzFactory;
import cn.mxz.events.Listener;
import cn.mxz.events.UserCreateEvent;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.listeners.init.PassGuide;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.skill.SkillManager;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.MissionChallengeDao;
import db.dao.impl.UserDogzDao;
import db.domain.MissionChallenge;
import db.domain.MissionChallengeImpl;
import db.domain.NewFighter;
import db.domain.UserDogz;
import define.D;

//测试用户
public class TesterCreate implements Listener<UserCreateEvent> {

	public static final String MXZ1 = "mxz1";
	private static final int LEVEL_55 = 55;
	private static final int LEVEL30 = 30;

	@Override
	public void onEvent(UserCreateEvent event) {

		City city = event.getCity();

		String un = event.getUserName();

		if (un != null) {
			un = un.toLowerCase();
//			if (un.startsWith("dengchengxiao") || un.startsWith("dengchenxiao")
//					|| un.equals("18321633")) {
//				forDengChengXiao(city);
//				return;
//			}
		}

		if (city.isTester()) {

			toTester(city);
		}
	}

	private void forDengChengXiao(City city) {

		// tRee 2014/7/24 15:10:46
		// 满等级 满级所有甲乙伙伴 1000w元宝5000w铜钱
		// 尽量多的甲级装备技能阵法 满级神兽 满vip 副本全开
		// 玩家vip
		vip(city);

		// 关卡
		mission(city, 50);

		playerLevel(city, 100);

		createDogz(city, 100);

		String fighterIds = "400001,400002,400003,400004,400005,400007,400008,400009,400011,400012,400013,400014,400015,400016,400017,400018,400019,400020,400021,400022,400025,400026,400027,400030,400033,400034,400035,400038,400039,400041,400042,400044,400048,400049,400051,400052,400054,400055,400056,400057,400059,400067,400069,400075,400076,400078,400079,400081,400082,400085,400086,400087,400088,400089,400090,400091,400092,400095,400096,400098,400099,400101,400102,400103,400106,400107,400108,400113,400114,400115,400116,400119,400120,400121,400124,400125,400136";
		createHeros(city, fighterIds, 100);

		gold(city, 10000000);
		cash(city, 50000000);
		jinBeiKe(city, 2000);

		String skills = "173007,173008,173009,173010,173011,173012,173013,173014,173015,173016,173017,173018,173019,173020,173021,173022,173023,173024,173025,173026,173027,173028,173029,173030,173031,173032,173033,173034,173035,173036,173037,173038,173039,173040,173041,173042,173043,173044,173045,173046,173047,173048,173049,173050,173051,173052,173053,173054,173055,173056,173057,173058,173059,173060,173061,173062,173063,173064,173065,173066,173067,173068,173069,173070,173071,173072,173073,173074,173075,173076,173077,173078,173079,173080,173081,173082,173083,173084,173085,173086,173087,173088,173089,173090,173091,173092,173093,173094,173095,173096,173097,173098,173099,173100,173101,173102,173103,173104,173105,173106,173107,173108,173109,173110,173111,173112,173113,173114,173115,173116,173117,173118,173119,173120,173121,173122,173123,173124,173125,173126,173127,173128,173129,173130,173131,173132,183001,183002,183003,183004,183005,183006,183007,183008,183009,183010,183011,183012,183013,183014,183015,183016,183017,183018,183019,183020,183021,183022,183023,183024,183025,183026,183027,183028,183029,183030,183031,183032,183033,183034,183035,183036,183037,183038,183039,183040,183041,183042,183043,183044,183045,183046,183047,183048,183049,183050,183051,183052,183053,183054,183055,183056,183057,183058,183059,183060,183061,183062,183063,183064,183065,183066,183067,183068,183069,183070,183071,183072";
		createSkills(city, skills);

		String equipments = "153020,153120,153320,153420,153520,153021,153221,153321,153421,153521,154018,154118,154218,154318,154418,154518,154001,154002,154003,154004,154005,154006,154007,154008,154009,154010,154011,154012,154013,154014,154015,154016,154017,154101,154102,154103,154104,154105,154106,154107,154108,154109,154110,154111,154112,154113,154114,154115,154116,154117,154201,154202,154203,154204,154205,154206,154207,154208,154209,154210,154211,154212,154213,154214,154215,154216,154217,154301,154302,154303,154304,154305,154306,154307,154308,154309,154310,154311,154312,154313,154314,154315,154316,154317,154401,154402,154403,154404,154405,154406,154407,154408,154409,154410,154411,154412,154413,154414,154415,154416,154417,154501,154502,154503,154504,154505,154506,154507,154508,154509,154510,154511,154512,154513,154514,154515,154516,154517";
		createEquipments(city, equipments);

		String ids = "190001,190002,190003,190004,190005,190006,190007,190008,190009,190010,190011,190012,190013,190014,190015,190016,190017,190018,190019,190020,190021,190022,190023,190024,190025,190026,190027,190028,190029,190030,190031,190032,190033,190034,190035,190036,190037,190038,190039,190040,190041,190042,190043,190044,190045,190046";
		tactical(city, ids);

		new PassGuide().pass(city);
	}

	private void createDogz(City city, int i) {
		List<DogzTemplet> all = DogzTempletConfig.getAll();
		UserCounter his = city.getUserCounterHistory();
		his.set(CounterKey.DOGZ_LEVEL, i);
		for (DogzTemplet dt : all) {
			int dogzId = dt.getDogzId();
			Dogz dogz = DogzFactory.createNewDogz(city, dogzId);
			UserDogz dto = dogz.getDto();
			dto.setLevel(i);
			UserDogzDao DAO = DaoFactory.getUserDogzDao();
			DAO.update(dto);
		}
	}

	private void jinBeiKe(City city, int i) {
		city.getPlayer().add(PlayerProperty.NEW_GOLD, i);
	}

	private void playerLevel(City city, int i) {
		PlayerHero player = city.getTeam().getPlayer();
		NewFighter dto = player.getDto();
		dto.setLevel(i);
		player.commit();
	}

	private void cash(City city, int i) {
		city.getPlayer().add(PlayerProperty.CASH, i);
	}

	private void gold(City city, int i) {
		city.getPlayer().addGiftGold(i);
	}

	public void toTester(City city) {

		if (city.getId().startsWith("mxz")) {
			forMxz(city);
			return;
		}

		if (city.getId().startsWith("hbs")) {
			forHbs(city);
			return;
		}

		// 玩家vip
		vip(city);

		// 关卡
		mission(city, 50);

		// 道具
		prop(city);

		// 玩家属性
		player(city);

		// 装备
		equipment(city);

		// 技能
		skill(city);

		// 阵法
		tactical(city);

		// 战士
		fighters(city);

		city.getPrizeSender1().send(D.INIT_TESTER_PRIZE);
	}

	private void forHbs(City city) {
		// TODO Auto-generated method stub

	}

	private void forMxz(City city) {

		// 新号
		if (city.getId().startsWith(MXZ1)) {

			// 通关一半 部分物品
		} else if (city.getId().startsWith("mxz2")) {

			city.getPlayer().addGiftGold(4000);
			city.getPlayer().add(PlayerProperty.CASH, 500000);
			city.getPlayer().add(PlayerProperty.NEW_GOLD, 1000);

			NewFighter dto = city.getTeam().getPlayer().getDto();
			dto.setLevel(LEVEL30);
			DaoFactory.getNewFighterDao().update(dto);

			String heros = "400001,400002,400003,400004,400005,400007,400008,400009,400011,400012,400013,400014,400015,400016,400017,400018,400019,400020,400021,400022,400025,400026,400027,400030,400033,400034,400035,400038,400039,400041,400042,400044,400048,400049,400051,400052,400054,400055,400056,400057,400059,400067,400069,400075";
			String equipments = "153020,153120,153320,153420,153520,153021,153221,153321,153421,153521,154018,154118,154218,154318,154418,154518,154001,154002,154003,154004,154005,154006,154007,154008,154009,154010,154011,154012,154013,154014,154015,154016,154017,154101,154102,154103,154104,154105,154106,154107,154108,154109,154110,154111,";
			String skills = "173007,173008,173009,173010,173011,173012,173013,173014,173015,173016,173017,173018,173019,173020,173021,173022,173023,173024,173025,173026,173027,173028,173029,173030,173031,173032,173033,173034,173035,173036,173037,173038,173039,173040,173041,173042,173043,173044,173045,173046,173047,173048,173049,173050,173051,173052,173053,173054,173055,173056,173057,173058,173059";
			String zhenFas = "190001,190002,190003,190004,190005,190006,190007,190008,190009,190010,190011,190012,190013,190014,190015,190016,190017,190018,190019,190020,190021,190022,190023,190024,190025,190026";
			String rewards = "130032,200|130033,200";

			createHeros(city, heros, LEVEL_55);
			createEquipments(city, equipments);
			createSkills(city, skills);
			createZhenFas(city, zhenFas);
			createProps(city, rewards);

			// 关卡
			mission(city, 15);

			// 55级超级帐号
		} else if (city.getId().startsWith("mxz3")) {
			city.getPlayer().addGiftGold(9000);
			city.getPlayer().add(PlayerProperty.CASH, 1000000);
			city.getPlayer().add(PlayerProperty.NEW_GOLD, 2000);

			NewFighter dto = city.getTeam().getPlayer().getDto();
			dto.setLevel(LEVEL_55);
			DaoFactory.getNewFighterDao().update(dto);

			String heros = "400001,400002,400003,400004,400005,400007,400008,400009,400011,400012,400013,400014,400015,400016,400017,400018,400019,400020,400021,400022,400025,400026,400027,400030,400033,400034,400035,400038,400039,400041,400042,400044,400048,400049,400051,400052,400054,400055,400056,400057,400059,400067,400069,400075,400076,400078,400079,400081,400082,400085,400086,400087,400088,400089,400090,400091,400092,400095,400096,400098,400099,400101,400102,400103,400106,400107,400108,400113,400114,400115,400116,400119,400120,400121,400124,400125,400136";
			String equipments = "153020,153120,153320,153420,153520,153021,153221,153321,153421,153521,154018,154118,154218,154318,154418,154518,154001,154002,154003,154004,154005,154006,154007,154008,154009,154010,154011,154012,154013,154014,154015,154016,154017,154101,154102,154103,154104,154105,154106,154107,154108,154109,154110,154111,154112,154113,154114,154115,154116,154117,154201,154202,154203,154204,154205,154206,154207,154208,154209,154210,154211,154212,154213,154214,154215,154216,154217,154301,154302,154303,154304,154305,154306,154307,154308,154309,154310,154311,154312,154313,154314,154315,154316,154317,154401,154402,154403,154404,154405,154406,154407,154408,154409,154410,154411,154412,154413,154414,154415,154416,154417,154501,154502,154503,154504,154505,154506,154507,154508,154509,154510,154511,154512,154513,154514,154515,154516,154517";
			String skills = "173007,173008,173009,173010,173011,173012,173013,173014,173015,173016,173017,173018,173019,173020,173021,173022,173023,173024,173025,173026,173027,173028,173029,173030,173031,173032,173033,173034,173035,173036,173037,173038,173039,173040,173041,173042,173043,173044,173045,173046,173047,173048,173049,173050,173051,173052,173053,173054,173055,173056,173057,173058,173059,173060,173061,173062,173063,173064,173065,173066,173067,173068,173069,173070,173071,173072,173073,173074,173075,173076,173077,173078,173079,173080,173081,173082,173083,173084,173085,173086,173087,173088,173089,173090,173091,173092,173093,173094,173095,173096,173097,173098,173099,173100,173101,173102,173103,173104,173105,173106,173107,173108,173109,173110,173111,173112,173113,173114,173115,173116,173117,173118,173119,173120,173121,173122,173123,173124,173125,173126,173127,173128,173129,173130,173131,173132,183001,183002,183003,183004,183005,183006,183007,183008,183009,183010,183011,183012,183013,183014,183015,183016,183017,183018,183019,183020,183021,183022,183023,183024,183025,183026,183027,183028,183029,183030,183031,183032,183033,183034,183035,183036,183037,183038,183039,183040,183041,183042,183043,183044,183045,183046,183047,183048,183049,183050,183051,183052,183053,183054,183055,183056,183057,183058,183059,183060,183061,183062,183063,183064,183065,183066,183067,183068,183069,183070,183071,183072";
			String zhenFas = "190001,190002,190003,190004,190005,190006,190007,190008,190009,190010,190011,190012,190013,190014,190015,190016,190017,190018,190019,190020,190021,190022,190023,190024,190025,190026,190027,190028,190029,190030,190031,190032,190033,190034,190035,190036,190037,190038,190039,190040,190041,190042,190043,190044,190045,190046";
			String rewards = "130032,200|130033,200|130045,2000";

			createHeros(city, heros, LEVEL_55);
			createEquipments(city, equipments);
			createSkills(city, skills);
			createZhenFas(city, zhenFas);
			createProps(city, rewards);

			// 玩家vip
			vip(city);

			// 关卡
			mission(city, 50);
		}

	}

	private void createProps(City city, String rewards) {
		city.getPrizeSender1().send(rewards);
	}

	private void createZhenFas(City city, String zhenFas) {
		Formation formation = city.getFormation();

		List<Integer> ls = Util.Collection.getIntegers(zhenFas);
		for (Integer id : ls) {
			formation.addNewTactical(id);
		}
	}

	private void createSkills(City city, String skills) {
		SkillManager sm = city.getSkillManager();
		List<Integer> ls = Util.Collection.getIntegers(skills);
		sm.add(ls);
	}

	private void createEquipments(City city, String equipments) {
		List<Integer> hs = Util.Collection.getIntegers(equipments);
		for (Integer id : hs) {
			city.getEquipmentManager().createNewEquipment(id);
		}
	}

	private void createHeros(City city, String heros, int level) {
		List<Integer> hs = Util.Collection.getIntegers(heros);
		for (Integer id : hs) {
			Hero h = createNewHero(city, id);
			if (h != null) {
				h.getDto().setLevel(level);
				h.commit();
			}
		}
	}

	private void vip(City city) {
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) 16);
		int g = temp.getGrowth();
		city.getUserCounterHistory().add(CounterKey.VIP_GROWTH, g);
	}

	private void player(City city) {
		Player player = city.getPlayer();
		player.add(PlayerProperty.CASH, 10000);
		player.addGiftGold(10000);
		player.add(PlayerProperty.NEW_GOLD, 10000);
		player.add(PlayerProperty.PHYSICAL, 100000);
		player.add(PlayerProperty.POWER, 100000);
		player.add(PlayerProperty.SHOU_HUN, 100000);

		NewFighter dto = city.getTeam().getPlayer().getDto();
		dto.setLevel(LEVEL_55);
		DaoFactory.getNewFighterDao().update(dto);
	}

	private void prop(City city) {

		String props = "刷新令,金宝箱";
		String[] split = props.split(",");
		for (String propName : split) {
			addProp(city, propName);
		}

		BagAuto bag = city.getBagAuto();

		bag.addProp(D.ID_JIN_JIE_DAN, 10000);
		bag.addProp(140032, 10);

		// bag.addProp(137282, 100);
		// bag.addProp(138037, 100);
		// bag.addProp(138038, 100);
		bag.addProp(138039, 100);

		List<String> ls = Lists.newArrayList();
		ls.add("136641,1|136642,1|136643,1|136644,1|136645,1");
		ls.add("136646,1|136647,1|136648,1|136649,1");
		ls.add("136650,1|136651,1|136652,1|136653,1");
		ls.add("136654,1|136655,1|136656,1|136657,1");
		// ls.add("136658,1|136659,1|136660,1|136661,1|136662,1");
		// ls.add("136663,1|136664,1|136665,1|136666,1");
		// ls.add("136667,1|136668,1|136669,1|136670,1");
		// ls.add("136671,1|136672,1|136673,1|136674,1");
		// ls.add("136675,1|136676,1|136677,1|136678,1|136679,1");
		// ls.add("136680,1|136681,1|136682,1|136683,1");
		// ls.add("136684,1|136685,1|136686,1|136687,1|136688,1");
		// ls.add("136689,1|136690,1|136691,1|136692,1|136693,1");
		// ls.add("136694,1|136695,1|136696,1|136697,1");
		// ls.add("136698,1|136699,1|136700,1|136701,1");
		// ls.add("136702,1|136703,1|136704,1|136705,1|136706,1");
		// ls.add("136707,1|136708,1|136709,1|136710,1|136711,1");
		// ls.add("136712,1|136713,1|136714,1");
		// ls.add("136715,1|136716,1|136717,1|136718,1|136719,1");
		// ls.add("136720,1|136721,1|136722,1|136723,1");
		// ls.add("136724,1|136725,1|136726,1|136727,1");
		// ls.add("136728,1|136729,1|136730,1|136731,1");
		// ls.add("136732,1|136733,1|136734,1|136735,1");

		Bag<Grid> pb = city.getPiecesBag();
		pb.addProp(Util.Collection.linkWith("|", ls));
	}

	private void addProp(City city, String propName) {
		Collection<PropTemplet> all = PropTempletFactory.getAll();
		for (PropTemplet pt : all) {
			if (pt.getName().equals(propName)) {
				city.getBagAuto().addProp(pt.getId(), 1);
				return;
			}
		}
		Debuger.debug("TesterCreate.addProp() " + propName + " 赠送失败!");
	}

	private void fighters(City city) {

		createNewHero(city, "姜子牙,东海龙王," + "杨戬,哪吒," + "胡喜媚,妖怪战神,王贵人,蝎子精,"
				+ "罗刹女,邬文化,王贵人," + "微子衍,微子启," + "北海龙王,龙吉公主," + "飞廉,恶来");
	}

	private void createNewHero(City city, String fighters) {
		String[] split = fighters.split(",");
		for (String string : split) {
			List<FighterTemplet> all = FighterTempletConfig.findByName(string);
			for (FighterTemplet t : all) {
				int id = t.getId();
				if (t.getCategory() != 4) {
					continue;
				}
				createNewHero(city, id);
			}
		}
	}

	private Hero createNewHero(City city, int id) {
		Team team = city.getTeam();
		if (team.contains(id)) {
			return null;
		}
		Hero h = team.createNewHero(id);
		h.getDto().setLevel(LEVEL_55);

		h.commit();
		return h;
	}

	private static void tactical(City city) {
		Formation formation = city.getFormation();
		formation.addNewTactical(FormationTempletConfig.getMinKey());
	}

	private static void tactical(City city, String ids) {
		List<Integer> ls = Util.Collection.getIntegers(ids);
		Formation formation = city.getFormation();
		for (Integer id : ls) {
			formation.addNewTactical(id);
		}
	}

	private static void skill(City city) {
		SkillManager sm = city.getSkillManager();
		String t = "170184,170185,170186,170187,170188,170189,170190,170191,170192,170193,170194,170195,170196,170197,170198,170199,170200,170201";
		List<Integer> ls = Util.Collection.getIntegers(t);
		sm.add(ls);
	}

	private static void equipment(City city) {
		List<Integer> keys = Lists.newArrayList(EquipmentTempletConfig
				.getKeys());
		Util.Collection.upset(keys);

		String equipment2 = "九彩育灵玉,九彩天重剑,九彩云母杖,九彩游梦服,九彩斗仙旗,九彩云斑鸟";

		equipments(city, equipment2);

		String equipments = "154001,154002,154003,154004,154005,154006,154007,"
				+ "154008,154009,154010,154011,154012,154013,154014,154015,154016,"
				// +
				// "154017,154101,154102,154103,154104,154105,154106,154107,154108,"
				// +
				// "154109,154110,154111,154112,154113,154114,154115,154116,154117,"
				// +
				// "154201,154202,154203,154204,154205,154206,154207,154208,154209,"
				// +
				// "154210,154211,154212,154213,154214,154215,154216,154217,154301,"
				// +
				// "154302,154303,154304,154305,154306,154307,154308,154309,154310,"
				// +
				// "154311,154312,154313,154314,154315,154316,154317,154401,154402,"
				// +
				// "154403,154404,154405,154406,154407,154408,154409,154410,154411,"
				// +
				// "154412,154413,154414,154415,154416,154417,154501,154502,154503,"
				+ "154504,154505,154506,154507,154508,154509,154510,154511,154512,"
				+ "154513,154514,154515,154516,154517";
		List<Integer> sub = Util.Collection.getIntegers(equipments);
		city.getEquipmentManager().createNewEquipment(sub);
	}

	private static void equipments(City city, String equipment) {
		StringResolver resolve = Util.Str.resolve(equipment);
		List<StringResolver> all = resolve.split(",");
		for (StringResolver s : all) {
			String name = s.getString();
			List<EquipmentTemplet> e = EquipmentTempletConfig.findByName(name);
			addEquipments(e, city);
		}
	}

	private static void addEquipments(List<EquipmentTemplet> e, City city) {
		for (EquipmentTemplet eq : e) {
			city.getEquipmentManager().createNewEquipment(eq.getId());
		}
	}

	private static String buildText() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < D.GUIDE_SIZE; i++) {

			sb.append("1");
		}
		return sb + "";
	}

	private static void mission(City city, int mid) {

		city.getUserCounterHistory().set(CounterKey.MAX_MISSION_ID, mid);

		MissionChallengeDao DAO = DaoFactory.getMissionChallengeDao();
		List<MissionChallenge> ls = Lists.newArrayList();
		for (int i = 0; i < mid; i++) {
			MissionChallenge mm = new MissionChallengeImpl();
			if (i != 0) {// 第一关没有支线
				mm.setBranchIsCross(true);
			}
			mm.setMainIsCross(true);
			mm.setMissionId(i + 1);
			mm.setUname(city.getId());

			ls.add(mm);
			Debuger.debug("TesterCreate.mission() 初始化副本");
		}
		DAO.addAll(ls);
	}

}
