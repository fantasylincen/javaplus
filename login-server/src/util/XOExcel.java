package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Excel工具类<br>
 * 可以生产Excel表格
 * @author deng
 *
 */
public class XOExcel {

	// 表名
	private String 	_tableName = "";
	
	// 写入表格
	private WritableWorkbook _book 	= null;
	
	// 
	private WritableSheet _sheet	= null;	
	
	private List<String> _labelName = new ArrayList<String>();
	
	/**
	 * @param tableName 表名
	 */
	public XOExcel( String tableName ){
		_tableName 	= tableName;
	}
	
	/**
	 * 这个表是否存在
	 * @return
	 */
	public boolean exists(){
		return false;
	}
	
	public void create( String sheetName ){
		try {
			_book 		= Workbook.createWorkbook( new File( _tableName + ".xls" ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		//生成名为“第一页”的工作表，参数0表示这是第一页 
		_sheet				= _book.createSheet( sheetName, 0 );
	}
	
	public void setLabelName( String ... strings ){
		
		if( strings == null || strings.length == 0 )
			return;
		//将定义好的单元格添加到工作表中 
		try {
					
			for( int i = 0; i < strings.length; i++ ){
				
				Label label	= new Label( i, 0, strings[i] );
				
				_sheet.addCell( label );
				_labelName.add( strings[i] );
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} 
	}
	
	public void setLabel( String name, String arg, int idx ) throws RowsExceededException, WriteException{
		int index = _labelName.indexOf(name);
		if( index < 0 || index > _labelName.size() )
			return ;
		Label label	= new Label( index, idx + 1, arg );
		_sheet.addCell( label );
	}
	
	public void setLabel( String name, int arg, int idx ) throws RowsExceededException, WriteException{
		int index = _labelName.indexOf(name);
		if( index < 0 || index > _labelName.size() )
			return ;
		Number label = new Number( index, idx + 1, arg );
		_sheet.addCell( label );
	}

	/**
	 * 完成 
	 * @throws WriteException
	 * @throws IOException
	 */
	public void finish() throws WriteException, IOException {
		_book.write();
		_book.close();
	}
	
}
