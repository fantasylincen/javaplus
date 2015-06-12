package cn.vgame.a.recharge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Closer;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.AppStoreRechargeLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.result.ErrorResult;

import com.alibaba.fastjson.JSONObject;
import com.ning.http.util.Base64;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 充值 -----------------
 * 
 * A.正常情况: { }
 * 
 * B.错误: 标准错误
 * 
 */
public class Recharge4AppStoreBuyClientAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 527271364543104039L;

	private HttpServletResponse response;

	private HttpServletRequest request;

	private HttpSession session;
	
	AppStoreRechargeLogDto dto = new AppStoreRechargeLogDto();
	
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

	public JSONObject verifyReceipt(String receipt)  {

		return verify(receipt.getBytes(),
				"https://buy.itunes.apple.com/verifyReceipt");
	}

	private JSONObject verify(byte[] receipt, String site)  {

		StringBuffer sb;
		try {
			URL url = new URL(site); 

			HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setAllowUserInteraction(false);

			String encodedReceipt = new String(Base64.encode(receipt));

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("receipt-data", encodedReceipt);

			Log.d(" encode " + encodedReceipt);
			
			PrintStream ps = new PrintStream(connection.getOutputStream());
			ps.print(jsonObject.toString());
			ps.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String str;
			sb = new StringBuffer();
			while ((str = br.readLine()) != null)
				sb.append(str);
			br.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		String response = sb.toString();
		JSONObject result = JSONObject.parseObject(response);
		int status = result.getInteger("status");


//		21000 App Store无法读取你提供的JSON数据
//		21002 收据数据不符合格式
//		21003 收据无法被验证
//		21004 你提供的共享密钥和账户的共享密钥不一致
//		21005 收据服务器当前不可用
//		21006 收据是有效的，但订阅服务已经过期。当收到这个信息时，解码后的收据信息也包含在返回内容中
//		21007 收据信息是测试用（sandbox），但却被发送到产品环境中验证
//		21008 收据信息是产品环境中使用，但却被发送到测试环境中验证
		if (status == 0) {
			return (JSONObject) result.get("receipt");
		} else if (status == 21007) {
			return verify(receipt,
					"https://sandbox.itunes.apple.com/verifyReceipt");
		} else {
			throw new ErrorResult(status + "").toException();
		}
	}

	private String receipt;
	
	private void recharge() {


		JSONObject obj = verifyReceipt(receipt);
		String transaction_id = obj.getString("transaction_id");
		
		
		// 如果订单号已经处理过了,就不再 处理
		AppStoreRechargeLogDto log = Daos.getAppStoreRechargeLogDao().get(transaction_id);
		if (log != null)
			return;

		
		String product_id = obj.getString("product_id");
		
		String roleId = (String) session.getAttribute("roleId");
		Role role = Server.getRole(roleId);
		
		dto.setProductId(product_id);

		long coin = getCoinAdd();

		dto.setId(transaction_id);
		dto.setRoleId(roleId);
		dto.setNick(role.getNick());
		dto.setCoin(coin);
		dto.setFee(getFee());
		

		role.addCoin(coin);
		role.addRechargeHistory(coin, "appstore");


		Log.d("recharge success", role.getId(), role.getNick(), coin,
				role.getCoin());
		Daos.getAppStoreRechargeLogDao().save(this.dto);
	}

	/**
	 * 人民币 分
	 */
	private int getFee() {
		Sheet sheet = Server.getXml().get("recharge-D");
		String productId = dto.getProductId();
		Row row = find(productId, sheet);
		double yuan = row.getDouble("rmb");
		return (int) (yuan * 100);
	}

	private long getCoinAdd() {
		Sheet sheet = Server.getXml().get("recharge-D");
		String productId = dto.getProductId();
		Row row = find(productId, sheet);
		return row.getInt("jinDou");
	}


	private Row find(String productId, Sheet sheet) {
		List<Row> all = sheet.getAll();
		for (Row row : all) {
			String str = row.get("appstore");
			if(str.equals(productId)) {
				return row;
			}
		}
		throw new NullPointerException();
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

}
