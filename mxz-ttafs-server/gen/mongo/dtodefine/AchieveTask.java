package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DaoInterface;
import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.db.mongo.Key;

/**
 * 成就任务
 * @author 林岑
 *
 */
@Dao
@DaoInterface("cn.mxz.base.task.TaskDAO<AchieveTaskDto>")
@DtoInterface("db.domain.TaskDTO")
interface AchieveTask {

	@Key
	int getTaskId();

	@Key
	String getUname();

	int getFinishtimes();

	boolean isDraw();
}
