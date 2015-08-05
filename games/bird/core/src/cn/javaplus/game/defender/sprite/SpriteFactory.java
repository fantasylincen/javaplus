//package cn.javaplus.game.defender.sprite;
//
//import java.util.List;
//
//import org.andengine.entity.sprite.TiledSprite;
//import org.andengine.opengl.texture.ITexture;
//import org.andengine.opengl.texture.region.ITextureRegion;
//import org.andengine.opengl.texture.region.TextureRegion;
//import org.andengine.opengl.texture.region.TiledTextureRegion;
//
//import cn.javaplus.game.defender.base.AssetsFactory;
//import cn.javaplus.game.defender.json.Frame;
//import cn.javaplus.game.defender.json.FrameFactory;
//import cn.javaplus.game.defender.textture.TexttureFactory;
//
//public class SpriteFactory extends AssetsFactory{
//	
//	private static SpriteFactory instance;
//	
//	public static final SpriteFactory getInstance() {
//		if(instance == null) {
//			instance = new SpriteFactory();
//		}
//		return instance;
//	}
//	
//	private SpriteFactory () {
//		
//	}
//	
//
//	/**
//	 * 创建一个动画, 该动画必须有一个json文件和一个png文件
//	 * @param name			动画图片名字， 不包含文件后缀名（.png   但必须是以png结尾的图片文件）
//	 * @param activity
//	 * @return
//	 */
//	public TiledSprite createSprite(String name){
//		
//		FrameFactory.getInstance().setAssetBasePath(assetsPath);
//		List<Frame> frames = FrameFactory.getInstance().getFrames(name);
//		
//		name = getFilePath(name);
//		
//		ITexture t = TexttureFactory.getInstance().getTextTure(name);
//		
//		ITextureRegion[] ts = new ITextureRegion[frames.size()];
//		for (int i = 0; i < ts.length; i++) {
//			Frame fr = frames.get(i);
//			ts[i] = new TextureRegion(t, fr.getX(), fr.getY(), fr.getWidth(), fr.getHeight(), fr.isRotated());
//		}
//		TiledTextureRegion ttr = new TiledTextureRegion(t, false, ts);
//		return new SpriteAutoDisplay(ttr);
//	}
//	
//	/**
//	 * 创建一个动画, 该动画必须有一个json文件和一个png文件
//	 */
//	public TiledSprite createSprite(int jsonId, int pngId){
//		
//		FrameFactory.getInstance().setAssetBasePath(assetsPath);
//		List<Frame> frames = FrameFactory.getInstance().getFrames(jsonId);
//		
//		ITexture t = TexttureFactory.getInstance().getTextTure(pngId);
//		
//		ITextureRegion[] ts = new ITextureRegion[frames.size()];
//		for (int i = 0; i < ts.length; i++) {
//			Frame fr = frames.get(i);
//			ts[i] = new TextureRegion(t, fr.getX(), fr.getY(), fr.getWidth(), fr.getHeight(), fr.isRotated());
//		}
//		TiledTextureRegion ttr = new TiledTextureRegion(t, false, ts);
//		return new SpriteAutoDisplay(ttr);
//	}
//	
//	/**
//	 * 创建一个动画, 该动画必须有一个json文件和一个png文件
//	 * @param name			动画图片名字， 不包含文件后缀名（.png   但必须是以png结尾的图片文件）
//	 * @param activity
//	 * @return
//	 */
//	public TiledSprite createSprite(List<Frame> frames, ITexture t){
//		ITextureRegion[] ts = new ITextureRegion[frames.size()];
//		for (int i = 0; i < ts.length; i++) {
//			Frame fr = frames.get(i);
//			ts[i] = new TextureRegion(t, fr.getX(), fr.getY(), fr.getWidth(), fr.getHeight(), fr.isRotated());
//		}
//		TiledTextureRegion ttr = new TiledTextureRegion(t, false, ts);
//		return new SpriteAutoDisplay(ttr);
//	}
//	
//	public TiledTextureRegion create(final ITexture pTexture,
//			final int pTextureX, final int pTextureY, final int pTextureWidth,
//			final int pTextureHeight, final int pTileColumns,
//			final int pTileRows, final boolean pRotated) {
//		final ITextureRegion[] textureRegions = new ITextureRegion[pTileColumns * pTileRows];
//
//		final int tileWidth = pTextureWidth / pTileColumns;
//		final int tileHeight = pTextureHeight / pTileRows;
//
//		for (int tileColumn = 0; tileColumn < pTileColumns; tileColumn++) {
//			for (int tileRow = 0; tileRow < pTileRows; tileRow++) {
//				final int tileIndex = tileRow * pTileColumns + tileColumn;
//
//				final int x = pTextureX + tileColumn * tileWidth;
//				final int y = pTextureY + tileRow * tileHeight;
//				textureRegions[tileIndex] = new TextureRegion(pTexture, x, y, tileWidth, tileHeight, pRotated);
//			}
//		}
//
//		return new TiledTextureRegion(pTexture, false, textureRegions);
//	}
//}
