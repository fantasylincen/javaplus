package cn.javaplus.shhz.actor;

import java.util.List;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.animation.Animation;
import cn.javaplus.shhz.animation.AnimationFrame;
import cn.javaplus.shhz.assets.Assets;
import cn.javaplus.shhz.components.plist.Frame;
import cn.javaplus.shhz.components.plist.FrameOffset;
import cn.javaplus.shhz.components.plist.FrameRect;
import cn.javaplus.shhz.components.plist.Plist;
import cn.javaplus.shhz.components.plist.Size;
import cn.javaplus.shhz.listeners.background.AnimationFrameImpl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 该Actor默认无体积, 坐标在显示对象的中部
 */
public class ActorPlist extends Actor {

	private Texture texture;
	private TextureRegion actorBackground;
	private Plist plist;

	private AnimationFrame[] frames;
	private Animation animation;

	private float stateTime;

	/**
	 * @param name
	 *            可传入: xxx 或者 xxx.png 或者 xxx.plist
	 * 
	 */
	public ActorPlist(String name) {

		loadAssets(name);
		initFrames();
		initAnimation();

		if (D.IS_SHOW_ACTOR_BACKGROUND) {
			actorBackground = new TextureRegion(
					Assets.getTexture("redbackground.png"), (int) getWidth(),
					(int) getHeight());
		}
	}

	private void initAnimation() {
		animation = new Animation((float) D.ANIMATION_TIME_SPACE, frames);
	}

	private void initFrames() {
		List<Frame> pfs = plist.getFrames();
		frames = new AnimationFrame[pfs.size()];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = buildFrame(pfs.get(i));
		}
		adjustActorSize();
	}

	private AnimationFrame buildFrame(Frame frame) {
		FrameRect rect = frame.getRect();
		int x = rect.getX();
		int y = rect.getY();
		int w = rect.getW();
		int h = rect.getH();

		Size size = frame.getSourceSize();
		setSize(size.getWidth(), size.getHeight());

		TextureRegion r;
		if (frame.isRotated()) {
			r = new TextureRegion(texture, x, y, h, w);
		} else {
			r = new TextureRegion(texture, x, y, w, h);
		}
		return new AnimationFrameImpl(r, frame);
	}

	private void loadAssets(String name) {
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

		plist = Assets.getPlist(plistPath);
		texture = Assets.getTexture(pngPath);
	}

	private void adjustActorSize() {
		int widthSum = 0;
		int heightSum = 0;
		int length = frames.length;
		for (AnimationFrame frame : frames) {
			widthSum += frame.getWidth();
			heightSum += frame.getHeight();
		}
		setSize(widthSum / length, heightSum / length);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		stateTime += Gdx.graphics.getDeltaTime();
		AnimationFrame frame = animation.getKeyFrame(stateTime, true);

		drawFrame(batch, frame);
	}

	public void drawFrame(Batch batch, AnimationFrame frame) {
		float rotation = getRotation();

		float sx;
		float sy;

		float angle;
		int ox = frame.getWidth() / 2;
		int oy = frame.getHeight() / 2;
		int frameH = frame.getHeight();
		int frameW = frame.getWidth();

		if (frame.isRotated()) {
			sx = getScaleY();
			sy = getScaleX();
			angle = 90;
		} else {
			sx = getScaleX();
			sy = getScaleY();
			angle = 0;
		}

		Frame f = frame.getFrameConfig();
		FrameOffset offset = f.getOffset();

		float actorX = getX();
		float actorY = getY();
		actorX += offset.getX();
		actorY += offset.getY();
		actorX -= frameW / 2;
		actorY -= frameH / 2;
		actorX += getWidth() / 2;

		TextureRegion rg = frame.getTextureRegion();

		if (D.IS_SHOW_ACTOR_BACKGROUND) {
			batch.draw(actorBackground, getX(), getY());
		}

		if (D.IS_SHOW_ACTOR_TEXTURE_REGION_BACKGROUND) {
			batch.draw(frame.getTextureRegionBackground(), actorX, actorY, ox,
					oy, frameW, frameH, sx, sy, rotation + angle);
		}

		batch.draw(rg, actorX, actorY, ox, oy, frameW, frameH, sx, sy, rotation
				+ angle);
	}

}
