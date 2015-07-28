package cn.vgame.a.message;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.share.EncodingUtil;

/**
 * 发送喊话消息 , 消耗 game.xml - const.HAN_HUA_LA_BA 个喇叭 -----------------
 * 
 * A.正常情况: { boolean suceess = true }
 * 
 * B.错误: 标准错误
 */
public class SendMessageAction extends JsonActionAfterRoleEnterGame {

	private static final long serialVersionUID = -1677339523616150366L;

	private String message;


	@Override
	public Object run() {
		message = EncodingUtil.iso2Utf8(message);
		checkJinYan();
//		checkSencitive();
		message = Util.Sencitive.sencitive(message);
		
		checkLen();
		reduceLaba();
		sendMessage();
		Log.d("send message", message);
		return new SuccessResult();
	}

	private void checkJinYan() {
		if (role.hasJinYan())
			throw new ErrorResult(10015).toException();
	}

	private void sendMessage() {
		MessageManager manager = Server.getMessageManager();
		manager.sendMessage(role.getNick(), getMessage());
	}

	private void reduceLaba() {
		int constInt = getConstInt("HAN_HUA_LA_BA");
		role.getBag().remove(10001, constInt);
	}

	private void checkLen() {
		int len = getConstInt("HAN_HUA_MESSAGE_LEN");
		if (getMessage().length() > len) {
			throw new ErrorResult(10008, len).toException();
		}
	}

	private void checkSencitive() {
		boolean canUse = Util.Sencitive.canUse(getMessage());
		if (!canUse) {
			throw new ErrorResult(10010).toException();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
