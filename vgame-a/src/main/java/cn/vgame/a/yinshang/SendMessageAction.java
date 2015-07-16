package cn.vgame.a.yinshang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.message.MessageManager;
import cn.vgame.share.EncodingUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 发送喊话消息 , 消耗 game.xml - const.HAN_HUA_LA_BA 个喇叭 -----------------
 * 
 * A.正常情况: { boolean suceess = true }
 * 
 * B.错误: 标准错误
 */
public class SendMessageAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7254252286751992819L;
	private String message;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;

	
	@Override
	public String execute() throws Exception {
		
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();
		if(message == null || message.isEmpty())
			return SUCCESS;
		message = EncodingUtil.iso2Utf8(message);
		message = Util.Sencitive.sencitive(message);
		checkLen();
		sendMessage();
		return SUCCESS;
	}


	private void sendMessage() {
		MessageManager manager = Server.getMessageManager();
		Role role = Server.getRole(session);
		manager.sendMessage(role.getNick(), getMessage());
	}

	private void checkLen() {
		int len = Server.getConst().getInt("HAN_HUA_MESSAGE_LEN");
		if (getMessage().length() > len) {
			throw new YinShangException("消息长度不可超过:" + len);
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
