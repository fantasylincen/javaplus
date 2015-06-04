package cn.mxz.zan;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDao;
import mongo.gen.MongoGen.KeyValueDto;
import cn.javaplus.util.Util;
import cn.mxz.Attribute;
import cn.mxz.RegistAwardTemplet;
import cn.mxz.RegistAwardTempletConfig;
import cn.mxz.battle.buff.AttributeSingle;
import cn.mxz.city.City;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.formation.AdditionType;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

public class ZanManagerImpl implements ZanManager {
	private final City			user;
	/**
	 * 今日变量
	 */
	private final UserCounter	uCounter;
	/**
	 * 永久变量
	 */
	private final UserCounter	hCounter;

	private final String		KEY	= "ZAN";

	// private final KeyValueData db =;

	public ZanManagerImpl(City user) {
		this.user = user;
		uCounter = user.getUserCounter();
		hCounter = user.getUserCounterHistory();
	}

	/**
	 * 获取相应等级的奖励模板
	 *
	 * @return
	 */
	private RegistAwardTemplet getTemplet() {
		int level = getZanLevel();
		return RegistAwardTempletConfig.get(level);
	}

	@Override
	public void clickZan() {

		if (getTodayIsClick()) {
			System.err.println(user.getId() + "今日已经点过赞了");
			return;
		}

		getPrize();
		uCounter.set(CounterKey.ZAN_TODAY_CLICK, 1);
		hCounter.add(CounterKey.ZAN_COUNT, 1);
		addTotalCount();
		setNextGold();

	}

	/**
	 * 领取今日奖励
	 */
	private void getPrize() {
		int gold = getNextGold();
		user.getPlayer().addGiftGold(gold);

		// 林岑加 2014年5月5日 20:06:54
		hCounter.add(CounterKey.VIP_GROWTH, (int) (gold * D.ZAN_VIP_GROWTH_PAR));
	}

	/**
	 * 返回增加的加成
	 *
	 * @return
	 * @return
	 */
	@Override
	public Attribute getAddition(Hero hero) {
		int position = user.getFormation().getPosition(hero);
		if (!user.getFormation().getSelected().getFighters().contains(hero)) {
			return new AttributeEmpty();
		}

		int part = user.getFormation().getFormationPart(hero);
		RegistAwardTemplet templet = getTemplet();

		AdditionType at;
		switch (part) {
		case 0:
			at = AdditionType.fromNum(templet.getFrontFirst());
			return new AttributeSingle(at, templet.getFrontFirstFixed());
		case 1:
			at = AdditionType.fromNum(templet.getFrontMiddle());
			return new AttributeSingle(at, templet.getFrontMiddle());
		default:
			at = AdditionType.fromNum(templet.getFrontTail());
			return new AttributeSingle(at, templet.getFrontTailFixed());
		}

	}

	@Override
	public int getCount() {
		return hCounter.get(CounterKey.ZAN_COUNT);
	}

	@Override
	public int getTotalCount() {
		KeyValueDao DAO = Daos.getKeyValueDao();

		KeyValueDto ret = DAO.get(KEY);
		if (ret == null) {
			ret = new KeyValueDto();
			ret.setK(KEY);
			ret.setV("0");
			DAO.save(ret);
			return 0;
		}
		return Integer.parseInt(ret.getV());
	}

	private void addTotalCount() {
		KeyValueDao DAO = Daos.getKeyValueDao();
		KeyValueDto ret = DAO.get(KEY);
		int count = Integer.parseInt(ret.getV());
		count++;
		ret.setV(count + "");
		DAO.save(ret);

	}

	@Override
	public int getZanLevel() {
		int zanCount = getCount();
		
		int[] arrayByTotalDay = RegistAwardTempletConfig.getArrayByTotalDay();
		
		

		return calcLevel( arrayByTotalDay, zanCount, 0);
	}
	
	public static int calcLevel( int[] data, int input, int beginWith ){
        if( data == null ) {
            throw new IllegalArgumentException();
        }
        int level = beginWith - 1;
        for( int aData : data ) {
            if( aData > input ) {
                break;
            }
            level++;
        }
        return level;
    }

	@Override
	public boolean getTodayIsClick() {
		return uCounter.get(CounterKey.ZAN_TODAY_CLICK) == 1;
	}

	/**
	 * 计算下一次的领奖
	 *
	 * @return
	 */
	private int setNextGold() {
		// int zanCount = getCount();
		int base = getTemplet().getAward();
		double rate = Util.Random.get(0.8, 1.2);
		int ret = (int) (base * rate);
		hCounter.set(CounterKey.NEXT_GOLD, ret);
		return ret;
	}

	@Override
	public int getNextGold() {
		int count = getCount();
		if (count == 0) {
			return RegistAwardTempletConfig.get(count).getAward();
		}
		return hCounter.get(CounterKey.NEXT_GOLD);

	}

	public static void main(String[] args) {

		int[] arrayByTotalDay = RegistAwardTempletConfig.getArrayByTotalDay();
		for( int i = 0; i < 1000; i++ ){
			System.out.println( i + ":" + calcLevel(arrayByTotalDay, i, 0));
		}
		
	}

}
