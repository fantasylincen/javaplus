package manager;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public enum DWType {

	MISCELLANEOUS( "其他","其他" ),
	
	PREPAID_PHONE_FOR( "充值获得","其他" ),
	
	ATLASLOOT( "副本掉落 获得","其他" ),
	
	SYSTEM_IS_PRESENTED( "系统赠送 获得","其他" ),
	
	PURCHASE_OF_PHYSICAL( "购买体力 消耗", "购买体力" ),
	
	HERO_OF_THE_BACKPACK( "英雄背包上限 消耗","购买英雄背包上限" ),
	
	EQUIPMENT_BACKPACK( "装备背包上限 消耗","购买装备背包上限" ),
	
	BUY_FRIENDS_LIMIT( "购买好友上限 消耗","购买好友上限" ),
	
	TALENT_TO_ACCELERATE( "天赋加速 消耗","其他" ),
	
	CAPTAIN_SKILLS( "队长技能 消耗","刷新队长技能" ),
	
	BUYING_COPY_NUMBER( "购买副本次数 消耗","其他" ),
	
	PRAYERS_FOR_A_SINGLE( "祈福单次 消耗", "单抽" ),
	
	PRAYERS_FOR_TEN_TIMES( "祈福十次 消耗", "10连抽" ),
	
	EQUIPPED_WITH_SYNTHETIC( "装备合成 消耗","其他" ),
	
	BUY_MATCHING_NUMBER( "购买匹配次数 消耗", "购买PVP" ); 
	
	private String desc;
	private String type;
	DWType( String desc ) {
		this.desc = desc;
	}
	DWType( String desc, String type ) {
		this.desc = desc;
		this.type = type;
	}
	public String getType(){
		return type;
	}
	
	private static final Map<Integer, DWType> numToEnum = new HashMap<Integer, DWType>();
	static{
		for( DWType a : values() ){
			numToEnum.put( a.ordinal(), a );
		}
	}
	public static DWType fromNumber( int n ){
		return numToEnum.get( n );
	}
	
	public static void main(String[] args) {
		for( DWType code : values() ){
			System.out.println( "[" + code.ordinal() + "]\t[" + code + "]\t(" + code.desc + ")" );
		}
		
		Element rootElt 		= new Element("explain");
        for( DWType code : values() ){
        	
        	Element selectElt 	= new Element("xml");  
        	Element idElt		= new Element("id");
            Element descElt		= new Element("desc");
            idElt.addContent( code.ordinal() + "" );  
            descElt.addContent( code.desc );
            
            selectElt.addContent( idElt );  
            selectElt.addContent( descElt );
            
            rootElt.addContent( selectElt );
        }
        
        Document doc = new Document( rootElt );
          
        try {  
        	XMLOutputter out 	= new XMLOutputter();   
            FileWriter writer 	= new FileWriter("D:/手游-英雄之城/前后端通信协议/explain.xml");
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
