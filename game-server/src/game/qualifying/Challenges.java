package game.qualifying;

import util.SystemTimer;
import lombok.Data;

@Data
public class Challenges {
	
	private int  	uid;
	
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
