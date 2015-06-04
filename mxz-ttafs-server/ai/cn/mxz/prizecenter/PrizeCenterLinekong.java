package cn.mxz.prizecenter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.KeyValueDataDao;
import mongo.gen.MongoGen.KeyValueDataDto;

import org.joda.time.DateTime;

import cn.mxz.bag.SuperBagSnapsort;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.IPrizeCenter;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.user.builder.UserBuilder;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class PrizeCenterLinekong implements IPrizeCenter {

	private final City				user;
	private final String			key;
	/**
	 * 所有的奖品,注意不包括蓝港的奖品
	 */
	private List<UserPrizePackage>	prizes	= Lists.newArrayList();
	private AtomicInteger			maxId;


	public PrizeCenterLinekong(City user) {
		this.user = user;
		key = "PrizeCenter" + user.getId();
		load();
		getMaxId();
	}
	
		
	private void getMaxId() {
		int tempId = 0;
		for (UserPrizePackage p : prizes) {
			if (p.getId() > tempId) {
				tempId = p.getId();
			}
		}
		maxId = new AtomicInteger(tempId);
	}

	
	private void load() {

		KeyValueDataDto o = Daos.getKeyValueDataDao().get(key);
		if (o != null) {
			String jsonString = o.getData();
			if (jsonString != null && !jsonString.isEmpty()) {
				try {

					prizes = JSON.parseArray(jsonString, UserPrizePackage.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		Iterator<UserPrizePackage> iterator = prizes.iterator();
		while( iterator.hasNext() ){
			UserPrizePackage p = iterator.next();
			//System.out.println( p.getTitle() + " :" + new DateTime( p.getEndTime() *1000l));
			if( p.getEndTime() < (System.currentTimeMillis() / 1000) ){
				iterator.remove();
			}
		}

	}


	private void save() {

		KeyValueDataDao DAO = Daos.getKeyValueDataDao();

		KeyValueDataDto o = new KeyValueDataDto();
		o.setUname(key);
		try {
			o.setData(JSON.toJSONString(prizes));
		} catch (Exception e) {
			e.printStackTrace();
		}

		DAO.save(o);

	}

	
	@Override
	public List<IUserPrizePackage> getData() {
		
		// System.out.println( "奖励内容 " + prizes );
		List<IUserPrizePackage> ret = Lists.newArrayList();

		if(user.getPlayer().isThirdPartyPlayer()) {
			ret.addAll(ThirdPartyPlatformFactory.getThirdPartyPlatform().queryPrize(user));
		}
//		 System.out.println( "蓝港奖励内容1 " + ret );
		
		ret.addAll( prizes );//增加非蓝港的奖励
		return ret;
	}

	/**
	 * 获取一个奖励，非蓝港的
	 *
	 * @param id
	 */
	@Override
	public void getPrize(int id) {
		UserPrizePackage packag = findPrizeById(id);
		if (packag == null) {
			throw new SureIllegalOperationException("奖品id" + id + "不存在，或者已经到期");
		}
		packag.getPrize(user);
		prizes.remove(packag);
		save();
		
		
		
		UserBuilder bd = new UserBuilder();

		UserPro data = bd.build(user);
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
	}
	
	private UserPrizePackage findPrizeById(int id) {
		for (UserPrizePackage p : prizes) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * 获取一串奖励，蓝港的
	 *
	 * @param id
	 */
	@Override
	public void getPrize(List<Prize> prize, long activityId ) {
		ThirdPartyPlatform e = ThirdPartyPlatformFactory.getThirdPartyPlatform();
		// e.bind();
		// e.checkToken(id, token, mac, clientType, unixTime, otherValue);
		// e.unbind();
		try {
			e.getPrize(ThirdPartyPlatformFactory.createRole(user), prize, activityId );

			SuperBagSnapsort b = new SuperBagSnapsort();
			b.snapsort(user);

			FighterSnapshoot f = new FighterSnapshoot(user);
			f.snapshoot();
			for( Prize p : prize){
				p.award(user.getPlayer());
			}

			b.snapsort(user);
			f.snapshoot();
			
			UserBuilder bd = new UserBuilder();
			UserPro data = bd.build(user);
			MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	/**
	 * 添加一个游戏自身的奖励，不包括蓝港的
	 * 
	 *
	 * @param type
	 * 			2   boss 		
	 * 			3	闯阵
	 * 			4	月卡
	 * 
	 */
	@Override
	public int addPrize(int type, List<Prize> prize, String desc, String title) {
		int endSecond = (int) (new DateTime().plusYears( 1 ).getMillis() / 1000);//不指定，就1年后过期
		UserPrizePackage pack = new UserPrizePackage(prize, desc,type,  title, endSecond );
		int key = maxId.incrementAndGet();
		pack.setId(key);
		prizes.add(pack);
		save();
		return key;
		
	}



	@Override
	public int addPrize(int type, String prizeStr, String title, String desc, int endSecond) {
		
		UserPrizePackage pack = new UserPrizePackage(prizeStr, desc, type, title, endSecond);
		int key = maxId.incrementAndGet();
		pack.setId(key);
		prizes.add(pack);
		save();
		return key;
		
	}

	/**
	 * 添加一个游戏自身的奖励，不包括蓝港的
	 * 
	 *
	 * @param type
	 * 			2   boss 		
	 * 			3	闯阵
	 * 			4	月卡
	 * 			5   黑市奖励
	 * 			6   限时通关
	 * 
	 */
	@Override
	public int addPrize(int type, String prizeStr, String desc, String title) {
		int endSecond = (int) (new DateTime().plusYears( 1 ).getMillis() / 1000);//不指定，就1年后过期
		return addPrize(type, prizeStr,  title, desc,endSecond);
	}
	
	public static void main(String[] args) {
		List<String> sss = Lists.newArrayList();
		for( int i = 0; i< 10; i++){
			sss.add( i + "");
		}
		
		Iterator<String> iterator = sss.iterator();
		while( iterator.hasNext() ){
			String t = iterator.next();
			if( t.equals( "1") || t.equals("2") ){
				iterator.remove();
			}
		}
//		System.out.println( sss );
		for (String s : sss) {
			if( s.equals( "1") || s.equals("2") ){
				sss.remove( s );
			}
		}
		System.out.println( sss );
	}


	@Override
	public boolean isGotPrize(int id) {
		for( UserPrizePackage p : prizes){
			if( p.getId() == id ){
				return false;
			}
		}
		return true;
	}
}
