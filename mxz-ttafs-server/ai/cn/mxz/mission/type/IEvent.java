package cn.mxz.mission.type;

import cn.mxz.city.City;

public interface IEvent {
	
	Object run( City user );

	/**
	 * 返回给客户端的相应简要内容
	 * @return
	 */
	int getBrief();
	
	
	EventType getType();

	/**
	 * 获取关卡的完整参数
	 * @return 
	 */
	String getMissionArg();
	
	//String transformToString();
	

	
}
