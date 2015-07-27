package cn.javaplus.jigsaw.channel;

import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.game.ShowListener;
import org.javaplus.game.common.log.Log;

import cn.javaplus.jigsaw.App;

public class DefaultChannel implements Channel {

	@Override
	public void exit() {

		App.getAds().show(new ShowListener() {

			@Override
			public void onClose() {
				App.getOs().exit();
			}
		});
		Log.d("show ads");

		App.getLogger().onCountEvent("showads");
	}

	@Override
	public void gameOver() {
	}

}
