package cn.vgame.a.account;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.config.ServerConfig;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.share.GameException;

/**
 * 玩家进入游戏服务器
 * 
 * -----------------
 * 
 * 
 * A.正常情况:
 * 	{
 * 		String id :  客户端传过来的userId,
 * 		String sessionId,
 * 	}
 * 
 * B.错误:
 *  标准错误: 10003 token error
 * 
 * 
 * 说明: 标准错误, 客户端需要对所有包  统一处理
 *    {
 *    	String error 错误文字,
 *    	int code 这个错误对应到配置表 messages 里面的错误号,
 *      String args [    比如 消息: 10008    message too long len must < %s0     那么 args =[ 10] 时 , 该消息表示  message too long len must < 10
 *      	String arg1...
 *      	String arg2...
 *      	String arg3...
 *      ],
 *    }
 */
public class EnterServerAction extends JsonAction {

	private static final long serialVersionUID = -6099859675509539457L;

	private String token;
	private String userId;

	@Override
	public Object exec() {
		String token = getToken();
		String tokenKey = getTokenKey();
		boolean tokenRight = Util.Token.isTokenRight(tokenKey, getUserId(), token);
		if (tokenRight) {
			session.setAttribute("userId", getUserId());
			Log.d("enter game " + getUserId());
			return new EnterServerResult(getUserId(), session.getId());
		} else {
			throw new ErrorResult(10003).toException();
		}

	}

	private String getTokenKey() {
		ServerConfig c = Server.getConfig();
		String key = c.getString("tokenKey");
		return key;
	}

	/**
	 * 验证串
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 玩家ID
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
