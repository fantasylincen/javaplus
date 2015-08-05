package cn.javaplus.shhz.buildingcreator;

//package cn.javaplus.game.shhz.buildingcreator;
//
//import cn.javaplus.game.shhz.Game;
//import cn.javaplus.game.shhz.R;
//import cn.javaplus.game.shhz.assets.Assets;
//import cn.javaplus.game.shhz.components.itemlist.Item;
//import cn.javaplus.game.shhz.screen.GameScreen;
//import cn.javaplus.log.Log;
//
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
//
//public class TakeOutListener extends ActorGestureListener {
//
//	private Item item;
//	// private boolean readyToTakeOut;
//	private float startX;
//	private float startY;
//	private Image mover;
//	private Game game;
//
//	public TakeOutListener(Item item, Game game) {
//		this.item = item;
//		this.game = game;
//	}
//
//	@Override
//	public void touchDown(InputEvent event, float x, float y, int pointer,
//			int button) {
//		this.startX = x;
//		this.startY = y;
//		
//	}
//
//	@Override
//	public void pan(InputEvent event, float x, float y, float deltaX,
//			float deltaY) {
//		if (mover == null) {
//			if (Math.abs(x - startX) > 50 || Math.abs(y - startY) > 50) {
//				takeOutAndMove(x, y);
//			}
//		} else {
//			Log.d("TakeOutListener.pan " , event.getStageX(), event.getStageY());
//			setPosition(x, y);
//		}
//	}
//
//	private void setPosition(float x, float y) {
//		float xp = item.getParent().getX();
//		float yp = item.getParent().getY();
//		mover.setPosition(item.getX() + x - item.getWidth() / 2 + xp,
//				item.getY() + y + yp + item.getHeight() );
//	}
//
//	@Override
//	public void touchUp(InputEvent event, float x, float y, int pointer,
//			int button) {
//		if (mover != null) {
//			getStage().getRoot().removeActor(mover);
//			mover = null;
//		}
//	}
//
//	private void takeOutAndMove(float x, float y) {
//		mover = new Image(Assets.getTexture(R.PathbuttonPng));
//		TanAction action = new TanAction();
//		mover.setSize(mover.getWidth() / action.getScale(), mover.getHeight()
//				/ action.getScale());
//		mover.addAction(action);
//		setPosition(x, y);
//		getStage().addActor(mover);
//	}
//
//	private Stage getStage() {
//		GameScreen screen = (GameScreen) game.getScreen();
//		return screen.getGameStage();
//	}
// }
