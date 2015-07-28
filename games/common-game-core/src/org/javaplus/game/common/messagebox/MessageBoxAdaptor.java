package org.javaplus.game.common.messagebox;

import org.javaplus.game.common.log.Log;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MessageBoxAdaptor implements MessageBox {

	private Label messageBox;

	public MessageBoxAdaptor(Label messageBox) {
		this.messageBox = messageBox;
	}

	@Override
	public void showMessage(String text) {
		Log.d(text);
		messageBox.setText(text);
	}

}
