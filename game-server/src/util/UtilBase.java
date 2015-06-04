package util;

import game.fighter.Hero;
import game.log.Logs;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import user.UserInfo;

import config.illegal.IllegalCharCfg;
import config.luckydraw.LuckydrawTemplet;

/**
 * 系统中最基础的一些工具，不仅仅针对游戏
 * 
 * @author DXF
 * 
 */
public class UtilBase {
	/**
	 * 编码字符串，先放入一个short的字符串长度，再放入字符串的内容<br>
	 * 字符串长度不得大于Short.MAX_VALUE
	 * 
	 * @param buf
	 * @param content
	 */
	public static void encodeString(ByteBuffer buf, String content) {

		if( content == null ){
			throw new IllegalArgumentException( "null 字符串" );
		}
		byte[] temp = content.getBytes();
		if (temp.length > Short.MAX_VALUE) {
			throw new IllegalArgumentException("字符串长度超限");
		}
		buf.putShort((short) temp.length);
		if( temp.length != 0 ){
			buf.put(temp);
		}

	}

	/**
	 * 解码字符串，从缓冲区中先读出一个short的字符串长度，然后读取内容并生产字符串 字符串长度不得大于Short.MAX_VALUE
	 * 
	 * @param buf
	 * @param content
	 */
	public static String decodeString(ByteBuffer buf) {

		short len = buf.getShort();
		if( len > 0 ){
			if( buf.limit() - buf.position() < len ){
				Logs.debug( "decodeString error: content not enouth,need length=" + len + " real len=" + (buf.limit() - buf.position()) );
				return "";
			}
			byte[] content = new byte[len];
			buf.get(content);
			return new String(content);
		}
		return "";
	}

	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty( "os.name" );
		if (osName.toLowerCase().indexOf( "windows" ) > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 把一个用秒数保存的时间值转换为易读的字符串
	 * @param seconds
	 * @return
	 */
	public static String secondsToDateStr( int seconds ){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		return dateFormat.format( new Date( seconds * 1000l ) );
		
	}
	
	/**
	 * 把一个用秒数保存的时间值转换为易读的字符串
	 * @param seconds
	 * @return
	 */
	public static String secondsToDate( int seconds, String format ){
		SimpleDateFormat dateFormat = new SimpleDateFormat( format );//可以方便地修改日期格式
		return dateFormat.format( new Date( seconds * 1000l ) );
	}
	
	/**
	 * 返回按字节为单位收到的客户端传来的数据信息，调试用
	 * 
	 * @param buf
	 * @return
	 */
	public static String bufToString( ByteBuffer buf ){
		ByteBuffer copy = buf.asReadOnlyBuffer();
		if( copy.position() != 0 ){
			copy.flip();
		}
		StringBuilder sb = new StringBuilder( "[");
		while( copy.hasRemaining() ){
			sb.append( copy.get() + " " );
		}
		sb.append( "]" );
		return sb.toString();
	}
	
	/**
	 * 获得当前星期
	 * @param currentTimeMillis 
	 * @return
	 */
	public static byte getWeek( long currentTimeMillis ){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( currentTimeMillis );
		return (byte) ( cal.get(Calendar.DAY_OF_WEEK) == 1 ? 7 : (cal.get(Calendar.DAY_OF_WEEK) - 1) );
	}
	
	public static long getEarliestDayTimeToWeek( int i ) {
		
		byte week = getWeek( SystemTimer.currentTimeMillis() );
		if( week <= i ){
			return i - week;
		}else{
			return 7 - week + i;
		}
	}
	
	/**
	 * 检查名字是否含有非法字符
	 * @param nickName
	 * @return
	 */
	public static boolean nameIsIllegal( String nickName ) {

		// 最大字符个数 7个 这里不管是英文还是汉字
		if( nickName.length() > 7 )
			return true;
		
		// 先检查非法字符
//		Pattern pattern = Pattern.compile( "[^\u4e00-\u9fa5]{1,7}$|[^A-Za-z0-9]{1,14}$+" );
		Pattern pattern = Pattern.compile( "[^\u4e00-\u9fa5A-Za-z0-9]+" );
		Matcher matcher = pattern.matcher( nickName );
		if ( matcher.find() ) return true;

		// 检查 屏蔽字符
//		pattern 		= Pattern.compile( nickName );
		for( String s : IllegalCharCfg.lists )
		{
//			matcher 	= pattern.matcher(s);
//			if (matcher.find()) 
//				return true;
			if( nickName.contains(s) )
				return true;
		}
		
		return false;
	}
	
	/** 纯文字 */
	public static String nToPlainText( String text ){
		return 0 + "|" + text;
	}
	
	/** 英雄 */
	public static String nToHero( Hero h ){
		return 1 + "|" + h.getNid() + "|" + h.getQuality().getColour().toNumber() + "|" + h.getQuality().getLevel();
	}
	
	/** 装备 */
	public static String nToEquip( Hero h ){
		return 2 + "|" + h.getNid() + "|" + h.getQuality().getColour().toNumber() + "|" + h.getQuality().getLevel();
	}
	
	/** 英雄 */
	public static String nToSkill( int id, byte level ){
		return 3 + "|" + id + "|" + level;
	}
	
	public static String nToHero( UserInfo user, List<LuckydrawTemplet> temp ){
		
		String content = 4 + "|" + user.getNickName() + "|" + temp.size() + "|";
		for( int i = 0; i < temp.size(); i++ ){
			LuckydrawTemplet lu = temp.get(i);
			if( i != 0 ) content += "|";
			content += lu.getNid() + "|" + lu.getQuality().getColour().toNumber() + "|" + lu.getQuality().getLevel() ;
		}
		
		return content;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
//		ByteBuffer buf = ByteBuffer.allocate(100);
//		encodeString(buf, "content中文");
//		buf.flip();
//		System.out.println(decodeString(buf));
//		isWindowsOS();
//		
//		System.out.println( secondsToDate( 1366363078 ) );
		
//		System.out.println( SystemTimer.currentTimeMillis()/1000 );
		System.out.println( secondsToDateStr( (int)(SystemTimer.currentTimeSecond()) ) );
		
		Date date = new Date();
		date.setTime( SystemTimer.currentTimeMillis() );
		date.setHours(24);
		date.setMinutes(0);
		date.setSeconds(0);
		date.getTime();
		System.out.println( date.getTime()/1000 );
		
		System.out.println( secondsToDateStr( (int)(date.getTime()/1000) ) );
//		System.out.println( date.getHours() );
	}


}
