package cn.mxz.zan;


import cn.mxz.Attribute;
import cn.mxz.user.team.god.Hero;




public interface ZanManager {

	void clickZan();

	int getCount();

	int getTotalCount();

	int getZanLevel();

	boolean getTodayIsClick();




	Attribute getAddition(Hero hero);


	int getNextGold();

	
}
