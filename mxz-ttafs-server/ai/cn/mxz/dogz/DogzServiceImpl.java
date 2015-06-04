package cn.mxz.dogz;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.exception.UnImplMethodException;
import cn.mxz.activity.PropBuilder;
import cn.mxz.bag.BagAuto;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.DogzService;
import cn.mxz.protocols.user.DogzP.DogzFighting;
import cn.mxz.protocols.user.DogzP.DogzListPro;
import cn.mxz.protocols.user.DogzP.DogzPro;
import cn.mxz.protocols.user.PropP.PropPro;
import cn.mxz.shenmo.PropExchanger;

@Component("dogzService")
@Scope("prototype")
public class DogzServiceImpl<E> extends AbstractService implements DogzService {

	// private static final int JING_LIANG = 90001;
	// private static final int CHUAN_SHUO = 99850;

	@Override
	public DogzListPro getDogzAll() {

		DogzManager manager = getCity().getDogzManager();

		Collection<Dogz> ds = manager.getDogzAll().values();
		
		return new DogzListBuilder().build(ds);

	}

	@Override
	public void protectedDogz(int dogzTypeId) {
		throw new UnImplMethodException();
	}

	// private boolean ishasUser(int dogzTypeId) {
	//
	// UserDogzDao DAO = DaoFactory.getUserDogzDAO();
	//
	// UserDogz u = DAO.get(dogzTypeId, getPlayer().getId());
	//
	// return u != null;
	// }

	@Override
	public DogzPro feed(int dogzTypeId, String gridIds) {
		return null;
	}

	@Override
	public DogzPro setFighting(int dogzTypeId) {

		takeBackFighting();

		DogzManager m = getCity().getDogzManager();

		Dogz dogz = m.getDogz(dogzTypeId);

		if (dogz == null) {
			throw new SureIllegalOperationException("神兽不存在:" + dogzTypeId);
		}

		dogz.setFighting(true);

		return new DogzBuilder().build(dogz);
	}

	/**
	 * 取消当前出战神兽
	 */
	private void takeBackFighting() {

		Dogz fighting = getCity().getDogzManager().getFighting();

		if (fighting != null) {

			getCity().getDogzManager().getDogz(fighting.getTypeId())
					.setFighting(false);
		}
	}

	@Override
	public void takeBack(int dogzTypeId) {

		getCity().getDogzManager().getDogz(dogzTypeId).setFighting(false);

	}

	@Override
	public DogzPro dunWu(int type, Boolean isProtected, int dogzTypeId) {

		// final int needZhuJiShi = 1;
		//
		// Bag<Grid> bag = getCity().getBag();
		//
		// if (isProtected) {
		//
		// getChecker().checkProp(D.ZHU_JI_SHI_ID, needZhuJiShi);
		// }
		//
		// if (type == 0) {
		//
		// getPlayer().reduce(PlayerProperty.GOLD, D.GOLD_NEED_VALUE);
		//
		// } else {
		//
		// bag.remove(D.KONG_LING_SHI, D.DUN_WU_HUAN_HUN_DAN_NEED);
		// }
		//
		// Integer maxKey = DogzQualityTempletConfig.getMaxKey();
		//
		// Random random = new Random();
		//
		// int nextInt =
		// random.nextInt(DogzQualityTempletConfig.get(maxKey).getTraitMax() +
		// 1);
		//
		// Dogz dogz = getCity().getDogzManager().getDogz(dogzTypeId);
		//
		// if (isProtected && nextInt < dogz.getTrait()) {
		//
		// nextInt = dogz.getTrait();
		// }
		//
		// dogz.setStepValue(nextInt);
		//
		// List<Integer> listByTraitMax =
		// DogzQualityTempletConfig.getListByTraitMax();
		//
		// List<DogzQualityTemplet> findByTraitMax = null;
		//
		// for (Integer i : listByTraitMax) {
		//
		// if (i < dogz.getTrait()) {
		//
		// findByTraitMax = DogzQualityTempletConfig.findByTraitMax(i);
		//
		// dogz.setStep(findByTraitMax.get(0).getQuality());
		// }
		// }
		//
		// dogz.setDunwuCount(dogz.getDunwuCount() + 1);
		//
		// if ((dogz.getDunwuCount()) == 1) {
		//
		// dogz.setStepValue(JING_LIANG);
		//
		// } else if ((dogz.getDunwuCount()) % 200 == 0) {
		//
		// if (dogz.getTrait() < CHUAN_SHUO) {
		//
		// dogz.setStepValue(CHUAN_SHUO);
		// }
		//
		// Debuger.debug("DogzServiceImpl.dunWu() 必出传说");
		// }
		//
		// dogz.setIsProtected(false);
		//
		// // 顿悟200次、神兽一定变为传说
		// if (dogz.getDunWuTimes() == 200) {
		//
		// dogz.setStep(5);
		// }
		//
		// if (isProtected) {
		//
		// bag.remove(D.ZHU_JI_SHI_ID, needZhuJiShi);
		// }
		//
		// getCity().getUserCounterHistory().add(CounterKey.DOGZ_DUNWU_TIMES,
		// 1);
		// getCity().getUserCounter().add(CounterKey.DOGZ_DUNWU_TIMES, 1);
		//
		// return new DogzBuilder().build(dogz, getCity().getBag());

		return null;
	}

	@Override
	public DogzFighting getFighting() {

		Dogz fighting = getCity().getDogzManager().getFighting();

		return new DogzFightingBuilder().build(fighting);
	}

	@Override
	public DogzPro generate(int dogzTypeId) {
		// DogzManager m = getCity().getDogzManager();
		// Dogz dogz = m.generate(dogzTypeId);
		// return new DogzBuilder().build(dogz, getCity().getBag());
		return null;
	}

	@Override
	public PropPro exchange(int stuffId) {
		new PropExchanger(getCity()).exchange(stuffId);
		BagAuto bag = getCity().getBagAuto();
		int count = bag.getCount(stuffId);
		return new PropBuilder().build(stuffId, count);
	}

	@Override
	public DogzPro zhuHun(int dogzId) {
		DogzManager m = getCity().getDogzManager();
		Dogz dogz = m.getDogz(dogzId);
		m.zhuHun(dogzId);
		return new DogzBuilder().build(dogz);
	}

}
