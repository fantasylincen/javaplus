package config.aa;

import game.award.AwardInfo;
import game.award.AwardType;
import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import manager.DWType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import user.UserInfo;

import define.SystemCfg;

public class ActivityAwardTempletCfg {
	
	private static final List<Award> data1 = new ArrayList<Award>();
	private static final List<Award> data2 = new ArrayList<Award>();
	private static final List<Award> data3 = new ArrayList<Award>();
	private static final List<Award> data4 = new ArrayList<Award>();
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/ActivityAward1.xml";
	
	public static void init(){
		
		data1.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				float id 			= Float.parseFloat( element.getChildText( "id" ) );
				String[] content	= element.getChildText( "content" ).split( "\\|" );
				
				Award a 			= new Award();
				a.id 				= id;
				a.award				= new ArrayList<AwardInfo>();
				
				for( String str : content ){
					String[] award 						= str.split(",");
					AwardType 			atype 			= AwardType.valueOf( award[0] );
					int					propId			= Integer.parseInt( award[1] );
					short				number			= Short.parseShort( award[2] );
					AwardInfo awardInfo					= new AwardInfo( atype, propId, number );
					int size 							= award.length - 3;
					if( size > 0 ){
						int[] 			arguments		= new int[size];
						for( int j = 0; j < size; j++ ){
							arguments[j]				= Integer.parseInt( award[3+j] );
						}
						awardInfo.setArguments( arguments );
					}
					
					a.award.add(awardInfo);
				}
				
				data1.add( a );
			}
			
			Collections.sort( data1, comparator1 );
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	
		data2.clear();
		file 	= new File( SystemCfg.FILE_NAME + "resource/ActivityAward2.xml" );
		builder = new SAXBuilder();    
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				float id 			= Float.parseFloat( element.getChildText( "id" ) );
				String[] content	= element.getChildText( "content" ).split( "\\|" );
				
				Award a 			= new Award();
				a.id 				= id;
				a.award				= new ArrayList<AwardInfo>();
				
				for( String str : content ){
					String[] award 						= str.split(",");
					AwardType 			atype 			= AwardType.valueOf( award[0] );
					int					propId			= Integer.parseInt( award[1] );
					short				number			= Short.parseShort( award[2] );
					AwardInfo 			awardInfo		= new AwardInfo( atype, propId, number );
					int size 							= award.length - 3;
					if( size > 0 ){
						int[] 			arguments		= new int[size];
						for( int j = 0; j < size; j++ ){
							arguments[j]				= Integer.parseInt( award[3+j] );
						}
						awardInfo.setArguments( arguments );
					}
					
					a.award.add( awardInfo );
				}
				
				data2.add( a );
			}
			
			Collections.sort( data2, comparator2 );
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		data3.clear();
		file 	= new File( SystemCfg.FILE_NAME + "resource/ActivityAward3.xml" );
		builder = new SAXBuilder();    
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				float id 			= Float.parseFloat( element.getChildText( "id" ) );
				String[] content	= element.getChildText( "content" ).split( "\\|" );
				
				Award a 			= new Award();
				a.id 				= id;
				a.award				= new ArrayList<AwardInfo>();
				
				for( String str : content ){
					String[] award 						= str.split(",");
					AwardType 			atype 			= AwardType.valueOf( award[0] );
					int					propId			= Integer.parseInt( award[1] );
					short				number			= Short.parseShort( award[2] );
					AwardInfo awardInfo					= new AwardInfo( atype, propId, number );
					int size 							= award.length - 3;
					if( size > 0 ){
						int[] 			arguments		= new int[size];
						for( int j = 0; j < size; j++ ){
							arguments[j]				= Integer.parseInt( award[3+j] );
						}
						awardInfo.setArguments( arguments );
					}
					
					a.award.add(awardInfo);
				}
				
				data3.add( a );
			}
			
			Collections.sort( data3, comparator2 );
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		data4.clear();
		file 	= new File( SystemCfg.FILE_NAME + "resource/ActivityAward4.xml" );
		builder = new SAXBuilder();    
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				float id 			= Float.parseFloat( element.getChildText( "id" ) );
				String[] content	= element.getChildText( "content" ).split( "\\|" );
				
				Award a 			= new Award();
				a.id 				= id;
				a.award				= new ArrayList<AwardInfo>();
				
				for( String str : content ){
					String[] award 						= str.split(",");
					AwardType 			atype 			= AwardType.valueOf( award[0] );
					int					propId			= Integer.parseInt( award[1] );
					short				number			= Short.parseShort( award[2] );
					AwardInfo awardInfo					= new AwardInfo( atype, propId, number );
					int size 							= award.length - 3;
					if( size > 0 ){
						int[] 			arguments		= new int[size];
						for( int j = 0; j < size; j++ ){
							arguments[j]				= Integer.parseInt( award[3+j] );
						}
						awardInfo.setArguments( arguments );
					}
					
					a.award.add(awardInfo);
				}
				
				data4.add( a );
			}
			
			Collections.sort( data4, comparator1 );
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		Logs.debug( "活动奖励 配置文件\t> 解析完毕" );
	}

	public static void shouChong( float moeny, UserInfo user ) {
		for( int i = 0; i < data2.size(); i++ ){
			Award a = data2.get(i);
			if( a.id <= moeny ){
				for( AwardInfo xx : a.award )
					user.changeAward( xx, "活动赠送", DWType.MISCELLANEOUS );
				return;
			}
		}
	}

	public static void xinKai( UserInfo user ) {
		for( int i = 0; i < data1.size(); i++ ){
			Award a = data1.get(i);
			if( user.xinkai < a.id && a.id <= user.getRechargeMoney() ){
				for( AwardInfo xx : a.award )
					user.changeAward( xx, "活动赠送", DWType.MISCELLANEOUS );
				user.xinkai = a.id;
//				return;
			}
		}
	}
	public static void xinKai1( UserInfo user ) {
		for( int i = 0; i < data3.size(); i++ ){
			Award a = data3.get(i);
			if( user.xinkai1 < a.id && a.id <= user.getRechargeMoney1() ){
				for( AwardInfo xx : a.award )
					user.changeAward( xx, "活动赠送", DWType.MISCELLANEOUS );
				user.xinkai1 = a.id;
//				return;
			}
		}
	}
	public static void xinKai2( UserInfo user ) {
		for( int i = 0; i < data4.size(); i++ ){
			Award a = data4.get(i);
			if( user.christmasRecharge < a.id && a.id <= user.getRechargeMoney2() ){
				for( AwardInfo xx : a.award )
					user.changeAward( xx, "活动赠送", DWType.MISCELLANEOUS );
				user.christmasRecharge = a.id;
//				return;
			}
		}
	}
	
	public static final Comparator<Award> comparator2 = new Comparator<Award>(){
        @Override
        public int compare( Award f1, Award f2 ) {
            return (int) (f2.id - f1.id);
        }
    };
    public static final Comparator<Award> comparator1 = new Comparator<Award>(){
        @Override
        public int compare( Award f1, Award f2 ) {
            return (int) (f1.id - f2.id);
        }
    };
    
    
    public static void main( String[] args ){
    	SystemCfg.init();
    	init();
    	for( int i = 0; i < data4.size(); i++ ){
    		
    		System.out.println( data4.get(i).id );
    		System.out.println( data3.get(i).id );
    	}
    }


    
}

class Award{
	
	float id;
	
	List<AwardInfo> award;
}

