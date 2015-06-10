package util;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 * @author DXF
 *
 */
public class UtilBase {
	private final static Logger		logger = LoggerFactory.getLogger( UtilBase.class ); 

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
				logger.debug( "decodeString error: content not enouth,need length=" + len + " real len=" + (buf.limit() - buf.position()) );
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
	public static String secondsToDate( int seconds ){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
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
	
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(100);
		encodeString(buf, "content中文");
		buf.flip();
		System.out.println(decodeString(buf));
		isWindowsOS();
		
		System.out.println( secondsToDate( 1366363078 ) );
	}
}
