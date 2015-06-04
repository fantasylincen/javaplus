package cn.mxz.formation;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.FormationTemplet;
import cn.mxz.FormationTempletConfig;
import cn.mxz.city.City;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import db.domain.BackupPosition;
import db.domain.Tactical;

/**
 * 阵型总管，包括：
 * 
 * 阵法，主力阵容，替补阵容
 * 
 * 三块内容
 * 
 * 
 * @author Administrator
 * 
 */
class FormationManager implements Formation {

	private final City user;

	private final TacticalManager tacticalManager;
	private final AlternateFormation alternateFormation;
	private final MainFormation mainFormation;

	FormationManager(City user) {
		this.user = user;
		tacticalManager = new TacticalManager(user);
		alternateFormation = new AlternateFormation(user);
		mainFormation = new MainFormation(user);
	}

	/**
	 * 阵法升级
	 * 
	 * @param tacticalId
	 * @param fregment
	 * @return
	 */
	boolean tacticalLevelUp(int tacticalId, List<Integer> fregment) {
		boolean levelUp = tacticalManager.levelUp(tacticalId, fregment);

		// 林岑加 2014年1月10日 10:07:59 任务用
		if (levelUp) {
			UserCounterSetter uc = user.getUserCounterHistory();
			uc.add(CounterKey.TACTICAL_LEVEL_UP_TIMES, 1);
		}
		return levelUp;
	}

	/**
	 * 设置当前阵法
	 * 
	 * @param taticalId
	 * @return
	 */
	boolean setCurrentTactical(int tacticalId) {
		return tacticalManager.setCurrent(tacticalId);
	}

	/**
	 * 刷新替补阵容一个格子的加成属性
	 * 
	 * @param position
	 * @return
	 */

	public boolean refreshAlternateFormation(int position) {
		return alternateFormation.refresh(position);

	}

	@Override
	public PlayerCamp getSelected() {
		return mainFormation;
	}

	/**
	 * 获取阵法的所有加成
	 */
	@Override
	public Attribute getAddition(Hero hero) {
		int position = getPosition(hero);
		// return new AttributeEmpty();
		if( mainFormation.isContain(position) ){
			Attribute attr1 = alternateFormation.getAddition(hero);
			Attribute attr2 = tacticalManager.getAddition(hero);
			Attribute adding = AttributeCalculator.adding(attr1, attr2);
			return adding;
		}
		// if(adding.getBlock() > 30000) {
		// Debuger.debug("FormationManager.getAddition()");
		// }

		return new AttributeEmpty();
	}

	@Override
	public int getPosition(Hero f) {
		int pos = mainFormation.getPosition(f);
		if (pos != -1) {
			return pos;
		}

		return alternateFormation.getPosition(f);
	}

	/**
	 * 查找位置上英雄，如果位置为-1，则意味着没有英雄
	 * 
	 * @param desPos
	 * @return
	 */
	private Hero getHeroByPos(int desPos) {
		if (desPos == -1) {
			return null;
		}
		for (Hero hero : user.getTeam().getAll()) {
			if (getPosition(hero) == desPos) {
				return hero;
			}
		}
		return null;
	}

	// Collection<? extends Hero> getFighters() {
	// return alternateFormation.getFighters().ad
	// }

	public List<Tactical> getAllTactical() {
		return tacticalManager.getAll();
	}

	public Tactical getCurrentTactical() {
		return tacticalManager.getCurrentTactical();
	}

	@Override
	public int getShenJia() {

		// 战斗力（身价）
		//
		// 单人身价=人物本身+装备+技能+元神
		//
		// 总身价=上阵人身价总和+阵法+缘分+神兽+pvp

		int ret = 0;
		ret += getHerosOnFormationShenJia();
		ret += tacticalManager.getShenJia();
		ret += user.getDogzManager().getShenJia();
		ret += user.getNewPvpManager().getShenJia();

		return ret;
	}

	private int getHerosOnFormationShenJia() {

		int ret = 0;

		for (int fid : mainFormation.getValidHeros().keySet()) {
			Hero h = user.getTeam().get(fid);
			ret += h.getShenJia();
		}
		return ret;
	}

	public List<BackupPosition> getAlternateFormation() {
		return alternateFormation.getAll();
	}

	public float[] getTacticalAddion(int id) {
		float[] addtion = new float[3];
		// tacticalManager.getAddition(hero);
		return addtion;
	}

	/**
	 * 最大上阵人数
	 * 
	 * @return
	 */
	public int getHeroMaxNum() {
		return mainFormation.getHeroMaxNum();
	}

	public String getMainFormation() {
		return mainFormation.getAll();
	}

	/**
	 * 获取英雄的等级，供前端显示用
	 * 
	 * @return
	 */
	public String getLevels() {
		return mainFormation.getLevels();
	}

	Boolean refreshBackupPos(int position) {
		boolean refresh = alternateFormation.refresh(position);

		UserCounterSetter his = user.getUserCounterHistory();
		his.add(CounterKey.TI_BU_WEI_NI_ZHUAN_TIMES, 1);

		Debuger.debug("FormationManager.refreshBackupPos() 逆转成功!");

		return refresh;
	}

	/**
	 * 最复杂的功能：指定英雄位置 1、主角不能下阵
	 * 
	 * @param targetPos
	 *            目的位置
	 * @param heroId
	 *            英雄id
	 * @return
	 */
	@Override
	public Boolean setHeroPosition(int desPos, int srcHeroId) {
		if (!positionValid(desPos)) {
			System.err.println("目标位置不是有效值" + desPos);
			return false;
		}
		Hero srcHero = user.getTeam().get(srcHeroId);
		if (srcHero == null) {
			System.err.println("英雄不存在" + srcHeroId);
			return false;
		}
		if (srcHero instanceof PlayerHero && !mainFormation.isContain(desPos)) {
			System.err.println("主角不能下阵,位置：" + desPos);
			return false;
		}

		int srcPos = getPosition(srcHero);
		if (srcPos == -1 && desPos == -1) {
			System.err.println("源，目的都是-1，你这是要干啥");
			return false;
		}

		Hero desHero = getHeroByPos(desPos);
		return setHero(srcPos, desPos, srcHero, desHero);

	}

	/**
	 * 检测此位置是否合法位置，合法的位置包括： 阵下：-1 主阵：1,3,4,5,7 替补阵：100,...,开放的数量
	 * 
	 * @param pos
	 * @return
	 */
	private boolean positionValid(int pos) {

		if (pos == -1 || mainFormation.isContain(pos)
				|| alternateFormation.isContain(pos)) {
			return true;
		}
		return false;
	}

	private boolean setHero(int sPos, int dPos, Hero sHero, Hero dHero) {
		IFormationMove sFormation = getFormationByPos(sPos);
		IFormationMove dFormation = getFormationByPos(dPos);

		if (sPos != -1 && dPos == -1) {// 下阵
			sFormation.remove(sPos);
		} else if (sPos == -1 && dPos != -1) {// 上阵
			if (dHero != null) {// 目标位置上有人
				dFormation.remove(dPos);// 让其下阵
			}
			return dFormation.add(sHero.getTypeId(), dPos);// 有可能人数满了，导致失败，很明显，如果目标位置有人则不会失败
		} else {// 目标位置和源位置都来自于阵上
			if (dHero != null) {// 目标位置上有人
				sFormation.put(dHero.getTypeId(), sPos);
				dFormation.put(sHero.getTypeId(), dPos);
			} else {
				if (sFormation == dFormation) {// 同一边移动，无需检查,必须下面2句，否则BackUpPos会出问题，可以在仔细想想
					dFormation.put(sHero.getTypeId(), dPos);
					dFormation.remove(sPos);
				} else {// 首发向替补移动， 或者替补向首发移动
					if (dFormation.add(sHero.getTypeId(), dPos)) {// 有可能人数满了，导致失败
						sFormation.remove(sPos);// 让其下阵
					} else {
						return false;
					}
				}
			}
		}

		return true;

	}

	/**
	 * 外层传保证不会传-1进来，否则只能返回null
	 * 
	 * @param pos
	 * @return
	 */
	private IFormationMove getFormationByPos(int pos) {
		if (mainFormation.isContain(pos)) {
			return mainFormation.get();
		}
		if (alternateFormation.isContain(pos)) {
			return alternateFormation.get();
		}
		return null;
	}

	@Override
	public AlternateFormation getAlternate() {
		return alternateFormation;
	}

	@Override
	public void addNewTactical(Integer id) {
		tacticalManager.create(id);
	}

	public Tactical tacticalCompound(int targetId) {
		return tacticalManager.compound(targetId);
	}

	@Override
	public int getTacticalsCount(int a0) {
		List<Tactical> all = tacticalManager.getAll();
		int count = 0;
		for (Tactical tactical : all) {
			FormationTemplet temp = FormationTempletConfig.get(tactical
					.getTempletId());
			int spet = temp.getSpet();
			if (spet >= a0) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getFormationPart(Hero hero) {
		return mainFormation.getFormationPart(hero);
	}

	@Override
	public int getMaxCount() {
		return mainFormation.getHeroMaxNum();
	}

	@Override
	public List<Tactical> getTacticalsAll() {
		return tacticalManager.getAll();
	}

}
