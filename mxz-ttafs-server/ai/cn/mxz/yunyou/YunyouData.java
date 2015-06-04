package cn.mxz.yunyou;


import java.util.Comparator;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.RoamAwardTemplet;
import cn.mxz.RoamAwardTempletConfig;
import cn.mxz.RoamMarketPlaceTemplet;
import cn.mxz.RoamMarketPlaceTempletConfig;



/**
 * 根据id逆序排列
 * @author Administrator
 *
 */
class SortById implements Comparator<YunyouData>{

	@Override
	public int compare(YunyouData o1, YunyouData o2) {
		return o2.getId() - o1.getId();
	}
}




/**
 * 保存玩家的每一次遇到云游仙人的数据
 * @author Administrator
 *
 */
public class YunyouData {

	public static final Comparator<YunyouData> ID_COMPARATOR = new SortById();


	private int				propId;//特价商品
	private int				maxPropCount;//特价商品的最大数量，前端显示用
	private int				propCount;//特价商品的数量，如果已经购买则需要设置为0
	private int				needGold;//特价商品的价格
	private int				needCash;//特价商品的价格
	private int				level;//仙人等级
	private int				heroId;//被指点的英雄
	private int				id;//唯一id
	private int				endSec;//结束时间

	public int getId(){
		return id;
	}


	public YunyouData() {

	}


	public int getPropId() {
		return propId;
	}

	public int getPropCount() {
		return propCount;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * 是否已经买过了
	 * @return
	 */
	public boolean isBuy() {
		return getPropCount() == 0;
	}

	/**
	 * 设置特价商品剩余的数量
	 */
	public void setRemainCount() {
		setPropCount(getPropCount() - 1 );
	}

	public YunyouData( int heroId, int id ) {
		RoamAwardTemplet t = getXianRenTemplet();
		setLevel(t.getStep());
		int  coldDownSecond= t.getTime();

		RoamMarketPlaceTemplet rt = getGoods();
		setPropId(rt.getTypeId());
		setPropCount(Random.get( rt.getMin(), rt.getMax() ));
		maxPropCount = propCount;
		setNeedGold(rt.getCouponsRoam());
		needCash = rt.getCashRoam();

		this.setHeroId(heroId);
		this.setId(id);
		int current = (int) (System.currentTimeMillis() / 1000);
		endSec = coldDownSecond + current;
	}

	/**
	 * 指点是否结束
	 * true		指点结束
	 * false	指点未结束
	 * @return
	 */
	boolean isExpire(){

//		String scope = "10:00 to 12:30 or 14:00 to 16:39";
//		活动刘昆
//		boolean in = Util.Time.isIn(new Date(), scope);

		int ret = getRemainSec();
		return ret == 0;
	}

	/**
	 * 指点剩余的秒数,如果为负数，则返回0
	 * @return
	 */
	int getRemainSec(){
		int current = (int) (System.currentTimeMillis() / 1000);
		int ret = endSec - current;
		ret = Math.max( ret, 0 );
		return ret;
//		return (int) (System.currentTimeMillis() / 1000) - endSec > 0 ? (int) (System.currentTimeMillis() / 1000) - endSec : 0;
	}

	int calcExp( int heroLevel ){
		RoamAwardTemplet t = RoamAwardTempletConfig.get( getLevel() );
		int exp = t.getExp();
		AuthorityTemplet at = AuthorityTempletConfig.get( heroLevel );
		exp *= at.getExpaddition();
		return exp;
	}

	/**
	 * 根据权重随机获取仙人
	 * @return
	 */
	private RoamAwardTemplet getXianRenTemplet() {

		WeightFetcher<RoamAwardTemplet> weightAble = new WeightFetcher<RoamAwardTemplet>() {

			@Override
			public Integer get(RoamAwardTemplet t) {
				return t.getWeight();
			}
		};
		RoamAwardTemplet templet = Util.Random.getRandomOneByWeight(RoamAwardTempletConfig.getAll(), weightAble  );
		return templet;
	}

	/**
	 * 获取特价商品的模板
	 * @return
	 */
	private RoamMarketPlaceTemplet getGoods() {
		WeightFetcher<RoamMarketPlaceTemplet> weightAble = new WeightFetcher<RoamMarketPlaceTemplet>() {

			@Override
			public Integer get(RoamMarketPlaceTemplet t) {
				return t.getWeight();
			}
		};
		//根据权重取一列数据
		RoamMarketPlaceTemplet rt = Util.Random.getRandomOneByWeight(RoamMarketPlaceTempletConfig.getAll(), weightAble);
		return rt;
	}

	/**
	 * @return the needGold
	 */
	public int getNeedGold() {
		return needGold;
	}
	/**
	 * @return heroId
	 */
	public int getHeroId() {
		return heroId;
	}


	/**
	 * @return begintSec
	 */
	public int getEndSec() {
		return endSec;
	}


	/**
	 * @param begintSec 要设置的 begintSec
	 */
	public void setEndSec(int begintSec) {
		this.endSec = begintSec;
	}


	/**
	 * @param propId 要设置的 propId
	 */
	public void setPropId(int propId) {
		this.propId = propId;
	}


	/**
	 * @param propCount 要设置的 propCount
	 */
	public void setPropCount(int propCount) {
		this.propCount = propCount;
	}


	/**
	 * @param needGold 要设置的 needGold
	 */
	public void setNeedGold(int needGold) {
		this.needGold = needGold;
	}


	/**
	 * @param level 要设置的 level
	 */
	public void setLevel(int level) {
		this.level = level;
	}


	/**
	 * @param heroId 要设置的 heroId
	 */
	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}


	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 购买所需金币
	 * @return
	 */
	public int getNeedCash() {
		return needCash;
	}


	/**
	 * @return maxPropCount
	 */
	public int getMaxPropCount() {
		return maxPropCount;
	}


	/**
	 * @param maxPropCount 要设置的 maxPropCount
	 */
	public void setMaxPropCount(int maxPropCount) {
		this.maxPropCount = maxPropCount;
	}

}
