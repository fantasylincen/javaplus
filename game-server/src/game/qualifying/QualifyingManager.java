package game.qualifying;



import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import define.DefaultCfg;
import define.GameData;
import define.GameDataProvider;
import user.UserInfo;
import user.UserManager;
import util.RandomUtil;
import util.SystemTimer;

/**
 * 排位赛管理
 * @author DXF
 */
public class QualifyingManager {

	private QualifyingBase								qualifyingBase;
	
	private UserInfo									user;
	private final QualifyingDataProvider 				db 				= QualifyingDataProvider.getInstance();	

	public QualifyingManager( UserInfo user ){
		this.user 		= user;
		qualifyingBase 	= db.get( user );
		if( qualifyingBase == null ){
			qualifyingBase = new QualifyingBase();
			db.add( user, qualifyingBase );
		}
		qualifyingBase.setBattlefields( db.getBattlefields( user ) );
		Inabatch();
	}

	public QualifyingBase getQualifying() {
		return qualifyingBase;
	}

	/** 点击换一批 */
	public void Inabatch() {
		
		List<Challenges> result = qualifyingBase.getChallenge();
		result.clear();
		
		if( GameData.ranking.isEmpty() ) {
			List<UserInfo> ls			= UserManager.getInstance().getMemoryAllUser();
			for( UserInfo u : ls ){
				if( u.getLevel() < DefaultCfg.NEED_RANKING_LEVEL ) continue;
				
				Challenges c = new Challenges();
				c.setUid( u.getUID() );
				c.setCdTime( 0 );
				result.add( c );
				if( result.size() >= 3 ) break;
			}
			if( result.isEmpty() && GameData.ranking.indexOf( user.getUID() ) == -1 ){
				GameData.ranking.add( user.getUID() );
				GameDataProvider.getInstance().updateRanking();
			}
			
			return;
		}
		
		int rank 	= GameData.ranking.indexOf( user.getUID() );
		int max 	= rank == -1 ? GameData.ranking.size() : rank;
		int min 	= (max - 299) < 0 ? 0 : (max - 299);
		
		List<Integer> temp = new ArrayList<Integer>();
		while( min++ <= (max - 1) ) temp.add( min - 1 );
		
		while( !temp.isEmpty() ){
			int index 	= RandomUtil.getRandomInt( 0, temp.size()-1 );
			int uid 	= GameData.ranking.get( temp.remove(index) );
			if( uid == user.getUID() ) continue; // 不能筛选自己
			
			Challenges c = new Challenges();
			c.setUid( uid );
			c.setCdTime( 0 );
			result.add( c );
			if( result.size() >= 3 ) break;
		}
	}
 
	/**
	 * 挑战后的处理
	 * @param b 是否主动挑战方
	 * 	0被动挑战胜利：排名不变。
			1主动挑战失败：排名不变。
			2被动挑战失败：排名下降至xxx名。
			3主动挑战胜利：排名上升至xxx名。
	 * @param isWin
	 * @param ranking 对方排名
	 * @param data 
	 * @param quilt ( 对方 )
	 */
	public void handle( boolean isWin, ByteBuffer data, UserInfo quilt ) {
		
		qualifyingBase.reResidueDegree();
		if( isWin ) qualifyingBase.reStandings();
		
		int rank1 	= GameData.ranking.indexOf( user.getUID() );
		int rank2	= GameData.ranking.indexOf( quilt.getUID() );
		
		// 主动挑战失败：排名不变。  被动挑战胜利：排名不变。
		if( isWin ){
			if( rank2 == -1 ) {
				GameData.ranking.add( user.getUID() );
				qualifyingBase.setIsGetAward( true );
				
			// 这里做下判断 必须比自己高才能赋值  因为有可能会比他低
			}else if( rank2 < rank1 || rank1 == -1 ){
				GameData.ranking.set( rank2, user.getUID() );
				if( rank1 == -1 )
					GameData.ranking.add( quilt.getUID() );
				else
					GameData.ranking.set( rank1, quilt.getUID() );
				quilt.getQualifyingManager().Inabatch();
			}
			
			// 重新换一批
			Inabatch();
		}else{
			for( Challenges c : qualifyingBase.getChallenge() )
				c.setCdTime( SystemTimer.currentTimeSecond() );
		}
		
		// 下面加入战报
		rank1 	= GameData.ranking.indexOf( user.getUID() );
		rank2	= GameData.ranking.indexOf( quilt.getUID() );
		data.flip();
		if( isWin ){
			addBattlefield( (byte)3, data, rank1, quilt );
			quilt.getQualifyingManager().addBattlefield( (byte)2, data, rank2, user );
		} else {
			addBattlefield( (byte)1, data, rank1, quilt );
			quilt.getQualifyingManager().addBattlefield( (byte)0, data, rank2, user );
		}
	}
	
	/**
	 * 加入战报
	 * @param b
	 * @param data
	 * @param rank 
	 */
	private void addBattlefield( byte b, ByteBuffer data, int rank, UserInfo quilt ) {
		BattlefieldReport battle = new BattlefieldReport();
		battle.type = b;
		battle.data = data;
		battle.time	= SystemTimer.currentTimeSecond();
		battle.rank	= rank;
		battle.quiltUID = quilt.getUID();
		if( qualifyingBase.battlefieldIsMax() ){
			updataBattle( battle );
		}else{
			addBattle( battle );
		}
	}

	private void addBattle( BattlefieldReport battle ) {
		battle.uid = user.getBasisUniqueID().QPVPINFO_ID();
		db.addBattlefield( user, battle );
		qualifyingBase.addBattlefield( battle );
	}
	private void updataBattle( BattlefieldReport battle ) {
		qualifyingBase.restBattlefield( battle );
		db.updateBattlefield( user, battle );
	}

	public void update() {
		db.update( user, qualifyingBase );
	}
	
	
}

