package cn.vgame.a.turntable;

import cn.javaplus.time.taskutil.TaskSafety;

public interface ITimer {

	void cancel();

	void scheduleAtFixedRate(TaskSafety robotTask, long sec, long sum);

}
