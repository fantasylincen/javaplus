package cn.vgame.a.yinshang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 赠送金豆 -----------------
 * 
 * A.正常情况: { coin: 1, // 身上的金豆 bankCoin:2// 银行中的金豆 }
 * 
 * B.错误: 标准错误
 */
public class SendCoinAction extends ActionSupport {

	private static final long serialVersionUID = -2558984329379031875L;
	private String coin;
	private String nick;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;
	private Long coinReal;

	@Override
	public String execute() throws Exception {
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		if(coin == null || nick == null)
			return SUCCESS;
		
		checkIsNumber();
		this.coinReal = new Long(coin);
		
		
		Role role = Server.getRole(session);
		
		checkCoin(role);

		Role otherRole = Server.getRole(getId(nick));

		if (otherRole == null) {
			throw new YinShangException("未找到指定玩家:" + nick);
		}

		if (role.getId().equals(otherRole.getId())) {
			throw new YinShangException("不能给自己赠送金豆");
		}

		try {
			role.sendCoin(otherRole.getId(), coinReal, role.getBankPassword());
		} catch (Exception e) {
			throw new YinShangException(e.getMessage());
		}

		return SUCCESS;
	}

	private void checkCoin(Role role) {
		if(coinReal <= 0 || coinReal > 100000000) {
			throw new YinShangException("金豆数量非法:" + coin);
		}
		if(role.getCoin() < coinReal)
			throw new YinShangException("您的金豆不够了");
	}

	private void checkIsNumber() {
		if(!coin.matches("[0-9]+")) {
			throw new YinShangException("请输入正确的金豆数量");
		}
	}

	/**
	 * 获取角色的id
	 * 
	 * @param nickOrId
	 */
	private String getId(String nickOrId) {
		RoleDtoCursor c = Daos.getRoleDao().findByNick(nickOrId);
		if (c.hasNext())
			return c.next().getId();
		return nickOrId;
	}


	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
//		nick = EncodingUtil.iso2Utf8(nick);
		nick = nick.trim();
		this.nick = nick;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

}
