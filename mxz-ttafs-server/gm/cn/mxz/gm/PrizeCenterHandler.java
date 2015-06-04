package cn.mxz.gm;

import java.util.Collection;
import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.IPrizeCenter;


public class PrizeCenterHandler extends AbstractHandler {

	private final String IS_ALL = "isAll";//是否发给所有用户 1：是    0：否
	@Override
	protected String doGet(Map<String, Object> parameters) {
		String isAll = (String)parameters.get(IS_ALL);
		if( isAll != null && isAll.equals( "1" )){//全服补偿
			Collection<City> all = WorldFactory.getWorld().getAll();
			for (City city : all) {
				sendPrize(parameters, city);
			}
		}
		else{

			City user = getUser( parameters );
			if( user == null ){
				return responseErr( ErrorCode.USER_NOT_FOUND );
			}

			sendPrize(parameters, user);
		}
		return responseSuccess();


	}
	private void sendPrize(Map<String, Object> parameters, City user) {
		IPrizeCenter pc = user.getPrizeCenter();

		if( parameters.get("prizePackageId") == null ){//直接输入奖励内容
			String prize = (String) parameters.get("prize");
			String desc = (String) parameters.get("desc");
			String title = (String) parameters.get("title");
			//String desc = (String) parameters.get("");
			int type = Integer.parseInt( (String) parameters.get("type") );

			//pc.addPrize(type, prize, desc, title);//屏蔽以适应蓝港的版本，

		}
	}

//	boolean checkValid( )

}
