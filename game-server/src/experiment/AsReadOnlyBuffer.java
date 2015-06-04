package experiment;

import java.nio.ByteBuffer;

public class AsReadOnlyBuffer {

	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate( 1024 );
		for( int i = 0; i < 10; i++ ){
			buf.putInt( 2 );
		}
		
		buf.flip();
		
		ByteBuffer bufReadOnly = buf.asReadOnlyBuffer();
		
		for( int i = 0; i < 3; i++ ){
			System.out.println( buf.getInt() );
		}
		
		for( int i = 0; i < 10; i++ ){
			System.out.println( bufReadOnly.getInt() );
		}
		System.out.println( "buf " + buf );
		System.out.println( "bufReadOnly " + bufReadOnly );
		
		
	}
}
