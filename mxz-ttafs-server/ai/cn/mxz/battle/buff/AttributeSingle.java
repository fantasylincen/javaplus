package cn.mxz.battle.buff;

import cn.mxz.Attribute;
import cn.mxz.formation.AdditionType;

public class AttributeSingle implements Attribute {

	private cn.mxz.formation.AdditionType	type;
	private int				value;

	public AttributeSingle(AdditionType type, int value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public int getHp() {
		if (type == AdditionType.HP) {
			return value;
		}
		return 0;
	}

	@Override
	public int getAttack() {
		if (type == AdditionType.ATTACK) {
			return value;
		}
		return 0;
	}

	@Override
	public int getDefend() {
		if (type == AdditionType.DEFEND) {
			return value;
		}
		return 0;
	}

	@Override
	public int getMAttack() {
		if (type == AdditionType.MATTACK) {
			return value;
		}
		return 0;
	}

	@Override
	public int getMDefend() {
		if (type == AdditionType.MDEFEND) {
			return value;
		}
		return 0;
	}

	@Override
	public int getSpeed() {
		if (type == AdditionType.SPEED) {
			return value;
		}
		return 0;
	}

	@Override
	public int getCrit() {
		if (type == AdditionType.CRIT) {
			return value;
		}
		return 0;
	}

	@Override
	public int getRCrit() {
		if (type == AdditionType.RCRIT) {
			return value;
		}
		return 0;
	}

	@Override
	public int getCritAddition() {
		if (type == AdditionType.CRIT_ADDITION) {
			return value;
		}
		return 0;
	}

	@Override
	public int getHit() {
		if (type == AdditionType.HIT) {
			return value;
		}
		return 0;
	}

	@Override
	public int getDodge() {
		if (type == AdditionType.DODGE) {
			return value;
		}
		return 0;
	}

	@Override
	public int getBlock() {
		if (type == AdditionType.BLOCK) {
			return value;
		}
		return 0;
	}

	@Override
	public int getRBlock() {
		if (type == AdditionType.RBLOCK) {
			return value;
		}
		return 0;
	}

	@Override
	public int getMagic() {
		if (type == AdditionType.MAGIC) {
			return value;
		}
		return 0;
	}

	@Override
	public String toString() {
		return type + ":" + value;
	}

}
