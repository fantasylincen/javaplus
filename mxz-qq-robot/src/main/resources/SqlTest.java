import db.dao.impl.DaoFactory;
import db.dao.impl.WarSituationDao2;
import db.domain.WarSituation;

public class SqlTest {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String test() {
		long t1 = System.currentTimeMillis();
		
		WarSituationDao2 dao = DaoFactory.getWarSituationDao();
		int id = 999999;
		for (int i = 0; i < 1000; i++) {
			WarSituation dto = dao.createDTO();
			dto.setData("1111111111111111111".getBytes());
			dto.setSituationId(id);
			id ++ ;
			
			dto.setCreateTime(1);
			dao.save(dto);
		}
		
		return "" + (System.currentTimeMillis()  -  t1);
	}
}