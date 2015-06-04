package game.hero;

import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.FighterBase;
import game.fighter.Hero;
import game.growup.Colour;
import game.growup.EvolutionInfo;
import game.growup.UpgradeInfo;
import game.growup.Quality;
import game.log.Logs;
import game.talent.TalentBase;
import game.talent.TalentType;
import game.team.TeamHero;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import manager.DWType;
import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;
import config.luckydraw.LuckydrawTemplet;
import user.UserInfo;
import util.ErrorCode;

/**
 * 玩家英雄管理类
 * @author DXF
 *
 */
public class HeroManager {

	private UserInfo									user;
	private final HeroDataProvider 						db = HeroDataProvider.getInstance();
	
	
	/** 玩家所有英雄 */
	private ConcurrentHashMap<Integer,Hero>				allHeroList;
	
//	private HeroPageControl								pageControl;
	
	// 删除列表
	private List<Hero>									deleteLists = new ArrayList<Hero>();
	// 创建列表
	private List<LuckydrawTemplet>						createLists	= new ArrayList<LuckydrawTemplet>();
	// 更新列表
	private List<Hero> 									updataLists	= new ArrayList<Hero>();
	
	public HeroManager( UserInfo user ) {
		super();
		this.user 	= user;
		allHeroList	= db.getAllHero( user );
//		pageControl	= new HeroPageControl( allHeroList, user );
	}
//	public HeroPageControl getPageControl() {
//		return pageControl;
//	}
	
	/**
	 * 以list的形式返回所有英雄数据
	 * @return
	 */
	public List<Hero> getLists() {
		
		List<Hero> list = new ArrayList<Hero>();
		
		for( Hero h : allHeroList.values() )
			list.add( h );
			
		return list;
	}
	
	/**
	 * 创建一个英雄
	 * @param templetId (英雄表格ID)
	 * @param intiQuality (英雄初始品质 默认Colour.GREEN)
	 * @param level (英雄初始等级  默认1)
	 * @param qlevel (英雄初始品质等级  默认0)
	 * @param isAnencephalyAdd (是否无脑加 true需要判断背包 false不需要判断背包)
	 * @return
	 */
	public int create( int templetId, Colour intiQuality, short level, byte qlevel, boolean isAnencephalyAdd )
	{
		
		if( isAnencephalyAdd && allHeroList.values().size() >= user.getBagCapacity() ){
			Logs.error( user, "创建英雄失败  英雄背包已满" );
			return -1;
		}
		
		HeroTemplet templet = HeroTempletCfg.getById( templetId );
		
		// 检查一下 这个ID 在表格上是否存在
		if( templet == null ){
			Logs.error( user, "创建英雄失败  找不到ID=" + templetId );
			return -1;
		}
		
		if( intiQuality == null ) intiQuality = Colour.PURPLE;
		if( intiQuality.toNumber() > templet.qualityMax.toNumber() )
			intiQuality			= templet.qualityMax;
		
		// 如果等级小于1 那么就等于1
		level 			= level < 1 ? 1 : level;
		qlevel 			= qlevel < 0 ? 0 : qlevel;
		byte qMaxLevel 	= (byte) HeroGrowupFormula.actualNumber[intiQuality.toNumber()].length;
		qlevel			= (byte) (qlevel >= qMaxLevel ? qMaxLevel - 1 : qlevel) ;
		
		// 算出品质
		Quality quality = new Quality( intiQuality, templet.qualityMax, qlevel  );
		
		Hero hero 		= new Hero( templet, user.getBasisUniqueID().HEROINFO_ID(), level, quality );
		
		// 先加入到数据库
		if( db.add( user, hero ) == 0 ){
			hero.settlementPropertyToTalent( user.getTalentManager().getTalents() ); // 这里要结算一下天赋信息
			allHeroList.putIfAbsent( hero.getUID(), hero );
		}
		
		return hero.getUID();
	}
	
	/**
	 * 专门为祈福 做一个创建英雄
	 * @param list
	 */
	public void create( List<LuckydrawTemplet> list ) {
		createLists.addAll( list );
		createLists.add( null );
	}
	
	/**
	 * 背包是否已满
	 */
	public ErrorCode getIsBagFull(){
		return allHeroList.values().size() >= user.getBagCapacity() ? ErrorCode.BAG_IS_FULL : ErrorCode.SUCCESS; 
	}
	
	/**
	 * 获得英雄
	 * @param id
	 * @return
	 */
	public FighterBase getFighter( int id ) {
		return allHeroList.get( id );
	}
	/**
	 * 获得英雄
	 * @param id
	 * @return
	 */
	public Hero getHero( int id ) {
		return allHeroList.get( id );
	}
	
	/**
	 * 复杂一个英雄出来
	 * @param suid
	 * @return
	 */
	public Hero cloneHero( int id ) {
		
		Hero hero = allHeroList.get( id );
		if( hero == null ) return null;
		
		return new Hero( hero );
	}
	
	/**
	 * 给当前出战英雄 添加经验
	 * @param exp
	 */
	public void addExp( int exp ) 
	{
		for( TeamHero team : user.getTeamManager().getTeam() ){
			
			if( !addExp( exp, team.getUId() ) )
				return;
		}
	}
	
	/**
	 * 给指定英雄添加经验
	 * @param exp
	 * @param id
	 */
	public boolean addExp( int exp, int id )
	{
		Hero h = allHeroList.get( id );
		
		if( h == null ){
			Logs.error( user, "给英雄添加经验 出错  英雄数据库没有 UID=" + id + " 这个英雄");
			return false;
		}
		
		if( h.addExp( exp ) > 0 )
			UpdateManager.instance.update( user, UpdateType.U_2 );
		
		db.upData(  user, h  );
		
		// 更新 到前端
		List<Hero> temp = new ArrayList<Hero>();
		temp.add( h );
		UpdateManager.instance.update( user, UpdateType.U_102, temp );
		
		return true;
	}


	/**
	 * 删除指定 英雄
	 * @param uid
	 */
	public void remove( int uid , String explain ) 
	{
		Hero h = allHeroList.remove( uid );
		
		if( h == null )
			return;
		
		deleteLists.add( h );
//		db.remove(  user, h  );
		
		// 如果有装备
		if( !h.getEquBar().isEmpty() ){
			// 将装备 全部放入背包
			user.getEquipmentManager().demountToList( h.getEquBar().get() );
			// 将身上的装备 全部清空
			h.demountEquip();
		}
		
		// 然后还要删除掉 团队里面的英雄  如果有
		user.getTeamManager().remove( h.getUID() );
		
		Logs.debug( user, explain + "(UID=" + h.getUID() + ")" );
	}
	
	/** 根据天赋信息  刷新所有英雄的 数据 */
	public void updataPropertyToTalent() {
		ConcurrentHashMap<TalentType,TalentBase> maps = user.getTalentManager().getTalents();
		for( Hero hero : allHeroList.values() ){
			hero.settlementPropertyToTalent( maps );
		}
	}
	/** 根据段位信息  刷新所有英雄的 数据 */
	public void updataPropertyToDangrad() {
		for( Hero hero : allHeroList.values() ){
			hero.settlementPropertyToDangrad( user.getDanGradingManager().getInfo().danGrad() );
		}
	}
	
	/**
	 * 吞噬英雄 升级
	 * @param lordHero
	 * @param list
	 * @param info
	 * @return
	 */
	public ErrorCode upgerade( Hero lordHero, List<Hero> list, UpgradeInfo info ) {
		
		if( user.changeAward(AwardType.CASH, -info.needGold(), "升级英雄 消耗金币", DWType.MISCELLANEOUS) == -1 )
			return ErrorCode.USER_CASH_NOT_ENOUTH;
		
		boolean isUpdate = false;
		// 添加经验
		if( lordHero.addExp( info.getExp() ) > 0 )
			isUpdate = true;
		
		// 技能升级
		if( lordHero.upgeradeSkill( info.skillOdds() ) )
			isUpdate = true;
		
		if( isUpdate )
			UpdateManager.instance.update( user, UpdateType.U_5 );
		
		// 刷新数据库  已在外层更新
//		db.upData(  user, lordHero  );
				
		// 删除被吞噬英雄
		for( Hero h : list )
			remove( h.getUID(), "吞噬-英雄被删除" );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 开始进化
	 * @param hero
	 * @param list 
	 * @param info
	 * @return
	 */
	public ErrorCode startEvolution( Hero hero, List<Hero> list, EvolutionInfo info ) {
		
		if( user.changeAward(AwardType.CASH, -info.needGold, "英雄进化 消耗金币", DWType.MISCELLANEOUS) == -1 )
			return ErrorCode.USER_CASH_NOT_ENOUTH;
		
		// 前面检查完毕后 就开始进化
		hero.evolution();
		
		// 技能等级清零 如果是高级和初级切换才清零
		if( hero.getQuality().getLevel() <= 1 )
			hero.getSkillAttack().setLevel( (byte)1 );
		
		// 刷新数据库   已在外层更新
//		db.upData(  user, hero  );
		
		// 删除材料英雄
		if( info.needTrophy != 0 ){
			user.changeAward( AwardType.TROPHY, -info.needTrophy, "英雄进化 消耗奖杯", DWType.MISCELLANEOUS );
			UpdateManager.instance.update( user, UpdateType.U_29 );
		}else{
			for( Hero h : list )
				remove( h.getUID(), "进化-被当做材料删除" );
		}
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 学习队长技能
	 * @param uid
	 * @param nid
	 */
	public void learnCaptainSkill( int uid, int nid ) {
		
		Hero hero = allHeroList.get( uid );
		if( hero == null ) return;
		if( hero.getCaptainSkill().getID() == nid ) return;
		
		hero.getCaptainSkill().setSkill( nid );
		
		// 刷新数据库
//		db.upData(  user, hero  );
	}
	
	/**
	 * 出售英雄
	 * @param list
	 * @return
	 */
	public ErrorCode sell( List<Hero> list ) {
		
		// 最终出售金币
		int sellMoney = 0;
		
		// 这里算出最终金币 然后顺便删除
		for( Hero hero : list ){
			int curMoney 	= hero.getExtraValue(0);
			sellMoney 		+= curMoney;
			remove( hero.getUID(), "出售 英雄被删除  出售金币=" + curMoney );
		}
		
		user.changeAward( AwardType.CASH, sellMoney, "出售英雄 获得金币", DWType.MISCELLANEOUS );
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 是否可携带该英雄
	 * @param uID
	 * @return
	 */
	public boolean isCarryLevel( int uID ) {
		
		Hero h = getHero( uID );
		if( h == null ) return false;
		
		short level = h.getCarryLevel();
		
		return level <= user.getLevel();
	}
	
	/**
	 * 刷新数据库
	 * @param uid
	 */
	public void update( int uid ){
		Hero hero = getHero( uid );
		if( hero != null )
			db.upData(  user, hero  );
	}
	
	public void addUpdata( int uid ){
		Hero hero = getHero( uid );
		if( hero != null )
			updataLists.add( hero );
	}
	
//	private List<Hero> tempLists = new ArrayList<Hero>();
	// 线程 用来跑 数据库
	public void runnable(){
		
		if( !deleteLists.isEmpty() ){
			Hero h = deleteLists.get(0);
			if( db.remove(  user, h  ) )
				deleteLists.remove(0);
		}
		
//		if( !createLists.isEmpty() ){
//			
//			LuckydrawTemplet templet = createLists.remove(0);
//			
//			if( templet == null ){
//				if( getIsBagFull() == ErrorCode.BAG_IS_FULL )
//					createLists.clear();
//			}else{
//				int id = create( templet.getNid(), templet.getQuality().getColour(),(short)1, templet.getQuality().getLevel(), false );
//				if( id != -1 )
//					tempLists.add( getHero(id) );
//			}
//			
//		}else if( !tempLists.isEmpty() ){
//			
//			UpdateManager.instance.update( user, UpdateType.U_100, tempLists );
//			tempLists.clear();
//		}
		
		if( !updataLists.isEmpty() ){
			Hero h = updataLists.remove(0);
			db.upData(  user, h  );
		}
		
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append( "[ " + allHeroList.size() + "/" + user.getBagCapacity() + " ]" ).append( "\r\n" );
		for( Hero h : allHeroList.values() ){
			content.append( h.toString() ).append( "\r\n" );
		}
		return content.toString();
	}
	
	public static void main( String[] s ){
		
//		ConcurrentHashMap<Integer,Hero>			allHeroList = new ConcurrentHashMap<Integer, Hero>();
//		
//		for( int i = 0; i < 10; i++ ){
//			Hero h = new Hero(null, i, (short) 0, null);
//			
//			allHeroList.putIfAbsent(h.getUID(), h);
//		}
//		
//		System.out.println( allHeroList );
//		System.out.println( allHeroList.size() );
//		
//		Hero hero = allHeroList.remove( (int)1 );
//		System.out.println( allHeroList );
//		System.out.println( allHeroList.size() );
//		System.out.println( hero.getUID() );
		
		// 给某个玩家 刷英雄 
		HeroTempletCfg.init();
		int name = 1011655;
		UserInfo user = new UserInfo( null, name );
		
		for( int i = 30000; i < 30050; i++ ){
			user.getHeroManager().create( i, Colour.GREEN, (short)20, (byte)0, false );
		}
		
//		for( int i = 0; i < 95; i++ ){
//			user.getHeroManager().create( 10094, Colour.GREEN, (short)22, (byte)0 );
//		}
		
		System.out.println( "添加英雄完成！" );
	}


}

