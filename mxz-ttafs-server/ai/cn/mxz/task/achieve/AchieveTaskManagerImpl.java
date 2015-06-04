package cn.mxz.task.achieve;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.AchieveTaskDao;
import mongo.gen.MongoGen.AchieveTaskDao.AchieveTaskDtoCursor;
import mongo.gen.MongoGen.AchieveTaskDto;
import mongo.gen.MongoGen.Daos;
import cn.mxz.AchieveTaskTemplet;
import cn.mxz.AchieveTaskTempletConfig;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskManager;
import cn.mxz.city.City;
import cn.mxz.task.TaskList;

import com.google.common.collect.Lists;

public class AchieveTaskManagerImpl extends TaskManager<AchieveTaskDto> implements AchieveTaskManager {

	private TaskList<AchieveTaskDto>	taskList;

	public AchieveTaskManagerImpl(City city) {
		super(city);
	}


	@Override
	protected void initToDBAndMemory() {

		Collection<AchieveTaskTemplet> values = AchieveTaskTempletConfig.getAll();

		List<AchieveTaskDto> tasks = Lists.newArrayList();

		for (AchieveTaskTemplet t : values) {

			if (t.getIsOpenDefault() == 1) {

				AchieveTaskDto ut = new AchieveTaskDto();

				ut.setTaskId(t.getId());

				ut.setUname(user.getId());

				tasks.add(ut);
			}
		}

//		Debuger.debug("AchieveTaskManagerImpl.initToDBAndMemory() 初始化成就任务:" + getCity());


		for (AchieveTaskDto a : tasks) {
			getDAO().save(a);
		}

		addToDB(tasks);
	}

	private void addToDB(List<AchieveTaskDto> tasks) {
		for (AchieveTaskDto dt : tasks) {
			Task<AchieveTaskDto> t = newTaskInstance(user.getId(), dt.getTaskId());
			allNormalTask.put(t.getId(), t);
		}
	}

	@Override
	protected Map<Integer, Task<AchieveTaskDto>> loadFromDB() {


		AchieveTaskDao DAO = Daos.getAchieveTaskDao();

		AchieveTaskDtoCursor all = DAO.findByUname(user.getId());

		Map<Integer, Task<AchieveTaskDto>> at = new HashMap<Integer, Task<AchieveTaskDto>>();

		while (all.hasNext()) {
			AchieveTaskDto ut = all.next();
			if (AchieveTaskTempletConfig.get(ut.getTaskId()) == null) {

				continue;
			}

			Task<AchieveTaskDto> task = AchieveTaskObjects.createTaskById(ut);

			at.put(task.getId(), task);
		}


		return at;
	}

	private City getCity() {
		return (City) user;
	}


	@Override
	protected Task<AchieveTaskDto> newTaskInstance(String uname, int id) {

		AchieveTaskDto ut = new AchieveTaskDto();

		ut.setUname(uname);

		ut.setTaskId(id);

		return AchieveTaskObjects.createTaskById(ut);
	}

	@Override
	protected void sendAward(Task<AchieveTaskDto> task) {
		AchieveTaskTemplet temp = AchieveTaskTempletConfig.get(task.getId());
		PrizeSender sender = PrizeSenderFactory.getPrizeSender();
		sender.send(getCity().getPlayer(), temp.getAwards());
	}

	@Override
	protected AchieveTaskDao getDAO() {
		return Daos.getAchieveTaskDao();
	}


	@Override
	public TaskList<AchieveTaskDto> getTaskList() {
//		ensureInit();
		if (taskList == null) {
			taskList = new AchieveTaskListImpl(getCity());
		}
		return taskList;
	}


	@Override
	public List<Task<AchieveTaskDto>> getTasksByChapterId(int chapterId) {
//		ensureInit();
		List<Task<AchieveTaskDto>> ls = Lists.newArrayList();
		for (Task<AchieveTaskDto> t : taskList) {
			if(getChapterId(t) == chapterId) {
				ls.add(t);
			}
		}
		return ls;
	}

	/**
	 * 指定任务所在章节
	 * @param t
	 * @return
	 */
	private int getChapterId(Task<AchieveTaskDto> t) {
		AchieveTaskTemplet temp = AchieveTaskTempletConfig.get(t.getId());
		short chapter = temp.getChapter();
		return chapter;
	}

}
