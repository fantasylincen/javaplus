package cn.javaplus.shhz.components.buildings;

import cn.javaplus.game.shhz.define.D;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class EditorSwitch extends ActorGestureListener {

	private Switch editorSwitch;

	public EditorSwitch(Switch editorSwitch) {
		this.editorSwitch = editorSwitch;
	}

	@Override
	public boolean longPress(Actor actor, float x, float y) {
		if (!D.IS_EDITOR_ENABLE) {
			return false;
		}
		if (editorSwitch.isEnable()) {
			editorSwitch.disable();
		} else {
			editorSwitch.enable();
		}
		// Log.d("BuildingEditor isEditable:" + editable.isEnable());
		return true;
	}
}
