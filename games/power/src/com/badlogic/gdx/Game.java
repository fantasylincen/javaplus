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

package com.badlogic.gdx;

/** <p>
 * An {@link ApplicationListener} that delegates to a {@link Screen}. This allows an application to easily have multiple screens.
 * </p>
 * <p>
 * Screens are not disposed automatically. You must handle whether you want to keep screens around or dispose of them when another
 * screen is set.
 * </p> */
public abstract class Game implements ApplicationListener, IGame {
	private Screen screen;

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#dispose()
	 */
	@Override
	public void dispose () {
		if (screen != null) screen.hide();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#pause()
	 */
	@Override
	public void pause () {
		if (screen != null) screen.pause();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#resume()
	 */
	@Override
	public void resume () {
		if (screen != null) screen.resume();
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#render()
	 */
	@Override
	public void render () {
		if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#resize(int, int)
	 */
	@Override
	public void resize (int width, int height) {
		if (screen != null) screen.resize(width, height);
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#setScreen(com.badlogic.gdx.Screen)
	 */
	@Override
	public void setScreen (Screen screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/* （非 Javadoc）
	 * @see com.badlogic.gdx.IGame#getScreen()
	 */
	@Override
	public Screen getScreen () {
		return screen;
	}
}
