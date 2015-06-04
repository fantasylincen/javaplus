package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DaoInterface;
import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.db.mongo.Key;

/**
 * 日常任务
 * @author 林岑
 *
 */
@Dao
@DaoInterface("cn.mxz.base.task.TaskDAO<DailyTaskDto>")
@DtoInterface("db.domain.TaskDTO")
interface DailyTask {

	@Key
	int getTaskId();

	@Key
	String getUname();

	int getFinishtimes();

	boolean isDraw();
}
