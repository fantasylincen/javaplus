package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.utils.Array;

public interface IButtonGroup {

	public abstract void add(Button button);

	public abstract void add(Button... buttons);

	public abstract void remove(Button button);

	public abstract void remove(Button... buttons);

	/** Sets the first {@link TextButton} with the specified text to checked. */
	public abstract void setChecked(String text);

	/** Sets all buttons' {@link Button#isChecked()} to false, regardless of {@link #setMinCheckCount(int)}. */
	public abstract void uncheckAll();

	/** @return the first checked button, or null. */
	public abstract IButton getChecked();

	public abstract Array<Button> getAllChecked();

	public abstract Array<Button> getButtons();

	/** Sets the minimum number of buttons that must be checked. Default is 1. */
	public abstract void setMinCheckCount(int minCheckCount);

	/** Sets the maximum number of buttons that can be checked. Set to -1 for no maximum. Default is 1. */
	public abstract void setMaxCheckCount(int maxCheckCount);

	/** If true, when the maximum number of buttons are checked and an additional button is checked, the last button to be checked
	 * is unchecked so that the maximum is not exceeded. If false, additional buttons beyond the maximum are not allowed to be
	 * checked. Default is true. */
	public abstract void setUncheckLast(boolean uncheckLast);

}