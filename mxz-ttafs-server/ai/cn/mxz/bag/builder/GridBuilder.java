package cn.mxz.bag.builder;

import cn.mxz.bag.Grid;
import cn.mxz.protocols.user.god.BagP.GridPro;

/**
 * 背包格子协议
 *
 * @param grid
 *            格子
 * @return
 */
public class GridBuilder {

	public GridPro build(Grid grid) {

		GridPro.Builder b = GridPro.newBuilder();

		b.setId(grid.getGridId());

		b.setTypeId(grid.getTempletId());

		b.setPropType(grid.getPropType());

		b.setCount(grid.getCount());

//		Debuger.debug("GridBuilder.build()" + grid.getGridId() + "," + grid.getTempletId() + "," + grid.getCount());

		return b.build();

	}
}
