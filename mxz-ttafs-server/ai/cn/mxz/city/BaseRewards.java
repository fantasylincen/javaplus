package cn.mxz.city;import java.util.Set;import com.google.common.collect.Sets;public class BaseRewards {	public static class BaseRewardId {		/**
		 * 銅錢  遊戲幣，用於遊戲日常消耗等。
		 */
		public static final int TongQian_110001 = 110001;
		/**
		 * 元寶  充值獲得貨幣，可用于商城購物和尋仙等。
		 */
		public static final int YuanBao_110002 = 110002;
		/**
		 * 銅仙令  普通的權杖，用於尋仙中召喚神將！
		 */
		public static final int TongXianLing_110003 = 110003;
		/**
		 * 銀仙令  銀質的優質權杖，用於尋仙中召喚神將！
		 */
		public static final int YinXianLing_110004 = 110004;
		/**
		 * 金仙令  黃金特質權杖，在尋仙中更容易召喚到優質的神將！
		 */
		public static final int JinXianLing_110005 = 110005;
		/**
		 * 經驗  神將經驗，可提升神將等級。
		 */
		public static final int JingYan_110006 = 110006;
		/**
		 * 體力  在副本中行動時消耗。
		 */
		public static final int TiLi_110007 = 110007;
		/**
		 * 精力  競技場、奪寶、活動中戰鬥時消耗。
		 */
		public static final int JingLi_110008 = 110008;
		/**
		 * 元寶  系統贈送的元寶。
		 */
		public static final int YuanBao_110009 = 110009;
		/**
		 * 修行  修行值
		 */
		public static final int XiuXing_110010 = 110010;
		/**
		 * 積分  每日目標積分
		 */
		public static final int JiFen_110011 = 110011;
		/**
		 * 聲望  獎勵聲望
		 */
		public static final int ShengWang_110012 = 110012;
		/**
		 * 緣分  雙修緣分
		 */
		public static final int YuanFen_110013 = 110013;
		/**
		 * 銅錢  遊戲幣，通過戰鬥、副本獲得。
		 */
		public static final int TongQian_110014 = 110014;
		/**
		 * 尋仙令  仙市尋仙用的道具
		 */
		public static final int XunXianLing_110015 = 110015;
		/**
		 * 活動幣  參與活動獲得，用於尋仙之用
		 */
		public static final int HuoDongBi_110016 = 110016;
		/**
		 * 活動幣  參與活動獲得，用於尋仙之用
		 */
		public static final int HuoDongBi_110017 = 110017;
		/**
		 * 活動幣  參與活動獲得，用於尋仙之用
		 */
		public static final int HuoDongBi_110018 = 110018;
		/**
		 * 活動幣  參與活動獲得，用於尋仙之用
		 */
		public static final int HuoDongBi_110019 = 110019;
		/**
		 * 獸魂  兌換神獸碎片
		 */
		public static final int ShouHun_110020 = 110020;
		/**
		 * 巨額銅錢  完成首充即可得50萬銅錢
		 */
		public static final int JuETongQian_110021 = 110021;
		/**
		 * 金貝殼  運營活動產出的貨幣
		 */
		public static final int JinBeiKe_110022 = 110022;
		/**
		 * 七色晶石  用於黑市兌換道具
		 */
		public static final int QiSeJingShi_110023 = 110023;
		/**
		 * 榮譽  鬥法日常獎勵貨幣
		 */
		public static final int RongYu_110024 = 110024;
		/**
		 * 白水晶  用於黑市兌換道具
		 */
		public static final int BaiShuiJing_110025 = 110025;
		/**
		 * 黃水晶  用於黑市兌換道具
		 */
		public static final int HuangShuiJing_110026 = 110026;
	}		private static Set<Integer> ids = Sets.newHashSet(110001,110002,110003,110004,110005,110006,110007,110008,110009,110010,110011,110012,110013,110014,110015,110016,110017,110018,110019,110020,110021,110022,110023,110024,110025,110026);	/**	 * 判断这个奖励是不是基本奖励	 */	public static boolean isBaseReward(int id) {		return ids.contains(id);	}	/**	 * 给user赠送基本奖励	 * @param user	 * @param id	 * @param count	 */	/*public static void add(City user, int id, int count) {		switch(id) {		case 110001:
			user.getPlayer().add(PlayerProperty.TongQian_110001, count);
			return;

		case 110002:
			user.getPlayer().add(PlayerProperty.YuanBao_110002, count);
			return;

		case 110003:
			user.getPlayer().add(PlayerProperty.TongXianLing_110003, count);
			return;

		case 110004:
			user.getPlayer().add(PlayerProperty.YinXianLing_110004, count);
			return;

		case 110005:
			user.getPlayer().add(PlayerProperty.JinXianLing_110005, count);
			return;

		case 110006:
			user.getPlayer().add(PlayerProperty.JingYan_110006, count);
			return;

		case 110007:
			user.getPlayer().add(PlayerProperty.TiLi_110007, count);
			return;

		case 110008:
			user.getPlayer().add(PlayerProperty.JingLi_110008, count);
			return;

		case 110009:
			user.getPlayer().add(PlayerProperty.YuanBao_110009, count);
			return;

		case 110010:
			user.getPlayer().add(PlayerProperty.XiuXing_110010, count);
			return;

		case 110011:
			user.getPlayer().add(PlayerProperty.JiFen_110011, count);
			return;

		case 110012:
			user.getPlayer().add(PlayerProperty.ShengWang_110012, count);
			return;

		case 110013:
			user.getPlayer().add(PlayerProperty.YuanFen_110013, count);
			return;

		case 110014:
			user.getPlayer().add(PlayerProperty.TongQian_110014, count);
			return;

		case 110015:
			user.getPlayer().add(PlayerProperty.XunXianLing_110015, count);
			return;

		case 110016:
			user.getPlayer().add(PlayerProperty.HuoDongBi_110016, count);
			return;

		case 110017:
			user.getPlayer().add(PlayerProperty.HuoDongBi_110017, count);
			return;

		case 110018:
			user.getPlayer().add(PlayerProperty.HuoDongBi_110018, count);
			return;

		case 110019:
			user.getPlayer().add(PlayerProperty.HuoDongBi_110019, count);
			return;

		case 110020:
			user.getPlayer().add(PlayerProperty.ShouHun_110020, count);
			return;

		case 110021:
			user.getPlayer().add(PlayerProperty.JuETongQian_110021, count);
			return;

		case 110022:
			user.getPlayer().add(PlayerProperty.JinBeiKe_110022, count);
			return;

		case 110023:
			user.getPlayer().add(PlayerProperty.QiSeJingShi_110023, count);
			return;

		case 110024:
			user.getPlayer().add(PlayerProperty.RongYu_110024, count);
			return;

		case 110025:
			user.getPlayer().add(PlayerProperty.BaiShuiJing_110025, count);
			return;

		case 110026:
			user.getPlayer().add(PlayerProperty.HuangShuiJing_110026, count);
			return;

		}		throw new RuntimeException("无法识别的ID:" + id);	}*/}