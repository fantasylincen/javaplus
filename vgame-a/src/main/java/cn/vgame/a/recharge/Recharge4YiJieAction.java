package cn.vgame.a.recharge;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.YiJieRechargeLogDto;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 充值 -----------------
 * 
 * A.正常情况: { }
 * 
 * B.错误: 标准错误
 * 
 */
public class Recharge4YiJieAction extends ActionSupport {

	private static final long serialVersionUID = 5948951589183031863L;

	public static final String KEY = "V4I9FSEJKPA2MWNSKVVFBXEW758F9HIM";

	// app
	// String
	// 是
	// 1234567890ABCDEF
	// 十六进制字符串形式的应用ID
	// cbi
	// String
	// 否
	// CBI123456
	// CP自定义的透传参数，原样返回给CP。
	// ct
	// long
	// 是
	// 1376578903
	// 支付完成时间
	// fee
	// int
	// 是
	// 100
	// 金额（分）
	// pt
	// long
	// 是
	// 1376577801
	// 付费时间，订单创建服务器UTC时间戳（毫秒）
	// sdk
	// String
	// 是
	// 09CE2B99C22E6D06
	// 渠道在易接服务器的ID
	// ssid
	// String
	// 是
	// 123456
	// 订单在渠道平台上的流水号
	// st
	// int
	// 是
	// 1
	// 是否成功标志，1标示支付成功，其余都表示支付失败，一定要判断这个标志位。
	// tcd
	// String
	// 是
	// 137657AVDEDFS
	// 订单在易接服务器上的订单号
	// uid
	// String
	// 是
	// 1234
	// 付费用户在渠道平台上的唯一标记
	// ver
	// String
	// 是
	// 1
	// 协议版本号，目前为“1”
	// sign
	// String
	// 是
	// f67893489267ea3
	// 上述内容的数字签名，方法在下文会说明

	YiJieRechargeLogDto dto = new YiJieRechargeLogDto();

	private HttpServletResponse response;

	private HttpServletRequest request;

	private HttpSession session;

	public String getSsid() {
		return dto.getSsid();
	}

	public String getApp() {
		return dto.getApp();
	}

	public String getCbi() {
		return dto.getCbi();
	}

	public long getCt() {
		return dto.getCt();
	}

	public int getFee() {
		return dto.getFee();
	}

	public long getPt() {
		return dto.getPt();
	}

	public String getSdk() {
		return dto.getSdk();
	}

	public String getSign() {
		return dto.getSign();
	}

	public int getSt() {
		return dto.getSt();
	}

	public String getTcd() {
		return dto.getTcd();
	}

	public String getUid() {
		return dto.getUid();
	}

	public String getVer() {
		return dto.getVer();
	}

	public void setSsid(String ssid) {
		dto.setSsid(ssid);
	}

	public void setApp(String app) {
		dto.setApp(app);
	}

	public void setCbi(String cbi) {
		dto.setCbi(cbi);
	}

	public void setCt(long ct) {
		dto.setCt(ct);
	}

	public void setFee(int fee) {
		dto.setFee(fee);
	}

	public void setPt(long pt) {
		dto.setPt(pt);
	}

	public void setSdk(String sdk) {
		dto.setSdk(sdk);
	}

	public void setSign(String sign) {
		dto.setSign(sign);
	}

	public void setSt(int st) {
		dto.setSt(st);
	}

	public void setTcd(String tcd) {
		dto.setTcd(tcd);
	}

	public void setUid(String uid) {
		dto.setUid(uid);
	}

	public void setVer(String ver) {
		dto.setVer(ver);
	}

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
			Log.d("ready recharge", dto.toString());
			recharge();
			Log.d("recharge success", dto.toString());
			out.println("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("recharge error", dto.toString());
			out.println("ERROR");
		} finally {
			out.flush();
			Closer.close(out);
		}
		return SUCCESS;
	}

	private void recharge() {

		//如果订单号已经处理过了,就不再 处理
		YiJieRechargeLogDto log = Daos.getYiJieRechargeLogDao().get(getSsid());
		if(log != null)
			return;
		
		
		checkSign();
		checkSt();

		String roleId = getCbi();

		Role role = Server.getRole(roleId);
		long add = getCoinAdd();
		role.addCoin(add);
		role.addRechargeHistory(add, "yijie");
		
		this.dto.setRoleId(roleId);
		this.dto.setNick(role.getNick());
		this.dto.setCoin(add);
		
		Log.d("recharge success", role.getId(), role.getNick(), add,
				role.getCoin());
		Daos.getYiJieRechargeLogDao().save(this.dto);
	}

	private long getCoinAdd() {
		double rmmb = (getFee() + 0) / 100;
		Sheet sheet = Server.getXml().get("recharge-A");
		List<Row> all = sheet.getAll();
		for (Row row : all) {
			double rmb = row.getDouble("rmb");
			if (Math.abs(rmb - rmmb) < 0.001) {
				return row.getInt("jinDou");
			}
		}
		return getLast(all);
	}

	private long getLast(List<Row> all) {
		Row row = all.get(all.size() - 1);
		return row.getInt("jinDou");
	}

	private void checkSt() {
		if (getSt() != 1)
			throw new RuntimeException("recharge faild");
	}

	private void checkSign() {
		List<String> items = Lists.newArrayList();
		items.add("app" + "=" + dto.getApp());
		items.add("cbi" + "=" + dto.getCbi());
		items.add("ct" + "=" + dto.getCt());
		items.add("fee" + "=" + dto.getFee());
		items.add("pt" + "=" + dto.getPt());
		items.add("sdk" + "=" + dto.getSdk());
		items.add("ssid" + "=" + dto.getSsid());
		items.add("st" + "=" + dto.getSt());
		items.add("tcd" + "=" + dto.getTcd());
		items.add("uid" + "=" + dto.getUid());
		items.add("ver" + "=" + dto.getVer());

		String sign = Util.Collection.linkWith("&", items);

		sign = Util.Secure.md5(sign + KEY);
		
		if (!sign.equals(getSign())) {
			throw new RuntimeException("sign error" + "," + sign + ","
					+ getSign() + "," + dto.toString());
		}
	}

}
