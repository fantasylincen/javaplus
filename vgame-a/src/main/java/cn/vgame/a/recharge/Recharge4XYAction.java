package cn.vgame.a.recharge;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.XYRechargeLogDto;

import com.opensymphony.xwork2.ActionSupport;
import com.xyzs.server.PayCallBack;
import com.xyzs.signature.GenSafeSign;

/**
 * 充值 -----------------
 * 
 * A.正常情况: { }
 * 
 * B.错误: 标准错误
 * 
 */
public class Recharge4XYAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298354778729941493L;

	public class Add {
		long coin;
		long jiangQuan;

		public Add(Row row) {

			coin = row.getInt("jinDou");
			jiangQuan = row.getInt("jiangQuan");
		}

		public long getCoin() {
			return coin;
		}

		public void setCoin(long coin) {
			this.coin = coin;
		}

		public long getJiangQuan() {
			return jiangQuan;
		}

		public void setJiangQuan(long jiangQuan) {
			this.jiangQuan = jiangQuan;
		}

	}

	static final String AppId = "100019423";
	static final String AppKey = "uiwasZGOzQ1RT6E7zfYi0YcyVouPVVZ6";
	static final String PayKey = "qnxNQSKVUjoUfwp2ozM4lSaKQZKRCCI8";

	XYRechargeLogDto dto = new XYRechargeLogDto();

	private HttpServletResponse response;

	private HttpServletRequest request;

	private HttpSession session;

	@Override
	public String execute() throws Exception {
		response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");
		request = ServletActionContext.getRequest();
		session = request.getSession();

		Map<String, String[]> parameterMap = request.getParameterMap();
		Log.d(parameterMap);

		PrintWriter out = response.getWriter();

		try {
			Log.d("ready xy recharge", dto.toString());

			// 发货处理，请在callBack方法中处理发货逻辑
			String callBack = callBack(getOrderid(), getUid(), getServerid(),
					getAmount(), getExtra(), getTs(), getSign(), getSig());
			Log.d("recharge xy success", dto.toString());
			out.println(callBack);
		} finally {
			out.flush();
			Closer.close(out);
		}
		return SUCCESS;
	}

	/**
	 * 支付回调方法
	 */
	public String callBack(String orderid, String uid, String serverid,
			String amount, String extra, String ts, String sign, String sig) {
		if (orderid.isEmpty() || uid.isEmpty()) {
			return responseResult(1);
		}

		Map maps = new TreeMap();
		maps.put("uid", uid);
		maps.put("orderid", orderid);
		maps.put("serverid", serverid);
		maps.put("amount", amount);
		maps.put("extra", extra);
		maps.put("ts", ts);

		// 验证App签名串
		String genSafeSign = GenSafeSign.getGenSafeSign(maps, AppKey);
		if (!genSafeSign.equalsIgnoreCase(sign)) {
			return responseResult(6);
		}

		// 如果支付签名串存在就验证
		if (!sig.isEmpty()) {
			genSafeSign = GenSafeSign.getGenSafeSign(maps, PayKey);
			if (!genSafeSign.equalsIgnoreCase(sig)) {
				return responseResult(6);
			}
		}

		try {
			// 如果订单号已经处理过了,就不再 处理
			XYRechargeLogDto log = Daos.getXYRechargeLogDao().get(getOrderid());
			if (log != null)
				return responseResult(4);

			String roleId = getExtra();

			Role role = Server.getRole(roleId);
			Add a = getAdd();
			role.addCoin(a.getCoin());
			role.addRechargeHistory(a.getCoin(), "xy");
			role.addJiangQuan(a.getJiangQuan());

			this.dto.setRoleId(roleId);
			this.dto.setNick(role.getNick());
			this.dto.setCoin(a.getCoin());
			this.dto.setJiangQuan(a.getJiangQuan());

			Log.d("recharge xy success", role.getId(), role.getNick(),
					a.getCoin(), role.getCoin());
			Daos.getXYRechargeLogDao().save(this.dto);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(e);

			return responseResult(8);
		}

		return responseResult(0);
	}

	/**
	 * 响应结果方法
	 * 
	 * @throws JSONException
	 */
	public static String responseResult(int code) {
		String msg = "";

		switch (code) {
		case 0:
			msg = "回调成功";
			break;
		case 1:
			msg = "参数错误";
			break;
		case 2:
			msg = "玩家不存在";
			break;
		case 3:
			msg = "游戏服不存在";
			break;
		case 4:
			msg = "订单已存在";
			break;
		case 5:
			msg = "透传信息错误";
			break;
		case 6:
			msg = "签名校验错误";
			break;
		case 7:
			msg = "数据库错误";
			break;
		case 8:
		default:
			code = 8;
			msg = "其它错误";
			break;
		}

		JSONObject json = new JSONObject();

		try {
			json.put("ret", code);
			json.put("msg", msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json.toString();
	}

	private Add getAdd() {
		double rmmb = (getFee() + 0) / 100;
		Sheet sheet = Server.getXml().get("recharge-xy");
		List<Row> all = sheet.getAll();
		for (Row row : all) {
			double rmb = row.getDouble("rmb");
			if (Math.abs(rmb - rmmb) < 0.001) {
				return new Add(row);
			}
		}

		boolean isDebug = Server.getConfig().getBoolean("isDebug");
		Log.d("isDebug", isDebug);
		if (isDebug)
			return getFirst(all);

		throw new RuntimeException("recharge record not found");
	}

	private int getFee() {
		String amount = getAmount();
		Double yuan = new Double(amount);
		return (int) (yuan * 100);
	}

	private Add getFirst(List<Row> all) {
		Row row = all.get(0);
		return new Add(row);
	}

	public String getOrderid() {
		return dto.getOrderid();
	}

	public String getUid() {
		return dto.getUid();
	}

	public String getAmount() {
		return dto.getAmount();
	}

	public String getServerid() {
		return dto.getServerid();
	}

	public String getExtra() {
		return dto.getExtra();
	}

	public String getTs() {
		return dto.getTs();
	}

	public String getSign() {
		return dto.getSign();
	}

	public String getSig() {
		return dto.getSig();
	}

	public String getRoleId() {
		return dto.getRoleId();
	}

	public String getNick() {
		return dto.getNick();
	}

	public long getCoin() {
		return dto.getCoin();
	}

	public long getJiangQuan() {
		return dto.getJiangQuan();
	}

	public void setOrderid(String orderid) {
		dto.setOrderid(orderid);
	}

	public void setUid(String uid) {
		dto.setUid(uid);
	}

	public void setAmount(String amount) {
		dto.setAmount(amount);
	}

	public void setServerid(String serverid) {
		dto.setServerid(serverid);
	}

	public void setExtra(String extra) {
		dto.setExtra(extra);
	}

	public void setTs(String ts) {
		dto.setTs(ts);
	}

	public void setSign(String sign) {
		dto.setSign(sign);
	}

	public void setSig(String sig) {
		dto.setSig(sig);
	}

	public void setRoleId(String roleId) {
		dto.setRoleId(roleId);
	}

	public void setNick(String nick) {
		dto.setNick(nick);
	}

	public void setCoin(long coin) {
		dto.setCoin(coin);
	}

	public void setJiangQuan(long jiangQuan) {
		dto.setJiangQuan(jiangQuan);
	}

}
