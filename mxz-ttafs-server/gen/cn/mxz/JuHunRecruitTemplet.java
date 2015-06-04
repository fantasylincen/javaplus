//[仙市]聚魂招募package cn.mxz;public class JuHunRecruitTemplet {	/** 	 * id 	 */	private int id;	/** 	 * 可招募的神将魂魄（品质，权重|） 	 */	private String steps;	/** 	 * 所需聚魂值 	 */	private int need;	/** 	 * 神将库(战士模板表中godType字段对应
)
 	 */	private String godBank;	/**	 * id	 */	void setId(int id) {		this.id = id;	}	/**	 * id	 */	public int getId() {		return this.id;	}	/**	 * 可招募的神将魂魄（品质，权重|）	 */	void setSteps(String steps) {		this.steps = steps;	}	/**	 * 可招募的神将魂魄（品质，权重|）	 */	public String getSteps() {		return this.steps;	}	/**	 * 所需聚魂值	 */	void setNeed(int need) {		this.need = need;	}	/**	 * 所需聚魂值	 */	public int getNeed() {		return this.need;	}	/**	 * 神将库(战士模板表中godType字段对应
)
	 */	void setGodBank(String godBank) {		this.godBank = godBank;	}	/**	 * 神将库(战士模板表中godType字段对应
)
	 */	public String getGodBank() {		return this.godBank;	}}