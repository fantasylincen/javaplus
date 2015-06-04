package cn.mxz.loganalysis;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import cn.mxz.define.NewFighterSql;
import cn.mxz.define.UserBaseSql;
import cn.javaplus.user.UserCollection;
import cn.javaplus.util.Util;

public class LoginAnalyzeData {
	private final String			name;
	private final String			nickName;
	
	/**
	 * 今日是否登陆
	 */
	private boolean					isLoginToday;
	
	/**
	 * 总登陆次数
	 */
	private int						loginCount;
	
	/**
	 * 次日是否留存,必须是昨日创建，今日登陆
	 */
	private boolean					liveTomorrow;
	
	/**
	 * 玩家等级
	 */
	private int 					level;
	
	/**
	 * 是否昨日注册
	 */
	private boolean					isRegistYesterday;
	/**
	 * 原始登陆数据	
	 */
	private final List<LoginBean>	loginList;
	
	public LoginAnalyzeData( List<LoginBean>	loginList ){
		this.loginList = loginList;
		this.name = loginList.get(0).getRoleId();
		this.nickName = loginList.get(0).getNick();
		
		analyzer();
	}

	private void analyzer() {
		level = NewFighterSql.getLevel(name);//等级
		calcLoginCount();
		calcTodayIsLogin();
		calcIsRegistYesterday();
		calcLiveTomorrow();		//这个必须放最后，依赖于前面的计算结果
		
	}
	
	/**
	 * 计算玩家是否指定日期注册
	 * @param time
	 * @return
	 */
	public boolean isRegister( LocalDate time ){
		LoginBean l = loginList.get(0);
		if( !l.isLogin() ){
			throw new RuntimeException( nickName + "的第一次信息是登出信息");
		}
		LocalDate registerDate = new LocalDate(l.getTime());
//		System.out.println( new DateTime(l.getTime()) );
		return time.equals( registerDate );
	}
	
	/**
	 * 计算玩家在指定日期是否登陆
	 * @param time
	 * @return
	 */
	public boolean isLogin( LocalDate time ){
		for( LoginBean l : loginList){
			if( l.isLogin() && new LocalDate(l.getTime()).equals(time)){
				DateTime registerDate = new DateTime(new UserCreateTime().getCreateTime(l.getNick()));
//				System.out.println( l.getNick() + "\t" + new DateTime(l.getTime()) + "\t" + registerDate);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 计算玩家是否是否昨日注册
	 */
	private void calcIsRegistYesterday(){
		LoginBean l = loginList.get(0);
		if( !l.isLogin() ){
			throw new RuntimeException( nickName + "的第一次信息是登出信息");
		}
		int registDay = Util.Time.getDay(l.getTime());
		if( Util.Time.getCurrentDay() -1 == registDay ){
			isRegistYesterday = true;
			return;
		}
		isRegistYesterday = false;
	}
	
	/**
	 * 玩家是否昨日创建，并且今日还登陆了的
	 */
	private void calcLiveTomorrow() {
		
		if( isRegistYesterday() && isLoginToday ){
			liveTomorrow = true;
			return;
		}
		liveTomorrow = false;
		
	}

	/**
	 * 今日是否登陆
	 */
	private void calcTodayIsLogin() {
		for( LoginBean l : loginList){
			if( l.isLogin() && Util.Time.getCurrentDay()== Util.Time.getDay(l.getTime())){
				isLoginToday = true;
				return;
			}
		}
		isLoginToday = false;
		
	}

	/**
	 * 计算所有登陆次数
	 */
	private void calcLoginCount() {
		for( LoginBean l : loginList){
			if( l.isLogin() ){
				loginCount++;
			}
		}
		
		
	}

	/**
	 * 获取最后一次登陆,直接从最后两个数据中获取
	 * 优化的事情不急 
	 */
	private LoginBean getLastLogin(){
		int size = loginList.size();
		
		if( loginList.get(size-1).isLogin() ){
			return loginList.get(size-1);
		}
		return loginList.get(size-2);
	}

	public String getName() {
		return name;
	}

	public String getNickName() {
		return nickName;
	}

	public boolean isLoginToday() {
		return isLoginToday;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public boolean isLiveTomorrow() {
		return liveTomorrow;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return name + " " + nickName + " " + isLoginToday + " " + loginCount + " " + liveTomorrow + " " + level;
	}

	/**
	 * @return isRegistYesterday
	 */
	public boolean isRegistYesterday() {
		return isRegistYesterday;
	}

	
	

}
