package experiment;



import java.io.IOException;

import lua.Lua;
import lua.LuaProxy;

import org.apache.log4j.PropertyConfigurator;
import org.keplerproject.luajava.LuaException;

import define.SystemCfg;


public class luaTest {
	public static void main( String[] args ) throws IOException, LuaException{
		PropertyConfigurator.configureAndWatch( "log4jconfig/log4j.properties" );
		SystemCfg.init();
		LuaProxy lua = Lua.createLuaState( "gameData.lua" );
		Object[] ret = lua.retArray( 7, "getAllBuyNeedCrystal", 
				0,
				0, 
				0,
				0,
				0,
				0,
				0
				);
		
		for( int i = 0; i < ret.length; i++ ){
			System.out.println( (int)(double)ret[i] );
		}
		
//		LuaState L = LuaStateFactory.newLuaState();
//        L.openLibs();
//        L.pushObjectValue( Logs.class );
//		L.setGlobal( "logs" );
//		
//        File file = new File("lua/gameData.lua");
//        InputStream isread = new FileInputStream( file );
//        int len = isread.available();
//        byte[] luaByte = new byte[len];
//        isread.read(luaByte);
//        isread.close();
//       	String str = new String( luaByte, "utf-8" );
//        
//       	int error = L.LdoString( str );
//        
////        int error = L.LdoFile( "lua/gameData.lua" );
//        if (error != 0) {
//            System.out.println("Read/Parse lua file error. Exit.");
//            return;
//        }
//        
//        //找到函数test
//        L.getField(LuaState.LUA_GLOBALSINDEX, "test");
//        //参数1压栈
//        L.pushNumber(1);
//        //参数2压栈
//        L.pushNumber(2);
//        //调用!! 一共两个参数, 1个返回值 PANIC: unprotected error in call to Lua API (attempt to call a nil value)
//        L.call(2, 1);
//        //保存返回值, 到a中
//        L.setField(LuaState.LUA_GLOBALSINDEX, "a");
//        //读入a
//        LuaObject l = L.getLuaObject("a");
//        //打印结果.
//        System.out.println("Result is " + l.getString());
//
//        L.close();
		
	}
}
