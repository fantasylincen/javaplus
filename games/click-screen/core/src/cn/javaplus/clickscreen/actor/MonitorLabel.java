package cn.javaplus.clickscreen.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MonitorLabel extends Label {

	private IMonitor monitor;

	public interface IMonitor {

		boolean hasChanged();

		CharSequence getText();

		void onChanged();

	}

	public MonitorLabel(IMonitor monitor, LabelStyle style) {
		super(monitor.getText(), style);
		this.monitor = monitor;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (monitor.hasChanged()) {
			setText(monitor.getText());
			monitor.onChanged();
		}
	}

}
