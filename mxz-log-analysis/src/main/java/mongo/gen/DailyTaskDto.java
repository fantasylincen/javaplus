package mongo.gen;
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
