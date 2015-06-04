package cn.mxz.util.message;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import define.D;

@Component("noticeMessage")
@Scope("prototype")

class NoticeMessageImpl extends MessageImpl {

	@Override
	protected int getPacketId() {
		return D.PACKET_NOTICE;
	}
}
