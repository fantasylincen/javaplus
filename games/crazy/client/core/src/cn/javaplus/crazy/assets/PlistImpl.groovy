package cn.javaplus.crazy.assets;

import java.io.IOException;
import java.util.List;

import cn.javaplus.crazy.collections.Lists;
import cn.javaplus.crazy.components.plist.Frame;
import cn.javaplus.crazy.components.plist.FrameOffset;
import cn.javaplus.crazy.components.plist.FrameRect;
import cn.javaplus.crazy.components.plist.Plist;
import cn.javaplus.crazy.components.plist.Size;
import cn.javaplus.crazy.util.StringResolver;
import cn.javaplus.exception.GameException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

class PlistImpl implements Plist {

	List<Frame> frames = Lists.newArrayList();

	PlistImpl(String filePath) {
		XmlReader reader = new XmlReader();
		try {
			FileHandle f = Assets.getFile(filePath);
			Element e = reader.parse(f);
			Element dict = e.getChildByName("dict").getChildByName("dict");
			int c = dict.getChildCount();
			for (int i = 0; i < c; i += 2) {
				Element key = dict.getChild(i);
				Element value = dict.getChild(i + 1);
				frames.add(buildFrame(key, value));
			}
		} catch (IOException e) {
			throw new GameException("plist file error:" + filePath);
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see cn.javaplus.crazy.plist.Plist#getFrames()
	 */
	@Override
	public List<Frame> getFrames() {
		return frames;
	}

	private FrameImpl buildFrame(Element key, Element value) {
		String k = key.getText();
		Element child = value.getChild(1);
		FrameRect rect = buildRect(child.getText());
		FrameOffset offset = buildFrameOffSet(value.getChild(3).getText());
		boolean isRotated = value.getChild(5).getName().equals("true");
		FrameRect sourceColorRect = buildRect(value.getChild(7).getText());
		Size sourceSize = buildSourceSize(value.getChild(9).getText());

		return new FrameImpl(k, rect, offset, isRotated, sourceColorRect,
				sourceSize);
	}

	private Size buildSourceSize(String text) {
		text = delete(text, "\\{", "\\}");
		StringResolver sr = new StringResolver(text);
		List<StringResolver> sss = sr.split(",");
		return new SizeImpl(sss.get(0).getInt(), sss.get(1).getInt());
	}

	private String delete(String text, String... s) {
		for (String string : s) {
			text = text.replaceAll(string, "");
		}
		return text;
	}

	private FrameRect buildRect(String text) {
		text = delete(text, "\\{", "\\}");
		StringResolver sr = new StringResolver(text);
		List<StringResolver> sss = sr.split(",");
		int x = sss.get(0).getInt();
		int y = sss.get(1).getInt();
		int w = sss.get(2).getInt();
		int h = sss.get(3).getInt();
		return new FrameRectImpl(x, y, w, h);
	}

	private FrameOffset buildFrameOffSet(String text) {
		text = delete(text, "\\{", "\\}");
		StringResolver sr = new StringResolver(text);
		List<StringResolver> sss = sr.split(",");
		return new FrameOffsetImpl(sss.get(0).getInt(), sss.get(1).getInt());
	}

}

// <plist version="1.0">
// <dict>
// <key>frames</key>
// <dict>
// <key>ab_04_attack_0_0000.png</key>
// <dict>
// <key>frame</key>
// <string>{{168,517},{80,67}}</string>
// <key>offset</key>
// <string>{2,15}</string>
// <key>rotated</key>
// <false/>
// <key>sourceColorRect</key>
// <string>{{137,83},{80,67}}</string>
// <key>sourceSize</key>
// <string>{350,263}</string>
// </dict>
// <key>ab_04_attack_0_0001.png</key>
// <dict>
// <key>frame</key>
// <string>{{418,556},{78,59}}</string>
// <key>offset</key>
// <string>{-1,35}</string>
// <key>rotated</key>
// <false/>
// <key>sourceColorRect</key>
// <string>{{135,67},{78,59}}</string>
// <key>sourceSize</key>
// <string>{350,263}</string>
// </dict>