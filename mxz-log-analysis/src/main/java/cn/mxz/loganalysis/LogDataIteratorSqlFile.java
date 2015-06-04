//package cn.mxz.loganalysis;
//
//import java.net.URL;
//import java.util.Iterator;
//
//import cn.javaplus.exception.UnImplMethodException;
//import cn.javaplus.io.LinesReader;
//import cn.javaplus.util.Util;
//
//import com.google.common.io.Resources;
//
//public class LogDataIteratorSqlFile implements Iterator<LogData> {
//
//	private LinesReader linesReader;
//	private Iterator<String> iterator;
//	private LogData next;
//
//	public LogDataIteratorSqlFile() {
//		try {
//			URL resource = Resources.getResource("logtable.sql");
//			linesReader = new LinesReader(resource.openStream());
//			iterator = linesReader.iterator();
//		} catch (Exception e) {
//			throw Util.Exception.toRuntimeException(e);
//		}
//	}
//	
//	public boolean hasNext() {
//		if(next != null) {
//			return true;
//		}
//		while (iterator.hasNext()) {
//			String s = iterator.next();
//			if (s.startsWith("INSERT INTO `logtable` VALUES ('")) {
//				next = new LogDataImpl2(s);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public LogData next() {
//		LogData n = next;
//		next = null;
//		return n;
//	}
//
//	public void remove() {
//		throw new UnImplMethodException();
//	}
//
//}
