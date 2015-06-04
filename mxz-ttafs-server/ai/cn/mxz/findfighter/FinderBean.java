package cn.mxz.findfighter;

import java.util.List;

import cn.mxz.user.team.god.Hero;

interface FinderBean {

	
	
	int getProbability();

	List<Hero> find(int times);

}
