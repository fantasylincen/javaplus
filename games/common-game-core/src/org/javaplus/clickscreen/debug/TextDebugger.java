package org.javaplus.clickscreen.debug;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.assets.Assets.Loader;
import org.javaplus.game.common.log.Out;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 调试工具 在屏幕上输出Debug信息
 */
public class TextDebugger extends Actor implements Out {

	public class MyStringWriter extends StringWriter {
		@Override
		public StringWriter append(char arg0) {
			StringWriter append = super.append(arg0);
			ensureLimit();
			return append;
		}

		@Override
		public StringWriter append(CharSequence arg0, int arg1, int arg2) {
			StringWriter append = super.append(arg0, arg1, arg2);
			ensureLimit();
			return append;
		}

		@Override
		public StringWriter append(CharSequence arg0) {
			StringWriter append = super.append(arg0);
			ensureLimit();
			return append;
		}

		@Override
		public void write(char[] arg0, int arg1, int arg2) {
			super.write(arg0, arg1, arg2);
			ensureLimit();
		}

		@Override
		public void write(int arg0) {
			super.write(arg0);
			ensureLimit();
		}

		@Override
		public void write(String arg0, int arg1, int arg2) {
			super.write(arg0, arg1, arg2);
			ensureLimit();
		}

		@Override
		public void write(String arg0) {
			super.write(arg0);
			ensureLimit();
		}

	}

	private BitmapFont font;
	static StringWriter texts = new StringWriter();
	private PrintWriter out;
	private static final int LIMIT = 4000;
	private Loader loader = Assets.getInternal();

	public void setLoader(Loader loader) {
		this.loader = loader;
	}
	public TextDebugger() {
		texts = new MyStringWriter();
		out = new PrintWriter(texts);
		font = loader.getBitmapFont("fonts.fnt");
	}

	private void ensureLimit() {
		StringBuffer bf = texts.getBuffer();
		if (bf.length() > LIMIT) {
			bf.delete(0, bf.length() - LIMIT);
		}
	}

	@Override
	public void draw(Batch batch, float delta) {
		String text = texts.toString();

		font.draw(batch, text, 0, getY(text));
	}

	private float getY(String text) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {

			char c = text.charAt(i);
			if (c == '\n') {
				count++;
			}
		}
		float y = (count + 1) * font.getLineHeight();
		return y;
	}

	public int getTextSize() {
		return texts.getBuffer().length();
	}

	public PrintWriter getOut() {
		return out;
	}

	@Override
	public void println(Object... message) {
		out.println(Util.Collection.linkWith("", message));
	}
}
