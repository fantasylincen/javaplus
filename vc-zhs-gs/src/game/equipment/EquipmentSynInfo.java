package game.equipment;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import notice.NoticeManager;

import config.equipment.EquipmentTemplet;
import config.equipment.EquipmentTempletCfg;
import define.DefaultCfg;

import user.UserInfo;
import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;

import lombok.Data;

/**
 * 装备合成信息
 * @author DXF
 */
public class EquipmentSynInfo {

	private UserInfo									user;
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getNextNid() {
		return nextNid;
	}

	public void setNextNid(int nextNid) {
		this.nextNid = nextNid;
	}

	public List<Integer> getStuffUid() {
		return stuffUid;
	}

	public void setStuffUid(List<Integer> stuffUid) {
		this.stuffUid = stuffUid;
	}

	public List<Integer> getStuffNid() {
		return stuffNid;
	}

	public void setStuffNid(List<Integer> stuffNid) {
		this.stuffNid = stuffNid;
	}

	public int getCurtime() {
		return curtime;
	}

	public void setCurtime(int curtime) {
		this.curtime = curtime;
	}
	// 合成装备
	private int 			uid			= -1;
	private int 			nid			= -1;
	private int 			nextNid		= -1;
	
	// 材料
	private List<Integer> 	stuffUid	= new ArrayList<Integer>();
	private List<Integer> 	stuffNid	= new ArrayList<Integer>();
	
	private int 			curtime		= 0;
	
	public EquipmentSynInfo( UserInfo user ){
		this.user = user;
	}
	
	/** 获取合成装备 */
	public EquipmentInfo getSyn() {
		return new EquipmentInfo( uid, EquipmentTempletCfg.get( nid ) );
	}
	
	/**
	 * 以列表形式获得所有 参与合成的装备
	 * @return
	 */
	public List<EquipmentInfo> getAll() {
		List<EquipmentInfo> list = new ArrayList<EquipmentInfo>();
		
		EquipmentInfo xx = new EquipmentInfo( uid, EquipmentTempletCfg.get( nid ) );
		list.add( xx );
		
		for( int i = 0; i < stuffUid.size(); i++ ){
			xx			 = new EquipmentInfo( stuffUid.get(i), EquipmentTempletCfg.get( stuffNid.get(i) ) );
			list.add( xx );
		}
		return list;
	}
	
	
	public void putBytes( ByteBuffer response ) {
		
		int t 			= getTime();
		int _tempNid	= nid;
		
		// 这里主要是给前端显示  不用担心有没有  前面判断了
		if( t != 0 ) _tempNid = EquipmentTempletCfg.get( nid ).getSynthesis();
			
		response.putInt( _tempNid );
		if( nid != -1 ) response.putInt( t );
	}

	/**
	 * 设置信息
	 * @param xxE
	 * @param xxL
	 */
	public void setData( EquipmentInfo xxE, List<EquipmentInfo> xxL ) {
		uid		= xxE.getUID();
		nid		= xxE.getNID();
		
		stuffUid.clear();
		stuffNid.clear();
		for( EquipmentInfo e : xxL ){
			stuffUid.add( e.getUID() );
			stuffNid.add( e.getNID() );
		}
		
		curtime	= SystemTimer.currentTimeSecond();
	}

	/**
	 * 立即完成
	 */
	public void completed( ) {
		
		int nextid = EquipmentTempletCfg.get( nid ).getSynthesis();
		if( nextid != 0 ) nid = nextid;
		
		//只需要将时间设置成0 就行了
		curtime = 0;
		
		if( EquipmentTempletCfg.get( nid ).getColor() == EColour.ORANGE )
			NoticeManager.getInstance().addTimely( 208, UtilBase.nToPlainText( user.getNickName() ) );
		if( EquipmentTempletCfg.get( nid ).getColor() == EColour.PURPLE )
			NoticeManager.getInstance().addTimely( 209, UtilBase.nToPlainText( user.getNickName() ) );
	}

	/**
	 * 获得立即完成需要消耗的水晶
	 * @return
	 */
	public int getAtonceCompletedCash() {
		
		// 半小时的毫秒数
		int residue 	= (getTime() / 1800) + 1;
		
		return residue * DefaultCfg.EQUIP_SYNTHESIS_COMPLETE;
	}
	
	/**
	 * 是否可以取出
	 * @return
	 */
	public ErrorCode isGetout() {
		
		if( nid == -1 ) return ErrorCode.UNKNOW_ERROR;
		
		// 时间还没有完
		if( getTime() > 0 ) return ErrorCode.UNKNOW_ERROR;
			
		return ErrorCode.SUCCESS;
	}
	
	/** 获得剩余时间 */
	private int getTime( ){
		
		if( curtime == 0 ) return 0;
		
		EquipmentTemplet templet 	= EquipmentTempletCfg.get( nid );
		if( templet == null ) 		return 0;
		
		// 算出已经过去的时间
		int formerly				= SystemTimer.currentTimeSecond() - curtime;
		
		int residue					= templet.getSynthesisTime() - formerly;
		if( residue <= 0 ){
			completed();
			return 0;
		}
		
		// 这里多给前端1秒
		return residue + 1;
	}

	/** 清除信息 */
	public void clear() {
		uid		= -1;
		nid		= -1;
		stuffUid.clear();
		stuffNid.clear();
		curtime	= 0;
	}


	public String synToStr() {
		StringBuilder content = new StringBuilder();
		content.append( uid ).append( "," ).append( nid );
		return content.toString();
	}
	public String stuffToStr() {
		StringBuilder content = new StringBuilder();
		if( stuffUid.isEmpty() ) return "";
		for( int i = 0; i < stuffUid.size(); i++ ){
			content.append( stuffUid.get(i) ).append( "," ).append( stuffNid.get(i) );
			content.append( "|" );
		}
		return content.toString();
	}
	public void setSyn( String string ) {
		if( string.isEmpty() || string == null ) return ;
		String[] content 	= string.split( "," );
		uid					= Integer.parseInt( content[0] );
		nid					= Integer.parseInt( content[1] );
	}
	public void setStuff( String string ) {
		stuffUid.clear();
		stuffNid.clear();
		if( string.isEmpty() || string == null ) return ;
		String[] content 	= string.split( "\\|" );
		for( String s : content ){
			int _uid		= Integer.parseInt( s.split( "," )[0] );
			int _nid		= Integer.parseInt( s.split( "," )[1] );
			stuffUid.add( _uid );
			stuffNid.add( _nid );
		}
	}
	
	
}

