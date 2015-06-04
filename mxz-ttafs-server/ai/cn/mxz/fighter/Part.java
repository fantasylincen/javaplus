package cn.mxz.fighter;

import java.util.Set;

import cn.mxz.formation.AdditionType;

import com.google.common.collect.Sets;

/**
 * 装备部位
 * @author 林岑
 *
 */
public enum Part {

	/**
	 * 武器
	 */
	WU_QI(AdditionType.ATTACK, AdditionType.MATTACK),

	/**
	 * 宝珠
	 */
	BAO_ZHU(AdditionType.HP),

	/**
	 * 衣袍
	 */
	YI_PAO(AdditionType.DEFEND),

	/**
	 * 符旗
	 */
	FU_QI(AdditionType.MDEFEND),

	/**
	 * 坐骑
	 */
	ZUO_QI(AdditionType.SPEED)
	;

	private Set<AdditionType>	set;

	Part(AdditionType... type) {
		set = Sets.newHashSet();
		for (AdditionType t : type) {
			set.add(t);
		}
	}

	/**
	 * 获得装备的加成类型
	 * @return
	 */
	public Set<AdditionType> getAdditionTypes() {
		return set;
	}

	/**
	 * 获得此种加成类型, 所对应的武器部位
	 * @param type
	 * @return
	 */
	public static Part getPart(AdditionType type) {
		Part[] all = values();
		for (Part part : all) {
			Set<AdditionType> ts = part.getAdditionTypes();
			if(ts.contains(type)) {
				return part;
			}
		}
		return null;
	}
}
