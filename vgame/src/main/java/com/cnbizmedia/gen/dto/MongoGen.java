
		public static OrderFinishDao getOrderFinishDao() {
		public static UserDao getUserDao() {

	
		public OrderDtoCursor findByServerId(String serverId) {
		public OrderDtoCursor findByCount(int count) {
		public OrderDtoCursor findByTime(long time) {
		public OrderDtoCursor findByRechargeUserId(String rechargeUserId) {
		public OrderDtoCursor findByUserId(String userId) {
		public OrderDtoCursor findByIsError(boolean isError) {
		public OrderDtoCursor findByLastProcessTime(long lastProcessTime) {
		public OrderDtoCursor findByRetrySpace(long retrySpace) {
		public OrderDtoCursor findByReason(String reason) {

	
		public OrderFinishDtoCursor findByServerId(String serverId) {
		public OrderFinishDtoCursor findByCount(int count) {
		public OrderFinishDtoCursor findByTime(long time) {
		public OrderFinishDtoCursor findByRechargeUserId(String rechargeUserId) {
		public OrderFinishDtoCursor findByUserId(String userId) {
		public OrderFinishDtoCursor findByIsError(boolean isError) {
		public OrderFinishDtoCursor findByLastProcessTime(long lastProcessTime) {
		public OrderFinishDtoCursor findByRetrySpace(long retrySpace) {
		public OrderFinishDtoCursor findByReason(String reason) {

	
		public UserDtoCursor findByEmail(String email) {
		public UserDtoCursor findByQQOpenId(String qQOpenId) {
		public UserDtoCursor findByPwdMD5(String pwdMD5) {
		public UserDtoCursor findByNick(String nick) {
		public UserDtoCursor findByIsMan(boolean isMan) {
		public UserDtoCursor findByAge(int age) {
		public UserDtoCursor findByVb(int vb) {

	
		private String serverId;
		private int count;
		private long time;
		private String rechargeUserId;
		private String userId;
		private boolean isError;
		private long lastProcessTime;
		private long retrySpace;
		private String reason;

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

		checkNull(serverId);
		checkNull(rechargeUserId);
		checkNull(userId);
		checkNull(reason);
		o.put("_id", id);
			o.put("id", id);
			o.put("serverId", serverId);
			o.put("count", count);
			o.put("time", time);
			o.put("rechargeUserId", rechargeUserId);
			o.put("userId", userId);
			o.put("isError", isError);
			o.put("lastProcessTime", lastProcessTime);
			o.put("retrySpace", retrySpace);
			o.put("reason", reason);

		serverId = getString(o, "serverId");
		count = getInteger(o, "count");
		time = getLong(o, "time");
		rechargeUserId = getString(o, "rechargeUserId");
		userId = getString(o, "userId");
		isError = getBoolean(o, "isError");
		lastProcessTime = getLong(o, "lastProcessTime");
		retrySpace = getLong(o, "retrySpace");
		reason = getString(o, "reason");

	
		private String serverId;
		private int count;
		private long time;
		private String rechargeUserId;
		private String userId;
		private boolean isError;
		private long lastProcessTime;
		private long retrySpace;
		private String reason;

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

		checkNull(serverId);
		checkNull(rechargeUserId);
		checkNull(userId);
		checkNull(reason);
		o.put("_id", id);
			o.put("id", id);
			o.put("serverId", serverId);
			o.put("count", count);
			o.put("time", time);
			o.put("rechargeUserId", rechargeUserId);
			o.put("userId", userId);
			o.put("isError", isError);
			o.put("lastProcessTime", lastProcessTime);
			o.put("retrySpace", retrySpace);
			o.put("reason", reason);

		serverId = getString(o, "serverId");
		count = getInteger(o, "count");
		time = getLong(o, "time");
		rechargeUserId = getString(o, "rechargeUserId");
		userId = getString(o, "userId");
		isError = getBoolean(o, "isError");
		lastProcessTime = getLong(o, "lastProcessTime");
		retrySpace = getLong(o, "retrySpace");
		reason = getString(o, "reason");

	
		private String email;
		private String qQOpenId;
		private String pwdMD5;
		private String nick;
		private boolean isMan;
		private int age;
		private int vb;

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

		checkNull(email);
		checkNull(qQOpenId);
		checkNull(pwdMD5);
		checkNull(nick);
		o.put("_id", id);
			o.put("id", id);
			o.put("email", email);
			o.put("qQOpenId", qQOpenId);
			o.put("pwdMD5", pwdMD5);
			o.put("nick", nick);
			o.put("isMan", isMan);
			o.put("age", age);
			o.put("vb", vb);

		email = getString(o, "email");
		qQOpenId = getString(o, "qQOpenId");
		pwdMD5 = getString(o, "pwdMD5");
		nick = getString(o, "nick");
		isMan = getBoolean(o, "isMan");
		age = getInteger(o, "age");
		vb = getInteger(o, "vb");

