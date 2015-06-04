package cn.mxz.loganalysis;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import cn.javaplus.util.Util;

public class LoginAnalysis extends Thread {

	Set<String> player = Sets.newHashSet();
	
	LoginAnalysis(){
		Iterator<LoginBean> it = new LoginBeanIterator();
		while (it.hasNext()) {
			LoginBean l = it.next();
			String nick = l.getNick();
			
			player.add( nick );
			//System.out.println( nick);
//			if( nick.equals("神奇少年") ){
//				System.out.println( new LocalDate(l.getTime()) );
//			}
		}
		
		//System.out.println( player.size() );
		
	}
	@Override
	public void run() {
		
		Iterator<LoginBean> it = new LoginBeanIterator();
		
		while (it.hasNext()) {
			LoginBean l = it.next();
			l.getTime();
			Util.Time.getCurrentDay();
//			int day = Util.Time.getDay(l.getTime());
			
			Debuger.debug("LoginAnalysis.run()" + l.getRoleId() + " " + l.getNick() + " " + l.getTime());
		}
	}
	
	
	/**
	 * 获取某个玩家的所有登陆信息
	 * @param user
	 * @return
	 */
	private List<LoginBean> getDataByUser(String user ){
		List<LoginBean> result = Lists.newArrayList();
		Iterator<LoginBean> it = new LoginBeanIterator();
		
		while (it.hasNext()) {
			LoginBean l = it.next();
			if( l.getNick().equals( user ) ){
				result.add(l);
			}
			//Debuger.debug("LoginAnalysis.run()" + l.getRoleId() + " " + l.getNick() + " " + l.getTime());
		}
		
		return result;
				
		
	}
	
	/**
	 * 计算某个指定日期的，n日留存率（n=1，代表次日留存，n=2,代表三日留存）
	 * 
	 * 所谓留存率的定义为（以次日留存为例）
	 * calcTime 登陆过，calcTime-1天注册的
	 * 
	 * @param calcTime
	 * @param n
	 * 				1：次日留存
	 * 				2：三日留存
	 * 
	 * 比如要求2014-08-15的次日留存，就表示，2014-08-14所有的注册人数当分母,2014-08-14注册并且在2014-08-15登陆过当分母
	 * 
	 *     2014-08-14注册并且在2014-08-15登陆过
	 * --------------------------------------------
	 * 	         2014-08-14所有的注册人数
	 * @return
	 */
	public float calcLiving( LocalDate calcTime, int n ){
		LocalDate registerDate = calcTime.plusDays( -n);
//		System.out.println( registerDate);
//		return 0.4f;
		Set<String> registerSet = calcRegistPlayer( registerDate );//获取总注册人数
		Set<String> loginSet = calcLoginPlayer(registerSet, calcTime);
		
		return (float)loginSet.size()/registerSet.size();
//		int registerPlayerCount = 
	}
	
	/**
	 * 给定一个日期，获取此日期注册玩家的Set
	 * @param registDate
	 * @return
	 */
	private Set<String> calcRegistPlayer( LocalDate registDate){
		Set<String> ret = new CreateUserCount().getUsers(registDate.toString() );
//		Set<String> ret = Sets.newHashSet();
//		int count = 0;
//		for( String nick : player ){
//			List<LoginBean> data = getDataByUser(nick);
//			LoginAnalyzeData loginAnalyzeData = new LoginAnalyzeData(data);
//			
////			System.out.println( nick + "是否" + registTime + "注册:" + loginAnalyzeData.isRegister(registTime) );
//			if( loginAnalyzeData.isRegister(registDate) ){
//				ret.add(nick);
//			}
//		}
//		System.out.println( registDate + "的注册总人数为 :" + ret.size() );
		return ret;
	}
	
	/**
	 * 计算指定玩家在指定日期登陆的set
	 * @param registTime
	 * @return
	 */
	private Set<String> calcLoginPlayer( Set<String> players, LocalDate LoginDate){
		Set<String> ret = Sets.newHashSet();
		for( String nick : players ){
			List<LoginBean> data = getDataByUser(nick);
			if( !data.isEmpty() ){
				LoginAnalyzeData loginAnalyzeData = new LoginAnalyzeData(data);
				
				if( loginAnalyzeData.isLogin(LoginDate) ){
					ret.add(nick);
				}
			}
		}
		//System.out.println( LoginDate + "的登陆总人数为 :" + ret.size() );
		return ret;
	}
	
	/**
	 * 获取所有的玩家的登陆信息
	 */
	void buildAll(){
//		Set<String> player = Sets.newHashSet();
		
		int registYesterdayCount = 0;//昨日注册人数
		int loginTodayCount = 0;//做题注册，今日再次登陆的人数
		for( String nick : player ){
//			System.out.println( nick ); 
			List<LoginBean> data = getDataByUser(nick);
			LoginAnalyzeData loginAnalyzeData = new LoginAnalyzeData(data);
			if( loginAnalyzeData.isRegistYesterday() ){
				registYesterdayCount++;
				if( loginAnalyzeData.isLoginToday() ){
					loginTodayCount++;
				}
			}
			System.out.println( loginAnalyzeData );
			
		}
		System.out.println( "二日留存率:" + loginTodayCount / (float)registYesterdayCount);
		System.out.println( "用户总数：" + player.size() );
	}
	public static void main(String[] args) {
		
		LogDB.getInstance().getConnection();
		LocalDate time = new LocalDate().plusDays(-6);//2014-08-14
//	System.out.println( time );
//		for( int i = 0; i < 6; i++ ){//次日

		for( int i = 0; i < 5; i++ ){//三日
				
			LocalDate dt = time.plusDays(i);
//			float ret = new LoginAnalysis().calcLiving( dt,1 );//次日
			float ret = new LoginAnalysis().calcLiving( dt,2 );//三日
			System.out.println( dt + "的次日留存率为:" + ret);
		}
		
		System.out.println( new DateTime(1407952137000L));
		System.out.println( new DateTime(1407952290000L));
		
		
		
		
		
//		System.out.println();
		
//		System.out.println(new LocalDate().plusDays(-2).toDateMidnight().getMillis());
//		long misc = new LocalDate().plusDays(-2).to.getMillis();
//		System.out.println(new Date( misc ));
		
//		for( String nick : new LoginAnalysis().calcRegistPlayer(new LocalDate().plusDays(-2)) ){
//			System.out.println( nick );
//		}
//		
		
//		List<LoginBean> data = new LoginAnalysis().getDataByUser("神奇少年");
//		for(LoginBean b : data  ){
//			System.out.println( b );
//		}
//		LoginAnalyzeData loginAnalyzeData = new LoginAnalyzeData(data);
		
//		new LoginAnalysis();
		
//		long misc = new LocalDate().plusDays( 0 ).toDateMidnight().getMillis();
//		System.out.println( misc );
//		
//		System.out.println(new DateTime( misc ));
		
		
	}
	
	

}
