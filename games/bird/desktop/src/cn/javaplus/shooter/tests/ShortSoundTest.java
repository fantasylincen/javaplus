package cn.javaplus.shooter.tests;

import cn.javaplus.shooter.tests.utils.GdxTest;

import com.badlogic.gdx.Gdx;

public class ShortSoundTest extends GdxTest {

	@Override
	public void create() {
		Gdx.audio.newSound(Gdx.files.internal("data/tic.ogg")).play();
	}

}
