package cn.javaplus.game.defender.assets;

import aurelienribon.bodyeditor.BodyEditorLoader;
import cn.javaplus.common.util.Util;
import cn.javaplus.game.defender.App;
import cn.javaplus.game.defender.physical.PhysicalActorImpl;
import cn.javaplus.game.defender.stage.PhysicalActor;
import cn.javaplus.game.defender.stage.StageToWorldPositionAdaptor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.IBody;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class Assets {

	private static String assetsRoot = "";
	
	private static AssetManager manager = new AssetManager();

	public static void setAssetsRoot(String assetsRoot) {
		Assets.assetsRoot = assetsRoot;
	}

	public static FileHandle create(String fileName) {
//		manager.load(fileName, FileHandle.class);
//		return manager.get(fileName, FileHandle.class);
		return Gdx.files.internal(assetsRoot + fileName);
	}
//
//	public static FileHandle createPng(String fileName) {
//		return create(fileName + ".png");
//	}

	// public static FileHandle getTmx(String fileName) {
	// return get(fileName + ".tmx");
	// }

	public static Texture createPngTexture(String fileName) {
		String n = assetsRoot + fileName + ".png";
		Class<Texture> c = Texture.class;
		manager.load(n, c);
		Util.Thread.sleep(100);
		return manager.get(n, c);
//		FileHandle png = createPng(fileName);
//		return new Texture(png);
	}
	
	public static void update() {
		manager.update();
	}

	// public static TiledMap getTmxMap(String mapName) {
	// TmxMapLoader l = new TmxMapLoader();
	// TiledMap load = l.load(assetsRoot + mapName + ".tmx");
	// return load;
	// }

	public static TextureRegionDrawable createPngTextureRegionDrawable(String name) {

		Texture pngTexture = Assets.createPngTexture(name);
		TextureRegion r = new TextureRegion(pngTexture);
		TextureRegionDrawable t = new TextureRegionDrawable(r);
		return t;
	}

	// /**
	// * physics.json中定义的物体
	// *
	// * @param name
	// * @return
	// */
	// public static RigidBodyModel getRigidBodyModel(String name) {
	// BodyEditorLoader loader = getBodyEditorLoader();
	// return loader.getInternalModel().rigidBodies.get(name);
	// }

	public static Image createImage(String pngName) {
		Texture png = createPngTexture(pngName);
		return new Image(png);
	}

	/**
	 * 带有物理效果的png图片
	 * 
	 * @param name
	 *            physics.json中定义的物体的名字
	 * @return
	 */
	public static PhysicalActor createPhysicalImage(String name) {
		return createPhysicalActor(name, 0, 0, 1);
	}

	public static Animation createAnimation(float d, String... names) {
	
		Array<TextureRegion> keyFrames = new SnapshotArray<TextureRegion>();
		for (String name : names) {
			keyFrames.add(createSprite(name));
		}
		Animation a = new Animation(d, keyFrames);
		return a;
	}

	public static PhysicalActor createPhysicalActor(String name, int x, int y) {
		return createPhysicalActor(name, x, y, 1);
	}

	public static PhysicalActor createPhysicalActor(String name, float x,
			float y, float scale) {
		IBody body = createBody(x, y);
	
		NormalFixtureDef fd = new NormalFixtureDef();
	
		Sprite s = createSpriteByImagePathInJson(name);
		BodyEditorLoader loader = getBodyEditorLoader();
		s.setSize(s.getWidth() * scale, s.getHeight() * scale);
		float max = Math.max(s.getHeight(), s.getWidth());
	
		float k = getScale(scale, max);
		loader.attachFixture(body, name, fd, k);
		Vector2 origin = loader.getOrigin(name, 1).cpy();
	
		setOrigin(scale, s, origin);
	
		s.setOrigin(origin.getX(), origin.getY());
	
		return new PhysicalActorImpl(body, s);
	}

	public static PhysicalActor createPhysicalActor(String name, Animation a,
			float x, float y, float scale) {
		IBody body = createBody(x, y);

		NormalFixtureDef fd = new NormalFixtureDef();

		BodyEditorLoader loader = getBodyEditorLoader();

		TextureRegion s = a.getKeyFrames()[0];
		int w = s.getRegionWidth();
		int h = s.getRegionHeight();
		float max = Math.max(w, h);

		float k = getScale(scale, max);
		loader.attachFixture(body, name, fd, k);
		Vector2 origin = loader.getOrigin(name, 1).cpy();

		setOrigin(scale, s, origin);

		return new PhysicalActorImpl(body, a, origin, scale);
	}

	public static Sprite createSprite(String pngName) {
		Texture t = createPngTexture(pngName);
		return new Sprite(t);
	}

	// /**
	// * physics.json中定义的物体
	// *
	// * @param name
	// * @return
	// */
	// public static RigidBodyModel getRigidBodyModel(String name) {
	// BodyEditorLoader loader = getBodyEditorLoader();
	// return loader.getInternalModel().rigidBodies.get(name);
	// }
	
	private static BodyEditorLoader getBodyEditorLoader() {
		FileHandle fh = create("physics.json");
		String text = fh.readString();
		BodyEditorLoader d = new BodyEditorLoader(text);
		return d;
	}

	private static Sprite createSpriteByImagePathInJson(String name) {
		BodyEditorLoader loader = getBodyEditorLoader();
		String path = loader.getImagePath(name);
		FileHandle f = create(path);
		return new Sprite(new Texture(f));
	}

	private static void setOrigin(float scale, TextureRegion s, Vector2 origin) {
		origin.setX(origin.getX() * s.getRegionWidth() * scale);
		origin.setY(origin.getY() * s.getRegionHeight() * scale);
	}

	private static float getScale(float scale, float max) {
		return max / 198 * scale;
	}

	private static IBody createBody(float x, float y) {
		NormalBodyDef def = new NormalBodyDef();

		Vector2 p = new StageToWorldPositionAdaptor(x, y);
		def.getPosition().set(p);
		IBody body = App.getWorld().createBody(def);
		return body;
	}
}
