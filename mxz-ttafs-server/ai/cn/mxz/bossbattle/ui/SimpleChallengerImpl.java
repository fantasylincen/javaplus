package cn.mxz.bossbattle.ui;

import mongo.gen.MongoGen.SimpleChallengerDto;
import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.ISimpleChallenger;
import define.D;


public class SimpleChallengerImpl implements ISimpleChallenger {

	
//	private  String		userId;
//	private  String		nickName;
//	private  int		reputation;
//	private  int		allDamage;
//	private  int		rank;
	SimpleChallengerDto	dto; 

	/**
	 * 由于前端显示需要，这里需要减去最后一击所得声望
	 * @param reputation
	 */
	public void setReputation(  ){
		dto.setReputation( dto.getReputation() - D.BOSS_KILLER_SHENGWANG );
	}
	
	public SimpleChallengerImpl(Challenger c, int rank ) { 
		dto = new SimpleChallengerDto();
		dto.setAllDamage(c.getAllDamage());
		dto.setUserId(c.getUser().getId()  );
		dto.setNick(c.getUser().getPlayer().getNick());
		
		dto.setReputation(  c.getAward().getReputation() );
		dto.setRank(rank);
//		this.userId = c.getUser().getId();
//		this.nickName = c.getUser().getPlayer().getNick();
//		this.reputation = c.getAward().getReputation();
//		this.allDamage = ;
//		this.rank = rank;
	}
	public SimpleChallengerImpl(SimpleChallengerDto killer) {
		dto = killer;
	}
	public String getUserId() {
		return dto.getUserId();
	}
	public String getNick() {
		return dto.getNick();
	}
	public int getReputation() {
		return dto.getReputation();
	}
	public int getAllDamage() {
		return dto.getAllDamage();
	}
	public int getRank() {
		return dto.getRank();
	}

	public SimpleChallengerDto getDto() {
		return dto;
	}

//	@Override
//	public String getUserId() {
//		return userId;
//	}
//
//	@Override
//	public String getNick() {
//		return nickName;
//	}
//
//	@Override
//	public int getReputation() {
//		return reputation;
//	}
//	
//
//	@Override
//	public int getAllDamage() {
//		return dto.getAllDamage();
//	}
//
//	@Override
//	public int getRank() {
//		return rank;
//	}

}
