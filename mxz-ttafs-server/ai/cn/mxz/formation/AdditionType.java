package cn.mxz.formation;

import java.util.HashMap;
import java.util.Map;

import message.S;
import cn.mxz.AdditionMultiplier;
import cn.mxz.AdditionMultiplierSetable;
import cn.mxz.Attribute;
import cn.mxz.fighter.AttributeAddable;
import cn.mxz.fighter.AttributeSetable;


public enum AdditionType {

	HP(0) {
		@Override
		public void add(AttributeAddable arr, int value) {
			arr.addHp(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setHp(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getHpPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getHp();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float v) {
			aa.setHpPar(v);
		}

		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getHp() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10295;
		}


	},
	ATTACK(1) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addAttack(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setAttack(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getAttackPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getAttack();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setAttackPar(value);
		}

		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getAttack() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10291;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	MATTACK(2) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addMAttack(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setMAttack(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getMAttackPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getMAttack();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setMAttackPar(value);
		}

		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getMAttack() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10292;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	DEFEND(3) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addDefend(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setDefend(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getDefendPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getDefend();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setDefendPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getDefend() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10293;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	MDEFEND(4) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addMDefend(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setMDefend(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getMDefendPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getMDefend();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setMDefendPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getMDefend() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10294;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	SPEED(5) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addSpeed(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setSpeed(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getSpeedPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getSpeed();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setSpeedPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getSpeed() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10296;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	CRIT(6) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addCrit(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setCrit(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getCritPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getCrit();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setCritPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getCrit() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10298;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	DODGE(7) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addDodge(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setDodge(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getDodgePar();
		}

		@Override
		public int get(Attribute a) {
			return a.getDodge();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setDodgePar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getDodge() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10301;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	BLOCK(8) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addBlock(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setBlock(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getBlockPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getBlock();
		}
		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setBlockPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getBlock() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10303;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	RCRIT(9) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addRCrit(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setRCrit(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getRCritPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getRCrit();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setRCritPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getRCrit() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10299;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	HIT(10) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addHit(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setHit(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getHitPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getHit();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setHitPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getHit() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10300;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	RBLOCK(11) {
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addRBlock(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setRBlock(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getRBlockPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getRBlock();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setRBlockPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getRBlock() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10302;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},
	CRIT_ADDITION(12){
		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addCritAddition(value);
		}
		@Override
		public void set(AttributeSetable a, int v) {
			a.setCritAddition(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getCritAdditionPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getCritAddition();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setCritAdditionPar(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getCritAddition() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10305;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
	},

	/**
	 * 装备用的.
	 */
	MAGIC(13) {

		@Override
		public
		void add(AttributeAddable arr, int value) {
			arr.addMagic(value);
		}

		@Override
		public void set(AttributeSetable a, int v) {
			a.setMagic(v);
		}

		@Override
		public float get(AdditionMultiplier a) {
			return a.getMagicPar();
		}

		@Override
		public int get(Attribute a) {
			return a.getMagic();
		}

		@Override
		public void set(AdditionMultiplierSetable aa, float value) {
			aa.setMagic(value);
		}
		@Override
		public void add(AttributeAddable arr, float percent, Attribute attr ) {
			int value = (int) (attr.getMagic() * percent);
			add( arr, value );
		}

		@Override
		public String getText() {
			return S.STR10304;
		}
		//  S.STR10291	物攻
		//  S.STR10292	法攻
		//  S.STR10293	物防
		//  S.STR10294	法防
		//  S.STR10295	气血
		//  S.STR10296	速度
		//  S.STR10297	反击
		//  S.STR10298	暴击
		//  S.STR10299	抗暴
		//  S.STR10300	命中
		//  S.STR10301	闪避
		//  S.STR10302	破格
		//  S.STR10303	格挡
//		10304	会心
//		10305	暴击加成

	}
	;

	public abstract void add(AttributeAddable arr, int value);

	private int										number;

	private static final Map<Integer, AdditionType>	numToEnum	= new HashMap<Integer, AdditionType>();
	static {
		for (AdditionType t : values()) {

			AdditionType s = numToEnum.put(t.number, t);
			if (s != null) {
				throw new RuntimeException(t.number + "重复了");
			}
		}
	}

	AdditionType(int number) {
		this.number = number;
	}


	public int toNum() {
		return number;
	}

	public static AdditionType fromNum(int n) {
		return numToEnum.get(n);
	}

	public abstract String getText();

	public abstract void set(AttributeSetable a, int v);

	public abstract float get(AdditionMultiplier a);

	public abstract int get(Attribute a);

	public abstract void set(AdditionMultiplierSetable aa, float value);

	public abstract void add(AttributeAddable arr, float percent, Attribute attr );

}
