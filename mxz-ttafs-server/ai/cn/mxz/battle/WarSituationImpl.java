package cn.mxz.battle;

import java.util.List;

import cn.mxz.formation.PlayerCamp;
import cn.mxz.protocols.user.battle.WarSituationP.FormationPro;

import com.google.common.collect.Lists;

class WarSituationImpl implements WarSituation {

	private boolean						isWin;

	private final List<AttackAction>	attackAction	= Lists.newArrayList();

	private FormationPro				formation;

	Battle								battle;

	private List<BuffEffect> effects;

	public WarSituationImpl(Battle battle) {
		super();
		this.battle = battle;
		effects = Lists.newArrayList();
	}
	
	@Override
	public void addEffect(BuffEffect f) {
		effects.add(f);
	}



	@Override
	public boolean isWin() {
		return isWin;
	}

	@Override
	public List<AttackAction> getActions() {
		return attackAction;
	}

	@Override
	public void add(AttackAction attackAction) {
		this.attackAction.add(attackAction);
	}

	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
	}

	@Override
	public FormationPro getFormation() {
		return formation;
	}

	@Override
	public void saveFormation(PlayerCamp under, BattleCamp upper) {
		formation = new FormationBuilder().build(under, upper);
	}

	@Override
	public Battle getBattle() {
		return battle;
	}

	@Override
	public List<BuffEffect> getBuffEffects() {
		return effects;
	}
}
