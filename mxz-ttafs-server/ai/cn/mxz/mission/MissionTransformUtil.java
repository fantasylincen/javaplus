package cn.mxz.mission;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Multimap;




public class MissionTransformUtil {
	
	
//	Multimap<Integer, MissionNode> fromDB( String json ){
//		Multimap<Integer, MissionNode> result = ArrayListMultimap.create();
//		
//		return result;
//		
//	}
//	
//	
	/**
	 * 从程序格式转换为数据库需要的格式
	 * @param map
	 * @return
	 */
	 public static String toDB( Multimap<Integer, MissionNode> data ){
		 for( MissionNode node : data.values() ){//确保是linkedlist，遍历顺序必须和插入顺序一致
			 System.out.println( JSON.toJSONString( node ) );
		 }
		return "";
		
		
	}
//

	/**
	 * 从数据库格式转换为程序需要的格式
	 * @param map
	 * @return
	 */
	public static Multimap<Integer, MissionNode> fromDB(String map) {
		
		return null;
		
	}
}
