package cn.mxz.util.message;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import define.D;


@Component("textMessage")
@Scope("prototype")

public class TextMessageImpl extends MessageImpl {

	@Override
	protected int getPacketId() {
		return D.PACKET_MESSAGE;
	}
}
