package cn.vgame.a.recharge;

import java.util.Map;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDao;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDto;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderFinishDao;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderFinishDto;
import cn.vgame.share.Xml;

public class ZfbRechargeManager {

	/**
	 * 创建支付宝订单
	 * 
	 * @param pars
	 * @param idd
	 * @param roleId
	 */
	public void createZfbOrder(Map<String, String> pars, String idd,
			String roleId) {

		int id = new Integer(idd);

		ZfbOrderDao dao = Daos.getZfbOrderDao();
		ZfbOrderDto dto = dao.createDTO();
		dto.setId(pars.get("out_trade_no"));
		Sheet sheet = Xml.getSheet("recharge-zfb");
		Row row = sheet.get(id);
		int jinDou = row.getInt("jinDou");
		dto.setCount(jinDou);
		dto.setPrice(pars.get("total_fee"));
		dto.setTime(System.currentTimeMillis());
		dto.setRoleId(roleId);
		dto.setJiangQuan(row.getInt("jiangQuan"));

		dao.save(dto);
	}

	/**
	 * 处理订单
	 */
	public void processOrder(String orderId) {

		ZfbOrderDao dao = Daos.getZfbOrderDao();
		ZfbOrderDto dto = dao.get(orderId);
		String roleId = dto.getRoleId();

		Role role = Server.getRole(roleId);
		long add = dto.getCount();
		Log.d("recharge for", roleId, "coin add", add, role.getNick());
		role.addCoin(add);
		role.addRechargeHistory(add, "zfb");
		role.addJiangQuan(dto.getJiangQuan());

		Log.d("recharge success", role.getId(), role.getNick(), add,
				role.getCoin());

		dao.delete(dto);

		ZfbOrderFinishDao fDao = Daos.getZfbOrderFinishDao();
		ZfbOrderFinishDto d1 = fDao.createDTO();
		d1.setCount(dto.getCount());
		d1.setId(dto.getId());
		d1.setPrice(dto.getPrice());
		d1.setTime(dto.getTime());
		d1.setUserId(dto.getRoleId());
		d1.setJiangQuan(dto.getJiangQuan());
		fDao.save(d1);

	}

}
