package experiment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import datalogging.ConsumelogF;

import manager.DWType;


public class Consumelog {
	
	private final String pathHead = "log/";
	
	private Map<String, TotalData> ls = new ConcurrentHashMap<String, TotalData>();
	
	private List<Data> read( String file ){
		List<Data> list		= new ArrayList<Data>();
		try {
			BufferedReader in 	= new BufferedReader( new FileReader( file ) );
			String temp			= null;
			while ((temp = in.readLine()) != null) {
				String[] content = temp.split( "," );
				if( !content[2].equals( "4" ) ) continue;
				if( Integer.parseInt( content[6] ) >= 0 ) continue;
				Data d		= new Data();
				d.name 		= content[4];
				d.number	= -Integer.parseInt( content[6] );
				d.type		= DWType.fromNumber( Integer.parseInt( content[7] ) ).getType();
				list.add( d );
//				System.out.println( d.name + "," + d.number + "," + d.type );
			}
			in.close();
		} catch (IOException e) { e.printStackTrace(); }
		return list;
	}
	
	public void run(){
		File file 			= new File( pathHead );
		String pathName[]	= file.list();
		for( String path : pathName ){
			List<Data> l	= read( pathHead + path );
			System.out.println( path + " ---------------> 读取完成 一共" + l.size() + "条" );
			// 开始塞入
			stuffing( l );
			System.out.println( path + " ---------------> 已塞入." );
		}
		
		System.out.println( "开始生成Execl表" );
		try {
			produceExecl( "C:/Users/Administrator/Desktop/消费记录.xls" );
		} catch ( WriteException | IOException e) { e.printStackTrace(); }
		System.out.println( "完成." );
	}
	
	private void produceExecl( String path ) throws IOException, RowsExceededException, WriteException {
		WritableWorkbook book 	= Workbook.createWorkbook( new File( path ) ); 
		//生成名为“第一页”的工作表，参数0表示这是第一页 
		WritableSheet sheet		= book.createSheet( "9.27~10.15", 0 );
		
		// 添加 字段名
		Label label				= new Label( 0, 0, ConsumelogF.PLAYER_NICKNAME );
		sheet.addCell( label ); 
		for( int i = 0; i < ConsumelogF.MAX; i++ ){
			label				= new Label( i+1, 0, ConsumelogF.get(i) );
			sheet.addCell( label ); 
		}
		label				= new Label( ConsumelogF.MAX + 1, 0, "其他" );
		sheet.addCell( label ); 
		
		WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);  
	    headerFormat.setAlignment( Alignment.CENTRE );  
		
		Number number	= null;

		int i 			= 1;
		for( TotalData x : ls.values() ){
			
			label 		= new Label( 0, i, x.name, headerFormat );
			sheet.addCell( label );
			
			for( int o = 0; o < x.number.length; o++){
				number 		= new Number( o+1, i, x.number[o], headerFormat );
				sheet.addCell( number );
			}
			++i;
		}
		//写入数据并关闭文件 
		book.write(); 
		book.close(); 
	}

	private void stuffing( List<Data> l ) {
		for( Data d : l ){
			TotalData t = ls.get( d.name );
			if( t != null ){
				t.number[ConsumelogF.getToIndex( d.type )] += d.number;
			}else{
				t = new TotalData();
				t.name = d.name;
				t.number[ConsumelogF.getToIndex( d.type )] = d.number;
				ls.put( d.name, t );
			}
		}
	}

	public static void main( String[] args ){
		Consumelog c = new Consumelog();
		c.run();
	}
}

class Data{
	String name;
	String type;
	int number;
}

class TotalData{
	String name;
	int number[] = new int[ConsumelogF.MAX+1];
}