package cn.javaplus.clickscreen.script;

import java.util.HashMap;
import java.util.Map;

import org.javaplus.game.common.assets.Assets;

import com.badlogic.gdx.files.FileHandle;

public class Scripts {

	private static Map<String, String> scripts = new HashMap<String, String>();

	public static String get(String fileName) {
		fileName = "scripts/" + fileName;
		if (scripts.get(fileName) == null) {
			FileHandle file = Assets.getSd().getFile(fileName);
			scripts.put(fileName, file.readString());
		}
		return scripts.get(fileName);
	}

}
