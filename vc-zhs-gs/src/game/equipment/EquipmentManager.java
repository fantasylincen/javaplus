package game.equipment;

import game.award.AwardType;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.fighter.Hero;
import game.log.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import manager.DWType;
import config.equipment.EquipmentTemplet;
import config.equipment.EquipmentTempletCfg;
import config.fighter.Professional;
import config.vip.VipInfoTemplet;
import config.vip.VipInfoTempletCfg;
import define.DefaultCfg;
import define.SystemCfg;
import user.UserInfo;
import util.ErrorCode;

/**
 * 装备管理中心
 * @author DXF
 *
 */
public class EquipmentManager {
	
	private UserInfo									user;
	private final EquipmentDataProvider 				db 				= EquipmentDataProvider.getInstance();	
	
	private ConcurrentHashMap<Integer,EquipmentInfo> 	equLists;
	private EquipmentSynInfo							equSynInfo;
	
	public EquipmentManager( UserInfo user )
	{
		this.user 	= user;
		equLists	= db.get( user );
		equSynInfo	= db.getSyn( user );
		if( equSynInfo == null ){
			equSynInfo = new EquipmentSynInfo( user );
			db.addSyn( user, equSynInfo );
		}
	}
	
	public EquipmentInfo get( int uid ){
		return equLists.get( uid );
	}
	
	public List<EquipmentInfo> getLists() {
		List<EquipmentInfo> list = new ArrayList<EquipmentInfo>();
		for( EquipmentInfo e : equLists.values() )
			list.add( e );
		return list;
	}
	
	public EquipmentSynInfo getEquSynInfo() {
		return equSynInfo;
	}
	
	/**
	 * 创建一件装备
	 * @param nId
	 * @param colour
	 * @return
	 */
	public int create( int nId ){
		EquipmentTemplet templet 		= EquipmentTempletCfg.get(nId);
		if( templet == null ){
			Logs.error( user, "创建装备出错 nId=" + nId + " 不存在!" );
			return -1;
		}
		return executeCreate( templet );
	}
	
	/**
	 * 主要是复制一件装备 不用刷新数据库
	 * @param e
	 */
	public int create( EquipmentInfo e ) {
		
		if( equLists.values().size() >= user.getEquipbagCapacity() ){
			Logs.error( user, "创建英雄失败  英雄背包已满" );
			return -1;
		}
		
		equLists.putIfAbsent( e.getUID(), e );
		return e.getUID();
	}
	
	private int executeCreate( EquipmentTemplet templet ){
		
		if( equLists.values().size() >= user.getEquipbagCapacity() ){
			Logs.error( user, "创建英雄失败  英雄背包已满" );
			return -1;
		}
		
		EquipmentInfo equ = new EquipmentInfo( user.getBasisUniqueID().EQUIPINFO_ID(), templet );
		if( db.create( user, equ ) == 0 ){
			equLists.putIfAbsent( equ.getUID(), equ );
			updateClient( equ );
		}else{
			Logs.error( user, "创建装备失败  nid=" + templet );
			return -1;
		}
		return equ.getUID();
	}

	// 更新前端
	private void updateClient( EquipmentInfo e ) {
		List<EquipmentInfo> list = new ArrayList<EquipmentInfo>();
		list.add(e);
		UpdateManager.instance.update( user, UpdateType.U_90, list );
	}

	/**
	 * 操作一件装备
	 * @param heroID
	 * @param equID
	 * @return
	 */
	public ErrorCode handle( int heroID, int equID, byte type ) {
		
		Hero hero = user.getHeroManager().getHero( heroID );
		if( hero == null ){
			Logs.error( user, "穿戴装备出错  英雄ID=" + heroID + "不存在!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		EquipmentInfo equip = get( equID );
		if( equip == null ){
			Logs.error( user, "穿戴装备出错  装备ID=" + equID + "不存在!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		// 检查等级
		if( hero.getLevel() < equip.getTemplet().getNeedLevel() ){
			Logs.error( user, "穿戴装备出错  装备限制等级不足!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		// 检查对应职业 
		if( equip.getTemplet().getRestrictJob() != Professional.ALL &&
				equip.getTemplet().getRestrictJob() != hero.getProfessional() ) {
			Logs.error( user, "穿戴装备出错  装备限制职业不匹配!" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		// 获取装备该英雄的装备栏
		EquipmentBar bar 	= hero.getEquBar();
		
		// 获取装备位置
		byte pos			= (byte) (equip.getTemplet().getType().toNumber() - 1);
		
		// 更新前端列表
		List<EquipmentInfo> els 	= new ArrayList<EquipmentInfo>();
		List<Hero> hls				= new ArrayList<Hero>();
		
		if( type == 1 ) // 表示穿戴
		{
			// 以前的装备
			EquipmentInfo beEqu	= bar.get( pos );
			if( beEqu != null  ){
				beEqu.setTheirHeroID( -1 );
				els.add( beEqu );
				// 刷新数据库
				db.updata( user, beEqu );
			}
			
			// 现在装备的以前拥有者
			int hID = equip.getTheirHeroID();
			if( hID != -1 ){
				Hero h = user.getHeroManager().getHero( hID );
				h.getEquBar().set( pos, null );
				// 刷新数据库
				user.getHeroManager().update( h.getUID() );
			}
			
			bar.set( pos, equip );
			equip.setTheirHeroID( heroID );
			
		}else { // 表示卸下
			
			if( bar.get( pos ) == null ){
				Logs.error( user, "卸下装备出错 位置=" + pos + " 没有装备!" );
				return ErrorCode.UNKNOW_ERROR;
			}
			if( bar.get(pos).getUID() != equID ) {
				Logs.error( user, "卸下装备出错 该位置装备=" + bar.get(pos).getUID() + " 不等于要卸下装备=" + equID );
				return ErrorCode.UNKNOW_ERROR;
			}
			
			// 执行卸下
			bar.set( pos, null );
			equip.setTheirHeroID( -1 );
		}
		
		// 结算一下 英雄属性
		hero.settlementPropertyToEquip( );
		
		els.add( equip );
		hls.add( hero );
		UpdateManager.instance.update( user, UpdateType.U_92, els );
		UpdateManager.instance.update( user, UpdateType.U_102, hls );
		UpdateManager.instance.update( user, UpdateType.U_5 );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 出售装备
	 * @param list
	 * @return
	 */
	public ErrorCode sell( List<EquipmentInfo> list ) {
		
		int sellPrice 	= 0;
		
		for( EquipmentInfo e : list ){
			sellPrice	+= e.getColour().getSellPrice();
			remove( e.getUID() );
		}
		
		// 添加金币
		user.changeAward( AwardType.CASH, sellPrice, "出售装备获得", DWType.MISCELLANEOUS );
		UpdateManager.instance.update( user, UpdateType.U_3 );
		
		UpdateManager.instance.update( user, UpdateType.U_91, list );
		
		return ErrorCode.SUCCESS;
	}

	/**
	 * 合成
	 * @param xxE
	 * @param xxL
	 * @return
	 */
	public ErrorCode synthesis( EquipmentInfo xxE, List<EquipmentInfo> xxL ) {
		
		if( !xxE.isSynthesis() ){
			Logs.error( user, xxE.getTemplet().getName() + " nid=" + xxE.getNID() + " 该装备不能合成！" );
			return ErrorCode.UNKNOW_ERROR;
		}
		
		// 设置合成信息
		equSynInfo.setData( xxE, xxL );
		
		// 然后删除掉所有参与合成的装备
		remove( xxE.getUID() );
		for( EquipmentInfo e : xxL )
			remove( e.getUID() );
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 合成 立即完成
	 * @return
	 */
	public ErrorCode synthesisAtonceCompleted() {
		
		// 先获得应该扣的水晶数
		int needGold 		= equSynInfo.getAtonceCompletedCash();
		// 看是不是有VIP打折
		VipInfoTemplet v	= VipInfoTempletCfg.get( user.getVipLevel() );
		if( v != null && v.getEquDiscount() != 0 )
			needGold		*= v.getEquDiscount() * 0.1;
		
		if( SystemCfg.PLATFORM.equals( "DZ" ) ) needGold *= DefaultCfg.DZ_CONSUMPTION_RATIO;
		if( user.changeAward( AwardType.GOLD, -needGold, "装备合成 立即完成 消耗", DWType.MISCELLANEOUS ) == -1 ) 
			return ErrorCode.USER_GOLD_NOT_ENOUTH;
		
		UpdateManager.instance.update( user, UpdateType.U_4 );
		
		// 这里不管他时间是否已经完成 无脑给他完成
		equSynInfo.completed();
		
		return ErrorCode.SUCCESS;
	}
	
	public boolean remove( int id ){
		
		EquipmentInfo e 	= equLists.remove( id );
		
		if( e == null ) return true;
		
		if( e.getTheirHeroID() != -1 ){
			Hero h 			= user.getHeroManager().getHero( e.getTheirHeroID() );
			h.getEquBar().remove( id );
			// 结算一下 英雄属性
			h.settlementPropertyToEquip( );
			// 更新前端
			List<Hero> hls = new ArrayList<Hero>(); hls.add( h );
			UpdateManager.instance.update( user, UpdateType.U_102, hls );
			UpdateManager.instance.update( user, UpdateType.U_5 );
			// 刷新数据库
			user.getHeroManager().addUpdata( h.getUID() );
		}
		
		return true;
	}
	
	/** 刷新数据库 
	 * @param equID */
	public void updata( int equID ) {
		EquipmentInfo e = get( equID );
		if( e != null )
			db.updata( user, e );
	}

	/** 删除数据库信息  */
	public void remove( List<EquipmentInfo> list ) {
		db.remove( user, list );
	}
	/** 将已删除的装备 取回来 */
	public void getBack( List<EquipmentInfo> list ) {
		db.getback( user, list );
	}

	public void updataSyn() {
		db.updataSyn( user, equSynInfo );
	}
	
	public void updateToList( List<EquipmentInfo> list ){
		db.updataToList( user, list );
	}

	/**
	 * 将这些装备放入背包
	 * @param list
	 */
	public void demountToList( List<EquipmentInfo> list ) {
		
		for( EquipmentInfo e : list ){
			EquipmentInfo x = get( e.getUID() );
			x.setTheirHeroID( -1 );
		}
		
		updateToList( list );
		
		UpdateManager.instance.update( user, UpdateType.U_92, list );
	}

	public String toString() {
		StringBuilder content = new StringBuilder();
		content.append( "[ " + equLists.size() + "/" + user.getEquipbagCapacity() + " ]" ).append( "\r\n" );
		for( EquipmentInfo e : equLists.values() ){
			content.append( e.toString() ).append( "\r\n" );
		}
		return content.toString();
	}

}

