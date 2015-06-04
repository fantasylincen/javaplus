package cn.mxz.prizecenter;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.BasePropTemplet;
import cn.mxz.BasePropTempletConfig;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

public class PropIdCheck {

	private static final String	TEMPLET	= "Templet";
	private static final String	CONFIG	= "Config";
	private static final String	PACKAGE	= "cn.mxz.";
	
	/**
	 * 系统中所有有效的id
	 */
	private static Set<Integer> allId = Sets.newTreeSet();
	public static BiMap<String, Integer> name2Id = HashBiMap.create();
	//private static BiMap<String, Integer> name2Id = HashBiMap.create(); 
	
	
	static{
		init();
	}

	private static void init() {
		for( BasePropTemplet bp : BasePropTempletConfig.getAll() ){
			String xmlFile = bp.getXml();
			String className = getClassName( xmlFile );
			//System.out.println( className );
			Class<?> cfg = null;
			try{
				cfg = Class.forName(className );
				
				Method m = cfg.getMethod("getAll");
				
				List<?> ret = (List<?>) m.invoke( null );
				
				for (Object obj : ret) {
					Method method = obj.getClass().getMethod("getId");
					Object id = method.invoke(obj);
					
					if( allId.add((Integer) id) ){
					
						method = obj.getClass().getMethod("getEndName");
						Object name = method.invoke(obj);
						if( (Integer)id == 136644 ){
							//System.out.println( id + " " + name );
						}
						name2Id.put((String)name, (Integer) id);
//						if( className.equals("cn.mxz.StuffTempletConfig"))
//							System.out.println( id + "\t" + name );
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
		    }
		}
		//Collections.sor
		//System.out.println( allId );
		//System.out.println( "size " + allId.size() );
	}
	
	/**
	 * 根据名字获得道具的ID
	 * @param propName
	 * @return
	 */
	public static int getId(String propName) {
		Integer id = name2Id.get(propName);
		if(id == null) {
			throw new NullPointerException(propName);
		}
		return id;
	}
	
	public static String getName(int propId) {
		return name2Id.inverse().get(propId);
	}
	
	boolean check( int id ){
		
//		int idHead = getIdHead(id);
//		BasePropTemplet bp = BasePropTempletConfig.get( idHead );
//		String xmlFile = bp.getXml();
//		String className = getClassName( xmlFile );
//		return check( id, className );
		return allId.contains( id );
		
	}
	

	private static String getClassName(String xmlFile) {
		int pos = xmlFile.indexOf( CONFIG );
		
		String className = xmlFile.substring( 0, pos );
		className += TEMPLET;
		className += CONFIG;
		String fullClassName = PACKAGE + className;
		//System.out.println( fullClassName );
		return fullClassName;
	}
//
//	private boolean check(int id, String fullClassName) {
//		Class<?> cfg = null;
//		try{
//			cfg = Class.forName(fullClassName );
//			
//			Method m = cfg.getMethod("get", Integer.class);
//			Integer idObj = id;
//			Object invoke = m.invoke(null, idObj );
//			if(invoke == null) {
//				return false;
//			}
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//	    }
//		return true;
//	}
//
//	/**
//	 * 获取id的头两位，方便确定该在什么表内查找id的有效性
//	 * 
//	 * @return
//	 */
//	private int getIdHead( int id ){
//		String s = id + "";
//		s = s.substring( 0, 2 );
//		return Integer.parseInt( s );
//		
//			
//	}
//	
	public static void main(String[] args) {
//		System.out.println( new PropIdCheck().check(110001) );
//		FighterTempletConfig.get(10);
		for( Entry<String, Integer> entry : PropIdCheck.name2Id.entrySet() ){
			System.out.print( "\"" + entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + entry.getKey() + "\"," );
		}
		
//		System.out.println( getName(136644));
//		for( Entry<String, Integer> entry : PropIdCheck.name2Id.entrySet() ){
//			System.out.println( entry.getKey() + "	nz	1000" );
//		}
		
		
	}
	
	public static Set<Integer> getAllId() {
		return allId;
	}
	
	public static BiMap<String, Integer> getName2Id() {
		return name2Id;
	}
	
}
