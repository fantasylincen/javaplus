
		public static BossRankInfoHistoryDao getBossRankInfoHistoryDao() {
		public static ColdDownDao getColdDownDao() {
		public static DailyTaskDao getDailyTaskDao() {
		public static EnemyDao getEnemyDao() {
		public static FeedBackDao getFeedBackDao() {
		public static FeedBack2Dao getFeedBack2Dao() {
		public static FighterHadDao getFighterHadDao() {
		public static FriendDao getFriendDao() {
		public static HeishiDao getHeishiDao() {
		public static HeiShiStoreDao getHeiShiStoreDao() {
		public static InviteCodeDao getInviteCodeDao() {
		public static InviteUsersDao getInviteUsersDao() {
		public static KeyValueDao getKeyValueDao() {
		public static KeyValueDataDao getKeyValueDataDao() {
		public static LogSnatchDao getLogSnatchDao() {
		public static MxzTokenDao getMxzTokenDao() {
		public static NvwaDao getNvwaDao() {
		public static PropAddLogDao getPropAddLogDao() {
		public static PropConsumeLogDao getPropConsumeLogDao() {
		public static PvpWarSituationDao getPvpWarSituationDao() {
		public static UserChatRecordDao getUserChatRecordDao() {
		public static UserCountersDao getUserCountersDao() {
		public static UserCountersAllDao getUserCountersAllDao() {
		public static UserFriendRequestDao getUserFriendRequestDao() {
		public static UserGuideDao getUserGuideDao() {
		public static WorldChatRecordDao getWorldChatRecordDao() {
		public static WorldChatRecordAllDao getWorldChatRecordAllDao() {
		public static ZbsrDao getZbsrDao() {

	
		public AchieveTaskDtoCursor findByUname(String uname) {
		public AchieveTaskDtoCursor findByFinishtimes(int finishtimes) {
		public AchieveTaskDtoCursor findByIsDraw(boolean isDraw) {

	

	
		public ColdDownDtoCursor findByIndex(int index) {
		public ColdDownDtoCursor findByEndTime(long endTime) {

	
		public DailyTaskDtoCursor findByUname(String uname) {
		public DailyTaskDtoCursor findByFinishtimes(int finishtimes) {
		public DailyTaskDtoCursor findByIsDraw(boolean isDraw) {

	
		public EnemyDtoCursor findByEnemyId(String enemyId) {

	
		public FeedBackDtoCursor findByRechargeGold(int rechargeGold) {

	
		public FeedBack2DtoCursor findByRechargeGold(int rechargeGold) {

	
		public FighterHadDtoCursor findByFighterTypeId(int fighterTypeId) {

	
		public FriendDtoCursor findByFriendName(String friendName) {

	
		public HeishiDtoCursor findByQsjs(int qsjs) {
		public HeishiDtoCursor findByBuyStr(String buyStr) {

	

	
		public InviteCodeDtoCursor findByCode(String code) {

	
		public InviteUsersDtoCursor findByB(String b) {

	
		public KeyValueDtoCursor findByV(String v) {

	
		public KeyValueDataDtoCursor findByData(String data) {

	
		public LogSnatchDtoCursor findByUname(String uname) {
		public LogSnatchDtoCursor findByDatatype(int datatype) {
		public LogSnatchDtoCursor findByNub(int nub) {
		public LogSnatchDtoCursor findByRobber(String robber) {
		public LogSnatchDtoCursor findByTime(int time) {
		public LogSnatchDtoCursor findByWarsituationid(int warsituationid) {
		public LogSnatchDtoCursor findByIswin(boolean iswin) {
		public LogSnatchDtoCursor findByIsSaw(boolean isSaw) {
		public LogSnatchDtoCursor findByIsSnatchSuccess(boolean isSnatchSuccess) {

	
		public MxzTokenDtoCursor findByToken(String token) {
		public MxzTokenDtoCursor findByUserId(String userId) {
		public MxzTokenDtoCursor findByGenerateTime(long generateTime) {

	
		public NvwaDtoCursor findByBuyCount(int buyCount) {
		public NvwaDtoCursor findByGoldAll(int goldAll) {
		public NvwaDtoCursor findByIsSendBack(boolean isSendBack) {

	
		public PropAddLogDtoCursor findByUname(String uname) {
		public PropAddLogDtoCursor findByPropId(int propId) {
		public PropAddLogDtoCursor findByCount(int count) {

	
		public PropConsumeLogDtoCursor findByUname(String uname) {
		public PropConsumeLogDtoCursor findByPropId(int propId) {
		public PropConsumeLogDtoCursor findByCount(int count) {

	
		public PvpWarSituationDtoCursor findByChallengerId(String challengerId) {
		public PvpWarSituationDtoCursor findByDefenderId(String defenderId) {
		public PvpWarSituationDtoCursor findByCreateTime(int createTime) {
		public PvpWarSituationDtoCursor findByIsWin(boolean isWin) {
		public PvpWarSituationDtoCursor findByIsSaw(boolean isSaw) {

	
		public UserChatRecordDtoCursor findBySender(String sender) {
		public UserChatRecordDtoCursor findByReceiver(String receiver) {
		public UserChatRecordDtoCursor findByTime(long time) {
		public UserChatRecordDtoCursor findByMessage(String message) {
		public UserChatRecordDtoCursor findByHasRead(boolean hasRead) {

	
		public UserCountersDtoCursor findByCounterId(String counterId) {
		public UserCountersDtoCursor findByCount(int count) {

	
		public UserCountersAllDtoCursor findByCounterId(String counterId) {
		public UserCountersAllDtoCursor findByCount(int count) {

	
		public UserFriendRequestDtoCursor findByReceiver(String receiver) {
		public UserFriendRequestDtoCursor findByRequestTime(long requestTime) {

	
		public UserGuideDtoCursor findByGuideId(int guideId) {
		public UserGuideDtoCursor findByGuideStatus(String guideStatus) {

	
		public WorldChatRecordDtoCursor findBySender(String sender) {
		public WorldChatRecordDtoCursor findByTime(long time) {
		public WorldChatRecordDtoCursor findByMessage(String message) {

	
		public WorldChatRecordAllDtoCursor findBySender(String sender) {
		public WorldChatRecordAllDtoCursor findByTime(long time) {
		public WorldChatRecordAllDtoCursor findByMessage(String message) {

	

	
		private String uname;
		private int finishtimes;
		private boolean isDraw;

		public String getUname() {
		public int getFinishtimes() {
		public boolean getIsDraw() {

		public void setUname(String uname) {
		public void setFinishtimes(int finishtimes) {
		public void setIsDraw(boolean isDraw) {

		o.put("_id", taskId + ":" + uname);
			o.put("taskId", taskId);
			o.put("uname", uname);
			o.put("finishtimes", finishtimes);
			o.put("isDraw", isDraw);

		uname = getString(o, "uname");
		finishtimes = getInteger(o, "finishtimes");
		isDraw = getBoolean(o, "isDraw");

	
		private List<SimpleChallengerDto> list = Lists.newArrayList();
		private int bossHpMax;

		public List<SimpleChallengerDto> getList() {
		public int getBossHpMax() {

		public void setList(List<SimpleChallengerDto> list) {
		public void setBossHpMax(int bossHpMax) {

			if(killer != null) {
			BasicDBList list = new BasicDBList() ;
			o.put("bossHpMax", bossHpMax);

	
		bossHpMax = getInteger(o, "bossHpMax");

	
		private int index;
		private long endTime;

		public int getIndex() {
		public long getEndTime() {

		public void setIndex(int index) {
		public void setEndTime(long endTime) {

		o.put("_id", uname + ":" + index);
			o.put("uname", uname);
			o.put("index", index);
			o.put("endTime", endTime);

		index = getInteger(o, "index");
		endTime = getLong(o, "endTime");

	
		private String uname;
		private int finishtimes;
		private boolean isDraw;

		public String getUname() {
		public int getFinishtimes() {
		public boolean getIsDraw() {

		public void setUname(String uname) {
		public void setFinishtimes(int finishtimes) {
		public void setIsDraw(boolean isDraw) {

		o.put("_id", taskId + ":" + uname);
			o.put("taskId", taskId);
			o.put("uname", uname);
			o.put("finishtimes", finishtimes);
			o.put("isDraw", isDraw);

		uname = getString(o, "uname");
		finishtimes = getInteger(o, "finishtimes");
		isDraw = getBoolean(o, "isDraw");

	
		private String enemyId;

		public String getEnemyId() {

		public void setEnemyId(String enemyId) {

		checkNull(enemyId);
		o.put("_id", uname + ":" + enemyId);
			o.put("uname", uname);
			o.put("enemyId", enemyId);

		enemyId = getString(o, "enemyId");

	
		private int rechargeGold;
		private List<ReceivedBoxDto> receivedIds = Lists.newArrayList();

		public int getRechargeGold() {
		public List<ReceivedBoxDto> getReceivedIds() {

		public void setRechargeGold(int rechargeGold) {
		public void setReceivedIds(List<ReceivedBoxDto> receivedIds) {

		o.put("_id", uname);
			o.put("uname", uname);
			o.put("rechargeGold", rechargeGold);
			BasicDBList receivedIds = new BasicDBList() ;

		rechargeGold = getInteger(o, "rechargeGold");
	

	
		private int rechargeGold;
		private List<ReceivedBox2Dto> receivedIds = Lists.newArrayList();

		public int getRechargeGold() {
		public List<ReceivedBox2Dto> getReceivedIds() {

		public void setRechargeGold(int rechargeGold) {
		public void setReceivedIds(List<ReceivedBox2Dto> receivedIds) {

		o.put("_id", uname);
			o.put("uname", uname);
			o.put("rechargeGold", rechargeGold);
			BasicDBList receivedIds = new BasicDBList() ;

		rechargeGold = getInteger(o, "rechargeGold");
	

	
		private int fighterTypeId;

		public int getFighterTypeId() {

		public void setFighterTypeId(int fighterTypeId) {

		o.put("_id", uname + ":" + fighterTypeId);
			o.put("uname", uname);
			o.put("fighterTypeId", fighterTypeId);

		fighterTypeId = getInteger(o, "fighterTypeId");

	
		private String friendName;

		public String getFriendName() {

		public void setFriendName(String friendName) {

		checkNull(friendName);
		o.put("_id", uname + ":" + friendName);
			o.put("uname", uname);
			o.put("friendName", friendName);

		friendName = getString(o, "friendName");

	
		private int qsjs;
		private String buyStr;

		public int getQsjs() {
		public String getBuyStr() {

		public void setQsjs(int qsjs) {
		public void setBuyStr(String buyStr) {

		checkNull(buyStr);
		o.put("_id", userName);
			o.put("userName", userName);
			o.put("qsjs", qsjs);
			o.put("buyStr", buyStr);

		qsjs = getInteger(o, "qsjs");
		buyStr = getString(o, "buyStr");

	
		private int remainCount;
		private int limit;
		private boolean hasExchange;
		private int countExchangeEvery;

		public int getRemainCount() {
		public int getLimit() {
		public boolean getHasExchange() {
		public int getCountExchangeEvery() {

		public void setRemainCount(int remainCount) {
		public void setLimit(int limit) {
		public void setHasExchange(boolean hasExchange) {
		public void setCountExchangeEvery(int countExchangeEvery) {

			o.put("id", id);
			o.put("remainCount", remainCount);
			o.put("limit", limit);
			o.put("hasExchange", hasExchange);
			o.put("countExchangeEvery", countExchangeEvery);

		remainCount = getInteger(o, "remainCount");
		limit = getInteger(o, "limit");
		hasExchange = getBoolean(o, "hasExchange");
		countExchangeEvery = getInteger(o, "countExchangeEvery");

	
		private List<HeiShiGoodsDto> heiShiGoods = Lists.newArrayList();

		public List<HeiShiGoodsDto> getHeiShiGoods() {

		public void setHeiShiGoods(List<HeiShiGoodsDto> heiShiGoods) {

		o.put("_id", uname);
			o.put("uname", uname);
			BasicDBList heiShiGoods = new BasicDBList() ;

	

	
		private String code;

		public String getCode() {

		public void setCode(String code) {

		checkNull(code);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("code", code);

		code = getString(o, "code");

	
		private String b;

		public String getB() {

		public void setB(String b) {

		checkNull(b);
		o.put("_id", a);
			o.put("a", a);
			o.put("b", b);

		b = getString(o, "b");

	
		private String v;

		public String getV() {

		public void setV(String v) {

		checkNull(v);
		o.put("_id", k);
			o.put("k", k);
			o.put("v", v);

		v = getString(o, "v");

	
		private String data;

		public String getData() {

		public void setData(String data) {

		checkNull(data);
		o.put("_id", uname);
			o.put("uname", uname);
			o.put("data", data);

		data = getString(o, "data");

	
		private String uname;
		private int datatype;
		private int nub;
		private String robber;
		private int time;
		private int warsituationid;
		private boolean iswin;
		private boolean isSaw;
		private boolean isSnatchSuccess;

		public String getUname() {
		public int getDatatype() {
		public int getNub() {
		public String getRobber() {
		public int getTime() {
		public int getWarsituationid() {
		public boolean getIswin() {
		public boolean getIsSaw() {
		public boolean getIsSnatchSuccess() {

		public void setUname(String uname) {
		public void setDatatype(int datatype) {
		public void setNub(int nub) {
		public void setRobber(String robber) {
		public void setTime(int time) {
		public void setWarsituationid(int warsituationid) {
		public void setIswin(boolean iswin) {
		public void setIsSaw(boolean isSaw) {
		public void setIsSnatchSuccess(boolean isSnatchSuccess) {

		checkNull(robber);
		o.put("_id", id);
			o.put("id", id);
			o.put("uname", uname);
			o.put("datatype", datatype);
			o.put("nub", nub);
			o.put("robber", robber);
			o.put("time", time);
			o.put("warsituationid", warsituationid);
			o.put("iswin", iswin);
			o.put("isSaw", isSaw);
			o.put("isSnatchSuccess", isSnatchSuccess);

		uname = getString(o, "uname");
		datatype = getInteger(o, "datatype");
		nub = getInteger(o, "nub");
		robber = getString(o, "robber");
		time = getInteger(o, "time");
		warsituationid = getInteger(o, "warsituationid");
		iswin = getBoolean(o, "iswin");
		isSaw = getBoolean(o, "isSaw");
		isSnatchSuccess = getBoolean(o, "isSnatchSuccess");

	
		private String token;
		private String userId;
		private long generateTime;

		public String getToken() {
		public String getUserId() {
		public long getGenerateTime() {

		public void setToken(String token) {
		public void setUserId(String userId) {
		public void setGenerateTime(long generateTime) {

		checkNull(token);
		checkNull(userId);
		o.put("_id", lineKongUname + ":" + token);
			o.put("lineKongUname", lineKongUname);
			o.put("token", token);
			o.put("userId", userId);
			o.put("generateTime", generateTime);

		token = getString(o, "token");
		userId = getString(o, "userId");
		generateTime = getLong(o, "generateTime");

	
		private int buyCount;
		private int goldAll;
		private boolean isSendBack;

		public int getBuyCount() {
		public int getGoldAll() {
		public boolean getIsSendBack() {

		public void setBuyCount(int buyCount) {
		public void setGoldAll(int goldAll) {
		public void setIsSendBack(boolean isSendBack) {

		o.put("_id", uname);
			o.put("uname", uname);
			o.put("buyCount", buyCount);
			o.put("goldAll", goldAll);
			o.put("isSendBack", isSendBack);

		buyCount = getInteger(o, "buyCount");
		goldAll = getInteger(o, "goldAll");
		isSendBack = getBoolean(o, "isSendBack");

	
		private String uname;
		private int propId;
		private int count;

		public String getUname() {
		public int getPropId() {
		public int getCount() {

		public void setUname(String uname) {
		public void setPropId(int propId) {
		public void setCount(int count) {


			o.put("time", time);
			o.put("uname", uname);
			o.put("propId", propId);
			o.put("count", count);

		uname = getString(o, "uname");
		propId = getInteger(o, "propId");
		count = getInteger(o, "count");

	
		private String uname;
		private int propId;
		private int count;

		public String getUname() {
		public int getPropId() {
		public int getCount() {

		public void setUname(String uname) {
		public void setPropId(int propId) {
		public void setCount(int count) {


			o.put("time", time);
			o.put("uname", uname);
			o.put("propId", propId);
			o.put("count", count);

		uname = getString(o, "uname");
		propId = getInteger(o, "propId");
		count = getInteger(o, "count");

	
		private String challengerId;
		private String defenderId;
		private int createTime;
		private byte[] data;
		private boolean isWin;
		private boolean isSaw;

		public String getChallengerId() {
		public String getDefenderId() {
		public int getCreateTime() {
		public byte[] getData() {
		public boolean getIsWin() {
		public boolean getIsSaw() {

		public void setChallengerId(String challengerId) {
		public void setDefenderId(String defenderId) {
		public void setCreateTime(int createTime) {
		public void setData(byte[] data) {
		public void setIsWin(boolean isWin) {
		public void setIsSaw(boolean isSaw) {

		checkNull(defenderId);
		o.put("_id", situationId);
			o.put("situationId", situationId);
			o.put("challengerId", challengerId);
			o.put("defenderId", defenderId);
			o.put("createTime", createTime);
			o.put("data", data);
			o.put("isWin", isWin);
			o.put("isSaw", isSaw);

		challengerId = getString(o, "challengerId");
		defenderId = getString(o, "defenderId");
		createTime = getInteger(o, "createTime");
		data = getBytes(o, "data");
		isWin = getBoolean(o, "isWin");
		isSaw = getBoolean(o, "isSaw");

	



			o.put("boxId", boxId);


	
		private int receiveTimes;

		public int getReceiveTimes() {

		public void setReceiveTimes(int receiveTimes) {

			o.put("boxId", boxId);
			o.put("receiveTimes", receiveTimes);

		receiveTimes = getInteger(o, "receiveTimes");

	
		private String nick;
		private int reputation;
		private int allDamage;
		private int rank;

		public String getNick() {
		public int getReputation() {
		public int getAllDamage() {
		public int getRank() {

		public void setNick(String nick) {
		public void setReputation(int reputation) {
		public void setAllDamage(int allDamage) {
		public void setRank(int rank) {

		checkNull(nick);

			o.put("userId", userId);
			o.put("nick", nick);
			o.put("reputation", reputation);
			o.put("allDamage", allDamage);
			o.put("rank", rank);

		nick = getString(o, "nick");
		reputation = getInteger(o, "reputation");
		allDamage = getInteger(o, "allDamage");
		rank = getInteger(o, "rank");

	
		private String sender;
		private String receiver;
		private long time;
		private String message;
		private boolean hasRead;

		public String getSender() {
		public String getReceiver() {
		public long getTime() {
		public String getMessage() {
		public boolean getHasRead() {

		public void setSender(String sender) {
		public void setReceiver(String receiver) {
		public void setTime(long time) {
		public void setMessage(String message) {
		public void setHasRead(boolean hasRead) {

		checkNull(receiver);
		checkNull(message);
		o.put("_id", id + ":" + receiver);
			o.put("id", id);
			o.put("sender", sender);
			o.put("receiver", receiver);
			o.put("time", time);
			o.put("message", message);
			o.put("hasRead", hasRead);

		sender = getString(o, "sender");
		receiver = getString(o, "receiver");
		time = getLong(o, "time");
		message = getString(o, "message");
		hasRead = getBoolean(o, "hasRead");

	
		private String counterId;
		private int count;

		public String getCounterId() {
		public int getCount() {

		public void setCounterId(String counterId) {
		public void setCount(int count) {

		checkNull(counterId);
		o.put("_id", uname + ":" + counterId);
			o.put("uname", uname);
			o.put("counterId", counterId);
			o.put("count", count);

		counterId = getString(o, "counterId");
		count = getInteger(o, "count");

	
		private String counterId;
		private int count;

		public String getCounterId() {
		public int getCount() {

		public void setCounterId(String counterId) {
		public void setCount(int count) {

		checkNull(counterId);
		o.put("_id", uname + ":" + counterId);
			o.put("uname", uname);
			o.put("counterId", counterId);
			o.put("count", count);

		counterId = getString(o, "counterId");
		count = getInteger(o, "count");

	
		private String receiver;
		private long requestTime;

		public String getReceiver() {
		public long getRequestTime() {

		public void setReceiver(String receiver) {
		public void setRequestTime(long requestTime) {

		checkNull(receiver);
		o.put("_id", applicant + ":" + receiver);
			o.put("applicant", applicant);
			o.put("receiver", receiver);
			o.put("requestTime", requestTime);

		receiver = getString(o, "receiver");
		requestTime = getLong(o, "requestTime");

	
		private int guideId;
		private String guideStatus;

		public int getGuideId() {
		public String getGuideStatus() {

		public void setGuideId(int guideId) {
		public void setGuideStatus(String guideStatus) {

		checkNull(guideStatus);
		o.put("_id", uname + ":" + guideId);
			o.put("uname", uname);
			o.put("guideId", guideId);
			o.put("guideStatus", guideStatus);

		guideId = getInteger(o, "guideId");
		guideStatus = getString(o, "guideStatus");

	
		private String sender;
		private long time;
		private String message;

		public String getSender() {
		public long getTime() {
		public String getMessage() {

		public void setSender(String sender) {
		public void setTime(long time) {
		public void setMessage(String message) {

		checkNull(message);
		o.put("_id", id);
			o.put("id", id);
			o.put("sender", sender);
			o.put("time", time);
			o.put("message", message);

		sender = getString(o, "sender");
		time = getLong(o, "time");
		message = getString(o, "message");

	
		private String sender;
		private long time;
		private String message;

		public String getSender() {
		public long getTime() {
		public String getMessage() {

		public void setSender(String sender) {
		public void setTime(long time) {
		public void setMessage(String message) {

		checkNull(message);
		o.put("_id", id);
			o.put("id", id);
			o.put("sender", sender);
			o.put("time", time);
			o.put("message", message);

		sender = getString(o, "sender");
		time = getLong(o, "time");
		message = getString(o, "message");

	
		private List<ZbsrGoodsDto> equipments = Lists.newArrayList();

		public List<ZbsrGoodsDto> getEquipments() {

		public void setEquipments(List<ZbsrGoodsDto> equipments) {

		o.put("_id", uname);
			o.put("uname", uname);
			BasicDBList equipments = new BasicDBList() ;

	

	
		private int equipmentTempletId;
		private boolean isTeJia;
		private boolean hasReceive;

		public int getEquipmentTempletId() {
		public boolean getIsTeJia() {
		public boolean getHasReceive() {

		public void setEquipmentTempletId(int equipmentTempletId) {
		public void setIsTeJia(boolean isTeJia) {
		public void setHasReceive(boolean hasReceive) {

			o.put("id", id);
			o.put("equipmentTempletId", equipmentTempletId);
			o.put("isTeJia", isTeJia);
			o.put("hasReceive", hasReceive);

		equipmentTempletId = getInteger(o, "equipmentTempletId");
		isTeJia = getBoolean(o, "isTeJia");
		hasReceive = getBoolean(o, "hasReceive");

