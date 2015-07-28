package cn.javaplus.shhz.events.components;

import cn.javaplus.shhz.components.Button;

public class ButtonTapEvent {

	private String id;
	private Button button;

	public ButtonTapEvent(String id, Button button) {
		this.id = id;
		this.button = button;
	}

	public String getId() {
		return id;
	}

	public Button getButton() {
		return button;
	}

}
