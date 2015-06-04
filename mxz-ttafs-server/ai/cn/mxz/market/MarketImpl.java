package cn.mxz.market;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import cn.mxz.city.City;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserMarketDao;
import db.domain.UserMarket;

class MarketImpl implements Market {

	private static Market	instance;

	private MarketImpl() {
	}

	static final Market getInstance() {

		if (instance == null) {

			instance = new MarketImpl();
		}

		return instance;
	}


	@Override
	public boolean isTrading(String id, int fighterId) {

		UserMarketDao DAO = DaoFactory.getUserMarketDao();

		List<UserMarket> findBy = DAO.findByUname(id);

		for (UserMarket u : findBy) {

			if(fighterId == u.getFighterId()){

				return true;
			}
		}

		return false;
	}

	@Override
	public List<TradeItem> getAllTraceItem(String id) {

		UserMarketDao DAO = DaoFactory.getUserMarketDao();

		List<UserMarket> findBy = DAO.findByUname(id);

		List<TradeItem> list = new ArrayList<TradeItem>();

		Map<Integer, List<UserMarket>> map = fenLei(findBy);

		for (Integer fighterId : map.keySet()) {

			List<UserMarket> list2 = map.get(fighterId);

			list.add(build(fighterId, list2, id));
		}

		return list;
	}

	private Map<Integer, List<UserMarket>> fenLei(List<UserMarket> findBy) {

		Map<Integer, List<UserMarket>> map = new HashMap<Integer, List<UserMarket>>();

		for (UserMarket m : findBy) {

			removePastDue(m);

			List<UserMarket> list = map.get(m.getFighterId());

			if(list == null) {

				list = new ArrayList<UserMarket>();

				map.put(m.getFighterId(), list);
			}

			list.add(m);
		}

		return map;
	}

	private void removePastDue(UserMarket m) {

		UserMarketDao DAO = DaoFactory.getUserMarketDao();

		if((int)System.currentTimeMillis()/1000 - m.getTime() > 48 *3600){

			DAO.delete(m.getTradId());
		}
	}

	private TradeItem build(Integer fighterId, List<UserMarket> list2, String uname) {

		return new TradeItemImpl(uname, fighterId, list2);
	}

	@Override
	public List<TradeItem> getAllFighterTradeMes(int typeId , City city) {

		List<TradeItem> list = new ArrayList<TradeItem>();

		Collection<String> allUser = getAllUserHave(typeId , city);

		for (String id : allUser) {

			list.addAll(getAllTraceItem(id));
		}

		return list;
	}

	/**
	 * 拿到所有正在交易typeId 的所有玩家
	 * @param typeId
	 * @return
	 */
	private Collection<String> getAllUserHave(int typeId , City city) {

		List<UserMarket> findBy = DaoFactory.getUserMarketDao().findByTypeId(typeId);

		Collection<String> allUser = new HashSet<String>();

		for (UserMarket u : findBy) {

			if(!isSlef(u,city)) {

				allUser.add(u.getUname());
			}
		}

		return allUser;
	}

	private boolean isSlef(UserMarket u, City city) {

		if(city.getPlayer().getId().equals(u.getUname())){

			return true;

		}else{

			return false;
		}

	}
}
