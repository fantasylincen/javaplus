package cn.mxz.gm;

import java.util.Map;

import cn.mxz.city.City;

public class UnfreezeAccountHandler extends AbstractHandler {


	@Override
	protected String doGet(Map<String, Object> parameters) {
		//蓝港（李阳）要求屏蔽
//		City user = getUser( parameters );
//		if( user == null ){			
//			return responseErr( ErrorCode.USER_NOT_FOUND ); 
//		}
//		
//		user.getFreezeManager().unFreeze();
		return responseSuccess();
		
	}

}
