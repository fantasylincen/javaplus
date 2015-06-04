package cn.mxz.chat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.ChatService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.prompt.PromptManager;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;

@Component("chatService")
@Scope("prototype")
public class ChatServiceImpl extends AbstractService implements ChatService {

	@Override
	public void sendToWorld(String message) {
		Chater c = getCity().getWorldChater();
		c.send(message);
	}

	@Override
	public void sendToUnion(String message) {
		Chater c = getCity().getUnionChater();
		c.send(message);
	}

	@Override
	public void sendToUser(String message, String userId) {
		UserChater c = getCity().getUserChater();
		c.send(message, userId);
	}

	@Override
	public void openMessage(int type) {
		PromptManager pm = getCity().getPromptManager();
		pm.removeMessageMark(type);
		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(getCity());
		MessageFactory.getUser().onUpdateUserList(getSocket(), data);
	}
}
