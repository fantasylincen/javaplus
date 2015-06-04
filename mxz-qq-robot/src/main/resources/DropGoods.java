import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mxz.bag.BagSnapsort;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.prizecenter.PropIdCheck;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Sets;

import db.dao.impl.DaoFactory;
import db.dao.impl.NewEquipmentDao2;
import db.dao.impl.UserGridDao2;
import db.dao.impl.UserPiecesGridDao2;
import db.domain.NewEquipment;
import db.domain.UserGrid;
import db.domain.UserPiecesGrid;

public class DropGoods {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void dropGoods(String user, String type) {

		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}


		if (type.equals("装备")) {
			NewEquipmentDao2 dao = DaoFactory.getNewEquipmentDao();
			List<NewEquipment> all = dao.findByUname(city.getId());
//			System.out.println("xxxxxx" + all.size());
			for (NewEquipment e : all) {
				dao.delete(e);
			}
			city.reloadEquipmentManager();
			return;
		}
		if (type.equals("魂魄")) {
			List<db.domain.Spirite> alls = DaoFactory.getSpiriteDao()
					.findByUname(city.getId());
			for (db.domain.Spirite spirite : alls) {
				DaoFactory.getSpiriteDao().delete(spirite);
			}

			city.reloadSpiriteManager();
			return;
		}

		Set<String> set = Sets.newHashSet("战士", "所有神将", "所有战士", "神将", "全部战士",
				"全部神将");
		if (set.contains(type)) {

			removeAllFighter(city);

		} else {

			int id = PropIdCheck.getId(type);

			dropPropInBag(city, id);// 移除物品
			dropFighter(city, id);// 移除神将
			dropSpirite(city, id);// 移除魂魄
			dropSkill(city, id); // 移除技能
		}
	}

	private void removeAllFighter(City city) {
		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();
		Collection<Hero> all = city.getTeam().getAll();
		for (Hero hero : all) {
			if (!hero.isPlayer()) {
				city.getTeam().remove(hero);
			}
		}
		s.snapshoot();
	}

	private void dropSkill(City city, int id) {
		city.getSkillManager().removeByIds(id);
	}

	private void dropSpirite(City city, int id) {
		city.getSpiriteManager().remove(id);
	}

	private void dropFighter(City city, int id) {
		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();
		Team team = city.getTeam();
		Hero hero = team.get(id);
		if (hero.isPlayer()) {
			return;
		}
		team.remove(id);
		s.snapshoot();
	}

	private void dropPropInBag(City city, int id) {
		BagSnapsort b1 = new BagSnapsort();
		BagSnapsort b2 = new BagSnapsort();
		b1.snapsort(city.getBag());
		b2.snapsort(city.getPiecesBag());

		UserGridDao2 dao = DaoFactory.getUserGridDao();
		List<UserGrid> all = dao.findByUname(city.getId());
		for (UserGrid g : all) {
			if (g.getTypeid() == id) {
				dao.delete(g);
			}
		}

		UserPiecesGridDao2 d = DaoFactory.getUserPiecesGridDao();
		List<UserPiecesGrid> aa = d.findByUname(city.getId());
		for (UserPiecesGrid g : aa) {
			if (g.getTypeid() == id) {
				d.delete(g);
			}
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