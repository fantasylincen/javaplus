package cn.mxz.firstrechargeperday;

import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDataDao;
import mongo.gen.MongoGen.KeyValueDataDto;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.EverydayChargeTemplet;
import cn.mxz.EverydayChargeTempletConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.util.counter.CounterKey;

import com.alibaba.fastjson.JSON;

public enum FirstRechargePerDay {
	INSTANCE;
	
	/**
	 * 领取每日首冲奖励需要今日重置的数量
	 */
	private static final int	NEED_GOLD	= 50;
	private static final String KEY = "FirstRechargePerDay";
	
	PrizeImpl prize;
	
	
	FirstRechargePerDay(){
		loadAward();
	}
	
	/**
	 * 从数据库获取今日奖励，如果不存在，则根据规则生成一个
	 */
	private void loadAward() {
		
		KeyValueDataDao DAO = Daos.getKeyValueDataDao();
		KeyValueDataDto o = DAO.get(KEY);
		if (o == null) {
			genAward();
			return;
		}

		String jsonString = o.getData();
		if (jsonString != null) {
			prize = JSON.parseObject(jsonString, PrizeImpl.class );
		}
		
	}
	
	/**
	 * 生成今日奖励，并保存到数据库
	 */
	public void genAward(){
		WeightFetcher<EverydayChargeTemplet> weightAble = new WeightFetcher<EverydayChargeTemplet>() {

			@Override
			public Integer get(EverydayChargeTemplet t) {
				return t.getWeight();
			}
		};
		
		List<EverydayChargeTemplet> list = EverydayChargeTempletConfig.getAll();		
		EverydayChargeTemplet t = Util.Random.getRandomOneByWeight(list, weightAble);

		String[] s = t.getAwards().split(":");

		int propId = Integer.parseInt(s[0]);
		s = s[1].split(",");
		int min = Integer.parseInt(s[0]);
		int max = Integer.parseInt(s[1]);

		int count = Random.get(min, max);
		prize = new PrizeImpl(propId, count);
		
		saveAward();
	}
	
	/**
	 * 在数据库中奖励中保存今日奖励
	 */
	private void saveAward() {
		//String data = prize.getId() + "," + prize.getCount();
		
		KeyValueDataDao DAO = Daos.getKeyValueDataDao();
		
		KeyValueDataDto o = new KeyValueDataDto();
		o.setUname(KEY);
		o.setData(JSON.toJSONString(prize));

		DAO.save(o);
	}
	
	
	/**
	 * 返回数据，简化起见，返回一个字符串，格式如下:
	 * 今日充值元宝数,是否领取奖励(1:领取，0：未领取)，道具id，数量                       
	 * @param user
	 * @return
	 */
	public String getDate( City user ){
		String ret = user.getUserCounter().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) + ",";
		ret += (user.getUserCounter().isMark( CounterKey.FIRST_RECHARGE_PER_DAY ) ? 1 : 0 ) + "," ;
		ret += prize.getId() + ",";
		ret += prize.getCount();
		return ret;
	}
	
	/**
	 * 返回数据，简化起见，返回一个字符串，格式如下:
	 * 今日充值元宝数,是否领取奖励(1:领取，0：未领取)，道具id，数量                       
	 * @param user
	 * @return
	 */
	public String getDate1( City user ){
		String ret = user.getUserCounter().isMark( CounterKey.FIRST_RECHARGE_PER_DAY ) ? "1" : "0" ;
		return ret;
	}
	
	
	
	/**
	 * 领取今日奖励
	 * @return
	 */
	public void getAward( City user ){
		if(user.getUserCounter().isMark( CounterKey.FIRST_RECHARGE_PER_DAY )){
			throw new OperationFaildException(S.S10335);
		}
		if( user.getUserCounter().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) < NEED_GOLD ){
			throw new OperationFaildException(S.S10337);
		}
		prize.award(user.getPlayer());
		user.getUserCounter().mark( CounterKey.FIRST_RECHARGE_PER_DAY );
	}
	
	public static void main(String[] args) {
		PrizeImpl p = FirstRechargePerDay.INSTANCE.prize;
		System.out.println(p.getId() + "," + p.getCount() );
	}

	public boolean showTips(City user) {
		if(user.getUserCounter().isMark( CounterKey.FIRST_RECHARGE_PER_DAY )){
			return false;
		}
		if( user.getUserCounter().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT) < NEED_GOLD ){
			return false;
		}
		return true;
	}

	
	
}
