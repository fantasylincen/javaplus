package com.cnbizmedia.recharge;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.cnbizmedia.Server;
import com.cnbizmedia.config.GameServersXml;
import com.cnbizmedia.config.ServerNode;
import com.cnbizmedia.gen.dto.MongoGen.OrderDto;
import com.cnbizmedia.user.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 充值到游戏
 */
public class RechargeCzdyxAction extends ActionSupport {

	private static final long serialVersionUID = -7153959615616607087L;
	private static final int INIT_VALUE = 100000000;
	private HttpSession session;

	int count;

	/**
	 * serverId
	 */
	String id;
	String error;

	/**
	 * userId
	 */
	String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	private static AtomicInteger orderId = new AtomicInteger(INIT_VALUE);

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			return ERROR;

		ServerNode node = GameServersXml.getServer(getId());

		if (node == null)
			return ERROR;

		
		OrderDto o = new OrderDto();

		RechargeManager manager = Server.getRechargeManager();
		long time = System.currentTimeMillis();
		o.setId(time + "" + nextId());
		o.setCount(getCount());
		o.setServerId(node.getString("id"));
		o.setTime(time);
		o.setRechargeUserId(userId.trim());
		o.setUserId(getUserId());
		o.setReason("");

		int vbNeed = OrderUtil.getVbNeed(o);
		User user = Server.loadUserById(o.getRechargeUserId());
		user.reduceVb(vbNeed);
		
		manager.addOrder(o);
		

		return SUCCESS;
	}

	private String getUserId() {

		String uid = getUid();
		if (uid == null || uid.isEmpty()) {
			return (String) session.getAttribute("userId");
		}
		return uid;
	}

	private int nextId() {
		if (orderId.get() > 999999999)
			orderId.set(INIT_VALUE);
		return orderId.addAndGet(1);
	}

}
