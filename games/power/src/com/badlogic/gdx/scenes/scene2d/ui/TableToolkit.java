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

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.IActor;
import com.badlogic.gdx.scenes.scene2d.IGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.tablelayout.BaseTableLayout.Debug;
import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.Toolkit;

/** The libgdx implementation of the table layout functionality.
 * @author Nathan Sweet */
public class TableToolkit extends Toolkit<IActor, Table, TableLayout> {
	static boolean drawDebug;
	static Pool<Cell> cellPool = new Pool() {
		protected Cell newObject () {
			return new Cell();
		}
	};

	public Cell obtainCell (TableLayout layout) {
		Cell cell = cellPool.obtain();
		cell.setLayout(layout);
		return cell;
	}

	public void freeCell (Cell cell) {
		cell.free();
		cellPool.free(cell);
	}

	public void addChild (IActor parent, IActor child) {
		child.remove();
		((IGroup)parent).addActor(child);
	}

	public void removeChild (IActor parent, IActor child) {
		((IGroup)parent).removeActor(child);
	}

	public float getMinWidth (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getMinWidth();
		return actor.getWidth();
	}

	public float getMinHeight (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getMinHeight();
		return actor.getHeight();
	}

	public float getPrefWidth (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getPrefWidth();
		return actor.getWidth();
	}

	public float getPrefHeight (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getPrefHeight();
		return actor.getHeight();
	}

	public float getMaxWidth (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getMaxWidth();
		return 0;
	}

	public float getMaxHeight (IActor actor) {
		if (actor instanceof Layout) return ((Layout)actor).getMaxHeight();
		return 0;
	}

	public float getWidth (IActor widget) {
		return widget.getWidth();
	}

	public float getHeight (IActor widget) {
		return widget.getHeight();
	}

	public void clearDebugRectangles (TableLayout layout) {
		if (layout.debugRects != null) layout.debugRects.clear();
	}

	public void addDebugRectangle (TableLayout layout, Debug type, float x, float y, float w, float h) {
		drawDebug = true;
		if (layout.debugRects == null) layout.debugRects = new Array();
		layout.debugRects.add(new DebugRect(type, x, layout.getTable().getHeight() - y, w, h));
	}

	static class DebugRect extends Rectangle {
		final Debug type;

		public DebugRect (Debug type, float x, float y, float width, float height) {
			super(x, y, width, height);
			this.type = type;
		}
	}
}
