package cn.javaplus.shhz.assets;

import java.util.HashMap;
import java.util.Map;

import cn.javaplus.shhz.components.plist.Plist;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Assets {

	static AssetManager manager = new AssetManager();
	static Map<String, FileHandle> files = new HashMap<String, FileHandle>();
	static Map<String, FileHandle> filesWriteable = new HashMap<String, FileHandle>();
	static Map<String, Plist> plists = new HashMap<String, Plist>();

	static {
		// manager.setLoader(type, loader)
	}

	public static <T> void load(String fileName, Class<T> type) {
		manager.load(fileName, type);
	}

	public static void loadTexture(String fileName) {
		load(fileName, Texture.class);
	}

	public static void loadBitmapFont(String fntName) {
		load(fntName, BitmapFont.class);
	}

	public static void finishLoading() {
		manager.finishLoading();
	}

	/**
	 * @param filePath
	 *            相对于assets目录的路径
	 */
	public static Texture getTexture(String filePath) {
		return manager.get(filePath, Texture.class);
	}

	public static BitmapFont getBitmapFont(String fntName) {
		return manager.get(fntName, BitmapFont.class);
	}

	/**
	 * @param filePath
	 *            相对于assets目录的路径
	 */
	public static FileHandle getFile(String filePath) {
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
	public static Plist getPlist(String filePath) {
		Plist plist = plists.get(filePath);
		if (plist == null) {
			plist = new PlistImpl(filePath);
			plists.put(filePath, plist);
		}
		return plist;
	}

	public static TextureAtlas getTextureAtlas(String filePath) {
		return manager.get(filePath, TextureAtlas.class);
	}

	public static boolean update() {
		return manager.update();
	}

	public static float getProgress() {
		return manager.getProgress();
	}

	public static void unload(String fileName) {
		manager.unload(fileName);
	}

	public static Image createImage(String path) {
		return new Image(getTexture(path));
	}
}
