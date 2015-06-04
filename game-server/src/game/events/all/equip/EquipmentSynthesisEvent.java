package game.events.all.equip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.ErrorCode;
import game.equipment.EquipmentInfo;
import game.equipment.EquipmentManager;
import game.equipment.EquipmentSynInfo;
import game.events.EventBase;
import game.events.EventDescrip;
import game.events.all.update.UpdateManager;
import game.events.all.update.UpdateType;
import game.log.Logs;

@EventDescrip(desc = "装备系统-合成")
public class EquipmentSynthesisEvent extends EventBase{

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		byte at					= buf.get();
		ByteBuffer response 	= buildEmptyPackage( 256 );
		
		response.put( at );
		
		
		switch( at ){
		
		case 1: // ---------------------------申请合成面板信息
			{
				EquipmentSynInfo esi 		= user.getEquipmentManager().getEquSynInfo();
				esi.putBytes( response );
				sendPackage( user.getCon(), response );
			}
			return;
		case 2: // ---------------------------开始合成
			{
				ErrorCode code 			= ErrorCode.SUCCESS;
				int uid					= buf.getInt();
				EquipmentInfo xxE 		= user.getEquipmentManager().get( uid );
				List<EquipmentInfo> xxL	= new ArrayList<EquipmentInfo>();
				
				do{
					if( xxE == null ){
						Logs.error( user, "合成装备 要合成的装备出错 uid=" + uid + "不存在！" );
						code				= ErrorCode.UNKNOW_ERROR;
						break;
					}
					
					byte size 			= buf.get();
					for( int i = 0; i < size; i++ ){
						int id			= buf.getInt();
						EquipmentInfo x	= user.getEquipmentManager().get( id );
						if( x == null ){
							Logs.error( user, "合成装备 材料装备出错 uid=" + id + "不存在！" );
							xxL			= null;
							break;
						}
						if( uid == id ){
							Logs.error( user, "合成装备 材料装备出错 不能把自己拿来当材料！" );
							xxL			= null;
							break;
						}
						if( x.getNID() != xxE.getNID() ){
							Logs.error( user, "合成装备 材料装备出错 表格ID不等于要合作装备表格ID！" );
							xxL			= null;
							break;
						}
						
						xxL.add( x );
					}
					
					if( xxL == null ){ code	= ErrorCode.UNKNOW_ERROR; break; }
					
					code				= user.getEquipmentManager().synthesis( xxE, xxL );
					
				}while( false );
				
				response.putShort( (short) code.ordinal() );
				sendPackage( user.getCon(), response );
				
				if( code == ErrorCode.SUCCESS ){
					// 先刷新前端
					xxL.add( xxE );
					UpdateManager.instance.update( user, UpdateType.U_91, xxL );
					// 最后执行数据库删除
					user.getEquipmentManager().remove( xxL );
					// 保存合成装备
					user.getEquipmentManager().updataSyn();
				}
				
			}
			return;
		case 3: // ---------------------------立即完成
			{
				ErrorCode code	= user.getEquipmentManager().synthesisAtonceCompleted();
				response.putShort( (short) code.ordinal() );
				sendPackage( user.getCon(), response );
				
				if( code == ErrorCode.SUCCESS )
					user.getEquipmentManager().updataSyn();
			}
			return;
		case 4: // ---------------------------取消合成
			{
				ErrorCode code				= ErrorCode.SUCCESS;
				
				EquipmentManager manager	= user.getEquipmentManager();
				// 先将删除掉的装备拿出来
				List<EquipmentInfo> list 	= manager.getEquSynInfo().getAll();
				
				// 放到装备背包
				for( EquipmentInfo e : list ){
					code 					= manager.create( e ) != -1 ? ErrorCode.SUCCESS : ErrorCode.UNKNOW_ERROR;
					if( code != ErrorCode.SUCCESS ) break;
				}
				
				// 显示更新前端
				if( code == ErrorCode.SUCCESS )
					UpdateManager.instance.update( user, UpdateType.U_90, list );
				
				response.putShort( (short) code.ordinal() );
				sendPackage( user.getCon(), response );
				
				if( code == ErrorCode.SUCCESS ){
					
					// 然后刷新数据库 将删除的装备取出来
					manager.getBack( list );
					// 在然后将参数合成装备清除掉
					manager.getEquSynInfo().clear();
					// 保存合成装备信息 
					manager.updataSyn();
				}
				
			}
		return;
		case 5: // ---------------------------取出装备
			{
				EquipmentManager manager	= user.getEquipmentManager();

				// 看是否可以取出
				ErrorCode code				= manager.getEquSynInfo().isGetout();
				
				EquipmentInfo equ			= null;
				List<EquipmentInfo> list 	= new ArrayList<EquipmentInfo>();
				if( code == ErrorCode.SUCCESS ){
					
					// 先将合成装备拿出来
					equ 					= manager.getEquSynInfo().getSyn();
					
					// 放到装备背包
					code					= manager.create( equ ) != -1 ? ErrorCode.SUCCESS : ErrorCode.UNKNOW_ERROR;
					
					if( code == ErrorCode.SUCCESS ){
						
						list.add( equ );
						
						UpdateManager.instance.update( user, UpdateType.U_90, list );
					}
				}
				 
				response.putShort( (short) code.ordinal() );
				sendPackage( user.getCon(), response );
				
				if( code == ErrorCode.SUCCESS ){
					// 然后刷新数据库 将删除的装备取出来
					manager.getBack( list );
					// 在然后将参数合成装备清除掉
					manager.getEquSynInfo().clear();
					// 保存合成装备信息 
					manager.updataSyn();
					// 刷新这个装备
					manager.updata( equ.getUID() );
				}
			}
		return;
			
		}
		
		
		
		
	}

}
