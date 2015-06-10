package server.event;

import http.RScorr;
import http.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

import org.xsocket.connection.INonBlockingConnection;

import util.UtilBase;

import com.alibaba.fastjson.JSON;

import events.EventBase;

public class InformRechargeEvent extends EventBase{

	private static final String[] desc = {
		"操作成功", 	// 0 成功
		"无效参数",	// 1 无效参数
		"订单重复",	// 2 订单重复
		"月卡上限",	// 3 月卡上限
	};
	
	
	private ConcurrentHashMap<Integer,Response> responses 	= new ConcurrentHashMap<Integer, Response>();
	
	
	@Override
	public void run(INonBlockingConnection con, String... args) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(INonBlockingConnection con, ByteBuffer buf) throws IOException {

		int uid		= buf.getInt();
		short code 	= buf.getShort();
		
		Response response = get( uid );
		if( response == null ) return;
		
		String jsonString = JSON.toJSONString( new RScorr( code == 0 ? "success" : "error", desc[code] ) );
		response.sendString(jsonString);
	}

	public void run(INonBlockingConnection con, int did, int uid, String goodsId, float money, int pt, String tcd,
			int st, Response response) throws IOException {
		
		putResponse( uid, response );
		
		ByteBuffer buf  = buildEmptyPackage( 1024 );
		buf.putInt( uid );
		UtilBase.encodeString( buf, goodsId );
		buf.putFloat( money );
//		buf.putInt( did );
		buf.putInt( pt );
		UtilBase.encodeString( buf, tcd );
		sendPackage( con, buf );
	}
	
	private void putResponse( int uid, Response response ){
		responses.put( uid, response );
	}
	
	private Response get( int uid ){
		return responses.remove( (Integer)uid );
	}

}
