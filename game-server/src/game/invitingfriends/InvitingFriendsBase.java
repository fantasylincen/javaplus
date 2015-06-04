package game.invitingfriends;

import java.util.ArrayList;
import java.util.List;

import define.DefaultCfg;

import user.UserInfo;
import user.UserManager;
import util.ErrorCode;

/**
 * 邀请好友基础类
 * @author DXF
 */
public class InvitingFriendsBase {

	// 邀请我的 
	private int						inviteMyID		= -1;
	
	// 被邀请的
	private List<InvitedBase> 		invitedList		= new ArrayList<InvitedBase>();

	public List<InvitedBase> getList() {
		return invitedList;
	}

	public void setListContent( String content ) {
		
		if( content == null || content.isEmpty() ) return ;
		
		String[] list = content.split("\\|");
		
		for( String s : list ){
			String[] temp = s.split(",");
			
			int uid = Integer.parseInt( temp[0] );
			if( UserManager.getInstance().getByName(uid) == null )
				continue;
					
			InvitedBase i = new InvitedBase( Integer.parseInt( temp[0] ) );
			i.setGottenGold( Integer.parseInt( temp[1] ) );
			
			invitedList.add( i );
		}
	}

	public String getListContent() {
		
		StringBuilder content = new StringBuilder();
		
		for( InvitedBase i : invitedList ){
			content.append( i.getUID() ).append( "," );
			content.append( i.getGottenGold() ).append( "," );
			content.append( "|" );
		}
		
		return content.toString();
	}
	
	public int getIMyID() {
		return inviteMyID;
	}
	public void setIMyID( int uid ) {
		this.inviteMyID = uid;
	}

	public ErrorCode add( UserInfo u ) {
		
		if( get( u.getUID() ) != null )
			return ErrorCode.INVITING_IS_HAVE;
		
		if( invitedList.size() >= DefaultCfg.INVITED_MAX_NUM )
			return ErrorCode.INVITING_IS_MAX;
		
		InvitedBase base = new InvitedBase( u.getUID() );
		base.init();
		invitedList.add( base );
		
		return ErrorCode.SUCCESS;
	}

	public InvitedBase get( int uid ) {
		for( InvitedBase base : invitedList )
		{
			if( base.getUID() == uid )
				return base;
		}
		return null;
	}

	/**
	 * 设置 对应的 领取水晶的 数值
	 * @param uid
	 * @param value
	 */
	public void setBeGottenGold( int uid, int value ) {
		InvitedBase base = get( uid );
		if( base != null )
			base.setGottenGold( value );
	}
	public int getBeGottenGold( int uid ) {
		InvitedBase base = get( uid );
		return base != null ? base.getGottenGold() : 0;
	}
	public void addBeGottenGold( int uid, int value ) {
		InvitedBase base = get( uid );
		if( base != null )
			base.addGottenGold( value );
	}

}
