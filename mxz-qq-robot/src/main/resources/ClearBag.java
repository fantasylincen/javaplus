import java.util.List;
import java.util.Map;

import cn.mxz.bag.BagSnapsort;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserGridDao2;
import db.dao.impl.UserPiecesGridDao2;
import db.domain.UserGrid;
import db.domain.UserPiecesGrid;

public class ClearBag {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void clearBag(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}
		BagSnapsort b1 = new BagSnapsort();
		BagSnapsort b2 = new BagSnapsort();
		b1.snapsort(city.getBag());
		b2.snapsort(city.getPiecesBag());
		
		UserGridDao2 dao = DaoFactory.getUserGridDao();
		List<UserGrid> all = dao.findByUname(city.getId());
		for (UserGrid g : all) {
			dao.delete(g);
		}
		
		UserPiecesGridDao2 d = DaoFactory.getUserPiecesGridDao();
		List<UserPiecesGrid> aa = d.findByUname(city.getId());
		for (UserPiecesGrid g : aa) {
			d.delete(g);
		}
		
		city.freeBag();
		city.freePiecesBag();
		
		b1.snapsort(city.getBag());
		b2.snapsort(city.getPiecesBag());
	}

	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		String id = all.get(user);
		if (id == null) {
			return null;
		}

		return getCity(id);
	}
}