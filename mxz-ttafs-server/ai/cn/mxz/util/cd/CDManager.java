package cn.mxz.util.cd;

import cn.javaplus.time.colddown.ColdDown;


/**
 * 冷却时间管理器
 * @author 林岑
 *
 */
public interface CDManager {

	/**
	 * 获得指定key + args的冷却时间计时器
	 * @param key
	 * @param args
	 * @return
	 */
	ColdDown get(CDKey key);

}
