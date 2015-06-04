package config.mission;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import config.fighter.NpcFighterTempletCfg;

import define.DefaultCfg;
import define.SystemCfg;

public class InstanceTempletCfg {
	private static final Map<Short,InstanceTemplet> missions = new HashMap<Short, InstanceTemplet>();
	
	static{
	}
	
	private static final String FILE = SystemCfg.FILE_NAME + "resource/Instance.xml";
	
	/** 初始 普通副本id号  	这里应该是关卡ID */
	public static short FIRST_POINTS_ID[];
	
		
	/**
	 * 通过配置表读取主线关卡模板
	 */
	public static void init(){
		
		missions.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				InstanceTemplet templet = new InstanceTemplet( element );
			
				/*******************关闭打印****************************
							System.out.println( templet );
				********************************************************/
				
				InstanceTemplet temp = missions.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "主线任务" + temp.getId() + "重复了" );
				}
				
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		// 加载完后 设置默认第一个ID
		try {
			FIRST_POINTS_ID = DefaultCfg.getEctypFirstID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			traverseID();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Logs.debug( "副    本 配置文件\t> 解析完毕" );
		
	}
	
	/**
	 * 通过id获取主线任务
	 * @param templetId
	 * @return
	 */
	public static InstanceTemplet getTempletById( short templetId ){
		return  missions.get( templetId );
	}
	
	/**
	 * 找出下一个副本
	 * @param id  
	 * @return
	 */
	public static InstanceTemplet getNext( short id ) {
		InstanceTemplet in = getTempletById( id );
		return getToIdTwo( (short) (in.getIdTwo() + 1), in.getType() );
	}
	
	private static InstanceTemplet getToIdTwo( short s, EctypeType type ) {
		for( InstanceTemplet in : missions.values() ){
			if( in.getType() == type && in.getIdTwo() == s ) return in;
		}
		return null;
	}

	// 遍历一下ID 是否自增
	private static void traverseID() throws IOException {
		
//		short id = FIRST_POINTS_ID[0];
//
//		for( int i = 0; i < missions.values().size(); i++ )
//		{
//			if( getTempletById( (short)(id + i) ) == null )
//				throw new RuntimeException( "副本ID不是自增  at=" + (id + i - 1) + " 后面" );
//		}
//		
//		for( InstanceTemplet instance : missions.values() ){
//			
//			if( instance.getType() == EctypeType.COMMON ){
//				
//				
//			}
//			
//		}
	}
	
	
	public static void main(String[] args) {
		NpcFighterTempletCfg.init();
		init();
		System.out.println( getTempletById( (short)1006 ) );
		
	}



}
