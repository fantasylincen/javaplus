package cn.mxz.task;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.AchieveTaskDto;
import mongo.gen.MongoGen.DailyTaskDto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;
import cn.mxz.AchieveTaskMissionTemplet;
import cn.mxz.AchieveTaskMissionTempletConfig;
import cn.mxz.AchieveTaskTemplet;
import cn.mxz.AchieveTaskTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.base.service.AbstractService;
import cn.mxz.base.task.AreadyReceivedReward;
import cn.mxz.base.task.Task;
import cn.mxz.base.task.TaskNotFoundException;
import cn.mxz.base.task.TaskUnFinishAllException;
import cn.mxz.city.City;
import cn.mxz.handler.TaskService;
import cn.mxz.protocols.user.task.TaskP.TaskListPro;
import cn.mxz.task.achieve.AchieveTaskManager;
import cn.mxz.task.dailytask.DailyTaskManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.message.MessageSender;

import com.google.common.collect.Lists;

@Component("taskService")
@Scope("prototype")
public class TaskServiceImpl extends AbstractService implements TaskService {

	@Override
	public TaskListPro getDailyTask() {
		DailyTaskManager manager = getCity().getDailyTaskManager();
		TaskList<DailyTaskDto> list = manager.getDailyTaskList();
		return new TaskListBuilder().build(list);
	}

	@Override
	public TaskListPro getAchieveTask() {
		AchieveTaskManager am = getCity().getAchieveTaskManager();
		return new TaskListBuilder().build(am.getTaskList());
	}

	@Override
	public void receiveAchieveTaskPrize(int taskId) {

		AchieveTaskManager manager = getCity().getAchieveTaskManager();

		try {
			manager.giveBack(taskId);

			MessageSender.sendMessage(getSocket(), S.S10147);

		} catch (TaskNotFoundException e) {

			throw new SureIllegalOperationException("-任务未找到!");

		} catch (AreadyReceivedReward e) {

			throw new OperationFaildException(S.S10111);

		} catch (TaskUnFinishAllException e) {

			throw new OperationFaildException(S.S10121);

		}
	}

	@Override
	public void receiveDailyTaskPrize(int taskId) {

		DailyTaskManager manager = getCity().getDailyTaskManager();

		try {

			manager.giveBack(taskId);

			MessageSender.sendMessage(getSocket(), S.S10147);

		} catch (TaskNotFoundException e) {

			throw new SureIllegalOperationException("-任务未找到!");

		} catch (AreadyReceivedReward e) {

			throw new OperationFaildException(S.S10111);

		} catch (TaskUnFinishAllException e) {

			throw new OperationFaildException(S.S10121);

		}
	}

	@Override
	public void receiveAchieveTaskChapterPrize(int chapterId) {

		City city = getCity();
		AchieveTaskManager am = city.getAchieveTaskManager();

		List<Task<AchieveTaskDto>> all = am.getTasksByChapterId(chapterId);

		if (isAllFinish(all) && !hasReceive(chapterId)) {
			receive(chapterId);
		} else {
			throw new OperationFaildException(S.S10253);
		}

	}

	private void receive(int chapterId) {

		AchieveTaskMissionTemplet temp = AchieveTaskMissionTempletConfig.get((short) chapterId);

		PrizeSenderFactory.getPrizeSender().send(getPlayer(), temp.getAwards());

		UserCounter uc = getCity().getUserCounterHistory();
		uc.mark(CounterKey.HAS_RECEIVE_CHAPTER_REWARD, chapterId);
	}

	private boolean hasReceive(int chapterId) {
		UserCounter uc = getCity().getUserCounterHistory();
		return uc.isMark(CounterKey.HAS_RECEIVE_CHAPTER_REWARD, chapterId);
	}

	private boolean isAllFinish(List<Task<AchieveTaskDto>> all) {
		for (Task<AchieveTaskDto> task : all) {
			if (!task.isFinishAll()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getAchieveTaskChapterPrizeStatus() {
		List<Integer> ls = Lists.newArrayList();
		int max = getMaxChapterId();

		for (int i = 1; i <= max; i++) {
			ls.add(hasReceive(i) ? 1 : 0);
		}

		String linkWith = Util.Collection.linkWith(",", ls);
//		Debuger.debug("TaskServiceImpl.getAchieveTaskChapterPrizeStatus()" + linkWith);
		return linkWith;
	}

	private int getMaxChapterId() {
		List<AchieveTaskTemplet> all = AchieveTaskTempletConfig.getAll();

		int max = Integer.MIN_VALUE;
		for (AchieveTaskTemplet a : all) {
			short c = a.getChapter();
			if (c > max) {
				max = c;
			}
		}
		return max;
	}

	@Override
	public void receiveDailyTaskBoxReward(int index) {
		TaskList<DailyTaskDto> list = getCity().getDailyTaskManager().getDailyTaskList();
		BoxReward reward = list.getBoxReward();
		List<TaskBox> bx = reward.getBoxes();
		TaskBox box = bx.get(index);
		if(!box.canReceive()) {
			throw new SureIllegalOperationException("不可领取");
		}
		if(box.isOpen()) {
			throw new SureIllegalOperationException("已经领取了");
		}
		
		box.open();
	}

}
