package cn.javaplus.game.defender.stage;

import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.google.common.collect.Lists;

public class GameInputProcessorImpl implements InputProcessor, GameInputProcessor {

	List<InputProcessor>	processor;

	public GameInputProcessorImpl() {
		processor = Lists.newArrayList();
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#add(com.badlogic.gdx.InputProcessor)
	 */
	
	public boolean add(InputProcessor object) {
		return processor.add(object);
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#touchDragged(int, int, int)
	 */
	
	
	public boolean touchDragged(int x, int y, int pointer) {
//		x = TouchUtils.parseX(x);
//		y = TouchUtils.parseY(y);
		// Debuger.debug("GameInputProcessor.touchDragged() 指针坐标:" + x + ", " +
		// y);
		for (InputProcessor p : processor) {
			p.touchDragged(x, y, pointer);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#keyDown(int)
	 */
	
	
	public boolean keyDown(int keycode) {
		for (InputProcessor p : processor) {
			p.keyDown(keycode);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#keyUp(int)
	 */
	
	
	public boolean keyUp(int keycode) {
		for (InputProcessor p : processor) {
			p.keyUp(keycode);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#keyTyped(char)
	 */
	
	
	public boolean keyTyped(char character) {
		for (InputProcessor p : processor) {
			p.keyTyped(character);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#touchDown(int, int, int, int)
	 */
	
	
	public boolean touchDown(int x, int y, int pointer, int button) {
//		x = TouchUtils.parseX(x);
//		y = TouchUtils.parseY(y);
//		Debuger.debug("GameInputProcessor.touchDown() 指针坐标:" + x + ", " + y);
		for (InputProcessor p : processor) {
			p.touchDown(x, y, pointer, button);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#touchUp(int, int, int, int)
	 */
	
	
	public boolean touchUp(int x, int y, int pointer, int button) {
//		x = TouchUtils.parseX(x);
//		y = TouchUtils.parseY(y);
//		Debuger.debug("GameInputProcessor.touchUp() 指针坐标:" + x + ", " + y);
		for (InputProcessor p : processor) {
			p.touchUp(x, y, pointer, button);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#mouseMoved(int, int)
	 */
	
	
	public boolean mouseMoved(int x, int y) {
//		x = TouchUtils.parseX(x);
//		y = TouchUtils.parseY(y);
		// Debuger.debug("GameInputProcessor.mouseMoved() 指针坐标:" + x + ", " +
		// y);
		for (InputProcessor p : processor) {
			p.mouseMoved(x, y);
		}
		return true;
	}

	/* （非 Javadoc）
	 * @see cn.javaplus.game.defender.stage.IGameInputProcessor#scrolled(int)
	 */
	
	
	public boolean scrolled(int amount) {
		for (InputProcessor p : processor) {
			p.scrolled(amount);
		}
		return true;
	}
}