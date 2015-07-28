package org.javaplus.clickscreen.excel;
//package cn.javaplus.clickscreen.excel;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import jxl.Cell;
//import cn.javaplus.clickscreen.util.Lists;
//
//public class SheetImpl implements Sheet {
//
//	public class Field {
//		String type;
//		String name;
//		String dsc;
//	}
//
//	public class Rect {
//		int x, y, w, h;
//
//		public Rect(int x, int y, int w, int h) {
//			this.x = x;
//			this.y = y;
//			this.w = w;
//			this.h = h;
//		}
//	}
//
//	private jxl.Sheet sheet;
//
//	public SheetImpl(jxl.Sheet sheet) {
//		this.sheet = sheet;
//		dataRect = getDataRect();
//		field = getFields();
//
//		loadCache();
//
//	}
//
//	private void loadCache() {
//		cache = new HashMap<String, Row>();
//		List<Row> all = getAll();
//		for (Row row : all) {
//			String name = field.get(0).name;
//			cache.put(row.get(name), row);
//		}
//	}
//
//	private List<Field> getFields() {
//		ArrayList<Field> ls = Lists.newArrayList();
//		int x = dataRect.x;
//		for (int i = 0; i < dataRect.w; i++) {
//			ls.add(getField(x));
//			x++;
//		}
//		return ls;
//	}
//
//	private Field getField(int x) {
//		Field f = new Field();
//		f.dsc = sheet.getCell(x, Y - 3).getContents();
//		f.type = sheet.getCell(x, Y - 2).getContents();
//		f.name = sheet.getCell(x, Y - 1).getContents();
//		return f;
//	}
//
//	private static int X = 0;
//	private static int Y = 3;
//	private Rect dataRect;
//	private List<Field> field;
//
//	private Map<String, Row> cache;
//
//	private Rect getDataRect() {
//		return new Rect(X, Y, findW(), findH());
//	}
//
//	private int findW() {
//		int x = X;
//		int y = Y;
//		while (true) {
//			if (!hasData(x, y)) {
//				break;
//			}
//			x++;
//		}
//		return x - X;
//	}
//
//	private int findH() {
//		int x = X;
//		int y = Y;
//		while (true) {
//			if (!hasData(x, y)) {
//				break;
//			}
//			y++;
//		}
//		return y - Y;
//	}
//
//	private boolean hasData(int x, int y) {
//		Cell c;
//		try {
//			c = sheet.getCell(x, y);
//		} catch (Exception e) {
//			return false;
//		}
//		String content = c.getContents();
//		return content != null && !content.isEmpty();
//	}
//
//	@Override
//	public List<Row> getAll() {
//
//		ArrayList<Row> ls = Lists.newArrayList();
//		int y = dataRect.y;
//		for (int i = 0; i < dataRect.h; i++) {
//			ls.add(getDataRow(y));
//			y++;
//		}
//		return ls;
//	}
//
//	private Row getDataRow(int y) {
//		int x = dataRect.x;
//		RowImpl r = new RowImpl();
//		for (int i = 0; i < dataRect.w; i++) {
//			String c = sheet.getCell(x, y).getContents();
//			r.put(field.get(x).name, c);
//			x++;
//		}
//		return r;
//	}
//
//	@Override
//	public Row find(String fieldName, Object value) {
//		int colIndex = findColIndex(fieldName);
//		if (colIndex == -1)
//			return null;
//
//		int y = dataRect.y;
//		for (int i = 0; i < dataRect.h; i++) {
//
//			Cell cell = sheet.getCell(colIndex, y);
//			if (cell.getContents().equals(value.toString())) {
//				return getDataRow(y);
//			}
//			y++;
//		}
//		return null;
//	}
//
//	private int findColIndex(String fieldName) {
//		for (int i = 0; i < field.size(); i++) {
//			Field f = field.get(i);
//			if (fieldName.equals(f.name)) {
//				return i;
//			}
//		}
//		return -1;
//	}
//
//	@Override
//	public Row get(Object key) {
//		return cache.get(key.toString());
//	}
//}
