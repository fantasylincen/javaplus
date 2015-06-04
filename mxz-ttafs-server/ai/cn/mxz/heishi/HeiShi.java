package cn.mxz.heishi;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.HeiShiGoodsDto;
import mongo.gen.MongoGen.HeiShiStoreDao;
import mongo.gen.MongoGen.HeiShiStoreDto;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.AgainstGoodsLibraryTemplet;
import cn.mxz.AgainstGoodsLibraryTempletConfig;
import cn.mxz.AgainstTypeLibraryTemplet;
import cn.mxz.AgainstTypeLibraryTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;

import com.google.common.collect.Lists;

import define.D;

public class HeiShi {

	private final class WeightFetcher2 implements
			WeightFetcher<AgainstTypeLibraryTemplet> {
		@Override
		public Integer get(AgainstTypeLibraryTemplet t) {
			if (isAgainst2()) {
				return t.getWeight3();
			}
			if (isAgainst1()) {
				return t.getWeight2();
			}
			return t.getWeight();
		}

	}

	private final class WeightFetcher1 implements
			WeightFetcher<AgainstGoodsLibraryTemplet> {
		@Override
		public Integer get(AgainstGoodsLibraryTemplet t) {
			return t.getWeight();
		}
	}

	public static final int LIMIT = 1;

	/** 刷新一次所需刷新令 */
	private static final int SHUA_XIN_LING_NEED = 1;
	private City city;
	private HeiShiStoreDto dto;

	public HeiShi(City city) {
		this.city = city;
		dto = getDto();
	}

	private HeiShiStoreDto getDto() {
		if (dto != null) {
			return dto;
		}

		HeiShiStoreDao dao = Daos.getHeiShiStoreDao();
		dto = dao.get(city.getId());

		if (dto != null) {
			return dto;
		}

		init();

		return dto;
	}

	private void init() {
		HeiShiStoreDao dao = Daos.getHeiShiStoreDao();
		dto = new HeiShiStoreDto();
		dto.setUname(city.getId());
		dto.setHeiShiGoods(getRandomGoods());
		dao.save(dto);
	}

	private boolean isAgainst2() {
		VipPrivilegeTemplet temp = getVipTemplet();
		return temp.getAgainst2() == 1;
	}

	private boolean isAgainst1() {
		VipPrivilegeTemplet temp = getVipTemplet();
		return temp.getAgainst1() == 1;
	}

	private VipPrivilegeTemplet getVipTemplet() {
		int level = city.getVipPlayer().getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig
				.get((byte) level);
		return temp;
	}
	private List<HeiShiGoodsDto> getRandomGoods() {
		List<AgainstGoodsLibraryTemplet> tempAll = Lists
				.newArrayList(AgainstGoodsLibraryTempletConfig.getAll());

		List<AgainstGoodsLibraryTemplet> rd = Lists.newArrayList();

		int count = D.SHEN_MI_GOODS_COUNT;

		if(isAgainst2()) {
			count += 4;
		} else if(isAgainst1()) {
			count += 2;
		}
		
		while (rd.size() < count) {
			int type = getRandomType();
			List<AgainstGoodsLibraryTemplet> all = findByType(tempAll, type);
			if (all.isEmpty()) {
				continue;
			}
			AgainstGoodsLibraryTemplet t = random(all);
			rd.add(t);
			tempAll.remove(t);
		}

		return build(rd);
	}

	private List<AgainstGoodsLibraryTemplet> findByType(
			List<AgainstGoodsLibraryTemplet> values, int type) {

		List<AgainstGoodsLibraryTemplet> all = Lists.newArrayList();

		for (AgainstGoodsLibraryTemplet f : values) {

			if (f.getType() == type) {

				all.add(f);

			}
		}

		return all;
	}

	private List<HeiShiGoodsDto> build(List<AgainstGoodsLibraryTemplet> all) {
		List<HeiShiGoodsDto> ls = Lists.newArrayList();
		for (AgainstGoodsLibraryTemplet a : all) {
			ls.add(build(a));
		}
		return ls;
	}

	private HeiShiGoodsDto build(AgainstGoodsLibraryTemplet a) {
		HeiShiGoodsDto dto = new HeiShiGoodsDto();
		dto.setId(a.getIdfen());
		dto.setLimit(LIMIT);
		dto.setRemainCount(a.getNumber() * a.getQuota());
		dto.setCountExchangeEvery(a.getNumber());
		return dto;
	}

	private AgainstGoodsLibraryTemplet random(
			List<AgainstGoodsLibraryTemplet> all) {
		WeightFetcher<AgainstGoodsLibraryTemplet> weightAble = new WeightFetcher1();
		return Util.Random.getRandomOneByWeight(all, weightAble);
	}

	/**
	 * 随机一个物品类型
	 * 
	 * @return
	 */
	private int getRandomType() {
		List<AgainstTypeLibraryTemplet> all = AgainstTypeLibraryTempletConfig
				.getAll();
		WeightFetcher<AgainstTypeLibraryTemplet> weightAble = new WeightFetcher2();
		AgainstTypeLibraryTemplet t = Util.Random.getRandomOneByWeight(all,
				weightAble);
		return t.getId();
	}

	public void exchange(int id) {
		HeiShiGoods goods = getGoods(id);
		if(goods == null) {
			throw new NullPointerException(id + "");
		}
		checkRemain(goods);
		checkHasExchange(goods);
		reduceExchange(goods);
		goods.send(city);
		commit();
	}

	private void checkHasExchange(HeiShiGoods goods) {
		if (goods.hasExchange()) {
			throw new OperationFaildException(S.S10329);
		}
	}

	/**
	 * 获得指定ID的物品
	 * 
	 * @param id
	 * @return
	 */
	private HeiShiGoods getGoods(int id) {
		List<HeiShiGoods> all = getGoods();
		for (HeiShiGoods g : all) {
			if (g.getId() == id) {
				return g;
			}
		}
		return null;
	}

	private void commit() {
		HeiShiStoreDto dto = getDto();
		HeiShiStoreDao dao = Daos.getHeiShiStoreDao();
		dao.save(dto);
	}

	private void checkRemain(HeiShiGoods goods) {
		if (goods.getCountRemain() <= 0) {
			throw new OperationFaildException(S.S10228);
		}
	}

	private void reduceExchange(HeiShiGoods goods) {
		if (goods == null) {
			throw new OperationFaildException(S.S10254);
		}

		String needs = goods.getNeeds();

		NeedsChecker c = NeedsFactory.getNeedsCheckerImpl2(needs);
		c.check(city);
		c.deduct(city);
	}

	/**
	 * 刷新物品列表
	 */
	public void refresh() {
		if (enouph(D.ID_SHUA_XIN_LING)) {
			reduce(D.ID_SHUA_XIN_LING);
		} else {
			city.getPlayer().reduceGoldOrJinDing(D.ZHAO_XIAN_BANG_REFRESH_GOLD_NEED);
		}
		updateGoods();
	}

	private void reduce(int idShuaXinLing) {
		city.getBagAuto().remove(idShuaXinLing, SHUA_XIN_LING_NEED);
	}

	private boolean enouph(int idShuaXinLing) {
		return city.getBagAuto().getCount(idShuaXinLing) >= SHUA_XIN_LING_NEED;
	}

	/**
	 * 更新黑市物品列表
	 */
	private void updateGoods() {
		dto.setHeiShiGoods(getRandomGoods());
		commit();
	}

	/**
	 * 所有的物品列表
	 * 
	 * @return
	 */
	public List<HeiShiGoods> getGoods() {
		HeiShiStoreDto dto = getDto();

		List<HeiShiGoods> ls = Lists.newArrayList();
		List<HeiShiGoodsDto> all = dto.getHeiShiGoods();

		for (HeiShiGoodsDto d : all) {
			ls.add(new HeiShiGoods(d));
		}
		return ls;
	}

	public City getCity() {
		return city;
	}

	/**
	 * 剩余刷新时间
	 */
	public int getRemainSec() {
		String s = Util.Time.getNearest(D.ZHAO_XIAN_BANG_REFRESH_TIME);
		return Util.Time.getRemainSec(s);
	}
}
