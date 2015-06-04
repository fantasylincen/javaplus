//package cn.mxz.findfighter;
//
//import templets.GodTypeTemplet;
//import templets.GodTypeTempletConfig;
//
//public abstract class GodProbabilitys implements IGodProbabilitys {
//
//	@Override
//	public final int[] getIs() {
//		final int[] is = new int[GodTypeTempletConfig.getKeys().size()];
//		for (int i = 0; i < is.length; i++) {
//			final GodTypeTemplet gt = GodTypeTempletConfig.get(GodTypeTempletConfig.getKeys().get(i));
//			is[i] = getProbability(gt);
//		}
//		return is;
//	}
//
//	protected abstract int getProbability(GodTypeTemplet gt);
//}
//
//
//class Di extends GodProbabilitys{
//
//	@Override
//	protected int getProbability(GodTypeTemplet gt) {
//		return gt.getLandOdds();
//	}
//}
//
//class Tian extends GodProbabilitys{
//
//	@Override
//	protected int getProbability(GodTypeTemplet gt) {
//		return gt.getSkyOdds();
//	}
//
//}
//
//class Jin extends GodProbabilitys{
//
//	@Override
//	protected int getProbability(GodTypeTemplet gt) {
//		return gt.getGoldOdds();
//	}
//
//}
