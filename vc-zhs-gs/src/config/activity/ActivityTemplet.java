package config.activity;

import java.util.Calendar;

import org.jdom2.Element;



/**
 * 活动模板
 * @author DXF
 */
public class ActivityTemplet {
	
	public final short 				m_nId;
	public final String 			m_nName;
	public final String 			m_nDesc;
	
	/** 开启时间（周 ）*/
	public final byte[]				m_nOpenTimeWeek;
	
	/** 开启时间 */
	public final byte[]				m_nStartTimeDay;
	
	/** 关闭时间 */
	public final byte[]				m_nStopTimeDay;
	
	/** 对应副本ID */
	public short 					m_nMissionId;
	
	
	public ActivityTemplet( Element element ){
		
		m_nId			= Short.parseShort( element.getChildText( "id" ) );
		
		try {
			m_nName			= element.getChildText( "name" );
			m_nDesc			= element.getChildText( "desc" );
			
			String content	= element.getChildText( "openTimeWeek" );
			if( content.isEmpty() ){
				throw new RuntimeException( "解析活动表格出错  openTimeWeek=NULL at=" + m_nId );
			}
			String[] list	= content.split(",");
			
			
			m_nOpenTimeWeek	= new byte[list.length];
			for( int i = 0; i < list.length; i++ )
				m_nOpenTimeWeek[i] = Byte.parseByte( list[i] );
	
			content			= element.getChildText( "openTimeDay" );
			if( content.isEmpty() ){
				throw new RuntimeException( "解析活动表格出错  openTimeDay=NULL at=" + m_nId );
			}
			list				= content.split("\\|");
			m_nStartTimeDay		= new byte[2];
			m_nStopTimeDay		= new byte[2];
			
			String[] temp		= list[0].split(",");
			m_nStartTimeDay[0] 	= Byte.parseByte( temp[0] );
			m_nStartTimeDay[1] 	= Byte.parseByte( temp[1] );
			
			temp				= list[1].split(",");
			m_nStopTimeDay[0] 	= Byte.parseByte( temp[0] );
			m_nStopTimeDay[1] 	= Byte.parseByte( temp[1] );
			
			try {
				m_nMissionId	= Short.parseShort( element.getChildText( "mappingId" ) );
			} catch (Exception e) {
				m_nMissionId	= 0;
			}
		
		} catch (Exception e) {
			throw new RuntimeException( "解析活动表格出错  at=" + m_nId );
		}
	}

	/**
	 * 是否属于这个周
	 * @param curWeek
	 * @return
	 */
	public boolean isBelong( byte curWeek ) {
		for( int i = 0; i < m_nOpenTimeWeek.length; i++ ){
			if( m_nOpenTimeWeek[i] == curWeek )
				return true;
		}
		return false;
	}

	/**
	 * 判断时间是在哪个点  1，开始时间之前  2，中间   3，结束时间之后
	 * @param currentTimeMillis
	 * @return
	 */
	public byte getTimePoint( long currentTimeMillis ) {
		
		Calendar currentDate 	= Calendar.getInstance();
		currentDate.setTimeInMillis( currentTimeMillis );
		
		int hours		= currentDate.get( Calendar.HOUR_OF_DAY );
		int minutes		= currentDate.get( Calendar.MINUTE );
		int seconds		= currentDate.get( Calendar.SECOND );
		
		int t1			= hours * 3600 + minutes * 60 + seconds;
		int t2			= m_nStartTimeDay[0] * 3600 + m_nStartTimeDay[1] * 60 + 0;
		int t3			= m_nStopTimeDay[0] * 3600 + m_nStopTimeDay[1] * 60 + 0;
		
		if( t1 < t2 ){
			return 1;
		}else if( t1 >= t2 && t1 < t3 ){
			return 2;
		}else{
			return 3;
		}
	}
	
	/**
	 * 根据时间点 获取剩余时间
	 * @param point
	 * @param curWeek 
	 * @return
	 */
	public int getSurplusTime( byte point, long currentTimeMillis, byte curWeek ){
		
		Calendar currentDate 	= Calendar.getInstance();
		currentDate.setTimeInMillis( currentTimeMillis );
		
		if( point == 1){
			currentDate.set( Calendar.HOUR_OF_DAY, m_nStartTimeDay[0] );
			currentDate.set( Calendar.MINUTE, m_nStartTimeDay[1] );
			currentDate.set( Calendar.SECOND, 0 );
			return (int) ((currentDate.getTimeInMillis() - currentTimeMillis) / 1000);
		}else if( point == 2 ){
			currentDate.set( Calendar.HOUR_OF_DAY, m_nStopTimeDay[0] );
			currentDate.set( Calendar.MINUTE, m_nStopTimeDay[1] );
			currentDate.set( Calendar.SECOND, 0 );
			return (int) ((currentDate.getTimeInMillis() - currentTimeMillis) / 1000);
		}else{
			return getSurplusTime( currentTimeMillis, curWeek );
		}
	}
	

	/**
	 * 获得没有满足的 时间
	 * @param curTime
	 * @param curWeek
	 * @return
	 */
	public int getSurplusTime( long currentTimeMillis, byte curWeek ) {
		int day					= getNumberOfDaysApart( curWeek );
		
		Calendar currentDate 	= Calendar.getInstance();
		// 设置成下次开启的时间 用天数*86400000
		currentDate.setTimeInMillis( currentTimeMillis + day * 86400000L );
		
		currentDate.set( Calendar.HOUR_OF_DAY, m_nStartTimeDay[0] );
		currentDate.set( Calendar.MINUTE, m_nStartTimeDay[1] );
		currentDate.set( Calendar.SECOND, 0 );
		
		return (int) ((currentDate.getTimeInMillis() - currentTimeMillis) / 1000);
	}
	
	
	/**
	 * 获得 这次到下一次开始的相隔天数
	 */
	private int getNumberOfDaysApart( byte curWeek ){
		
		int i 		= 0;
		for(; i < m_nOpenTimeWeek.length; i++ ){
			if( m_nOpenTimeWeek[i] > curWeek ) break;
		}
		
		if( i >= m_nOpenTimeWeek.length ){
			curWeek -= 7;
			i		= 0;
		}
		
		int count	= m_nOpenTimeWeek[i] - curWeek;
		
		return count;
	}
	
	
}
