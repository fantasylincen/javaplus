package mongo.gen;
	private long time;
	private int serverId;
	private String logHead;
	private String logText;

	public long getTime() {
	public int getServerId() {
	public String getLogHead() {
	public String getLogText() {

	public void setTime(long time) {
	public void setServerId(int serverId) {
	public void setLogHead(String logHead) {
	public void setLogText(String logText) {

		checkNull(logText);
		o.put("_id", ids);
		o.put("ids", ids);
		o.put("time", time);
		o.put("serverId", serverId);
		o.put("logHead", logHead);
		o.put("logText", logText);

		time = getLong(o, "time");
		serverId = getInteger(o, "serverId");
		logHead = getString(o, "logHead");
		logText = getString(o, "logText");
