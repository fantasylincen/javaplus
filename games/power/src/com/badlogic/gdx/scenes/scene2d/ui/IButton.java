package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public interface IButton extends IActor {

	public abstract void setChecked(boolean isChecked);

	/** Toggles the checked state. This method changes the checked state, which fires a {@link ChangeEvent}, so can be used to
	 * simulate a button click. */
	public abstract void toggle();

	public abstract boolean isChecked();

	public abstract boolean isPressed();

	public abstract boolean isOver();

	public abstract ClickListener getClickListener();

	public abstract boolean isDisabled();

	/** When true, the button will not toggle {@link #isChecked()} when clicked and will not fire a {@link ChangeEvent}. */
	public abstract void setDisabled(boolean isDisabled);

	public abstract void setStyle(ButtonStyle style);

	/** Returns the button's style. Modifying the returned style may not have an effect until {@link #setStyle(ButtonStyle)} is
	 * called. */
	public abstract ButtonStyle getStyle();

	public abstract void draw(Batch batch, float parentAlpha);

	public abstract float getPrefWidth();

	public abstract float getPrefHeight();

	public abstract float getMinWidth();

	public abstract float getMinHeight();

}