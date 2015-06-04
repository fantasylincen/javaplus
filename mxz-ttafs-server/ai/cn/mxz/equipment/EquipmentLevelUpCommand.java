package cn.mxz.equipment;

import message.S;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.base.ServerCommand;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.util.checker.Checker;
import cn.mxz.util.debuger.Debuger;
import db.dao.impl.DaoFactory;
import db.dao.impl.NewEquipmentDao;
import db.domain.NewEquipment;
import define.D;

/**
 * 强化装备操作
 *
 * @author 林岑
 *
 */
class EquipmentLevelUpCommand implements ServerCommand {
	private NewEquipment	data;

	/**
	 * 强化增加的等级
	 */
	 private int levelAdd;

	private City			city;

	private EquipmentImpl	e;

	EquipmentLevelUpCommand(EquipmentImpl e, int levelAdd, City city) {

		this.e = e;

		this.data = e.getDto();

		 this.levelAdd = levelAdd;

		this.city = city;
	}

	@Override
	public void run() {

//		int levelUpNeed = e.getLevelUpCashNeed();

		int levelUpNeed = EquipmentConfig.getLevelUpNeed(e.getTypeId(), e.getLevel(), levelAdd);

		city.getPlayer().reduce(PlayerProperty.CASH, levelUpNeed);

		int levelAdd = getLevelAdd();

		data.addLevel(levelAdd);
		data.addPrice(levelUpNeed);

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();

		DAO.update(data);
	}

	private int getLevelAdd() {

//		byte level = (byte) city.getVipPlayer().getLevel();
//		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get(level);
//
//		String c = temp.getIntensifyCrit();
//
//		StringResolver s;
//		StringResolver r = Util.Str.resolve(c);
//		try {
//
//			List<StringResolver> split = r.split("\\|");
//
//			WeightFetcher<StringResolver> weightAble = new WeightFetcher<StringResolver>() {
//
//				@Override
//				public Integer get(StringResolver t) {
//					List<StringResolver> ss = t.split(":");
//					return ss.get(1).getInt();
//				}
//			};
//
//			s = Util.Random.getRandomOneByWeight(split, weightAble);
//		} catch (Exception e) {
//			Debuger.error("---------------------- " + c);
//			throw Util.Exception.toRuntimeException(e);
//		}
//
//		int add = s.split(":").get(0).getInt();
		/// 以上  2014年6月20日 18:42:31   策划规则变更, 没有强化暴击
		
		int add = levelAdd;

		int ulv = city.getLevel();

		int elv = e.getLevel();

//		if(city.isTester()) {
//			add = 2;
//		}

		Debuger.debug("EquipmentLevelUpCommand.getLevelAdd()" + elv + ", " + add + ", " + ", " +  ulv +", "+ D.XXXXX);

		if(elv + add > ulv + D.XXXXX) { //最大强化等级限制
			add = 1;
		}

		return add;
	}

	@Override
	public void check() {

		checkLevelMax();

		checkCash();
	}

	private void checkCash() {

		Checker checker = city.getChecker();

//		int levelUpNeed = e.getLevelUpCashNeed();
		
		int levelUpNeed = EquipmentConfig.getLevelUpNeed(e.getTypeId(), e.getLevel(), levelAdd);
		
		Debuger.debug("EquipmentLevelUpCommand.checkCash()" + levelUpNeed);

		checker.checkPlayerProperty(PlayerProperty.CASH, levelUpNeed);
	}

	private void checkLevelMax() {

		int level = data.getLevel() + 1;

		EquipmentTemplet temp = EquipmentTempletConfig.get(data.getTempletId());

		int maxLvl = temp.getMaxLvl();

		if (level > maxLvl || data.getLevel() >= D.XXXXX + city.getLevel()) {

			throw new OperationFaildException(S.S10103);
		}
	}

}
