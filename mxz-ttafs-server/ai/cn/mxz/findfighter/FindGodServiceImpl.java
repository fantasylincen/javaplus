//package cn.mxz.findfighter;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import cn.mxz.GodTypeTempletConfig;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.base.service.AbstractService;
//import cn.mxz.event.EventDispatcher;
//import cn.mxz.event.EventDispatcherImpl;
//import cn.mxz.findfighter.event.FindFighterEvent;
//import cn.mxz.findfighter.event.FindFighterListener;
//import cn.mxz.protocols.user.god.FighterP.FightersPro;
//import cn.mxz.protocols.user.god.FindGodP.FindGodItemsPro;
//import cn.mxz.team.builder.FightersBuilder;
//import cn.mxz.user.team.god.Hero;
//import cn.mxz.util.counter.CounterKey;
//
///**
// * 寻仙
// *
// * @author 林岑
// */
//@Component("findGodService")
//@Scope("prototype")
//public class FindGodServiceImpl extends AbstractService implements FindGodService {
//
//	private EventDispatcher dispatcher = new EventDispatcherImpl();
//
//	public FindGodServiceImpl() {
//
//		dispatcher.addListener(new FindFighterListener());
//	}
//
//	@Override
////	public FightersPro findAnyTimes(int times, int type) {
//
//		FighterFinder finder = FindFighterFactory.getFinder(getCity(), type);
//
//		times = limit(finder, times);
//
//		finder.check(times);		//检查
//
//		finder.reduce(times);	//扣除
//
//		List<Hero> ls = finder.find(times);
//
//		dispatcher.dispatchEvent(new FindFighterEvent(getCity(), ls));
//
//		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//
//		ps.send(getPlayer(), GodTypeTempletConfig.get(type));
//
//		getCity().getUserCounterHistory().add(CounterKey.FIND_FIGHTER_TIMES, 1);
//		getCity().getUserCounter().add(CounterKey.FIND_FIGHTER_TIMES, 1);
//
//		return new FightersBuilder().build(getCity(), getCity().getFormation().getSelected(), ls);
//	}
//
//	private int limit(FighterFinder finder, int times) {
//
//		List<Integer> timesSelectedAble = finder.getTimesSelectedAble();
//
//		if(timesSelectedAble.contains(times)) {
//
//			return times;
//		}
//
//		Collections.sort(timesSelectedAble);
//
//		return timesSelectedAble.get(0);
//	}
//
//	@Override
//	public FindGodItemsPro getData() {
//
//		List<FighterFinder> allFinders = FindFighterFactory.getAllFinders(getCity());
//
//		return new FindGodItemsBuilder().build(allFinders, getCity());
//	}
//}
