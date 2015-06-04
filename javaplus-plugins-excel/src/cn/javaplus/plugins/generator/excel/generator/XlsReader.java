package cn.javaplus.plugins.generator.excel.generator;

import java.io.IOException;

import _util.LemonSheet;
import _util.LemonWorkBook;

public class XlsReader {

	public Constent getConstent(String sourceExcelFile, int sheetIndex, String className) throws IOException {
		LemonWorkBook lb = new LemonWorkBook(sourceExcelFile);
		LemonSheet s = lb.getSheet(sheetIndex);
		Constent c = get(s, className);
		return c;
	}
	public Constent get(LemonSheet s, String className) {

		String[][] c = s.autoGet2();

		String[] explains = c[0];

		String[] marks = c[1];

		String[] types = c[2];

		String[] filedNames = c[3];

		int d = 4;

		String[][] constent = new String[c.length - d][c[0].length];

		for (int i = d; i < c.length; i++) {

			for (int j = 0; j < c[0].length; j++) {

				constent[i - d][j] = c[i][j].replaceAll("\n", "[newline]");
			}
		}
		return new Constent(explains, marks, constent, types, filedNames, className);
	}
}
