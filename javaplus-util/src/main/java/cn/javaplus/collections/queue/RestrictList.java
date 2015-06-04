package cn.javaplus.collections.queue;

import java.util.ArrayList;

/**
 * 长度被限制的列表, 该队列一旦超过最大限制, 那么就会自动移除第一个元素
 * @author 	林岑
 * @since	2012年9月26日 11:34:09
 */
@SuppressWarnings("serial")
public class RestrictList<V> extends ArrayList<V> {
	
	/**
	 * 最大记录数量
	 */
	public final int maxCount;
	
	/**
	 * 新建一个记录队列, 并初始化一个最大记录数
	 * @param maxCount
	 */
	public RestrictList(int maxCount) {
		this.maxCount = maxCount;
	}

	/**
	 * 追加一条记录, 如果已经达到了最大记录数, 那么就会自动删除最前面一条记录
	 * @param value
	 */
	public V append(V value) {
		add(value);
		V poll = null;
		while(size() > maxCount) {
			poll = remove(0);
		}
		return poll;
	}
}
