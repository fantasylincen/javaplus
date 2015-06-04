package cn.mxz.daji;

import cn.mxz.util.cd.ColdDownImpl;

public class ColdDownTime {
	private final ColdDownImpl cd = new ColdDownImpl();
	
	public ColdDownTime( int coldSecond ) {
		cd.setEvery( coldSecond * 1000 );
		cd.setFreezing( coldSecond * 1000 );

		cd.setEnd(System.currentTimeMillis() + coldSecond * 1000 + 100);
		cd.add();
	}

	public boolean isFreezing(){
		return cd.isFreezing();
	}

	public int getRemainingSec(){
		return cd.getRemainingSec();
	}

}
