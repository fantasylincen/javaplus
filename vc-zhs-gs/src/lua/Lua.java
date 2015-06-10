package lua;

import game.log.Logs;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;


/**
 * lua管理类
 * @author deng
 */
public class Lua {
	
	private LuaState luaState;
	protected Lua(){ initialize(); }
	
	/**
	 * 创建一个lua
	 * @param name
	 * @return
	 */
	public static LuaProxy createLuaState( String name ){
		LuaProxy lua = new LuaProxy();
//		lua.initialize();
		lua.registerObject( Logs.class, "logs" );
		lua.getLuaState().LdoFile( "lua/" + name );
		return lua;
	}
	
	private void initialize(){
		luaState = LuaStateFactory.newLuaState();
		luaState.openLibs();
	}
	
	public LuaState getLuaState(){ return luaState ; }
	public void close() { luaState.close(); }
	
	
	/**
	 * 为lua里面注册对象
	 * @param obj ( 对象 )
	 * @param name ( 在lua中调用名 )
	 */
	public void registerObject( Object obj, String name ){
		try {
			luaState.pushObjectValue( obj );
			luaState.setGlobal( name );
		} catch (LuaException e) { e.printStackTrace(); }
	}
	
	/**
	 * 运行函数 (一个返回值)
	 * @param function 函数名
	 * @param args 参数
	 */
	protected Object runFunction( String function, Object ... args ){
		
		// 获取lua中的函数
		luaState.getField( LuaState.LUA_GLOBALSINDEX, function );
		
		// 参数压栈
		int len = args.length;
		try {
			for( int i = 0; i < len; i++ )
				luaState.pushObjectValue( args[i] );
		} catch (LuaException e) { e.printStackTrace(); }
	
		// 调用!! 一共len个参数, 1 返回值
		luaState.call( len, 1 );
		
		// 保存返回值, 到 ret 中
		luaState.setField(LuaState.LUA_GLOBALSINDEX, "ret" );
		LuaObject lo = luaState.getLuaObject( "ret" );
		return toObject( lo );
	}
	
	/**
	 * 运行函数 (多个返回值)
	 * @param retlen 返回值个数
	 * @param function 函数名
	 * @param args 参数
	 */
	protected Object[] runFunction( int retlen, String function, Object ... args ){
		
		// 获取lua中的函数
		luaState.getField( LuaState.LUA_GLOBALSINDEX, function );
		
		// 参数压栈
		int len = args.length;
		try {
			for( int i = 0; i < len; i++ )
				luaState.pushObjectValue( args[i] );
		} catch (LuaException e) { e.printStackTrace(); }
	
		// 调用!! 一共len个参数, retlen 返回值
		luaState.call( len, retlen );
		
		Object[] ret = new Object[retlen];
		// 保存返回值, 到 ret 中
		for( int i = 0; i < retlen; i++ ){
			luaState.setField(LuaState.LUA_GLOBALSINDEX, "ret" + i );
			ret[retlen-i-1] = toObject( luaState.getLuaObject( "ret" + i ) );
		}
		return ret;
	}
	
	
	private Object toObject( LuaObject lo ){
		try {
			if( lo.isBoolean() )
				return lo.getBoolean();
			if( lo.isNumber() )
				return lo.getNumber();
			if( lo.isString() )
				return lo.getString();
			if( lo.isJavaObject() )
				return lo.getObject();
			if( lo.isNil() )
				return null;
		} catch (LuaException e) { e.printStackTrace(); }
		return null;
	}
	
}
