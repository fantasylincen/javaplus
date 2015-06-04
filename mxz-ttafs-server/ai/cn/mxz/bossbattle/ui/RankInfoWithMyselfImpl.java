package cn.mxz.bossbattle.ui;

import java.util.List;

import cn.mxz.bossbattle.Challenger;
import cn.mxz.bossbattle.IRankInfoWithMyself;
import cn.mxz.bossbattle.ISimpleChallenger;

import com.google.common.collect.Lists;

public class RankInfoWithMyselfImpl implements IRankInfoWithMyself{

	
	private ISimpleChallenger			myself;
	private List<ISimpleChallenger>		list = Lists.newArrayList();
	
	public RankInfoWithMyselfImpl( List<Challenger> topTenList, Challenger myself ) {
		
		int rank = 1;
		for( Challenger c : topTenList ){
			ISimpleChallenger sc = new SimpleChallengerImpl( c, rank++ );
			list.add( sc );
		}
		this.myself = new SimpleChallengerImpl( myself, -1 );
	}

	@Override
	public List<ISimpleChallenger> getTopChallenger() {
		return list;
	}

	@Override
	public ISimpleChallenger getMyself() {
		// TODO 自动生成的方法存根
		return myself;
	}

}

