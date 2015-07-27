package org.javaplus.game.common.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javaplus.game.common.actor.AnimationFrameImpl;
import org.javaplus.game.common.animation.AnimationFrame;
import org.javaplus.game.common.components.plist.Frame;
import org.javaplus.game.common.components.plist.FrameRect;
import org.javaplus.game.common.components.plist.Plist;
import org.javaplus.game.common.log.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

	public interface Loader {
		void loadTextureAtlas(String fileName);

		TextureAtlas getTextureAtlas(String filePath);

		FileHandle getFile(String filePath);

		TextureRegion findRegion(String path, String name);

		TextureRegion getPlistTextureRegion(String name, int index);

		BitmapFont getBitmapFont(String fntName);

		Texture getTexture(String filePath);

		void loadBitmapFont(String fileName);

		void loadTexture(String fileName);

		void finishLoading();

		float getProgress();

		void unload(String fileName);

		boolean update();
	}

	static Map<String, FileHandle> files = new HashMap<String, FileHandle>();
	static Map<String, FileHandle> filesWriteable = new HashMap<String, FileHandle>();
	static Map<String, Plist> plists = new HashMap<String, Plist>();
	private static Loader sd;
	private static Loader internal;

	public static void waitLoadingOver() {
		getInternal().finishLoading();
		getSd().finishLoading();
	}

	public static Loader getInternal() {
		if (internal == null)
			internal = new Internal();
		return internal;
	}

	public static Loader getSd() {
		if (sd == null)
			sd = new SDLoader();
		return sd;
	}

	/**
	 * @param filePath
	 *            相对于assets目录的路径
	 */
	public static Plist getPlist(String filePath) {
		Plist plist = plists.get(filePath);
		if (plist == null) {
			plist = new PlistImpl(filePath);
			plists.put(filePath, plist);
		}
		return plist;
	}

	public static boolean update() {
		boolean a = getInternal().update();
		boolean b = getSd().update();
		return a && b;
	}

	public static float getProgress() {
		float a = getInternal().getProgress();
		float b = getSd().getProgress();
		return (a + b) / 2;
	}

	public static void unload(String fileName) {
		Log.d("unload texture:" + fileName);
		getInternal().unload(fileName);
		getSd().unload(fileName);
	}

	static Skin skin;

	private static AnimationFrame buildFrame(Texture texture, Frame frame) {
		FrameRect rect = frame.getRect();
		int x = rect.getX();
		int y = rect.getY();
		int w = rect.getW();
		int h = rect.getH();

		TextureRegion r;
		if (frame.isRotated()) {
			r = new TextureRegion(texture, x, y, h, w);
		} else {
			r = new TextureRegion(texture, x, y, w, h);
		}
		return new AnimationFrameImpl(r, frame);
	}

	public static Skin getDefaultSkin() {
		if (skin == null) {
			skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		}
		return skin;
	}
	
	private static class Internal implements Loader {

		AssetManager manager = new AssetManager();

		public <T> void load(String fileName, Class<T> type) {
			if (!manager.isLoaded(fileName)) {
				manager.load(fileName, type);
				Log.d("load assets " + fileName + " type: " + type);
			}
		}

		@Override
		public void loadTexture(String fileName) {
			load(fileName, Texture.class);
		}

		@Override
		public void loadBitmapFont(String fntName) {
			load(fntName, BitmapFont.class);
		}

		@Override
		public void loadTextureAtlas(String filePath) {
			load(filePath, TextureAtlas.class);
		}

		/**
		 * @param filePath
		 *            相对于assets目录的路径
		 */
		@Override
		public FileHandle getFile(String filePath) {
			FileHandle path = files.get(filePath);
			if (path == null) {
				path = Gdx.files.internal(filePath);
				files.put(filePath, path);
			}
			return path;
		}

		/**
		 * @param filePath
		 *            相对于assets目录的路径
		 */
		@Override
		public Texture getTexture(String filePath) {
			return manager.get(filePath, Texture.class);
		}

		@Override
		public BitmapFont getBitmapFont(String fntName) {
			return manager.get(fntName, BitmapFont.class);
		}

		@Override
		public TextureRegion getPlistTextureRegion(String name, int index) {

			String head = name;

			if (head.endsWith(".png")) {
				int last = head.lastIndexOf(".png");
				head = head.substring(0, last);
			} else if (head.endsWith(".plist")) {
				int last = head.lastIndexOf(".plist");
				head = head.substring(0, last);
			}

			String plistPath = head + ".plist";
			String pngPath = head + ".png";

			Plist plist = getPlist(plistPath);
			Texture texture = getTexture(pngPath);

			List<Frame> pfs = plist.getFrames();
			AnimationFrame frame = buildFrame(texture, pfs.get(index));

			return frame.getTextureRegion();
		}

		@Override
		public TextureRegion findRegion(String path, String name) {
			return getTextureAtlas(path).findRegion(name);
		}

		@Override
		public TextureAtlas getTextureAtlas(String filePath) {
			return manager.get(filePath, TextureAtlas.class);
		}

		@Override
		public void finishLoading() {
			manager.finishLoading();
		}

		@Override
		public float getProgress() {
			return manager.getProgress();
		}

		@Override
		public void unload(String fileName) {
			manager.unload(fileName);
		}

		@Override
		public boolean update() {
			return manager.update();
		}

	}

	private static class SDLoader implements Loader {

		AssetManager manager = new AssetManager(new LocalFileHandleResolver());

		public <T> void load(String fileName, Class<T> type) {

//			String path = getSdPath(fileName);
//			Log.d("load assets sd " + path + " type: " + type);

			if (!manager.isLoaded(fileName)) {
				manager.load(fileName, type);
				manager.finishLoading();
//				Log.d("load assets sd success " + path + " type: " + type);
			}
		}

		@Override
		public void loadTexture(String fileName) {
			load(fileName, Texture.class);
		}

		@Override
		public void loadBitmapFont(String fntName) {
			load(fntName, BitmapFont.class);
		}

		@Override
		public void loadTextureAtlas(String filePath) {
			load(filePath, TextureAtlas.class);
		}

		@Override
		public FileHandle getFile(String key) {
			FileHandle f = files.get(key);
			if (f == null) {
				f = Gdx.files.local(key);
				files.put(key, f);
			}
			return f;
		}

		@Override
		public Texture getTexture(String filePath) {
			return manager.get(filePath, Texture.class);
		}

		@Override
		public BitmapFont getBitmapFont(String fntName) {
			return manager.get(fntName, BitmapFont.class);
		}

		@Override
		public TextureRegion getPlistTextureRegion(String name, int index) {

			String head = name;

			if (head.endsWith(".png")) {
				int last = head.lastIndexOf(".png");
				head = head.substring(0, last);
			} else if (head.endsWith(".plist")) {
				int last = head.lastIndexOf(".plist");
				head = head.substring(0, last);
			}

			String plistPath = head + ".plist";
			String pngPath = head + ".png";

			Plist plist = getPlist(plistPath);
			Texture texture = getTexture(pngPath);

			List<Frame> pfs = plist.getFrames();
			AnimationFrame frame = buildFrame(texture, pfs.get(index));

			return frame.getTextureRegion();
		}

		@Override
		public TextureRegion findRegion(String path, String name) {
			TextureAtlas t = getTextureAtlas(path);
			return t.findRegion(name);
		}

		@Override
		public TextureAtlas getTextureAtlas(String filePath) {
			return manager.get(filePath, TextureAtlas.class);
		}

		@Override
		public void finishLoading() {
			manager.finishLoading();
		}

		@Override
		public float getProgress() {
			return manager.getProgress();
		}

		@Override
		public void unload(String fileName) {
			manager.unload(fileName);
		}

		@Override
		public boolean update() {
			return manager.update();
		}
	}
}
