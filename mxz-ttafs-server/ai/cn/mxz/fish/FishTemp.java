//package cn.mxz.fish;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//import templets.FishTemplet;
//import templets.FishTempletConfig;
//import cn.mxz.prop.Prop;
//
//public class FishTemp implements Fish {
//
//	private static AtomicInteger id = new AtomicInteger();
//
//	private int	typeId;
//
//	private int	count;
//
//	private int	gridId;
//
//	public FishTemp(int typeId, int count) {
//
//		this.typeId = typeId;
//
//		this.count = count;
//
//		gridId = id.addAndGet(1);
//	}
//
//	private class MyFish implements Prop {
//
//
//		@Override
//		public int getTypeId() {
//			return typeId;
//		}
//
//		@Override
//		public int getAddUp() {
//			return 1;
//		}
//
//		@Override
//		public String getName() {
//			return getTemplet().getName();
//		}
//
//		@Override
//		public int getLevel() {
//			return 1;
//		}
//
//		@Override
//		public int getPropType() {
//			return 16;
//		}
//
//		@Override
//		public boolean giftable() {
//			return getTemplet().getGiveable() == 1;
//		}
//
//		private FishTemplet getTemplet() {
//			return FishTempletConfig.get(getTypeId());
//		}
//
//	}
//
//	@Override
//	public int getFree() {
//
//		FishTemplet temp = FishTempletConfig.get(getProp().getTypeId());
//
//		return temp.getAddUp() - getCount();
//	}
//
//	@Override
//	public boolean isFull() {
//		return getFree() <= 0;
//	}
//
//	@Override
//	public Prop getProp() {
//		return new MyFish();
//	}
//
//	@Override
//	public int getCount() {
//		return count;
//	}
//
//	@Override
//	public int getGridId() {
//		return gridId;
//	}
//
//	public void add(int add) {
//		count += add;
//	}
//
//	@Override
//	public String toString() {
//		return "FishTemp [typeId=" + typeId + ", count=" + count + ", gridId=" + gridId + "]";
//	}
//
//}
