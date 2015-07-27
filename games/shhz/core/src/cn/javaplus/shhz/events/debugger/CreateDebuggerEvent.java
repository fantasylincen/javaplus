package cn.javaplus.shhz.events.debugger;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.debug.TextDebugger;

/**
 * 调试器初始化
 */
public class CreateDebuggerEvent {

	private TextDebugger debugger;
	private App game;

	public CreateDebuggerEvent(App game, TextDebugger debugger) {
		this.game = game;
		this.debugger = debugger;
	}

	public TextDebugger getDebugger() {
		return debugger;
	}

	public App getGame() {
		return game;
	}

}
