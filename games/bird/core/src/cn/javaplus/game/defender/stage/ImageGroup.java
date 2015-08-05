package cn.javaplus.game.defender.stage;

import cn.javaplus.game.defender.App;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.SnapshotArray;

public class ImageGroup extends Group {
	
	private float speed;

	public ImageGroup(String pngName, int count, float speed) {
		this.speed = speed;
		for (int i = 0; i < count; i++) {
			Image img = App.getAssetsManager().getImage(pngName);
			int x = (int) (img.getWidth() * i);
			img.setX(x);
			addActor(img);
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		float d = speed / 1000;
		SnapshotArray<IActor> cs = getChildren();
		for (IActor i : cs) {
			i.translate(-d, 0);
			if(i.getX() + i.getWidth() < 0) {
				moveToLast(i, cs);
			}
		}
	}

	private void moveToLast(IActor i, SnapshotArray<IActor> cs) {
		
		cs = new SnapshotArray<IActor>(cs);
		
		float maxX = Float.MIN_VALUE;
		float w = 0;
		for (IActor i2 : cs) {
			if(i2.getX() > maxX) {
				maxX = i2.getX();
				w = i2.getWidth();
			}
		}
		
		i.setX(maxX + w - 1);
		
	}
}
