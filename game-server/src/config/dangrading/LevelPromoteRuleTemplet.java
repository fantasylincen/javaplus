package config.dangrading;

import game.pvp.DanGrad;

import org.jdom2.Element;

public class LevelPromoteRuleTemplet {
	
	private final DanGrad 		_danGrad;
	
	private final int			_gradeConditions;
	
	private final int	 		_goldConditions;
	
	private final int 			_boonAttack;
	
	private final int			_boonHP;
	
	private final float 		_boonCrit;

	private final float 		_boonTenacity;
	
	private final short 		_boonStrlimit;
	
	private final int 			_boonCrystal;
	

	public LevelPromoteRuleTemplet(Element element) {
		
		_danGrad 			= DanGrad.fromNumber( Byte.parseByte( element.getChildText( "id" ) ) );
		_gradeConditions	= Integer.parseInt( element.getChildText( "gradeConditions" ) );
		_goldConditions		= Integer.parseInt( element.getChildText( "goldConditions" ) );
		_boonAttack			= Integer.parseInt( element.getChildText( "boonAttack" ) );
		_boonHP				= Integer.parseInt( element.getChildText( "boonHP" ) );
		_boonCrit			= Float.parseFloat( element.getChildText( "boonCrit" ) );
		_boonTenacity		= Float.parseFloat( element.getChildText( "boonTenacity" ) );
		_boonStrlimit		= Short.parseShort( element.getChildText( "boonStrlimit" ) );
		_boonCrystal		= Integer.parseInt( element.getChildText( "boonCrystal" ) );
	}

	public DanGrad getDanGrad() {
		return _danGrad;
	}

	public int getGradeConditions(){
		return _gradeConditions;
	}
	
	public int getGoldConditions(){
		return _goldConditions;
	}
	
	public int getBoonAttack(){
		return _boonAttack;
	}
	
	public int getBoonHP(){
		return _boonHP;
	}
	
	public float getBoonCrit(){
		return _boonCrit;
	}
	
	public float getBoonTenacity(){
		return _boonTenacity;
	}
	
	public short getBoonStrlimit(){
		return _boonStrlimit;
	}
	
	public int getBoonCrystal(){
		return _boonCrystal;
	}
	
	
}
