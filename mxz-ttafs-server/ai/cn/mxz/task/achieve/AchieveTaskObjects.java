package cn.mxz.task.achieve;

import java.lang.reflect.Constructor;

import mongo.gen.MongoGen.AchieveTaskDto;
import mongo.gen.MongoGen.Daos;
import cn.mxz.AchieveTaskTempletConfig;
import cn.mxz.base.task.Task;


public class AchieveTaskObjects {

	@SuppressWarnings("unchecked")
	static Task<AchieveTaskDto> createTaskById(AchieveTaskDto ut) {
		try {

			Class<?> c = Class.forName("cn.mxz.task.achieve.tasks.T" + ut.getTaskId());

			Constructor<?> con = c.getConstructors()[0];

			Object ins = con.newInstance();

			Task<AchieveTaskDto> task = (Task<AchieveTaskDto>) ins;

			task.init(Daos.getAchieveTaskDao(), ut, AchieveTaskTempletConfig.get(ut.getTaskId()));

			return task;

		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}
