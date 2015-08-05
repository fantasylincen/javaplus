package cn.javaplus.jigsaw.channel;

import org.javaplus.game.common.channel.Channel;

import cn.javaplus.jigsaw.App;

public class ChannelExp10086Cn implements Channel {

	@Override
	public void exit() {
		App.getOs().exit();
	}

	@Override
	public void gameOver() {
	}

}
