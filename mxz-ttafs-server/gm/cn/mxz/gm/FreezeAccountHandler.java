package cn.mxz.gm;

import java.util.Map;

import cn.mxz.city.City;

/**
 * 封禁账号
 * @author Administrator
 *
 */
public class FreezeAccountHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		
		//蓝港（李阳）要求屏蔽
//		City user = getUser( parameters );
//		if( user == null ){			
//			return responseErr( ErrorCode.USER_NOT_FOUND ); 
//		}
//		
//		user.getFreezeManager().freeze();
		return responseSuccess();
	}

}
