package cn.mxz;

public class FighterTempletAdaptor implements IFighterTemplet {

	private DogzTemplet	dogzTemplet;

	public FighterTempletAdaptor(DogzTemplet dogzTemplet) {
		this.dogzTemplet = dogzTemplet;
	}

	public int hashCode() {
		return dogzTemplet.hashCode();
	}

	public int getId() {
		return dogzTemplet.getId();
	}

	public int getDogzId() {
		return dogzTemplet.getDogzId();
	}

	public String getDogzName() {
		return dogzTemplet.getDogzName();
	}

	public int getStepAgo() {
		return dogzTemplet.getStepAgo();
	}

	public int getToolUseType() {
		return dogzTemplet.getToolUseType();
	}

	public int getAccordUse() {
		return dogzTemplet.getAccordUse();
	}

	public int getBackpack() {
		return dogzTemplet.getBackpack();
	}

	public boolean equals(Object obj) {
		return dogzTemplet.equals(obj);
	}

	public int getRetain() {
		return dogzTemplet.getRetain();
	}

	public int getResid() {
		return dogzTemplet.getResid();
	}

	public String getPurpose() {
		return dogzTemplet.getPurpose();
	}

	public String getName() {
		return dogzTemplet.getName();
	}

	public int getInitLevel() {
		return dogzTemplet.getInitLevel();
	}

	public int getAttribute() {
		return dogzTemplet.getAttribute();
	}

	public int getSuffId() {
		return dogzTemplet.getSuffId();
	}

	public float getEffectOdds() {
		return dogzTemplet.getEffectOdds();
	}

	public int getProtagonistLv() {
		return dogzTemplet.getProtagonistLv();
	}

	public String getDogzSource() {
		return dogzTemplet.getDogzSource();
	}

	public String getFormat() {
		return dogzTemplet.getFormat();
	}

	public int getPicType() {
		return dogzTemplet.getPicType();
	}

	public String getUrl() {
		return dogzTemplet.getUrl();
	}

	public int getCommonSkill() {
		return dogzTemplet.getCommonSkill();
	}

	public int getDogzSikll() {
		return dogzTemplet.getDogzSikll();
	}

	public int getHp() {
		return 0;
	}

	public int getAttack() {
		return 0;
	}

	public int getMAttack() {
		return 0;
	}

	public int getDefend() {
		return 0;
	}

	public int getMDefend() {
		return 0;
	}

	public int getSpeed() {
		return 0;
	}

	public int getCrit() {
		return 0;
	}

	public int getDodge() {
		return 0;
	}

	public int getBlock() {
		return 0;
	}

	public int getRCrit() {
		return 0;
	}

	public int getHit() {
		return 0;
	}

	public int getRBlock() {
		return 0;
	}

	public int getCritAddition() {
		return 0;
	}

	public int getMagic() {
		return 0;
	}

	public String getDescription() {
		return dogzTemplet.getDescription();
	}

	public String toString() {
		return dogzTemplet.toString();
	}

	@Override
	public int getProfessionId() {
		return 0;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int getQuality() {
		return 0;
	}

	@Override
	public int getCategory() {
		return 0;
	}

	@Override
	public int getSkill() {
		return dogzTemplet.getDogzSikll();
	}


}
