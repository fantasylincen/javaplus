package cn.mxz.gm;

import java.util.Map;

import cn.mxz.city.City;

public class ChangeVipLevel extends AbstractHandler {


	@Override
	protected String doGet(Map<String, Object> parameters) {
		City user = getUser( parameters );
		if( user == null ){			
			
			
			
			return responseErr( ErrorCode.USER_NOT_FOUND ); 
		}
		int level = Integer.parseInt((String) parameters.get( "vip_level" ));
		 user.getVipPlayer().setLevel(level);
		StringBuilder sb = new StringBuilder( "<?xml version=\"1.0\" encoding=\"utf-8\"?><Response><result>1</result></Response>");
		return sb.toString();
	}
	

}
