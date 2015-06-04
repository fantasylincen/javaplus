package cn.mxz.gm;

import java.util.Map;

import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;

public class KickRoleHandler extends AbstractHandler {


	@Override
	protected String doGet(Map<String, Object> parameters) {
//		City user = getUser( parameters );
//		if( user == null ){			
//			return responseErr( ErrorCode.USER_NOT_FOUND ); 
//		}
//		
//		if( user.getSocket() != null ){
//			MessageFactory.getSystem().kick(user.getSocket());
//			user.getSocket().close();
//		}
		
		return responseSuccess();
	}

}
