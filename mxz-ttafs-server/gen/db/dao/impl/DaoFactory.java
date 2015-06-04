package db.dao.impl;import java.util.concurrent.atomic.AtomicLong;import com.lemon.commons.database.ConnectionFetcher;import db.GameDB;import dbutils.IdUtil;public class DaoFactory {
	public static AvoidFighterDao2 getAvoidFighterDao() {
		return new AvoidFighterDao2(fetcher);
	}

	//public static AvoidFighterDao3 getCacheAvoidFighterDao() {
	//	return new AvoidFighterDao3(fetcher);
	//}

	public static BackupPositionDao2 getBackupPositionDao() {
		return new BackupPositionDao2(fetcher);
	}

	//public static BackupPositionDao3 getCacheBackupPositionDao() {
	//	return new BackupPositionDao3(fetcher);
	//}

	public static BossDamageDataDao2 getBossDamageDataDao() {
		return new BossDamageDataDao2(fetcher);
	}

	//public static BossDamageDataDao3 getCacheBossDamageDataDao() {
	//	return new BossDamageDataDao3(fetcher);
	//}

	public static BossDataDao2 getBossDataDao() {
		return new BossDataDao2(fetcher);
	}

	//public static BossDataDao3 getCacheBossDataDao() {
	//	return new BossDataDao3(fetcher);
	//}

	public static ChuangZhenDao2 getChuangZhenDao() {
		return new ChuangZhenDao2(fetcher);
	}

	//public static ChuangZhenDao3 getCacheChuangZhenDao() {
	//	return new ChuangZhenDao3(fetcher);
	//}

	public static ContinuousDao2 getContinuousDao() {
		return new ContinuousDao2(fetcher);
	}

	//public static ContinuousDao3 getCacheContinuousDao() {
	//	return new ContinuousDao3(fetcher);
	//}

	public static InviteUsersDao2 getInviteUsersDao() {
		return new InviteUsersDao2(fetcher);
	}

	//public static InviteUsersDao3 getCacheInviteUsersDao() {
	//	return new InviteUsersDao3(fetcher);
	//}

	public static LevelUpRewardDao2 getLevelUpRewardDao() {
		return new LevelUpRewardDao2(fetcher);
	}

	//public static LevelUpRewardDao3 getCacheLevelUpRewardDao() {
	//	return new LevelUpRewardDao3(fetcher);
	//}

	public static LogBossAwardDao2 getLogBossAwardDao() {
		return new LogBossAwardDao2(fetcher);
	}

	//public static LogBossAwardDao3 getCacheLogBossAwardDao() {
	//	return new LogBossAwardDao3(fetcher);
	//}

	public static LogBuyDao2 getLogBuyDao() {
		return new LogBuyDao2(fetcher);
	}

	//public static LogBuyDao3 getCacheLogBuyDao() {
	//	return new LogBuyDao3(fetcher);
	//}

	public static LogConsumeDao2 getLogConsumeDao() {
		return new LogConsumeDao2(fetcher);
	}

	//public static LogConsumeDao3 getCacheLogConsumeDao() {
	//	return new LogConsumeDao3(fetcher);
	//}

	public static LogFindFighterDao2 getLogFindFighterDao() {
		return new LogFindFighterDao2(fetcher);
	}

	//public static LogFindFighterDao3 getCacheLogFindFighterDao() {
	//	return new LogFindFighterDao3(fetcher);
	//}

	public static LogLoginDao2 getLogLoginDao() {
		return new LogLoginDao2(fetcher);
	}


	//public static LogSnatchDao3 getCacheLogSnatchDao() {
	//	return new LogSnatchDao3(fetcher);
	//}

	public static LogsDao2 getLogsDao() {
		return new LogsDao2(fetcher);
	}

	//public static LogsDao3 getCacheLogsDao() {
	//	return new LogsDao3(fetcher);
	//}

	public static MissionChallengeDao2 getMissionChallengeDao() {
		return new MissionChallengeDao2(fetcher);
	}

	//public static MissionChallengeDao3 getCacheMissionChallengeDao() {
	//	return new MissionChallengeDao3(fetcher);
	//}

	public static MissionMapDao2 getMissionMapDao() {
		return new MissionMapDao2(fetcher);
	}

	//public static MissionMapDao3 getCacheMissionMapDao() {
	//	return new MissionMapDao3(fetcher);
	//}

	public static MissionStarDao2 getMissionStarDao() {
		return new MissionStarDao2(fetcher);
	}

	//public static MissionStarDao3 getCacheMissionStarDao() {
	//	return new MissionStarDao3(fetcher);
	//}

	public static NewCampDao2 getNewCampDao() {
		return new NewCampDao2(fetcher);
	}

	//public static NewCampDao3 getCacheNewCampDao() {
	//	return new NewCampDao3(fetcher);
	//}

	public static NewEquipmentDao2 getNewEquipmentDao() {
		return new NewEquipmentDao2(fetcher);
	}

	//public static NewEquipmentDao3 getCacheNewEquipmentDao() {
	//	return new NewEquipmentDao3(fetcher);
	//}

	public static NewFighterDao2 getNewFighterDao() {
		return new NewFighterDao2(fetcher);
	}

	//public static NewFighterDao3 getCacheNewFighterDao() {
	//	return new NewFighterDao3(fetcher);
	//}

	public static PresentsDao2 getPresentsDao() {
		return new PresentsDao2(fetcher);
	}

	//public static PresentsDao3 getCachePresentsDao() {
	//	return new PresentsDao3(fetcher);
	//}

	public static PvpDao2 getPvpDao() {
		return new PvpDao2(fetcher);
	}

	//public static PvpDao3 getCachePvpDao() {
	//	return new PvpDao3(fetcher);
	//}

	public static PvpExtraDao2 getPvpExtraDao() {
		return new PvpExtraDao2(fetcher);
	}

	//public static PvpExtraDao3 getCachePvpExtraDao() {
	//	return new PvpExtraDao3(fetcher);
	//}

	public static PvpRankRewardDao2 getPvpRankRewardDao() {
		return new PvpRankRewardDao2(fetcher);
	}

	//public static PvpWarSituationDao3 getCachePvpWarSituationDao() {
	//	return new PvpWarSituationDao3(fetcher);
	//}

	public static RechargeRecordDao2 getRechargeRecordDao() {
		return new RechargeRecordDao2(fetcher);
	}

	//public static RechargeRecordDao3 getCacheRechargeRecordDao() {
	//	return new RechargeRecordDao3(fetcher);
	//}

	public static SkillsDao2 getSkillsDao() {
		return new SkillsDao2(fetcher);
	}

	//public static SkillsDao3 getCacheSkillsDao() {
	//	return new SkillsDao3(fetcher);
	//}

	public static SpiriteDao2 getSpiriteDao() {
		return new SpiriteDao2(fetcher);
	}

	//public static SpiriteDao3 getCacheSpiriteDao() {
	//	return new SpiriteDao3(fetcher);
	//}

	public static TacticalDao2 getTacticalDao() {
		return new TacticalDao2(fetcher);
	}

	//public static TacticalDao3 getCacheTacticalDao() {
	//	return new TacticalDao3(fetcher);
	//}

	public static UmsOnlinenoDao2 getUmsOnlinenoDao() {
		return new UmsOnlinenoDao2(fetcher);
	}

	//public static UmsOnlinenoDao3 getCacheUmsOnlinenoDao() {
	//	return new UmsOnlinenoDao3(fetcher);
	//}

	public static UserBagDao2 getUserBagDao() {
		return new UserBagDao2(fetcher);
	}

	//public static UserBagDao3 getCacheUserBagDao() {
	//	return new UserBagDao3(fetcher);
	//}

	public static UserBaseDao2 getUserBaseDao() {
		return new UserBaseDao2(fetcher);
	}

	//public static UserBaseDao3 getCacheUserBaseDao() {
	//	return new UserBaseDao3(fetcher);
	//}

	public static UserDataDao2 getUserDataDao() {
		return new UserDataDao2(fetcher);
	}

	//public static UserDataDao3 getCacheUserDataDao() {
	//	return new UserDataDao3(fetcher);
	//}

	public static UserDogzDao2 getUserDogzDao() {
		return new UserDogzDao2(fetcher);
	}

	//public static UserDogzDao3 getCacheUserDogzDao() {
	//	return new UserDogzDao3(fetcher);
	//}

	public static UserGridDao2 getUserGridDao() {
		return new UserGridDao2(fetcher);
	}

	//public static UserGridDao3 getCacheUserGridDao() {
	//	return new UserGridDao3(fetcher);
	//}

	public static UserMarketDao2 getUserMarketDao() {
		return new UserMarketDao2(fetcher);
	}

	//public static UserMarketDao3 getCacheUserMarketDao() {
	//	return new UserMarketDao3(fetcher);
	//}

	public static UserMissionDao2 getUserMissionDao() {
		return new UserMissionDao2(fetcher);
	}

	//public static UserMissionDao3 getCacheUserMissionDao() {
	//	return new UserMissionDao3(fetcher);
	//}

	public static UserPiecesBagDao2 getUserPiecesBagDao() {
		return new UserPiecesBagDao2(fetcher);
	}

	//public static UserPiecesBagDao3 getCacheUserPiecesBagDao() {
	//	return new UserPiecesBagDao3(fetcher);
	//}

	public static UserPiecesGridDao2 getUserPiecesGridDao() {
		return new UserPiecesGridDao2(fetcher);
	}

	//public static UserPiecesGridDao3 getCacheUserPiecesGridDao() {
	//	return new UserPiecesGridDao3(fetcher);
	//}

	public static WarSituationDao2 getWarSituationDao() {
		return new WarSituationDao2(fetcher);
	}

	//public static WarSituationDao3 getCacheWarSituationDao() {
	//	return new WarSituationDao3(fetcher);
	//}
	public static ChuangZhenRankingListDao getChuangZhenRankingListDao() {
		return new ChuangZhenRankingListDao(GameDB.getInstance());
}
	public static int nextBossDataBossId() {
		if(bossDataBossId == null) {
			String sql = "select max(boss_id) as ids from boss_data";
			bossDataBossId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)bossDataBossId.addAndGet(1);
	}
	
	private static AtomicLong bossDataBossId;
	
	public static int nextLogBossAwardLogId() {
		if(logBossAwardLogId == null) {
			String sql = "select max(log_id) as ids from log_boss_award";
			logBossAwardLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logBossAwardLogId.addAndGet(1);
	}
	
	private static AtomicLong logBossAwardLogId;
	
	public static int nextLogBuyLogId() {
		if(logBuyLogId == null) {
			String sql = "select max(log_id) as ids from log_buy";
			logBuyLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logBuyLogId.addAndGet(1);
	}
	
	private static AtomicLong logBuyLogId;
	
	public static int nextLogConsumeLogId() {
		if(logConsumeLogId == null) {
			String sql = "select max(log_id) as ids from log_consume";
			logConsumeLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logConsumeLogId.addAndGet(1);
	}
	
	private static AtomicLong logConsumeLogId;
	
	public static int nextLogFindFighterLogId() {
		if(logFindFighterLogId == null) {
			String sql = "select max(log_id) as ids from log_find_fighter";
			logFindFighterLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logFindFighterLogId.addAndGet(1);
	}
	
	private static AtomicLong logFindFighterLogId;
	
	public static int nextLogLoginLogId() {
		if(logLoginLogId == null) {
			String sql = "select max(log_id) as ids from log_login";
			logLoginLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logLoginLogId.addAndGet(1);
	}
	
	private static AtomicLong logLoginLogId;
	
//	public static int nextLogSnatchId() {
//		if(logSnatchId == null) {
//			String sql = "select max(id) as ids from log_snatch";
//			logSnatchId = IdUtil.getInstance().initIdStart(fetcher, sql);
//		}
//		return (int)logSnatchId.addAndGet(1);
//	}
	
	private static AtomicLong logSnatchId;
	
	public static int nextLogsLogId() {
		if(logsLogId == null) {
			String sql = "select max(log_id) as ids from logs";
			logsLogId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)logsLogId.addAndGet(1);
	}
	
	private static AtomicLong logsLogId;
	
	public static int nextPvpWarSituationSituationId() {
		if(pvpWarSituationSituationId == null) {
			String sql = "select max(situation_id) as ids from pvp_war_situation";
			pvpWarSituationSituationId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)pvpWarSituationSituationId.addAndGet(1);
	}
	
	private static AtomicLong pvpWarSituationSituationId;
	
	public static int nextUmsOnlinenoServerId() {
		if(umsOnlinenoServerId == null) {
			String sql = "select max(server_id) as ids from ums_onlineno";
			umsOnlinenoServerId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)umsOnlinenoServerId.addAndGet(1);
	}
	
	private static AtomicLong umsOnlinenoServerId;
	
	public static int nextUserMarketTradId() {
		if(userMarketTradId == null) {
			String sql = "select max(trad_id) as ids from user_market";
			userMarketTradId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)userMarketTradId.addAndGet(1);
	}
	
	private static AtomicLong userMarketTradId;
	
	public static int nextWarSituationSituationId() {
		if(warSituationSituationId == null) {
			String sql = "select max(situation_id) as ids from war_situation";
			warSituationSituationId = IdUtil.getInstance().initIdStart(fetcher, sql);
		}
		return (int)warSituationSituationId.addAndGet(1);
	}
	
	private static AtomicLong warSituationSituationId;
	
	public static void commitAllSafety() {		getAvoidFighterDao().commitAllSafety();
		getBackupPositionDao().commitAllSafety();
		getBossDamageDataDao().commitAllSafety();
		getBossDataDao().commitAllSafety();
		getChuangZhenDao().commitAllSafety();
		getContinuousDao().commitAllSafety();
		getInviteUsersDao().commitAllSafety();
		getLevelUpRewardDao().commitAllSafety();
		getLogBossAwardDao().commitAllSafety();
		getLogBuyDao().commitAllSafety();
		getLogConsumeDao().commitAllSafety();
		getLogFindFighterDao().commitAllSafety();
		getLogLoginDao().commitAllSafety();
		getLogsDao().commitAllSafety();
		getMissionChallengeDao().commitAllSafety();
		getMissionMapDao().commitAllSafety();
		getMissionStarDao().commitAllSafety();
		getNewCampDao().commitAllSafety();
		getNewEquipmentDao().commitAllSafety();
		getNewFighterDao().commitAllSafety();
		getPresentsDao().commitAllSafety();
		getPvpDao().commitAllSafety();
		getPvpExtraDao().commitAllSafety();
		getPvpRankRewardDao().commitAllSafety();
		getRechargeRecordDao().commitAllSafety();
		getSkillsDao().commitAllSafety();
		getSpiriteDao().commitAllSafety();
		getTacticalDao().commitAllSafety();
		getUmsOnlinenoDao().commitAllSafety();
		getUserBagDao().commitAllSafety();
		getUserBaseDao().commitAllSafety();
		getUserDataDao().commitAllSafety();
		getUserDogzDao().commitAllSafety();
		getUserGridDao().commitAllSafety();
		getUserMarketDao().commitAllSafety();
		getUserMissionDao().commitAllSafety();
		getUserPiecesBagDao().commitAllSafety();
		getUserPiecesGridDao().commitAllSafety();
		getWarSituationDao().commitAllSafety();
	}		public static void setFetcher(ConnectionFetcher fetcher) {		DaoFactory.fetcher = fetcher;	}	private static ConnectionFetcher		fetcher;}