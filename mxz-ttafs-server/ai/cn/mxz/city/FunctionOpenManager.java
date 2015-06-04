package cn.mxz.city;public class FunctionOpenManager {	/**	 * 模块名字	 */	public static enum ModuleType {		/**
		 * 背包模块
		 */
		BeiBaoMoKuai,

		/**
		 * 神兽模块
		 */
		ShenShouMoKuai,

		/**
		 * 夺宝模块
		 */
		DuoBaoMoKuai,

		/**
		 * 伙伴模块
		 */
		HuoBanMoKuai,

		/**
		 * 技能模块
		 */
		JiNengMoKuai,

		/**
		 * 布阵模块
		 */
		BuZhenMoKuai,

		/**
		 * 仙市模块
		 */
		XianShiMoKuai,

		/**
		 * 关卡模块
		 */
		GuanKaMoKuai,

		/**
		 * 目标成就
		 */
		MuBiaoChengJiu,

		/**
		 * 装备模块
		 */
		ZhuangBeiMoKuai,

		/**
		 * 斗法模块
		 */
		DouFaMoKuai,

		/**
		 * 第3个阵位
		 */
		DiGeZhenWei,

		/**
		 * 助威团
		 */
		ZhuWeiTuan,

		/**
		 * 元神
		 */
		YuanShen,

		/**
		 * 渡天劫
		 */
		DuTianJie,

		/**
		 * BOSS战
		 */
		Zhan,

		/**
		 * 云游仙人
		 */
		YunYouXianRen,

		/**
		 * 魔神入侵
		 */
		MoShenRuQin,

		/**
		 * 神秘商店
		 */
		ShenMiShangDian,

		/**
		 * 摇钱树
		 */
		YaoQianShu,

		/**
		 * 保护妲己
		 */
		BaoHuDaJi,

		/**
		 * 装备合成
		 */
		ZhuangBeiHeCheng,

		/**
		 * 主角进阶
		 */
		ZhuJiaoJinJie,

		/**
		 * 等级礼包
		 */
		DengJiLiBao,

		/**
		 * 阵法
		 */
		ZhenFa,

		/**
		 * 1级模块（hbs和后端用）
		 */
		JiMoKuaiHeHouDuanYong,

		/**
		 * 在线奖励
		 */
		ZaiXianJiangLi,

;		public static ModuleType getType(int id) {								if(id == 1) {
				return BeiBaoMoKuai;
			}


			if(id == 2) {
				return ShenShouMoKuai;
			}


			if(id == 3) {
				return DuoBaoMoKuai;
			}


			if(id == 4) {
				return HuoBanMoKuai;
			}


			if(id == 5) {
				return JiNengMoKuai;
			}


			if(id == 6) {
				return BuZhenMoKuai;
			}


			if(id == 7) {
				return XianShiMoKuai;
			}


			if(id == 8) {
				return GuanKaMoKuai;
			}


			if(id == 9) {
				return MuBiaoChengJiu;
			}


			if(id == 10) {
				return ZhuangBeiMoKuai;
			}


			if(id == 11) {
				return DouFaMoKuai;
			}


			if(id == 12) {
				return DiGeZhenWei;
			}


			if(id == 13) {
				return ZhuWeiTuan;
			}


			if(id == 14) {
				return YuanShen;
			}


			if(id == 15) {
				return DuTianJie;
			}


			if(id == 16) {
				return Zhan;
			}


			if(id == 17) {
				return YunYouXianRen;
			}


			if(id == 18) {
				return MoShenRuQin;
			}


			if(id == 19) {
				return ShenMiShangDian;
			}


			if(id == 20) {
				return YaoQianShu;
			}


			if(id == 21) {
				return BaoHuDaJi;
			}


			if(id == 22) {
				return ZhuangBeiHeCheng;
			}


			if(id == 24) {
				return ZhuJiaoJinJie;
			}


			if(id == 25) {
				return DengJiLiBao;
			}


			if(id == 26) {
				return ZhenFa;
			}


			if(id == 27) {
				return JiMoKuaiHeHouDuanYong;
			}


			if(id == 28) {
				return ZaiXianJiangLi;
			}


			throw new RuntimeException("无法找到对应类型:" + id);		}	}	private City city;	public FunctionOpenManager(City city) {		this.city = city;	}	/**	 * 该模块是否开启了	 */	public boolean isOpen(ModuleType type) {		return city.getLevel() >= getOpenLevel(type);	}		/**	 * 该模块是否开启了	 */	public boolean isOpen(int moduleId) {		int level = city.getLevel();		int openLevel = getOpenLevel(moduleId);		return level >= openLevel;	}		public int getOpenLevel(ModuleType type) {		switch(type) {		case BeiBaoMoKuai:
				return 1;


		case ShenShouMoKuai:
				return 15;


		case DuoBaoMoKuai:
				return 10;


		case HuoBanMoKuai:
				return 1;


		case JiNengMoKuai:
				return 8;


		case BuZhenMoKuai:
				return 1;


		case XianShiMoKuai:
				return 1;


		case GuanKaMoKuai:
				return 1;


		case MuBiaoChengJiu:
				return 1;


		case ZhuangBeiMoKuai:
				return 1;


		case DouFaMoKuai:
				return 12;


		case DiGeZhenWei:
				return 5;


		case ZhuWeiTuan:
				return 20;


		case YuanShen:
				return 18;


		case DuTianJie:
				return 21;


		case Zhan:
				return 14;


		case YunYouXianRen:
				return 8;


		case MoShenRuQin:
				return 16;


		case ShenMiShangDian:
				return 10;


		case YaoQianShu:
				return 13;


		case BaoHuDaJi:
				return 1;


		case ZhuangBeiHeCheng:
				return 6;


		case ZhuJiaoJinJie:
				return 4;


		case DengJiLiBao:
				return 5;


		case ZhenFa:
				return 9;


		case JiMoKuaiHeHouDuanYong:
				return 1;


		case ZaiXianJiangLi:
				return 7;


		}		return 10000;	}		public int getOpenLevel(int moduleId) {		switch(moduleId) {		case 1:
				return 1;


		case 2:
				return 15;


		case 3:
				return 10;


		case 4:
				return 1;


		case 5:
				return 8;


		case 6:
				return 1;


		case 7:
				return 1;


		case 8:
				return 1;


		case 9:
				return 1;


		case 10:
				return 1;


		case 11:
				return 12;


		case 12:
				return 5;


		case 13:
				return 20;


		case 14:
				return 18;


		case 15:
				return 21;


		case 16:
				return 14;


		case 17:
				return 8;


		case 18:
				return 16;


		case 19:
				return 10;


		case 20:
				return 13;


		case 21:
				return 1;


		case 22:
				return 6;


		case 24:
				return 4;


		case 25:
				return 5;


		case 26:
				return 9;


		case 27:
				return 1;


		case 28:
				return 7;


		}		return 10000;	}	}