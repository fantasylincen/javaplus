package cn.vgame.a.turntable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;

public class MyTimer implements TurntableTimer {

	private Timer timer;
	private List<TurntableTask> tasks = Lists.newArrayList();

	public MyTimer() {
		timer = new Timer(true);
	}

	public void cancel() {
		timer.cancel();
		tasks.clear();
	}

	boolean isPause;

	
	public boolean isPause() {
		return isPause;
	}

	@Override
	public void pause(long pauseMillis) {
		if (isPause)
			throw new RuntimeException("is pause now! can't pause agaim!");
		
		isPause = true;
		
		Log.d("timer pause");
		
		final List<TurntableTask> all = Lists.newArrayList(tasks);
		cancel();
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				timer = new Timer();
				for (TurntableTask t : all) {
					scheduleAtFixedRate(t.copy());
				}
				Log.d("reset tasks");
				isPause = false;
			}
		}, pauseMillis);

	}

	@Override
	public void scheduleAtFixedRate(TurntableTask task) {
		timer.scheduleAtFixedRate(task, task.getDelay(), task.getPeriod());
		tasks.add(task);
	}

}
