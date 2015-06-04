package _util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class LemonWorkBook {

	private Workbook workBook;

	public LemonWorkBook(String fileName) {
		InputStream fis = null;
		try {
			fis = new FileInputStream( fileName );
			fis = new BufferedInputStream(fis);
			this.workBook = Workbook.getWorkbook( fis );

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(fileName);
		} finally {
			Closer.close(fis);
		}
	}

	public LemonSheet getSheet(int index) {
		Sheet sheet = this.workBook.getSheet(index);
		return new NormalSheet(sheet);
	}
}
