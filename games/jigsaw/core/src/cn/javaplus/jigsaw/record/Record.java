package cn.javaplus.jigsaw.record;

import org.javaplus.game.common.IPreferences;

import cn.javaplus.jigsaw.App;

public class Record {

	private static Record record;

	public void load() {
		IPreferences p = App.getPreferences();
		p.getString("click-screen");
	}
	
	public static Record getRecord() {
		if(record == null)
			record = new Record();
		return record;
	}

}
