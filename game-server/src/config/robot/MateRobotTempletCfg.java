package config.robot;

import game.fighter.Hero;
import game.log.Logs;
import game.pvp.MatchingType;
import game.util.fighting.FightingFormula;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import util.RandomUtil;

import define.SystemCfg;

/**
 * 匹配机器人
 * @author DXF
 */
public class MateRobotTempletCfg {

	private static final List<MateRobotTemplet> green = new ArrayList<MateRobotTemplet>();
	
	private static final List<MateRobotTemplet> blue = new ArrayList<MateRobotTemplet>();
	
	private static final List<MateRobotTemplet> purple = new ArrayList<MateRobotTemplet>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/MateRobot.xml";
	
	public static void init(){
		
		green.clear();
		blue.clear();
		purple.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				MateRobotTemplet templet = new MateRobotTemplet( element );
				
				switch( templet.type ){
				case GREEN_CARD:
					green.add(templet);
					break;
				case BLUE_CARD:
					blue.add(templet);
					break;
				case PURPLE_CARD:
					purple.add(templet);
					break;
				}
				
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "机 器 人 配置文件\t> 解析完毕" );
		
	}

	/**
	 * 根据战斗力 和颜色 获取对应 机器人
	 * @param fighting
	 * @param type
	 * @return
	 */
	public static MateRobotTemplet get( float fighting, MatchingType type, boolean isRound ) {
		
		List<MateRobotTemplet> list 	= get( type );
		if( list == null || list.isEmpty() ) return null;
		
		List<MateRobotTemplet> reslut	= new ArrayList<MateRobotTemplet>();
		
		for( MateRobotTemplet r : list ){
			
			float fig	= getFighting( r.list );
			float f		= (1f - ( fig / fighting )) * 100f;
			
			if( (f > 0 && isRound) || (f < 0) && !isRound ) 
				continue;
			f			= Math.abs( f );
			
			if( f <= 500 )
				reslut.add(r);
		}
		
		// 这里随机一个出来
		if( !reslut.isEmpty() ){
			int rand 	= RandomUtil.getRandomInt(0, reslut.size() - 1) ;
			return reslut.get(rand);
		}
		
		return list.get(0);
	}

	private static int getFighting(List<Hero> list) {
		int result = 0;
		for( Hero h : list )
			result += FightingFormula.formula( h );
		return result;
	}

	private static List<MateRobotTemplet> get(MatchingType type) {
		switch( type ){
		case GREEN_CARD:
			return green;
		case BLUE_CARD:
			return blue;
		case PURPLE_CARD:
			return purple;
		}
		return null;
	}
	
}
