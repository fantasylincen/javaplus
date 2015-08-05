package cn.javaplus.shhz.listeners.stage;

import cn.javaplus.game.shhz.Events;
import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.debug.TextDebugger;
import cn.javaplus.shhz.events.Listener;
import cn.javaplus.shhz.events.debugger.CreateDebuggerEvent;
import cn.javaplus.shhz.events.screen.CreateGameScreenEvent;
import cn.javaplus.shhz.log.Log;

public class CreateDebugger implements Listener<CreateGameScreenEvent> {

	@Override
	public void onEvent(CreateGameScreenEvent e) {
		if (D.IS_USE_DEBUGGER) {
			TextDebugger debugger = new TextDebugger();
			e.getScreen().getControllerStage().addActor(debugger);
			Log.bind(debugger);
			Events.dispatch(new CreateDebuggerEvent(e.getGame(), debugger));
		}
	}

}
