package cn.mxz.task.dailytask;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.DailyTaskDao;
import mongo.gen.MongoGen.DailyTaskDao.DailyTaskDtoCursor;
import mongo.gen.MongoGen.DailyTaskDto;
import mongo.gen.MongoGen.Daos;
import cn.javaplus.util.Util;
import cn.mxz.DailyTaskTemplet;
import cn.mxz.DailyTaskTempletConfig;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskManager;
import cn.mxz.city.City;
import cn.mxz.task.TaskList;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DailyTaskManagerImpl extends TaskManager<DailyTaskDto> implements
		DailyTaskManager {

	public class GiveBackOnFinish extends TaskAdaptor<DailyTaskDto> {

		@Override
		public void onFinish(Task<DailyTaskDto> task) {
			try {
				if (task.isFinishAll()) {
					giveBack(task.getId());
				}
			} catch (Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}
		}
	}

	private DailyTaskPanelImpl panel;
	private DailyTaskListImpl taskList;

	public DailyTaskManagerImpl(City city) {
		super(city);
		addTaskListener(new GiveBackOnFinish());
	}

	@Override
	protected void initToDBAndMemory() {

		Collection<DailyTaskTemplet> values = DailyTaskTempletConfig.getAll();

		List<DailyTaskDto> tasks = Lists.newArrayList();

		for (DailyTaskTemplet t : values) {

			if (t.getIsOpenDefault() == 1) {

				DailyTaskDto ut = getDAO().createDTO();

				ut.setTaskId(t.getId());

				ut.setUname(user.getId());

				tasks.add(ut);
			}
		}

		for (DailyTaskDto d : tasks) {
			getDAO().save(d);
		}

		addToMemory(tasks);
	}

	private void addToMemory(List<DailyTaskDto> tasks) {
		for (DailyTaskDto dt : tasks) {
			Task<DailyTaskDto> t = newTaskInstance(user.getId(), dt.getTaskId());
			allNormalTask.put(t.getId(), t);
		}
	}

	@Override
	protected Map<Integer, Task<DailyTaskDto>> loadFromDB() {

		DailyTaskDao DAO = Daos.getDailyTaskDao();

		DailyTaskDtoCursor f = DAO.findByUname(user.getId());

		Map<Integer, Task<DailyTaskDto>> at = Maps.newHashMap();

		while (f.hasNext()) {
			DailyTaskDto ut = f.next();
			if (DailyTaskTempletConfig.get(ut.getTaskId()) == null) {

				continue;
			}

			Task<DailyTaskDto> task = createTaskById(ut);

			at.put(task.getId(), task);
		}

		return at;
	}

	private City getCity() {
		return (City) user;
	}

	@Override
	protected Task<DailyTaskDto> newTaskInstance(String uname, int id) {

		DailyTaskDto ut = new DailyTaskDto();

		ut.setUname(uname);

		ut.setTaskId(id);

		return createTaskById(ut);
	}

	@Override
	protected void sendAward(Task<DailyTaskDto> task) {
		DailyTaskTemplet temp = DailyTaskTempletConfig.get(task.getId());
		PrizeSender sender = PrizeSenderFactory.getPrizeSender();
		sender.send(getCity().getPlayer(), temp.getAwards());

		int integral = temp.getIntegral();
		getCity().getDailyTaskManager().addScore(integral);
		
		getCity().getUserCounterHistory().add(CounterKey.DAILY_TASK_FINISH_COUNT_HISTORY, 1);
	}

	@Override
	protected DailyTaskDao getDAO() {
		return Daos.getDailyTaskDao();
	}

	@Override
	public DailyTaskPanel getDailyTaskPanel() {
		// ensureInit();
		if (panel == null) {
			panel = new DailyTaskPanelImpl(getCity());
		}
		return panel;
	}

	@Override
	public TaskList<DailyTaskDto> getDailyTaskList() {
		// ensureInit();
		if (taskList == null) {
			taskList = new DailyTaskListImpl(getCity());
		}
		return taskList;
	}

	Task<DailyTaskDto> createTaskById(DailyTaskDto ut) {
		try {

			Class<?> c = Class.forName("cn.mxz.task.dailytask.tasks.T"
					+ ut.getTaskId());

			Constructor<?> con = c.getConstructors()[0];

			Object ins = con.newInstance();

			@SuppressWarnings("unchecked")
			Task<DailyTaskDto> task = (Task<DailyTaskDto>) ins;

			task.init(Daos.getDailyTaskDao(), ut,
					DailyTaskTempletConfig.get(ut.getTaskId()));

			return task;

		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	@Override
	public Task<DailyTaskDto> getTask(int taskId) {
		// ensureInit();
		return getAllVisible().get(taskId);
	}

	@Override
	public void addScore(int integral) {
		getCity().getUserCounter().add(CounterKey.DAILY_TASK_SCORE, integral);
	}

	@Override
	public int getScore() {
		if(getCity().isTester()) {
			return 120;
		}
		return getCity().getUserCounter().get(CounterKey.DAILY_TASK_SCORE);
	}

}
