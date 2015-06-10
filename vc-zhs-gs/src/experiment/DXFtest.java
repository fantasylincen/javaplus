package experiment;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.web.WebContentFethcer;
import deng.xxoo.utils.XOTime;
import lombok.Data;

public class DXFtest {

	private int a;

	private int b;

	// private static Properties p;

	public int getA() {
		return a;
	}


	public void setA(int a) {
		this.a = a;
	}


	public int getB() {
		return b;
	}


	public void setB(int b) {
		this.b = b;
	}


	public static void main(String[] arg) throws Exception {

		// 本机IP
		Pattern c = Pattern.compile("\"cip\": \"[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\"");
		String json = WebContentFethcer.get("gb2312", "http://pv.sohu.com/cityjson");
		Matcher m = c.matcher(json);
		m.find();
		String group = m.group();
		String[] split = group.split(":");
		String ss = split[1];
		ss = ss.replaceAll("\"", "").trim();
		System.out.println(ss);
		// 当前操作系统
		String osName = System.getProperty("os.name");
		String user = System.getProperty("user.name");
		System.out.println("当前操作系统是：" + osName + " - " + user);
		System.out.println(System.getProperty("java.library.path"));
		Calendar currentDate = Calendar.getInstance();
		// long currentDateLong = currentDate.getTime().getTime();
		// currentDate.setTimeInMillis( 1390644669000L );
		// 这里直接设置分钟和秒 为0 这样就是当前整点
		// currentDate.set( Calendar.YEAR, 2015 );
		// currentDate.set( Calendar.MONTH, 4 );
		// currentDate.set( Calendar.DAY_OF_MONTH, 12 );
		currentDate.set(Calendar.HOUR_OF_DAY, 24);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		System.out.println(currentDate.getTime().toString());
		// System.out.println( UtilBase.secondsToDateStr( (int)
		// (currentDate.getTimeInMillis()/1000) ) );
		// // 先得到当前整点的时间 然后加上3600000就是一个小时的时间 就变成下一个小时的时间
		// long earliestDtae = currentDate.getTime().getTime() + 3600000L;
		// // 用下一个小时的时间 减当前时间 就变成延期时间
		// System.out.println( currentDate.getTime().toString() );
		// System.out.println( earliestDtae - currentDateLong );
		// System.out.println( (earliestDtae - currentDateLong) / 1000 / 60 / 60
		// );
		// Timer timer = new Timer();
		// timer.schedule( new PlainTimerTask(), 1000L, 1000L );

		// Pattern pattern = Pattern.compile(
		// "[^\u4e00-\u9fa5]{1,7}|[^A-Za-z0-9]{1,14}+" );
		// Pattern pattern = Pattern.compile( "[^\u4e00-\u9fa5\\w]{1,7}$" );
		//
		// Matcher matcher = pattern.matcher( "asdsadsadssssssssssssssss" );
		// if ( matcher.find() )
		// System.out.println( "找到了" );
		//
		// Calendar currentDate = Calendar.getInstance();
		// System.out.println( currentDate.getTime().toString() );
		//
		// currentDate.setTimeInMillis( 1384753483000L );
		//
		// System.out.println( currentDate.getTime().toString() );
		//
		//
		// List<Integer> list = new ArrayList<Integer>();
		// System.out.println( list );
		// aaaa( list );
		// System.out.println( list );

		// DXFtest d = new DXFtest();
		// d.setB( 5 );
		// System.out.println( d.toString() + "   " + d.hashCode() );

		// System.out.println( Integer.parseInt( sss.split( "c" )[1] ) );
		//
		// for( int i = 0; i < sss.split( "c" ).length; i++ )
		// System.out.println( sss.split( "c" )[i] );2147483647
		// System.out.println( sss.split( "c" ).length );380608399

		// 山东省青岛市 电信
		// 山东省青岛市 电信
		// String cityname = "------" + IPSeeker.getInstance().getAddress(
		// "174.36.242.19" );
		//
		// System.out.println( cityname );

		// System.out.println( UtilBase.secondsToDateStr( 1409987823 ) );
		// SimpleDateFormat dateFormat = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		// System.out.println( dateFormat.format( new Date( 0 ) ) );
		// ConcurrentHashMap<Integer,Byte> starLevel = new
		// ConcurrentHashMap<Integer, Byte>();
		// starLevel.putIfAbsent( 1, (byte) 2 );
		// System.out.println( starLevel.get(1) );
		// starLevel.replace( 1, (byte) 3 );
		// System.out.println( starLevel.get(1) );

		// String passive = "无";
		// int i = 10;
		// try {
		// i = Integer.parseInt( passive );
		// System.out.println( i );
		// } catch (Exception e) {
		// }
		// System.out.println( i );
		// p = new Properties();
		// InputStream input = new FileInputStream( "config/aaaa.properties" );
		// p.load( input);
		// input.close();
		//
		// System.out.println( p.getProperty( "name" ) );
		// 10000000
		// 得到00:00:00的时间

		// File testFile = new File( "D:/英雄之城/SVN数据/后端配置表/client/ServerList.xml"
		// );
		// File testFile = new File(
		// "C:/Users/Administrator/Desktop/SystemAward.xlsx" );
		//
		// FileInputStream fis=new FileInputStream(testFile);
		// byte[] buf = new byte[1024];
		// // StringBuffer sb=new StringBuffer();
		// while((fis.read(buf))!=-1){
		// System.out.println( new String(buf) );
		// // sb.append(new String(buf));
		// buf=new byte[1024];//重新生成，避免和上次读取的数据重复
		// }

		// Date lastModified = new Date(testFile.lastModified()); //文件最后修改时间
		// SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String dataTimeStr = fmt.format(lastModified); //把它转化为yyyy-MM-dd
		// HH:mm:ss形式
		// System.out.println(dataTimeStr);
		System.out.println(XOTime.refFormatDate(1429308732000l));
//		System.out.println(XOTime.refFormatDate(1417961376000l));
		// DELETE FROM syslogs WHERE status=1 ORDER BY statusid LIMIT 10000;

		// float price=89.89f;
		// int itemNum=3;
		// float totalPrice=price*itemNum;
		// float num=(float)(Math.round(totalPrice*100)/100);

	}


	public static void list1(List<String> list) {
		long l1 = System.currentTimeMillis();
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println((float) (System.currentTimeMillis() - l1) / 1000f);
	}

	public static void list2(List<String> list) {
		long l1 = System.currentTimeMillis();
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
		System.out.println((float) (System.currentTimeMillis() - l1) / 1000f);
	}

	public final static void aaaa(final boos b) {
		b.id = 1221;
	}

	public static boolean run() throws Exception {

		throw new Exception("1111");
	}

	public static void put(String... args) {

		String content = "";

		for (String s : args) {
			content += s;
		}

		System.out.println(content);
	}

	public static int getint(int i) throws SQLException {

		try {
			if (i == 1)
				return 1;
			else
				return 0;
		}

		finally {
			System.out.println("ssssssssssss");
		}
	}

	public static Object[] x() {

		Object[] obj = new Object[2];

		aaaaa a = new aaaaa(0, 3);

		obj[0] = a;
		obj[1] = 2;

		return obj;
	}

}

class PlainTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println(new Date());
	}

}

class aaaaa {

	public int a = 0;

	public int b = 3;

	public aaaaa(int i, int j) {
		a = i;
		b = j;
	}

	public String toString() {
		return a + "-" + b;
	}

	public String geta() {
		return this.getClass().getResource("/").getPath();
	}
}

class EventList {

	public void run() {
		Events.addEventListener(EventList.class, 1, "adc", this);
	}

	public void adc(String str) {
		System.out.println(str + "EventList 类被调用!");
	}
}

class Test11 {

	public Test11() {
		Events.addEventListener(Test11.class, 1, "asdasd", this);
	}

	public void asdasd(String str) {
		System.out.println(str + "Test11 类被调用!");
	}
}

class Events {

	private static Map<Integer, List<EventBase>> map = new HashMap<Integer, List<EventBase>>();

	public Events() {
	}

	public static void addEventListener(Class<?> clazz, int _type, String function, Object obj) {
		try {
			EventBase base = new EventBase();
			base.clazz = clazz;
			base.function = function;
			base.obj = obj;

			List<EventBase> list = map.get(_type);
			if (list == null) {
				list = new ArrayList<EventBase>();
				list.add(base);
				map.put(_type, list);
			} else {
				list.add(base);
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void run(int _type, String str) {

		List<EventBase> list = map.get(_type);

		if (list != null) {

			for (EventBase base : list) {
				base.run(str);
			}
		}
	}

}

class boos {
	public int id;
}

class EventBase {

	Class<?> clazz;

	Object obj;

	String function;

	public void run(String str) {

		try {
			clazz.getMethod(function, String.class).invoke(obj, str);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}