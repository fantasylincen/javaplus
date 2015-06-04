package com.cnbizmedia.zfb;

import java.util.Map;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.ZfbOrderDao;
import com.cnbizmedia.gen.dto.MongoGen.ZfbOrderDto;

public class ZfbRechargeManager {
	/**
	 * @param pars
	 * @param vb V币
	 */
	public void createZfbOrder(Map<String, String> pars, int vb, String userId) {
		
		ZfbOrderDao dao = Daos.getZfbOrderDao();
		ZfbOrderDto dto = dao.createDTO();
		dto.setId(pars.get("out_trade_no"));
		dto.setCount(vb);
		dto.setPrice(pars.get("total_fee"));
		dto.setTime(System.currentTimeMillis());
		dto.setUserId(userId);
		
		dao.save(dto);
	}
}
