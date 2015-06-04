package cn.mxz.gm;

import java.util.Map;

import com.lemon.commons.socket.ISocket;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.messagesender.UserMessageSender;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.SystemLog;

public class SendMailHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		Parameters p = new Parameters(parameters);
		
		String roleId = p.getString("role_id");
		String title = p.getString("title");
		String body = p.getString("body");
		String type = p.getString("type");
		String senderName = p.getString("sender_name");
//		http://42.62.9.127:21508?role_id=100011&title=aaa&body=aaa&type=111&sender_name=1111
//		http://localhost:21525/SendMail?role_id=lc11&title=aaa&body=aaa&type=111&sender_name=1111
		City user = CityFactory.getCity(roleId);
		if( user == null ){			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
		
		SystemLog.debug("收到了 GM 回答消息!" + "," +  roleId + "," +  title + "," +  body + "," +  type + "," +  senderName);
		UserCounter c = user.getUserCounterHistory();
		c.mark(CounterKey.RECEIVE_KE_FU_MESSAGE);

//		boolean mark = c.isMark(CounterKey.RECEIVE_KE_FU_MESSAGE);
//		MessageFactory.getChat().receiveGmMessage(user.getSocket(), title, body, type, senderName);
		
		ISocket s = user.getSocket();
		UserPro b = new UserBuilder().build(user);
		UserMessageSender sd = MessageFactory.getUser();
		sd.onUpdateUserList(s, b);
		return new NormalHttpResponse().setValue(1);
		
	}

}
