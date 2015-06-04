package cn.mxz.bag.builder;

import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.ConsumableTemplet;
import cn.mxz.ConsumableTempletConfig;
import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.protocols.user.god.BagP.BagPro;

public class BagBuilder {

	public BagPro build(Bag<Grid> bag) {

		List<Grid> all = bag.getAll();
		
//		all = merge(all);

		BagPro.Builder b = BagPro.newBuilder();

		b.setCapacity(bag.getCapacity());

		for (Grid grid : all) {

			int id = grid.getTempletId();

			ConsumableTemplet temp = ConsumableTempletConfig.get(id);

			if (temp != null && temp.getBackpack() == 0) {

				continue;
			}

			b.addGrid(new GridBuilder().build(grid));
		}

		return b.build();
	}

//	private List<Grid> merge(List<Grid> all) {
//		Counter<Integer> counter = new Counter<Integer>();
//		for (Grid grid : all) {
//			counter.add(grid.getTempletId(), grid.getCount());
//		}
//		return build(counter);
//	}
//
//	private List<Grid> build(Counter<Integer> counter) {
//		List<Grid> ls = Lists.newArrayList();
//		for (Integer typeId : counter.keySet()) {
//			int count = counter.get(typeId);
//			ls.addAll(split(typeId, count));
//		}
//		return ls;
//	}
//
//	private static AtomicInteger ids = new AtomicInteger();
//	
//	private List<Grid> split(int typeId, int count) {
//		PropTemplet temp = PropTempletFactory.get(typeId);
//		int addUp = temp.getAddUp();
//		List<Grid> ls = Lists.newArrayList();
//		while(true) {
//			if(count <= addUp) {
//				
//			}
//		}
//	}

	public BagPro buildChanges(List<Grid> changes) {
		BagPro.Builder b = BagPro.newBuilder();
		b.setCapacity(0);
		for (Grid grid : changes) {
			b.addGrid(new GridBuilder().build(grid));
		}
		return b.build();
	}

	public String buildRemoves(List<Grid> removes) {

		Fetcher<Grid, String> fetcher = new Fetcher<Grid, String>() {

			@Override
			public String get(Grid t) {
				return t.getGridId() + "";
			}
		};

		return Util.Collection.linkWith(",", removes, fetcher);
	}
}
