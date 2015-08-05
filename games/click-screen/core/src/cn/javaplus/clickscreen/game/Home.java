package cn.javaplus.clickscreen.game;

import org.javaplus.clickscreen.bullet.HitListener.Target;
import org.javaplus.game.common.actions.ShakeAction;
import org.javaplus.game.common.assets.Assets;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.game.HpLabel.Hpable;
import cn.javaplus.clickscreen.hp.Hp;
import cn.javaplus.clickscreen.hp.Hp.HpListener;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Home extends Group implements Target, Hpable {
	private static final int HP_MAX = 10;
	private static final int H = 100;
	private static final int W = 90;
	private static final int Y = 240;
	private static final int X = 50;

	private AtlasRegion h1;
	private AtlasRegion h2;
	private AtlasRegion h3;
	private AtlasRegion h4;
	private Hp hp = new Hp(HP_MAX);

	public Home() {
		TextureAtlas a = Assets.getSd().getTextureAtlas("data/game.txt");
		h1 = a.findRegion("rpgTile114");
		h2 = a.findRegion("rpgTile107");
		h3 = a.findRegion("rpgTile116");

		h4 = a.findRegion("rpgTile187");
		setSize(W, H);
		setPosition(X, Y);
		HpLabel label = new HpLabel(this);
		addActor(label);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		drawCenter(batch, h3, 62);
		drawCenter(batch, h2, 0);
		drawCenter(batch, h4, -10);
		drawCenter(batch, h1, 30);
	}

	private void drawCenter(Batch batch, AtlasRegion h, int dy) {
		float w = h.getRegionWidth()/2;
		float hh = h.getRegionHeight()/2;
		float x = W / 2 - w + getX();
		float y = H / 2 - hh + getY() + dy;
		
		batch.draw(h, x, y);
	}

	@Override
	public void reduceHp(double damage) {
		if (isDeath()) {
			App.getDb().reduceCoin(damage);
			return;
		}
		hp.reduce(damage);
	}

	public void addListener(HpListener l) {
		hp.addListener(l);
	}

	public boolean isDeath() {
		return hp.isDeath();
	}

	@Override
	public void shake() {
		addAction(new ShakeAction(this, X, Y));
	}

	@Override
	public Actor toActor() {
		return this;
	}

	@Override
	public double getHp() {
		return hp.getValue();
	}

	@Override
	public double getHpMax() {
		return HP_MAX;
	}
}
