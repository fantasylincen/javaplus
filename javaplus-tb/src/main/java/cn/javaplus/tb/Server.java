package cn.javaplus.tb;

public class Server {
	public static RecordManager manager;
	public static RecordManager getRecordManager() {
		if(manager == null)
			manager = new RecordManager();
		return manager;
	}
}
