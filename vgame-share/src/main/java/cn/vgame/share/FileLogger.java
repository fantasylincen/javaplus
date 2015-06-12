package cn.vgame.share;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.javaplus.log.SystemOutputStream;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

public class FileLogger implements Out {

	public class LogDataImpl implements LogData {

		private final String head;
		private Date time;
		private String log;

		public LogDataImpl(String head, Object... message) {
			this.head = head;
			time = new Date(System.currentTimeMillis());

			if (message == null)
				log = "";
			else
				log = Util.Collection.linkWith(",", message);
		}

		@Override
		public int compareTo(LogData o) {
			return time.compareTo(o.getTime());
		}

		@Override
		public Date getTime() {
			return time;
		}

		@Override
		public String getHead() {
			return head;
		}

		@Override
		public String getLog() {
			return log;
		}

	}

	static final SimpleDateFormat F = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public interface LogData extends Comparable<LogData> {

		Date getTime();

		String getHead();

		String getLog();
	}

	/**
	 * 这个线程负责将队列中的日志, 输出到日志中
	 * 
	 * @author 林岑
	 * 
	 */
	public class PushThread extends Thread {

		@Override
		public void run() {

			while (true) {
				try {
					pushToDb();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Util.Thread.sleep(3000);
			}
		}

		private void pushToDb() {
			ArrayList<LogData> ls = new ArrayList<LogData>(queue);
			queue.clear();
			appendToFile(ls);
		}

		private void appendToFile(ArrayList<LogData> ls) {

			StringPrinter sp = new StringPrinter();

			Collections.sort(ls);

			for (LogData dt : ls) {
				Log.print(dt.getHead(), sp, dt.getLog());
			}
			write(sp.toString());
		}

		private void write(String log) {

			OutputStream fos = null;
			OutputStreamWriter osw = null;
			try {
				Util.File.mkdirs(file);
				
				String pathname = file + "/" + getDate() + ".log";
				java.io.File f = new java.io.File(pathname);
				if (!f.exists()) {
					int lastIndexOf = file.lastIndexOf("/");
					if (lastIndexOf == -1) {
						lastIndexOf = file.lastIndexOf("\\");
					}
					java.io.File path = new java.io.File(file.substring(0,
							lastIndexOf));
					path.mkdirs();
					f.createNewFile();
				}

				fos = new FileOutputStream(f, true);
				osw = new OutputStreamWriter(fos, "UTF-8");
				osw.write(log);
				osw.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				Closer.close(fos);
			}

		}
		
		private String getDate() {
			return DATE_FORMAT.format(new Date(System.currentTimeMillis()));
		}
	}
	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	ConcurrentLinkedQueue<LogData> queue = new ConcurrentLinkedQueue<LogData>();

	private final String file;

	public FileLogger(String file) {
		this.file = file;
		new PushThread().start();
	}

	SystemOutputStream out = new SystemOutputStream();

	@Override
	public void println(String head, Object... message) {
		Log.print(head, out, message);
		LogData data = new LogDataImpl(head, message);
		queue.offer(data);
	}

}
