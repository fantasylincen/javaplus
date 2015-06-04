package cn.mxz.util.counter;

public enum CounterKey {

	/**
	 * 玩家今日登陆次数
	 */
	USER_LOGIN_TIMES_TODAY,

	/**
	 * 钓鱼次数
	 */
	FISH_TIMES,

	/**
	 * 亲密示爱次数
	 */
	PRACTICE_QMSA,

	/**
	 * 百年好合次数
	 */
	PRACTICE_BNHH,

	/**
	 * 比翼双飞次数
	 */
	PRACTICE_BYSF,

	/**
	 * 今日PVP胜利次数
	 */
	PVP_WIN_TIMES,

	/**
	 * 签到补签次数
	 */
	REGIST_REMEDY_TIMES,

	/**
	 * 上香中午
	 */
	RECEIVED_NOON,

	/**
	 * 上香晚上
	 */
	RECEIVED_NIGHT,
	//
	// /**
	// * 根据wayId寻仙的次数
	// */
	// FIND_FIGHTER_TIMES_BY_WAY,
	//
	// /**
	// * 根据ID寻仙的次数
	// */
	// FIND_FIGHTER_TIMES_BY_ID,

	/**
	 * 好友送礼物标记
	 */
	FRIEND_SEND_GIFT_MARK,

	/**
	 * 今日是否已经有10人关注我了
	 */
	IS_10_PERSON_CARE_ME,

	/**
	 * 商城购买记录
	 */
	MARKET_BUY_COUNT,

	/**
	 * 引导状态
	 */

	GUIDE_STATUS,

	/**
	 * 赠送第一件装备标记
	 */

	SEND_FIRST_EQUIPMENT_MARK,

	/**
	 * 流量使用情况(字节数)
	 */
	FLOW,

	/**
	 * 成为好友后, 增加的魅力值计数器
	 */
	FRIEND_ACCEPT_CHARM_ADD,

	/**
	 * 在线时长(秒)
	 */
	ONLINE_TIME,

	/**
	 * 是否自动接受双休申请
	 */
	PRACTICE_IS_AUTO_ACCEPT,

	/**
	 * 最后一次攻击的副本
	 */

	LAST_MAP_ATTACK,

	/**
	 * 玩家死亡时间
	 */
	PLAYER_DEATH_TIME,

	/**
	 * 每日刷新转盘的次数
	 */
	CORONA_REFRESH_TIMES,
	/**
	 * 每日免费转转盘的次数
	 */
	CORONA_FREE_RUN_TIMES,

	/**
	 * 保护妲己当前的阶段
	 */
	DAJI_PROTECT_PHASE,

	/**
	 * 保护妲己连续签到次数
	 */
	DAJI_TODAY_PROTECT_COUNT,

	// /**
	// * 今日是否签到了
	// */
	// IS_REGIST_TODAY,

	/**
	 * 聚宝盆, 捞金次数(摇钱树, 摇钱次数)
	 */
	CORNUCOPIA_RUN_TIMES,

	/**
	 * 系统累计赠送给玩家的元宝
	 */
	GIFT_GOLD_COUNT,

	/**
	 * pvp挑战次数
	 */
	PVP_CHALLENGE_TIMES,

	/**
	 * 最强王者挑战次数
	 */
	PVP_JOIN_SUPER_MAN_TIMES,

	/**
	 * PVP 失败次数
	 */
	PVP_LOSE_TIMES,

	/**
	 * PVP 最强王者连胜次数
	 */
	PVP_SUPER_MAN_WIN_STREAK,

	/**
	 * 玩家最后一次打的地图的最大怪物数量
	 */

	LAST_MAP_DEMON_MAX,

	/**
	 * 装备合成次数
	 */
	EQUIPMENT_GENERATE_COUNT,

	/**
	 * 历史双休完成次数
	 */
	PRACTICE_FINISH_TIMES,

	/**
	 * 今日起始PVP排名, 如果没有排名, 返回0
	 */
	PVP_RANK_START_TODAY,

	/**
	 * pvp最大连胜次数
	 */
	PVP_WINNING_STREAK_MAX,

	/**
	 * 传承功能使用次数
	 */
	INHERIT_TIMES,

	/**
	 * 玩转盘次数
	 */
	CORONA_RUN_TIMES,

	/**
	 * 神兽顿悟次数
	 */
	DOGZ_DUNWU_TIMES,

	/**
	 * 神兽喂养次数
	 */
	DOGZ_FEED_TIMES,

	/**
	 * 所有神将历史进阶次数中 的 最大值
	 */
	FIGHTER_LEVEL_UP_TIMES_MAX,

	/**
	 * 
	 * 某个玩家进阶次数(需要追加参数: 战士唯一ID)
	 */
	FIGHTER_LEVEL_UP_TIMES,

	/**
	 * 参加祭祀 牺牲的神将数量
	 */
	FIGHTER_SACRIFICE_COUNT,

	/**
	 * 成功吊起来的某种鱼的数量(需要追加参数: 鱼的ID)
	 */
	FISH_SUCCESS_COUNT,

	/**
	 * 玩家首次充值数
	 */
	FIRST_GOLD_COUNT,

	/**
	 * 玩家实际充值总数（各种活动送的不算）
	 */
	TOTAL_RECHARGE_GOLD_COUNT,

	/**
	 * 上香次数
	 */
	SHANG_XIANG_TIMES,

	/**
	 * 夺宝成功次数
	 */
	SNATCH_SUCCESS_TIMES,

	/**
	 * 某个地图上 某个打某个怪所获得的星星数量(参数: ":" + 地图ID + ":", 怪物ID)
	 */
	MISSION_DEMON_STAR,

	/**
	 * 保护妲己次数
	 */
	PROTECT_DAJI_TIMES,

	/**
	 * 装备升级次数
	 */
	EQUIPMENT_LEVEL_UP_TIMES,

	/**
	 * 祭祀次数
	 */
	FIGHTER_SACRIFICE_TIMES,

	/**
	 * 今日是否通关了任何一个地图
	 */
	HAS_PASS_MISSION_TODAY,

	/**
	 * 今日连胜次数(历史连胜次数, 直接从pvp数据库表里面取得)
	 */
	PVP_WINNING_STREAK_TODAY,

	/**
	 * 记录vip卡的起始时间
	 */
	VIP_CARD_START,
	/**
	 * VIP月卡类型0:什么都没有买 1:白银月卡 2:黄金月卡 3:至尊月卡
	 */
	VIP_CARD_TYPE,
	/**
	 * 是否领取过充值回馈
	 */
	RECHARGE_FEEDBACK1, RECHARGE_FEEDBACK2, RECHARGE_FEEDBACK3, RECHARGE_FEEDBACK4, RECHARGE_FEEDBACK5, RECHARGE_FEEDBACK6,

	/**
	 * 今日 寻仙 招募 次数(参数:int:招募类型(1,2,3))
	 */
	RECRUIT_TIMES,

	/**
	 * 今日叩头次数
	 */
	PVP_KOWTOW_TIMES,

	/**
	 * 今日PVP扣除免费次数的次数
	 */
	PVP_REDUCE_FREE_TIMES_TIMES,

	/**
	 * 直接挑战boss次数
	 */
	BOSS_CHALLENGE,

	/**
	 * 元神升级次数
	 */
	YUAN_SHEN_LEVEL_UP_TIMES,

	/**
	 * 非免费次数 招募次数 参数: 招募类型 xxx挑一, 百里挑一 万里挑一...1.2..3
	 */
	RECRUIT_WITH_OUT_FREE_TIMES,

	/**
	 * 技能升级次数
	 */
	SKILL_LEVEL_UP_TIMES,

	/**
	 * 合成的技能数量
	 */
	SKILL_GENERATE_COUNT,

	/**
	 * 阵法升级次数
	 */
	TACTICAL_LEVEL_UP_TIMES,

	/**
	 * 替补位逆转次数
	 */
	TI_BU_WEI_NI_ZHUAN_TIMES,

	/**
	 * 功德值
	 */
	GONG_DE,

	/**
	 * Boss挑战次数
	 */
	SHEN_MO_CHALLENGE_TIMES,

	/**
	 * 元神Reset次数
	 */
	YUAN_SHEN_RESET,

	/**
	 * 攻击好友的次数
	 */
	FRIEND_ATTACK_TIMES,

	/**
	 * 阵法合成次数
	 */
	TACTICAL_GENERATE_COUNT,

	/**
	 * 装备合成次数
	 */
	GENERATE_EQUIPMENT_COUNT,

	/**
	 * 成功复仇次数
	 */
	REVENGE_TIMES,

	// /**
	// * 是否领取了pvp排位奖励
	// */
	// HAS_RECEIVE_PVP_RANK_REWARD,

	/**
	 * 功德领奖
	 */
	GONGDE_PRIZE,
	/**
	 * 引导地图的id
	 */
	GUIDE_MISSION_ID,

	/**
	 * 引导地图的节点
	 */
	GUIDE_MISSION_NODE,

	/**
	 * 章节的星星奖励是否领取
	 * 
	 * 参数: 章节ID,奖励类型(1,2,3 : 30%, 60%, 90%)
	 */
	STAR_REWARD_RECEIVE,

	/**
	 * 每日PVP符文购买次数
	 */
	PVP_FU_WEN_BUY_TIMES,

	// /**
	// * 是否招募过战士
	// */
	// HAS_RECRUIT_FIRST_FIGHTER,

	/**
	 * 今日免费寻仙次数: 参数 寻仙类型
	 */
	RECRUIT_FREE_TIMES,

	/**
	 * 使用符文次数
	 */
	USE_FU_WEN_TIMES,

	USE_SHEN_XING_DAN_TIMES,

	USE_HUI_QI_DAN_TIMES,

	/**
	 * 是否领取了某章节的奖励
	 */
	HAS_RECEIVE_CHAPTER_REWARD,

	/**
	 * 上次保护妲己的时间
	 */
	DAJI_LAST_PROTECT_TIME,

	/**
	 * 登陆送元宝领取次数
	 */
	LOGIN_GOLD_REWARD_TIMES,

	/**
	 * 登陆奖励, 今日是否翻牌了
	 */
	HAS_OPEN_ALL_CARD,

	/**
	 * 今天是否领取了送元宝奖励
	 */
	HAS_RECEIVE_GOLD_REWARD_TODAY,

	/**
	 * 是否购买了某个等级的VIP礼包
	 */
	VIP_GIFT_BOUGHT,

	/**
	 * 今日战斗次数
	 */
	DAJI_TODAY_BATTLE_COUNT,

	/**
	 * 摇钱树 今日运势
	 */
	YUN_SHI,

	/**
	 * 摇钱树累计获得金币
	 */
	CORNUCOPIA_ALL,
	/**
	 * 今日是否点赞
	 */
	ZAN_TODAY_CLICK,
	/**
	 * 整个服务器玩家点赞的数量
	 */

	ZAN_COUNT_IN_SERVER,
	/**
	 * 下一次获奖的元宝数量
	 */
	NEXT_GOLD,
	/**
	 * 玩家自己点赞的数量
	 */
	ZAN_COUNT,

	/**
	 * 邀请有礼 礼包 是否被领取了
	 * 
	 * 参数: 邀请人数
	 */
	INVITE_GIFT,

	/**
	 * 是否领取了开服奖励
	 */
	HAS_RECEIVE_OPEN_SERVER_REWARD,

	// /**
	// * 闯阵最大层数
	// */
	// CHUANG_ZHEN_MAX_FLOOR,
	//
	//
	// /**
	// * 历史最大星星数
	// */
	// CHUANG_ZHEN_STAR_MAX_HIS,
	/**
	 * 闯阵最大场次
	 */
	CHUANG_ZHEN_MAX_TIMES,

	/**
	 * 历史最大星星数
	 */
	CHUANG_ZHEN_STAR_MAX_HIS,
	/**
	 * 是否拥有封神令
	 */
	FENGSHENGLING,
	/**
	 * 记录玩家声望兑换道具的冷却时间
	 */
	SHENGWANG_TO_DAOJU,

	/**
	 * PVP战斗次数
	 */
	PVP_FIGNTING_TIMES,

	/**
	 * 魔神全力一击次数
	 */
	SHEN_MO_QLYJ_FIGNTING_TIMES,
	/**
	 * 每日挑战boss 的轮数
	 */
	BOSS_CHALLENGE_ROUND,

	/**
	 * 闯阵 玩家在第N层, 最后一次, 获得到的星星数 参数: 楼层数
	 */
	CHUANG_ZHEN_STAR_RECEIVE,

	/**
	 * 是否有新的PVP挑战消息
	 */
	HAS_NEW_PVP_MESSAGE,

	// /**
	// * 是否有新的被夺宝的信息
	// */
	// HAS_NEW_SNATCH_MESSAGE,

	/**
	 * 该帐号是否被查封
	 */
	FREEZE,

	/**
	 * 闯阵每日排名 参数: 当前天数(1970 - 今 的天数)
	 */
	CHUANG_ZHEN_RANK,

	/**
	 * 最后一次最高楼层
	 */
	CHUANG_ZHEN_LATEST_FLOOR_MAX,

	/**
	 * 闯阵, 最后一次最多获得星星
	 */
	CHUANG_ZHEN_LATEST_STAR_MAX,

	/**
	 * 包操作日志
	 */
	PACKET_LOG,

	/**
	 * 翻牌次数
	 */
	OPEN_CARD_TIMES,
	/**
	 * 副本最大关卡数
	 */

	/**
	 * 神兽的等级
	 */
	DOGZ_LEVEL,

	MAX_MISSION_ID,

	/**
	 * 记录是否第一次领奖，如果是第一次，那么要考虑一个伪随机的问题
	 */
	DAJI_FIRST_GOT_PRIZE,
	/**
	 * 神兽的魂力
	 */
	HUN_LI,

	/**
	 * 闯阵: 标记玩家在当前这个楼层 是否领取了结算奖励
	 */
	RECEIVE_BATTLE_REWARD,

	/**
	 * 闯阵: 标记玩家在当前这个楼层 是否领取了驾车功能奖励
	 */
	RECEIVE_ADDITION_REWARD,
	/**
	 * 副本：扫荡次数
	 */
	MISSION_MOPPINGUP,
	/**
	 * 今日重置次数
	 */
	MISSION_MOPPINGUP_RESET,

	/**
	 * 开宝箱时, 记录的某些宝箱的概率增量
	 * 
	 * 参数 宝箱ID 消耗品表的ID
	 */
	BOX_OPEN_PROBABILITYS,

	/**
	 * PVP 保护状态剩余次数
	 */
	PVP_PROTECT_TIMES,

	/**
	 * 连续失败次数
	 */
	PVP_LOSE_STREAK_TODAY,

	/**
	 * 十次寻仙次数
	 */
	XUN_XIAN_TEN_TIMES_TIMES,

	/**
	 * 是否领取了段位奖励
	 * 
	 * 参数: 段位ID
	 */
	PVP_HAS_RECEIVE_DAN_REWARD,

	/**
	 * 玩家当前关卡ID
	 */
	MISSION_CURRENT_ID,

	/**
	 * 闯阵次数
	 */
	CHUANG_ZHEN_TIMES,

	/**
	 * 黑市兑换次数
	 */
	HEI_SHI_EXCHANGE_TIMES, BOSS_BATTLE_TIMES,

	/**
	 * VIP 成长值
	 */
	VIP_GROWTH,

	/**
	 * 某一关 主线boss奖励掉落次数
	 * 
	 * 参数: 关卡ID
	 */

	MAIN_BOSS_PRIZE_TIMES,

	// /**
	// * 开服奖励是否领取
	// *
	// * 参数: 天
	// */
	// OPEN_SERVER_REWARD,
	// /**
	// * 开服礼包已经领取到那一天了
	// */
	// OPEN_SERVER_REWARD_PROGRESS,
	//
	// /**
	// * 今日的开服奖励是否领取了
	// */
	// OPEN_SERVER_REWARD_HAS_RECEIVE_TODAY,

	/**
	 * 某一天的开服礼包是否领取了 参数: 天
	 */
	OPEN_SERVER_REWARD_HAS_RECEIVE,

	/**
	 * 用于前端扫荡bug修复
	 */
	SAODANG_FIXUP_BUG,

	/**
	 * 引导期间，需要自动生成一个神魔
	 */
	SHENMO_HAS_FIRST_SHOW,

	/**
	 * 夺某个材料的次数 参数: 材料ID 专为夺宝必掉服务
	 */
	SNATCH_TIMES_FOR_MUST_DROP,

	/**
	 * 每日目标积分
	 */
	DAILY_TASK_SCORE,

	/**
	 * 每日宝箱奖励是否开启 参数: 宝箱ID
	 */
	DAILY_TASK_BOX_IS_OPEN,
	/**
	 * 是否第一次阵法升级
	 */
	TACTICAL_IS_FIRST_LEVELUP,
	/**
	 * 月卡结束时间的秒数
	 */
	MONTH_CARD_END_SECOND,
	/**
	 * 各档次的充值情况
	 */
	RECHARGE,

	/**
	 * 是否领取过首冲奖励
	 */
	HAS_RECEIVE_FIRST_RECHARGE_REWARD,

	/**
	 * 是否购买了成长计划
	 */
	HAS_BOUGHT_CHENG_ZHANG,

	/**
	 * 是否领取了某个成长计划的奖励 参数: id
	 */
	HAS_RECEIVE_CHENG_ZHANG_BOX,

	// /**
	// * 是否收到了好友留言
	// */
	// RECEIVE_MESSAGE,

	/**
	 * 客户端类型
	 */
	CLIENT_TYPE,

	/**
	 * 收到GM客服新消息
	 */
	RECEIVE_KE_FU_MESSAGE,

	/**
	 * PVP 初始化时间
	 */
	PVP_INIT_SEC,
	/**
	 * 保护妲己的冷却时间
	 */
	DAJI_COLD_DOWN,

	/**
	 * 某月充值元宝数 参数: 年-月 比如 2014-3 2014-12 2014-1 可使用new
	 * MonthRechargeKeyBuilder().key()进行构造
	 */
	MONTH_RECHARGE,
	/**
	 * 最后一次购买月卡的月份， 参数: 年月 比如 2014-3 就返回20143这个数字 可使用new
	 * MonthRechargeKeyBuilder().getCurrentMonthKeyNum()进行构造
	 */
	MONTH_CARD_BUY,

	/**
	 * 历史累计完成的日常任务数量
	 */
	DAILY_TASK_FINISH_COUNT_HISTORY,

	/**
	 * 金宝箱开启次数
	 */
	JIN_BAO_XIANG_OPEN_TIMES,

	/**
	 * 紫金宝箱开启次数
	 */
	ZI_JIN_BAO_XIANG_OPEN_TIMES,

	/**
	 * 助威团累计多少次没有抽到甲级品质了
	 */
	ALTERNATE_JIA,

	/**
	 * 助威团累计多少次没有抽到乙级品质了
	 */
	ALTERNATE_YI,

	// 扫荡累计多少次没有产生紫金宝箱
	MOUPPING_ZJBX,

	// 扫荡累计多少次没有出现神魔
	MOUPPING_SHENMO,

	/**
	 * 记录玩家最后一次登录是哪一天(1970 - 今天 的天数 注意是当地时间, 不是世界时间)
	 */
	LAST_LOGIN_DAY,

	/**
	 * 开服礼包, 今天登录是否开启了一个新的开服礼包
	 */
	IS_OPEN_SEV_REWARD_OPEN,

	/**
	 * 开服礼包 进度
	 */
	OPEN_SERVER_REWARD_PROGRESS,

	/**
	 * 技能没升级 累计几率 每升级失败累计一次 值为实际值 * 1000
	 */
	SKILL_LEVEL_UP_FAILD_PRO,

	/**
	 * PVP 某个物品兑换的次数
	 */
	PVP_DUIHUAN_TIMES,

	/**
	 * 某个物品最后一次兑换的秒 1970
	 */
	LAST_PVP_DUIHUAN_SEC,
	/**
	 * 此玩家今日是否领取 每日首冲奖励
	 */
	FIRST_RECHARGE_PER_DAY,

	/**
	 * 装备商人兑换次数
	 */
	ZBSR_EXCHANGE_TIMES,

	/**
	 * 支线Boss第一次掉落
	 */
	LINE_BOSS_PRIZE_TIMES,

	/**
	 * 在线奖励领取标记 参数 Online表的ID
	 */
	ONLINE_REWARD_MARK,

	/**
	 * 今天是否发放了国庆礼包
	 */
	GUO_QING,

	/**
	 * 国庆领取到了那个礼包
	 */
	GUO_QING_REWARD_ID,

	/**
	 * 邀请码 某个礼包是否领取了 参数: 礼包id
	 */
	INVITE_REWARD,

	/**
	 * 是否已经提交邀请码
	 */
	HAS_BEAN_INVITED,

	/**
	 * 是否领取了豪华大礼包
	 */

	HAS_RECEIVE_HHDLB, 
	
	/**
	 * 白水晶
	 */
	BAI_SHUI_JING, 
	/**
	 * 黄水晶
	 */
	HUANG_SHUI_JING,


	
	/**
	 * 邀请礼包ID
	 */
	INVITE_PRIZE_ID, 
	
	/**
	 * 黑市每日登陆送奖励
	 */
	HEI_SHI_MEI_RI_DENG_LU, 
	/**
	 * 上次消费元宝后，黑市活动兑换奖励之后，剩余的元宝数
	 */
	HEISHI_REDUCE_REMAIN_GOLD,
	/**
	 * 今日充值元宝数
	 */
	HEISHI_RECHARGE_REMAIN_GOLD, 
	/**
	 * 活動期間每日參與BOSS戰2次，可獲得白水晶 X 2。 
	 */
	HEISHI_BOSS, 
	/**
	 * 是否领取限时通关的奖励
	 */
	XIAN_SHI_TONG_GUAN, 
	
	/**
	 * 是否领取过“每日储值1000元宝发送的奖励”
	 */
	HEISHI_RECHARGE_GOT_PRIZE, 
	
	/**
	 * 用户GAME_ID
	 */
	GAME_ID,


	// /**
	// * 商城礼包购买次数
	// * 参数, 礼包ID
	// */
	// PRESENT_BUY_TIMES,
	;
}