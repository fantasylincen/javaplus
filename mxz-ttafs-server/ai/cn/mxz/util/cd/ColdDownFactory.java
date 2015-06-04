package cn.mxz.util.cd;

public class ColdDownFactory {

	public static CDManager getCDManager(String userId) {

		return new CDManagerImpl(userId);
	}
}
