package org.javaplus.clickscreen.excel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.javaplus.game.common.assets.Assets.Loader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XmlDataImpl implements StaticData {
	public XmlDataImpl(Loader loader) {
		FileHandle file = loader.getFile("data/game.xml");
		XmlReader r = new XmlReader();
		Element root;
		try {
			root = r.parse(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Array<Element> ss = root.getChildrenByName("Worksheet");

		for (Element s : ss) {
			String attribute = s.getAttribute("ss:Name");
			map.put(attribute, new XmlSheetImpl(s));
		}
	}

	private Map<String, Sheet> map = new HashMap<String, Sheet>();

	@Override
	public Sheet get(String name) {
		Sheet value = map.get(name);
		return value;
	}

}
