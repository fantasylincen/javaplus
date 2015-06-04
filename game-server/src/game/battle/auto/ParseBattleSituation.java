package game.battle.auto;

import game.battle.formation.IFormation;

import java.nio.ByteBuffer;

import config.skill.accord.SkillTempletCfg;

/**
 * 用于解析战况的，主要用于验证战斗模式是否正确
 * @author DXF
 * 2013-2-28 下午3:29:17
 */
public class ParseBattleSituation {

	IFormation 				attackers = null;
	IFormation				defenders = null;
	ByteBuffer 				data;
	
//	private IFormation getFriend( FighterBase fighter ){
//		return fighter.isBottom() ? attackers : defenders;
//	}
	public ParseBattleSituation( IFormation aFormation, IFormation dFormation, BattleSituation situation ) {
		attackers 	= aFormation;
		defenders 	= dFormation;
		data 		= situation.getData();
		data.flip();
	}
	
	public ParseBattleSituation( ByteBuffer situation ) {
		data 		= situation;
//		data.flip();
	}

//	private FighterBase getFighterByPos( byte pos ){
//		for( FighterBase f : attackers.getAllFighters() ){
//			if( f.getPosition() == pos ){
//				return f;
//			}
//		}
//		for( FighterBase f : defenders.getAllFighters() ){
//			if( f.getPosition() == pos ){
//				return f;
//			}
//		}
//		return null;
//	}
	/**
	 * 解析战况信息
	 */
	public void parse( ) {
//		List<FighterBase> all = new ArrayList<FighterBase>();
//		all.addAll( attackers.getAllFighters() );
//		all.addAll( defenders.getAllFighters() );
		
//		for( int i = 0; i < 18; i++ ){
//			BaseFighter f = all.get( i );
//			if( f != null ){
//				System.out.println( f.getName() );
//			}
//			else{
//				
//			}
//		}
		int endPosition = data.limit();
		System.out.println( "战况内容的长度为：" + endPosition );
		//int i = 0;
		
//		String ret = "攻\t防\t命      暴    格\t伤害\t反击\t";
//		System.out.println( ret );
		
//		short code = data.getShort();
//		System.out.println( "验证：" + code );
//		if( code != 0 ) return;
			
		short fightCount = data.getShort();
		System.out.println( "战斗次数：" + fightCount );
		
		for( int i = 0; i < fightCount; i++ )
		{
			byte at = data.get();
			if( at == 100 ){
				parseRound();
				at = data.get();
			}
			
			int type = data.getInt();
			parseSkillAttack( at , type );
		}
		
		byte iswin		= data.get();
		System.out.println( "是否胜利：" + iswin );
		
		if( attackers == null )
			return;
		
		byte trophy		= data.get();
		System.out.println( "奖杯数量：" + trophy );
		
		byte count		= data.get();
		String strDrop	= "最后掉落：" + count + "[";
		for( int i = 0; i < count; i++ ){
			byte type 	= data.get();
			if( type == 6 ){
				data.get();
				data.get();
			}
			int	Id		= data.getInt();
			int num		= data.getInt();
			strDrop		+= type + "," + Id + "," + num + "|";
		}
		System.out.println( strDrop + "]" );
	}

	private void parseSkillAttack(byte at, int skillId ) {
		
		if( skillId == 0 )
			return;
		
		byte count 				= data.get();
        StringBuilder output 	= new StringBuilder("<" + at + ">\t" + SkillTempletCfg.getSkillTempletById(skillId).getName() + "\t" );
		for( int i = 0; i < count; i++ ){
			byte defenderPos = data.get();
			output.append( "<" + defenderPos + ">" );
			
			AttackInfo info = new AttackInfo( data.get() );
			output.append( "[" + info.isHit() );
			
			if( !info.isHit() ) {
				output.append( "]\t" );
				continue;
			}
			
			int hpDamage = data.getInt();
			output.append( "(hp:" + hpDamage + "x" + info.getCrit() ).append( parseDrop() + "]\t" );
		}
		System.out.println( output.toString() );
		
	}

	private void parseRound() {
		System.out.println( "----------------------------------------------------------------" );
	}

	private String parseDrop()
	{
		boolean isDie	= data.get() == 1 ? true : false;
		
		String str		= "{" + isDie;
		if( isDie ){
			byte count	= data.get();
			str			+= "[";
			for( int i = 0; i < count; i++ ){
				
				byte type 	= data.get();
				if( type == 6 )
				{
					data.get();
					data.get();
				}
				int	Id		= data.getInt();
				int num		= data.getInt();
				str			+= type + "," + Id + "," + num + "|";
			}
			str			+= "]";
		}
		return str + "}";
	}
}
