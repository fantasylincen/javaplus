package cn.mxz.market;

import java.util.ArrayList;
import java.util.List;

import message.S;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.exception.UnImplMethodException;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.handler.MarketService;
import cn.mxz.protocols.user.market.MarketP.MyTradePro;
import cn.mxz.protocols.user.market.MarketP.TradeFighterMessagePro;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.dao.impl.UserMarketDao;
import db.domain.UserMarket;
import db.domain.UserMarketImpl;

@Component("marketService")
@Scope("prototype")

public class MarketServiceImpl<E> extends AbstractService implements MarketService{

	@Override
	public MyTradePro getMyTrade() {

		MyTradePro.Builder b = MyTradePro.newBuilder();

		List<TradeItem> all = MarketImpl.getInstance().getAllTraceItem(getId());

		for (TradeItem t : all) {

			b.addFighters(new FighterItemBuilder(getCity()).build(t));
		}

		return b.build();
	}


	@Override
	public void getChooseFighterPro(String fighterId, String tradeNeed) {

//		putFighterToData(fighterId);

		Team team = getCity().getTeam();

		int fId = Integer.parseInt(fighterId);

		if(team.contains(fId)) {

			Hero hero = team.get(fId);

			if(hero instanceof PlayerHero) {

				throw new OperationFaildException(S.S10181);
			}

//			throw new IllegalArgumentException("队伍中的神将不可交易!");

//			getCity().getEquipmentTeam().moveFighterToDepot(fId);//移动到库存中
		}

		addData(fighterId,tradeNeed);

	}

	private void addData(String fighterId, String tradeNeed) {

		UserMarketDao DAO = DaoFactory.getUserMarketDao();

		String[] split = tradeNeed.split("\\|");

		List<String> tradeList = Lists.newArrayList();

		for(int i = 0; i < split.length ; i++){

			tradeList.add(split[i]);
		}

		for (String s : tradeList) {

			String[] ullbs = s.split("\\,");

			int parseInt = Integer.parseInt(ullbs[0]);

			int parseInt2 = Integer.parseInt(ullbs[1]);

			UserMarket u = new UserMarketImpl();

			u.setUname(getPlayer().getId());

			int fId = Integer.parseInt(fighterId);

			u.setFighterId(fId);

			u.setTradNub(parseInt2);

			u.setTradType(parseInt);

			u.setTypeId(getCity().getTeam().get(fId).getTypeId());

			u.setTime((int) (System.currentTimeMillis() / 1000));

//			u.setId(DAO.nextId());

			DAO.add(u);
		}
	}

//	public static void main(String[] args) {
//		int i = (int) (System.currentTimeMillis() / 1000);
//
//	Debuger.debug("MarketServiceImpl.main()" + i);
//	}

	@Override
	public void AcceptTrade(int fighterId,String uName) {
//
//		removeMarketMes(fighterId, uName);
//
//		Team myTeam = getCity().getTeam();
//
//		Team otherTeam = getWorld().get(uName).getTeam();
//
//		otherTeam.move(fighterId, myTeam);
		throw new UnImplMethodException();

	}

//	private void removeMarketMes(int fighterId, String uName) {
//
//		DAO<Integer, UserMarket> DAO = DaoFactory.getUserMarketDAO();
//
//		List<UserMarket> findBy = DAO.findBy("fighter_id", String.valueOf(fighterId));
//
//		for (UserMarket u : findBy) {
//
//			DAO.delete(u.getTradId());
//		}
//
//	}


	@Override
	public void cancelTrade(int fighterId, String uName) {

//		DAO<Integer, UserMarket> DAO = DaoFactory.getUserMarketDAO();
//
//		List<UserMarket> findBy = DAO.findBy("fighter_id", String.valueOf(fighterId));
//
//		for (UserMarket u : findBy) {
//
//			DAO.delete(u.getTradId());
//		}
		throw new UnImplMethodException();

	}

	@Override
	public TradeFighterMessagePro queryTradeMes(int typeId, int page) {

		List<TradeItem> all =  MarketImpl.getInstance().getAllFighterTradeMes(typeId, getCity());

		return new TradeFighterMessageBuilder(getCity()).build(all, page);

	}

	@Override
	public TradeFighterMessagePro queryToStep(int step) {

		List<TradeItem> list = new ArrayList<TradeItem>();

		List<UserMarket> allTradeMes = allTradeMes();

		for (UserMarket u : allTradeMes) {



			Team team = getCity().getTeam();
			Hero hero = team.get(u.getFighterId());

			int quality = hero.getQuality();
			GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
			int step2 = temp.getStep();

			if(step == step2){

				list = MarketImpl.getInstance().getAllFighterTradeMes(u.getTypeId(), getCity());
			}
		}

		return new AllTradeMesBuild(getCity()).build(list);
	}

	private List<UserMarket> allTradeMes() {

		UserMarketDao DAO = DaoFactory.getUserMarketDao();

		List<UserMarket> all = DAO.getAll();

		return all;
	}

}
