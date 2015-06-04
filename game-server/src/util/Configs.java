package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * txt配置表解析
 * @author DXF
 */
public class Configs {

	private List<Data> content = new ArrayList<Data>();
	
	public Configs( String file ){
		
		try {
			
			BufferedReader in 	= new BufferedReader( new FileReader( file ) );

			String temp			= null;

			while ((temp = in.readLine()) != null) {
				
				int endIndex 	= temp.indexOf( "#" );
				if( endIndex != -1 )
					temp		= temp.substring( 0, endIndex );
				String[] list	= temp.split( "=" );
				if( list.length != 2 ) continue;
				Data data		= new Data();
				data.key		= list[0].trim();
				data.value		= list[1].trim();
				content.add( data );
			}
			
			in.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}   
	}
	
	public String getValue( String key ){
		for( Data d : content ){
			if( d.key.equals(key) ) return d.value;
		}
		return null;
	}
	
	public static void main( String[] args ){
		
		Configs c = new Configs( "config/aaaa.txt" );
		
		
		System.out.println( c.getValue( "name" ) );
		System.out.println( c.getValue( "attack" ) );
		System.out.println( c.getValue( "hp" ) );
		
	}
	
}

class Data{
	
	public String 	key;
	
	public String 	value;
}
