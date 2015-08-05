package cn.javaplus.crazy.tests;

import cn.javaplus.crazy.tests.utils.GdxTest;

import com.badlogic.gdx.Gdx;

public class ShortSoundTest extends GdxTest {

	@Override
	public void create() {
		Gdx.audio.newSound(Gdx.files.internal("data/tic.ogg")).play();
	}

}
