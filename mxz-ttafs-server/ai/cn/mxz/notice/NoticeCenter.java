package cn.mxz.notice;

import java.util.List;

import com.google.common.collect.Lists;

public class NoticeCenter {

	private static NoticeCenter	instance;
	private List<NoticeItem>	items	= Lists.newArrayList();

	private NoticeCenter() {
		loadItems();
	}

	private void loadItems() {
//		KeyValueCollection c = KeyValueCollectionFactory.getMySqlCollection();
//		c.
	}

	public static final NoticeCenter getInstance() {
		if (instance == null) {
			instance = new NoticeCenter();
		}
		return instance;
	}

	public List<NoticeItem> getItems() {
		return items;
	}

}
