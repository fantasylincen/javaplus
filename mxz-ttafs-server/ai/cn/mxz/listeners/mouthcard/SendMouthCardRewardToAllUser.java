package cn.mxz.listeners.mouthcard;

import java.util.Collection;

import message.S;

import org.joda.time.LocalDate;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.Messages;
import cn.mxz.events.Listener;
import cn.mxz.events.ZeroClockEvent;
import cn.mxz.monthcard.MonthCard;

public class SendMouthCardRewardToAllUser implements Listener<ZeroClockEvent>{

	private static final int	GIFT_GOLD_COUNT	= 120;
	private static final String PRIZE_STR = "110009," + GIFT_GOLD_COUNT;
	private static final String TITLE = Messages.getText(S.S60217);
	private static final String DESC = Messages.getText(S.S60218); 

	@Override
	public void onEvent(ZeroClockEvent e) {
		try {
			
			Collection<City> all = WorldFactory.getWorld().getAll();
			
			for (City city : all) {
				if( city.getMonthCard().isValid() ){
					sendPrize(city);
				}
			}			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void sendPrize( City user ){
		int endSecond = (int) (new LocalDate().plusDays(1).toDateMidnight().getMillis() / 1000) - 1;
		user.getPrizeCenter().addPrize( 4, PRIZE_STR, TITLE, DESC, endSecond );
	}

}
