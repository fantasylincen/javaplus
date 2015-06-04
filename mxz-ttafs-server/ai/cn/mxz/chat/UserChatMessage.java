package cn.mxz.chat;

import mongo.gen.MongoGen.UserChatRecordDto;

public interface UserChatMessage extends ChatMessage {

	boolean hasRead();

	void onRead();

	String getReceiverId();

	UserChatRecordDto getDto();

	String getShortContent();
}
