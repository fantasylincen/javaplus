package game.log;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 日志类型
 * @author DXF
 */
public enum L {

	L_000( "0", 	"其他"			, 		"无" ) ,
	L_001( "1", 	"创建用户"		, 		"用户ID,用户名字,终端标识" ) ,
	L_002( "2", 	"用户登陆"		, 		"用户ID,用户名字,终端标识,是否拥有月卡" ) ,
	L_003( "3", 	"用户退出"		, 		"用户ID,用户名字,本次在线时间(秒)" ) ,
	L_004( "4", 	"水晶操作"		, 		"用户ID,用户名字,物品类型,变化值,调用的说明" ) ,
	L_005( "5|0",	"副本战斗"		, 		"用户ID,用户名字" ) ,
	L_006( "5|1",	"活动玩法"		, 		"用户ID,用户名字" ) ,
	L_007( "5|2",	"升级操作"		, 		"用户ID,用户名字" ) ,
	L_008( "5|3",	"进化操作"		, 		"用户ID,用户名字" ) ,
	L_009( "5|4",	"装备合成操作"	, 		"用户ID,用户名字" ) ,
	L_010( "5|5",	"匹配战斗"		, 		"用户ID,用户名字" ) ,
	L_011( "6",		"每小时在线人数"	, 		"当前在线人数" ) ,
	L_012( "7",		"没天最大在线人数"	, 		"当前最大在线人数,记录时间" ),
	L_013( "8",		"充值"			, 		"用户ID,用户名字,充值额度,是否新增用户,表格ID,人民币,订单号" ),
	L_014( "9",		"月卡充值"		, 		"用户ID,用户名字,充值额度,是否新增用户,表格ID,人民币,订单号" ),
	L_015( "10",	"七日流失率"		, 		"流失数" );
	
	private final String 			id;
	private final String 			name;
	private final String 			desc;
	
	L( String ... args ) {
		id		= args[0];
		name	= args[1];
		desc	= args[2];
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getDesc(){
		return desc;
	}
	
	private static final Map<String, L> numToEnum = new HashMap<String, L>();
	static{
		for( L a : values() ){
			numToEnum.put( a.id, a );
		}
	}
	public static L fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	public static void main( String[] args ){
		
		for( L code : values() ){
			System.out.println( "[" + code.id + "]\t(" + code.desc + ")" );
		}
		
		Element rootElt 		= new Element("LogType");
        for( L code : values() ){
        	
        	Element selectElt 	= new Element("xml");
        	
        	Element idElt		= new Element("id");
            Element nameElt		= new Element("name");
            Element descElt		= new Element("desc");
            
            idElt.addContent( code.id );  
            nameElt.addContent( code.name );
            descElt.addContent( code.desc );
            
            selectElt.addContent( idElt );  
            selectElt.addContent( nameElt );
            selectElt.addContent( descElt );
            
            rootElt.addContent( selectElt );
        }
        
        Document doc = new Document( rootElt );
          
        try {  
        	XMLOutputter out 	= new XMLOutputter();   
            FileWriter writer 	= new FileWriter("D:/手游-英雄之城/前后端通信协议/LogType.xml");
            Format f			= Format.getPrettyFormat();
            out.setFormat(f);
            out.output( doc, writer );
            writer.close();
           
            System.out.println( "已生成XML表格 ！" );
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
	}
	
}


