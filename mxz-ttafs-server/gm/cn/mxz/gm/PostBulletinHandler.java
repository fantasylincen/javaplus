package cn.mxz.gm;

import java.util.Map;

import message.S;
import cn.mxz.util.message.MessageSenderToAllUp;

/**
 * 发送广播公告
 * @author Administrator
 *
 */
public class PostBulletinHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		String content = (String) parameters.get( "content" );
		
		MessageSenderToAllUp  a = new MessageSenderToAllUp();
		a.sendMessage(S.S0, content);
		return responseSuccess();
	}
}
