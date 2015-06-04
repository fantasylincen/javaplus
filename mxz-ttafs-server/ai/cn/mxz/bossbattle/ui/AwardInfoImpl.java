package cn.mxz.bossbattle.ui;

import java.util.List;

import cn.mxz.bossbattle.IAwardInfo;
import cn.mxz.bossbattle.IRankInfo;
import cn.mxz.bossbattle.Prize;

public class AwardInfoImpl implements IAwardInfo {

	private IRankInfo 			rankinfo;
	private int					bossHpMax;
	private boolean				bossIsDie;
	private List<Prize>			prize;
	/**
	 * 在领奖中心的id
	 */
	private int					id;
	//private 
	
	
	public AwardInfoImpl() {
//		rankInfo = new RankInfoImpl( killer, me ) ;
//		bossHpMax = boss.getHpMax();
//		bossIsDie = boss.
	}

	

	public void setRankinfo(IRankInfo rankinfo) {
		this.rankinfo = rankinfo;
	}

	public void setBossHpMax(int bossHpMax) {
		this.bossHpMax = bossHpMax;
	}

	public void setBossIsDie(boolean bossIsDie) {
		this.bossIsDie = bossIsDie;
	}

	public void setPrize(List<Prize> prize) {
		this.prize = prize;
	}

	@Override
	public int getBossHpMax() {
		return bossHpMax;
	}

	@Override
	public boolean getBossIsDie() {
		return bossIsDie;
	}

	@Override
	public IRankInfo getRankInfo() {
		return rankinfo;
	}

	
	@Override
	public List<Prize> getPrize() {
		return prize;
	}



	@Override
	public int getIdInPrizeCenter() {
		return id;
	}
	
	public void setIdInPrizeCenter( int id ) {
		this.id = id;
	}

}
