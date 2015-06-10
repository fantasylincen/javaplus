package game.fighter;

import game.skill.AccordSkill;
import game.skill.CommonSkill;
import config.fighter.NpcFighterTemplet;


public class NpcFighter extends FighterBase {

	public NpcFighter( NpcFighterTemplet templet ) {
		
		setId( templet.templetId );
		
		setIsMonster( true );
		setName( templet.name );
		setProfessional( templet.professional );
		
		setHpMax( templet.hpBase );
		setHp( templet.hpBase );
		
		setAttackType( templet.attackType );
		setAttack( templet.attackBase );
		
		setCommonAttack( new CommonSkill( templet.commonAttack ) );
		setSkillAttack( new AccordSkill( templet.skillAttack, (byte)1 ) );
		setPassiveSkill( templet.passiveSkill );
		setCaptainSkill( null ); // 怪物没有队长技能
		
		setDodge( templet.dodge );
		setCommonCrit( templet.commonCrit );
		setSkillCrit( templet.skillCrit );
		
		// 掉落
		setAwardContent( templet.content );
		
		// 是否精英
		setIsElite( templet.qualityBase.isElite() );
	}

	public NpcFighter() {
		setId( 0 );
	}

	public void clone( NpcFighter templet ) {
		setId( templet.getId() );
		
		setIsMonster( true );
		setName( templet.getName() );
		setProfessional( templet.getProfessional() );
		
		setHpMax( templet.getHpMax() );
		setHp( templet.getHpMax() );
		
		setAttackType( templet.getAttackType() );
		setAttack( templet.getAttack() );
		
		setCommonAttack( templet.getCommonAttack() );
		setSkillAttack( templet.getSkillAttack() );
		setPassiveSkill( templet.getPassiveSkill() );
		setCaptainSkill( null ); // 怪物没有队长技能
		
		setDodge( templet.getDodge() );
		setCommonCrit( templet.getCommonCrit() );
		setSkillCrit( templet.getSkillCrit() );
		
		// 掉落
		setAwardContent( templet.getAwardContent() );
		
		// 是否精英
		setIsElite( templet.getIsElite() );
	}
	
	
}
