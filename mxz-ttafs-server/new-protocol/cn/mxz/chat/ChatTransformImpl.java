package cn.mxz.chat;

import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.prompt.PromptManager;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;

public class ChatTransformImpl implements ChatTransform {

	private City user;

	@Override
	public ChatUI openMessage(int type) {

		PromptManager pm = user.getPromptManager();
		pm.removeMessageMark(type);
		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(user);

		// * 0:世界 1:联盟 2:私聊3: 客服
		if (type == 0) {
			MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
			return new WorldChatUIAdapter(user);
		}

		if (type == 1) {
			MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
			return new UnionChatUIAdaptor(user);
		}

//		if (type == 2) {
//			user.getUserCounterHistory().clear(CounterKey.RECEIVE_MESSAGE);
//			MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
//			return new UserChatUIAdaptor(user);
//		}

		if (type == 3) {
			MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
			user.getUserCounterHistory()
					.clear(CounterKey.RECEIVE_KE_FU_MESSAGE);
			return new KeFuChatUIAdaptor(user);
		}

		throw new SureIllegalOperationException("无法识别的值:" + type
				+ " -- 0:世界 1:联盟 2:私聊");
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public void closeTips(int type) {
		PromptManager pm = user.getPromptManager();
		pm.removeMessageMark(type);
		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(user);

//		if (type == 2) {
//			user.getUserCounterHistory().clear(CounterKey.RECEIVE_MESSAGE);
//		}

		if (type == 3) {
			user.getUserCounterHistory()
					.clear(CounterKey.RECEIVE_KE_FU_MESSAGE);
		}

		MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
	}

	@Override
	public ChatUI openPrivateMessage(String userId) {
		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(user);
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
		return new UserChatUIAdaptor(user, userId);
	}

}
