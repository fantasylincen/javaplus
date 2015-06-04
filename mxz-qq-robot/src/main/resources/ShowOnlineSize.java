import java.util.List;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import db.dao.impl.DaoFactory;
import db.dao.impl.RechargeRecordDao2;
import db.domain.RechargeRecord;

public class ShowOnlineSize {

	public String run() {
		return "RUN_FOUNCTION";
	}

	String showOnlineSize() {
		World s = WorldFactory.getWorld();
		
		

		RechargeRecordDao2 dao = DaoFactory.getRechargeRecordDao();
		List<RechargeRecord> all = dao.getAll();
		int count = 0;
		for (RechargeRecord r : all) {
			count += r.getAmount();
		}
		
		return s.getOnlineAll().size() + " 个人在线!   总共:"  + s.getAll().size() + "人 ---  总计充值:" + count;
	}
}