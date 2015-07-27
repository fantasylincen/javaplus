package cn.javaplus.crazy.login;

import com.badlogic.gdx.scenes.scene2d.ui.Label

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
