package cn.mxz.yuanshen;

import cn.javaplus.math.Fraction;
import cn.mxz.Attribute;
import cn.mxz.YuanShenTemplet;
import cn.mxz.YuanShenTempletConfig;
import cn.mxz.battle.YuanShen;
import cn.mxz.city.City;
import cn.mxz.fighter.AttributeCalculator;
import cn.mxz.fighter.YuanShenDto;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import define.D;

public class YuanShenImpl implements YuanShen {

	private static final int RESET_NEED_PROP = 1;
	private YuanShenDto dto;
	private City city;

	public YuanShenImpl(City city, YuanShenDto dto) {
		this.city = city;
		this.dto = dto;
	}

	@Override
	public int getType() {
		return dto.getType();
	}

	@Override
	public int getLevel() {
		return dto.getLevel();
	}

	@Override
	public Attribute getAddition() {
		YuanShenTemplet temp = YuanShenTempletConfig.get(dto.getType());
		return AttributeCalculator.multiply(temp, getLevel());
	}

	@Override
	public void addExp(int exp) {

		int maxLevel = D.MAX_YUANSHEN_LEVEL;

		if (getLevel() >= maxLevel) {

			return;
		}

		dto.addExp(exp);

		while (!dto.getExp().isProper()) { // 如果经验是个假分数

			dto.addExp(-dto.getExp().getDenominator()); // 当前经验的分子 -去当前经验的分母

			dto.addLevel(1); // 等级 + 1
		}

		dto.commit();
	}

	@Override
	public void reset() {

		
		if(!propEnouph()) {
			city.getPlayer().reduceGoldOrJinDing(D.YUAN_SHEN_RESET_NEED_GOLD);
		} else {
			city.getBagAuto().remove(D.QKD_ID, RESET_NEED_PROP);
		}
		
		resetReal();
	}

	private boolean propEnouph() {
		int c = city.getBagAuto().getCount(D.QKD_ID);
		return c >= RESET_NEED_PROP;
	}

	private void resetReal() {
//		dto.setLevel(1);
		
		int all = getExpAll();
		
		Integer id = dto.getRandomInitId();
		dto.setType(id);
		
		dto.setExpAll(all);

		dto.commit();

		UserCounterSetter uc = city.getUserCounterHistory();
		uc.add(CounterKey.YUAN_SHEN_RESET, 1);
		
		
	}


	@Override
	public Fraction getExp() {
		return dto.getExp();
	}

	@Override
	public int getExpAll() {
		return dto.getExpAll();
	}

	@Override
	public int getShenJia() {

		YuanShenTemplet temp = getTemplet();
		return (int) (temp.getSocial() + (getLevel() - 1)
				* temp.getSocialGrow());
	}

	@Override
	public float getExpPar() {
		return dto.getExpPar();
	}

	@Override
	public int getStep() {
		return getTemplet().getStep();
	}

	private YuanShenTemplet getTemplet() {
		return YuanShenTempletConfig.get(getType());
	}
}
