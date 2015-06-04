package game.events.all;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import user.UserInfo;
import util.UtilBase;
import game.events.EventBase;
import game.events.EventDescrip;
import game.log.Logs;

@EventDescrip(desc = "玩家请求配置表")
public class UserRequestConfigEvent extends EventBase{

	private FileInputStream input = null;

	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		
		String fileName = UtilBase.decodeString( buf );
		
		if( fileName.isEmpty() )
			return;
		
		String file[] 		= fileName.split(",");
		
		long start = System.nanoTime();
		
		for( int i = 0; i < file.length; i++ )
		{
			input = new FileInputStream( "resource/" + file[i] + ".xml" );
			
			if( input == null ){
				Logs.error( user, "申请配置表出错    没有找到<" + file[i] + ">这个配置表！" );
				return;
			}
			
			synchronized( this ){
				
				long startTime = System.nanoTime();
				while( input.available() > 0 ){
					
					if( (System.nanoTime() - startTime)/1000000000f < 1.5 )
					{
						continue;
					}
					startTime = System.nanoTime();
					
					ByteBuffer buffer = buildEmptyPackage( 40960 );
					
					UtilBase.encodeString( buffer, file[i] );
					
					boolean isOk = true;
					int size = input.available();
					if( input.available() > 20000 ){
						size = 20000;
						isOk = false;
					}
					buffer.putInt( size );
					
					byte[] bytes = new byte[ size ]; 
					input.read(bytes); 
					
					buffer.put( bytes );
					buffer.put( (byte)(isOk ? 1 : 0) );
					
					sendPackage( user.getCon(), buffer );
					
				}
			}
			
		}
		
		Logs.error( user, "读取配置表 用时 ："  + ( System.nanoTime() - start ) / 1000000000f );
	}

}
