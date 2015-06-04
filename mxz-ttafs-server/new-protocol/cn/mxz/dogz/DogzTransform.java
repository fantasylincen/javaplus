package cn.mxz.dogz;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 神兽
 * @author 林岑
 *
 */
@Communication
public interface DogzTransform {

	/**
	 * 神兽开启界面
	 * @return
	 */
	DogzOpenUI getDogzOpenUI();

	/**
	 * 开启某个神兽
	 * @param dogzId
	 */
	DogzOpenUI open(int dogzId);

	/**
	 * 出战某个神兽
	 * @param dogzId
	 * @return
	 */
	DogzOpenUI setFighting(int dogzId);

	/**
	 * 注魂
	 * @param dogzId
	 * @return
	 */
	DogzOpenUI zhuHun(int dogzId);

	void setUser(City user);
}
