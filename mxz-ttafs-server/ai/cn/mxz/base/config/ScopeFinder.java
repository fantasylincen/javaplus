package cn.mxz.base.config;

import cn.javaplus.math.Scope;



public abstract class ScopeFinder {

	/**
	 * 在scope中寻找 value 的所处范围的索引位置 , 返回索引位置  + 1
	 * @param scope	范围列表
	 * @param value
	 * @return	注意是索引位置 + 1
	 */
	public int find(Scope[] scope, long value) {
		
		int min = 0;
		
		int max = scope.length;
		
		int now;
		
		while(true) {	//二分查找  最大时间复杂度: 7
			now = (min + max) / 2;
			
			if(scope[now].contains(value)) {
				return now + 1;
			}
			
			if(scope[0].isLeft(value)) {
				return 1;
			}
			
			if(scope[scope.length - 1].isRight(value)) {
				return getMaxLevel();
				
			} else if(scope[now].isRight(value)){
				min = now;
				
			} else if(scope[now].isLeft(value)){
				max = now;
				
			}
		}
	}

	/**
	 * 最大等级
	 * @return
	 */
	public abstract int getMaxLevel();
}
