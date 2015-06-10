package server.event;

import http.Response;
import http.UserData;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

import org.xsocket.connection.INonBlockingConnection;

import com.alibaba.fastjson.JSON;

import util.UtilBase;
import events.EventBase;

public class GetUserdataEvent extends EventBase{

	private ConcurrentHashMap<Integer,Response> responses 	= new ConcurrentHashMap<Integer, Response>();
	
	@Override
	public void run( INonBlockingConnection con, String... args ) throws IOException {
	}

	@Override
	public void run( INonBlockingConnection con, ByteBuffer buf ) throws IOException {
		
		int uid 	= buf.getInt();
		String name = UtilBase.decodeString(buf);
		int level 	= buf.getShort();
		
		Response response = get( uid );
		if( response == null ) return;
		
		UserData data = new UserData();
		data.setUid( name.isEmpty() ? -1 : uid );
		data.setName( name );
		data.setLevel( level );
		
		String jsonString = JSON.toJSONString(data);
		response.sendString(jsonString);
	}

	public void run(INonBlockingConnection con, int uid, Response response) throws IOException {
		
		putResponse( uid, response );
		
		ByteBuffer buf  = buildEmptyPackage( 256 );
		buf.putInt( uid );
		sendPackage( con, buf );
	}

	private void putResponse( int uid, Response response ){
		responses.put( uid, response );
	}
	
	private Response get( int uid ){
		return responses.remove( (Integer)uid );
	}
}
