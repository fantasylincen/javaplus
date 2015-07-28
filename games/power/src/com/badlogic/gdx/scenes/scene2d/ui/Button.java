/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;

/** A button is a {@link Table} with a checked state and additional {@link ButtonStyle style} fields for pressed, unpressed, and
 * checked. Each time a button is clicked, the checked state is toggled. Being a table, a button can contain any other actors.
 * <p>
 * {@link ChangeEvent} is fired when the button is clicked. Cancelling the event will restore the checked button state to what is
 * was previously.
 * <p>
 * The preferred size of the button is determined by the background and the button contents.
 * @author Nathan Sweet */
public class Button extends Table implements Disableable, IButton {
	private ButtonStyle style;
	boolean isChecked, isDisabled;
	ButtonGroup buttonGroup;
	private ClickListener clickListener;

	public Button (Skin skin) {
		super(skin);
		initialize();
		setStyle(skin.get(ButtonStyle.class));
		setSize(getPrefWidth(), getPrefHeight());
	}

	public Button (Skin skin, String styleName) {
		super(skin);
		initialize();
		setStyle(skin.get(styleName, ButtonStyle.class));
		setSize(getPrefWidth(), getPrefHeight());
	}

	public Button (IActor child, Skin skin, String styleName) {
		this(child, skin.get(styleName, ButtonStyle.class));
	}

	public Button (IActor child, ButtonStyle style) {
		initialize();
		add(child);
		setStyle(style);
		setSize(getPrefWidth(), getPrefHeight());
	}

	public Button (ButtonStyle style) {
		initialize();
		setStyle(style);
		setSize(getPrefWidth(), getPrefHeight());
	}

	/** Creates a button without setting the style or size. At least a style must be set before using this button. */
	public Button () {
		initialize();
	}

	private void initialize () {
		setTouchable(Touchable.enabled);
		addListener(clickListener = new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				if (isDisabled) return;
				setChecked(!isChecked);
			}
		});
	}

	public Button (Drawable up) {
		this(new ButtonStyle(up, null, null));
	}

	public Button (Drawable up, Drawable down) {
		this(new ButtonStyle(up, down, null));
	}

	public Button (Drawable up, Drawable down, Drawable checked) {
		this(new ButtonStyle(up, down, checked));
	}

	public Button (IActor child, Skin skin) {
		this(child, skin.get(ButtonStyle.class));
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#setChecked(boolean)
	 */
	@Override
	public void setChecked (boolean isChecked) {
		if (this.isChecked == isChecked) return;
		if (buttonGroup != null && !buttonGroup.canCheck(this, isChecked)) return;
		this.isChecked = isChecked;
		if (!isDisabled) {
			ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
			if (fire(changeEvent)) this.isChecked = !isChecked;
			Pools.free(changeEvent);
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#toggle()
	 */
	@Override
	public void toggle () {
		setChecked(!isChecked);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#isChecked()
	 */
	@Override
	public boolean isChecked () {
		return isChecked;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#isPressed()
	 */
	@Override
	public boolean isPressed () {
		return clickListener.isPressed();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#isOver()
	 */
	@Override
	public boolean isOver () {
		return clickListener.isOver();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getClickListener()
	 */
	@Override
	public ClickListener getClickListener () {
		return clickListener;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#isDisabled()
	 */
	@Override
	public boolean isDisabled () {
		return isDisabled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#setDisabled(boolean)
	 */
	@Override
	public void setDisabled (boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#setStyle(com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle)
	 */
	@Override
	public void setStyle (ButtonStyle style) {
		if (style == null) throw new IllegalArgumentException("style cannot be null.");
		this.style = style;

		Drawable background = style.up;
		if (background == null) {
			background = style.down;
			if (background == null) background = style.checked;
		}
		if (background != null) {
			padBottom(background.getBottomHeight());
			padTop(background.getTopHeight());
			padLeft(background.getLeftWidth());
			padRight(background.getRightWidth());
		}
		invalidateHierarchy();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getStyle()
	 */
	@Override
	public ButtonStyle getStyle () {
		return style;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	@Override
	public void draw (Batch batch, float parentAlpha) {
		validate();

		Drawable background = null;
		float offsetX = 0, offsetY = 0;
		if (isPressed() && !isDisabled) {
			background = style.down == null ? style.up : style.down;
			offsetX = style.pressedOffsetX;
			offsetY = style.pressedOffsetY;
		} else {
			if (isDisabled && style.disabled != null)
				background = style.disabled;
			else if (isChecked && style.checked != null)
				background = (isOver() && style.checkedOver != null) ? style.checkedOver : style.checked;
			else if (isOver() && style.over != null)
				background = style.over;
			else
				background = style.up;
			offsetX = style.unpressedOffsetX;
			offsetY = style.unpressedOffsetY;
		}

		if (background != null) {
			Color color = getColor();
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			background.draw(batch, getX(), getY(), getWidth(), getHeight());
		}

		Array<IActor> children = getChildren();
		for (int i = 0; i < children.size; i++)
			children.get(i).translate(offsetX, offsetY);
		super.draw(batch, parentAlpha);
		for (int i = 0; i < children.size; i++)
			children.get(i).translate(-offsetX, -offsetY);
	}

	protected void drawBackground (Batch batch, float parentAlpha) {
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getPrefWidth()
	 */
	@Override
	public float getPrefWidth () {
		float width = super.getPrefWidth();
		if (style.up != null) width = Math.max(width, style.up.getMinWidth());
		if (style.down != null) width = Math.max(width, style.down.getMinWidth());
		if (style.checked != null) width = Math.max(width, style.checked.getMinWidth());
		return width;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getPrefHeight()
	 */
	@Override
	public float getPrefHeight () {
		float height = super.getPrefHeight();
		if (style.up != null) height = Math.max(height, style.up.getMinHeight());
		if (style.down != null) height = Math.max(height, style.down.getMinHeight());
		if (style.checked != null) height = Math.max(height, style.checked.getMinHeight());
		return height;
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getMinWidth()
	 */
	@Override
	public float getMinWidth () {
		return getPrefWidth();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.scenes.scene2d.ui.IButton#getMinHeight()
	 */
	@Override
	public float getMinHeight () {
		return getPrefHeight();
	}

	/** The style for a button, see {@link Button}.
	 * @author mzechner */
	static public class ButtonStyle {
		/** Optional. */
		public Drawable up, down, over, checked, checkedOver, disabled;
		/** Optional. */
		public float pressedOffsetX, pressedOffsetY;
		/** Optional. */
		public float unpressedOffsetX, unpressedOffsetY;

		public ButtonStyle () {
		}

		public ButtonStyle (Drawable up, Drawable down, Drawable checked) {
			this.up = up;
			this.down = down;
			this.checked = checked;
		}

		public ButtonStyle (ButtonStyle style) {
			this.up = style.up;
			this.down = style.down;
			this.over = style.over;
			this.checked = style.checked;
			this.checkedOver = style.checkedOver;
			this.disabled = style.disabled;
			this.pressedOffsetX = style.pressedOffsetX;
			this.pressedOffsetY = style.pressedOffsetY;
			this.unpressedOffsetX = style.unpressedOffsetX;
			this.unpressedOffsetY = style.unpressedOffsetY;
		}
	}
}
