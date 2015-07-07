package cn.vgame.a.gm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.bag.Bag;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重新加载系统配置(刘雨诚请无视) ----------------- 无
 */
public class SetUserAction extends ActionSupport {

	private static final long serialVersionUID = -6099859675509539457L;

	private long addCoin;
	String newNick;
	String isJinYan;
	String isFengHao;
	private String reason;
	private String roleId;
	private int addLaBa;
	private int reduceLaBa;
	
	

	private int addJiangQuan;
	private int reduceJiangQuan;
	
	
	
	
	private long reduceCoin;

	public String getNewNick() {
		return newNick;
	}

	public void setNewNick(String newNick) {
		this.newNick = newNick;
	}

	public String getIsJinYan() {
		return isJinYan;
	}

	public void setIsJinYan(String isJinYan) {
		this.isJinYan = isJinYan;
	}

	public String getIsFengHao() {
		return isFengHao;
	}

	public void setIsFengHao(String isFengHao) {
		this.isFengHao = isFengHao;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");

		if (reason == null || reason.isEmpty()) {
			String reason = request.getParameter("reason");
			if (reason == null || reason.isEmpty()) {
				throw new RuntimeException("reason must not be null");
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("roleId", getRoleId());

		Role role = Server.getRole(getRoleId());

		boolean isJinYan = "on".equals(getIsJinYan());
		boolean isFengHao = "on".equals(getIsFengHao());

		RoleDto dto = role.getDto();

		long oldCoin = dto.getCoin();

		dto.setNick(newNick);
		dto.setHasJinYan(isJinYan);
		dto.setHasFengHao(isFengHao);
		RoleDao dao = Daos.getRoleDao();
		dao.save(dto);

		Object gmUserId = session.getAttribute("gmUserId");

		Bag bag = role.getBag();

		if (addJiangQuan > 0) {
			role.addJiangQuan(addJiangQuan);
		}

		if (reduceJiangQuan > 0) {
			role.reduceJiangQuan(reduceJiangQuan);
		}
		
		if (addLaBa > 0) {
			bag.add(10001, addLaBa);
		}

		if (reduceLaBa > 0) {
			bag.remove(10001, reduceLaBa);
		}

		if (reduceCoin > 0) {
			role.reduceCoin(reduceCoin);
			role.addCoinLog(-reduceCoin, gmUserId, "gm reduce");
		}

		if (addCoin > 0) {
			role.addCoin(addCoin);
			role.addCoinLog(addCoin, gmUserId, "gm add");
		}

		long coinNow = role.getCoin();

		Log.d("set user", role.getId(), newNick, addLaBa, reduceLaBa, addCoin,
				reduceCoin, oldCoin, coinNow, gmUserId, reason);

		return super.execute();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getAddLaBa() {
		return addLaBa;
	}

	public void setAddLaBa(int addLaBa) {
		if (addLaBa < 0)
			addLaBa = 0;
		this.addLaBa = addLaBa;
	}

	public long getAddCoin() {
		return addCoin;
	}

	public void setAddCoin(long addCoin) {
		if (addCoin < 0)
			addCoin = 0;
		this.addCoin = addCoin;
	}

	public int getReduceLaBa() {
		return reduceLaBa;
	}

	public void setReduceLaBa(int reduceLaBa) {
		if (reduceLaBa < 0)
			reduceLaBa = 0;
		this.reduceLaBa = reduceLaBa;
	}

	public long getReduceCoin() {
		return reduceCoin;
	}

	public void setReduceCoin(long reduceCoin) {
		if (reduceCoin < 0)
			reduceCoin = 0;
		this.reduceCoin = reduceCoin;
	}

	public int getAddJiangQuan() {
		return addJiangQuan;
	}

	public void setAddJiangQuan(int addJiangQuan) {
		this.addJiangQuan = addJiangQuan;
	}

	public int getReduceJiangQuan() {
		return reduceJiangQuan;
	}

	public void setReduceJiangQuan(int reduceJiangQuan) {
		this.reduceJiangQuan = reduceJiangQuan;
	}

}
