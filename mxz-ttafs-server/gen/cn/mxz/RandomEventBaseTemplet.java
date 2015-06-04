//[奇遇]15[关卡][神魔]基础奖励表package cn.mxz;public class RandomEventBaseTemplet {	/** 	 * 神魔等级 	 */	private int rappelzLv;	/** 	 * 功德值 	 */	private int merit;	/** 	 * 发现物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励） 	 */	private String findReward;	/** 	 * MVP物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励） 	 */	private String mvpReward;	/** 	 * 击杀者物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励） 	 */	private String killReward;	/** 	 * 神魔伤害系数 	 */	private float bossParam;	/**	 * 神魔等级	 */	void setRappelzLv(int rappelzLv) {		this.rappelzLv = rappelzLv;	}	/**	 * 神魔等级	 */	public int getRappelzLv() {		return this.rappelzLv;	}	/**	 * 功德值	 */	void setMerit(int merit) {		this.merit = merit;	}	/**	 * 功德值	 */	public int getMerit() {		return this.merit;	}	/**	 * 发现物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	void setFindReward(String findReward) {		this.findReward = findReward;	}	/**	 * 发现物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	public String getFindReward() {		return this.findReward;	}	/**	 * MVP物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	void setMvpReward(String mvpReward) {		this.mvpReward = mvpReward;	}	/**	 * MVP物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	public String getMvpReward() {		return this.mvpReward;	}	/**	 * 击杀者物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	void setKillReward(String killReward) {		this.killReward = killReward;	}	/**	 * 击杀者物品奖励
（因加VIP加成奖励，此处奖励不能再填写除兽魂以外的奖励）	 */	public String getKillReward() {		return this.killReward;	}	/**	 * 神魔伤害系数	 */	void setBossParam(float bossParam) {		this.bossParam = bossParam;	}	/**	 * 神魔伤害系数	 */	public float getBossParam() {		return this.bossParam;	}}