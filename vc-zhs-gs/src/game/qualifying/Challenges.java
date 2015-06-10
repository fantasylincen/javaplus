package game.qualifying;

import util.SystemTimer;
import lombok.Data;

public class Challenges {
	
	private int  	uid;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCdTime() {
		return cdTime;
	}

	public void setCdTime(int cdTime) {
		this.cdTime = cdTime;
	}

	private int 	cdTime;

	public int getCdTimeToTimer() {
		if( cdTime == 0 ) return 0;
		int t	= SystemTimer.currentTimeSecond() - cdTime;
		if( t >= 300 ) {
			t		= 300;
			cdTime 	= 0;
		}
		return 300 - t;
	}

}
