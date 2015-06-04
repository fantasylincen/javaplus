//package cn.mxz.init;
//
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import templets.FighterTemplet;
//import templets.FighterTempletConfig;
//import cn.mxz.handler.InitService;
//
//import com.lemon.commons.socket.ISocket;
//
///**
// * 测试帐号创建工厂
// * @author 林岑
// *
// */
//public class TesterFactory {
//
//	public static boolean isTester(String id) {
//
//		return id.equals("mxztester");
//	}
//
//	public static void createTester(ISocket socket, String id) {
//
//		InitService init = new InitServiceImpl();
//
//		init.init(socket);
//
//		id = id + Util.R.nextInt();
//
//		init.access(id, "");
//
//		List<FighterTemplet> fs = FighterTempletConfig.findByCategory(0);
//
//		init.setUserType(fs.get(Util.R.nextInt(fs.size())).getType());
//
//		init.setNick("nick" + id);
//
//		init.create();
//	}
//
//}
