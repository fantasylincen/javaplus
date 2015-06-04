package frontanimation;


import game.battle.auto.AutoBattle;
import game.battle.auto.Formation;
import game.battle.auto.ParseBattleSituation;
import game.battle.formation.IFormation;
import game.fighter.FighterBase;
import game.fighter.Hero;
import game.pvp.PvpMateManager;

import java.nio.ByteBuffer;
import java.util.List;

import config.critofjob.CritOfJobTempletCfg;
import config.fighter.HeroTempletCfg;
import config.fighter.NpcFighterTempletCfg;
import config.front_animation.FrontAnimationTempmletCfg;
import config.skill.PassiveSkillTempletCfg;
import config.skill.accord.SkillTempletCfg;
import config.skill.captain.CaptainSkillTempletCfg;

import define.GameDataProvider;


/**
 * 开场动画
 * @author DXF
 */
public class FrontAnimation {
	
	private static final FrontAnimation instance = new FrontAnimation();
	public static final FrontAnimation getInstance(){
		return instance;
	}
	
	
	GameDataProvider db			= GameDataProvider.getInstance();
	
	ByteBuffer data[] 			= null;
	
	private FrontAnimation() {	
		data = db.getFA();
		if( data[0] == null || data[1] == null ){
			createFA();
			db.addFA( data );
		}
	}
	
	public ByteBuffer get( byte at ){
		return data[at-1];
	}
	
	private void create( int at ){
		ByteBuffer buffer 				= ByteBuffer.allocate( 10240 );
		List<Hero> a					= FrontAnimationTempmletCfg.getHero( at );
		
		List<FighterBase> attackers 	= PvpMateManager.getMateReadyToHero( a );
		List<FighterBase> defenses 		= FrontAnimationTempmletCfg.getEnemy( at );
		// 初始化 双方阵型
		IFormation aFormation 			= new Formation( attackers, true );
		IFormation dFormation 			= new Formation( defenses, false );
		
		// 开始把信息放入 自动回合战斗系统
		AutoBattle battle 				= new AutoBattle( aFormation, dFormation, null, null, null, null, false );
		
		// 这里重新覆盖一次数据 因为 可能有队长技能加血量的
		List<FighterBase> fighter_a		= battle.getAttackHeroData();
		List<FighterBase> fighter_d		= battle.getDefenseHeroData();
		putHeroData( a, fighter_a, buffer );
		
		buffer.put( (byte) fighter_d.size() );
		for( FighterBase f : fighter_d ){
			buffer.putInt( f.getId() );
			buffer.put( f.getPosition() );
		}
		
		// 开始战斗
		battle.run( true );
		
		ByteBuffer content 				= battle.getBattleSituation().getData().asReadOnlyBuffer();
		content.flip();
		buffer.put( content );
		
		ByteBuffer xx			= buffer.asReadOnlyBuffer();
		xx.flip();
		buffer.flip();
		data[at] 					= ByteBuffer.allocate( xx.limit() );
		data[at].put( buffer );
	}
	private void createFA() {
		
		data = new ByteBuffer[2];
		FrontAnimationTempmletCfg.init();
		
		create( 0 );
		create( 1 );
	}
	
	private void putHeroData( List<Hero> a, List<FighterBase> fighter, ByteBuffer response ) {
		response.put( (byte)a.size() ); /////////////////////
		for( Hero hero : a ){
			int HP	= getHP( fighter, hero.getPosition() );
			if( HP != hero.getHpMax() && HP != 0 ){
				hero.setHp( HP );
				hero.setHpMax( HP );
			}
			
			response.putInt( hero.getUID() );
			response.putInt( hero.getNid() );
			response.putShort( hero.getLevel() );
			response.putInt( hero.getExp() );
			hero.getQuality().toByte( response );
			response.put( hero.getPosition() );
			response.putInt( hero.getHpMax() );
			response.putInt( hero.getAttack() );
			response.put( (byte) 0 );
			response.put( (byte) (hero.getIsCaptain() ? 1 : 0) );
			response.put( hero.getSkillAttack().getLevel() );
			response.putInt( hero.getCaptainSkill().getID() );
		}
	}
	
	private int getHP(List<FighterBase> fighter, byte position) {
		for( FighterBase f : fighter ){
			if( f.getPosition()%6 == position )
				return f.getHpMax();
		}
		return 0;
	}
	
	
	public static void main( String[] args ){
		
		SkillTempletCfg.init();
		// 被动技能  此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能）
		PassiveSkillTempletCfg.init();
		// 队长技能  此配置表必须先于NpcFighterTempletCfg初始化（每个npc都有相应的技能）
		CaptainSkillTempletCfg.init();
		// npc战士 此配置表必须先于MissionTempletCfg初始化，因此无需提前手动调用
		NpcFighterTempletCfg.init();
		// hero
		HeroTempletCfg.init();
		CritOfJobTempletCfg.init();
		
		instance.createFA();
		instance.data[0].flip();
		
		ByteBuffer content1 	= instance.get( (byte) 1 ).asReadOnlyBuffer();
		System.out.println( content1.capacity() );
		byte len = content1.get();
		System.out.println( "我方出战英雄个数:" + len );
		for( int i = 0; i < len; i++ ){
			System.out.println( content1.getInt() );
			System.out.println( content1.getInt() );
			System.out.println( content1.getShort() );
			System.out.println( content1.getInt() );
			System.out.println( content1.get() ); System.out.println( content1.get() );
			System.out.println( content1.get() );
			System.out.println( content1.getInt() );
			System.out.println( content1.getInt() );
			System.out.println( content1.get() );
			System.out.println( content1.get() );
			System.out.println( content1.get() );
			System.out.println( content1.getInt() );
		}
		
		len = content1.get();
		System.out.println( "敌方出战英雄个数:" + len );
		for( int i = 0; i < len; i++ ){
			System.out.println( content1.getInt() );
			System.out.println( content1.get() );
		}
		new ParseBattleSituation( content1 ).parse();
	}
	
}
