package cn.mxz.city;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.ZbsrDao;
import mongo.gen.MongoGen.ZbsrDto;
import mongo.gen.MongoGen.ZbsrGoodsDto;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.EquipmentMerchantTemplet;
import cn.mxz.EquipmentMerchantTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;
import cn.mxz.zbsr.ZbsrGoods;

import com.google.common.collect.Lists;

import define.D;

public class Zbsr {
	private final class WeightFetcherImpl implements
			WeightFetcher<EquipmentMerchantTemplet> {
		@Override
		public Integer get(EquipmentMerchantTemplet t) {
			return t.getWeight();
		}

	}

	private City city;

	private ZbsrDto dto;

	public Zbsr(City user) {
		this.city = user;

		dto = getDto();
	}

	private ZbsrDto getDto() {
		if (dto != null) {
			return dto;
		}
		ZbsrDao dao = Daos.getZbsrDao();
		dto = dao.get(city.getId());

		if (dto != null) {
			return dto;
		}

		init();

		return dto;
	}

	private void init() {
		ZbsrDao dao = Daos.getZbsrDao();
		dto = dao.createDTO();
		dto.setUname(city.getId());
		dto.setEquipments(getRandomGoods());
		dao.save(dto);
	}

	private List<ZbsrGoodsDto> getRandomGoods() {
		List<EquipmentMerchantTemplet> tempAll = Lists
				.newArrayList(EquipmentMerchantTempletConfig.getAll());

		List<EquipmentMerchantTemplet> rd = Lists.newArrayList();

		int count = D.ZBSR_GOODS_COUNT;
		
		while (rd.size() < count) {
			List<EquipmentMerchantTemplet> all = EquipmentMerchantTempletConfig.getAll();
			if (all.isEmpty()) {
				continue;
			}
			EquipmentMerchantTemplet t = random(all);
			rd.add(t);
			tempAll.remove(t);
		}

		return build(rd);
	}private EquipmentMerchantTemplet random(
			List<EquipmentMerchantTemplet> all) {
		WeightFetcher<EquipmentMerchantTemplet> weightAble = new WeightFetcherImpl();
		return Util.Random.getRandomOneByWeight(all, weightAble);
	}
	private List<ZbsrGoodsDto> build(List<EquipmentMerchantTemplet> all) {
		List<ZbsrGoodsDto> ls = Lists.newArrayList();
		
		String[] split = D.ZUI_DUO_ZHE_KOU.split("-");
		int min = new Integer(split[0]);
		int max = new Integer(split[1]);
		int zheKouCount = Util.Random.get(min, max);

		for (EquipmentMerchantTemplet a : all) {
			boolean isTeJia = zheKouCount > 0;
			ls.add(build(a, isTeJia));
			zheKouCount--;
		}
		return ls;
	}
	private ZbsrGoodsDto build(EquipmentMerchantTemplet a, boolean isTeJia) {
		ZbsrGoodsDto dto = new ZbsrGoodsDto();
		dto.setId(a.getIdfen());
		dto.setEquipmentTempletId(a.getEquipmentTempletId());
		dto.setIsTeJia(isTeJia);
		return dto;
	}
	public City getCity() {
		return city;
	}

	public void refresh() {
		if (enouph(D.ID_SHUA_XIN_LING)) {
			reduce(D.ID_SHUA_XIN_LING);
		} else {
			city.getPlayer().reduceGold(D.ZBSR_REFRESH_GOLD_NEED);
		}
		updateGoods();
	}

	private void updateGoods() {
		dto.setEquipments(getRandomGoods());
		commit();
	}
	private void commit() {
		ZbsrDto dto = getDto();
		ZbsrDao dao = Daos.getZbsrDao();
		dao.save(dto);
	}
	private static final int SHUA_XIN_LING_NEED = 1;

	private void reduce(int idShuaXinLing) {
		city.getBagAuto().remove(idShuaXinLing, SHUA_XIN_LING_NEED);
	}

	private boolean enouph(int idShuaXinLing) {
		return city.getBagAuto().getCount(idShuaXinLing) >= SHUA_XIN_LING_NEED;
	}

	public void exchange(int id) {

		ZbsrGoods goods = getGoods(id);
		if(!goods.getCanReceive())
			return;
		reduceExchange(goods);
		goods.send(city);
		Daos.getZbsrDao().save(dto);
		city.getUserCounterAuto().add(CounterKey.ZBSR_EXCHANGE_TIMES, 1);
		city.getMessageSender().send(S.S10312);
	}
	
	private void reduceExchange(ZbsrGoods goods) {
		if (goods == null) {
			throw new OperationFaildException(S.S10254);
		}
		int goldNew = goods.getGoldNew();
		city.getPlayer().reduceGold(goldNew);
	}
	/**
	 * 获得指定ID的物品
	 * 
	 * @param id
	 * @return
	 */
	private ZbsrGoods getGoods(int id) {
		List<ZbsrGoods> all = getGoods();
		for (ZbsrGoods g : all) {
			if (g.getId() == id) {
				return g;
			}
		}
		return null;
	}
	public List<ZbsrGoods> getGoods() {
		ZbsrDto dto = getDto();

		List<ZbsrGoods> ls = Lists.newArrayList();
		List<ZbsrGoodsDto> all = dto.getEquipments();

		for (ZbsrGoodsDto d : all) {
			ls.add(new ZbsrGoods(d));
		}
		return ls;
	}

	public int getRemainSec() {
		String s = Util.Time.getNearest(D.ZBSR_REFRESH_TIME);
		return Util.Time.getRemainSec(s);
	}

}
