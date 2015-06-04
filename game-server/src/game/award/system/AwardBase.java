package game.award.system;

import game.award.AwardInfo;

import java.util.List;

import config.saward.SystemAwardTemplet;
import config.saward.SystemAwardTempletCfg;

/**
 * 奖励基础类 
 * @author DXF
 *
 */
public class AwardBase {

	// ID
	public int					m_nID			= 0;
	
	// 条件
	public AwardCondition		m_nCondition	= null;
	
	public AwardBase(){ }


	/** 是否满足条件 */
	public boolean isFulfil() {
		
		SystemAwardTemplet templet = SystemAwardTempletCfg.getTempletById( m_nID );
		if( templet == null ) return false;
		
		return m_nCondition.getValue() >= templet.m_nCondition;
	}

	/**
	 *  记录条件信息
	 * @param value  
	 */
	public void record( int value ) {
		if( m_nCondition == null ) return;
		m_nCondition.setValue( value );
	}
	/** 这种 是直接加加 */
	public void record() {
		if( m_nCondition == null ) return;
		m_nCondition.addValue();
	}
	
	/** 切换到下一个 */
	public void changeNext() {
		
		if( !isEveryday() ){
			SystemAwardTemplet templet 	= SystemAwardTempletCfg.getTempletById( m_nID + 1 );
			m_nID 						= templet != null ? templet.m_nId : 0;
		}else{
			m_nCondition.setValue( -1 );
		}
		
	} 

	/**
	 * 获得奖励
	 * @return
	 */
	public List<AwardInfo> getAwards() {
		
		SystemAwardTemplet templet = SystemAwardTempletCfg.getTempletById( m_nID );
		if( templet == null ) return null;
		
		return templet.m_nAward;
	}
	
	/** 是否每日*/
	public boolean isEveryday(){
		return m_nID / 100000 == 1;
	}


	/**
	 * 设置内容
	 * @param content
	 */
	public void setContent( String content ) {
		if( content.isEmpty() ) return;
		
		String[] list 	= content.split(",");
		
		m_nID			= Integer.parseInt( list[0] );
		m_nCondition	= new AwardCondition();
		m_nCondition.setValue( Integer.parseInt( list[1] ) );
	}
	
	/**
	 * 获得内容
	 * @return
	 */
	public String getContent(){
		
		if( m_nID == 0 )
			return "";
		
		StringBuilder content = new StringBuilder();
		content.append( m_nID ).append(",");
		content.append( m_nCondition.getValue() );
		
		return content.toString();
	}

}
