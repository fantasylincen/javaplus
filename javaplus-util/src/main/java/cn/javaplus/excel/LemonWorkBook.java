package cn.javaplus.excel;

import java.io.FileInputStream;

import jxl.Sheet;
import jxl.Workbook;
import cn.javaplus.util.Closer;

/**
 * 工作簿, 用户读取配置表.
 * @author 	林岑
 * @since	2012年3月23日 11:17:33
 *
 */
public class LemonWorkBook {

	/**
	 * 工作本
	 */
	private Workbook workBook;

	public LemonWorkBook(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( fileName );
			this.workBook = Workbook.getWorkbook( fis );
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(fileName);
		} finally {
			Closer.close(fis);
		}
	}

	/**
	 * 获取第index个表
	 * @param index
	 * @return
	 */
	public LemonSheet getSheet(int index) {
		Sheet sheet = this.workBook.getSheet(index);
		return new NormalSheet(sheet);
	}
}
