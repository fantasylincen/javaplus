package cn.mxz.nvwa;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 女娲造人
 *
 * @author 林岑
 *
 */
@Communication
public interface NvwaTransform {

	/**
	 * 界面
	 * @return
	 */
	NvwaUI getUI();

	/**
	 * 买
	 */
	NvwaUI buy();

	void setUser(City user);
}
