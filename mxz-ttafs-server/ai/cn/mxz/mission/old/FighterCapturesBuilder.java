//package cn.mxz.mission.old;
//
//import cn.mxz.FighterTemplet;
//import cn.mxz.FighterTempletConfig;
//import cn.mxz.protocols.user.mission.MissionP.FighterCapturesPro;
//import cn.mxz.user.mission.FighterCapture;
//
//public class FighterCapturesBuilder {
//
//	public FighterCapturesPro build(FighterCapture fc) {
//
//		FighterCapturesPro.Builder b = FighterCapturesPro.newBuilder();
//
//		int id = fc.getId();
//
//		FighterTemplet fighterTemplet = FighterTempletConfig.get(id);
//
//		b.setId(fighterTemplet.getId());
//
//		b.setPosition(fc.getPosition());
//
//		b.setQuality(fc.getStep());
//
//		return b.buildTest();
//	}
//
//}
