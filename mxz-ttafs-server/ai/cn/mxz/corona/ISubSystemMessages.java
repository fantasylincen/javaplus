package cn.mxz.corona;

import java.util.List;

/**
 * 管理功能子系统的广播消息的接口
 * @author Administrator
 *
 * @param <T>
 */
interface ISubSystemMessages<T> {

	public abstract List<T> add(T m);
	public abstract List<T> get();

}