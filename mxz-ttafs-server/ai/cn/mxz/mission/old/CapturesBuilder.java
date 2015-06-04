//package cn.mxz.mission.old;
//
//import cn.mxz.protocols.user.mission.MissionP.CapturesPro;
//import cn.mxz.user.mission.Captures;
//import cn.mxz.user.mission.FighterCapture;
//
///**
// * 战俘列表
// * @author 林岑
// *
// */
//public class CapturesBuilder {
//
//	public CapturesPro build(Captures captures) {
//
//		CapturesPro.Builder b = CapturesPro.newBuilder();
//
//		for (FighterCapture fc : captures) {
//
//			b.addFighters(new FighterCapturesBuilder().build(fc));
//		}
//
//		return b.buildTest();
//	}
//
//
//}
