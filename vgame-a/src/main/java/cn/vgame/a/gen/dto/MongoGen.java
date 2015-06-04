


























	
		public static ConsoleLogDao getConsoleLogDao() {
		public static GmLogDao getGmLogDao() {
		public static RoleDao getRoleDao() {
		public static SystemKeyValueDao getSystemKeyValueDao() {
		public static YiJieRechargeLogDao getYiJieRechargeLogDao() {
		public static ZfbOrderDao getZfbOrderDao() {
		public static ZfbOrderFinishDao getZfbOrderFinishDao() {
		public static ZhuangDao getZhuangDao() {

	
		/**
		public CaiJinLogDtoCursor findByRoleId(String roleId) {
		/**
		public CaiJinLogDtoCursor findByNick(String nick) {
		/**
		public CaiJinLogDtoCursor findByCaiJin(long caiJin) {
		/**
		public CaiJinLogDtoCursor findByIsSmall(boolean isSmall) {

	
		/**
		public ConsoleLogDtoCursor findByDate(String date) {
		/**
		public ConsoleLogDtoCursor findByLog(String log) {
		/**

	
		/**
		public GmLogDtoCursor findByDate(String date) {
		/**
		public GmLogDtoCursor findByUser(String user) {
		/**
		public GmLogDtoCursor findByClassName(String className) {
		/**
		public GmLogDtoCursor findByMethodName(String methodName) {
		/**
		public GmLogDtoCursor findByResult(String result) {
		/**

	
		/**
		public RoleDtoCursor findByOwnerId(String ownerId) {
		/**
		public RoleDtoCursor findByIsRobot(boolean isRobot) {
		public RoleDtoCursor findByNick(String nick) {
		/**
		public RoleDtoCursor findByCoin(long coin) {
		/**
		public RoleDtoCursor findByBankPassword(String bankPassword) {
		/**
		public RoleDtoCursor findByCreateTime(long createTime) {
		/**
		public RoleDtoCursor findByBankCoin(long bankCoin) {
		/**
		public RoleDtoCursor findByRechargeHistory(long rechargeHistory) {
		/**
		public RoleDtoCursor findByHasJinYan(boolean hasJinYan) {
		public RoleDtoCursor findByIsOnline(boolean isOnline) {
		public RoleDtoCursor findByHasFengHao(boolean hasFengHao) {

	
		/**
		public SystemKeyValueDtoCursor findByValue(String value) {
		/**

	
		/**
		public YiJieRechargeLogDtoCursor findByApp(String app) {
		/**
		public YiJieRechargeLogDtoCursor findByCbi(String cbi) {
		/**
		public YiJieRechargeLogDtoCursor findByCt(long ct) {
		/**
		public YiJieRechargeLogDtoCursor findByFee(int fee) {
		/**
		public YiJieRechargeLogDtoCursor findByPt(long pt) {
		/**
		public YiJieRechargeLogDtoCursor findBySdk(String sdk) {
		/**
		public YiJieRechargeLogDtoCursor findBySign(String sign) {
		/**
		public YiJieRechargeLogDtoCursor findBySt(int st) {
		/**
		public YiJieRechargeLogDtoCursor findByTcd(String tcd) {
		/**
		public YiJieRechargeLogDtoCursor findByUid(String uid) {
		/**
		public YiJieRechargeLogDtoCursor findByVer(String ver) {
		/**
		public YiJieRechargeLogDtoCursor findByRoleId(String roleId) {
		/**
		public YiJieRechargeLogDtoCursor findByNick(String nick) {
		/**
		public YiJieRechargeLogDtoCursor findByCoin(long coin) {
		/**

	
		/**
		public ZfbOrderDtoCursor findByCount(int count) {
		/**
		public ZfbOrderDtoCursor findByPrice(String price) {
		/**
		public ZfbOrderDtoCursor findByTime(long time) {
		/**
		public ZfbOrderDtoCursor findByRoleId(String roleId) {
		/**

	
		/**
		public ZfbOrderFinishDtoCursor findByCount(int count) {
		/**
		public ZfbOrderFinishDtoCursor findByPrice(String price) {
		/**
		public ZfbOrderFinishDtoCursor findByTime(long time) {
		/**
		public ZfbOrderFinishDtoCursor findByUserId(String userId) {
		/**

	
		/**
		public ZhuangDtoCursor findByTime(long time) {
		/**
		public ZhuangDtoCursor findByIsZhuang(boolean isZhuang) {

	
		private String roleId = "";
		private String nick = "";
		private long caiJin = 0;
		private boolean isSmall = false;






		public String getRoleId() {
		public String getNick() {
		public long getCaiJin() {
		public boolean getIsSmall() {

		public void setRoleId(String roleId) {
		public void setNick(String nick) {
		public void setCaiJin(long caiJin) {
		public void setIsSmall(boolean isSmall) {

			o.put("id", MongoGen.toObject(id));
			o.put("roleId", MongoGen.toObject(roleId));
			o.put("nick", MongoGen.toObject(nick));
			o.put("caiJin", MongoGen.toObject(caiJin));
			o.put("isSmall", MongoGen.toObject(isSmall));

			roleId = getString(o, "roleId");
			nick = getString(o, "nick");
			caiJin = getLong(o, "caiJin");
			isSmall = getBoolean(o, "isSmall");











	
		private String date = "";
		private String log = "";




		public String getDate() {
		public String getLog() {

		public void setDate(String date) {
		public void setLog(String log) {

			o.put("id", MongoGen.toObject(id));
			o.put("date", MongoGen.toObject(date));
			o.put("log", MongoGen.toObject(log));

			date = getString(o, "date");
			log = getString(o, "log");







	
		private String date = "";
		private String user = "";
		private String className = "";
		private String methodName = "";
		private List<String> args = Lists.newArrayList();
		private String result = "";








		public String getDate() {
		public String getUser() {
		public String getClassName() {
		public String getMethodName() {
		public List<String> getArgs() {
		public String getResult() {

		public void setDate(String date) {
		public void setUser(String user) {
		public void setClassName(String className) {
		public void setMethodName(String methodName) {
		public void setArgs(List<String> args) {
		public void setResult(String result) {

			o.put("id", MongoGen.toObject(id));
			o.put("date", MongoGen.toObject(date));
			o.put("user", MongoGen.toObject(user));
			o.put("className", MongoGen.toObject(className));
			o.put("methodName", MongoGen.toObject(methodName));
			o.put("args", MongoGen.toObjectString(args));
			o.put("result", MongoGen.toObject(result));

			date = getString(o, "date");
			user = getString(o, "user");
			className = getString(o, "className");
			methodName = getString(o, "methodName");
			args = loadArgs(o);
			result = getString(o, "result");












		List<String> loadArgs(DBObject o) {


	
		private String ownerId = "";
		private boolean isRobot = false;
		private String nick = "";
		private long coin = 0;
		private String bankPassword = "";
		private long createTime = 0;
		private long bankCoin = 0;
		private long rechargeHistory = 0;
		private boolean hasJinYan = false;
		private boolean isOnline = false;
		private boolean hasFengHao = false;
		private MongoMap<String> keyValueDaily = Maps.newMongoMap();
		private MongoMap<String> keyValueForever = Maps.newMongoMap();















		public String getOwnerId() {
		public boolean getIsRobot() {
		public String getNick() {
		public long getCoin() {
		public String getBankPassword() {
		public long getCreateTime() {
		public long getBankCoin() {
		public long getRechargeHistory() {
		public boolean getHasJinYan() {
		public boolean getIsOnline() {
		public boolean getHasFengHao() {
		public MongoMap<String> getKeyValueDaily() {
		public MongoMap<String> getKeyValueForever() {

		public void setOwnerId(String ownerId) {
		public void setIsRobot(boolean isRobot) {
		public void setNick(String nick) {
		public void setCoin(long coin) {
		public void setBankPassword(String bankPassword) {
		public void setCreateTime(long createTime) {
		public void setBankCoin(long bankCoin) {
		public void setRechargeHistory(long rechargeHistory) {
		public void setHasJinYan(boolean hasJinYan) {
		public void setIsOnline(boolean isOnline) {
		public void setHasFengHao(boolean hasFengHao) {
		public void setKeyValueDaily(MongoMap<String> keyValueDaily) {
		public void setKeyValueForever(MongoMap<String> keyValueForever) {

			o.put("id", MongoGen.toObject(id));
			o.put("ownerId", MongoGen.toObject(ownerId));
			o.put("isRobot", MongoGen.toObject(isRobot));
			o.put("nick", MongoGen.toObject(nick));
			o.put("coin", MongoGen.toObject(coin));
			o.put("bankPassword", MongoGen.toObject(bankPassword));
			o.put("createTime", MongoGen.toObject(createTime));
			o.put("bankCoin", MongoGen.toObject(bankCoin));
			o.put("rechargeHistory", MongoGen.toObject(rechargeHistory));
			o.put("hasJinYan", MongoGen.toObject(hasJinYan));
			o.put("isOnline", MongoGen.toObject(isOnline));
			o.put("hasFengHao", MongoGen.toObject(hasFengHao));
			o.put("keyValueDaily", MongoGen.toObjectString(keyValueDaily));
			o.put("keyValueForever", MongoGen.toObjectString(keyValueForever));

			ownerId = getString(o, "ownerId");
			isRobot = getBoolean(o, "isRobot");
			nick = getString(o, "nick");
			coin = getLong(o, "coin");
			bankPassword = getString(o, "bankPassword");
			createTime = getLong(o, "createTime");
			bankCoin = getLong(o, "bankCoin");
			rechargeHistory = getLong(o, "rechargeHistory");
			hasJinYan = getBoolean(o, "hasJinYan");
			isOnline = getBoolean(o, "isOnline");
			hasFengHao = getBoolean(o, "hasFengHao");
			keyValueDaily = loadKeyValueDaily(o);
			keyValueForever = loadKeyValueForever(o);












		MongoMap<String> loadKeyValueDaily(DBObject o) {
		MongoMap<String> loadKeyValueForever(DBObject o) {















	
		private String value = "";



		public String getValue() {

		public void setValue(String value) {

			o.put("key", MongoGen.toObject(key));
			o.put("value", MongoGen.toObject(value));

			value = getString(o, "value");





	
		private String app = "";
		private String cbi = "";
		private long ct = 0;
		private int fee = 0;
		private long pt = 0;
		private String sdk = "";
		private String sign = "";
		private int st = 0;
		private String tcd = "";
		private String uid = "";
		private String ver = "";
		private String roleId = "";
		private String nick = "";
		private long coin = 0;
















		public String getApp() {
		public String getCbi() {
		public long getCt() {
		public int getFee() {
		public long getPt() {
		public String getSdk() {
		public String getSign() {
		public int getSt() {
		public String getTcd() {
		public String getUid() {
		public String getVer() {
		public String getRoleId() {
		public String getNick() {
		public long getCoin() {

		public void setApp(String app) {
		public void setCbi(String cbi) {
		public void setCt(long ct) {
		public void setFee(int fee) {
		public void setPt(long pt) {
		public void setSdk(String sdk) {
		public void setSign(String sign) {
		public void setSt(int st) {
		public void setTcd(String tcd) {
		public void setUid(String uid) {
		public void setVer(String ver) {
		public void setRoleId(String roleId) {
		public void setNick(String nick) {
		public void setCoin(long coin) {

			o.put("ssid", MongoGen.toObject(ssid));
			o.put("app", MongoGen.toObject(app));
			o.put("cbi", MongoGen.toObject(cbi));
			o.put("ct", MongoGen.toObject(ct));
			o.put("fee", MongoGen.toObject(fee));
			o.put("pt", MongoGen.toObject(pt));
			o.put("sdk", MongoGen.toObject(sdk));
			o.put("sign", MongoGen.toObject(sign));
			o.put("st", MongoGen.toObject(st));
			o.put("tcd", MongoGen.toObject(tcd));
			o.put("uid", MongoGen.toObject(uid));
			o.put("ver", MongoGen.toObject(ver));
			o.put("roleId", MongoGen.toObject(roleId));
			o.put("nick", MongoGen.toObject(nick));
			o.put("coin", MongoGen.toObject(coin));

			app = getString(o, "app");
			cbi = getString(o, "cbi");
			ct = getLong(o, "ct");
			fee = getInteger(o, "fee");
			pt = getLong(o, "pt");
			sdk = getString(o, "sdk");
			sign = getString(o, "sign");
			st = getInteger(o, "st");
			tcd = getString(o, "tcd");
			uid = getString(o, "uid");
			ver = getString(o, "ver");
			roleId = getString(o, "roleId");
			nick = getString(o, "nick");
			coin = getLong(o, "coin");































	
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String roleId = "";






		public int getCount() {
		public String getPrice() {
		public long getTime() {
		public String getRoleId() {

		public void setCount(int count) {
		public void setPrice(String price) {
		public void setTime(long time) {
		public void setRoleId(String roleId) {

			o.put("id", MongoGen.toObject(id));
			o.put("count", MongoGen.toObject(count));
			o.put("price", MongoGen.toObject(price));
			o.put("time", MongoGen.toObject(time));
			o.put("roleId", MongoGen.toObject(roleId));

			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			roleId = getString(o, "roleId");











	
		private int count = 0;
		private String price = "";
		private long time = 0;
		private String userId = "";






		public int getCount() {
		public String getPrice() {
		public long getTime() {
		public String getUserId() {

		public void setCount(int count) {
		public void setPrice(String price) {
		public void setTime(long time) {
		public void setUserId(String userId) {

			o.put("id", MongoGen.toObject(id));
			o.put("count", MongoGen.toObject(count));
			o.put("price", MongoGen.toObject(price));
			o.put("time", MongoGen.toObject(time));
			o.put("userId", MongoGen.toObject(userId));

			count = getInteger(o, "count");
			price = getString(o, "price");
			time = getLong(o, "time");
			userId = getString(o, "userId");











	
		private long time = 0;
		private boolean isZhuang = false;




		public long getTime() {
		public boolean getIsZhuang() {

		public void setTime(long time) {
		public void setIsZhuang(boolean isZhuang) {

			o.put("id", MongoGen.toObject(id));
			o.put("time", MongoGen.toObject(time));
			o.put("isZhuang", MongoGen.toObject(isZhuang));

			time = getLong(o, "time");
			isZhuang = getBoolean(o, "isZhuang");







