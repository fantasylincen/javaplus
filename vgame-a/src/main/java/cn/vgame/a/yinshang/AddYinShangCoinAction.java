package cn.vgame.a.yinshang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.YinShangDao;
import cn.vgame.a.gen.dto.MongoGen.YinShangDto;

import com.opensymphony.xwork2.ActionSupport;

public class AddYinShangCoinAction extends ActionSupport {

	private static final long serialVersionUID = -7216556879198306440L;

	String id;
	private long coin;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	private HttpSession session;


	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		YinShangDao dao = Daos.getYinShangDao();

		YinShangDto dto = dao.get(id);
		String roleId = dto.getRoleId();
		
		Role role = Server.getRole(roleId);
		
		Object gmUserId = session.getAttribute("gmUserId");
		
		if (getCoin() > 0) {
			role.addCoin(getCoin());
			role.addCoinLog(getCoin(), gmUserId, "gm add");

			long coinNow = role.getCoin();

			Log.d("gm add yinshang coin",gmUserId, role.getId(), getCoin(), id, coinNow);

			dao.save(dto);
		}

		return SUCCESS;
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

}
