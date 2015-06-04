package cn.mxz.bag;

import java.util.List;

import cn.mxz.bag.builder.BagBuilder;
import cn.mxz.messagesender.MessageFactory;

import com.google.common.collect.Lists;

public class BagSnapsort {

	protected boolean		isFirst	= true;
	protected List<Grid>	allGrid	= Lists.newArrayList();
	protected List<Grid>	removes	= Lists.newArrayList();
	protected List<Grid>	changes	= Lists.newArrayList();
	protected Bag<Grid>		bag;

	private static class MyGrid implements Grid {

		private int	count;
		private int	gridId;
		private int	propType;
		private int	templetId;

		public void setCount(int count) {
			this.count = count;
		}

		public void setGridId(int gridId) {
			this.gridId = gridId;
		}

		public void setPropType(int propType) {
			this.propType = propType;
		}

		public void setTempletId(int templetId) {
			this.templetId = templetId;
		}

		@Override
		public int getCount() {
			return count;
		}

		@Override
		public int getGridId() {
			return gridId;
		}

		@Override
		public int getTempletId() {
			return templetId;
		}

		@Override
		public int getPropType() {
			return propType;
		}

		@Override
		public int getFree() {
			return 0;
		}

		@Override
		public boolean isFull() {
			return false;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + count;
			result = prime * result + gridId;
			result = prime * result + propType;
			result = prime * result + templetId;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MyGrid other = (MyGrid) obj;
			if (count != other.count)
				return false;
			if (gridId != other.gridId)
				return false;
			if (propType != other.propType)
				return false;
			if (templetId != other.templetId)
				return false;
			return true;
		}

	}

	/**
	 * 对背包进行快照, 如果是第二次快照,且数据发生了变化, 那么会主动给客户端发送变化了的格子
	 *
	 * @param bag
	 */
	public void snapsort(Bag<Grid> bag) {
		this.bag = bag;
		if (isFirst) {
			saveAll(bag);
		} else {
			snapsortChanges(bag);
			update();
		}

		isFirst = false;

	}

	/**
	 * 比较出变化的格子
	 *
	 * @param bag
	 */
	private void snapsortChanges(Bag<Grid> bag) {

		List<Grid> all = bag.getAll();

		clearAllRecord();// 清空所有之前的记录

		saveAllChange(all);

		saveAllRemoved(all);

		saveAll(bag);
	}

	private void saveAllRemoved(List<Grid> all) {
		for (Grid grid : allGrid) {
			if (hasRemoved(grid, all)) {
				addToRemoved(grid);

				// Debuger.debug("BagSnapsort.snapsortChanges()" +
				// grid.getTempletId() + "被移除了!" + grid.getGridId());
			}
		}
	}

	private void saveAllChange(List<Grid> all) {
		for (Grid grid : all) {
			if (isNew(grid) || hasChange(grid)) { // 新增或者变化了
				addToChanges(grid);
				// Debuger.debug("BagSnapsort.snapsortChanges()" +
				// grid.getTempletId() + "发生变化了!" + grid.getGridId());
			}
		}
	}

	/**
	 * 是否被移除了
	 *
	 * @param grid
	 * @param all
	 * @return
	 */
	private boolean hasRemoved(Grid grid, List<Grid> all) {
		for (Grid g : all) {
			if (g.getGridId() == grid.getGridId()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否与先前的格子 发生了变化
	 *
	 * @param grid
	 * @return
	 */
	private boolean hasChange(Grid grid) {
		Grid g = getOldGrid(grid);
		if (g == null) {
			return true;
		}
		return g.getCount() != grid.getCount();
	}

	/**
	 * 是否是新增加的格子
	 *
	 * @param grid
	 * @return
	 */
	private boolean isNew(Grid grid) {
		return getOldGrid(grid) == null;
	}

	/**
	 * 先前与之ID相同的格子
	 *
	 * @param grid
	 * @return
	 */
	private Grid getOldGrid(Grid grid) {
		for (Grid g : allGrid) {
			if (g.getGridId() == grid.getGridId()) {
				return g;
			}
		}
		return null;
	}

	/**
	 * 清空所有记录
	 */
	private void clearAllRecord() {
		this.removes = Lists.newArrayList();
		this.changes = Lists.newArrayList();
	}

	private void addToRemoved(Grid grid) {
		this.removes.add(copy(grid));
	}

	private void addToChanges(Grid grid) {
		this.changes.add(copy(grid));
	}

	/**
	 * 保存背包所有格子记录
	 *
	 * @param bag
	 */
	private void saveAll(Bag<Grid> bag) {
		allGrid = Lists.newArrayList();
		List<Grid> all = bag.getAll();
		for (Grid grid : all) {
			if (grid.getCount() != 0) {
				allGrid.add(copy(grid));
			}
		}
	}

	private Grid copy(Grid grid) {
		MyGrid m = new MyGrid();
		m.setCount(grid.getCount());
		m.setGridId(grid.getGridId());
		m.setPropType(grid.getPropType());
		m.setTempletId(grid.getTempletId());
		return m;
	}

	/**
	 * 获得与先前发生了变化的格子列表
	 *
	 * @return
	 */
	public List<Grid> getChanges() {
		return changes;
	}

	/**
	 * 获得被移除了的格子列表
	 *
	 * @return
	 */
	public List<Grid> getRemoves() {
		return removes;
	}

	protected void update() {
		if (!changes.isEmpty()) {
//			 Debuger.debug("BagSnapsort-- update()" + changes);
			MessageFactory.getBag().bagUpdate(bag.getCity().getSocket(), new BagBuilder().buildChanges(changes));
		}

		if (!removes.isEmpty()) {
//			 Debuger.debug("BagSnapsort-- remove()" + removes);
			MessageFactory.getBag().bagRemoved(bag.getCity().getSocket(), new BagBuilder().buildRemoves(removes));
		}
	}
}
