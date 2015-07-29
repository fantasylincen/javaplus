package org.hhhhhh.fqzs.login;

import java.util.List;

import org.hhhhhh.fqzs.core.D;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class LoginStage extends AbstractStage {

	private List<LoginSuccessListener> listeners = Lists.newArrayList();
	private TextField username;
	private TextField password;

	public LoginStage() {
		super(D.STAGE_W, D.STAGE_H);
		initUsername();
		initPassword();
	}

	private void initPassword() {
		password = new TextField("XXXXX", Assets.getDefaultSkin());
		password.setSize(300, 100);
		password.setPosition(300, 500);
		addActor(password);		
	}

	private void initUsername() {
		BitmapFont font = Assets.getDefaultLoader().getBitmapFont("simhei.ttf");
		TextFieldStyle style = new TextFieldStyle();
		style.font = font;
		username = new TextField("XXXXX", style);
		username.setSize(300, 100);
		username.setPosition(300, 300);
		addActor(username);
	}

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}

	public void addLoginSuccessListener(LoginSuccessListener l) {
		listeners.add(l);
	}
}
