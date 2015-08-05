package org.javaplus.clickscreen.button;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

public interface IButtonListener {

	public abstract void onException(Exception e);

	public abstract void action(ChangeEvent event, Actor actor);

}