package cn.mxz.base.prize;

import java.util.List;

import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.equipment.EquipmentManager;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

/**
 * 根据玩家的基本属性  送对应的装备
 * @author 林岑
 *
 */
public class ZBPrize implements Prize {

	private List<Integer>	xxx;
	private Integer	id;

	public ZBPrize(List<Integer> xxx) {
		this.xxx = xxx;
	}
	public void award(City city) {
		award(city.getPlayer());
	}

	@Override
	public void award(Player player) {

		String id = player.getId();
		City city = CityFactory.getCity(id);
		PlayerHero h = city.getTeam().getPlayer();

		int typeId = h.getTypeId();
		FighterTemplet t = FighterTempletConfig.get(typeId);

//		攻击类型（1物理，2法术
		int type = t.getAttackType();

		for (int i : xxx) {

			boolean typeEqual = isTypeEqual(type, i);
			if(typeEqual) {
				send(city, i, getCount());
				this.id = i;
				return;
			}
		}

	}

	private boolean isTypeEqual(int type, int id) {

		EquipmentTemplet temp = EquipmentTempletConfig.get(id);

		int t2 = temp.getBaseAdditionType();
//		装备类型（数据类型-1无 0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心）

		if(t2 == 1 || t2 == 3) {
			t2 = 1;
		} else if(t2 == 2 || t2 == 4) {
			t2 = 2;
		}

		boolean typeEqual = type == t2;
		return typeEqual;
	}

	private void send(City city, int id, int count) {
		EquipmentManager m = city.getEquipmentManager();
		m.createNewEquipment(build(id, count));
	}

	private List<Integer> build(int id2, int count) {
		List<Integer> ls = Lists.newArrayList();
		for (int i = 0; i < count; i++) {
			ls.add(id2);
		}
		return ls;
	}

	@Override
	public int getId() {
		if(id == null) {
			throw new RuntimeException("礼包还没有送出去, 无法确定装备ID!");
		}
		return id;
	}

	@Override
	public int getCount() {
		return xxx.get(xxx.size() - 1);
	}

}
