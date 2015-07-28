package cn.javaplus.twolegs.log;

import cn.javaplus.twolegs.debug.TextDebugger;

/**
 * 调试器初始化
 */
public class CreateDebuggerEvent {

	private TextDebugger debugger;

	public CreateDebuggerEvent(TextDebugger debugger) {
		this.debugger = debugger;
	}

	public TextDebugger getDebugger() {
		return debugger;
	}

}
