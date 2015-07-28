package cn.javaplus.shhz.animation;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * <p>
 * An Animation stores a list of {@link TextureRegion}s representing an animated
 * sequence, e.g. for running or jumping. Each region of an Animation is called
 * a key frame, multiple key frames make up the animation.
 * </p>
 * 
 * @author mzechner
 */
public class Animation {

	/** Defines possible playback modes for an {@link Animation}. */
	public enum PlayMode {
		NORMAL, REVERSED, LOOP, LOOP_REVERSED, LOOP_PINGPONG, LOOP_RANDOM,
	}

	final AnimationFrame[] keyFrames;
	public final float frameDuration;
	public final float animationDuration;

	private PlayMode playMode = PlayMode.NORMAL;

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link AnimationFrame}s representing the frames.
	 */
	public Animation(float frameDuration, List<AnimationFrame> keyFrames) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.size() * frameDuration;
		this.keyFrames = new AnimationFrame[keyFrames.size()];
		for (int i = 0, n = keyFrames.size(); i < n; i++) {
			this.keyFrames[i] = keyFrames.get(i);
		}

		this.playMode = PlayMode.NORMAL;
	}

	/**
	 * Constructor, storing the frame duration, key frames and play type.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link AnimationFrame}s representing the frames.
	 * @param playMode
	 *            the animation playback mode.
	 */
	public Animation(float frameDuration, List<AnimationFrame> keyFrames,
			PlayMode playMode) {

		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.size() * frameDuration;
		this.keyFrames = new AnimationFrame[keyFrames.size()];
		for (int i = 0, n = keyFrames.size(); i < n; i++) {
			this.keyFrames[i] = keyFrames.get(i);
		}

		this.playMode = playMode;
	}

	/**
	 * Constructor, storing the frame duration and key frames.
	 * 
	 * @param frameDuration
	 *            the time between frames in seconds.
	 * @param keyFrames
	 *            the {@link AnimationFrame}s representing the frames.
	 */
	public Animation(float frameDuration, AnimationFrame... keyFrames) {
		this.frameDuration = frameDuration;
		this.animationDuration = keyFrames.length * frameDuration;
		this.keyFrames = keyFrames;
		this.playMode = PlayMode.NORMAL;
	}

	/**
	 * Returns a {@link AnimationFrame} based on the so called state time. This
	 * is the amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on. The mode specifies
	 * whether the animation is looping or not.
	 * 
	 * @param stateTime
	 *            the time spent in the state represented by this animation.
	 * @param looping
	 *            whether the animation is looping or not.
	 * @return the Frame representing the frame of animation for the given state
	 *         time.
	 */
	public AnimationFrame getKeyFrame(float stateTime, boolean looping) {
		// we set the play mode by overriding the previous mode based on looping
		// parameter value
		PlayMode oldPlayMode = playMode;
		if (looping
				&& (playMode == PlayMode.NORMAL || playMode == PlayMode.REVERSED)) {
			if (playMode == PlayMode.NORMAL)
				playMode = PlayMode.LOOP;
			else
				playMode = PlayMode.LOOP_REVERSED;
		} else if (!looping
				&& !(playMode == PlayMode.NORMAL || playMode == PlayMode.REVERSED)) {
			if (playMode == PlayMode.LOOP_REVERSED)
				playMode = PlayMode.REVERSED;
			else
				playMode = PlayMode.LOOP;
		}

		AnimationFrame frame = getKeyFrame(stateTime);
		playMode = oldPlayMode;
		return frame;
	}

	/**
	 * Returns a {@link AnimationFrame} based on the so called state time. This
	 * is the amount of seconds an object has spent in the state this Animation
	 * instance represents, e.g. running, jumping and so on using the mode
	 * specified by {@link #setPlayMode(PlayMode)} method.
	 * 
	 * @param stateTime
	 * @return the Frame representing the frame of animation for the given state
	 *         time.
	 */
	public AnimationFrame getKeyFrame(float stateTime) {
		int frameNumber = getKeyFrameIndex(stateTime);
		return keyFrames[frameNumber];
	}

	/**
	 * Returns the current frame number.
	 * 
	 * @param stateTime
	 * @return current frame number
	 */
	public int getKeyFrameIndex(float stateTime) {
		if (keyFrames.length == 1)
			return 0;

		int frameNumber = (int) (stateTime / frameDuration);
		switch (playMode) {
		case NORMAL:
			frameNumber = Math.min(keyFrames.length - 1, frameNumber);
			break;
		case LOOP:
			frameNumber = frameNumber % keyFrames.length;
			break;
		case LOOP_PINGPONG:
			frameNumber = frameNumber % ((keyFrames.length * 2) - 2);
			if (frameNumber >= keyFrames.length)
				frameNumber = keyFrames.length - 2
						- (frameNumber - keyFrames.length);
			break;
		case LOOP_RANDOM:
			frameNumber = MathUtils.random(keyFrames.length - 1);
			break;
		case REVERSED:
			frameNumber = Math.max(keyFrames.length - frameNumber - 1, 0);
			break;
		case LOOP_REVERSED:
			frameNumber = frameNumber % keyFrames.length;
			frameNumber = keyFrames.length - frameNumber - 1;
			break;
		}

		return frameNumber;
	}

	/**
	 * Returns the keyFrames[] array where all the Frames of the animation are
	 * stored.
	 * 
	 * @return keyFrames[] field
	 */
	public AnimationFrame[] getKeyFrames() {
		return keyFrames;
	}

	/** Returns the animation play mode. */
	public PlayMode getPlayMode() {
		return playMode;
	}

	/**
	 * Sets the animation play mode.
	 * 
	 * @param playMode
	 *            The animation {@link PlayMode} to use.
	 */
	public void setPlayMode(PlayMode playMode) {
		this.playMode = playMode;
	}

	/**
	 * Whether the animation would be finished if played without looping
	 * (PlayMode#NORMAL), given the state time.
	 * 
	 * @param stateTime
	 * @return whether the animation is finished.
	 */
	public boolean isAnimationFinished(float stateTime) {
		int frameNumber = (int) (stateTime / frameDuration);
		return keyFrames.length - 1 < frameNumber;
	}
}
