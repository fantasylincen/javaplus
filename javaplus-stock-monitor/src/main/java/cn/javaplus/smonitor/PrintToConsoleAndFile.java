package cn.javaplus.smonitor;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import cn.javaplus.log.IPrintStream;
import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.javaplus.log.SystemOutPutStream;

public class PrintToConsoleAndFile implements Out {

	public class FileOut implements IPrintStream {
		private PrintStream out;

		public FileOut() {
			try {
				out = new PrintStream(
						"C:\\Users\\Administrator\\Desktop\\xx.csv");
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void println(Object obj) {
			out.println(obj);
		}

	}

	IPrintStream fileout = new FileOut();
	IPrintStream sysout = new SystemOutPutStream();

	@Override
	public void println(String head, Object... message) {
		Log.print(head, sysout, message);
		Log.print(head, fileout, message);
	}

}
