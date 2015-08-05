package cn.javaplus.twolegs.channel;

import org.javaplus.game.common.channel.Channel;

import cn.javaplus.twolegs.App;

public class ChannelExp10086Cn implements Channel {

	@Override
	public void exit() {
		App.getOs().exit();
	}

	@Override
	public void gameOver() {
	}

}
