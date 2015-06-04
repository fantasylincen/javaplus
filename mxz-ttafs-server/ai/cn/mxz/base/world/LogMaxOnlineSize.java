//package cn.mxz.base.world;
//
//import java.util.Collection;
//
//import cn.mxz.user.City;
//
//import com.linekong.platform.protocol.erating.debug.Debuger;
//
//public class LogMaxOnlineSize extends SocketMangerAdapter {
//
//	private int						maxSize;
//
//	private static LogMaxOnlineSize	instance;
//
//	private LogMaxOnlineSize() {
//	}
//
//	public static final LogMaxOnlineSize getInstance() {
//		if (instance == null) {
//			instance = new LogMaxOnlineSize();
//		}
//		return instance;
//	}
//
//	@Override
//	public void onBind(City u) {
//		World world = WorldFactory.getWorld();
//		Collection<City> all = world.getOnlineAll();
//		int size = all.size();
//
//		if (size > maxSize) {
//			maxSize = size;
//
//			Debuger.debug("[MaxOnlineSize]" + maxSize);
//		}
//
//	}
//
//	public int getMaxSize() {
//		return maxSize;
//	}
//
//}
