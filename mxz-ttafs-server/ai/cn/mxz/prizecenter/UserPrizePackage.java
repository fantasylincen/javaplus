package cn.mxz.prizecenter;

import java.util.List;

import cn.mxz.bag.SuperBagSnapsort;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.mission.old.PrizeImpl;

import com.google.common.collect.Lists;

public class UserPrizePackage implements IUserPrizePackage {

	private static final PropIdCheck	PROP_ID_CHECKER = new PropIdCheck();
	private  int						type;//2   boss 3	闯阵  	4	月卡
	/**
	 * 奖励描述
	 */
	private  String						desc;

	private List<PrizeImpl>				prizes = Lists.newArrayList();

	private int							endTime;

	private int							createTime;

	private String						title;


	/**
	 * 活动id
	 */
	private int							id;
	
	/**
	 * 此奖励是否来自于蓝港,缺省不是蓝港
	 */
	private boolean						isLingkong = false;;

	/* 
	 * 用于客户端显示，避免java强制转换
	 */
	@Override
	public List<IPrize> getPrizes() {
		List<IPrize> temp = Lists.newArrayList();
		
		temp.addAll( prizes );
		return temp;
	}

	/**
	 *
	 * @param prizeStr
	 * @param desc
	 * @param type
	 */
	public UserPrizePackage( String prizeStr, String desc, int type, String title, int endSecond ) {
		this.setType( type );
		parse( prizeStr );
		this.setDesc(desc);
		setEndTime(endSecond);
		setCreateTime((int) (System.currentTimeMillis() / 1000));
		this.setTitle(title);
	}
//	public UserPrizePackage(String prizeStr, String desc, int type, String title ) {
//		this( prizeStr, desc, type, title,  (int) (new DateTime().plusMonths( 100).getMillis()/ 1000) );
//	}
	
	@SuppressWarnings("unchecked")
	public UserPrizePackage(List<? extends Prize> prizes, String desc, int type, String title, int endSecond ) {
		this.setType(type);
		this.prizes = (List<PrizeImpl>) prizes;
		this.setDesc(desc);
		setEndTime( endSecond );
		setCreateTime((int) (System.currentTimeMillis() / 1000));
		this.setTitle(title);
	}
	

	public UserPrizePackage() {
		// TODO 自动生成的构造函数存根
	}

	private void parse(String prizeStr) {
		String[] prizeArr = prizeStr.split( "," );
		for( int i = 0; i < prizeArr.length;){
			int id = Integer.parseInt( prizeArr[i++] );
			int count = Integer.parseInt( prizeArr[i++] );
			PrizeImpl p = new PrizeImpl( id, count );
			if( checkIdValid( id ) ){
				prizes.add(p);
			}
			else{
				System.err.println( id + "是无效的奖品id" );
			}
		}
	}

	/**
	 * 判断此道具id是否存在
	 * @param id
	 * @return
	 */
	private boolean checkIdValid( int id ){
		return PROP_ID_CHECKER.check( id );
	}

	
	/**
	 * 获得奖励
	 * @param user
	 */
	public void getPrize( City user ) {

		SuperBagSnapsort b = new SuperBagSnapsort();
		b.snapsort(user);

		FighterSnapshoot f = new FighterSnapshoot(user);
		f.snapshoot();

		for (IPrize prize : getPrizes()) {
			prize.award( user.getPlayer() );
		}

		b.snapsort(user);
		f.snapshoot();
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getDesc()
	 */
	@Override
	public String getDesc() {
		return desc;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getEndTime()
	 */
	@Override
	public int getEndTime() {
		return endTime;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getType()
	 */
	@Override
	public int getType() {
		return type;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getCreateTime()
	 */
	@Override
	public int getCreateTime() {
		return createTime;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * @param prizes 要设置的 prizes
	 */
	public void setPrizes(List<PrizeImpl> prizes) {
		this.prizes = prizes;
	}

	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param title 要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param desc 要设置的 desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/* （非 Javadoc）
	 * @see cn.mxz.prizecenter.IUserPrizePackage#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserPrizePackage [type=" + type + ", desc=" + desc + ", prizes=" + prizes + ", endTime=" + endTime + ", createTime=" + createTime + ", title=" + title + ", id=" + id + "]";
	}

	/**
	 * @return isLingkong
	 */
	@Override
	public boolean getIsLingkong() {
		return isLingkong;
	}

	/**
	 * @param isLingkong 要设置的 isLingkong
	 */
	public void setLingkong(boolean isLingkong) {
		this.isLingkong = isLingkong;
	}

	

}
