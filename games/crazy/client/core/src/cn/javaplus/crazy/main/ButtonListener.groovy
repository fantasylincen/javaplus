package cn.javaplus.crazy.main;

import cn.javaplus.crazy.App.AppContext;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent

public abstract class ButtonListener extends ChangeListener {

	@Override
	public final void changed(ChangeEvent event, Actor actor) {
		try {
			onClick();
		} catch (Exception e) {
			AppContext.getMessageBox().showMessage(e.getMessage());
		}
	}

	protected abstract void onClick();


}
