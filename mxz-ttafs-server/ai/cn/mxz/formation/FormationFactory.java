package cn.mxz.formation;

import cn.mxz.city.City;
import cn.mxz.user.team.Formation;


public class FormationFactory {

	/**
	 * 获得用户的阵形
	 * @param city
	 * @return
	 */

	public static Formation getFormation( City user ) {
		return new FormationManager( user );
	}

	public static void main(String[] args) {
	}
	
	

}

