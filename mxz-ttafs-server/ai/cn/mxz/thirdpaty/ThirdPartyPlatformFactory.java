package cn.mxz.thirdpaty;

import cn.mxz.city.City;



/**
 * 第三方合作平台工厂
 * @author 林岑
 *
 */
public class ThirdPartyPlatformFactory {

	public static ThirdPartyPlatform getThirdPartyPlatform() {
		return new CustomizedEratingConnector();
	}

	public static ThirdPartyRole createRole(City city) {
		return new LineKongRoleImpl(city);
	}

	public static ThirdPartyPlatform getThirdPartyPlatform(String ip, int port) {
		return new EratingConnectorImpl1(ip + ":" + port);
	}
}
