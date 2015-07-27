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

package cn.javaplus.twolegs.tests.gwt;

import cn.javaplus.twolegs.tests.utils.GdxTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GwtInputTest extends GdxTest {
	ShapeRenderer renderer;

	@Override
	public void create() {
		renderer = new ShapeRenderer();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.begin(ShapeType.Filled);
		if (Gdx.input.isTouched())
			renderer.setColor(Color.RED);
		else
			renderer.setColor(Color.GREEN);
		renderer.rect(Gdx.input.getX() - 15, Gdx.graphics.getHeight()
				- Gdx.input.getY() - 15, 30, 30);
		renderer.end();
	}
}
