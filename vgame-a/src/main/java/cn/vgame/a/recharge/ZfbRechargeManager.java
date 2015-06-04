package cn.vgame.a.recharge;

import java.util.Map;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDao;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDto;

public class ZfbRechargeManager {
	/**
	 * @param pars
	 * @param vb V币
	 */
	public void createZfbOrder(Map<String, String> pars, int vb, String roleId) {
		
		ZfbOrderDao dao = Daos.getZfbOrderDao();
		ZfbOrderDto dto = dao.createDTO();
		dto.setId(pars.get("out_trade_no"));
		dto.setCount(vb);
		dto.setPrice(pars.get("total_fee"));
		dto.setTime(System.currentTimeMillis());
		dto.setRoleId(roleId);
		
		dao.save(dto);
	}
}
