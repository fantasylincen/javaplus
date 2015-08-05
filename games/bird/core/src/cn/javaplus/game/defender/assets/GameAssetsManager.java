package cn.javaplus.game.defender.assets;

import aurelienribon.bodyeditor.BodyEditorLoader;
import cn.javaplus.game.defender.App;
import cn.javaplus.game.defender.physical.PhysicalActorImpl;
import cn.javaplus.game.defender.stage.PhysicalActor;
import cn.javaplus.game.defender.stage.StageToWorldPositionAdaptor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.IBody;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.SnapshotArray;

public class GameAssetsManager {

	private String assetsRoot = "";

	private static AssetManager manager = new AssetManager();

	public void setAssetsRoot(String assetsRoot) {
		this.assetsRoot = assetsRoot;
	}

	public <T> T get(String fileName) {
		return manager.get(assetsRoot + fileName);
	}

	public <T> T get(String fileName, Class<T> type) {
		return manager.get(assetsRoot + fileName, type);
	}

	public Texture getTexture(String fileName) {
		return get(fileName, Texture.class);
	}

	public Texture getPngTexture(String fileName) {
		return get(fileName + ".png", Texture.class);
	}

	public <T> T get(AssetDescriptor<T> assetDescriptor) {
		return manager.get(assetDescriptor);
	}

	public void unload(String fileName) {
		manager.unload(assetsRoot + fileName);
	}

	public <T> boolean containsAsset(T asset) {
		return manager.containsAsset(asset);
	}

	public <T> String getAssetFileName(T asset) {
		return manager.getAssetFileName(asset);
	}

	public boolean isLoaded(String fileName) {
		return manager.isLoaded(assetsRoot + fileName);
	}

	public boolean isLoaded(String fileName, Class type) {
		return manager.isLoaded(assetsRoot + fileName, type);
	}

	public <T> AssetLoader getLoader(Class<T> type) {
		return manager.getLoader(type);
	}

	public <T> AssetLoader getLoader(Class<T> type, String fileName) {
		return manager.getLoader(type, assetsRoot + fileName);
	}

	public <T> void load(String fileName, Class<T> type) {
		manager.load(assetsRoot + fileName, type);
	}

	public <T> void load(String fileName, Class<T> type,
			AssetLoaderParameters<T> parameter) {
		manager.load(assetsRoot + fileName, type, parameter);
	}

	public void load(AssetDescriptor desc) {
		manager.load(desc);
	}

	public boolean update() {
		return manager.update();
	}

	public boolean update(int millis) {
		return manager.update(millis);
	}

	public void finishLoading() {
		manager.finishLoading();
	}

	public <T, P extends AssetLoaderParameters<T>> void setLoader(
			Class<T> type, AssetLoader<T, P> loader) {
		manager.setLoader(type, loader);
	}

	public <T, P extends AssetLoaderParameters<T>> void setLoader(
			Class<T> type, String suffix, AssetLoader<T, P> loader) {
		manager.setLoader(type, suffix, loader);
	}

	public int getLoadedAssets() {
		return manager.getLoadedAssets();
	}

	public int getQueuedAssets() {
		return manager.getQueuedAssets();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public void setErrorListener(AssetErrorListener listener) {
		manager.setErrorListener(listener);
	}

	public void dispose() {
		manager.dispose();
	}

	public void clear() {
		manager.clear();
	}

	public Logger getLogger() {
		return manager.getLogger();
	}

	public int getReferenceCount(String fileName) {
		return manager.getReferenceCount(assetsRoot + fileName);
	}

	public String getDiagnostics() {
		return manager.getDiagnostics();
	}

	public Array<String> getAssetNames() {
		return manager.getAssetNames();
	}

	public Array<String> getDependencies(String fileName) {
		return manager.getDependencies(assetsRoot + fileName);
	}

	public void setReferenceCount(String fileName, int refCount) {
		manager.setReferenceCount(assetsRoot + fileName, refCount);
	}

	public Class getAssetType(String fileName) {
		return manager.getAssetType(assetsRoot + fileName);
	}

	public String toString() {
		return manager.toString();
	}

	public void load() {
		loadTexture("bird1.png");
		loadTexture("bird2.png");
		loadTexture("bird3.png");
		loadTexture("city.png");
		loadTexture("ground.png");
		loadTexture("trees.png");
		loadTexture("image.png");
		loadTexture("bullet.png");
	}

	private void loadTexture(String fileName) {
		load(fileName, Texture.class);
	}

	public Animation getAnimation(float frameDuration, String... fileNames) {
		Array<TextureRegion> keyFrames = new SnapshotArray<TextureRegion>();
		for (String name : fileNames) {
			keyFrames.add(getSprite(name));
		}
		Animation a = new Animation(frameDuration, keyFrames);
		return a;
	}

	public Sprite getSprite(String name) {
		return new Sprite(getTexture(name));
	}

	public PhysicalActor getPhysicalActor(String name, int x, int y) {
		return getPhysicalActor(name, x, y, 1);
	}

	public PhysicalActor getPhysicalActor(String name, float x, float y,
			float scale) {
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

	public PhysicalActor getPhysicalActor(String name, Animation a, float x,
			float y, float scale) {
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

	private BodyEditorLoader getBodyEditorLoader() {
		FileHandle fh = create("physics.json");
		String text = fh.readString();
		return new BodyEditorLoader(text);
	}

	private FileHandle create(String fileName) {
		return Gdx.files.internal(assetsRoot + fileName);
	}

	private Sprite createSpriteByImagePathInJson(String name) {
		BodyEditorLoader loader = getBodyEditorLoader();
		String path = loader.getImagePath(name);
		FileHandle f = create(path);
		return new Sprite(new Texture(f));
	}

	private void setOrigin(float scale, TextureRegion s, Vector2 origin) {
		origin.setX(origin.getX() * s.getRegionWidth() * scale);
		origin.setY(origin.getY() * s.getRegionHeight() * scale);
	}

	private float getScale(float scale, float max) {
		return max / 198 * scale;
	}

	private IBody createBody(float x, float y) {
		NormalBodyDef def = new NormalBodyDef();

		Vector2 p = new StageToWorldPositionAdaptor(x, y);
		def.getPosition().set(p);
		IBody body = App.getWorld().createBody(def);
		return body;
	}

	public Image getImage(String pngName) {
		return new Image(getPngTexture(pngName));
	}
}
