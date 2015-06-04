//package cn.mxz.friend;
//
//import cn.javaplus.common.util.collections.counter.Counter;
//import cn.javaplus.common.util.collections.counter.ICounter;
//import cn.mxz.PropTemplet;
//import cn.mxz.bag.Bag;
//import cn.mxz.bag.Grid;
//import cn.mxz.prop.PropTempletFactory;
//import cn.mxz.protocols.user.PropP.PropPro;
//import cn.mxz.protocols.user.friend.PropsGiftableP.PropsGiftablePro;
//
//class PropsGiftableBuilder {
//
//
//	public PropsGiftablePro build(Bag<Grid> bag) {
//
//		ICounter<Integer> types = getTypes(bag);
//
//		PropsGiftablePro.Builder b = PropsGiftablePro.newBuilder();
//
//		for (Integer type : types.keySet()) {
//
//			b.addProps(build(type, types.get(type)));
//		}
//
//		return b.build();
//	}
//
//	private PropPro build(Integer type, int count) {
//
//		PropPro.Builder b = PropPro.newBuilder();
//
//		b.setTypeId(type);
//
//		b.setCount(count);
//
//		return b.build();
//	}
//
//	private ICounter<Integer> getTypes(Bag<Grid> bag) {
//
//		ICounter<Integer> c = new Counter<Integer>();
//
//		for (Grid grid : bag) {
//
//			PropTemplet temp = PropTempletFactory.get(grid.getTempletId());
//
//			if(temp.getGiveable() == 1) {
//
//				c.add(grid.getTempletId(), grid.getCount());
//			}
//		}
//
//		return c;
//	}
//
//
//}
