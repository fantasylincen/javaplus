package cn.mxz.shenmo;

import java.util.List;

import message.S;

import org.joda.time.LocalDate;

import cn.javaplus.util.Util;
import cn.mxz.RandomEventBaseTemplet;
import cn.mxz.RandomEventBaseTempletConfig;
import cn.mxz.RandomEventSHTempletConfig;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.IllegalOperationException;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.friend.FriendManager;
import cn.mxz.script.Script;
import cn.mxz.shenmo.battle.ShemmoWarSituation;
import cn.mxz.shenmo.battle.ShenMoBattleImpl;
import cn.mxz.shenmo.battle.ShenmoBattle;
import cn.mxz.user.team.Formation;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.BossDamageData;
import db.domain.BossData;
import define.D;

/**
 * 玩家的神魔信息
 * @author Administrator
 *
 */
public class UserShenmo {
	private final City 			user;
	private GongdePrizes		gongdePrize;

	public UserShenmo( City user) {
		this.user = user;
		gongdePrize = new GongdePrizes(user);
	}

	public ShenmoItem createShenmo(){
		return ShenmoManager.INSTANCE.create( user );
	}

	/**
	 * 查找与自己相关的所有神魔信息
	 * @return
	 */
	public List<ShenmoItem> getOurShenmo(){
		List<ShenmoItem> list = Lists.newArrayList();

		List<ShenmoItem> all = Lists.newArrayList(ShenmoManager.INSTANCE.getAll());
		for( ShenmoItem item : all ){//遍历所有的神魔信息
			City founder = CityFactory.getCity( item.getFounder() );
			if( founder.getId().equals( user.getId()) ){
				list.add( item );
			}
		}
		
		/**
		 * 如果是玩家第一次请求神魔，并且不存在神魔，那么帮助玩家自动生成一个
		 * 为了引导用，有没有问题，需要确定
		 */
		if( list.size() == 0 && user.getUserCounterHistory().get(CounterKey.SHENMO_HAS_FIRST_SHOW ) == 0 ){
			list.add( ShenmoManager.INSTANCE.create( user ) );
		}
		
		if( user.getUserCounterHistory().get(CounterKey.SHENMO_HAS_FIRST_SHOW ) == 1 ){//只有击杀过神魔之后，才有资格看到朋友的神魔
			//朋友发现的神魔，新规则只允许显示指定数量的朋友发现的神魔
			List<ShenmoItem> friendFound = Lists.newArrayList();
			for( ShenmoItem item : all ){//遍历所有的神魔信息
	//			if( item.getBossData().getLevel() == 1 ){//1级的神魔引导用，不能被分享
	//				continue;
	//			}
				City founder = CityFactory.getCity( item.getFounder() );
				if( isFriend(founder) && item.getBossData().getLevel() != 1 ){//1级的神魔，不能被分享
					friendFound.add( item );
					if( friendFound.size() > D.SHENMO_MAX_COUNT ){
						break;
					}
				}
			}
			list.addAll( friendFound);
			
		}
		return list;
	}



	private ShenmoItem getShenmoById( int shenmoId ){

		for( ShenmoItem item : getOurShenmo() ){//遍历与自己有关的所有的神魔信息
			if( item.getBossId() == shenmoId ){
				return item;
			}
		}
		return null;
	}

	/**
	 * 用户领奖
	 * @param shenmoId
	 */
	public String getPrize( int shenmoId ){
		ShenmoItem item = getShenmoById(shenmoId);//已经进行了有效性检测
		if( item == null ){
			throw new SureIllegalOperationException( user.getId() + "没有神魔信息不能领奖,神魔id:" + shenmoId );
		}
		BossDamageData bdd = item.getUserData( user.getId() );
		if( bdd == null ){//从未攻击过此boss
			System.err.println( user.getId() + "从未攻击过此神魔" );
			return null;
		}

		int damage = bdd.getDamage();

		if( /**damage == 0 ||**/ !item.isEnd() || bdd.getAward() ){//伤害为0，是有可能领奖的，比如发现者
			System.err.println( "玩家" + user.getId() + "已经领奖了---" + shenmoId );
			return null;
		}
		String prize = "";
		if( item.isDeath() ){
			prize = getWinPrize( item, damage );
		}
		else{
//			prize = getLosePrize(item, damage);
		}

		bdd.setAward( true );
		ShenmoManager.INSTANCE.saveToDB( bdd );
		return prize;
	}

//	private String getLosePrize(ShenmoItem item, int damage ) {
//		int gongde = getGongde(item, damage, 2);
//		user.getPlayer().add( PlayerProperty.SHOU_HUN, 1 );
//		return gongde+"";
//	}



	/**
	 * 根据RandomEventSH.xls计算参加了击杀神魔的玩家应该获得多少兽魂
	 * ｖｉｐ额外得５０％	 * 
	 * 周1、6、7这3天额外得５０％
	 * 
	 * @return RandomEventSH.xls
	 */
	private int calcSoHun( ShenmoItem item ){
		int totalPlayerCount = item.getDamageList().size();
		int index = calcLevel( RandomEventSHTempletConfig.getArrayByNum(), totalPlayerCount, 0 );
//		for( RandomEventSHTemplet templet : RandomEventSHTempletConfig.getAll() ){
//			
//		}
		int baseShouHun = RandomEventSHTempletConfig.get(index).getShouHun();
		int shouHun = baseShouHun;
		
		int vipLevel = user.getPlayer().getVipLevel();
		int vipPrivilege = VipPrivilegeTempletConfig.get( (byte) vipLevel ).getMonsterSoul();
		if( vipPrivilege == 1){
			shouHun +=  baseShouHun * 0.5f;
		}
		
		LocalDate now = new LocalDate();
		if( now.getDayOfWeek() == 1 || now.getDayOfWeek() == 6 || now.getDayOfWeek() == 7 ){
			shouHun +=  baseShouHun * 0.5f;
		}
		return shouHun;		
	}
	
	/**
	 * 根据区间获取相应的索引，比如通过经验算等级
	 * @param data
	 * @param input
	 * @param beginWith
	 * @return
	 */
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
	
	/**
	 * 1、按照伤害比例获得功德值
	 * 2、只要有伤害，通过计算获取一定数量的兽魂
	 * 3、mvp，击杀者，发现者获得额外奖励
	 *
	 * @param item
	 */
	private String getWinPrize(ShenmoItem item, int damage ) {
//		BossData bd = item.getBossData();
//		int shenmoId = item.getTypeId();

		//item.getDamageList().size();伤害总人数 
		int shouhun = calcSoHun( item );
		user.getPlayer().add( PlayerProperty.SHOU_HUN, shouhun );

		int gongde = getGongde(item, damage, 2);

		String result = shouhun+ "," + gongde + ",";//暂时不要更新，除非前端也更新了

		
		//特殊奖励
//		List<Prize> prize = Lists.newArrayList();
		if( item.isMvp( user ) ){
			Prize p = calcPrize( item, 1 );//黄必胜同学确认正确性
			result += p.getId() + "," + p.getCount() + ",";
		}
		else{
			result += "0,0,";
		}
		if( item.isKiller( user ) ){
			Prize p = calcPrize( item, 2 );
			result += p.getId() + "," + p.getCount() + ",";
		}
		else{
			result += "0,0,";
		}
		if( item.isFounder( user ) )
		{
			Prize p = calcPrize( item, 3 );
			result += p.getId() + "," + p.getCount() + ",";
		}
		else{
			result += "0,0,";
		}
		return result;
	}

	/**
	 * 计算功德值，并且直接获取
	 * @param item
	 * @param damage
	 * @param type		1、每次攻击的时候结算功德值
	 * 					2、boss死亡后，结算总的功德值
	 * @return
	 * 				1：按照伤害值的百分比进行结算,乘以20%，不足100算作100
	 */
	private int getGongde(ShenmoItem item, int damage, int type ) {
		int gongde = 0;
//		if( type == 1 ){
//			gongde = damage / 1000;
//		}
//		else{
			int hpMax =  item.getFighter().getHpMax();
			float rate = (float)damage / hpMax;
			
			RandomEventBaseTemplet templet = RandomEventBaseTempletConfig.get( item.getBossData().getLevel() );
			gongde = (int) (templet.getMerit() * rate);

			if( type != 1 ){//神魔死亡后的最后结算
				gongde *= D.SHENGMO_RATE;
				//gongde = gongde < 100?100:gongde;黄必生同学要求去掉
			}

		user.getUserCounter().add( CounterKey.GONG_DE, gongde );//获取功德值
		
		System.out.println( "获取的共得之   " + gongde );
		return gongde;
	}


	/**
	 * @param item		神魔的模板id
	 * @param type			1、mvp  2、killer 3、finder		
	 * @return
	 */
	private Prize calcPrize(  final ShenmoItem item, int type ) {
//		Collection<RandomEventChipTemplet>  filterCollection =
//		        Collections2.filter( RandomEventChipTempletConfig.getAll(), new Predicate<RandomEventChipTemplet>(){
//		    @Override
//		    public boolean apply(RandomEventChipTemplet templet) {
//
//		        return templet.getRappelzId() == templetId;
//		}});
//
//		List<RandomEventChipTemplet> list = Lists.newArrayList( filterCollection );
//
//		RandomEventChipTemplet r = Util.Random.getRandomOneByWeight( list, new WeightFetcher<RandomEventChipTemplet>() {
//			@Override
//			public Integer get(RandomEventChipTemplet t) {
//				return t.getWeight();
//			}
//		});
//
//		SimplePrize p = new SimplePrize( r.getId(), count );
//		p.award( user.getPlayer() );
		
//		UserPrizeSender s = user.getPrizeSender2();
//		List<Prize> b = s.buildPrizes("");
//		
//		for (Prize prize : b) {
//			prize.award(user.getPlayer());
//		}
		
		RandomEventBaseTemplet templet = RandomEventBaseTempletConfig.get( item.getBossData().getLevel() );
		Util.Exception.checkNull(templet, "神魔击杀配置表未找到"); //神魔的等级只会是5,10,15这样的
		String strPrize;
		if( type == 1 ){
			strPrize = templet.getMvpReward();
		}
		else if( type == 2 ){
			strPrize = templet.getKillReward();
		}
		else{
			strPrize = templet.getFindReward();
		}
		if( strPrize == null || strPrize.isEmpty() ){
			return new SimplePrize(0, 0);
		}
//		UserPrizeSender s = user.getPrizeSender1();
//		List<Prize> b = s.buildPrizes( strPrize );
//		
		double rate = Script.MoShen.getShouHun( item.getBossData().getLevel(),user.getLevel());
		int id = Integer.parseInt( strPrize.split(",")[0] );
		int count = (int) (Integer.parseInt( strPrize.split(",")[1]) * rate);
		
		int vipLevel = user.getPlayer().getVipLevel();
		int vipPrivilege = VipPrivilegeTempletConfig.get( (byte) vipLevel ).getMonsterSoul();
		if( vipPrivilege == 1){
			count +=  count * 0.5f;
		}
		
		Prize result = new SimplePrize( id, count );
		user.getPrizeSender1().send( id + "," + count );//黄必生同学不得修改奖品数量@@！！修改前需通知修改
		
		
		
		
//		result.award( user.getPlayer() );
		return result;
//		for( Prize prize : b){
//			Prize p = new SimplePrize( pri, count)
//		}
//		
//		for (Prize prize : b) {
//			prize.
//			prize.award(user.getPlayer());
//		}
//		if( b.size() > 0  ){
//			return b.get(0);//只会有一个奖励，如果是多个奖励要修改程序
//		}
//		else{
//			return new SimplePrize(0, 0);
//		}


//		return r.getId();
//		RandomEventChipTemplet chip = RandomEventChipTempletConfig.get(x)

//
//		PrizeSender s = PrizeSenderFactory.getPrizeSender2();
//		List<Prize> buildPrizes = s.buildPrizes(prize);
//		s.send( user.getPlayer(), prize);
//		return buildPrizes;
	}


	public ShemmoWarSituation fight( int shenmoId, boolean isFullFighting ){
		
		

		if( user.getUserCounterHistory().get(CounterKey.SHENMO_HAS_FIRST_SHOW ) == 0 ){
			user.getUserCounterHistory().set(CounterKey.SHENMO_HAS_FIRST_SHOW, 1 );
		}
		ShenmoItem item = getShenmoById(shenmoId);
		
		


		ShenmoFighter fighter = item.getFighter();
		
		if( fighter.getHpNow() <= 0 ){
//			throw new IllegalOperationException();
			throw new OperationFaildException(S.S10259);
		}
		
		BossDamageData userData = item.getUserData(user.getId() );
		if( userData == null ){
			userData = ShenmoManager.INSTANCE.createBossDamageData(shenmoId, user);
		}

		Formation formation = user.getFormation();
		PlayerCamp selected = formation.getSelected();


		if( isFullFighting ){
			user.getPlayer().reduce( PlayerProperty.POWER, D.SHENMO_FULL_FIGHT_POWER );
		} else{
			user.getPlayer().reduce( PlayerProperty.POWER, D.SHENMO_NORMAL_FIGHT_POWER );
		}


		ShenmoBattle battle = new ShenMoBattleImpl(selected, fighter, isFullFighting);
		int oldHp = fighter.getHpNow();
		battle.fighting();

		int damage = 0;
		if( fighter.getHpNow() < 0 ){
			damage = oldHp;
		}
		else{
			damage = oldHp - fighter.getHpNow();
		}

		updateDamage( userData, damage );
		DaoFactory.getBossDataDao().update( item.getBossData() );

		int gongde = getGongde(item, damage,1);

		if( battle.isWin() ){
			item.getBossData().setKiller( user.getId() );
			//TODO通知相关玩家领奖
			sendMsg( item.getBossData(), ShenmoMsgType.NEW_BOSS );
		} else {

		}

		ShemmoWarSituation situation = new ShemmoWarSituation(battle.getWarSituation(), gongde, damage );
		return situation;

	}




	private void sendMsg(BossData bossData, ShenmoMsgType newBoss) {
		// TODO 自动生成的方法存根

	}

	/**
	 * 更新用户对此神魔的伤害值
	 * @param shenmo
	 * @param damage
	 */
	private void updateDamage(BossDamageData bdd, int damage) {
		//BossDamageData userData = shenmo.getUserData();
		int oldDamage = bdd.getDamage();
		bdd.setDamage( oldDamage + damage );
		DaoFactory.getBossDamageDataDao().update( bdd );
	}


	private boolean isFriend( City finder ){

//		long t1 = System.currentTimeMillis();

		FriendManager friendManager = finder.getFriendManager();
		boolean friend = friendManager.isFriend( user.getId() );

//		long t2 = System.currentTimeMillis();
//		Debuger.debug("PromptManagerImpl.getYmsktz() ================" + (t2 - t1));
		return friend;
	}


	
	/**
	 * @return gongdePrize
	 */
	public GongdePrizes getGongdePrize() {
		return gongdePrize;
	}

	/**
	 * 判断是否存在可以战斗或者领奖的神魔，方便前端显示 “!号”
	 * @return
	 */
	public boolean hasPrizeOrShenmo() {
		for( ShenmoItem item : getOurShenmo() ){
			if( item.isEnd() ){
				//如果逃走了，神魔又没死，也不用给奖励所以再加一个if
				if( item.getHp() > 0 ){
					continue;
				}
				
				BossDamageData userData = item.getUserData( user.getId() );
				if( userData != null && userData.getDamage() > 0 && !userData.getAward() ){
					return true;
				}
			}
			else if( !item.isEnd() ){
				return true;
			}
		}
		
		return gongdePrize.showGongdeTips();
	}

	/**
	 * 获取最新神魔，方便客户端直接弹出
	 * @return
	 */
	public ShenmoItem getNewShenmo(){
		for( ShenmoItem item : getOurShenmo() ){
			if( !item.isEnd() && item.getFounder().equals( user.getId() ) ){
				return item;
			}
		}
		return null;
	}

	/**
	 * 是否存在ke
	 * @return
	 */
	public boolean hasCanFighter() {
		// TODO 自动生成的方法存根
		return false;
	}
	
	/**
	 * 扫荡时候能否触发神魔
	 * @return
	 */
	public boolean MoppingUpCanTrigger(){
		
		List<ShenmoItem> all = Lists.newArrayList(ShenmoManager.INSTANCE.getAll());
		for( ShenmoItem item : all ){//遍历所有的神魔信息
			City founder = CityFactory.getCity( item.getFounder() );
			if( founder.getId().equals( user.getId()) ){
				if( !item.isEnd() ){
					return false;
				}
			}
		}
		return true;
		
		
	}

	public static void main(String[] args) {

		LocalDate now = new LocalDate();
		System.out.println( now.getDayOfWeek() );
		for( int i = 0; i < 15; i++ ){ 
//		int totalPlayerCount = 5;
		int index = calcLevel( RandomEventSHTempletConfig.getArrayByNum(), i, 0 );
		System.out.print( i + " ：" );
//		
		System.out.println( RandomEventSHTempletConfig.get(index).getShouHun() );;
		}
	}


}
