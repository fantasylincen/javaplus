/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : mobile10005

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-12-17 10:20:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `avoid_fighter`
-- ----------------------------
DROP TABLE IF EXISTS `avoid_fighter`;
CREATE TABLE `avoid_fighter` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '玩家账号',
  `is_fighter` bit(1) NOT NULL COMMENT '是否免战',
  `start_time` int(11) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of avoid_fighter
-- ----------------------------

-- ----------------------------
-- Table structure for `backup_position`
-- ----------------------------
DROP TABLE IF EXISTS `backup_position`;
CREATE TABLE `backup_position` (
  `uname` varchar(32) NOT NULL,
  `position` int(11) NOT NULL COMMENT '基数从100开始',
  `templet_id` int(11) NOT NULL COMMENT '模板id',
  `hero_id` int(11) DEFAULT NULL COMMENT '英雄的类型id，没有则为-1',
  PRIMARY KEY (`uname`,`position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='替补位置';

-- ----------------------------
-- Records of backup_position
-- ----------------------------

-- ----------------------------
-- Table structure for `boss_damage_data`
-- ----------------------------
DROP TABLE IF EXISTS `boss_damage_data`;
CREATE TABLE `boss_damage_data` (
  `boss_id` int(11) NOT NULL COMMENT 'boss_id',
  `challenger_id` varchar(32) NOT NULL COMMENT '挑战者ID',
  `damage` int(11) NOT NULL COMMENT '对Boss造成的伤害值',
  `award` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否领奖',
  PRIMARY KEY (`boss_id`,`challenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boss_damage_data
-- ----------------------------

-- ----------------------------
-- Table structure for `boss_data`
-- ----------------------------
DROP TABLE IF EXISTS `boss_data`;
CREATE TABLE `boss_data` (
  `boss_id` int(11) NOT NULL COMMENT 'Boss唯一ID',
  `uname` varchar(32) NOT NULL COMMENT '发现者ID',
  `boss_templet_id` int(11) NOT NULL,
  `hp` int(11) NOT NULL COMMENT 'Boss剩余血量',
  `level` int(11) NOT NULL COMMENT 'Boss等级',
  `map_id` int(11) NOT NULL COMMENT '发现这个Boss的地图ID',
  `killer` varchar(32) DEFAULT NULL COMMENT '击杀者',
  `found_time` int(11) NOT NULL COMMENT '发现这个Boss时的时间',
  `is_shared` bit(1) NOT NULL COMMENT '是否共享过',
  PRIMARY KEY (`boss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boss_data
-- ----------------------------

-- ----------------------------
-- Table structure for `chuang_zhen`
-- ----------------------------
DROP TABLE IF EXISTS `chuang_zhen`;
CREATE TABLE `chuang_zhen` (
  `uname` varchar(32) NOT NULL,
  `star` int(11) NOT NULL DEFAULT '0' COMMENT '得星数量',
  `star_remain` int(11) NOT NULL COMMENT '剩余德行',
  `floor` int(11) NOT NULL DEFAULT '0' COMMENT '当前第几层',
  `times` int(11) NOT NULL COMMENT '当前闯了多少场',
  `attack` float(20,5) NOT NULL,
  `defend` float(20,5) NOT NULL,
  `m_attack` float(20,5) NOT NULL,
  `m_defend` float(20,5) NOT NULL,
  `hp` float(20,5) NOT NULL,
  `speed` float(20,5) NOT NULL,
  `star_max_today` int(11) NOT NULL DEFAULT '0' COMMENT '今日最大星星',
  `floor_max_today` int(11) NOT NULL DEFAULT '0' COMMENT '今日最大楼层',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chuang_zhen
-- ----------------------------

-- ----------------------------
-- Table structure for `continuous`
-- ----------------------------
DROP TABLE IF EXISTS `continuous`;
CREATE TABLE `continuous` (
  `uname` varchar(32) NOT NULL,
  `type` int(11) NOT NULL COMMENT '类型',
  `status` varchar(100) NOT NULL COMMENT '前N天的操作状态值',
  `last_update_day` int(11) NOT NULL COMMENT '最后一次更新的天数',
  PRIMARY KEY (`uname`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of continuous
-- ----------------------------

-- ----------------------------
-- Table structure for `invite_users`
-- ----------------------------
DROP TABLE IF EXISTS `invite_users`;
CREATE TABLE `invite_users` (
  `uname` varchar(32) NOT NULL DEFAULT '邀请别人的玩家',
  `friend` varchar(32) NOT NULL DEFAULT '被邀请的玩家',
  PRIMARY KEY (`uname`,`friend`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invite_users
-- ----------------------------

-- ----------------------------
-- Table structure for `level_up_reward`
-- ----------------------------
DROP TABLE IF EXISTS `level_up_reward`;
CREATE TABLE `level_up_reward` (
  `uname` varchar(32) NOT NULL,
  `level` int(11) NOT NULL COMMENT '领取过的奖励等级',
  PRIMARY KEY (`uname`,`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of level_up_reward
-- ----------------------------

-- ----------------------------
-- Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL,
  `create_time` date NOT NULL COMMENT '日志生成时间',
  `type` int(11) NOT NULL COMMENT '日志类别',
  `log` varchar(1024) NOT NULL COMMENT '日志记录',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for `log_boss_award`
-- ----------------------------
DROP TABLE IF EXISTS `log_boss_award`;
CREATE TABLE `log_boss_award` (
  `log_id` int(11) NOT NULL,
  `uname` varchar(32) NOT NULL COMMENT '玩家ID',
  `nick` varchar(32) NOT NULL COMMENT '昵称',
  `prop_id` int(11) NOT NULL COMMENT '掉落道具ID',
  `count` int(11) NOT NULL COMMENT '掉落数量',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_boss_award
-- ----------------------------

-- ----------------------------
-- Table structure for `log_buy`
-- ----------------------------
DROP TABLE IF EXISTS `log_buy`;
CREATE TABLE `log_buy` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL COMMENT '用户帐号',
  `nick` varchar(32) NOT NULL COMMENT '昵称',
  `prop_id` int(11) NOT NULL COMMENT '购买的物品ID',
  `time` int(11) NOT NULL DEFAULT '0' COMMENT '秒  1970-今',
  `count` int(11) NOT NULL COMMENT '购买数量',
  `comment` varchar(200) NOT NULL COMMENT '说明',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50002406 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_buy
-- ----------------------------

-- ----------------------------
-- Table structure for `log_consume`
-- ----------------------------
DROP TABLE IF EXISTS `log_consume`;
CREATE TABLE `log_consume` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL COMMENT '用户帐号',
  `nick` varchar(32) NOT NULL COMMENT '昵称',
  `time` int(11) NOT NULL DEFAULT '0' COMMENT '秒  1970-今',
  `cash_count` int(11) NOT NULL COMMENT '消费金币数量',
  `jin_bei_ke_count` int(11) NOT NULL COMMENT '消费金贝壳数量',
  `gold_count` int(11) NOT NULL COMMENT '消费元宝数量',
  `comment` varchar(200) NOT NULL COMMENT '说明',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50280418 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_consume
-- ----------------------------

-- ----------------------------
-- Table structure for `log_find_fighter`
-- ----------------------------
DROP TABLE IF EXISTS `log_find_fighter`;
CREATE TABLE `log_find_fighter` (
  `log_id` int(11) NOT NULL,
  `uname` varchar(32) NOT NULL COMMENT '玩家帐号',
  `nick` varchar(32) NOT NULL COMMENT '玩家昵称',
  `fighter_id` int(11) NOT NULL COMMENT '抽到的战士ID',
  `time` int(11) NOT NULL COMMENT '抽到神将的时间 秒  1970-今',
  `type` int(11) NOT NULL COMMENT '伙伴类型，1为成品，2为魂魄',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_find_fighter
-- ----------------------------

-- ----------------------------
-- Table structure for `log_login`
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL COMMENT '用户ID',
  `nick` varchar(32) NOT NULL COMMENT '用户昵称',
  `login_time` int(11) NOT NULL COMMENT '登录时间 秒  1970-今',
  `type` int(11) NOT NULL COMMENT '登录类型，1为登录，0为登出',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50020965 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_login
-- ----------------------------

-- ----------------------------
-- Table structure for `log_snatch`
-- ----------------------------
DROP TABLE IF EXISTS `log_snatch`;
CREATE TABLE `log_snatch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号',
  `dataType` int(11) NOT NULL COMMENT '材料类型',
  `nub` int(11) NOT NULL COMMENT '数量',
  `robber` varchar(32) NOT NULL DEFAULT '' COMMENT '被那个玩家夺宝',
  `time` int(11) NOT NULL COMMENT '抢夺时间 秒  1970-今',
  `warSituationId` int(11) NOT NULL COMMENT '战况信息ID',
  `isWin` bit(1) NOT NULL COMMENT '是否胜利了',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of log_snatch
-- ----------------------------

-- ----------------------------
-- Table structure for `mission_challenge`
-- ----------------------------
DROP TABLE IF EXISTS `mission_challenge`;
CREATE TABLE `mission_challenge` (
  `uname` varchar(255) NOT NULL,
  `mission_id` int(11) NOT NULL,
  `branch_is_cross` bit(1) DEFAULT b'0' COMMENT '是否通过支线',
  `main_is_cross` bit(1) DEFAULT b'1' COMMENT '是否通过主线',
  PRIMARY KEY (`uname`,`mission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mission_challenge
-- ----------------------------

-- ----------------------------
-- Table structure for `mission_map`
-- ----------------------------
DROP TABLE IF EXISTS `mission_map`;
CREATE TABLE `mission_map` (
  `ids` varchar(32) NOT NULL COMMENT '由uname和mission_id构成 例如lk100_ro|1',
  `templet_id` int(11) NOT NULL,
  `is_finish` bit(1) NOT NULL COMMENT '是否完成',
  `args` varchar(100) DEFAULT NULL COMMENT '关卡参数',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1、空节点，2、战斗事件,3、开宝箱，4、随机事件',
  `paths` int(11) DEFAULT NULL,
  `indexs` int(11) DEFAULT NULL,
  PRIMARY KEY (`ids`,`templet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mission_map
-- ----------------------------

-- ----------------------------
-- Table structure for `mission_star`
-- ----------------------------
DROP TABLE IF EXISTS `mission_star`;
CREATE TABLE `mission_star` (
  `uname` varchar(32) NOT NULL,
  `mission_id` int(11) NOT NULL,
  `demon_star_max` int(11) NOT NULL DEFAULT '0' COMMENT '小怪获得最大星星数量',
  `demon_star` int(11) NOT NULL DEFAULT '0' COMMENT '小怪获得星星数量',
  `main_boss_star_max` int(11) NOT NULL COMMENT '主线boss最大星星数量',
  `main_boss_star` int(11) NOT NULL COMMENT '主线boss星星数量',
  `branch_boss_star_1` int(11) NOT NULL,
  `branch_boss_star_2` int(11) NOT NULL,
  `branch_boss_star_3` int(11) NOT NULL,
  `branch_boss_star_4` int(11) NOT NULL,
  `branch_boss_star_5` int(11) NOT NULL,
  `branch_boss_star_6` int(11) NOT NULL,
  `branch_boss_star_max_1` int(11) NOT NULL,
  `branch_boss_star_max_2` int(11) NOT NULL,
  `branch_boss_star_max_3` int(11) NOT NULL,
  `branch_boss_star_max_4` int(11) NOT NULL,
  `branch_boss_star_max_5` int(11) NOT NULL,
  `branch_boss_star_max_6` int(11) NOT NULL,
  PRIMARY KEY (`uname`,`mission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mission_star
-- ----------------------------

-- ----------------------------
-- Table structure for `new_camp`
-- ----------------------------
DROP TABLE IF EXISTS `new_camp`;
CREATE TABLE `new_camp` (
  `uname` varchar(32) NOT NULL COMMENT '玩家id',
  `currentformationid` int(11) DEFAULT NULL,
  `positionStr` varchar(500) DEFAULT NULL COMMENT '用逗号分隔的阵型字符串，规则：英雄id,位置',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_camp
-- ----------------------------

-- ----------------------------
-- Table structure for `new_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `new_equipment`;
CREATE TABLE `new_equipment` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '所属用户名',
  `equipment_id` int(11) NOT NULL DEFAULT '0' COMMENT '玩家个人的装备ID',
  `templet_id` int(11) NOT NULL DEFAULT '0' COMMENT '类型Id',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '等级',
  `fighter_type_id` int(11) NOT NULL COMMENT '在哪个战士身上',
  `price` int(11) NOT NULL COMMENT '强化所消耗的总金币',
  PRIMARY KEY (`uname`,`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??';

-- ----------------------------
-- Records of new_equipment
-- ----------------------------

-- ----------------------------
-- Table structure for `new_fighter`
-- ----------------------------
DROP TABLE IF EXISTS `new_fighter`;
CREATE TABLE `new_fighter` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '所属玩家',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '0',
  `exp` int(11) NOT NULL DEFAULT '0',
  `v1` int(11) NOT NULL DEFAULT '0',
  `v2` int(11) NOT NULL DEFAULT '0',
  `v3` int(11) NOT NULL DEFAULT '0',
  `v4` int(11) NOT NULL DEFAULT '0',
  `v5` int(11) NOT NULL DEFAULT '0',
  `v6` int(11) NOT NULL DEFAULT '0',
  `v7` int(11) NOT NULL DEFAULT '0',
  `v8` int(11) NOT NULL DEFAULT '0',
  `v9` int(11) NOT NULL DEFAULT '0',
  `v10` int(11) NOT NULL,
  `v11` int(11) NOT NULL,
  `v12` int(11) NOT NULL,
  `v13` int(11) NOT NULL DEFAULT '0',
  `v14` int(11) NOT NULL DEFAULT '0',
  `v15` int(11) NOT NULL DEFAULT '0',
  `v16` int(11) NOT NULL DEFAULT '0',
  `v17` int(11) NOT NULL DEFAULT '0',
  `v18` int(11) NOT NULL DEFAULT '0',
  `v19` int(11) NOT NULL DEFAULT '0',
  `v20` int(11) NOT NULL DEFAULT '0',
  `yuanshen_type1` int(11) NOT NULL,
  `yuanshen_type2` int(11) NOT NULL,
  `yuanshen_type3` int(11) NOT NULL,
  `yuanshen_type4` int(11) NOT NULL,
  `yuanshen_type5` int(11) NOT NULL,
  `yuanshen_type6` int(11) NOT NULL,
  `yuanshen_type7` int(11) NOT NULL,
  `yuanshen_type8` int(11) NOT NULL,
  `yuanshen_type9` int(11) NOT NULL,
  `yuanshen_type10` int(11) NOT NULL,
  `yuanshen_level1` int(11) NOT NULL,
  `yuanshen_level2` int(11) NOT NULL,
  `yuanshen_level3` int(11) NOT NULL,
  `yuanshen_level4` int(11) NOT NULL,
  `yuanshen_level5` int(11) NOT NULL,
  `yuanshen_level6` int(11) NOT NULL,
  `yuanshen_level7` int(11) NOT NULL,
  `yuanshen_level8` int(11) NOT NULL,
  `yuanshen_level9` int(11) NOT NULL,
  `yuanshen_level10` int(11) NOT NULL,
  `yuanshen_exp1` int(11) NOT NULL,
  `yuanshen_exp2` int(11) NOT NULL,
  `yuanshen_exp3` int(11) NOT NULL,
  `yuanshen_exp4` int(11) NOT NULL,
  `yuanshen_exp5` int(11) NOT NULL,
  `yuanshen_exp6` int(11) NOT NULL,
  `yuanshen_exp7` int(11) NOT NULL,
  `yuanshen_exp8` int(11) NOT NULL,
  `yuanshen_exp9` int(11) NOT NULL,
  `yuanshen_exp10` int(11) NOT NULL,
  PRIMARY KEY (`uname`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??';

-- ----------------------------
-- Records of new_fighter
-- ----------------------------

-- ----------------------------
-- Table structure for `presents`
-- ----------------------------
DROP TABLE IF EXISTS `presents`;
CREATE TABLE `presents` (
  `uname` varchar(32) NOT NULL,
  `v1` int(11) NOT NULL,
  `v2` int(11) NOT NULL,
  `v3` int(11) NOT NULL,
  `v4` int(11) NOT NULL,
  `v5` int(11) NOT NULL,
  `v6` int(11) NOT NULL,
  `v7` int(11) NOT NULL,
  `v8` int(11) NOT NULL,
  `v9` int(11) NOT NULL,
  `v10` int(11) NOT NULL,
  `v11` int(11) NOT NULL,
  `v12` int(11) NOT NULL,
  `v13` int(11) NOT NULL,
  `v14` int(11) NOT NULL,
  `v15` int(11) NOT NULL,
  `v16` int(11) NOT NULL,
  `v17` int(11) NOT NULL,
  `v18` int(11) NOT NULL,
  `v19` int(11) NOT NULL,
  `v20` int(11) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of presents
-- ----------------------------

-- ----------------------------
-- Table structure for `pvp`
-- ----------------------------
DROP TABLE IF EXISTS `pvp`;
CREATE TABLE `pvp` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号',
  `dan_id` int(11) NOT NULL COMMENT '等级',
  `practice` int(11) NOT NULL DEFAULT '0' COMMENT '修行',
  `shen_jia` int(11) NOT NULL DEFAULT '0' COMMENT '身价缓存',
  PRIMARY KEY (`uname`),
  KEY `dan_id` (`dan_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pvp
-- ----------------------------

-- ----------------------------
-- Table structure for `pvp_extra`
-- ----------------------------
DROP TABLE IF EXISTS `pvp_extra`;
CREATE TABLE `pvp_extra` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '用户账号',
  `status` varchar(32) NOT NULL DEFAULT '' COMMENT '战斗状态',
  `pvp_up_time` date NOT NULL DEFAULT '2011-11-11',
  `v1` int(11) NOT NULL DEFAULT '0',
  `v2` int(11) NOT NULL DEFAULT '0',
  `v3` int(11) NOT NULL DEFAULT '0',
  `v4` int(11) NOT NULL DEFAULT '0',
  `v5` int(11) NOT NULL DEFAULT '0',
  `v6` int(11) NOT NULL,
  `v7` int(11) NOT NULL,
  `v8` int(11) NOT NULL DEFAULT '0',
  `v9` int(11) NOT NULL DEFAULT '0',
  `v10` int(11) NOT NULL DEFAULT '0',
  `v11` int(11) NOT NULL DEFAULT '0',
  `v12` int(11) NOT NULL DEFAULT '0',
  `v13` int(11) NOT NULL DEFAULT '0',
  `v14` int(11) NOT NULL DEFAULT '0',
  `v15` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uname`),
  KEY `practice` (`v4`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pvp_extra
-- ----------------------------

-- ----------------------------
-- Table structure for `pvp_rank_reward`
-- ----------------------------
DROP TABLE IF EXISTS `pvp_rank_reward`;
CREATE TABLE `pvp_rank_reward` (
  `uname` varchar(32) NOT NULL,
  `rank` int(11) NOT NULL,
  `has_receive` bit(1) NOT NULL COMMENT '是否领取了奖励',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pvp_rank_reward
-- ----------------------------

-- ----------------------------
-- Table structure for `pvp_war_situation`
-- ----------------------------
DROP TABLE IF EXISTS `pvp_war_situation`;
CREATE TABLE `pvp_war_situation` (
  `situation_id` int(11) NOT NULL AUTO_INCREMENT,
  `challenger_id` varchar(32) NOT NULL COMMENT '攻击方ID',
  `defender_id` varchar(32) NOT NULL COMMENT '防御方ID',
  `create_time` int(11) NOT NULL DEFAULT '0',
  `data` varbinary(20480) NOT NULL COMMENT '战况数据',
  `is_win` bit(1) NOT NULL COMMENT '是否胜利',
  PRIMARY KEY (`situation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pvp_war_situation
-- ----------------------------

-- ----------------------------
-- Table structure for `recharge_record`
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `ids` bigint(20) NOT NULL COMMENT '充值流水号',
  `uname` varchar(32) NOT NULL,
  `amount` int(11) NOT NULL COMMENT '充值的人民币数目',
  `createTime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------

-- ----------------------------
-- Table structure for `skills`
-- ----------------------------
DROP TABLE IF EXISTS `skills`;
CREATE TABLE `skills` (
  `uname` varchar(32) NOT NULL,
  `ids` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `fighter_type_id` int(11) NOT NULL COMMENT '装备在哪个神将身上的',
  PRIMARY KEY (`uname`,`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skills
-- ----------------------------

-- ----------------------------
-- Table structure for `spirite`
-- ----------------------------
DROP TABLE IF EXISTS `spirite`;
CREATE TABLE `spirite` (
  `uname` varchar(32) NOT NULL,
  `type_id` int(11) NOT NULL COMMENT '魂魄类型ID',
  `step` int(11) NOT NULL COMMENT '品质',
  `count` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`uname`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='魂魄';

-- ----------------------------
-- Records of spirite
-- ----------------------------

-- ----------------------------
-- Table structure for `tactical`
-- ----------------------------
DROP TABLE IF EXISTS `tactical`;
CREATE TABLE `tactical` (
  `uname` varchar(32) NOT NULL,
  `ids` int(11) NOT NULL,
  `templet_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `isCurrent` bit(1) DEFAULT b'0' COMMENT '是否当前阵法',
  `accumulate` float(200,0) NOT NULL COMMENT '积累的人品值',
  PRIMARY KEY (`uname`,`ids`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tactical
-- ----------------------------

-- ----------------------------
-- Table structure for `ums_onlineno`
-- ----------------------------
DROP TABLE IF EXISTS `ums_onlineno`;
CREATE TABLE `ums_onlineno` (
  `server_id` int(11) NOT NULL,
  `online_number` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ums_onlineno
-- ----------------------------

-- ----------------------------
-- Table structure for `user_bag`
-- ----------------------------
DROP TABLE IF EXISTS `user_bag`;
CREATE TABLE `user_bag` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '背包用户名',
  `capacity` int(11) NOT NULL COMMENT '背包容量',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_bag
-- ----------------------------

-- ----------------------------
-- Table structure for `user_base`
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `uname` varchar(32) NOT NULL,
  `nick` varchar(32) NOT NULL COMMENT '昵称',
  `last_login_time` int(11) NOT NULL COMMENT '最后一次登陆时间  秒',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base
-- ----------------------------

-- ----------------------------
-- Table structure for `user_data`
-- ----------------------------
DROP TABLE IF EXISTS `user_data`;
CREATE TABLE `user_data` (
  `uname` varchar(32) NOT NULL COMMENT '用户ID',
  `nick` varchar(32) NOT NULL COMMENT '昵称',
  `coupon` int(11) NOT NULL COMMENT '点券:系统赠送的货币, 可以当元宝使用',
  `gold` int(11) NOT NULL COMMENT '充值获得的元宝',
  `invitation_code` varchar(10) NOT NULL COMMENT '邀请码',
  `v1` int(11) NOT NULL COMMENT '活跃度',
  `v2` int(11) NOT NULL COMMENT '金币',
  `v3` int(11) NOT NULL COMMENT '元宝',
  `v4` int(11) NOT NULL COMMENT '服装类型',
  `v5` int(11) NOT NULL COMMENT '体力',
  `v6` int(11) NOT NULL COMMENT '修行',
  `v7` int(11) NOT NULL COMMENT '声望',
  `v8` int(11) NOT NULL COMMENT '精力',
  `v9` int(11) NOT NULL COMMENT '积分',
  `v10` int(11) NOT NULL COMMENT '地仙令',
  `v11` int(11) NOT NULL COMMENT '天仙令',
  `v12` int(11) NOT NULL COMMENT '金仙令',
  `v13` int(11) NOT NULL COMMENT '系统赠送元宝',
  `v14` int(11) NOT NULL COMMENT '活动币1',
  `v15` int(11) NOT NULL COMMENT '活动币',
  `v16` int(11) NOT NULL COMMENT '活动币',
  `v17` int(11) NOT NULL COMMENT '活动币',
  `v18` int(11) NOT NULL COMMENT '活动币',
  `v19` int(11) NOT NULL,
  `v20` int(11) NOT NULL,
  `v21` int(11) NOT NULL,
  `v22` int(11) NOT NULL,
  `v23` int(11) NOT NULL,
  `v24` int(11) NOT NULL,
  `v25` int(11) NOT NULL,
  `v26` int(11) NOT NULL,
  `v27` int(11) NOT NULL,
  `v28` int(11) NOT NULL,
  `v29` int(11) NOT NULL,
  `v30` int(11) NOT NULL,
  `v31` int(11) NOT NULL,
  `v32` int(11) NOT NULL,
  `v33` int(11) NOT NULL,
  `v34` int(11) NOT NULL,
  `v35` int(11) NOT NULL,
  `v36` int(11) NOT NULL,
  `v37` int(11) NOT NULL,
  `v38` int(11) NOT NULL,
  `v39` int(11) NOT NULL,
  `v40` int(11) NOT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_data
-- ----------------------------

-- ----------------------------
-- Table structure for `user_dogz`
-- ----------------------------
DROP TABLE IF EXISTS `user_dogz`;
CREATE TABLE `user_dogz` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `dogz_id` int(11) NOT NULL COMMENT '神兽Id',
  `is_battlefield` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否在战场',
  `level` int(11) NOT NULL COMMENT '等级',
  `growth` int(11) NOT NULL COMMENT '成长值',
  `step` int(11) NOT NULL COMMENT '品阶',
  `is_protected` bit(1) NOT NULL COMMENT '是否受保护',
  `trait` int(11) NOT NULL COMMENT '品质',
  `dunwu_count` int(11) NOT NULL COMMENT '顿悟次数',
  PRIMARY KEY (`uname`,`dogz_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_dogz
-- ----------------------------

-- ----------------------------
-- Table structure for `user_grid`
-- ----------------------------
DROP TABLE IF EXISTS `user_grid`;
CREATE TABLE `user_grid` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '背包用户名',
  `grid_id` int(11) NOT NULL,
  `typeId` int(11) NOT NULL COMMENT '道具类型ID',
  `count` int(11) NOT NULL COMMENT '道具数量',
  PRIMARY KEY (`uname`,`grid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_grid
-- ----------------------------

-- ----------------------------
-- Table structure for `user_market`
-- ----------------------------
DROP TABLE IF EXISTS `user_market`;
CREATE TABLE `user_market` (
  `trad_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '玩家账号',
  `fighter_id` int(11) NOT NULL COMMENT '交易物品的id',
  `type_id` int(11) NOT NULL COMMENT '神将ID',
  `trad_nub` int(11) NOT NULL COMMENT '交易数量',
  `trad_type` int(11) NOT NULL COMMENT '交换物品的类型ID',
  `time` int(11) NOT NULL COMMENT '放入市场的时间',
  PRIMARY KEY (`trad_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_market
-- ----------------------------

-- ----------------------------
-- Table structure for `user_mission`
-- ----------------------------
DROP TABLE IF EXISTS `user_mission`;
CREATE TABLE `user_mission` (
  `uname` varchar(32) NOT NULL COMMENT '用户ID',
  `mission_id` int(11) NOT NULL COMMENT '当前通过了的关卡ID',
  `die_count` int(11) NOT NULL DEFAULT '0' COMMENT '死亡次数',
  `node_index` int(11) NOT NULL,
  `create_date` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间，用于排序',
  PRIMARY KEY (`uname`,`mission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_mission
-- ----------------------------

-- ----------------------------
-- Table structure for `user_pieces_bag`
-- ----------------------------
DROP TABLE IF EXISTS `user_pieces_bag`;
CREATE TABLE `user_pieces_bag` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '背包用户名',
  `capacity` int(11) NOT NULL COMMENT '背包容量',
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_pieces_bag
-- ----------------------------

-- ----------------------------
-- Table structure for `user_pieces_grid`
-- ----------------------------
DROP TABLE IF EXISTS `user_pieces_grid`;
CREATE TABLE `user_pieces_grid` (
  `uname` varchar(32) NOT NULL DEFAULT '' COMMENT '背包用户名',
  `grid_id` int(11) NOT NULL,
  `typeId` int(11) NOT NULL COMMENT '道具类型ID',
  `count` int(11) NOT NULL COMMENT '道具数量',
  PRIMARY KEY (`uname`,`grid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_pieces_grid
-- ----------------------------

-- ----------------------------
-- Table structure for `war_situation`
-- ----------------------------
DROP TABLE IF EXISTS `war_situation`;
CREATE TABLE `war_situation` (
  `situation_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` int(11) NOT NULL DEFAULT '0',
  `data` varbinary(20480) NOT NULL COMMENT '战况数据',
  PRIMARY KEY (`situation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50030494 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of war_situation
-- ----------------------------

-- ----------------------------
-- View structure for `chuang_zhen_ranking_list`
-- ----------------------------
DROP VIEW IF EXISTS `chuang_zhen_ranking_list`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `chuang_zhen_ranking_list` AS select `chuang_zhen`.`uname` AS `uname`,`chuang_zhen`.`star` AS `star`,`chuang_zhen`.`star_remain` AS `star_remain`,`chuang_zhen`.`floor` AS `floor`,`chuang_zhen`.`times` AS `times`,`chuang_zhen`.`attack` AS `attack`,`chuang_zhen`.`defend` AS `defend`,`chuang_zhen`.`m_attack` AS `m_attack`,`chuang_zhen`.`m_defend` AS `m_defend`,`chuang_zhen`.`hp` AS `hp`,`chuang_zhen`.`speed` AS `speed`,`chuang_zhen`.`star_max_today` AS `star_max_today`,`chuang_zhen`.`floor_max_today` AS `floor_max_today` from `chuang_zhen` order by `chuang_zhen`.`floor_max_today` desc,`chuang_zhen`.`star_max_today` desc limit 0,150 ;
