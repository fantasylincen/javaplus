package cn.javaplus.crazy.assets;

import cn.javaplus.crazy.actor.AnimationFrameImpl
import cn.javaplus.crazy.animation.AnimationFrame
import cn.javaplus.crazy.components.plist.Frame
import cn.javaplus.crazy.components.plist.FrameRect
import cn.javaplus.crazy.components.plist.Plist
import cn.javaplus.crazy.log.Log

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin

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
		Log.d("load texture:" + fileName);
		load(fileName, Texture.class);
	}

	public static void loadBitmapFont(String fntName) {
		load(fntName, BitmapFont.class);
	}

	public static void waitLoadingOver() {
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
	
	public static void loadTextureAtlas(String filePath) {
		manager.load(filePath, TextureAtlas.class);
	}

	public static boolean update() {
		return manager.update();
	}

	public static float getProgress() {
		return manager.getProgress();
	}

	public static void unload(String fileName) {
		Log.d("unload texture:" + fileName);
		manager.unload(fileName);
	}

	public static Image createImage(String path) {
		return new Image(getTexture(path));
	}

	static Skin skin;

//	public static Skin getDefualtSkin() {
//		if (skin == null)
//			skin = new Skin(Gdx.files.internal("data/uiskin.json"));
//
//		return skin;
//	}

	public static TextureRegion getPlistTextureRegion(String name, int index) {

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

		Plist plist = Assets.getPlist(plistPath);
		Texture texture = Assets.getTexture(pngPath);

		List<Frame> pfs = plist.getFrames();
		AnimationFrame frame = buildFrame(texture, pfs.get(index));

		return frame.getTextureRegion();
	}

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

}
