package cn.javaplus.game.power.assets;

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
		loadTexture("1024.png");
		loadTexture("128.png");
		loadTexture("16.png");
		loadTexture("2.png");
		loadTexture("2048.png");
		loadTexture("256.png");
		loadTexture("32.png");
		loadTexture("4.png");
		loadTexture("4096.png");
		loadTexture("512.png");
		loadTexture("64.png");
		loadTexture("8.png");
		loadTexture("8192.png");
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

	private FileHandle create(String fileName) {
		return Gdx.files.internal(assetsRoot + fileName);
	}

	public Image getImage(String pngName) {
		return new Image(getPngTexture(pngName));
	}
}
