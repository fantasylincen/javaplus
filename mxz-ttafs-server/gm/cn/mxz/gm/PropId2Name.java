package cn.mxz.gm;

import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.prizecenter.PropIdCheck;

/**
 * 把道具id转为名字，用户客户端查询
 * @author Administrator
 *
 */
public class PropId2Name  extends AbstractHandler {

	String ret;
	
	PropId2Name(){
		//{
//	    "options": [
//	                "Option 1",
//	                "Option 2",
//	                "Option 3",
//	                "Option 4",
//	                "Option 5"
//	            ]
//	        }
		ret = "var propName = [";
		for( Entry<String, Integer> entry : PropIdCheck.name2Id.entrySet() ){
			//System.out.print( "\"" + entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + entry.getKey() + "\"," );
			ret += "\"";
			ret += entry.getValue();
//			ret += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			ret += "[";
//			ret += "                   ";
			ret += entry.getKey();
			
			ret += "]\",";
			
		}
		if( ret.length() > 1 ){
			ret =  ret.substring( 0, ret.length() - 1 );
		}
		ret += "];";
		
		//ret = "var ss = [\"Deluxe Bicycle\", \"Super Deluxe Trampoline\", \"Super Duper Scooter\"];";
		//ret = "{\"options\": [\"Option 1\",\"Option 2\",\"Option 3\",\"Option 4\",\"Option 5\"]}";
		//137409&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丙级九彩游梦服卷轴",
	}
	
	@Override
	protected String doGet(Map<String, Object> parameters) {
//		System.out.println( "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		return ret;
	}
	


}
