package cn.vgame.a.recharge;

import java.util.Map;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDao;
import cn.vgame.a.gen.dto.MongoGen.ZfbOrderDto;
import cn.vgame.share.Xml;

public class ZfbRechargeManager {
	
	public void createZfbOrder(Map<String, String> pars, String idd, String roleId) {
		
		int id = new Integer(idd);
		
		ZfbOrderDao dao = Daos.getZfbOrderDao();
		ZfbOrderDto dto = dao.createDTO();
		dto.setId(pars.get("out_trade_no"));
		Sheet sheet = Xml.getSheet("recharge-A");
		Row row = sheet.get(id);
		int jinDou = row.getInt("jinDou");
		dto.setCount(jinDou);
		dto.setPrice(pars.get("total_fee"));
		dto.setTime(System.currentTimeMillis());
		dto.setRoleId(roleId);
		
		dao.save(dto);
	}

}
