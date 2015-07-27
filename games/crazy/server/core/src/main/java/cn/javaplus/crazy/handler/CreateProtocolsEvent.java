package cn.javaplus.crazy.handler;

import cn.javaplus.crazy.protocol.Protocols;

public class CreateProtocolsEvent {

	private Protocols protocols;

	public CreateProtocolsEvent(Protocols protocols) {
		this.protocols = protocols;
	}

	public Protocols getProtocols() {
		return protocols;
	}

}
