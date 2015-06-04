package cn.mxz.dogz.death;
//package cn.mxz.dogz;
//
//import interfaces.Addition;
//import interfaces.AdditionMultiplier;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lincen.javase.math.Fraction;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import templets.AbilityTemplet;
//import templets.AbilityTempletConfig;
//import templets.DogzLevelTemplet;
//import templets.DogzLevelTempletConfig;
//import templets.DogzQualityTemplet;
//import templets.DogzQualityTempletConfig;
//import templets.DogzSkillTemplet;
//import templets.DogzSkillTempletConfig;
//import templets.DogzTemplet;
//import templets.DogzTempletConfig;
//import util.AdditionEmpty;
//
//import com.lemon.ai.main.logic.dogz.DogzActiveSkill;
//import com.lemon.ai.main.logic.dogz.DogzPassiveSkill;
//import com.lemon.ai.main.logic.dogz.DogzPassiveSkillManager;
//import com.lemon.ai.main.logic.dogz.IDogz;
//
//import db.dao.factory.DaoFactory;
//import db.domain.Dogz;
//
//@Component("dogz")
//@Scope("prototype")
//public class DogzImpl implements IDogz<Dogz> {
//
//	private static final int INIT_PASSIVE_SKILL_LEVEL = 1;
//	private static final int EMPTY_SKILL_ID = 0;
//
//	private DogzPassiveSkillManager dogzPassiveSkillManager;
//	private Dogz dto;
//
//	public DogzImpl() {
//		dogzPassiveSkillManager = new DogzPassiveSkillManagerImpl();
//	}
//
//	@Override
//	public DogzActiveSkill getActiveSkill() {
//		return new ActiveSkillImpl();
//	}
//
//	@Override
//	public Fraction getExp() {
//		DogzLevelTemplet temp = DogzLevelTempletConfig.get(getLevel());
//		int max = temp.getExpNeed();
//		return new Fraction(dto.getExp(), max);
//	}
//
//	@Override
//	public Fraction getGrowth() {
//		DogzQualityTemplet temp = DogzQualityTempletConfig.get(getStep());
//		return new Fraction(dto.getGrowth(), temp.getGrowNeed());
//	}
//
//	@Override
//	public boolean isActivate() {
//		return dto.getIsActivate();
//	}
//
//	@Override
//	public int getLevel() {
//		return dto.getLevel();
//	}
//
//	@Override
//	public int getStep() {
//		return dto.getStep();
//	}
//
//	@Override
//	public int getTypeId() {
//		return dto.getTypeId();
//	}
//
//
//	@Override
//	public Fraction getGenGu() {
//		DogzTemplet temp = DogzTempletConfig.get(getTypeId());
//		int max = temp.getInitSta() + getStep() * temp.getGrowSta();
//		return new Fraction(dto.getGenGu(), max);
//	}
//
//	@Override
//	public Fraction getLiDao() {
//		DogzTemplet temp = DogzTempletConfig.get(getTypeId());
//		int max = temp.getInitStrength() + getStep() * temp.getGrowStrength();
//		return new Fraction(dto.getLiDao(), max);
//	}
//
//	@Override
//	public Fraction getLingXing() {
//		DogzTemplet temp = DogzTempletConfig.get(getTypeId());
//		int max = temp.getInitIntelligence() + getStep() * temp.getGrowIntelligence();
//		return new Fraction(dto.getLingXing(), max);
//	}
//
//	@Override
//	public Fraction getYuanLi() {
//		DogzTemplet temp = DogzTempletConfig.get(getTypeId());
//		int max = temp.getInitElementary() + getStep() * temp.getGrowElementary();
//		return new Fraction(dto.getYuanLi(), max);
//	}
//
//	@Override
//	public int getFightingCapacity() {
//		return 2000 + dto.getGenGu() + dto.getLingXing() + dto.getLiDao() + dto.getYuanLi();
//	}
//
//	@Override
//	public boolean isFighting() {
//		return dto.getIsFighting();
//	}
//
//	@Override
//	public void addExp(int add) {
//
//		dto.addExp(add);
//		while(!getExp().isProper()) {
//			Fraction exp = getExp();
//			dto.setExp(dto.getExp() - exp.getDenominator());
//			dto.addLevel(1);
//			onLevelUp();
//		}
//	}
//
//	private void onLevelUp() {
//		dto.addSkillPoint(1);
//	}
//
//	@Override
//	public void commit() {
//		DaoFactory.getUserDogzDAO().update(dto);
//	}
//
//	@Override
//	public AdditionMultiplier getStepAddition() {
//
//
//		final float lidao = getLiDao().getNumerator();
//		final float yuanli = getYuanLi().getNumerator();
//		final float gengu = getGenGu().getNumerator();
//		final float lingxing = getGenGu().getNumerator();
//
//
//		return new AdditionMultiplier() {
//
//			@Override
//			public float getSpPar() {
//				return 1;
//			}
//
//			@Override
//			public float getSDefendPar() {
//				return 1 + (lingxing / 8000);
//			}
//
//			@Override
//			public float getSAttackPar() {
//				return 1 + (lingxing / 8000);
//			}
//
//			@Override
//			public float getMDefendPar() {
//				return 1 + (yuanli / 8000);
//			}
//
//			@Override
//			public float getMAttackPar() {
//				return 1 + (yuanli / 8000);
//			}
//
//			@Override
//			public float getHpPar() {
//				return 1 + (gengu / 8000);
//			}
//
//			@Override
//			public float getDodgePar() {
//				return 1;
//			}
//
//			@Override
//			public float getDefendPar() {
//				return 1;
//			}
//
//			@Override
//			public float getCritPar() {
//				return 1;
//			}
//
//			@Override
//			public float getBlockPar() {
//				return 1;
//			}
//
//			@Override
//			public float getAttackPar() {
//				return 1 + (lidao / 8000);
//			}
//		};
//	}
//
//	private class ActiveSkillImpl implements DogzActiveSkill {
//
//		@Override
//		public int getLevel() {
//			int lv = dto.getActiveSkillLevel();
//
//			return lv;
//		}
//
//		@Override
//		public void addExp(int add) {
//
//			dto.addActiveSkillExp(add);
//			while(!getActiveSkill().getExp().isProper()) {
//				Fraction exp = getActiveSkill().getExp();
//				dto.setActiveSkillExp(exp.getNumerator() - exp.getDenominator());
//				dto.addActiveSkillLevel(1);
//			}
//
//			Integer max = AbilityTempletConfig.getMaxKey();
//			if(getLevel() >= max) {
//				dto.setActiveSkillExp(0);
//				dto.addActiveSkillLevel(max);
//			}
//		}
//		@Override
//		public int getId() {
//
//			DogzTemplet temp = DogzTempletConfig.get(dto.getTypeId());
//			if(DogzImpl.this.getStep() >= temp.getSkillOpenFour()) {
//				return temp.getSkillFour();
//			}
//
//			if(DogzImpl.this.getStep() >= temp.getSkillOpenThr()) {
//				return temp.getSkillThr();
//			}
//
//			if(DogzImpl.this.getStep() >= temp.getSkillOpenTwo()) {
//				return temp.getSkillTwo();
//			}
//
//			if(DogzImpl.this.getStep() >= temp.getSkillOpenOne()) {
//				return temp.getSkillOne();
//			}
//
//			return EMPTY_SKILL_ID;
//		}
//
//		@Override
//		public Fraction getExp() {
//
//			AbilityTemplet temp = AbilityTempletConfig.get(getLevel());
//			int max = temp.getSkillGrowup();
//			Fraction exp = new Fraction(dto.getActiveSkillExp(), max);
//			return exp;
//
//		}
//
//		@Override
//		public float getProbability() {
//			AbilityTemplet at = AbilityTempletConfig.get(getLevel());
//			DogzTemplet dt = DogzTempletConfig.get(DogzImpl.this.getTypeId());
//			return dt.getProc() + at.getSkillOdds();
//		}
//
//	}
//
//	@Override
//	public DogzPassiveSkillManager getPassiveSkill() {
//		return dogzPassiveSkillManager;
//	}
//
//	private class DogzPassiveSkillManagerImpl implements DogzPassiveSkillManager {
//
//		private class PassiveSkillImpl implements DogzPassiveSkill {
//
//				private int id;
//
//				public PassiveSkillImpl(int id) {
//					this.id = id;
//				}
//
//				@Override
//				public int getLevel() {
//					int index = getIndex();
//					return dto.getPassiveSkillLevel(index);
//				}
//
//				@Override
//				public int getId() {
//					return id;
//				}
//
//		//		@Override
//		//		public boolean isLock() {
//		//			int index = getIndex();
//		//			return dto.getIsLock(index);
//		//		}
//
//				private int getIndex() {
//					for (int i = 0; i < Dogz.PASSIVE_SKILL_ID_LEN; i++) {
//						int id = dto.getPassiveSkillId(i);
//						if(this.id == id) {
//							return i;
//						}
//					}
//					return -1;
//				}
//
//				@Override
//				public void levelUp(int add) {
//					int index = getIndex();
//					dto.setPassiveSkillLevel(index, dto.getPassiveSkillLevel(index) + add);
//				}
//
//				@Override
//				public void lock() {
//					int index = getIndex();
//					dto.setIsLock(index, true);
//				}
//			}
//
//
//		@Override
//		public boolean isFull() {
//			return getSkills().size() >= getOpenGridCount();
//		}
//
//		@Override
//		public int getSkillPoint() {
//			return dto.getSkillPoint();
//		}
//
//		@Override
//		public void addSkillPoint(int add) {
//			dto.addSkillPoint(add);
//		}
//
//		@Override
//		public void addNewSkill(int skillId) {
//			for (int i = 0; i < Dogz.PASSIVE_SKILL_ID_LEN; i++) {
//				int id = dto.getPassiveSkillId(i);
//				if(id == EMPTY_SKILL_ID) {
//					dto.setPassiveSkillId(i, skillId);
//					dto.setPassiveSkillLevel(i, INIT_PASSIVE_SKILL_LEVEL);
//					break;
//				}
//			}
//		}
//
//		@Override
//		public void remove(int skillId) {
//			for (int i = 0; i < Dogz.PASSIVE_SKILL_ID_LEN; i++) {
//				int id = dto.getPassiveSkillId(i);
//				if(id == skillId) {
//					dto.setPassiveSkillId(i, EMPTY_SKILL_ID);
//					dto.setPassiveSkillLevel(i, 0);
//					dto.setIsLock(i, false);
//				}
//			}
//		}
//
//		@Override
//		public DogzPassiveSkill getPassiveSkill(int skillId) {
//			List<DogzPassiveSkill> all = getSkills();
//			for (DogzPassiveSkill s : all) {
//				if(s.getId() == skillId) {
//					return s;
//				}
//			}
//			return null;
//		}
//		@Override
//		public void reduceSkillPoint(int reduce) {
//			dto.setSkillPoint(getSkillPoint() - reduce);
//		}
//
//		@Override
//		public int getGridLimit() {
//			DogzTemplet temp = DogzTempletConfig.get(getTypeId());
//			return temp.getPassivitySkillLimit();
//		}
//
//		@Override
//		public int getOpenGridCount() {
//			return dto.getPassiveSkillCountMax();
//		}
//
//		@Override
//		public List<DogzPassiveSkill> getSkills() {
//			List<DogzPassiveSkill> all = Lists.newArrayList();
//			for (int i = 0; i < Dogz.PASSIVE_SKILL_ID_LEN; i++) {
//				int id = dto.getPassiveSkillId(i);
//				if(id != EMPTY_SKILL_ID) {
//					all.add(new PassiveSkillImpl(id));
//				}
//			}
//			return all;
//		}
//
//		@Override
//		public void openLock() {
//			dto.addPassiveSkillCountMax(1);
//		}
//
//		@Override
//		public boolean isAllLevelMax() {
//			DogzTemplet dt = DogzTempletConfig.get(getTypeId());
//			List<DogzPassiveSkill> skills = getSkills();
//
//			if(skills.isEmpty()) {
//				return false;
//			}
//
//			for (DogzPassiveSkill s : skills) {
//				int limit = dt.getPassivitySkillLevelLimit();
//				if(s.getLevel() < limit ) {
//					return false;
//				}
//			}
//			return true;
//		}
//
//		@Override
//		public void closeAllGrid() {
//			dto.setPassiveSkillCountMax(0);
//		}
//
//		@Override
//		public boolean isOpenAll() {
//			return getOpenGridCount() >= getGridLimit();
//		}
//
//		@Override
//		public Addition getAddition() {
//			AdditionEmpty a = new AdditionEmpty();
//			List<DogzPassiveSkill> skills = getSkills();
//			for (DogzPassiveSkill s : skills) {
//				DogzSkillTemplet temp = DogzSkillTempletConfig.get(s.getId());
//				int e = temp.getEffect();
//				int lv = s.getLevel();
//
//
//
////				技能增加或者减少类型（ 0气血 1物攻 2法功 3技攻 4物防 5法防 6技防 7反击 8闪避 9暴击 10 怒气）
//				switch (temp.getSkillType()) {
//				case 0:
//					a.addHp(lv * e);
//					break;
//				case 1:
//					a.addAttack(lv * e);
//					break;
//				case 2:
//					a.addMAttack(lv * e);
//					break;
//				case 3:
//					a.addSAttack(lv * e);
//					break;
//				case 4:
//					a.addDefend(lv * e);
//					break;
//				case 5:
//					a.addMDefend(lv * e);
//					break;
//				case 6:
//					a.addSDefend(lv * e);
//					break;
//				case 7:
//					a.addBlock(lv * e);
//					break;
//				case 8:
//					a.addDodge(lv * e);
//					break;
//				case 9:
//					a.addCrit(lv * e);
//					break;
//				case 10:
//					a.addSp(lv * e);
//					break;
//				}
//			}
//			return a;
//		}
//
//		@Override
//		public void setSkillPoint(int point) {
//			dto.setSkillPoint(point);
//		}
//	}
//
//	@Override
//	public void setDTO(Dogz dogz) {
//		this.dto = dogz;
//	}
//
//	@Override
//	public Dogz getDTO() {
//		return dto;
//	}
//}
