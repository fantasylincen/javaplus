package cn.mxz.bossbattle.ui;

import java.util.List;

import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.IRankInfo;
import cn.mxz.bossbattle.ISimpleChallenger;

import com.google.common.collect.Lists;

public class RankInfoImpl implements IRankInfo{

	
	private ISimpleChallenger			killer;
	private ISimpleChallenger			myself;
	private List<ISimpleChallenger>		list = Lists.newArrayList();
	
	public RankInfoImpl( List<Challenger> topTenList, Challenger killer,Challenger me, int myRank ) {
		
		int rank = 1;
		for( Challenger c : topTenList ){
			
			SimpleChallengerImpl sc = new SimpleChallengerImpl( c, rank++ );
			list.add( sc );
			if( c == killer ){
				sc.setReputation();
			}
		}
		if( killer != null ){
			this.killer = new SimpleChallengerImpl( killer, -1 );
			
		}
		
		this.myself = new SimpleChallengerImpl( me, myRank );
		
	}


	@Override
	public List<ISimpleChallenger> getTopChallenger() {
		return list;
	}


	@Override
	public ISimpleChallenger getKiller() {
		return killer;
	}


	@Override
	public ISimpleChallenger getMyself() {
		return myself;
	}
	
	

}

