package cn.mxz.bag;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;
import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.EquipmentTemplet;
import cn.mxz.PropTemplet;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.events.GetPropEvent;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.UserGridDao2;
import db.domain.UserBag;
import db.domain.UserGrid;
import db.domain.UserGridImpl;
import define.D;

/**
 * 玩家背包
 * 
 * @author 彭超
 * @since 2013年8月13日 14:30:35
 * 
 */
class BagImpl extends AbstractBag<Grid> {

	private UserBag dto;

	@Override
	public int getCapacity() {
//		return getDto().getCapacity();
		return 50000;
	}

	@Override
	public void remove(int typeId, int count) {

		UserGridDao2 dao = DaoFactory.getUserGridDao();
		checkRemoCount(typeId, count);
		GridImpl gridImpl = null;
		UserGrid g = null;

		BagSnapsort bs = new BagSnapsort();
		bs.snapsort(this);

		sortByCount();

		// 数量-偏移量
		int offset = -count;

		for (int i = 0; i < all.size();) {
			gridImpl = (GridImpl) all.get(i);
			g = gridImpl.getDto();
			if (g.getTypeid() == typeId) {
				offset = g.getCount() - count;
				if (offset >= 0) {
					g.setCount(offset);

					Debuger.debug("BagImpl.remove() " + g.getTypeid() + "," + g.getCount()) ;

					// 全部移除
//					if (offset == 0) {
//						dao.delete(g.getGridId(), g.getUname());
//						all.remove(gridImpl);
//						break;
//					} else {
						dao.save(g);
						break;
//					}
				} else {
//					dao.delete(g.getGridId(), g.getUname());
//					all.remove(gridImpl);
					count = -offset;
				}
			} else {
				i++;
			}
		}

		PropLogs.logComsume(typeId, count, city);

//		removeEmpty();

		bs.snapsort(this);
	}

	private void sortByCount() {
		Comparator<Grid> c = new Comparator<Grid>() {

			@Override
			public int compare(Grid o1, Grid o2) {
				return o1.getCount() - o2.getCount();
			}
		};
		Collections.sort(all, c);
	}

	/**
	 * 检查移除道具个数
	 * 
	 * @param typeId
	 *            移除物品ID
	 * @param count
	 *            移除个数
	 */
	private void checkRemoCount(int typeId, int count) {

		World w = WorldFactory.getWorld();

		City city = w.get(dto.getUname());

		city.getChecker().checkProp(typeId, count);
	}

	@Override
	public void addProp(int id, int count) {

		BagSnapsort s = new BagSnapsort();
		s.snapsort(this);

		addProp2(id, count);

		PropLogs.logAdd(id, count, city);

		Events.getInstance().dispatch(
				new GetPropEvent(getCity(), this, id, count));
//		removeEmpty();
		s.snapsort(this);
	}

	private void addProp2(int id, int count) {

		if (D.MONEY_BAG_ID == id) {

			throw new OperationFaildException(S.S10160);
		}

		if (count <= 0) {

			return;
		}

		final PropTemplet templet = PropTempletFactory.get(id);

		if (templet == null || templet instanceof EquipmentTemplet) {

			throw new SureIllegalOperationException("-无效ID:" + id);
		}

		UserGrid userGrid = new UserGridImpl();

		boolean isHas = false;

		for (int i = 0; i < all.size(); i++) {

			if (all.get(i).getTempletId() == id && !all.get(i).isFull()) {

				userGrid = ((GridImpl) all.get(i)).getDto();

				isHas = true;

				break;
			}
		}

		// 是否超出叠加上限
		boolean isBeyand = false;

		// 递归添加数量缓存
		int addBuffer = 0;

		if (isHas) {

			// 超过叠加上限
			if (count + userGrid.getCount() > templet.getAddUp()) {

				addBuffer = userGrid.getCount();

				userGrid.setCount(templet.getAddUp());

				isBeyand = true;

			} else {

				userGrid.setCount(count + userGrid.getCount());
			}

			DaoFactory.getUserGridDao().update(userGrid);

		} else {

			userGrid.setTypeid(id);

			userGrid.setGridId(nextGridId());

			if (count + userGrid.getCount() > templet.getAddUp()) {

				addBuffer = userGrid.getCount();

				userGrid.setCount(templet.getAddUp());

				isBeyand = true;

			} else {

				userGrid.setCount(count);
			}

			userGrid.setUname(getCity().getPlayer().getId());

			DaoFactory.getUserGridDao().add(userGrid);

			final GridImpl grid = new GridImpl();

			grid.setCity(city);

			grid.setDto(userGrid);

			getAll().add(grid);

		}

		if (isBeyand) {

			addProp2(id, count - templet.getAddUp() + addBuffer);
		}

		dispatchEvent(new AddPropEvent(id, count, dto.getUname()));
	}

	public UserBag getDto() {
		return dto;
	}

	public void setDto(UserBag dto) {
		this.dto = dto;
	}

	@Override
	public void addProp(String props) {
		BagSnapsort sb = new BagSnapsort();
		sb.snapsort(this);

		StringResolver s = Util.Str.resolve(props);
		List<StringResolver> ss = s.split("\\|");
		for (StringResolver sr : ss) {
			List<StringResolver> lis = sr.split(",");
			addProp2(lis.get(0).getInt(), lis.get(1).getInt());
		}

		sb.snapsort(this);
	}

	@Override
	protected void deleteInDB(List<Grid> ls) {
		List<UserGrid> l = Lists.newArrayList();
		for (Grid grid : ls) {
			GridImpl g = (GridImpl) grid;
			UserGrid dto = g.getDto();
			l.add(dto);
		}
		DaoFactory.getUserGridDao().delete(l);
	}
}
