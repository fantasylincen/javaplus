package cn.mxz.activity.heishi;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.HeishiDto;
import cn.mxz.FighterTempletConfig;
import cn.mxz.TrueBlackMarketTemplet;
import cn.mxz.TrueBlackMarketTempletConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.god.FighterP.FightersPro;
import cn.mxz.team.builder.FightersBuilder;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 玩家的个人数据
 * 
 * @author Administrator
 * 
 */
public class HeishiManager {

	private final City user;

	/**
	 * 七彩金石的数量,白水晶的数量，黄水晶的数量
	 */
	private int qsjs,bsj,hsj;
	private HeishiDto heishiDto;
	/**
	 * 购买数据，<物品id,购买数量>
	 */
	private Map<Integer, Integer> data = Maps.newHashMap();

	public HeishiManager(City user) {
		super();
		this.user = user;
		heishiDto = Daos.getHeishiDao().get(user.getId());
		if (heishiDto != null) {
			qsjs = heishiDto.getQsjs();
			bsj = user.getUserCounterHistory().get( CounterKey.BAI_SHUI_JING );
			hsj = user.getUserCounterHistory().get( CounterKey.HUANG_SHUI_JING );
			buildMap(heishiDto.getBuyStr());
		} else {
			heishiDto = new HeishiDto();
			heishiDto.setQsjs(0);
			heishiDto.setUserName(user.getId());
			heishiDto.setBuyStr("");
			user.getUserCounterHistory().set( CounterKey.BAI_SHUI_JING, 0 );
			user.getUserCounterHistory().set( CounterKey.HUANG_SHUI_JING, 0 );
			Daos.getHeishiDao().save(heishiDto);

			bsj=hsj=qsjs = 0;
		}
	}

	/**
	 * 构建商品兑换情况的map
	 */
	private void buildMap(String str) {
		
		// for(StackTraceElement s : Thread.currentThread().getStackTrace() ){
		// System.out.println("dddddddddddddddddddddddd" + s.getMethodName());
		// }

		if (str == null || str.isEmpty()) {
			return;
		}
		String[] arr = str.split(",");
		for (int i = 0; i < arr.length; i += 2) {
			int propId = Integer.parseInt(arr[i]);
			int count = Integer.parseInt(arr[i + 1]);
			data.put(propId, count);
		}
	}

	/**
	 * 构建商品兑换情况的的字符串，方便数据库保存,以客户端使用
	 */
	public String mapToStr() {
		String ret = "";
		Set<Entry<Integer, Integer>> entrySet = data.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			ret += entry.getKey();
			ret += ",";
			ret += entry.getValue();
			ret += ",";
		}

		if (ret.length() > 1) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	/**
	 * 兑换物品
	 * 
	 * @param propId
	 * @param count
	 */
	public void exchange(int propId, int count) {
		int allCount = count;
		if (data.containsKey(propId)) {
			allCount += data.get(propId);
		}
		TrueBlackMarketTemplet templet = TrueBlackMarketTempletConfig
				.get(propId);
		if (templet == null) {
			throw new SureIllegalOperationException("配置表不存在此物品id: " + propId);
		}
		if (templet.getState() == 0) {
			throw new SureIllegalOperationException("该商品已经下架，id: " + propId);
		}
		if (allCount > templet.getMax() && templet.getMax() != -1) {
			throw new SureIllegalOperationException("该商品(" + propId
					+ ")已经超过最大购买限制." + data.get(propId) + "," + count + ","
					+ templet.getMax());
		}

//		String need = templet.getSpar() * count;

		reduceJingShi(templet.getSpar());

		// public void send(City city) {
		// String prize = getPrize();
		// city.getPrizeSender1().send(prize);
		// dto.setHasExchange(true);
		// }
		//
		// String getPrize() {
		// return getTemplet().getReward() + "," + getRemainCount();
		// }

		String prize = propId + "," + count;
		user.getPrizeSender1().send(prize);

		data.put(propId, allCount);
		saveDb();

		updateFighter(propId);
		// updateEquipment(propId);

	}

	private void updateFighter(int propId) {
		if (FighterTempletConfig.get(propId) != null) {

			Hero hero = user.getTeam().get(propId);
			Collection<Hero> fighters = Lists.newArrayList(hero);

			FightersPro fs = new FightersBuilder().build(fighters);

			MessageFactory.getFighter().fightersUpdate(user.getSocket(), fs);
		}
	}

	/**
	 * 保存数据库，也保存为了台湾版特制的黄水晶和白水晶到UserCounterHistory中
	 */
	private void saveDb() {
		heishiDto.setBuyStr(mapToStr());
		heishiDto.setQsjs(qsjs);
		Debuger.debug(user.getId()
				+ ":HeishiManager.saveDb() ------------------------ "
				+ heishiDto.getQsjs());
		Daos.getHeishiDao().save(heishiDto);
		
		user.getUserCounterHistory().set( CounterKey.BAI_SHUI_JING, bsj );
		user.getUserCounterHistory().set( CounterKey.HUANG_SHUI_JING, hsj );
	}

	/**
	 * @return hsj
	 */
	public int getHsj() {
		return hsj;
//		return 10;
	}
	
	/**
	 * @return bsj
	 */
	public int getBsj() {
//		return 30;
		return bsj;
	}
	
	/**
	 * @return qcjs
	 */
	public int getQsjs() {
		return qsjs;
	}

	/**
	 * 增加七色晶石
	 * 
	 * @param addCount
	 */
	public void addQsjs(int addCount) {

		int old = qsjs;
		if (addCount < 0) {
			throw new SureIllegalOperationException("增加七色晶石参数错误：" + addCount);
		}
		qsjs += addCount;
		saveDb();

		// StackTraceElement[] stackTrace =
		// Thread.currentThread().getStackTrace();
		// String str = "玩家：" + user.getId() + "在" +
		// stackTrace[2].getClassName() + "." +stackTrace[2].getMethodName()
		// +",";
		// str += stackTrace[3].getClassName() + "." +
		// stackTrace[3].getMethodName() + ",";
		// str += stackTrace[4].getClassName() + "." +
		// stackTrace[4].getMethodName() ;
		// str += "函数中调用addQsjs";
		// str += ",原始:" + old + ",要增加:" + addCount + "，剩余：" + qsjs;
		// Debuger.debug( "qsjs", str );
	}
	
	/**
	 * 增加黄水晶
	 * 
	 * @param addCount
	 */
	public void addHsj(int addCount) {

		
		if (addCount < 0) {
			throw new SureIllegalOperationException("增加黄水晶参数错误：" + addCount);
		}
		hsj += addCount;
		saveDb();
	}

	/**
	 * 增加白水晶
	 * 
	 * @param addCount
	 */
	public void addBsj(int addCount) {

		
		if (addCount < 0) {
			throw new SureIllegalOperationException("增加白水晶参数错误：" + addCount);
		}
		bsj += addCount;
		saveDb();
	}


	public void reduceJingShi(String jingshi ) {

		String[] split = jingshi.split(",");
		if( split.length < 2 ){
			throw new IllegalOperationException("黑市配置表有错误:" + jingshi );
		}
		int jingshiId = Integer.parseInt(split[0]);
		int needCount = Integer.parseInt(split[1]);
		if (needCount < 0) {
			Debuger.error("扣除晶石参数错误：" + needCount);
			throw new OperationFaildException(S.S10338);
		}
		
		int oldCount = 0;
		if( jingshiId == 110025){//白水晶
			if( bsj < needCount ){
				throw new OperationFaildException(S.S10339);
			}
			bsj -= needCount;
		}
		
		else if( jingshiId == 110026){//黄水晶
			if( hsj < needCount ){
				throw new OperationFaildException(S.S10339);
			}
			hsj -= needCount;
		}
		else{
			if (qsjs < needCount) {
				throw new OperationFaildException(S.S10339);
			}
			qsjs -= needCount;
		}
		
		
		
		

		saveDb();
		
	}

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList();
		for (int i = 0; i < 100000000; i++) {
			list.add(i + "");
			System.out.println(i);
		}
	}

}
