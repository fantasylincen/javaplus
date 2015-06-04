package cn.mxz.base.task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lemon.commons.user.IUser;

import db.domain.TaskDTO;

/**
 * Task管理器
 *
 * @author 林岑
 * @since 2013-3-6
 */
public abstract class TaskManager<T extends TaskDTO> implements TaskChecker {

	protected IUser					user;

	// 别把这个变量的初始化放到构造函数里面去. 不然日常任务会报错的.
	private List<TaskListener<T>>	listener	= Lists.newArrayList();

	public List<TaskListener<T>> getTaskListener() {

		return listener;
	}

	/**
	 * 当前所有普通任务(所有 已接且未完成, 已完成且未领奖 的任务)
	 */
	protected Map<Integer, Task<T>>	allNormalTask;

//	private boolean					isInit;

	protected TaskManager(IUser user) {

		this.user = user;
		init();
	}

	private void init() {

		allNormalTask = loadFromDB();

		// if (isNoTask()) { // 如果该用户没有任何任务， 那么就开启第一个任务

		if (allNormalTask.isEmpty()) {
			initToDBAndMemory();
		}
		// }
	}

	/**
	 * 添加一个任务完成监听器
	 *
	 * @param tl
	 */
	public void addTaskListener(TaskListener<T> tl) {
		listener.add(tl);
	}

	/**
	 * 初始化用户任务到内存和数据库中, 该方法仅在用户没有任何任务的时候调用
	 */
	protected abstract void initToDBAndMemory();

	protected abstract Map<Integer, Task<T>> loadFromDB();

	/**
	 * 创建一个任务
	 */
	protected abstract Task<T> newTaskInstance(String uname, int id);

	/**
	 * 任务归还后发奖
	 */
	protected abstract void sendAward(Task<T> task);

	/**
	 * 数据库工具
	 *
	 * @return
	 */
	protected abstract TaskDAO<T> getDAO();

	/**
	 * 开启指定任务, id不能是当前已经接受的任务, 否则会报错
	 *
	 * @param id
	 */
	public void openNewTask(int id) {

		if (hasBeenDone(id)) {// 如果这个任务曾今已经做过了

			delete(id);
		}

		T ut = getDAO().createDTO();

		ut.setTaskId(id);

		ut.setUname(user.getId());

		getDAO().save(ut);

		Task<T> t = newTaskInstance(user.getId(), id);

		allNormalTask.put(t.getId(), t);
	}

	public void delete(int id) {

		getDAO().delete(id, user.getId());

		allNormalTask.remove(id);
	}

	/**
	 * 判断这个任务是否是曾今已经做过的
	 *
	 * @param id
	 * @return
	 */
	private boolean hasBeenDone(Integer id) {

		return getAllVisible().containsKey(id);
	}

	/**
	 * @return 获取所有可见的任务
	 */
	protected Map<Integer, Task<T>> getAllVisible() {
		HashMap<Integer, Task<T>> newHashMap = Maps.newHashMap(allNormalTask);
		Collection<Task<T>> values = newHashMap.values();
		Iterator<Task<T>> it = values.iterator();
		while (it.hasNext()) {
			Task<T> task = (Task<T>) it.next();
			if (!task.isVisible()) {
				it.remove();
			}
		}
		return newHashMap;
	}

	/**
	 * 指定任务是否被开启
	 */

	public boolean isOpen(Integer taskId) {

		return getAllVisible().containsKey(taskId);
	}

	/**
	 * 归还任务, 同时给与奖励
	 *
	 * @param id
	 * @throws TaskUnFinishAllException
	 * @throws AreadyReceivedReward
	 * @throws TaskNotFoundException
	 *
	 * @throws TaskException
	 */
	public void giveBack(int id) throws TaskNotFoundException, AreadyReceivedReward, TaskUnFinishAllException {

		Task<T> task = getAllVisible().get(id); // 取得指定ID任务

		checkGiveBack(task, id); // 检查

		sendAward(task); // 发放奖励

		task.giveBack(); // 归还这个任务

		getDAO().save(task.getDTO());

		for (TaskListener<T> tl : listener) {

			tl.onGiveBack(task);
		}
	}

	//
	// private boolean isNoTask() {
	//
	// return getAllVisible().isEmpty();
	// }

	/*
	 * 还任务前检查
	 */
	private void checkGiveBack(Task<T> task, int id) throws TaskNotFoundException, AreadyReceivedReward, TaskUnFinishAllException {

		if (task == null) {

			throw new TaskNotFoundException(id);
		}

		if (task.isGiveBack()) {

			throw new AreadyReceivedReward(id);
		}

		if (!task.isFinishAll()) {

			throw new TaskUnFinishAllException(id);
		}
	}

	/**
	 * 当前所有任务(所有 已接且未完成, 已完成且未领奖 的任务), 不可见的任务是不会返回的
	 */
	public Collection<Task<T>> getAll() {

//		ensureInit();

		return getAllVisible().values();
	}

//	protected void ensureInit() {
//		if (!isInit) {
//			init();
//			isInit = true;
//		}
//	}

	/**
	 * 所有未完成任务
	 *
	 * @return
	 */
	public Collection<Task<T>> getAllUnFinish() {
//		ensureInit();
		Collection<Task<T>> c = Lists.newArrayList();

		for (Task<T> t : getAll()) {

			if (!t.isFinishAll()) {

				c.add(t);
			}
		}

		return c;
	}

	/**
	 * 所有未归还的任务
	 *
	 * @return
	 */
	public Collection<Task<T>> getAllUnGiveBack() {
//		ensureInit();
		Collection<Task<T>> c = Lists.newArrayList();

		for (Task<T> t : getAll()) {

			if (!t.isGiveBack()) {

				c.add(t);
			}
		}

		return c;
	}

	/**
	 * 尝试完成当前的任务
	 *
	 * @param e
	 */
	@Override
	public final void tryToFinishTask(Environment e) {

		// Clock c = new ClockImpl();
		// c.start();

		for (Task<T> t : getNeedCheck()) {

			t.updateEnviroment(e);

			// 如果任务已经达成,
			// 且未归还的前提下,
			// 执行该方法
			if (!t.isGiveBack() && !t.isFinishAll() && t.hasBeenCompleted()) {

				int times = t.finishOneTime();

				if (times > 0) {

					getDAO().save(t.getDTO());

					// Debuger.debug("TaskManager.tryToFinishTask()" + t.getId()
					// + ", " + times
					// );

					for (TaskListener<T> tl : listener) {

						tl.onFinish(t);
					}
				}
			}
		}

		for (TaskListener<T> tl : listener) {

			tl.onCheckOver();
		}

		// c.stop();
		// Debuger.debug("TaskManager.tryToFinishTask() 任务校验耗时:" + c.getTime() /
		// 10000000 + "ms");
	}

	/**
	 * 需要检查的任务
	 *
	 * @return
	 */
	private Collection<Task<T>> getNeedCheck() {
//		ensureInit();
		return getAllVisible().values();
	}

	/**
	 *
	 * 获取玩家指定ID的任务, 如果是没有开启的任务, 返回空
	 */
	public Task<T> get(Integer id) {
//		ensureInit();
		return allNormalTask.get(id);
	}

	public IUser getUser() {

		return user;
	}
}
