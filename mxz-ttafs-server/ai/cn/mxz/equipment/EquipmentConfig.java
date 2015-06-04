package cn.mxz.equipment;

import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import define.D;

public class EquipmentConfig {

	public static int getLevelUpNeed(int templetId, int level) {

		int cash = 0;

		// 强化当前消耗=品阶*(强化等级 + 1)*(强化等级 + 1)*部位强化权重 * 强化装备的基础调节系数  + 强化装备所需基础金币
		double q = getQ(templetId);

		cash += q * (level + 1) * (level + 1) * getPar(templetId) * D.EQUIPMENT_LEVELUP_PAR_JI_CHU
				+ D.EQUIPMENT_LEVEL_UP_NEED;

		return cash;
	}

	/**
	 * 把等级为currentLevel的装备强化到 currentLevel + levelAdd 级所需金币
	 * 
	 * @param templetId
	 * @param currentLevel
	 * @param levelAdd
	 * @return
	 */
	public static int getLevelUpNeed(int templetId, int currentLevel,
			int levelAdd) {
		if (levelAdd < 0 || levelAdd + currentLevel > 100) {
			throw new RuntimeException("等级超限" + currentLevel + "," + levelAdd);
		}
		int need = 0;
		for (int i = 0; i < levelAdd; i++) {
			need += getLevelUpNeed(templetId, currentLevel);
			currentLevel++;
		}
		return need;
	}

	public static double getQ(int templetId) {
		double q = getQuality(templetId);

		if (q == 1) {
			q = D.EQUIPMENT_LEVELUP_PAR_DING;
		} else if (q == 2) {
			q = D.EQUIPMENT_LEVELUP_PAR_BING;
		} else if (q == 3) {
			q = D.EQUIPMENT_LEVELUP_PAR_YI;
		} else if (q == 4) {
			q = D.EQUIPMENT_LEVELUP_PAR_JIA;
		}
		return q;
	}

	/**
	 * 部位权重
	 * 
	 * @param templetId
	 */
	// 类别id（1气血 2攻击 3物防 4法防 5速度 ）
	private static double getPar(Integer templetId) {

		EquipmentTemplet temp = EquipmentTempletConfig.get(templetId);

//		0气血 1物攻 2法攻 3物防 4法防 5速度 6暴击 7闪避 8格挡 9抗暴 10 命中 11破格 12会心
		int part = temp.getBaseAdditionType();

		if (part == 0) {

			return D.EQUIPMENT_LEVELUP_PAR_HP;

		} else if (part == 1 || part == 2) {

			return D.EQUIPMENT_LEVELUP_PAR_ATTACK;

		} else if (part == 3) {

			return D.EQUIPMENT_LEVELUP_PAR_NORMAL_DEFEND;

		} else if (part == 4) {

			return D.EQUIPMENT_LEVELUP_PAR_MAGIC_DEFEND;

		} else if (part == 5) {

			return D.EQUIPMENT_LEVELUP_PAR_SPEED;
		}

		return D.EQUIPMENT_LEVELUP_PAR_SPEED;
	}

	private static int getQuality(int templetId) {

		EquipmentTemplet temp = EquipmentTempletConfig.get(templetId);

		if(temp == null) {
			throw new NullPointerException(templetId + "");
		}
		
		int quality = temp.getQuality();

		return quality;
	}

	/**
	 * typeId 的level级装备, 变换为dstEquipmentTempletId的装备后, dstEquipmentTempletId的等级
	 * 
	 * @param typeId
	 *            源装备类型ID
	 * @param level
	 *            源装备等级
	 * @param dstEquipmentTempletId
	 *            目标装备ID
	 * @return
	 */
	public static int getLevelParse(int typeId, int level,
			int dstEquipmentTempletId) {
		int cashNeed = getCashNeed(typeId, level);

		int cash = 0;
		int l = 0;
		while (cash < cashNeed) {
			cash += getLevelUpNeed(dstEquipmentTempletId, l);
			l++;
		}
		return l;
	}

	/**
	 * 把某个装备强化到level级 总共需要的金币
	 * 
	 * @param typeId
	 * @param level
	 * @return
	 */
	public static int getCashNeed(int typeId, int level) {
		int cash = 0;
		for (int lv = 1; lv <= level; lv++) {
			cash += getLevelUpNeed(typeId, lv);
		}
		return cash;
	}
}
