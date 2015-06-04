package cn.mxz.task;

import cn.javaplus.math.Fraction;
import cn.mxz.base.task.Task;
import cn.mxz.protocols.user.task.TaskP.TaskPro;
import cn.mxz.util.FractionBuilder;
import cn.mxz.util.debuger.Debuger;

class TaskBuilder {

	public TaskPro build(Task<?> t) {
		TaskPro.Builder b = TaskPro.newBuilder();
		b.setId(t.getId());
		Fraction schedule = getSchedule(t);
		if(t.getId() == 10409) {
			Debuger.debug("TaskBuilder.build()" + schedule);
		}
		b.setSchedule(new FractionBuilder().build(schedule));
		b.setState(getStatus(t));
		return b.build();
	}
	

	// 任务状态: 0进行中 1可领取 2已领取
	private int getStatus(Task<?> t) {

		if (t.isGiveBack()) {
			return 2;
		}

		if (t.isFinishAll()) {
			return 1;
		}

		return 0;
	}

	private Fraction getSchedule(Task<?> t) {
//		if(t.getId() == 12201) {
//			int passed = t.getPassed();
//			Debuger.debug("TaskBuilder.getSchedule()" + passed);
//		}
		int p = t.getPassed();
		int m = t.getMax();

		if (p > m) {
			p = m;
		}
		Fraction f = new Fraction(p, m);
		return f;
	}

}
