package config.recharge;

import game.log.Logs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import define.SystemCfg;

public class RechargeTempletCfg {
	
	private static final List<RechargeTemplet> data = new ArrayList<RechargeTemplet>();
	
	private static final String FILE 				= SystemCfg.FILE_NAME + "resource/Recharge.xml";
	
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
				
				RechargeTemplet templet = new RechargeTemplet( element );
				
				data.add( templet );
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			Logs.error( e.getMessage() );
		}   
		
		Logs.debug( "充值信息 配置文件\t> 解析完毕" );
	}
	
	
	public static RechargeTemplet get( short id ){
		
		for( RechargeTemplet r : data ){
			if( r.getId() == id ) return r;
		}
		
		return null;
	}

	/**
	 * 根据商品ID 获取对应
	 * @param goodsId
	 * @return
	 */
	public static RechargeTemplet get( String goodsId ) {
		
		for( RechargeTemplet r : data ){
			if( r.getGoodsId()[0].equals( goodsId ) || r.getGoodsId()[1].equals( goodsId ) || r.getGoodsId()[2].equals( goodsId ) ) 
				return r;
		}
		
		return null;
	}


	public static String getRestrictionToString() {
		
		StringBuilder content = new StringBuilder();
		
		for( int i = 0; i < data.size(); i++ ){
			
			RechargeTemplet r = data.get(i);
			
			if( (int)(r.getId()*0.01) == 3 )
				content.append( r.getId() ).append( "," );
		}
		
		return content.toString();
	}

	
}
