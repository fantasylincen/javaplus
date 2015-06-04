package cn.mxz.base.telnet.command.system;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;

import cn.javaplus.util.Closer;
import cn.mxz.base.telnet.TelnetCommand;

/**
 * 该类用于执行 管理平台输入的Groovy脚本
 *
 * @author 林岑
 *
 */
@Component("telnetCommand:rungroovyscript")

public class RunGroovyScript implements TelnetCommand {

	private class WatchDogThread extends Thread {

		private RunScriptThread	thread;

		private long	startTime;

		private PrintWriter	out;

		private WatchDogThread(RunScriptThread thread, PrintWriter out) {

			this.thread = thread;

			this.out = out;

			startTime = System.currentTimeMillis();
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {

			while(true) {

				long now = System.currentTimeMillis();

				long alive = now - startTime;

				if(alive > thread.getTimeOut()) {

					out.println("脚本执行超时!");

					thread.stop();

					break;
				}

				cn.javaplus.util.Util.Thread.sleep(100);
			}
		}

	}

	private class RunScriptThread extends Thread {

		private GroovyObject	o;

		private int	timeOut;

		private PrintWriter	out;

		private RunScriptThread(GroovyObject o, PrintWriter out) {

			this.o = o;

			this.out = out;
		}

		@Override
		public void run() {

			try {

				o.invokeMethod("run", out);

			} catch (Throwable e) {

				e.printStackTrace(out);
			}
		}

		public void setTimeOut(int timeOut) {
			this.timeOut = timeOut;
		}

		public int getTimeOut() {
			return timeOut;
		}
	}

	@SuppressWarnings({  "rawtypes" })
	@Override
	public void run(PrintWriter out, String... args) {

		String script = args[0];

		ClassLoader parent = ClassLoader.getSystemClassLoader();

		GroovyClassLoader loader = new GroovyClassLoader(parent);
		
		String templet = getTemplet("res/script.txt");

		templet= templet.replaceAll("SCRIPT", script);

		Class gclass = loader.parseClass(templet);

		GroovyObject groovyObject;

		try {

			groovyObject = (GroovyObject) gclass.newInstance();

		} catch (Exception e) {

			throw new RuntimeException(e);

		} finally {
			loader.clearCache();
//			Closer.close(loader);
		}

		runInThread(groovyObject, out);
	}

	private void runInThread(final GroovyObject o, PrintWriter out) {

		RunScriptThread thread = new RunScriptThread(o, out);

		thread.setTimeOut(10000);

		Thread t2 = new WatchDogThread(thread, out);

		thread.start();

		t2.start();


		try {

			thread.join();

		} catch (InterruptedException e) {

			throw new RuntimeException();
		}
	}

	private String getTemplet(String string) {

		FileReader f = null;

		StringBuilder sb = new StringBuilder();

		BufferedReader br = null;

		try {

			f = new FileReader(string);

			br = new BufferedReader(f);

			while (true) {

				String line = br.readLine();

				if (line == null) {

					break;
				}

				sb.append(line);

				sb.append("\r");

			}

			return sb.toString();


		} catch (Exception e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(br);

			Closer.close(f);
		}
	}

}
