package cn.mxz.formation;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.javaplus.util.Util.Random;
import cn.mxz.Attribute;
import cn.mxz.FormationBenchSpetTempletConfig;
import cn.mxz.FormationBenchTemplet;
import cn.mxz.FormationBenchTempletConfig;
import cn.mxz.SubstitutionArmyOpenTempletConfig;
import cn.mxz.city.City;
import cn.mxz.fighter.AttributeAddable;
import cn.mxz.fighter.AttributeEmpty;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;

import com.google.common.collect.Lists;

import db.dao.impl.BackupPositionDao;
import db.dao.impl.DaoFactory;
import db.domain.BackupPosition;
import db.domain.BackupPositionImpl;
import define.D;

/**
 * 替补阵型
 *
 * @author Administrator
 *
 */
public class AlternateFormation implements IFormationAddtion {

	private static final int JIA = 4;//甲级id
	private static final int YI = 3;//乙级id
	
	private static final int POSITION_BASE = 100;
	private static final int MAX_POSITION = 105;

	private List<BackupPosition> list = Lists.newArrayList();
	private final BackupPositionDao db = DaoFactory.getBackupPositionDao();
	private final City user;

	private AtomicInteger position = new AtomicInteger();
	private IFormationMove fm = new Move();

	AlternateFormation(City user) {
		this.user = user;
		this.list = db.findByUname(user.getId());
		position.set(POSITION_BASE + list.size());
		// if( list.size() == 0 ){
		// create();
		// create();
		// create();
		// create();
		// create();
		// }

	}

	/**
	 * 计算可以开启的替补位置
	 */
	private void createNew() {
		int count = calcLevel( SubstitutionArmyOpenTempletConfig.getArrayByLeadLv() , user.getLevel(), 1 );

		int newSize = count - list.size();
		if (newSize > 0) {
			for (int i = 0; i < newSize; i++) {
				create();
			}
		}

	}

	/**
	 * 当某些条件触发的时候，开启一个替补格子
	 */

	void create() {

		int pos = position.getAndIncrement();
		if (pos >= MAX_POSITION) {
			return;
		}

		BackupPosition bp = new BackupPositionImpl();
		bp.setUname(user.getId());
		bp.setHeroId(-1);// 这是个空位置
		bp.setPosition(pos);
		refresh(bp);
		db.add(bp);
		list.add(bp);
	}

	/**
	 * 计算每个替补位置对英雄的加成，包括2部分：<br/>
	 * 1、普通加成
	 *
	 * @param hero
	 * @return
	 */
	@Override
	public Attribute getAddition(Hero hero) {
		Attribute attr = new AttributeEmpty();
		for (BackupPosition bp : list) {
			if (bp.getHeroId() != -1) {
				calcAddtionByOnePos(bp, attr);
				// attr = AttributeCalculator.adding(attr,
				// hero.getTianMing().getAddition());
			}
		}

		return attr;
	}

	/**
	 * 刷新替补阵容一个格子的加成属性
	 *
	 * @param position
	 * @return
	 */
	boolean refresh(int position) {

		BackupPosition bp = get(position);
		if (bp == null) {
			return false;
		}
		// int needGold = D.REFRESH_BACKUP_POSITION;
		
//		String  needs = D.REFRESH_BACKUP_PROP + ",1|110022,18|110009,18|110002,18";
//		
//		NeedsFactory.getNeedsCheckerImpl2(needs).deduct(user);
//		try{
//		user.getBagAuto().remove(D.REFRESH_BACKUP_PROP, 1);
//		}catch( Exce)
		
		if( user.getBagAuto().getCount( D.REFRESH_BACKUP_PROP) >= 1 ){
			user.getBagAuto().remove(D.REFRESH_BACKUP_PROP, 1);
		}
		else {
			user.getPlayer().reduceGoldOrJinDing( 18 );
		}

		refresh( bp );
		db.update(bp);
		return true;
	}

	private BackupPosition get(int position) {
		for (BackupPosition bp : list) {
			if (bp.getPosition() == position) {
				return bp;
			}
		}
		return null;
	}

	/**
	 * 计算品质的算法，50（配置表）次必出乙级。100（配置表）次必出甲级，如果当前次数满足甲和乙两个品质，则选择甲品质，同时清空积累次数
	 * @return
	 */
	private int calcQuality(){
		int baseCount = FormationBenchSpetTempletConfig.get(JIA).getMustNumber();//甲级阵法必出次数
		int refreshCount = getCount(CounterKey.ALTERNATE_JIA );
		System.out.println( "甲级的刷新次数：" + refreshCount );
		if( refreshCount == baseCount - 1 ){//必出甲级
			clearRefreshCount( CounterKey.ALTERNATE_JIA );
			clearRefreshCount( CounterKey.ALTERNATE_YI );
			
			return JIA;
		}
//		addRefreshCount(CounterKey.ALTERNATE_JIA);
		
		baseCount = FormationBenchSpetTempletConfig.get(YI).getMustNumber();//乙级阵法必出次数
		refreshCount = getCount(CounterKey.ALTERNATE_YI );
		if( refreshCount == baseCount - 1 ){//必出乙级
			clearRefreshCount( CounterKey.ALTERNATE_YI );
			addRefreshCount( CounterKey.ALTERNATE_JIA );
			return YI;
		}
//		addRefreshCount(CounterKey.ALTERNATE_YI);
		
		int quality = FormationBenchSpetTempletConfig.getKeys().get(
				Random.getRandomIndex(FormationBenchSpetTempletConfig
						.getArrayByWeight()));
		
		
		if( quality == JIA ){
			clearRefreshCount( CounterKey.ALTERNATE_JIA );
			clearRefreshCount( CounterKey.ALTERNATE_YI );
		}
		else{
			addRefreshCount( CounterKey.ALTERNATE_JIA );
		}
		
		if( quality == YI ){
			clearRefreshCount( CounterKey.ALTERNATE_YI );
		}
		else{
			addRefreshCount( CounterKey.ALTERNATE_YI );
		}
		
		return quality;
	}
	
	/**
	 * 清空累计为抽到甲级或者乙级的抽奖次数
	 * 			
	 */
	private void clearRefreshCount(CounterKey key) {
		user.getUserCounterHistory().set( key,0 );
		
	}
	
	private void addRefreshCount(CounterKey key) {
		user.getUserCounterHistory().add( key,1 );
		
	}

	private int getCount( CounterKey key ){
		return user.getUserCounterHistory().get(key);
	}
	/**
	 * 刷新替补阵容一个格子的加成属性
	 *
	 * @param position
	 * @return
	 */
	private void refresh(BackupPosition bp) {
		int id = 0;
		int quality = calcQuality();
		
		while(true){
			
			List<FormationBenchTemplet> list = FormationBenchTempletConfig
					.findBySpet(quality);
			int index = Random.getRandomIndex(getWeight(list));
			FormationBenchTemplet t = list.get(index);

			//检测是否重复

			id = t.getId();
			if( !checkReduplicate( id ) ){

				break;
			}
		}


		bp.setTempletId( id );
	}

	/**
	 * 检测替补位置是否已经存在此种加成
	 * @param id	需要检测的加成id
	 * @return
	 */
	private boolean checkReduplicate(int id) {
		for (BackupPosition bp : list) {
			if( bp.getTempletId() == id ){
				return true;
			}
		}
		return false;
	}

	private int[] getWeight(List<FormationBenchTemplet> list) {
		int[] ret = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i).getWeight();
		}
		return ret;
	}

	/**
	 * 计算一个替补位对一个英雄的加成
	 *
	 * @param bp
	 * @param attr
	 */
	private void calcAddtionByOnePos(BackupPosition bp, Attribute attr) {

		FormationBenchTemplet t = FormationBenchTempletConfig.get(bp
				.getTempletId());
		Hero hero = user.getTeam().get(bp.getHeroId());

		 if(hero == null) {
			// 2014年1月25日 14:54:45 林岑加
			// new NullPointerException("用了传承功能后, 这里就报错了!").printStackTrace();
			System.err.println( bp.getHeroId() + "英雄不见了，是不是用了传承功能后？");
			return;
		 }

		int shenJia = Math.min( hero.getShenJia(), D.ALTERNATE_SHENJIA_MAX );


		float value = shenJia * t.getAddition();

		AdditionType at = AdditionType.fromNum(t.getNatureType());
		at.add((AttributeAddable) attr, (int) value);
	}

	List<BackupPosition> getAll() {
		createNew();
		return list;
	}

	int getPosition(Hero f) {

		for (BackupPosition bp : list) {
			if (bp.getHeroId() == f.getTypeId()) {
				return bp.getPosition();
			}
		}

		return -1;
	}

	IFormationMove get() {
		return fm;
	}

	private class Move implements IFormationMove {

		@Override
		public void remove(int pos) {
			BackupPosition bp = get(pos);
			if (bp == null) {
				return;
			}
			bp.setHeroId(-1);
			db.update(bp);
		}

		@Override
		public void put(int srcHeroId, int desPos) {
			BackupPosition bp = get(desPos);
			// if( bp != null ){故意让她报错
			bp.setHeroId(srcHeroId);
			db.update(bp);
			// }
		}

		@Override
		public boolean add(int srcHeroId, int desPos) {
			BackupPosition bp = get(desPos);
			// if( bp != null )故意让她报错，按道理不应该出现问题，外层已经过滤过此问题
			if (bp.getHeroId() != -1) {// 这个其实不需要，不过暂时不删，外层保证不会出现此位置有人的情况
				return false;
			}
			bp.setHeroId(srcHeroId);
			db.update(bp);
			return true;
		}
	}

	boolean isContain(int targetPos) {
		if (targetPos >= POSITION_BASE
				&& targetPos < POSITION_BASE + list.size()) {
			return true;
		}
		return false;
	}

	public Collection<? extends Hero> getFighters() {
		List<Hero> ls = Lists.newArrayList();
		for (BackupPosition bp : list) {
			int id = bp.getHeroId();
			Hero hero = user.getTeam().get(id);
			if (hero != null) {
				ls.add(hero);
			}
		}
		return ls;
	}
	
	 /**
     * 从一个数组中，找到输入相应的位置，典型应用是通过经验获取等级,允许输入数据超过数组的最大值,此时直接返回数组的数量<br/>
     * 举例如下<br/>
     * [0,10,20,50,100]，input=35
     * beginWith1=true      return 3，即玩家拥有35经验的时候，等级为<b>3</><br/>
     * beginWith1=false     return 2，即玩家拥有35经验的时候，等级为<b>2</><br/>
     *
     * @param data       等级数组数据  例如[0,10,20,50,100]
     * @param input      输入数据      例如   35
     * @param beginWith1 等级从0还是从1开始计算
     * @return 输入数据在数组中的位置
     */
	public int calcLevel( int[] data, int input, int beginWith ){
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
}
