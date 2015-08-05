package cn.javaplus.crazy.main;

import cn.javaplus.crazy.protocol.Protocols.Channel;

public interface Player {

	Channel getChannel();

	String getId();

	String getNick();

}
