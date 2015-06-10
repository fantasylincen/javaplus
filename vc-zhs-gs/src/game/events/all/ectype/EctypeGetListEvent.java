package game.events.all.ectype;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

import config.mission.EctypeType;
import config.mission.InstanceTemplet;
import config.mission.InstanceTempletCfg;
import define.DefaultCfg;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;
import util.UtilBase;
import game.activity.ActivityBase;
import game.activity.ActivityManager;
import game.activity.DragonBase;
import game.activity.ParticipationBase;
import game.ectype.EctypeBase;
import game.ectype.EctypeStartLevel;
import game.ectype.EliteCount;
import game.ectype.EliteCountBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "申请副本信息")
public class EctypeGetListEvent extends EventBase{

	
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte type 			= buf.get();
				
		ByteBuffer response = buildEmptyPackage( 256 );
		response.put( type );
		
		switch( type )
		{
		case 0: // 购买次数（暂时仅限英雄副本）
			{
//				synchronized (user) {
					
					byte count 		= buf.get();
					short mid		= buf.getShort();
					int pid			= buf.getInt();
					
					ErrorCode code 	= user.getEliteEctypeCountManager().buyCount( count, mid, pid );
					response.putShort( (short)code.ordinal() );
					
					if( code == ErrorCode.SUCCESS ){
						
						EliteCount ec = user.getEliteEctypeCountManager().get( mid, pid );
						if( ec == null ) {
							Logs.error( user, "在购买英雄副本次数 出错 ec=null" );
							return ;
						}
						response.putInt( pid );
						response.put( ec.m_nTodayCount );
						response.put( ec.m_nBuyCount );
					}
					
					sendPackage( user.getCon(), response );
//				}
			}
			return;
		case 1:// 请求主线副本进度
			{
//				synchronized (user) {
					
					byte ectypeType 	= buf.get();
					EctypeType eType	= EctypeType.fromNumber( ectypeType );
					if( eType == null ){
						Logs.error( user, "申请副本主线ID 出错 类型为NULL！" );
						return ;
					}
					
					// 这里就是主线  发最后一个副本ID 就行了 让前台自己去填补前面的副本
					EctypeBase ectype	= user.getEctypeManager().getEctype( eType );
					
					if( ectype == null ) return;
					
					response.putShort( ectype.getLastId() );
					
					// 下面是专为 精英副本 是否可以挑战 只做最后一个精英副本的判断  因为前面滴 都是可以挑战的
					boolean isChallenge = true;
					
					if( eType == EctypeType.ELITE ){
						InstanceTemplet templet = InstanceTempletCfg.getTempletById( ectype.getLastId() );
						// 获取普通副本记录列表
						EctypeBase common 		= user.getEctypeManager().getEctype( EctypeType.COMMON );
						// 判断 这个副本是否通过
						isChallenge 			= templet.getNeedLvToElite() < common.getLastId();
					}
					
					response.put( (byte) (isChallenge ? 1 : 0) );
					sendPackage( user.getCon(), response );
//				}
			}
			return;
		case 2:// 请求某一个副本里的详细信息
			{
//				synchronized (user) {
					
					short mid 			= buf.getShort();
					
					// 获取副本所有支线列表
					InstanceTemplet te	= InstanceTempletCfg.getTempletById( mid );
					if( te == null ) {
						Logs.error( user, "申请的副本ID不存在   ID=" + mid );
						return;
					}
					
					EctypeType eType	= te.getType();
					int guankID			= user.getEctypeManager().getLastPid( eType );
					response.putInt( guankID );
					EctypeStartLevel ectypeStartLevel = user.getEctypeStartLevel();
					if( eType == EctypeType.COMMON ){
						
						List<Integer> gks	= te.getGuanKaIDs( guankID );
						response.put( (byte) gks.size() );
						for( int i : gks ){
							response.putInt( i );
							response.put( ectypeStartLevel.get(i) );
						}
					}
					
					if( eType == EctypeType.ELITE ){
						
						
						EliteCountBase ecb 	= user.getEliteEctypeCountManager().get(mid);
						response.put( (byte) (ecb == null ? 0 : ecb.m_nPList.size()) );
						if( ecb != null )
							for( EliteCount e : ecb.m_nPList ){
								
								response.putInt(  e.m_nPid  );
								byte count = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? e.m_nTodayCount + DefaultCfg.ELITE_TODAY_COUNT : e.m_nTodayCount);
								response.put( count < 0 ? 0 : count );
								response.put(  e.m_nBuyCount  );
								response.put( ectypeStartLevel.get(e.m_nPid) );
							}
					}
					
					sendPackage( user.getCon(), response );
					
//				}
			}
			return;
		case 3://申请活动列表
			{
				List< ActivityBase > list = ActivityManager.getInstance().getList();
				response.put( (byte)list.size() );
				for( int i = 0; i < list.size(); i++ ){
					
					response.putShort( list.get(i).getNID() );
					response.put( (byte) (list.get(i).isOpen() ? 1 : 0));
					response.putInt( list.get(i).getSurplusTime() );
				}
				
				sendPackage( user.getCon(), response );
			}
			return;
			
		case 4://申请挑战小龙详细列表
			{
				short id			= buf.getShort();
				
				ActivityBase base 	= ActivityManager.getInstance().get( id );
				if( base == null ){
					Logs.error( user, "申请小龙详细信息出错  id=" + id );
					return ;
				}
				
				if( !base.isOpen() ){
					Logs.error( user, "申请小龙详细信息出错  该活动还没开启!");
					return ;
				}
				
				// 先刷新一下
//				user.getEliteEctypeCountManager().updataTime();
				
				response.putShort( id );
				
				List<Byte> list	= user.getEliteEctypeCountManager().getDragonetLists( id );
				
				byte count = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? list.get(0) + DefaultCfg.ELITE_TODAY_COUNT : list.get(0));
				response.put( count < 0 ? 0 : count );
				count = (byte) (ActivityManager.getInstance().isConsumeOrgyIsOpen() ? list.get(1) + DefaultCfg.ELITE_TODAY_COUNT : list.get(1));
				response.put( count < 0 ? 0 : count );
				response.put( list.get(2) );
				
				sendPackage( user.getCon(), response );
			}
			return;
		case 5://申请大龙列表
			{
				short id			= buf.getShort();
				ActivityBase base 	= ActivityManager.getInstance().get( id );
				
				byte code 			= -1;
				if( base == null ){
					Logs.error( user, "申请大龙详细信息出错  id=" + id );
					response.put( code );
					sendPackage( user.getCon(), response );
					return;
				}
				
				code				= 0;
				if( !base.isOpen() ){
					Logs.error( user, "申请大龙详细信息出错  该活动还没开启!");
					code			= 1;
				}
				
				DragonBase dragon	= ActivityManager.getInstance().getDragon();
				
				response.put( code );
				response.putInt( dragon.getHpCur() );
				response.putInt( dragon.getHpMax() );
				response.putInt( dragon.getAttack() );
				response.put( dragon.getCount( user.getUID() ) );
				
				List<ParticipationBase> list = ActivityManager.getInstance().getDragon().getTheTopTens();
				byte size = (byte) (list.size() < DefaultCfg.DRAGON_DAMAGE_MAXCOUNT ? list.size() : DefaultCfg.DRAGON_DAMAGE_MAXCOUNT);
				response.put( size );
				for( int i = 0; i < size; i++ ){
					
					ParticipationBase p = list.get(i);
					UserInfo x 			= UserManager.getInstance().getByName( p.getUID() );
					
					UtilBase.encodeString( response, x == null ? "" : x.getNickName() );
					response.putInt( p.getValue() );
				}
				int data[] = ActivityManager.getInstance().getDragon().getRanking( user.getUID() );
				response.putShort( (short) (data[0] + 1) );
				response.putInt( data[1] );
				
				sendPackage( user.getCon(), response );
			}
			return;
		case 6://申请试炼列表
			{
				short id			= buf.getShort();
				ActivityBase base 	= ActivityManager.getInstance().get( id );
				if( base == null ){
					Logs.error( user, "申请试炼详细信息出错  id=" + id );
					return ;
				}
				if( !base.isOpen() ){
					Logs.error( user, "申请试炼详细信息出错  该活动还没开启!");
					return ;
				}
				response.putShort( id );
				sendPackage( user.getCon(), response );
			}
			return;
		case 7://申请大龙伤害排行
			{
				List<ParticipationBase> list = ActivityManager.getInstance().getDragon().getTheTopTens();
				byte size = (byte) (list.size() < DefaultCfg.DRAGON_DAMAGE_MAXCOUNT ? list.size() : DefaultCfg.DRAGON_DAMAGE_MAXCOUNT);
				response.put( size );
				for( int i = 0; i < size; i++ ){
					
					ParticipationBase p = list.get(i);
					UserInfo x 			= UserManager.getInstance().getByName( p.getUID() );
					
					UtilBase.encodeString( response, x == null ? "" : x.getNickName() );
					response.putInt( p.getValue() );
				}
				sendPackage( user.getCon(), response );
			}
			return;
		case 8://申请特殊活动列表（暂定圣诞节）
			{
				short id			= buf.getShort();
				ActivityBase base 	= ActivityManager.getInstance().get( id );
				if( base == null ){
					Logs.error( user, "申请试炼详细信息出错  id=" + id );
					return ;
				}
				if( !base.isOpen() ){
					Logs.error( user, "申请试炼详细信息出错  该活动还没开启!");
					return ;
				}
				
				response.putShort( id );
				response.put( user.christmasTimes );
				sendPackage( user.getCon(), response );
			}
			return;
		}
		
	}
	
}
