//package cn.javaplus.game.defender.sprite;
//
//import org.andengine.engine.handler.IUpdateHandler;
//import org.andengine.entity.sprite.TiledSprite;
//import org.andengine.opengl.texture.region.ITiledTextureRegion;
//
//import cn.javaplus.game.defender.AndroidApp;
//
///**
// * 自动循环播放的精灵
// */
//public class SpriteAutoDisplay extends TiledSprite {
//
//	public SpriteAutoDisplay(ITiledTextureRegion p) {
//		super(0, 0, p, AndroidApp.getDefault().getVertexBufferObjectManager());
//		this.registerUpdateHandler(new ToNextFrameHandler());
//	}
//
//	private class ToNextFrameHandler implements IUpdateHandler{
//
//		/**
//		 * 下一帧动画
//		 */
//		private void nextFrame() {
//			int index = getCurrentTileIndex() + 1;
//			if(index >= getTileCount()) {
//				index = 0;
//			}
//			setCurrentTileIndex(index);
//		}
//		
//		@Override
//		public void onUpdate(float pSecondsElapsed) {
//			nextFrame();
//		}
//
//		@Override
//		public void reset() {
//			// TODO Auto-generated method stub
//			
//		}
//	}
//		
//}
