package cn.mxz.yunyou;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDataDao;
import mongo.gen.MongoGen.KeyValueDataDto;
import cn.javaplus.util.Util;
import cn.mxz.RoamAwardTemplet;
import cn.mxz.RoamAwardTempletConfig;
import cn.mxz.bag.BagSnapsort;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.shop.Shopper;
import cn.mxz.user.team.god.Hero;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import define.D;

public class YunYouPlayerImpl implements YunYouPlayer {

	public class YunYouFilter implements Predicate<YunyouData> {

		@Override
		public boolean apply(YunyouData arg0) {
			return user.getTeam().contains(arg0.getHeroId());
		}

	}

	private static final int	WINE_ID		= 130031;

	private static final String	MODULE_NAME	= "YunYouPlayer";

	private final AtomicInteger	index;

	private final String		key;
	private City				user;
	private List<YunyouData>	list;

	/**
	 * 如果此英雄已经消失（被吞噬）了，则过滤掉
	 */
	@Override
	public Collection<YunyouData> getList() {
		// for( YunyouData d : list ){
		// if( Hero h = user.getTeam().getHero( d.getHeroId() );)
		// }
		Collection<YunyouData> filter = Collections2.filter(list, new YunYouFilter());
		//filter.
		return filter;
	}

	public YunYouPlayerImpl(City city) {
		this.user = city;
		key = user.getId() + MODULE_NAME;
		loadFromDB();

		Collections.sort(list, YunyouData.ID_COMPARATOR );
		if( list.size() == 0 ){
			index = new AtomicInteger(0);
		}else{
			index = new AtomicInteger( list.get(0).getId() );
		}

	}
	
	

	public YunyouData getDataById(int id) {
		for (YunyouData data : list) {
			if (data.getId() == id) {
				return data;
			}
		}
		// System.err.println( "云游仙人的id不存在 " + id );
		return null;
	}

	/**
	 * 购买产品
	 *
	 * @param index
	 */
	public void buy(int id) {
		/************************************** 是否多余 *********************************/
		YunyouData data = getDataById(id);
		if (data == null) {
			// throw new IllegalOperationException( "云游仙人不存在" );
			throw new NullPointerException("云游仙人不存在");
			// System.err.println( "云游仙人不存在" );
		}
		/************************************** 是否多余 *********************************/
		if (!data.isBuy() && !data.isExpire()) {
			//new Shopper(user).buy(data.getPropId(), 1);
			user.getPlayer().reduceGoldOrJinDing(data.getNeedGold());
			user.getBagAuto().addProp( data.getPropId(), 1);
			
			data.setRemainCount();
		} else {
			throw new OperationFaildException(S.S10210);
		}
		user.getMessageSender().send(S.S10334);
		saveToDB();
	}

	

	@Override
	public void onEvent() {
		// if( list.size() == MAX_COUNT ){
		// return ;
		// }

		// 获取被指点的英雄id
		List<Hero> fighters = new ArrayList<Hero>(user.getFormation().getAlternate().getFighters());
		fighters.addAll(user.getFormation().getSelected().getFighters());
		if( fighters.size() == 1 ){
			throw new OperationFaildException(S.S10249);
		}
		int heroId = 0;
		while( true ){//排除主角
			heroId = Util.Random.getRandomOne(fighters).getTypeId();
			if( user.getTeam().getPlayer().getTypeId() != heroId ){
				break;
			}
		}
		YunyouData data = new YunyouData(heroId, index.incrementAndGet());

		// list.add( 0, data );
		list.add(data);

		saveToDB();
	}

	private void saveToDB() {
		KeyValueDataDao DAO = Daos.getKeyValueDataDao();
		if (list.size() == 0) {
			DAO.delete(key);
			return;
		}
		KeyValueDataDto o = new KeyValueDataDto();
		o.setUname(key);
//		System.out.println( "xxxxxxxxx" + JSON.toJSONString(list) );
		o.setData(JSON.toJSONString(list));

		DAO.save(o);
	}

	/**
	 * 如果数据库不存在，则返回空list
	 */
	private void loadFromDB() {

		KeyValueDataDao DAO = Daos.getKeyValueDataDao();
		KeyValueDataDto o = DAO.get(key);
		if (o == null) {
			list = Lists.newArrayList();
			return;
		}

		String jsonString = o.getData();
		if (jsonString != null) {
			list = JSON.parseArray(jsonString, YunyouData.class);
			if (list == null) {
				System.err.println("云游仙人list出问题了" + jsonString);
			}
		}
	}

	// @Override
	// public float getDiscount(int toolId) {
	// return 0;
	//
	// }

	@Override
	public int getXianRenCount() {
		return list.size();
	}

	/**
	 * 购买并使用“酒”来强制双倍领取经验
	 * @param index
	 * @param isDouble
	 */
	@Override
	public void getExpForceDouble(int index) {
		YunyouData data = getDataById(index);
		if (data == null) {
			System.err.println("云游仙人不存在");
			return;
		}
		RoamAwardTemplet t = RoamAwardTempletConfig.get(data.getLevel());
		int needWine = t.getWine();
		if( user.getBag().getCount(WINE_ID) < needWine ){
			needWine -= user.getBag().getCount(WINE_ID);//差几个买几个
			new Shopper(user).buy(WINE_ID, needWine);

		}
		getExp( index, true );
	}
	

	@Override
	public void getExp(int index, Boolean isDouble) {
		/************************************** 是否多余 *********************************/
		YunyouData data = getDataById(index);
		if (data == null) {
			System.err.println("云游仙人不存在");
			return;
		}
		/************************************** 是否多余 *********************************/

		if (!data.isExpire()) {
			System.err.println("指点时间未结束");
			return;
		}
		Hero h = user.getTeam().get(data.getHeroId());
		if (h == null) {
			System.err.println("hero id 不存在" + data.getHeroId());
			return;
		}

		int exp = data.calcExp(h.getLevel());
		if (isDouble) {
			RoamAwardTemplet t = RoamAwardTempletConfig.get(data.getLevel());
			int needWine = t.getWine();
			exp *= D.YUNYOU_ADDTION_EXP;
			BagSnapsort b = new BagSnapsort();
			b.snapsort(user.getBag());
			user.getBag().remove(WINE_ID, needWine);
			b.snapsort( user.getBag() );
			
		}

		FighterSnapshoot s =  new FighterSnapshoot(user);
		s.snapshoot();
		h.addExp(exp);
		s.snapshoot();

		list.remove(data);
		saveToDB();

		//xxx获得了xxx经验
		user.getMessageSender().send(S.S60169, h.getName(), exp);
//		 MessageFactory.getSystem().sendMessage( user.getSocket(), S.S12003,
//		 h.getName()+"$"+exp, MessageType.APPEND );//S12003 %s0 获得了%s1 经验值
//
//		
	}

}
