package cn.mxz.bag;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.listeners.ShowMessageOnAddListener;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.UserGridDao;
import db.domain.UserBag;
import db.domain.UserBagImpl;
import db.domain.UserGrid;
import define.D;

/**
 * 背包工厂
 *
 * @author 林岑
 * @since 2013年6月7日 11:48:43
 *
 */
public class BagFactory {

	/**
	 * 创建玩家背包, 如果数据库里面已经有玩家的背包数据了, 那么就直接读取出来, 如果没有的话, 那么就初始化一个背包
	 *
	 * @param user
	 * @return
	 */
	public static Bag<Grid> createBag(City city) {

		final UserGridDao userGridDAO = DaoFactory.getUserGridDao();

		final UserBag data = initBagData(city);

		final List<Grid> grids = initGrids(city, userGridDAO);

		final BagImpl bag = new BagImpl();

		bag.addListener(new ShowMessageOnAddListener());

		bag.setCity(city);

		bag.setDto(data);

		bag.setGrids(grids);

		return bag;
	}

//	/**
//	 * 初始化临时背包 如果数据库中有临时背包记录, 就从数据库里面读取, 如果没有, 就初始化到数据库
//	 *
//	 * @param cityImpl
//	 * @return
//	 */
//	public static Bag<Grid> createTempBag(City city) {
//
//		final DAO2<Integer, String, UserTemporaryGrid> userTemporaryGridDAO = DaoFactory.getUserTemporaryGridDAO();
//
//		final List<UserTemporaryGrid> findBy = userTemporaryGridDAO.findByUname(city.getId());
//
//		TemporaryBag tBag = null;
//
//		final List<Grid> grids = initTemproraryGrid(city, findBy);
//
//		tBag = new TemporaryBag();
//
//		tBag.addListener(new ShowMessageOnAddListener());
//
//		tBag.setCity(city);
//
//		tBag.setUserTemporaryGridDAO(userTemporaryGridDAO);
//
//		tBag.setGrids(grids);
//
//		return tBag;
//	}

	/**
	 * 碎片背包
	 *
	 * @param cityImpl
	 * @return
	 */
	public static Bag<Grid> createPiecesBag(City city) {

		final PiecesBagImpl bag = new PiecesBagImpl(city);
		return bag;
	}

//	private static List<Grid> initTemproraryGrid(City city, List<UserTemporaryGrid> findBy) {
//
//		final List<UserTemporaryGrid> userTemporaryGrids = Lists.newArrayList();
//
//		UserTemporaryGrid g = null;
//
//		for (UserTemporaryGrid uGrid : findBy) {
//
//			g = new UserTemporaryGridImpl();
//
//			g.setCount(uGrid.getCount());
//
//			g.setGridId(uGrid.getGridId());
//
//			g.setTypeid(uGrid.getTypeid());
//
//			g.setUname(uGrid.getUname());
//
//			userTemporaryGrids.add(g);
//		}
//
//		return createTemporaryGrids(userTemporaryGrids, city);
//	}

	/**
	 * 创建一个背包格子, 创建过程中, 会初始化背包中的装备, 材料, 宝石
	 *
	 * @param user
	 * @param grid
	 * @return
	 */
	private static Grid createGrid(City city, UserGrid grid, UserGridDao userGridDAO) {

		final GridImpl g = new GridImpl();

		g.setCity(city);

		g.setDto(grid);

		return g;
	}

//	private static Grid createTemporaryGrid(City city, UserTemporaryGrid userTemporaryGrid) {
//
//		final TemporaryGrid tg = new TemporaryGrid();
//
//		tg.setCity(city);
//
//		tg.setUserTemporaryGrid(userTemporaryGrid);
//
//		return tg;
//	}

	/**
	 * 初始化玩家格子数据
	 *
	 * @param user
	 * @return
	 */
	private static List<Grid> initGrids(City city, UserGridDao userGridDAO) {

		final UserGridDao DAO = DaoFactory.getUserGridDao();

		final List<UserGrid> grids = DAO.findByUname(city.getId());

		if (grids.isEmpty()) {

			DAO.addAll(grids);
		}

		return createGrids(grids, city, userGridDAO);
	}



	/**
	 * 创建用户格子列表, 同时初始化格子里面的物品, 像装备这些
	 *
	 * @param find
	 * @param user
	 * @return
	 */
	private static List<Grid> createGrids(List<UserGrid> find, City city, UserGridDao userGridDAO) {

		final List<Grid> all = Lists.newArrayList();

		for (UserGrid grid : find) {
			if(grid.getCount() == 0) {
//				userGridDAO.delete(grid);
				continue;
			}
			all.add(createGrid(city, grid, userGridDAO));
		}

		return all;
	}

//	private static List<Grid> createTemporaryGrids(List<UserTemporaryGrid> userTemporaryGrids, City city) {
//
//		final List<Grid> all = Lists.newArrayList();
//
//		for (UserTemporaryGrid uGrid : userTemporaryGrids) {
//
//			all.add(createTemporaryGrid(city, uGrid));
//		}
//
//		return all;
//	}


	private static UserBag initBagData(City city) {

		UserBag bag = DaoFactory.getUserBagDao().get(city.getId());

		if (bag == null) {

			bag = new UserBagImpl();

			bag.setCapacity(D.BAG_INIT_SIZE);

			bag.setUname(city.getId());

			DaoFactory.getUserBagDao().add(bag);
		}

		return bag;
	}

	// /*
	// * 对格子按照位置排序
	// */
	// private static void sort(List<UserGrid> ls) {
	//
	// Collections.sort(ls, new Comparator<UserGrid>() {
	//
	// @Override
	// public int compare(UserGrid o1, UserGrid o2) {
	// return o1.getGridId() - o2.getGridId();
	// }
	// });
	// }
}
