package cn.mxz.bag;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.javaplus.util.Util;
import cn.mxz.PropTemplet;
import cn.mxz.city.City;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.prop.PropTempletFactory;

import com.google.common.collect.Lists;

/**
 * 背包
 *
 * @author 林岑
 *
 */
public abstract class AbstractBag<T extends Grid> extends EventDispatcher2Impl implements Bag<T> {

	protected List<T>	all;

	protected City		city;

	@Override
	public final Iterator<T> iterator() {

		return getAll().iterator();
	}
	private void removeEmpty() {
		Iterator<T> it = all.iterator();
		List<T> ls = Lists.newArrayList();
		while (it.hasNext()) {
			T grid = it.next();
			int c = grid.getCount();
			if(c == 0) {
				it.remove();
			}
		}
		deleteInDB(ls);
	}
	
	protected abstract void deleteInDB(List<T> ls);
	
	@Override
	public final int getCount(final int typeId) {

		int haveNow = 0;

		for (final Grid now : getAll()) {

			if (now.getTempletId() == typeId) {

				haveNow += now.getCount();
			}
		}

		return haveNow;
	}

	@Override
	public List<T> getAll() {

		Iterator<T> it = all.iterator();
		while (it.hasNext()) {
			T t = (T) it.next();
			if(t.getCount() == 0) {
				it.remove();
			}
		}
		return all;
	}

	@Override
	public final T get(final int position) {

		return getAll().get(position);
	}

	private AtomicInteger	maxId	= new AtomicInteger(-1);

	protected final int nextGridId() {

		if (maxId.get() != -1) {

			return maxId.addAndGet(1);
		}

		for (final Grid g : all) {

			int gridId = g.getGridId();

			if (gridId > maxId.get()) {

				maxId.set(gridId);
			}
		}

		return maxId.addAndGet(1);
	}

	public final void setGrids(final List<T> grids) {
		this.all = grids;
		removeEmpty();
	}

	@Override
	public final City getCity() {
		return city;
	}

	public final void setCity(final City city) {
		this.city = city;
	}

	@Override
	public final boolean isFull() {

//		return getAll().size() >= getCapacity();
		return false;
	}

	@Override
	public String getMD5() {
		List<T> all2 = getAll();
		StringBuilder sb = new StringBuilder(getCapacity() + "");

		for (T t : all2) {
			sb.append(t.getGridId() + ":");
			sb.append(t.getTempletId() + ":");
			sb.append(t.getCount() + ":");
		}
		return Util.Secure.md5(sb + "");
	}

	/**
	 * 是否可以放下count个指定id的物品
	 *
	 * @param id
	 * @param count
	 * @return
	 */
	protected boolean canPut(int id, int count) {

		PropTemplet temp = PropTempletFactory.get(id);

		int need = getGridNeed(temp, count);

		return getCapacity() - getAll().size() >= need;
	}

	/**
	 * 获得 放入 count个类型为TypeId 的商品 所需要的格子数量
	 *
	 * @param typeId
	 * @param count
	 * @return
	 */
	private int getGridNeed(PropTemplet temp, Integer count) {

		int addUp = temp.getAddUp();

		boolean isJust = count % addUp == 0;// 是否刚好放下

		return isJust ? count / addUp : count / addUp + 1;
	}
}
