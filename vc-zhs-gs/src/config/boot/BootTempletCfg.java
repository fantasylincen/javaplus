package config.boot;

import game.award.AwardInfo;
import game.award.AwardType;
import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import manager.DWType;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import user.UserInfo;
import define.SystemCfg;

public class BootTempletCfg {
	
	private static final List<BootTemplet> data					= new ArrayList<BootTemplet>();
	
	private static final String FILE 							= SystemCfg.FILE_NAME + "resource/Booth.xml";
	
	
	/**
	 * 通过配置表读取职业暴击
	 */
	public static void init(){
		
		data.clear();
		
		File file = new File( FILE );
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( file );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "xml" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				BootTemplet templet = new BootTemplet( element );
				
				data.add(templet);
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "引导赠送 配置文件\t> 解析完毕" );
		
	}
	
	public static BootTemplet get( short level ){
		for( BootTemplet b : data ){
			if( b.getId() == level )
				return b;
		}
		return null;
	}

	/**
	 * 执行引导赠送
	 * @param user
	 * @param level
	 */
	public static void run(UserInfo user, short level) {
		
		BootTemplet boot = get( level );
		
		// 先看是否满足条件
		if( boot == null ) return;
		if( boot.getAward().isEmpty() ) return;
		
		// 开始赠送
		for( AwardInfo a : boot.getAward() ){
			
			AwardInfo xx	= new AwardInfo(a);
			// 这里特殊处理一下
			if( xx.getAward() == AwardType.HERO && xx.getPropId() == -1 ){
				xx.setPropId( user.getTeamManager().getCaptain().getNid() );
			}
			
			user.changeAward( xx, "新手引导赠送", DWType.MISCELLANEOUS );
		}
		
	}
	
	
}
