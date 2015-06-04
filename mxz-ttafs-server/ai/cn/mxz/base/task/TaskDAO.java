package cn.mxz.base.task;

/**
 * 任务DAO
 * @author 林岑
 *
 * @param <T>
 */
public interface TaskDAO<T> {

	T createDTO();

	void save(T t);

	void delete(int id, String id2);
}
