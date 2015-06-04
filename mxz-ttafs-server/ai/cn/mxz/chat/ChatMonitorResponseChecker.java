package cn.mxz.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChatMonitorResponseChecker {

	//1.2.2	返回数据格式和结果标识
	//返回数据的格式是json格式
	//{"result":"value"}
	//Value值对应的错误信息
//	          返回value值	             对应信息
	//-100	Key值认证错误
	//-101	服务端没有配置接收该游戏日志信息
	//1	服务端接收信息成功

	
	public void check(String content) {
		JSONObject parse = JSON.parseObject(content);
		
		String value = parse.get("result").toString();
		if("-100".equals(value)) {
			throw new ChatMonitorException("Key值认证错误");
		}
		if("-101".equals(value)) {
			throw new ChatMonitorException("服务端没有配置接收该游戏日志信息");
		}
		if("1".equals(value)) {
			return;
		} else {
			throw new ChatMonitorException("未知错误:" + value);
		}
	}

}
