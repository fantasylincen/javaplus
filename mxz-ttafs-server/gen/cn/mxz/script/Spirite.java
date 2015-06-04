package cn.mxz.script;
	/**
	 * 升星或者招募所需魂魄数量 计算公式
	 * @param typeId	神将ID
	 * @param hero	是否是招募
	 * @return
	 */
	public int getCountMax(int typeId, Hero hero) {
		int times; // 第n次升星
	
		FighterTemplet temp = FighterTempletConfig.get(typeId);
	
		if (hero == null) {	//招募
			times = 0;
			return (int) temp.getSoul();	//招募所需数量
			
		} else {			//升星
			times = hero.get(HeroProperty.QUALITY) + 1;
			return (int) (temp.getSoul() + times * 1.1 + 1);
		}
	}
