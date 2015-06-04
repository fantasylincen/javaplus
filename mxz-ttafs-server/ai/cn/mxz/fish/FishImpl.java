//package cn.mxz.fish;
//
//import cn.mxz.FishTemplet;
//import cn.mxz.FishTempletConfig;
//import cn.mxz.prop.PropTempletFactory;
//import cn.mxz.user.City;
//import db.domain.UserFishes;
//
//class FishImpl implements Fish {
//
//	UserFishes	fish;
//
//
//	FishImpl(City city, UserFishes fish) {
//
//		this.fish = fish;
//	}
//
//	@Override
//	public int getFree() {
//
//		FishTemplet temp = FishTempletConfig.get(getTempletId());
//
//		return temp.getAddUp() - getCount();
//	}
//
//	@Override
//	public boolean isFull() {
//
//		return getFree() <= 0;
//	}
//
//	@Override
//	public int getCount() {
//
//		return fish.getCount();
//	}
//
//	@Override
//	public int getGridId() {
//
//		return fish.getGridId();
//	}
//
//	@Override
//	public int getTempletId() {
//
//		return fish.getTypeid();
//	}
//
//	@Override
//	public int getPropType() {
//
//		return PropTempletFactory.getPropType(getTempletId());
//	}
//
//	@Override
//	public String toString() {
//		return "FishImpl [getCount()=" + getCount() + ", getGridId()=" + getGridId() + ", getTempletId()=" + getTempletId() + "]";
//	}
//
//
//}
