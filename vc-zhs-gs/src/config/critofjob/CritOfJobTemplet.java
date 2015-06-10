package config.critofjob;


import org.jdom2.Element;

import util.RandomUtil;

import config.fighter.Professional;

public class CritOfJobTemplet {

	private final Professional 				p;
	
	// 70 20 7 3
	private final float[][]					o	= new float[4][4];
	
	public CritOfJobTemplet( Element element ) {
		
		p			= Professional.fromNumber( Integer.parseInt( element.getChildText( "id" ) ) ) ;
		o[0]		= maping( element.getChildText( "odds_70" ) ); 
		o[1]		= maping( element.getChildText( "odds_20" ) ); 
		o[2]		= maping( element.getChildText( "odds_7" ) ); 
		o[3]		= maping( element.getChildText( "odds_3" ) ); 
	}

	private float[] maping( String content ){
		
		
		if( content.isEmpty() )
			throw new RuntimeException( "职业对应暴击表格出错 content=" + content );
		String[] list 	= content.split( "," );
		if( list.length != 4 )
			throw new RuntimeException( "职业对应暴击表格出错 content=" + content );
		
		float[] odds 	= new float[4];
		
		try {
			for( int i = 0; i < list.length; i++ )
				odds[i]		= Float.parseFloat( list[i] );
		} catch ( Exception e ) {
			throw new RuntimeException( "职业对应暴击表格出错 at=" + p.toNumber() );
		}
		
		
		return odds;
	}
	
	public Professional getProfessional() {
		return p;
	}
	
	/**
	 * 获得暴击伤害
	 * @return
	 */
	public float[] getCriticalDamage(){
		
		int rand 		= RandomUtil.getRandomInt( 0, 9999 );
		int[] number 	= { 7000, 2000, 700, 300 };
		
		int i			= 0;
		for( ; i < number.length; i++ ){
			rand		-= number[i];
			if( rand < 0 ) break; 
		}
		
		return o[i];
	}
	
}
