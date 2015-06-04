package cn.mxz.yoxi;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface YoXiTransform {

	YoXi yoXi(String yoXi, String pwd);
	void setUser(City user);
}
