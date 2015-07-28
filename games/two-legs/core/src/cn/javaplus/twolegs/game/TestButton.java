package cn.javaplus.twolegs.game;
//package cn.javaplus.shooter.game;
//
//import cn.javaplus.shooter.Ads;
//import cn.javaplus.shooter.App;
//import cn.javaplus.shooter.assets.Assets;
//import cn.javaplus.shooter.define.D;
//
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
//
//public class TestButton extends TextButton {
//	public TestButton() {
//		super("test", Assets.getDefaultSkin());
//		setSize(100, 50);
//		setPosition(D.STAGE_W / 2 - getWidth(), D.STAGE_H / 2 - getHeight()
//				- 100);
//		addListener(new ChangeListener() {
//
//			@Override
//			public void changed(ChangeEvent event, Actor actor) {
//				Ads ads = App.getApp().getPlantform().getAds();
////				if (ads.isInitOver()) {
//					ads.show(null);
//					
////				} else {
////					Log.e("ads has not init over");
////				}
//			}
//		});
//	}
//}
