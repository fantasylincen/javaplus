





























	
		public static OrderFinishDao getOrderFinishDao() {
		public static ProjectDao getProjectDao() {
		public static SystemKeyValueDao getSystemKeyValueDao() {
		public static UserDao getUserDao() {
		public static ZfbOrderDao getZfbOrderDao() {
		public static ZfbOrderFinishDao getZfbOrderFinishDao() {

	
		/**
		public OrderDtoCursor findByServerId(String serverId) {
		/**
		public OrderDtoCursor findByCount(int count) {
		/**
		public OrderDtoCursor findByTime(long time) {
		/**
		public OrderDtoCursor findByRechargeUserId(String rechargeUserId) {
		/**
		public OrderDtoCursor findByUserId(String userId) {
		/**
		public OrderDtoCursor findByIsError(boolean isError) {
		public OrderDtoCursor findByLastProcessTime(long lastProcessTime) {
		/**
		public OrderDtoCursor findByRetrySpace(long retrySpace) {
		/**
		public OrderDtoCursor findByReason(String reason) {
		/**

	
		/**
		public OrderFinishDtoCursor findByServerId(String serverId) {
		/**
		public OrderFinishDtoCursor findByCount(int count) {
		/**
		public OrderFinishDtoCursor findByTime(long time) {
		/**
		public OrderFinishDtoCursor findByRechargeUserId(String rechargeUserId) {
		/**
		public OrderFinishDtoCursor findByUserId(String userId) {
		/**
		public OrderFinishDtoCursor findByIsError(boolean isError) {
		public OrderFinishDtoCursor findByLastProcessTime(long lastProcessTime) {
		/**
		public OrderFinishDtoCursor findByRetrySpace(long retrySpace) {
		/**
		public OrderFinishDtoCursor findByReason(String reason) {
		/**

	
		/**
		public ProjectDtoCursor findByName(String name) {
		/**
		public ProjectDtoCursor findByGmScript(String gmScript) {
		/**

	
		/**
		public SystemKeyValueDtoCursor findByValue(String value) {
		/**

	
		/**
		public UserDtoCursor findByEmail(String email) {
		/**
		public UserDtoCursor findByQQOpenId(String qQOpenId) {
		/**
		public UserDtoCursor findByPwdMD5(String pwdMD5) {
		/**
		public UserDtoCursor findByNick(String nick) {
		/**
		public UserDtoCursor findByIsMan(boolean isMan) {
		public UserDtoCursor findByAge(int age) {
		/**
		public UserDtoCursor findByVb(int vb) {
		/**

	
		/**
		public ZfbOrderDtoCursor findByCount(int count) {
		/**
		public ZfbOrderDtoCursor findByPrice(String price) {
		/**
		public ZfbOrderDtoCursor findByTime(long time) {
		/**
		public ZfbOrderDtoCursor findByUserId(String userId) {
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

	
		private int version = 0;
		private byte[] data = null;




		public int getVersion() {
		public byte[] getData() {

		public void setVersion(int version) {
		public void setData(byte[] data) {

			o.put("updateTime", MongoGen.toObject(updateTime));
			o.put("version", MongoGen.toObject(version));
			o.put("data", MongoGen.toObject(data));

			version = getInteger(o, "version");
			data = getBytes(o, "data");







	
		private String value = "";
		private boolean isClientVisible = false;
		private String dsc = "";





		public String getValue() {
		public boolean getIsClientVisible() {
		public String getDsc() {

		public void setValue(String value) {
		public void setIsClientVisible(boolean isClientVisible) {
		public void setDsc(String dsc) {

			o.put("key", MongoGen.toObject(key));
			o.put("value", MongoGen.toObject(value));
			o.put("isClientVisible", MongoGen.toObject(isClientVisible));
			o.put("dsc", MongoGen.toObject(dsc));

			value = getString(o, "value");
			isClientVisible = getBoolean(o, "isClientVisible");
			dsc = getString(o, "dsc");









	
		private String serverId = "";
		private int count = 0;
		private long time = 0;
		private String rechargeUserId = "";
		private String userId = "";
		private boolean isError = false;
		private long lastProcessTime = 0;
		private long retrySpace = 0;
		private String reason = "";











		public String getServerId() {
		public int getCount() {
		public long getTime() {
		public String getRechargeUserId() {
		public String getUserId() {
		public boolean getIsError() {
		public long getLastProcessTime() {
		public long getRetrySpace() {
		public String getReason() {

		public void setServerId(String serverId) {
		public void setCount(int count) {
		public void setTime(long time) {
		public void setRechargeUserId(String rechargeUserId) {
		public void setUserId(String userId) {
		public void setIsError(boolean isError) {
		public void setLastProcessTime(long lastProcessTime) {
		public void setRetrySpace(long retrySpace) {
		public void setReason(String reason) {

			o.put("id", MongoGen.toObject(id));
			o.put("serverId", MongoGen.toObject(serverId));
			o.put("count", MongoGen.toObject(count));
			o.put("time", MongoGen.toObject(time));
			o.put("rechargeUserId", MongoGen.toObject(rechargeUserId));
			o.put("userId", MongoGen.toObject(userId));
			o.put("isError", MongoGen.toObject(isError));
			o.put("lastProcessTime", MongoGen.toObject(lastProcessTime));
			o.put("retrySpace", MongoGen.toObject(retrySpace));
			o.put("reason", MongoGen.toObject(reason));

			serverId = getString(o, "serverId");
			count = getInteger(o, "count");
			time = getLong(o, "time");
			rechargeUserId = getString(o, "rechargeUserId");
			userId = getString(o, "userId");
			isError = getBoolean(o, "isError");
			lastProcessTime = getLong(o, "lastProcessTime");
			retrySpace = getLong(o, "retrySpace");
			reason = getString(o, "reason");





















	
		private String serverId = "";
		private int count = 0;
		private long time = 0;
		private String rechargeUserId = "";
		private String userId = "";
		private boolean isError = false;
		private long lastProcessTime = 0;
		private long retrySpace = 0;
		private String reason = "";











		public String getServerId() {
		public int getCount() {
		public long getTime() {
		public String getRechargeUserId() {
		public String getUserId() {
		public boolean getIsError() {
		public long getLastProcessTime() {
		public long getRetrySpace() {
		public String getReason() {

		public void setServerId(String serverId) {
		public void setCount(int count) {
		public void setTime(long time) {
		public void setRechargeUserId(String rechargeUserId) {
		public void setUserId(String userId) {
		public void setIsError(boolean isError) {
		public void setLastProcessTime(long lastProcessTime) {
		public void setRetrySpace(long retrySpace) {
		public void setReason(String reason) {

			o.put("id", MongoGen.toObject(id));
			o.put("serverId", MongoGen.toObject(serverId));
			o.put("count", MongoGen.toObject(count));
			o.put("time", MongoGen.toObject(time));
			o.put("rechargeUserId", MongoGen.toObject(rechargeUserId));
			o.put("userId", MongoGen.toObject(userId));
			o.put("isError", MongoGen.toObject(isError));
			o.put("lastProcessTime", MongoGen.toObject(lastProcessTime));
			o.put("retrySpace", MongoGen.toObject(retrySpace));
			o.put("reason", MongoGen.toObject(reason));

			serverId = getString(o, "serverId");
			count = getInteger(o, "count");
			time = getLong(o, "time");
			rechargeUserId = getString(o, "rechargeUserId");
			userId = getString(o, "userId");
			isError = getBoolean(o, "isError");
			lastProcessTime = getLong(o, "lastProcessTime");
			retrySpace = getLong(o, "retrySpace");
			reason = getString(o, "reason");





















	
		private String name = "";
		private String gmScript = "";
		private List<ZoneDto> zones = Lists.newArrayList();





		public String getName() {
		public String getGmScript() {
		public List<ZoneDto> getZones() {

		public void setName(String name) {
		public void setGmScript(String gmScript) {
		public void setZones(List<ZoneDto> zones) {

			o.put("id", MongoGen.toObject(id));
			o.put("name", MongoGen.toObject(name));
			o.put("gmScript", MongoGen.toObject(gmScript));
			o.put("zones", MongoGen.toObjectZoneDto(zones));

			name = getString(o, "name");
			gmScript = getString(o, "gmScript");
			zones = loadZones(o);







		List<ZoneDto> loadZones(DBObject o) {

	
		private String value = "";



		public String getValue() {

		public void setValue(String value) {

			o.put("key", MongoGen.toObject(key));
			o.put("value", MongoGen.toObject(value));

			value = getString(o, "value");





	
		private String email = "";
		private String qQOpenId = "";
		private String pwdMD5 = "";
		private String nick = "";
		private boolean isMan = false;
		private int age = 0;
		private int vb = 0;









		public String getEmail() {
		public String getQQOpenId() {
		public String getPwdMD5() {
		public String getNick() {
		public boolean getIsMan() {
		public int getAge() {
		public int getVb() {

		public void setEmail(String email) {
		public void setQQOpenId(String qQOpenId) {
		public void setPwdMD5(String pwdMD5) {
		public void setNick(String nick) {
		public void setIsMan(boolean isMan) {
		public void setAge(int age) {
		public void setVb(int vb) {

			o.put("id", MongoGen.toObject(id));
			o.put("email", MongoGen.toObject(email));
			o.put("qQOpenId", MongoGen.toObject(qQOpenId));
			o.put("pwdMD5", MongoGen.toObject(pwdMD5));
			o.put("nick", MongoGen.toObject(nick));
			o.put("isMan", MongoGen.toObject(isMan));
			o.put("age", MongoGen.toObject(age));
			o.put("vb", MongoGen.toObject(vb));

			email = getString(o, "email");
			qQOpenId = getString(o, "qQOpenId");
			pwdMD5 = getString(o, "pwdMD5");
			nick = getString(o, "nick");
			isMan = getBoolean(o, "isMan");
			age = getInteger(o, "age");
			vb = getInteger(o, "vb");

















	
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











	
		private String name = "";
		private List<KeyValueDto> properties = Lists.newArrayList();
		private GameXmlFileDto gameXmlFile = null;
		private GameXmlFileDto clientXmlFile = null;






		public String getName() {
		public List<KeyValueDto> getProperties() {
		public GameXmlFileDto getGameXmlFile() {
		public GameXmlFileDto getClientXmlFile() {

		public void setName(String name) {
		public void setProperties(List<KeyValueDto> properties) {
		public void setGameXmlFile(GameXmlFileDto gameXmlFile) {
		public void setClientXmlFile(GameXmlFileDto clientXmlFile) {

			o.put("id", MongoGen.toObject(id));
			o.put("name", MongoGen.toObject(name));
			o.put("properties", MongoGen.toObjectKeyValueDto(properties));
			o.put("gameXmlFile", MongoGen.toObject(gameXmlFile));
			o.put("clientXmlFile", MongoGen.toObject(clientXmlFile));

			name = getString(o, "name");
			properties = loadProperties(o);
			DBObject gameXmlFile_dto = (DBObject) o.get("gameXmlFile");
			DBObject clientXmlFile_dto = (DBObject) o.get("clientXmlFile");







		List<KeyValueDto> loadProperties(DBObject o) {



