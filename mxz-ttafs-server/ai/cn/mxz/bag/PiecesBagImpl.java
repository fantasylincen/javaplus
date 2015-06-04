package cn.mxz.bag;

import java.util.List;

import message.S;
import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.mxz.PropTemplet;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.prop.PropTempletFactory;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.UserPiecesGridDao;
import db.dao.impl.UserPiecesGridDao2;
import db.domain.UserPiecesBag;
import db.domain.UserPiecesBagImpl;
import db.domain.UserPiecesGrid;
import db.domain.UserPiecesGridImpl;

public class PiecesBagImpl extends AbstractBag<Grid> {

	private UserPiecesBag	dto;

	public PiecesBagImpl(City city) {
		setCity(city);
	}

	@Override
	public int getCapacity() {
		ensureInit();

//		return dto.getCapacity();
		return 50000;
	}

	private synchronized void ensureInit() {

		if (dto == null) {

			dto = initPiecesBagData(city);

			final List<Grid> grids = initPiecesGrids(city);

			setGrids(grids);
		}

	}

	private static List<Grid> initPiecesGrids(City city) {

		UserPiecesGridDao DAO = DaoFactory.getUserPiecesGridDao();

		final List<UserPiecesGrid> grids = DAO.findByUname(city.getId());

		List<Grid> createPiecesGrids = createPiecesGrids(grids, city, DAO);

		return createPiecesGrids;
	}

	private static List<Grid> createPiecesGrids(List<UserPiecesGrid> find, City city, UserPiecesGridDao dAO) {

		final List<Grid> all = Lists.newArrayList();

		for (UserPiecesGrid grid : find) {

			if(grid.getCount() == 0) {
//				dAO.delete(grid);
				continue;
			}
			all.add(createPiecesGrid(city, grid, dAO));
		}

		return all;
	}

	private static Grid createPiecesGrid(City city, UserPiecesGrid grid, UserPiecesGridDao dAO) {

		final PiecesGridImpl g = new PiecesGridImpl();

		g.setDto(grid);

		return g;
	}

	private static UserPiecesBag initPiecesBagData(City city) {

		UserPiecesBag bag = DaoFactory.getUserPiecesBagDao().get(city.getId());

		if (bag == null) {

			bag = new UserPiecesBagImpl();

			bag.setCapacity(1000);

			bag.setUname(city.getId());

			DaoFactory.getUserPiecesBagDao().add(bag);
		}

		return bag;
	}

	@Override
	public void remove(int typeId, int count) {

		PiecesBagSnapsort p = new PiecesBagSnapsort();
		p.snapsort(this);
		rm(typeId, count);
		PropLogs.logComsume(typeId, count, city);
//		removeEmpty();
		p.snapsort(this);
	}
	
	private void rm(int typeId, int count) {
		ensureInit();

		checkRemoCount(typeId, count);
		PiecesGridImpl gridImpl = null;
		UserPiecesGrid g = null;

		// 数量-偏移量
		int offset = -count;
		for (int i = 0; i < all.size();) {
			gridImpl = (PiecesGridImpl) all.get(i);
			g = gridImpl.getDto();
			if (g.getTypeid() == typeId) {
				offset = g.getCount() - count;

				UserPiecesGridDao dao = DaoFactory.getUserPiecesGridDao();

				if (offset >= 0) {
					g.setCount(offset);

					// 全部移除
//					if (offset == 0) {
//						dao.delete(g.getGridId(), g.getUname());
//						all.remove(gridImpl);
//						break;
//					} else {
						dao.update(g);
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
	}

	private void checkRemoCount(int typeId, int count) {
		int c = getCount(typeId);
		if (c < count) {
			throw new OperationFaildException(S.S10172);
		}
	}

	@Override
	public void addProp(int id, int count) {
		PiecesBagSnapsort p = new PiecesBagSnapsort();
		p.snapsort(this);
		addProp2(id, count);
		PropLogs.logAdd(id, count, city);
//		removeEmpty();
		p.snapsort(this);
	}

	private void addProp2(int id, int count) {
		ensureInit();
		if (!canPut(id, count)) {

			throw new OperationFaildException(S.S10161);
		}

		UserPiecesGridDao2 DAO = DaoFactory.getUserPiecesGridDao();

		PropTemplet templet = PropTempletFactory.get(id);

		UserPiecesGrid g = new UserPiecesGridImpl();

		boolean isHas = false;

		for (int i = 0; i < all.size(); i++) {

			if (all.get(i).getTempletId() == id && !all.get(i).isFull()) {

				g = ((PiecesGridImpl) all.get(i)).getDto();

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
			if (count + g.getCount() > templet.getAddUp()) {

				addBuffer = g.getCount();

				g.setCount(templet.getAddUp());

				isBeyand = true;

			} else {

				g.setCount(count + g.getCount());
			}

			DAO.update(g);

		} else {

			g.setGridId(nextGridId());

			g.setTypeid(id);

			if (count + g.getCount() > templet.getAddUp()) {

				addBuffer = g.getCount();

				g.setCount(templet.getAddUp());

				isBeyand = true;

			} else {

				g.setCount(count);
			}

			Player player = getCity().getPlayer();

			g.setUname(player.getId());

			DAO.add(g);

			final PiecesGridImpl tGrid = new PiecesGridImpl();

			tGrid.setDto(g);

			getAll().add(tGrid);

		}

		if (isBeyand) {

			addProp2(id, count - templet.getAddUp() + addBuffer);
		}

	}

	// @Override
	// public void removeAllBySpotId(int spotId) {
	// ensureInit();
	// Iterator<Grid> it = all.iterator();
	// while (it.hasNext()) {
	// PiecesGridImpl grid = (PiecesGridImpl) it.next();
	// PropTemplet temp = PropTempletFactory.get(grid.getTempletId());
	// if (temp.getSpot() == spotId) {
	//
	// DaoFactory.getUserPiecesGridDAO().delete(grid.getGridId(),
	// grid.getCity().getId());
	//
	// it.remove();
	// }
	// }
	//
	// }

	@Override
	public List<Grid> getAll() {
		ensureInit();
		return super.getAll();
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
		List<UserPiecesGrid> l = Lists.newArrayList();
		for (Grid grid : ls) {
			PiecesGridImpl g = (PiecesGridImpl) grid;
			UserPiecesGrid dto = g.getDto();
			l.add(dto);
		}
		DaoFactory.getUserPiecesGridDao().delete(l);
	}


}
