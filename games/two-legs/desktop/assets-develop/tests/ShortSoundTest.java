package cn.javaplus.twolegs.tests;

import cn.javaplus.twolegs.tests.utils.GdxTest;

import com.badlogic.gdx.Gdx;

public class ShortSoundTest extends GdxTest {

	@Override
	public void create() {
		Gdx.audio.newSound(Gdx.files.internal("data/tic.ogg")).play();
	}

}
