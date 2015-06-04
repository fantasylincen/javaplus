package cn.mxz.base.logserver.command;

import java.util.Set;

class LiuCunLvLoginCountor {


	int getLogind(Set<String> allCreate, Set<String> allLogin) {
		int count = 0;
		for (String string : allLogin) {
			if(allCreate.contains(string)) {
				count ++;
			}
		}
		return count;
	}
}
