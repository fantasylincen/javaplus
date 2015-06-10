package util.taskscheduling.task;

import game.log.Logs;
import game.log.L;

import java.util.TimerTask;

import define.GameData;

/**
 * 每天 最大人数
 * @author DXF
 */
public class EveryDayMaxOUesrNumTask extends TimerTask{

	@Override
	public void run() {
		
		// 记录每日 最大在线人数
		Logs.log( L.L_012, GameData.maxOnlineNumber + "," + GameData.maxOnlineTime );
		// 然后清空
		GameData.maxOnlineNumber 	= 0;
		GameData.maxOnlineTime		= 0;
		
		// 生成每日 充值明细
//		try {
//			String path	= SystemCfg.LOG_PATH.replace( "log", "recharge" ) + "/" + 
//		UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy-MM-dd" ) + "-";
//			
//			RechargeDataProvider.getInstance().produceExcel( path );
//		} catch (RowsExceededException e) {
//			e.printStackTrace();
//		} catch (WriteException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}

}
