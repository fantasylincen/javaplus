package cn.mxz.base.servertask;

import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManagerImpl;


public class OnlineSizeManager  {

	private static OnlineSizeManager	instance;
	private int	onlineMaxToday;
	private int	onlineMaxHistory;
	private int	onlineSize;

	private OnlineSizeManager() {
		KeyValueManagerImpl kv = new KeyValueManagerImpl();
		String his = kv.get(KeyValueDefine.ONLINE_SIZE_MAX_HISTORY);
		String tod = kv.get(KeyValueDefine.ONLINE_SIZE_MAX_TODAY);

		onlineMaxToday = new Integer(tod);
		onlineMaxHistory = new Integer(his);
	}

	public static final OnlineSizeManager getInstance() {
		if (instance == null) {
			instance = new OnlineSizeManager();
		}
		return instance;
	}

	private void saveToDB() {
		KeyValueManagerImpl kv = new KeyValueManagerImpl();
		kv.put(KeyValueDefine.ONLINE_SIZE_MAX_HISTORY, onlineMaxHistory);
		kv.put(KeyValueDefine.ONLINE_SIZE_MAX_TODAY, onlineMaxToday);
		kv.put(KeyValueDefine.ONLINE_SIZE_NOW, onlineSize);
	}

	public void adjust(int size) {

		onlineSize = size;

		if(onlineMaxToday < size) {

			onlineMaxToday = size;
		}

		if(onlineMaxHistory < onlineMaxToday) {

			onlineMaxHistory = onlineMaxToday;
		}
		
		saveToDB();
	}

}
