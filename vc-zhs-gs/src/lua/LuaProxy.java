package lua;


/**
 * lua代理
 * @author deng
 */
public class LuaProxy extends Lua{
	
	public byte retByte( String function, Object ... args ){
		return (byte)retInteger( function, args );
	}
	public short retShort( String function, Object ... args ){
		return (short)retInteger( function, args );
	}
	public int retInteger( String function, Object ... args ){
		double ret = (double) runFunction( function, args );
		return (int)ret;
	}
	public float retFloat( String function, Object ... args ){
		double ret = (double) runFunction( function, args );
		return (float)ret;
	}
	public double retDouble( String function, Object ... args ){
		double ret = (double) runFunction( function, args );
		return ret;
	}
	public Object retObject( String function, Object ... args ){
		return runFunction( function, args );
	}
	public String retString( String function, Object ... args ){
		return (String)runFunction( function, args );
	}
	public Object[] retArray( int retlen, String function, Object ... args ){
		return runFunction( retlen, function, args );
	}
	
}
