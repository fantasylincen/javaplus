package cn.mxz.zbsr;

import com.lemon.commons.socket.ISocket;

import message.S;
import cn.mxz.city.City;
import cn.mxz.city.Zbsr;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.messagesender.UserMessageSender;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;

/**
 * 装备商人
 *
 * @author 林岑
 *
 */
public class ZbsrTransformImpl implements ZbsrTransform {

	private City	user;

	@Override
	public ZbsrUI getUI() {
		Zbsr zbsr = user.getZbsr();
		return new ZbsrUIAdaptor(zbsr);
	}

	@Override
	public ZbsrUI refresh() {
		Zbsr zbsr = user.getZbsr();
		zbsr.refresh();
		updateUser();
		return getUI();
	}

	@Override
	public ZbsrUI exchange(int id) {
		Zbsr zbsr = user.getZbsr();
		zbsr.exchange(id);
		
		updateUser();
		
		return getUI();
	}

	private void updateUser() {
		UserPro b = new UserBuilder().build(user);
		UserMessageSender s = MessageFactory.getUser();
		ISocket socket = user.getSocket();
		s.onUpdateUserList(socket, b);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
