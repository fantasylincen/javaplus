package util.taskscheduling.task;

import java.util.TimerTask;

public class CommandTask extends TimerTask{

	private int a = 0;
	@Override
	public void run() {
		a++;
		System.out.println( "执行了" + a + "次" );
	}
	
}
