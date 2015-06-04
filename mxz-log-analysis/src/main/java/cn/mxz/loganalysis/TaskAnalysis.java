package cn.mxz.loganalysis;

import java.util.Map.Entry;

import cn.javaplus.collections.counter.Counter;
import cn.mxz.AchieveTaskTemplet;
import cn.mxz.AchieveTaskTempletConfig;
import mongo.gen.AchieveTaskDao;
import mongo.gen.AchieveTaskDao.AchieveTaskDtoCursor;
import mongo.gen.AchieveTaskDto;
import mongo.gen.DailyTaskDao;
import mongo.gen.DailyTaskDao.DailyTaskDtoCursor;
import mongo.gen.DailyTaskDto;
import mongo.gen.Daos;
import mongo.gen.MongoCollectionFetcher;

public class TaskAnalysis {

	public void start() {
//		记录格式: Debuger.debug("TaskBase.giveBack() task finish " + nick + ", " + id + " task id " + taskId);
		
		Daos.setCollectionFetcher(new MongoCollectionFetcher());
		AchieveTaskDao dao = Daos.getAchieveTaskDao();
		AchieveTaskDtoCursor find = dao.find();
		Counter<Integer> counter = new Counter<Integer>();
		while (find.hasNext()) {
			AchieveTaskDto next = find.next();
			boolean isDraw = next.getIsDraw();
			if (isDraw) {
				counter.add(next.getTaskId());
			}
		}
		
		
		for (Entry<Integer, Integer> e : counter.entrySet()) {
			Integer value = e.getValue();
			Integer key = e.getKey();
			
			AchieveTaskTemplet temp = AchieveTaskTempletConfig.get(key);
			short chapter = temp.getChapter();
			System.out.println("完成  第" + chapter+ "章成就任务 <" + temp.getName() + ">的人数:" + value);
		}
		
		
		
		
		DailyTaskDao d = Daos.getDailyTaskDao();
		DailyTaskDtoCursor find2 = d.find();
		Counter<Integer> counter2 = new Counter<Integer>();
		while (find2.hasNext()) {
			DailyTaskDto next = find2.next();
			boolean isDraw = next.getIsDraw();
			if (isDraw) {
				counter.add(next.getTaskId());
			}
		}
		
		
		for (Entry<Integer, Integer> e : counter2.entrySet()) {
			Integer value = e.getValue();
			Integer key = e.getKey();
			
			System.out.println("完成日常任务" + key + " 的人数:" + value);
		}

		
		
		
	}

}
