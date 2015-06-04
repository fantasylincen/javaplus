package cn.mxz.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import cn.mxz.city.City;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class UserChatUIAdaptor implements ChatUI {

	private City user;
	private UserChater me;

	/**
	 * 发送者ID
	 */
	private String senderId;

	public UserChatUIAdaptor(City user, String userId) {
		this.user = user;
		this.senderId = userId;
		me = user.getUserChater();
	}

	@Override
	public List<ChatMessageUI> getMessages() {
		List<UserChatMessage> messages = me.getMessages();

		messages = Lists.newArrayList(messages);

		Collections.sort(messages, new Comparator<UserChatMessage>() {

			@Override
			public int compare(UserChatMessage o1, UserChatMessage o2) {
				long t1 = o1.getTime();
				long t2 = o2.getTime();
				t1 /= 1000;
				t2 /= 1000;
				return (int) (t1 - t2);
			}
		});

		ArrayList<ChatMessageUI> ls = Lists.newArrayList();
		for (UserChatMessage cm : messages) {

			String rId = cm.getReceiverId();
			String sId = cm.getId();

			HashSet<String> s = Sets.newHashSet(sId, rId);

			String id = user.getId();
			if (s.contains(senderId) && s.contains(id)) {
				ls.add(new PrivateChatMessageUIAdaptor(user, cm));
				if(!id.equals(cm.getId())) {

//					Debuger.debug("PrivateChatMessage.onRead()" + id + "	- " + cm.getId() );
					cm.onRead();
				}
			}
		}
		return ls;
	}
}
