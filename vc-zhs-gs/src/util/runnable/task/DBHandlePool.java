package util.runnable.task;

import game.log.Logs;
import user.UserInfo;
import user.UserManager;
import util.runnable.IThread;


/**
 * 数据库 处理池 
 * @author DXF
 */
public class DBHandlePool extends IThread {

	@Override
	public void run() {
		try {
			
			UserInfo user = UserManager.getInstance().removeDBUpdate();
			if( user == null ) return;
			UserManager.getInstance().updata(user);
			
		} catch (Exception e) {
			Logs.error( "DBHandlePool 线程崩溃  " + e.toString() );
			stop();
		}
	}

}
