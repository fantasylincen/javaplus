package cn.mxz.base.task;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;
import db.domain.TaskDTO;

abstract class TaskBase<T extends TaskDTO> implements Task<T> {

	protected City user;


	protected T task;

	private TaskTemplet temp;

	private Environment	environment;


	protected TaskDAO<T>	DAO;

	@Override
	public void init(TaskDAO<T> DAO, T task, TaskTemplet temp){

		this.task = task;

		this.DAO = DAO;

		this.temp = temp;

		if(temp == null) {
			throw new NullPointerException("未找到任务模板!" + task.getTaskId());
		}

		World w = WorldFactory.getWorld();

		user = w.get(task.getUname());
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public T getDTO(){
		return task;
	}

	@Override
	public void giveBack() {
		task.setIsDraw(true);
		String id = user.getId();
		String nick = user.getPlayer().getNick();
		int taskId = task.getTaskId();
		Debuger.debug("TaskBase.giveBack() task finish " + nick + ", " + id + " task id " + taskId);
	}

	@Override
	public boolean isGiveBack() {
		return task.getIsDraw();
	}

	@Override
	public int getId() {
		return task.getTaskId();
	}

	@Override
	public int getPassed() {
		return task.getFinishtimes();
	}

	@Override
	public boolean isFinishAll() {

		return isGiveBack() || getPassed() >= getMax();
	}

	/**
	 * 获得所需达成的次数
	 * @return
	 */
	@Override
	public int getMax() {

		return temp.getMax();
	}

	@Override
	public int finishOneTime() {
		int finishTimes = getFinishTimesThisTime();
		addFinishTimes(finishTimes);
		return finishTimes;
	}

	private void addFinishTimes(int count) {
		task.setFinishtimes((short) (task.getFinishtimes() + count));
	}

	/**
	 * 本次完成的次数
	 */
	protected abstract int getFinishTimesThisTime();

	@Override
	public final boolean hasBeenCompleted() {
		return levelEnough() && !isFinishAll() && isAchievement();
	}

	/***
	 * 是否达到任务条件
	 * @return
	 */
	protected abstract boolean isAchievement();

	private boolean levelEnough() {
		return user.getLevel() >= temp.getUserLevelNeed();
	}

	@Override
	public void updateEnviroment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return getId() + ":" + "		是否完成:" + isFinishAll() + "		是否归还:" + isGiveBack();
	}
}
