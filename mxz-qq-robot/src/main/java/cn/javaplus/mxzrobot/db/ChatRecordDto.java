package cn.javaplus.mxzrobot.db;
	private String talker;
	private String content;

	public String getTalker() {
	public String getContent() {

	public void setTalker(String talker) {
	public void setContent(String content) {

		checkNull(talker);
		checkNull(content);

		o.put("time", time);
		o.put("talker", talker);
		o.put("content", content);

		talker = (String) o.get("talker");
		content = (String) o.get("content");
